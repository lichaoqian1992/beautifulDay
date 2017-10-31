package com.manji.circlemes.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.model.common.ResultCode;

public class UserValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		
		System.out.println(getActionKey());
		
		 validateRequiredString("name", "0003", ResultCode.CODE0003);  
		
	}

	@Override
	protected void handleError(Controller c) {

			Result r =new Result();
			
			r.setCode("0003");
			r.setData(ResultCode.CODE0003);
			c.renderJson(r);  
			return ;
		
	}

	
	
	
	
}
