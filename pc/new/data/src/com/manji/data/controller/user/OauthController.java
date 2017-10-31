package com.manji.data.controller.user;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.model.request.user.OauthPageVo;
import com.manji.data.service.UserService;

public class OauthController extends Controller{

	private UserService service =new UserService();
	
	
	public void index(){
		
		OauthPageVo vo =getBean(OauthPageVo.class,"");
		
		Page<Record> page =service.getOauthPage(vo);
		
		setAttr("page",page);
		
		render("list.html");
		
	}
	
}
