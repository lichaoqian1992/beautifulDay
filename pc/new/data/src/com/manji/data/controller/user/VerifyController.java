package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.VerifyPageVo;
import com.manji.data.service.UserService;

public class VerifyController extends Controller{

	private UserService service =new UserService();
	
	public void index(){
		
		VerifyPageVo vo =getBean(VerifyPageVo.class,"");
		
		Page<Record> page =service.getVerifyPage(vo);
		
		setAttr("page",page);
		
		render("list.html");
	}
	
	
}
