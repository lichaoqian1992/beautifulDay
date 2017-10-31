package com.manji.sheet.validator.pc.shop;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.manji.sheet.base.Message;

import net.sf.json.JSONObject;

public class addInformationValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		
		validateRequiredString("sheet_id", "0005", "");
		validateRequiredString("descr", "0005", "");

	}

	@Override
	protected void handleError(Controller c) {

		String callback = c.getPara("callback");

		Message m = new Message();

		m.setStatus("0005");
		m.setMessage("数据不能为空！");

		String reText = callback + "(" + JSONObject.fromObject(m).toString() + ")";
		c.renderText(reText);

		return;

	}

}
