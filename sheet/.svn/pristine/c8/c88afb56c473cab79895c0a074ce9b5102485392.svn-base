package com.manji.sheet.intercepter;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.manji.sheet.base.Message;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.utils.GetAccountUtils;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ShopIntercepter implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// 获取当前控制器
		Controller controller = inv.getController();

		controller.getFile();

		String callback = controller.getPara("callback");
		if (null == callback || "".equals(callback)) {

			Message m = new Message();

			m.setStatus("0004");
			m.setMessage("方法名未填写！");

			String reText = callback + "(" + JSONObject.fromObject(m).toString() + ")";
			controller.renderText(reText);
			return;

		}
		// 先判断是否已经存入了
		Account sessAccount = controller.getSessionAttr("Account");// 获取session里面的账户信息
		if (sessAccount == null) {// 还没有存放账户信息

			String sessionid = controller.getPara("sessionId");

			if (null == sessionid || "".equals(sessionid)) {

				Message m = new Message();

				m.setStatus("0002");
				m.setMessage("session值未填写");

				String reText = callback + "(" + JSONObject.fromObject(m).toString() + ")";
				controller.renderText(reText);
				return;

			}

			// 获取用户信息
			Account userinfo = GetAccountUtils.getAccount(sessionid);

			if (null == userinfo || userinfo.getUser_id() == null) {

				Message m = new Message();

				m.setStatus("0003");
				m.setMessage("该账户未登陆！");

				String reText = callback + "(" + JSONObject.fromObject(m).toString() + ")";
				controller.renderText(reText);
				return;
			} else {
				// 将用户信息存入session
				controller.setSessionAttr("Account", userinfo);
			}
		}

		inv.invoke();

	}


}
