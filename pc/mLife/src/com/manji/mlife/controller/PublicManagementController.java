package com.manji.mlife.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.mlife.utils.Logger;

/**
 * 公共服务类
 * @author gaochao
 * 2016年8月30日下午2:16:49
 * PublicManagementController
 *
 */
@Controller
@RequestMapping("/public")
public class PublicManagementController {
	
	
	/**
	 * 操作页面
	 * @author gaochao
	 * @return
	 * 2016年8月30日下午5:15:09
	 * String
	 *
	 */
	@RequestMapping("/management")
	public String Management(HttpServletRequest request){
		String password =request.getParameter("password");
		
		if(!"manjishenghuo".equals(password)){
			return "redirect:/";
		}
		
		return "/management";
	}
	
	/**
	 * 打开日志记录
	 * @author gaochao
	 * 2016年8月30日下午5:21:01
	 * void
	 * @return 
	 *
	 */
	@RequestMapping(value="/logger" ,method=RequestMethod.GET,produces="text/html;charset=utf-8")
	@ResponseBody
	public String logger(){
		
		Logger.main(null);
	
		String logger="启动成功";
		
		return logger;
	}
	

	

}
