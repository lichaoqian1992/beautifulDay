package com.manji.adds.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
			throws Exception {
		

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		
		String url = request.getRequestURI();  

        //获取Session  
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute("username");  
        if(url.indexOf("login")>=0){  
            return true;  
        }  
        if(url.indexOf("v1")>=0){  
        	return true;
        }
        if(url.indexOf("v2")>=0){  
        	return true;
        }
        if(url.indexOf("addCount")>=0){
        	 return true;  
        }

        
		
		
		
          
        if(username != null){ 
//        if(1==1){
            return true;  
        }  
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/").forward(request, response);  
          
		
		
		
		return false;
	}

}
