package com.manji.backstage.controller.monitor;

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
import com.manji.backstage.model.monitor.InfoCorrecting;
import com.manji.backstage.model.monitor.Manager;
import com.manji.backstage.model.monitor.ManagerLog;
import com.manji.backstage.model.monitor.ManagerRecharge;
import com.manji.backstage.model.monitor.ManagerRole;
import com.manji.backstage.model.monitor.ManagerRoleValue;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.monitor.MonitorService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.monitor.InfoCorrectingVo;
import com.manji.backstage.vo.monitor.ManagerLogVo;
import com.manji.backstage.vo.monitor.ManagerRechargeVo;
import com.manji.backstage.vo.monitor.ManagerRoleValueVo;
import com.manji.backstage.vo.monitor.ManagerRoleVo;
import com.manji.backstage.vo.monitor.ManagerVo;

@Controller
public class MonitorController extends BaseController{
	@Autowired
	private MonitorService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("monitor");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	
	
	@RequestMapping("/selManager")
	public String selManager(HttpServletRequest req){
		
		return "monitor/monitor_manager_list";
	}
	@RequestMapping(value ="/queryManager", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryManager(HttpServletRequest req,ManagerVo vo){
		Message msg =createMsg();
		
		Page<Manager> page =service.queryManager(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insManager")
	public String insManager(HttpServletRequest req){
		
		return "monitor/monitor_manager_add";
	}
	@RequestMapping(value ="/addManager", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addManager(HttpServletRequest req,Manager m){
		Message msg =createMsg();
		m.setAdd_time(TimeUtils.getCurrentTime());
		service.addManager(m);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(m),LogRemark.ADDMANAGER);
		return Json(msg);
		
	}
	@RequestMapping("/readManager")
	public String readManager(HttpServletRequest req,int id){
		Manager av =service.getManager(id);
		req.setAttribute("managerinfo", Json(av));
		return "monitor/monitor_manager_upd";
	}
	@RequestMapping(value ="/updManager", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updManager(HttpServletRequest req,Manager manager){
		Message msg =createMsg();
		Manager m =service.getManager(manager.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMANAGER);
		if(service.updManager(manager)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delManager")
	public String delManager(HttpServletRequest req ,int id){
		Message msg =createMsg();
		Manager m =service.getManager(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMANAGER);
		if(service.delManager(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selManager";
	}
	
	
	
	@RequestMapping("/selManagerLog")
	public String selManagerLog(HttpServletRequest req){
		
		return "monitor/monitor_managerlog_list";
	}
	@RequestMapping(value ="/queryManagerLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryManagerLog(HttpServletRequest req,ManagerLogVo vo){
		Message msg =createMsg();
		
		Page<ManagerLog> page =service.queryManagerLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insManagerLog")
	public String insManagerLog(HttpServletRequest req){
		
		return "monitor/monitor_managerlog_add";
	}
	@RequestMapping(value ="/addManagerLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addManagerLog(HttpServletRequest req,ManagerLog ml){
		Message msg =createMsg();
		ml.setAdd_time(TimeUtils.getCurrentTime());
		service.addManagerLog(ml);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ml),LogRemark.ADDMANAGERLOG);
		
		return Json(msg);
		
	}
	@RequestMapping("/readManagerLog")
	public String readManagerLog(HttpServletRequest req,int id){
		ManagerLog av =service.getManagerLog(id);
		req.setAttribute("managerloginfo", Json(av));
		return "monitor/monitor_managerlog_upd";
	}
	@RequestMapping(value ="/updManagerLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updManagerLog(HttpServletRequest req,ManagerLog ml){
		Message msg =createMsg();
		ManagerLog m =service.getManagerLog(ml.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMANAGERLOG);
		if(service.updManagerLog(ml)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delManagerLog")
	public String delManagerLog(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		ManagerLog m =service.getManagerLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMANAGERLOG);
		
		if(service.delManagerLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selManagerLog";
	}
	
	
	
	@RequestMapping("/selManagerRecharge")
	public String selManagerRecharge(HttpServletRequest req){
		
		return "monitor/monitor_managerrecharge_list";
	}
	@RequestMapping(value ="/queryManagerRecharge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryManagerRecharge(HttpServletRequest req,ManagerRechargeVo vo){
		Message msg =createMsg();
		
		Page<ManagerRecharge> page =service.queryManagerRecharge(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insManagerRecharge")
	public String insManagerRecharge(HttpServletRequest req){
		
		return "monitor/monitor_managerrecharge_add";
	}
	@RequestMapping(value ="/addManagerRecharge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addManagerRecharge(HttpServletRequest req,ManagerRecharge mr){
		Message msg =createMsg();
		mr.setAdd_time(TimeUtils.getCurrentTime());
		service.addManagerRecharge(mr);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(mr),LogRemark.ADDMANAGERRECHARGE);
		return Json(msg);
		
	}
	@RequestMapping("/readManagerRecharge")
	public String readManagerRecharge(HttpServletRequest req,int id){
		ManagerRecharge av =service.getManagerRecharge(id);
		req.setAttribute("managerrechargeinfo", Json(av));
		return "monitor/monitor_managerrecharge_upd";
	}
	@RequestMapping(value ="/updManagerRecharge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updManagerRecharge(HttpServletRequest req,ManagerRecharge mr){
		Message msg =createMsg();
		ManagerRecharge m =service.getManagerRecharge(mr.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMANAGERRECHARGE);
		
		if(service.updManagerRecharge(mr)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delManagerRecharge")
	public String delManagerRecharge(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		ManagerRecharge m =service.getManagerRecharge(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMANAGERRECHARGE);
		
		if(service.delManagerRecharge(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selManagerRecharge";
	}
	
	
	
	@RequestMapping("/selManagerRole")
	public String selManagerRole(HttpServletRequest req){
		
		return "monitor/monitor_managerrole_list";
	}
	@RequestMapping(value ="/queryManagerRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryManagerRole(HttpServletRequest req,ManagerRoleVo vo){
		Message msg =createMsg();
		
		Page<ManagerRole> page =service.queryManagerRole(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insManagerRole")
	public String insManagerRole(HttpServletRequest req){
		
		return "monitor/monitor_managerrole_add";
	}
	@RequestMapping(value ="/addManagerRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addManagerRole(HttpServletRequest req,ManagerRole mr){
		Message msg =createMsg();
		service.addManagerRole(mr);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(mr),LogRemark.ADDMANAGERROLE);
		return Json(msg);
		
	}
	@RequestMapping("/readManagerRole")
	public String readManagerRole(HttpServletRequest req,int id){
		ManagerRole av =service.getManagerRole(id);
		req.setAttribute("managerroleinfo", Json(av));
		return "monitor/monitor_managerrole_upd";
	}
	@RequestMapping(value ="/updManagerRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updManagerRole(HttpServletRequest req,ManagerRole mr){
		Message msg =createMsg();
		ManagerRole m =service.getManagerRole(mr.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMANAGERROLE);
		if(service.updManagerRole(mr)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delManagerRole")
	public String delManagerRole(HttpServletRequest req ,int id){
		Message msg =createMsg();
		ManagerRole m =service.getManagerRole(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMANAGERROLE);
		if(service.delManagerRole(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selManagerRole";
	}
	
	
	
	
	@RequestMapping("/selManagerRoleValue")
	public String selManagerRoleValue(HttpServletRequest req){
		
		return "monitor/monitor_managerrolevalue_list";
	}
	@RequestMapping(value ="/queryManagerRoleValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryManagerRoleValue(HttpServletRequest req,ManagerRoleValueVo vo){
		Message msg =createMsg();
		
		Page<ManagerRoleValue> page =service.queryManagerRoleValue(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insManagerRoleValue")
	public String insManagerRoleValue(HttpServletRequest req){
		
		return "monitor/monitor_managerrolevalue_add";
	}
	@RequestMapping(value ="/addManagerRoleValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addManagerRoleValue(HttpServletRequest req,ManagerRoleValue mrv){
		Message msg =createMsg();
		service.addManagerRoleValue(mrv);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(mrv),LogRemark.ADDMANAGERROLEVALUE);
		return Json(msg);
		
	}
	@RequestMapping("/readManagerRoleValue")
	public String readManagerRoleValue(HttpServletRequest req,int id){
		ManagerRoleValue av =service.getManagerRoleValue(id);
		req.setAttribute("managerrolevalueinfo", Json(av));
		return "monitor/monitor_managerrolevalue_upd";
	}
	@RequestMapping(value ="/updManagerRoleValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updManagerRoleValue(HttpServletRequest req,ManagerRoleValue mrv){
		Message msg =createMsg();
		ManagerRoleValue m =service.getManagerRoleValue(mrv.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMANAGERROLEVALUE);
		
		
		if(service.updManagerRoleValue(mrv)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delManagerRoleValue")
	public String delManagerRoleValue(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		ManagerRoleValue m =service.getManagerRoleValue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMANAGERROLEVALUE);
		
		if(service.delManagerRoleValue(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selManagerRoleValue";
	}
	
	
	@RequestMapping("/selInfoCorrecting")
	public String selInfoCorrecting(HttpServletRequest req){
		
		return "monitor/monitor_infocorrecting_list";
	}
	@RequestMapping(value ="/queryInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryInfoCorrecting(HttpServletRequest req,InfoCorrectingVo vo){
		Message msg =createMsg();
		
		Page<InfoCorrecting> page =service.queryInfoCorrecting(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insInfoCorrecting")
	public String insInfoCorrecting(HttpServletRequest req){
		
		return "monitor/monitor_infocorrecting_add";
	}
	@RequestMapping(value ="/addInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addInfoCorrecting(HttpServletRequest req,InfoCorrecting ic){
		Message msg =createMsg();
		service.addInfoCorrecting(ic);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ic),LogRemark.ADDINFOCORRECT);
		return Json(msg);
		
	}
	@RequestMapping("/readInfoCorrecting")
	public String readInfoCorrecting(HttpServletRequest req,int id){
		InfoCorrecting ic =service.getInfoCorrecting(id);
		req.setAttribute("infocorrectinginfo", Json(ic));
		return "monitor/monitor_infocorrecting_upd";
	}
	@RequestMapping(value ="/updInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updInfoCorrecting(HttpServletRequest req,InfoCorrecting ic){
		Message msg =createMsg();
		InfoCorrecting i =service.getInfoCorrecting(ic.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(i),LogRemark.UPDINFOCORRECT);
		
		if(service.updInfoCorrecting(ic)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delInfoCorrecting")
	public String delInfoCorrecting(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		InfoCorrecting i =service.getInfoCorrecting(id);
		saveLog(createLog(req),LogRemark.DEL,Json(i),LogRemark.DELINFOCORRECT);
		if(service.delInfoCorrecting(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selInfoCorrecting";
	}
	
	
	
}
