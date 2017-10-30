package com.manji.advert.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.service.LogsService;
import com.manji.advert.service.UserService;
@Before(LoginInterceptor.class)
public class RecordController extends Controller{

	LogsService service = new LogsService();
	UserService uservice = new UserService();
	
	public void index(){
		
		List<Record> list = uservice.findUser();
		setAttr("aa",list);
		render("record.html");
	}
	
	/**
	 * 查询日志
	 */
	public void getRecord(){
		String userName = getPara("userName","");
		String businessName = getPara("businessName","");
		String operationType = getPara("operationType","");
		int page = getParaToInt("page");
		Page<Record> p = service.queryLogs(userName,businessName,operationType,page);
		renderJson(p);
	}
}
