package com.manji.backstage.controller.login;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.login.LoggersVo;

@Controller
public class LoggersController extends BaseController{

	@Autowired
	private LoggersService service;

	


	
	@RequestMapping("/selLocalLoggers")
	public String loggersList(HttpServletRequest req){
		
		
		return "login/login_loggers_list";
	}
	@RequestMapping(value ="/queryLocalLoggers", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryLoggersList(HttpServletRequest req,LoggersVo vo){
		Message msg =createMsg();
		
		Page<Loggers> page=service.queryLoggers(vo);
		
		if(page.getTotalCount() ==0){
			msg.setStatus("1");
			msg.setResult("查无数据");
		}else{
			msg.setStatus("0");
			msg.setResult(page);
		}
		
		
		return Json(msg);
	}
	
	@RequestMapping("/readLoggers")
	public String readLoggers(HttpServletRequest req,int id){
		
		Loggers loggers =service.getLoggers(id);
		System.out.println("1================"+loggers);
		if(null !=loggers.getData()&&!"".equals(loggers.getData())){
			System.out.println("2");
			int dataId =Integer.valueOf(loggers.getData());
			
			
			Data d =service.getData(dataId);
			req.setAttribute("data", Json(d));
			System.out.println(Json(d));
		}
		
		req.setAttribute("loggers", Json(loggers));
		
		
		return "login/login_loggers_detail";
		
	}
	
	
	
}
