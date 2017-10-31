package com.manji.sheet.validator.pc.buyer;

import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.jfinal.validate.Validator;
import com.manji.sheet.model.common.SheetResult;

public class ParaValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		
		validateRequiredString("callback", "403", "callback参数不能为空！");
	}

	@Override
	protected void handleError(Controller c) {
		
		SheetResult result = new SheetResult();
		String callBack = c.getPara("callback");
		
		result.setCode("403");
		result.setMessage("callback参数不能为空！");
		
		c.renderText(callBack+"("+JFinalJson.getJson().toJson(result).toString()+")");
	}

}
