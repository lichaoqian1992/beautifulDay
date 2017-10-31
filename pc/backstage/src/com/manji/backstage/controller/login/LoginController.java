package com.manji.backstage.controller.login;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.login.LoginService;
import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.login.User;

@Controller
public class LoginController extends BaseController{

	@Autowired
	private LoginService service;
	@Autowired
	private LoggersService logService;
	


	
	public  void saveLog(Loggers log,String prop){
		log.setModule("login");
		log.setType(prop);
		logService.addLoggers(log);
		
	}
	

	@RequestMapping(value ="/login" ,method= RequestMethod.POST)
	public String login(HttpServletRequest req,HttpServletResponse rsp){
		
	
		String userName =req.getParameter("username");
		String password =req.getParameter("password");

		
		User u =service.checkUser(userName, password);
		
		if(u ==null){
			return "redirect:/"; 
		}
		
		
		HttpSession session =req.getSession();
		session.setAttribute("username", u.getUserName());
		session.setAttribute("userid", u.getId());
//		session.setAttribute("role", role);
//		session.setAttribute("authority", authority);
		
		createLog(u.getId(),u.getUserName());
		String logStr ="登陆";
		
		saveLog(createLog(u.getId(),u.getUserName()),logStr);
		
		
		return "main";
	}
	
	@RequestMapping("/loginout")
	public String loginout(HttpServletRequest req){
		saveLog(createLog(req),"登出");
		req.getSession().invalidate();
		
		
		return "redirect:/";
	}
	


	
	
	@RequestMapping("/lockOut")
	public String lockOut(HttpServletRequest req){
		
		
		return "base/lockout";
	}
	
	@RequestMapping("/toMain")
	public String toMain(HttpServletRequest req){
		
		
		return "main";
	}
	
}
