package com.manji.backstage.controller.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.user.SafeService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.user.VerifyVo;
@Controller
public class SafeController extends BaseController{

	@Autowired
	private SafeService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("user");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	@RequestMapping("/selVerify")
	public String selVerify(HttpServletRequest req){
		
		return "user/user_verify_list";
	}
	@RequestMapping(value ="/queryVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryVerify(HttpServletRequest req,VerifyVo vo){
		Message msg =createMsg();
		
		Page<Verify> page =service.queryVerify(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insVerify")
	public String insVerify(HttpServletRequest req){
		
		return "user/user_verify_add";
	}
	@RequestMapping(value ="/addVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addVerify(HttpServletRequest req,Verify verify){
		Message msg =createMsg();
		verify.setUpdate_time(TimeUtils.getCurrentTime());
		service.addVerify(verify);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(verify),LogRemark.ADDVERIFY);
		return Json(msg);
		
	}
	@RequestMapping("/readVerify")
	public String readVerify(HttpServletRequest req,int id){
		Verify verify =service.getVerify(id);
		req.setAttribute("verifyinfo", Json(verify));
		return "user/user_verify_upd";
	}
	@RequestMapping(value ="/updVerify", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updVerify(HttpServletRequest req,Verify verify){
		Message msg =createMsg();
		Verify v =service.getVerify(verify.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(v),LogRemark.UPDVERIFY);
		if(service.updVerify(verify)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delVerify")
	public String delVerify(HttpServletRequest req,int id){
		Message msg =createMsg();
		Verify v =service.getVerify(id);
		saveLog(createLog(req),LogRemark.DEL,Json(v),LogRemark.DELVERIFY);
		if(service.delVerify(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selVerify";
	}
	
	
	
}
