package com.manji.finance.index;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;

public class MyInterceptor implements Interceptor{
	/**
	 * 主要用于拦截所有的非登录页面的请求
	 */
	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		HttpSession session = inv.getController().getSession();
		if(session == null){
			inv.getController().redirect("/");//跳转到登录页面
		}else{
			Record user = (Record) session.getAttribute("user");
			if(user == null){
				inv.getController().redirect("/");//跳转到登录页面
			}else{
				inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
			}
		}
		
	}

}
