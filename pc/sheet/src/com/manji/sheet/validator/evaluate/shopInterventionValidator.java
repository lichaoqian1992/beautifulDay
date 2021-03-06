package com.manji.sheet.validator.evaluate;

import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.jfinal.validate.Validator;
import com.manji.sheet.base.Message;
import com.manji.sheet.controller.EvaluateController;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class shopInterventionValidator extends Validator {

	protected static final String ERROR_KEY = ":errorkey";
	private static Logger logger = Logger.getLogger(EvaluateController.class);

	@Override
	protected void validate(Controller c) {
		validateRequiredString("content", msgKey("content"), "content:参数不能为空");
		validateInteger("orderId",msgKey("orderId"),"orderId:只能为整数(参数类型错误)");
		validateRequiredString("orderId", msgKey("orderId"), "orderId:参数不能为空");
	}

	@Override
	protected void handleError(Controller c) {

		String callback =c.getPara("callback","");
		String firstErrMsg = getFirstErrMsg(c);

		Message message =new Message();

		message.setStatus("1");
		message.setMessage(firstErrMsg);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		logger.info("调用接口 : "+getActionMethod()+" ; 日志信息 ："+ JFinalJson.getJson().toJson(message).toString()+"调用时间:"+df.format(new Date()));

		if(!callback.equals("")){
			c.renderText(callback + "(" + JFinalJson.getJson().toJson(message).toString() + ")");
		}else{
			c.renderJson(message);
		}

		return;

	}

	public String msgKey(String msg) {
		return msg+ERROR_KEY;
	}

	public static String getFirstErrMsg(Controller c) {
		String findErrorAttrName = "";
		Enumeration<String> attrNames = c.getAttrNames();

		//获取第一个错误属性key
		if (attrNames != null) {
			while (attrNames.hasMoreElements()) {
				String attrName = attrNames.nextElement();
				if (attrName.lastIndexOf(ERROR_KEY) > 0) {
					findErrorAttrName = attrName;
					break;
				}
			}
		}

		//获取第一个验证错误详情
		String errMsg = c.getAttr(findErrorAttrName);

		return errMsg;
	}

}
