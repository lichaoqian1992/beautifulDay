package com.manji.circlemes.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.manji.circlemes.interceptor.SignInterceptor;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.service.UserService;
import com.manji.circlemes.validator.UserValidator;

@Before(SignInterceptor.class)
public class UserController extends Controller {

	private UserService service = new UserService();

	public void index() {

		Result r = new Result();
		r.setCode("0");
		r.setData("sb");

		renderJson(r);
	}

	/**
	 * 注册用户
	 */
	@Before(UserValidator.class)
	public void register() {

		Result r = new Result();

		String name = getPara("name");

		r = service.registerUser(name);

		renderJson(r);

	}

	/**
	 * 获取用户信息
	 */
	@Before(UserValidator.class)
	public void query() {

		Result r = new Result();

		String name = getPara("name");

		r = service.queryUser(name);

		renderJson(r);

	}
	
	/**
	 * 禁用用户
	 * 
	 * name 禁用用户名
	 */
	
	@Before(UserValidator.class)
	public void disable(){
		
		Result r =new Result();
		
		String name =getPara("name");
		
		r =service.disableUser(name);
		
		renderJson(r);
		
		
	}
	
	
	/**
	 * 解冻用户
	 * 
	 * name 解冻用户名
	 */
	@Before(UserValidator.class)
	public void relieve(){
		
		Result r =new Result();
		
		String name =getPara("name");
		
		r =service.relieveUser(name);
		
		renderJson(r);
	}
	
	
	
	/**
	 * 强制下线
	 * 
	 * name 强制下线用户名
	 */
	@Before(UserValidator.class)
	public void disconnect(){
		
		Result r =new Result();
		
		String name =getPara("name");
		
		r =service.disconnect(name);
		
		renderJson(r);
		
		
	}
	
	
	
	
	/***
	 * 删除用户
	 * 
	 * name 删除用户名
	 */
	@Before(UserValidator.class)
	public void delete(){
		
		Result r =new Result();
		
		String name =getPara("name");
		
		r =service.deleteUser(name);
		
		renderJson(r);
		
	}
	

	public void test() {

		renderText("sb");
	}

}
