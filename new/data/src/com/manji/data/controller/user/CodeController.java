package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.UserCodePageVo;
import com.manji.data.service.UserService;

public class CodeController extends Controller{

	private UserService service =new UserService();
	
	public void index(){
		
		UserCodePageVo vo =getBean(UserCodePageVo.class,"");
		
		Page<Record> page =service.getCodePage(vo);
		
		setAttr("page",page);
		
		render("list.html");
	}
	
	
	
}
