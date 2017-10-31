package com.manji.sheet.validator.pc.shop;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.manji.sheet.base.Message;

import net.sf.json.JSONObject;

public class SheetTypeValidator extends Validator {
	@Override
	protected void validate(Controller c) {
		validateRequiredString("short_title", "0005", "");

	}

	@Override
	protected void handleError(Controller c) {

		String callback = c.getPara("callback");

		Message m = new Message();

		m.setStatus("0005");
		m.setMessage("工单类型未填写！");

		String reText = callback + "(" + JSONObject.fromObject(m).toString() + ")";
		c.renderText(reText);

		return;

	}
}
