package com.manji.sheet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.plugin.activerecord.Record;
import com.manji.sheet.base.UrlCode;
import com.manji.sheet.intercepter.LocalIntercepter;
import com.manji.sheet.model.bean.Menu;
import com.manji.sheet.model.bean.MenuInfo;
import com.manji.sheet.model.reqParam.SheetInfoParam;
import com.manji.sheet.service.ManageService;
import com.manji.sheet.utils.HttpClientUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 本地登陆相关
 * @author Administrator
 *
 */
public class IndexController extends Controller{
	
    private ManageService manageService = new ManageService();
	public void index(){
		
		
//		HttpSession session =getSession();
//		Record user = getSessionAttr("user");
		String token = getCookie("ssoToken");
		if(token !=null){
			//验证token
			String url_auth = UrlCode.URL_AUTH+"system=sheet&ssoToken="+token;
			String authData = HttpClientUtils.postMethod(url_auth);
			if(JSONObject.fromObject(authData).get("code").equals("0000")){
				redirect("/home");
			}
		}
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
		
	}
	/**
	 * 退出登录
	 */
	public void loginOut(){
		
		removeSessionAttr("user");
		removeCookie("ssoToken");
		redirect("/");
		
	}
	public void login() throws IOException{
		try{
			String inputRandomCode = getPara("code");
			boolean loginSuccess = CaptchaRender.validate(this, inputRandomCode.toUpperCase(), RANDOM_CODE_KEY);  
			//1. 先拿名字和密码
			String name = getPara("username");
			String password = getPara("password");
			//2.发送post请求，拿到一个token
			String url_login = UrlCode.URL_LOGIN+"name="+name+"&password="+password;
			String loginData = HttpClientUtils.postMethod(url_login);
			//System.out.println(loginData);
			//转换JSON
			JSONObject login_json = JSONObject.fromObject(loginData);
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
				//System.out.println(login_json.get("data"));
				String token = login_json.get("data").toString();
//				http://192.168.0.68:8080/singleSign/login?name=huanghan&password=123
				//3.token..cookie,把得到的token存在cookie中
				//System.out.println("token:"+token);
				removeCookie("ssoToken");
				setCookie("ssoToken",token,1000*60*60*24);
				//4.用这个TOKEN发起一个请求，项目名称
				String url_auth = UrlCode.URL_AUTH+"system=sheet&ssoToken="+token;
				String authData = HttpClientUtils.postMethod(url_auth);
				//System.out.println(authData);
				JSONObject auth_json = JSONObject.fromObject(authData);
				JSONObject roleInfo = auth_json.getJSONObject("data").getJSONObject("roleInfo");
				JSONArray menuInfo = auth_json.getJSONObject("data").getJSONArray("menuInfo");
				JSONObject userInfo = auth_json.getJSONObject("data").getJSONObject("userInfo");
				//System.out.println(userInfo.get("username"));
//				http://192.168.0.68:8080/singleSign/auth?system=sheet&ssoToken=2AEC16D5A590964C0F3CA30FCA000507
				//获取返回的用户信息、权限信息、角色信息
				//1用户信息
				/*Record user = new Record().set("username", userInfo.get("username")).set("dept_name", userInfo.get("dept_name"))
										.set("user_id", userInfo.get("user_id")).set("status", userInfo.get("status"));*/
				setSessionAttr("user", userInfo);
				//2.角色信息
				setSessionAttr("role", roleInfo);
				//3.目录信息
				//处理目录信息
				List<MenuInfo> menu = getMenu(menuInfo);
				setSessionAttr("menu", menu);

				Record menuRecord=new Record();
				menuRecord.set("acceptanceUrl","");
				menuRecord.set("alreadyUrl","");
				menuRecord.set("pushUrl","");
				for(MenuInfo menuInfoChild:menu){
					if(menuInfoChild.getSys_id().equals("1") && menuInfoChild.getTitle().equals("工单管理") ){
						for(Menu menuChild: menuInfoChild.getChild()){
							if(menuChild.getAddress().equals("01_01") || menuChild.getTitle().equals("工单受理")){
								menuRecord.set("acceptanceUrl",menuChild.getUrl());
							}else if(menuChild.getAddress().equals("01_02") || menuChild.getTitle().equals("工单处理")){
								menuRecord.set("alreadyUrl",menuChild.getUrl());
							}else if(menuChild.getAddress().equals("01_03") || menuChild.getTitle().equals("结果推送")){
								menuRecord.set("pushUrl",menuChild.getUrl());
							}
						}
					}
				}
				setSessionAttr("menuRecord", menuRecord);
				redirect("/home");
			}
		}catch(Exception e){
			setSessionAttr("aa", "ERROR");
			redirect("/");
		}
	}
	/**
	 * 处理菜单
	 * @param menuInfo
	 * @return
	 */
	public List<MenuInfo> getMenu(JSONArray menuInfo){
		List<MenuInfo> menu = new ArrayList<MenuInfo>();
		for(int i=0;i<menuInfo.size();i++){
			List<Menu> childList = new ArrayList<Menu>();
			JSONObject menui = (JSONObject) menuInfo.get(i);
			MenuInfo m = new MenuInfo();
			if(menui.get("layer").toString().equals("1")){
				m.setId(Integer.parseInt(menui.get("id").toString()));
				m.setTitle(menui.get("title").toString());
				m.setSort(Integer.parseInt(menui.get("sort").toString()));
				m.setAddress(menui.get("address").toString());
				m.setSys_id(menui.get("sys_id").toString());
				m.setLayer(Integer.parseInt(menui.get("layer").toString()));
				m.setIs_del(Integer.parseInt(menui.get("is_del").toString()));
				m.setId(Integer.parseInt(menui.get("type").toString()));
				m.setUrl(menui.get("url").toString());
				for(int j=0;j<menuInfo.size();j++){
					JSONObject menuj = (JSONObject) menuInfo.get(j);
					Menu child = new Menu();
					if(menuj.get("address").toString().indexOf(menui.get("address").toString()+"_") != -1){
						child.setId(Integer.parseInt(menuj.get("id").toString()));
						child.setTitle(menuj.get("title").toString());
						child.setSort(Integer.parseInt(menuj.get("sort").toString()));
						child.setAddress(menuj.get("address").toString());
						child.setSys_id(menuj.get("sys_id").toString());
						child.setLayer(Integer.parseInt(menuj.get("layer").toString()));
						child.setIs_del(Integer.parseInt(menuj.get("is_del").toString()));
						child.setId(Integer.parseInt(menuj.get("type").toString()));
						child.setUrl(menuj.get("url").toString());
						childList.add(child);

					}
				}
				m.setChild(childList);
				menu.add(m);
			}
		}
		return menu;
	}
	
	@Before(LocalIntercepter.class)
	public void home(){
		JSONObject user = getSessionAttr("user");
    	List<Record> exeDeptList = manageService.newSheet(Integer.parseInt(user.get("dept_id").toString()));
    	setAttr("exeDeptList",exeDeptList);
    	setAttr("status","home");
    	
    	Map<String, Object> map=manageService.workStatistics();
    	Record sheetStatistics=(Record) map.get("sheetStatistics");
        Record sheetStatisticsNewTime=(Record) map.get("sheetStatisticsNewTime");
        
        setAttr("sheetStatistics",sheetStatistics);
    	setAttr("sheetStatisticsNewTime",sheetStatisticsNewTime);
    	setAttr("title","首页");
		render("home.html");
		
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

}
