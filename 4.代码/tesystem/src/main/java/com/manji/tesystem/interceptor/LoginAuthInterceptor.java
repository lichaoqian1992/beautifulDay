package com.manji.tesystem.interceptor;

import com.alibaba.fastjson.JSON;
import com.manji.tesystem.common.enums.CodeEnum;
import com.manji.tesystem.common.result.BaseResult;
import com.manji.tesystem.feign.response.account.Account;
import com.manji.tesystem.service.AccountManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired AccountManager accountManager;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod _handler=((HandlerMethod) handler);
			LoginAuth loginAuth = _handler.getMethodAnnotation(LoginAuth.class);
			if (loginAuth == null) {
				return true;
			}
			String sessionId = request.getHeader("sessionId");
			if(StringUtils.isBlank(sessionId)){
				BaseResult baseResult=new BaseResult(CodeEnum.PLEASE_SIGN_IN.getCode(),"请您先登录 ");
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(baseResult));
				response.getWriter().close();
				return false;
			}
			Account account = accountManager.checkUser(sessionId);
			if(null == account){
				BaseResult baseResult=new BaseResult(CodeEnum.PLEASE_SIGN_IN.getCode(),"登录信息已失效，请您重新登录");
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(baseResult));
				response.getWriter().close();
				return false;
			}
			if(0!=account.getResult()){
				BaseResult baseResult=new BaseResult(CodeEnum.PLEASE_SIGN_IN.getCode(),account.getPrompt());
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(JSON.toJSONString(baseResult));
				response.getWriter().close();
				return false;
			}
		}
		return super.preHandle(request, response, handler);
	}
}
