package com.manji.singleSign.interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		
		HttpSession session = inv.getController().getSession();
		Object user = session.getAttribute("user");
		if(user != null){
			inv.getController().redirect("/user");
			inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
		}else{
			inv.getController().redirect("/");
		}
	}

}
