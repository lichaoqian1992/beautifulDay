package com.manji.adds.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manji.adds.service.LoginService;
import com.manji.adds.utils.JsonUtils;




//用户管理+登录
@Controller
public class LoginController {

	@Autowired
	private LoginService service;
	
	
	/**
	 * 用户登录
	 * @param req
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req){
		
		String username=req.getParameter("username");
		String password =req.getParameter("password");
		System.out.println(username+password);
		
		Map<String, String> map =service.checkUser(username, password);
		System.out.println(JsonUtils.getJson(map));
		
		HttpSession session =req.getSession();
		
//		session.setAttribute("username", username);
		
		
		
		if(null==map){
			
			return "redirect:/";
		}else{
			
			
			session.setAttribute("username", username);
			session.setAttribute("privilege",map.get("privilege"));
			
			
			return "main";
		}
		
		
//		HttpSession session =req.getSession();
//		session.setAttribute("username", "huanghan");
//		return "redirect:toMain";
		
	}
	
	  @RequestMapping("/toMain")
	    public String toMain(HttpSession session){  
		  
		  System.out.println("跳转主页");
		  return "main";
		  
	  }
	  
	  
	  @RequestMapping("/toLogin")
	    public String toLogin(){  
		  
		  System.out.println("跳转登录");
		  return "redirect:/";
		  
	  }

	  
	  
	  @RequestMapping("logOut")
	  public String logOut(HttpServletRequest req){
		  
		  req.getSession().invalidate();
		  
		  
		  return "redirect:/";
		  
	  }
	
	
	
	
	
	
}
