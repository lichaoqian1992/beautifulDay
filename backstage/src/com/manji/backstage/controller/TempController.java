package com.manji.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.ShopService;
import com.manji.backstage.service.TempService;
import com.manji.backstage.vo.content.BusiTempVo;
@Controller
public class TempController extends BaseController{

	@Autowired
	private TempService service;
	@Autowired
	private LoggersService logService;
	
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("temp");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
	

	
	
	/*模板*/

	@RequestMapping("/selBusiTemp")
	public String selBusiTemp(HttpServletRequest req){
		
		return "content/template_busi_list";
	}
	@RequestMapping(value ="/queryBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBusiTemp(HttpServletRequest req,BusiTempVo vo){
		Message msg =createMsg();
		Page<BusiTemp> page =service.queryBusiTemp(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readBusiTemp")
	public String readBusiTemp(HttpServletRequest req,int id){
		BusiTemp bt =service.getBusiTemp(id);
		req.setAttribute("busitemp", Json(bt));
		return "content/busitemp_upd";
	}
	@RequestMapping(value ="/updBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBusiTemp(HttpServletRequest req,BusiTemp bt){
		Message msg =createMsg();
		if(service.updBusiTemp(bt)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insBusiTemp")
	public String insBusiTemp(HttpServletRequest req){
	
		return "content/busitemp_add";	
	}
	@RequestMapping(value ="/addBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBusiTemp(HttpServletRequest req,BusiTemp bt){
		Message msg =createMsg();
		service.addBusiTemp(bt);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/delBusiTemp")
	public String delBusiTemp(HttpServletRequest req,int id){
		
		if(service.delBusiTemp(id)){
			
		}
		
		return "redirect:/selBusiTemp";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
