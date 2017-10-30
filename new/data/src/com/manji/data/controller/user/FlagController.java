package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.FlagPageVo;
import com.manji.data.service.UserService;

public class FlagController extends Controller{
	
	private UserService service =new UserService();
	
	
	public void index(){
		
		FlagPageVo vo =getBean(FlagPageVo.class,"");
		
		Page<Record> page =service.getFlagPage(vo);
		
		setAttr("page",page);
		
		render("list.html");
	}
	
	
}
