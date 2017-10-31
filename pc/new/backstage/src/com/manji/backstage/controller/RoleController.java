package com.manji.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AuditLog;
import com.manji.backstage.model.logger.RoleAudit;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.monitor.Manager;
import com.manji.backstage.model.monitor.ManagerLog;
import com.manji.backstage.model.monitor.ManagerRecharge;
import com.manji.backstage.model.monitor.ManagerRole;
import com.manji.backstage.model.monitor.ManagerRoleValue;
import com.manji.backstage.model.role.RoleOperatorNavigation;
import com.manji.backstage.model.role.RoleOperatorType;
import com.manji.backstage.model.role.RoleOperatorTypeValue;
import com.manji.backstage.model.role.UserRole;
import com.manji.backstage.model.role.UserRoleBusiness;
import com.manji.backstage.model.role.UserRoleGroup;
import com.manji.backstage.model.role.UserRoleMedal;
import com.manji.backstage.model.shop.ShopGroup;
import com.manji.backstage.model.user.UserGroup;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.MontService;
import com.manji.backstage.service.RoleService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.logger.AuditLogVo;
import com.manji.backstage.vo.logger.RoleAuditVo;
import com.manji.backstage.vo.monitor.ManagerLogVo;
import com.manji.backstage.vo.monitor.ManagerRechargeVo;
import com.manji.backstage.vo.monitor.ManagerRoleValueVo;
import com.manji.backstage.vo.monitor.ManagerRoleVo;
import com.manji.backstage.vo.monitor.ManagerVo;
import com.manji.backstage.vo.role.RoleOperatorNavigationVo;
import com.manji.backstage.vo.role.RoleOperatorTypeValueVo;
import com.manji.backstage.vo.role.RoleOperatorTypeVo;
import com.manji.backstage.vo.role.UserRoleBusinessVo;
import com.manji.backstage.vo.role.UserRoleGroupVo;
import com.manji.backstage.vo.role.UserRoleMedalVo;
import com.manji.backstage.vo.role.UserRoleVo;
@Controller
public class RoleController extends BaseController {

