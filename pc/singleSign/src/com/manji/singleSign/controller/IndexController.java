package com.manji.singleSign.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.Code_Remark;
import com.manji.singleSign.model.Result;
import com.manji.singleSign.service.AuthService;
import com.manji.singleSign.service.LoginService;
import com.manji.singleSign.utils.DatesUtils;

import net.sf.json.JSONObject;
@Clear
public class IndexController extends Controller{

	private LoginService logService =new LoginService();
	private AuthService authService =new AuthService();
	
	
	
	public void index(){
		
		String name = getPara("name");
		String pwd = getPara("password");
		if(name == null || "".equals(name)){
			name="请输入用户名";
		}
		if(pwd == null || "".equals(pwd)){
			pwd="请输入密码";
		}
		setSessionAttr("name", name);
	    setSessionAttr("password", pwd);
		
		render("index.html");
		
	}
	
	public void toLogin(){
		
		try{
			String inputRandomCode = getPara("code");
			boolean loginSuccess = CaptchaRender.validate(this, inputRandomCode.toUpperCase(), RANDOM_CODE_KEY);  
			//1. 先拿名字和密码
			String name = getPara("username");
			String password = getPara("password");
			
			//2.发送post请求，拿到一个token
			Result r =logService.checkUserLogin(name,password);
			//转换JSON
			JSONObject login_json = JSONObject.fromObject(r);
			if(login_json.get("code").toString().equals("0101")){
				//账号不存在或密码不匹配
				setSessionAttr("aa", "NOTEXIT");
				redirect("/?name="+getPara("username")+"&password="+getPara("password"));
			}else if(login_json.get("code").toString().equals("0102")){
				setSessionAttr("aa", "ERROR");
				redirect("/?name="+getPara("username")+"&password="+getPara("password"));
			}else if(!loginSuccess){
				//验证码错误
				setSessionAttr("aa", "YZM");
				redirect("/?name="+getPara("username")+"&password="+getPara("password"));
			}else{
				//1.获取token
				String token =authService.generateToken((Record) r.getData());
				setCookie("ssoToken",token, 24*60*60*1000);
				//2.获取菜单信息
				Record role = logService.getRole(name);
				List<Record> menuInfo =authService.getMenuInfo(role.getInt("id"));
				if(menuInfo != null && menuInfo.size() > 0){
					setSessionAttr("menu", menuInfo);
					setSessionAttr("user", name);
					redirect("/user");
				}else{
					setSessionAttr("aa", "NOTAUTH");
					redirect("/?name="+getPara("username")+"&password="+getPara("password"));
				}
			}
		}catch(Exception e){
			setSessionAttr("aa", "ERROR");
			redirect("/");
		}
	}
	
	/**
	 * 登陆
	 */
	public void login(){
		
		String userName =getPara("name");
		String password =getPara("password");
		
		Result r =logService.checkUserLogin(userName,password);
		if("0000".equals(r.getCode())){
			
			String token =authService.generateToken((Record) r.getData());
//					try {
//						setCookie("ssotoken",URLEncoder.encode(token, "UTF-8"),1000*60*60*24);
						r.setData(token);
						
//					} catch (UnsupportedEncodingException e) {
//						
//						e.printStackTrace();
//					}
		}
		
		renderJson(r);
		
		
	}
	
	
	
	/**
	 * 验证令牌信息
	 */
	public void auth(){
				
		Result r =new Result();
		//系统名称
		String sysName =getPara("system");
		//验证令牌
		String token =getPara("ssoToken");
		
		//查询验证信息
		Record tokenInfo =authService.getTokenInfo(token);
		
		if(tokenInfo ==null){
			r.setCode("0201");
			r.setData(Code_Remark.SSO_CODE_0201);
			renderJson(r);
			return ;
		}else if(5<tokenInfo.getInt("count")){
			r.setCode("0208");
			r.setData(Code_Remark.SSO_CODE_0208);
			renderJson(r);
			return ;
			
		}
		
		int user_id =tokenInfo.getInt("user_id");
		//查询用户信息
		Record userInfo =authService.getUserInfo(user_id);
		
		if(userInfo ==null){
			r.setCode("0202");
			r.setData(Code_Remark.SSO_CODE_0202);
			renderJson(r);
			return ;
		}else if(!"1".equals(userInfo.get("status"))){
			
			r.setCode("0203");
			r.setData(Code_Remark.SSO_CODE_0203);
			renderJson(r);
			return ;
		}
		
		
		
//		setAttr("userinfo",userinfo);
		
		//查询项目信息
		Record sysInfo =authService.getSystemInfo(sysName);
		
		if(sysInfo ==null){
			r.setCode("0204");
			r.setData(Code_Remark.SSO_CODE_0204);
			renderJson(r);
			return ;
			
		}else if(!"1".equals(sysInfo.get("status"))){
			r.setCode("0205");
			r.setData(Code_Remark.SSO_CODE_0205);
			renderJson(r);
			return ;
			
		}
		
		
		int sys_id =sysInfo.getInt("id");
		
		//角色信息
		//单一用户一个系统只能拥有一个角色
		
		Record roleInfo =authService.getRoleInfo(user_id,sys_id);
		if(null ==roleInfo){
			r.setCode("0206");
			r.setData(Code_Remark.SSO_CODE_0206);
			renderJson(r);
			return ;
		}else if(!"1".equals(roleInfo.get("status"))){
			r.setCode("0207");
			r.setData(Code_Remark.SSO_CODE_0207);
			renderJson(r);
			return ;
		}
		
		
		//系统目录信息
		int role_id =roleInfo.getInt("id");
		
		List<Record> menuInfo =authService.getMenuInfo(role_id);
		
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("userInfo", userInfo);
		map.put("roleInfo", roleInfo);
		map.put("menuInfo", menuInfo);
		
		//返回成功信息
		r.setCode("0000");
		r.setData(map);
		
		renderJson(r);
		
		//后台记录登陆系统日志
		Record log =new Record();
		
		log.set("user_id", user_id).set("user_name", userInfo.get("username")).set("sys_id",sys_id).set("sys_name", sysInfo.get("system_name")).set("time",DatesUtils.time() );
		
		Db.save("sso_log", log);
		
		Db.update("update sso_token set count = "+(tokenInfo.getInt("count")+1)+"where  id =" +tokenInfo.getInt("id"));
		
	}
	
	/**
	 * 生成验证码
	 */
	private static final String RANDOM_CODE_KEY = "1";  
	public void img() {  
	    CaptchaRender img = new CaptchaRender(RANDOM_CODE_KEY); 
	    setSessionAttr("name", getPara("name"));
	    setSessionAttr("password", getPara("password"));
	    render(img);  
	} 
	
	/**
	 * 退出登录
	 */
	public void loginOut(){
		
		removeSessionAttr("user");
		redirect("/");
		
	}
	
	
	
	
	
}
