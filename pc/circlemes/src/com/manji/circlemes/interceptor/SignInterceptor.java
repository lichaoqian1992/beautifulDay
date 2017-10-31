package com.manji.circlemes.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.manji.circlemes.model.common.Result;
import com.manji.circlemes.model.common.ResultCode;
import com.manji.circlemes.utils.MD5Utils;

public class SignInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {

		Controller c =inv.getController();
		
		String sign =c.getPara("sign");
		System.out.println(sign);
		Result r =new Result();
		
		if("".equals(sign)||sign==null){
			r.setCode("0001");
			r.setData(ResultCode.CODE0001);
			
			c.renderJson(r);
			return;

			
			
		}else if(!sign.equals(MD5Utils.getSign())){
			
			r.setCode("0002");
			r.setData(ResultCode.CODE0002);
			c.renderJson(r);
			return ;
		
			
		}
		inv.invoke();
		
	}

}
