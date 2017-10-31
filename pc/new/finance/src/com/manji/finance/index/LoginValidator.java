package com.manji.finance.index;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;


public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		
		 validateRequiredString("username", "username", "请输入用户名！");  
		 validateRequiredString("password", "password", "请输入用户密码！");  
		
	}

	@Override
	protected void handleError(Controller c) {
		
		c.render("login.html");
		
	}

}
