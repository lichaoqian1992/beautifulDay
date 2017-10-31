package com.manji.singleSign.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.bean.Sys;
import com.manji.singleSign.service.SystemService;

public class SystemController  extends Controller{

	private SystemService service =new SystemService();
	
	public void index(){
		
		Page<Record> page = service.query("",1,"");
		setAttr("systemList",page);
		render("list.html");
		
	}
	
	
	
	public void add(){
		
		Sys s =getBean(Sys.class,"");
		
		service.addSystem(s);
		
		redirect("/sys/index");
		
	}
	
	
	public void update(){
		
		Sys s =getBean(Sys.class,"");
		
		
		service.updSystem(s);
		
		setAttr("message", "SUCCESS");
		renderJson();
		
	}
	
	public void query(){
		String id = getPara("id");
		int pageNum = getParaToInt("pageNum");
		String name = getPara("name");
		Page<Record> page = service.query(id,pageNum,name);
		renderJson(page);
	}
	
}
