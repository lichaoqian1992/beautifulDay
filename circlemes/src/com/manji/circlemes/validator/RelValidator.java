package com.manji.circlemes.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.model.common.ResultCode;

public class RelValidator extends Validator{

	@Override
	protected void validate(Controller c) {

		String url =getActionKey();
		
		
		if("/rel/addFriend".equals(url)){
			
			 validateRequiredString("name", "0005", ResultCode.CODE0005);  
			 validateRequiredString("friend", "0005", ResultCode.CODE0005);  
			
		}else if("/rel/selFriends".equals(url)){
			validateRequiredString("name", "0005", ResultCode.CODE0003);  
			validateRequiredString("friend", "0005", ResultCode.CODE0003);  
			
		}else if("/rel/addBlocks".equals(url)){
			validateRequiredString("name", "0005", ResultCode.CODE0005);  
			validateRequiredString("blocks", "0005", ResultCode.CODE0005);  
			
		}else if("/rel/delBlock".equals(url)){
			validateRequiredString("name", "0005", ResultCode.CODE0005);  
			validateRequiredString("block", "0005", ResultCode.CODE0005);  
		}
		
	}

	@Override
	protected void handleError(Controller c) {
		Result r =new Result();
		
		r.setCode("0005");
		r.setData(ResultCode.CODE0005);
		c.renderJson(r);  
		return ;
		
	}

}
