package com.manji.data.intercepter;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.manji.data.controller.service.IndexController;
import com.manji.data.utils.AllConfig;
import com.manji.data.utils.HttpClientUtils;

import net.sf.json.JSONObject;

public class LocalIntercepter implements Interceptor{

	@Override
	public void intercept(Invocation inv) {

		HttpSession session = inv.getController().getSession();
		String token = inv.getController().getCookie("ssoToken");
		if(session == null){
			//跳转到登录页面
			inv.getController().redirect("/");
		}else{
			
			Object user = session.getAttribute("user");
			if(user == null){
				
				//验证token
				String url_auth = AllConfig.URL_AUTH+"system=data&ssoToken="+token;
				String authData = HttpClientUtils.postMethod(url_auth);
				if(JSONObject.fromObject(authData).get("code").equals("0000")){
					JSONObject userInfo = JSONObject.fromObject(authData).getJSONObject("data").getJSONObject("userInfo");
					//JSONArray menuInfo = JSONObject.fromObject(authData).getJSONObject("data").getJSONArray("menuInfo");
					inv.getController().setSessionAttr("user", userInfo);
					inv.invoke();
				}else{
					inv.getController().redirect("/");//跳转到登录页面
				}
			}else{
				inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
			}
		}
	}

	
}