	@Autowired
	private RoleService service;
	@Autowired
	private LoggersService logService;

	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("role");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}

	// / dt_user_role_audit_list 角色后台管理员审核分配表

	@RequestMapping("/selRoleAudit")
	public String selRoleAudit() {

		return "logger/logger_roleaudit_list";
	}

	@RequestMapping(value = "/queryRoleAudit", method = RequestMethod.GET)
	@ResponseBody
	public String queryRoleAudit(HttpServletRequest req, RoleAuditVo vo) {
		Message msg = createMsg();

		Page<RoleAudit> page = service.queryRoleAudit(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/readRoleAudit")
	public String readRoleAudit(HttpServletRequest req, int id) {

		RoleAudit ra = service.getRoleAudit(id);
		req.setAttribute("roleaudit", Json(ra));
		return "logger/logger_roleaudit_upd";

	}

	@RequestMapping(value = "/updRoleAudit", method = RequestMethod.GET)
	@ResponseBody
	public String updRoleAudit(HttpServletRequest req, RoleAudit ra) {

		Message msg = createMsg();
		RoleAudit role = service.getRoleAudit(ra.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(role), LogRemark.UPDROLEAUDIT);
		if (service.updRoleAudit(ra)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/insRoleAudit")
	public String insRoleAudit(HttpServletRequest req) {
		return "logger/logger_roleaudit_add";
	}

	@RequestMapping(value = "/addRoleAudit", method = RequestMethod.GET)
	@ResponseBody
	public String addRoleAudit(HttpServletRequest req, RoleAudit ra) {
		Message msg = createMsg();
		saveLog(createLog(req), LogRemark.ADD, Json(ra), LogRemark.ADDROLEAUDIT);
		if (service.addRoleAudit(ra)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/delRoleAudit")
	public String delRoleAudit(HttpServletRequest req, int id) {
		Message msg = createMsg();
		RoleAudit agt = service.getRoleAudit(id);
		saveLog(createLog(req), LogRemark.DEL, Json(agt), LogRemark.DELROLEAUDIT);
		if (service.delRoleAudit(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selRoleAudit";
	}

	// dt_user_role_audit_log 用户角色管理员审核记录操作日子表

	@RequestMapping("/selAuditLog")
	public String selAuditLog() {

		return "logger/logger_auditlog_list";

	}

	@RequestMapping(value = "/queryAuditLog", method = RequestMethod.GET)
	@ResponseBody
	public String queryAuditLog(HttpServletRequest req, AuditLogVo vo) {
		Message msg = createMsg();

		Page<AuditLog> page = service.queryAuditLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/readAuditLog")
	public String readAuditLog(HttpServletRequest req, int id) {

		AuditLog al = service.getAuditLog(id);

		req.setAttribute("auditlog", Json(al));

		return "logger/logger_auditlog_upd";
	}

	@RequestMapping(value = "/updAuditLog", method = RequestMethod.GET)
	@ResponseBody
	public String updAuditLog(HttpServletRequest req, AuditLog al) {
		Message msg = createMsg();
		AuditLog agt = service.getAuditLog(al.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(agt), LogRemark.UPDROLEAUDITLOG);
		if (service.updAuditLog(al)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/insAuditLog")
	public String insAuditLog(HttpServletRequest req) {
		return "logger/logger_auditlog_add";
	}

	@RequestMapping(value = "/addAuditLog", method = RequestMethod.GET)
	@ResponseBody
	public String addAuditLog(HttpServletRequest req, AuditLog al) {
		Message msg = createMsg();
		saveLog(createLog(req), LogRemark.ADD, Json(al), LogRemark.ADDROLEAUDITLOG);

		al.setAdd_time(TimeUtils.getCurrentTime());
		if (service.addAuditLog(al)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/delAuditLog")
	public String delAuditLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		AuditLog agt = service.getAuditLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(agt), LogRemark.DELROLEAUDITLOG);
		if (service.delAuditLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selAuditLog";
	}
	
	
	
	
	
	
	//管理员
	

	
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
	
	
	//agent

	@RequestMapping(value ="/selAgentPie")
	
	public String countAgentGroup(HttpServletRequest req){
		List<AgentGroup> list= service.countAgentGroup();
		
		req.setAttribute("agentpielist",Array(list));
		return "agent/agent_pie_list"; 
	}

	
	
	@RequestMapping("/selAgentGroup")
	public String agentGroupList(HttpServletRequest req){
		
		List<AgentGroup> agentGroups =service.selAgentGroups();
		
		req.setAttribute("agentgroups", Array(agentGroups));
		
		return "agent/agent_group_list";
	}
	
	@RequestMapping(value="/findAgentGroup",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findGroupList(HttpServletRequest req){
		Message msg = createMsg();
		List<AgentGroup> agentGroups =service.findAgentGroup();
		msg.setStatus("0");
		msg.setResult(agentGroups);
		return Json(msg);
		
	}
	
	


	
	
	@RequestMapping("/insAgentGroup")
	public String insAgentGroup(HttpServletRequest req){
		
		return "agent/agent_group_add";
	}

	

	@RequestMapping(value ="/addAgentGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAgentGourp(HttpServletRequest req,AgentGroup group){
		
		Message msg =createMsg();
		service.addAgentGroup(group);
		
		
		
		msg.setStatus("0");
		msg.setResult("增加成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(group),LogRemark.ADDAGENTGROUP);
		
		
		return Json(msg);
	}
	
	
	@RequestMapping(value ="/updAgentGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAgentGroup(HttpServletRequest req,AgentGroup group){
		
		Message msg =new Message();
		
		AgentGroup g =service.getAgentGroup(group.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(g),LogRemark.UPDAGENTGROUP);
		
		if(service.updAgentGroup(group)){
			msg.setStatus("0");
			msg.setResult("修改成功");
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
		}
		return Json(msg); 
		
		
	}
	
	
	@RequestMapping("/chgAgentGroup")
	public String chgAgentGroup(HttpServletRequest req,int id){
		AgentGroup group =service.getAgentGroup(id);
		req.setAttribute("groupinfo", Json(group));

		return "agent/agent_group_upd";
	}
	

	@RequestMapping("/delAgentGroup")
	public String delAgentGroup(HttpServletRequest req,int id){
		
		Message msg =new Message();
		
		AgentGroup g =service.getAgentGroup(id);
		
		saveLog(createLog(req),LogRemark.DEL,Json(g),LogRemark.DELAGENTGROUP);
		
		if(service.delAgentGroup(id)){
			msg.setStatus("0");
			msg.setResult("删除成功");
		}else{
			msg.setStatus("1");	
			msg.setResult("删除失败");
		}
		
		return "redirect:/selAgentGroup";
	}
	
	
	
	@RequestMapping(value ="/countAgentGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopAgentCounts(HttpServletRequest req){
		String title =req.getParameter("title");
		
		Message msg =createMsg();
		if(service.shopAgentCounts(title)){
			msg.setStatus("1");
			msg.setResult("该品牌名已存在!");
			
		}else{
			msg.setStatus("0");
			msg.setResult("该品牌名无重复");
		}
		
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/getAgentGroupList", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAgentGroupList(HttpServletRequest req){
		
		Message msg =createMsg();
		
		List<AgentGroup> list =service.getAgentGroupList();
		
		if(list.size()>0){
			msg.setStatus("0");
			msg.setResult(list);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	
	//1.商家组别管理
	
	

	
	@RequestMapping(value ="/selShopPie")
		
		public String countShopGroup(HttpServletRequest req){
			List<ShopGroup> list= service.countShopGroup();
			
			req.setAttribute("shoppielist",Array(list));
			return "shop/shop_pie_list"; 
		}


	


	@RequestMapping("/selShopGroup")
	public String selShopGroups(HttpServletRequest req){
		
		
		List<ShopGroup> shopGroups =service.selShopGroups();
		
		
		req.setAttribute("shopgroups", Array(shopGroups));
		
		return "shop/shop_group_list";
	}
	

	/**
	 * 查询商家
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/queryShopById", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShopById(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String id =req.getParameter("id");
		
		System.out.println(id);
		
		List<ShopGroup> list =service.queryShopById(id);
		
		msg.setStatus("0");
		msg.setResult(list);
		
		return Json(msg);
		
	}
	




	/**
	 * 1.1.1增加商家组别
	 * @param req
	 * @param group
	 * @return
	 */
	@RequestMapping("/insShopGroup")
	public String insShopGroup(HttpServletRequest req){
		
		
		return "shop/shop_group_add";
	}

	
	/**
	 * 1.1.2添加商家组别记录
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/addShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addShopGroup(HttpServletRequest req,ShopGroup group){
		
		Message msg =createMsg();
		
		service.addShopGroup(group);
		
		msg.setStatus("0");
		msg.setResult("添加成功");
		saveLog(createLog(req),LogRemark.ADD,Json(group),LogRemark.ADDSHOPGROUPS);
		return Json(msg);
	}
	
	
	/**
	 * 1.1.2修改商家组别
	 * @param req
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/updShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updShopGroup(HttpServletRequest req,ShopGroup group){
		ShopGroup g =service.getShopGroup(group.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(g),LogRemark.UPDSHOPGROUPS);
		Message msg =createMsg();
		
		if(service.updShopGroup(group)){
			msg.setStatus("0");
			msg.setResult("成功");
		}else{
			msg.setStatus("1");
			msg.setResult("失败");
		}
	
		return Json(msg);
	}
	
	@RequestMapping("/chgShopGroup")
	public String getShopGroup(HttpServletRequest req,int id){
		ShopGroup group =service.getShopGroup(id);
		req.setAttribute("shopinfo", Json(group));
		return "shop/shop_group_upd";
	}
	
	//删除商家组别
	
	@RequestMapping(value = "/delShopGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String delShopGroup(HttpServletRequest req,int id){
		
		Message msg =createMsg();
		ShopGroup g =service.getShopGroup(id);
		saveLog(createLog(req),LogRemark.DEL,Json(g),LogRemark.DELSHOPGROUPS);
		if(service.delShopGroup(id)){
			msg.setStatus("0");
			msg.setResult("操作成功");
		}else{
			msg.setStatus("1");
			msg.setResult("操作失败");
		}
		
		return "redirect:/selShopGroup";
	}

	
	//user
	
	@RequestMapping(value = "/selUserPie")

	public String countGroupId(HttpServletRequest req) {
		List<UserGroup> list = service.countGroupId();

		req.setAttribute("userpielist", Array(list));
		return "user/user_pie_list";
	}

	@RequestMapping(value = "/findUserGroup", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findUserGroup(HttpServletRequest req) {
		Message msg = createMsg();
		List<UserGroup> list = service.findUserGroup();
		msg.setStatus("0");
		msg.setResult(list);
		return Json(msg);
	}

	/**
	 * 1.1.1查询用户组别
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/selUserGroup")
	// @RequestMapping(value = "/selUserGroup", method =
	// RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String userGroupList(HttpServletRequest req) {

		List<UserGroup> userGroupList = service.userGroupList();

		req.setAttribute("usergrouplist", Array(userGroupList));

		return "user/user_group_list";
	}


	@RequestMapping(value = "/getUserGroup", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getUserGroup(HttpServletRequest req, int id) {

		UserGroup group = service.getUserGroup(id);
		req.setAttribute("groupinfo", Json(group));
		// req.setAttribute("id", id);
		return "user/user_group_list";
	}

	@RequestMapping("/insUserGroup")
	public String insUserGroup(HttpServletRequest req) {

		return "user/user_group_add";
	}

	/**
	 * 1.1.2添加用户组别记录
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/addUserGroup", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserGroup(HttpServletRequest req, UserGroup group) {

		Message msg = createMsg();

		service.addUserGroup(group);

		msg.setStatus("0");
		msg.setResult("添加成功");

		saveLog(createLog(req), LogRemark.ADD, Json(group), LogRemark.ADDUSERGROUP);
		return Json(msg);
	}

	/**
	 * 1.1.3修改用户组别记录
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/updUserGroup", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserGroup(HttpServletRequest req, UserGroup group) {

		Message msg = createMsg();
		UserGroup l = service.getUserGroup(group.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(l), LogRemark.UPDUSERGROUP);
		if (service.updUserGroup(group)) {
			msg.setStatus("0");
			msg.setResult("成功");
		} else {
			msg.setStatus("1");
			msg.setResult("失败");
		}

		return Json(msg);
	}

	@RequestMapping("/readUserGroup")
	public String readUserGroup(HttpServletRequest req, int id) {
		UserGroup group = service.getUserGroup(id);
		req.setAttribute("groupinfo", Json(group));
		return "user/user_group_upd";
	}

	/**
	 * 1.1.4删除用户组别记录
	 * 
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/delUserGroup", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String delUserGroup(HttpServletRequest req, int id) {

		Message msg = createMsg();
		UserGroup l = service.getUserGroup(id);
		saveLog(createLog(req), LogRemark.DEL, Json(l), LogRemark.DELUSERGROUP);
		if (service.delUserGroup(id)) {
			msg.setStatus("0");
			msg.setResult("操作成功");
		} else {
			msg.setStatus("1");
			msg.setResult("操作失败");
		}

		return "redirect:/selUserGroup";
	}
	

	//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
	
	@RequestMapping("/selRoleOperatorTypeValue")
	public String selRoleOperatorTypeValue(HttpServletRequest req){
		
		return "role/role_operatortypevalue_list";
	}
	@RequestMapping(value ="/queryRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValueVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorTypeValue> page =service.queryRoleOperatorTypeValue(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorTypeValue")
	public String insRoleOperatorTypeValue(HttpServletRequest req){
		
		return "role/role_operatortypevalue_add";
	}
	@RequestMapping(value ="/addRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValue og){
		Message msg =createMsg();
		service.addRoleOperatorTypeValue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDTYPEVALUE);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorTypeValue")
	public String readRoleOperatorTypeValue(HttpServletRequest req,int id){
		RoleOperatorTypeValue og =service.getRoleOperatorTypeValue(id);
		req.setAttribute("roleotv", Json(og));
		return "role/role_operatortypevalue_upd";
	}
	@RequestMapping(value ="/updRoleOperatorTypeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorTypeValue(HttpServletRequest req,RoleOperatorTypeValue og){
		Message msg =createMsg();
		RoleOperatorTypeValue o =service.getRoleOperatorTypeValue(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDTYPEVALUE);
		if(service.updRoleOperatorTypeValue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorTypeValue")
	public String delRoleOperatorTypeValue(HttpServletRequest req,int id){
		
		RoleOperatorTypeValue o =service.getRoleOperatorTypeValue(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELTYPEVALUE);
		
		if(service.delRoleOperatorTypeValue(id)){
			
		}
		return "redirect:/selRoleOperatorTypeValue";
	}
	
	
	
	
	//2.8.4.dt_user_role（用户角色信息表）
	
	@RequestMapping("/selUserRole")
	public String selUserRole(HttpServletRequest req){
		
		return "role/user_role_list";
	}
	@RequestMapping(value ="/queryUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRole(HttpServletRequest req,UserRoleVo vo){
		Message msg =createMsg();
		
		Page<UserRole> page =service.queryUserRole(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRole")
	public String insUserRole(HttpServletRequest req){
		
		return "role/user_role_add";
	}
	@RequestMapping(value ="/addUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRole(HttpServletRequest req,UserRole og){
		Message msg =createMsg();
		service.addUserRole(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDUSERROLE);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRole")
	public String readUserRole(HttpServletRequest req,int id){
		UserRole og =service.getUserRole(id);
		req.setAttribute("userrole", Json(og));
		return "role/user_role_upd";
	}
	@RequestMapping(value ="/updUserRole", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRole(HttpServletRequest req,UserRole og){
		Message msg =createMsg();
		UserRole o =service.getUserRole(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDUSERROLE);
		if(service.updUserRole(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRole")
	public String delUserRole(HttpServletRequest req,int id){
		
		UserRole o =service.getUserRole(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELUSERROLE);
		
		if(service.delUserRole(id)){
			
		}
		return "redirect:/selUserRole";
	}
	
	
	
	
	//2.8.3.dt_role_operator_navigation（角色后台管理总菜单信息表）
	
	@RequestMapping("/selRoleOperatorNavigation")
	public String selRoleOperatorNavigation(HttpServletRequest req){
		
		return "role/role_operatornavigation_list";
	}
	@RequestMapping(value ="/queryRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigationVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorNavigation> page =service.queryRoleOperatorNavigation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorNavigation")
	public String insRoleOperatorNavigation(HttpServletRequest req){
		
		return "role/role_operatornavigation_add";
	}
	@RequestMapping(value ="/addRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigation og){
		Message msg =createMsg();
		service.addRoleOperatorNavigation(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDOPERNAV);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorNavigation")
	public String readRoleOperatorNavigation(HttpServletRequest req,int id){
		RoleOperatorNavigation og =service.getRoleOperatorNavigation(id);
		req.setAttribute("roleopernav", Json(og));
		return "role/role_operatornavigation_upd";
	}
	@RequestMapping(value ="/updRoleOperatorNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorNavigation(HttpServletRequest req,RoleOperatorNavigation og){
		Message msg =createMsg();
		RoleOperatorNavigation o =service.getRoleOperatorNavigation(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDOPERNAV);
		if(service.updRoleOperatorNavigation(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorNavigation")
	public String delRoleOperatorNavigation(HttpServletRequest req,int id){
		
		RoleOperatorNavigation o =service.getRoleOperatorNavigation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELOPERNAV);
		
		if(service.delRoleOperatorNavigation(id)){
			
		}
		return "redirect:/selRoleOperatorNavigation";
	}
	
	
	
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	
	@RequestMapping("/selUserRoleBusiness")
	public String selUserRoleBusiness(HttpServletRequest req){
		
		return "role/user_rolebusiness_list";
	}
	@RequestMapping(value ="/queryUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleBusiness(HttpServletRequest req,UserRoleBusinessVo vo){
		Message msg =createMsg();
		
		Page<UserRoleBusiness> page =service.queryUserRoleBusiness(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleBusiness")
	public String insUserRoleBusiness(HttpServletRequest req){
		
		return "role/user_rolebusiness_add";
	}
	@RequestMapping(value ="/addUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleBusiness(HttpServletRequest req,UserRoleBusiness og){
		Message msg =createMsg();
		service.addUserRoleBusiness(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDROLEBUSINESS);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleBusiness")
	public String readUserRoleBusiness(HttpServletRequest req,int id){
		UserRoleBusiness og =service.getUserRoleBusiness(id);
		req.setAttribute("rolebusiness", Json(og));
		return "role/user_rolebusiness_upd";
	}
	@RequestMapping(value ="/updUserRoleBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleBusiness(HttpServletRequest req,UserRoleBusiness og){
		Message msg =createMsg();
		UserRoleBusiness o =service.getUserRoleBusiness(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDROLEBUSINESS);
		if(service.updUserRoleBusiness(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleBusiness")
	public String delUserRoleBusiness(HttpServletRequest req,int id){
		
		UserRoleBusiness o =service.getUserRoleBusiness(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELROLEBUSINESS);
		
		if(service.delUserRoleBusiness(id)){
			
		}
		return "redirect:/selUserRoleBusiness";
	}
	
	//dt_user_role_medal(用户角色荣誉勋章信息表)
	
	@RequestMapping("/selUserRoleMedal")
	public String selUserRoleMedal(HttpServletRequest req){
		
		return "role/user_rolemedal_list";
	}
	@RequestMapping(value ="/queryUserRoleMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleMedal(HttpServletRequest req,UserRoleMedalVo vo){
		Message msg =createMsg();
		
		Page<UserRoleMedal> page =service.queryUserRoleMedal(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleMedal")
	public String insUserRoleMedal(HttpServletRequest req){
		
		return "role/user_rolemedal_add";
	}
	@RequestMapping(value ="/addUserRoleMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleMedal(HttpServletRequest req,UserRoleMedal og){
		Message msg =createMsg();
		service.addUserRoleMedal(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDROLEMEDAL);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleMedal")
	public String readUserRoleMedal(HttpServletRequest req,int id){
		UserRoleMedal og =service.getUserRoleMedal(id);
		req.setAttribute("rolemedal", Json(og));
		return "role/user_rolemedal_upd";
	}
	@RequestMapping(value ="/updUserRoleMedal", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleMedal(HttpServletRequest req,UserRoleMedal og){
		Message msg =createMsg();
		UserRoleMedal o =service.getUserRoleMedal(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDROLEMEDAL);
		if(service.updUserRoleMedal(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleMedal")
	public String delUserRoleMedal(HttpServletRequest req,int id){
		
		UserRoleMedal o =service.getUserRoleMedal(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELROLEMEDAL);
		
		if(service.delUserRoleMedal(id)){
			
		}
		return "redirect:/selUserRoleMedal";
	}
	
	//dt_user_role_group(用户角色分组信息表)）
	
	@RequestMapping("/selUserRoleGroup")
	public String selUserRoleGroup(HttpServletRequest req){
		
		return "role/user_rolegroup_list";
	}
	@RequestMapping(value ="/queryUserRoleGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserRoleGroup(HttpServletRequest req,UserRoleGroupVo vo){
		Message msg =createMsg();
		
		Page<UserRoleGroup> page =service.queryUserRoleGroup(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserRoleGroup")
	public String insUserRoleGroup(HttpServletRequest req){
		
		return "role/user_rolegroup_add";
	}
	@RequestMapping(value ="/addUserRoleGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserRoleGroup(HttpServletRequest req,UserRoleGroup og){
		Message msg =createMsg();
		service.addUserRoleGroup(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDROLEGROUP);
		return Json(msg);
		
	}
	@RequestMapping("/readUserRoleGroup")
	public String readUserRoleGroup(HttpServletRequest req,int id){
		UserRoleGroup og =service.getUserRoleGroup(id);
		req.setAttribute("rolegroup", Json(og));
		return "role/user_rolegroup_upd";
	}
	@RequestMapping(value ="/updUserRoleGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserRoleGroup(HttpServletRequest req,UserRoleGroup og){
		Message msg =createMsg();
		UserRoleGroup o =service.getUserRoleGroup(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDROLEGROUP);
		if(service.updUserRoleGroup(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserRoleGroup")
	public String delUserRoleGroup(HttpServletRequest req,int id){
		
		UserRoleGroup o =service.getUserRoleGroup(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELROLEGROUP);
		
		if(service.delUserRoleGroup(id)){
			
		}
		return "redirect:/selUserRoleGroup";
	}

	//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
	
	@RequestMapping("/selRoleOperatorType")
	public String selRoleOperatorType(HttpServletRequest req){
		
		return "role/role_operatortype_list";
	}
	@RequestMapping(value ="/queryRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryRoleOperatorType(HttpServletRequest req,RoleOperatorTypeVo vo){
		Message msg =createMsg();
		
		Page<RoleOperatorType> page =service.queryRoleOperatorType(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insRoleOperatorType")
	public String insRoleOperatorType(HttpServletRequest req){
		
		return "role/role_operatortype_add";
	}
	@RequestMapping(value ="/addRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addRoleOperatorType(HttpServletRequest req,RoleOperatorType og){
		Message msg =createMsg();
		service.addRoleOperatorType(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDOPERTYPE);
		return Json(msg);
		
	}
	@RequestMapping("/readRoleOperatorType")
	public String readRoleOperatorType(HttpServletRequest req,int id){
		RoleOperatorType og =service.getRoleOperatorType(id);
		req.setAttribute("roleopertype", Json(og));
		return "role/role_operatortype_upd";
	}
	@RequestMapping(value ="/updRoleOperatorType", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updRoleOperatorType(HttpServletRequest req,RoleOperatorType og){
		Message msg =createMsg();
		RoleOperatorType o =service.getRoleOperatorType(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDOPERTYPE);
		if(service.updRoleOperatorType(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delRoleOperatorType")
	public String delRoleOperatorType(HttpServletRequest req,int id){
		
		RoleOperatorType o =service.getRoleOperatorType(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELOPERTYPE);
		
		if(service.delRoleOperatorType(id)){
			
		}
		return "redirect:/selRoleOperatorType";
	}
	
	
}
