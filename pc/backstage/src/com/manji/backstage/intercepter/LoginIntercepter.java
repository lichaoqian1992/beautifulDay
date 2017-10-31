package com.manji.backstage.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.service.login.AuthService;

public class LoginIntercepter implements HandlerInterceptor{

	private AuthService service;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

		String url = request.getRequestURI();  
//		System.out.println(url);
		String[] reqPath =url.split("/");
		
		String path =reqPath[reqPath.length-1];
		
		System.out.println(path);
        //获取Session  
        HttpSession session = request.getSession();  
        
//        List<String>  auth =(List<String>) session.getAttribute("authority");
//        
//        if(url.indexOf("login")>=0){  
//            return true;  
//        }  
//        if(url.indexOf("loginout")>=0){  
//            return true;  
//        }  
//        
//        
////        String username = (String)session.getAttribute("username");  
//        String userId =(String) session.getAttribute("userid");
//       
//        
//        
//        if(service.checkAuth(userId, path)){
//        	return true;
//        	
//        }else{
//        	request.getRequestDispatcher("/").forward(request, response);  
////        	return false;
//        }
//        
      
       
        
       
//        if(username !=null&&!"".equals(username)){
//        	return true;
//        }
        
       
//        return true;
//        
//        
////          
////		
		return true;
	}
	
	
	public static boolean checkAuth(String path,List<String> authority ){
		
		if(null==authority){
			return false;
		}
		
		for(int i=0;i<authority.size();i++){
			String auth =authority.get(i);
			
			if(auth.equals(path)){
				return true;
			}
		}
		
//		return false;
		return true;
	}

}
