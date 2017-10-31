package com.manji.advert.interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.activerecord.Record;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		
		HttpSession session = inv.getController().getSession();
		if(session == null){					//没有登录
			inv.getController().redirect("/");//跳转到登录页面
		}else{
			Record user = (Record) session.getAttribute("user");
			if(user == null){
				inv.getController().redirect("/");//跳转到登录页面
			}else{									//用户登录了，后续判断用户是否拥有权限
				inv.invoke();//为了将当前调用传递到后续的 Interceptor与 Action。
			}
		}
		
		
	}

}
