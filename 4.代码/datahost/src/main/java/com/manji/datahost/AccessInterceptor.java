package com.manji.datahost.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.manji.datahost.service.mysql.PermissionService;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.ResultEmuns;

/**
 * 权限拦截
 */
public class AccessInterceptor implements HandlerInterceptor{

	@Autowired
	private PermissionService perService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		
		String path = request.getServletPath();
		String interface_name = path.substring(1);
		Integer per = perService.getConnector(interface_name);
		if (per == 1) {
			return true;
		} else {
			Message msg = new Message();
			msg.setCode(ResultEmuns.NOACCESS.getCode());
			msg.setMessage(ResultEmuns.NOACCESS.getMessage());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JSON.toJSONString(msg));
			response.getWriter().close();
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav)
			throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		
	}
	
}
