package com.manji.advert.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.User;
import com.manji.advert.service.UserService;
@Before(LoginInterceptor.class)
public class UserController extends Controller{

	UserService service = new UserService();
	
	/**
	 * 查询用户信息
	 */
	public void toUser(){
		render("user.html");
	}
	/**
	 * 添加用户页面
	 */
	public void addUser(){
		System.out.println(getSessionAttr("user"));
		render("user_add.html");
		
	}
	/**
	 * 添加用户角色
	 */
	public void add(){
		User u = getBean(User.class,"");
		int i = service.saveUser(u);
		if(i>0){
			setAttr("aa", "");
			toUser();
		}else{
			setAttr("aa", "EXIT");
			addUser();
		}
		
	}
	/**
	 * 删除用户
	 */
	public void delUser(){
		
		String id = getPara("id");
		if(service.delUser(id)>0){
			
		}
		
	}
}
