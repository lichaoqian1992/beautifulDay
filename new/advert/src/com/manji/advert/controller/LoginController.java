package com.manji.advert.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.HttpClientUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.Logs;
import com.manji.advert.model.User;
import com.manji.advert.service.LoginService;
import com.manji.advert.service.LogsService;
import com.manji.advert.service.UserService;
import com.manji.advert.utils.MD5util;

import net.sf.json.JSONObject;
public class LoginController extends Controller{
	
	LoginService service = new LoginService();
	UserService uservice = new UserService();
	LogsService lservice = new LogsService();
	
	public void index(){
		render("login.html");
	}

	/**
	 * 登录
	 */
	public void login(){
		String userName = getPara("username");
		String password = getPara("password");
		
		//加密密码
		String newPassword = MD5util.getMD5(password);
//		//查询数据   192.168.0.47:8002/api/Login?LoginName=admin&Pwd=123456&tag=1
//		String url = "http://192.168.0.47:8002/api/Login?LoginName="+userName+"&Pwd="+password+"&tag=1";
//		//System.out.println(url);
//		try {
//			String message = com.manji.advert.utils.HttpClientUtils.GetAPI(url);
//			JSONObject json = JSONObject.fromObject(message);
//			System.out.println(json);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		
		try{
			List<Record> user = service.findUser(userName,newPassword);
			if(user != null && user.size() == 1){
				//登录成功，记录session
				setSessionAttr("user", user.get(0));
				setSessionAttr("menu", service.findMenu());
				//记录登陆次数
				User u = new User();
				u.setUserName(userName);
				u.setPassword(newPassword);
				u.setCount(Integer.parseInt(user.get(0).get("login_count").toString())+1);
				uservice.updateUser(u);
				//记录登录日志
				Logs logs = new Logs();
				String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				logs.setUserName(userName);
				logs.setContent(userName+"在"+d+"登录系统");
				logs.setCreateTime(d);
				lservice.saveLogs(logs);
				redirect("/index/");
			}else{
				setAttr("aa", "ERROR");
			}
		}catch(Exception e){
			e.printStackTrace();
			setAttr("aa", "ERROR");
		}
	}
	/**
	 * 退出系统
	 */
	@Before(LoginInterceptor.class)
	public void logOut(){
		
		Record r = getSessionAttr("user");
		String userName = r.get("user_name").toString();
		//记录登录日志
		Logs logs = new Logs();
		String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		logs.setUserName(userName);
		logs.setContent(userName+"在"+d+"退出系统");
		logs.setCreateTime(d);
		lservice.saveLogs(logs);
		
		removeSessionAttr("user");
		index();
		
	}
	
}
