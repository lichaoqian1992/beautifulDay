package com.manji.data.controller.service;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.CaptchaRender;
import com.manji.data.model.request.common.Menu;
import com.manji.data.model.request.common.MenuInfo;
import com.manji.data.utils.AllConfig;
import com.manji.data.utils.HttpClientUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 本地登录相关
 * @author Administrator
 *
 */
@Clear
public class IndexController extends Controller{

	final String URL_LOGIN = AllConfig.URL_LOGIN;
	final String URL_AUTH = AllConfig.URL_AUTH;

	private static final String RANDOM_CODE_KEY = "1";
	
	public void index(){
		
		/*String token = getCookie("ssoToken");
		if(token != null){
			
			//验证token
			String url_auth = URL_AUTH+"system=sheet&ssoToken="+token;
			String authData = HttpClientUtils.postMethod(url_auth);
			if(JSONObject.fromObject(authData).get("code").equals("0000")){
				redirect("/home");
			}
		}
		
		String name = getPara("name");
		String pwd = getPara("password");
		
		if(name == null || "".equals(name)){
			
			name = "请输入用户名";
		}
		if(pwd == null || "".equals(pwd)){
			pwd = "请输入密码";
		}
		
		setSessionAttr("name",name);
		setSessionAttr("password",pwd);*/
		render("login.html");
		
	}
	
	
	/**
	 * 退出登录
	 */
	
	public void loginOut(){
		
		removeSessionAttr("user");
		removeCookie("ssoToken");
		redirect("/");
	}
	
	
	@SuppressWarnings("deprecation")
	public void login(){
		
		//获取验证码
		String inputRandomCode = getPara("code");
		//校验验证码
		boolean loginSuccess = CaptchaRender.validate(this, inputRandomCode.toUpperCase(),RANDOM_CODE_KEY);
		
		//1.先拿名字和密码
		String name = getPara("name");
		String password = getPara("password");
		//2.发送post请求，拿到一个token
		String url_login = URL_LOGIN + "name="+name+"&password="+password;
		String loginData = HttpClientUtils.postMethod(url_login);
		
		JSONObject login_json = JSONObject.fromObject(loginData);
		if(login_json.get("code").toString().equals("0101")){
			//账号不存在货密码不匹配
			setAttr("aa","NOTEXIT");
			
		}else if(login_json.get("code").toString().equals("0102")){
			//密码错误
			setAttr("aa","ERROR");
			
		}else if(!loginSuccess){
			//验证码错误
			setAttr("aa","YZM");
			
		}else{
			String token = login_json.get("data").toString();
			//3.把得到的token存在cookie中
			removeCookie("ssoToken");
			setCookie("ssoToken",token,1000*60*60*24);
			//4.用这个TOKEN发起一个请求，项目名称
			String url_auth = URL_AUTH + "system=data&ssoToken="+token;
			String authData = HttpClientUtils.postMethod(url_auth);
			
			JSONObject auth_json = JSONObject.fromObject(authData);
			if(auth_json.get("code").toString().equals("0206")){
				setAttr("aa", "NOAUTH");
				
			}else{
				JSONObject roleInfo = auth_json.getJSONObject("data").getJSONObject("roleInfo");
				JSONArray menuInfo = auth_json.getJSONObject("data").getJSONArray("menuInfo");
				JSONObject userInfo = auth_json.getJSONObject("data").getJSONObject("userInfo");
				
				//获取返回的用户信息，权限信息，角色信息

				//1.用户信息
				getSession().setMaxInactiveInterval(-1);
				setSessionAttr("user",userInfo);

				//2.角色信息
				setSessionAttr("role",roleInfo);
				setAttr("role", roleInfo);
				//3.目录信息
				List<MenuInfo> menu = getMenu(menuInfo);
				setSessionAttr("menu", menu);
				setAttr("menu", menu);

				setAttr("aa", "SUCCESS");
			}
		}
		renderJson();
	}
	/**
	 * 处理菜单
	 * @param menuInfo
	 * @return
	 */
	public List<MenuInfo> getMenu(JSONArray menuInfo){
		
		List<MenuInfo> menu = new ArrayList<MenuInfo>();
		if(menuInfo != null){
			for(int i=0;i<menuInfo.size();i++){
				List<Menu> childList = new ArrayList<Menu>();
				JSONObject menui = (JSONObject) menuInfo.get(i);
				MenuInfo m = new MenuInfo();
				if(menui.get("layer").toString().equals("1")){
					m.setName(menui.get("title").toString());
					m.setUrl(menui.get("url").toString());
					for(int j=0;j<menuInfo.size();j++){
						JSONObject menuj = (JSONObject) menuInfo.get(j);
						Menu child = new Menu();
						if(menuj.get("address").toString().indexOf(menui.get("address").toString()+"_") != -1){
							
							child.setName(menuj.get("title").toString());
							child.setUrl(menuj.get("url").toString());
							childList.add(child);

						}
					}
					m.setChild(childList);
					menu.add(m);
				}
				
			}
		}
		return menu;
	}
		
		/**
		 * 生成验证码
		 */
		public void img(){
			CaptchaRender img = new CaptchaRender(RANDOM_CODE_KEY);
			setSessionAttr("name",getPara("name"));
			setSessionAttr("password",getPara("password"));
			render(img);
		}
		
	}
	
	
	
