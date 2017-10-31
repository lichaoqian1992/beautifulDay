package com.manji.backstage.controller.agent;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.agent.Group;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.service.agent.AgentGroupService;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.vo.base.Message;

@Controller
public class AgentGroupController extends BaseController{
	
	@Autowired
	private AgentGroupService service;
	@Autowired
	private LoggersService logService;
	
	
	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("agent");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	
	@RequestMapping(value ="/selAgentPie")
	
	public String countAgentGroup(HttpServletRequest req){
		List<Group> list= service.countAgentGroup();
		
		req.setAttribute("agentpielist",Array(list));
		return "agent/agent_pie_list"; 
	}

	
	
	@RequestMapping("/selAgentGroup")
	public String agentGroupList(HttpServletRequest req){
		
		List<Group> agentGroups =service.selAgentGroups();
		
		req.setAttribute("agentgroups", Array(agentGroups));
		
		return "agent/agent_group_list";
	}
	
	@RequestMapping(value="/findAgentGroup",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findGroupList(HttpServletRequest req){
		Message msg = createMsg();
		List<Group> agentGroups =service.findAgentGroup();
		msg.setStatus("0");
		msg.setResult(agentGroups);
		return Json(msg);
		
	}
	
	

/*	@RequestMapping(value ="/queryAgentById", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAgentById(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String id =req.getParameter("id");
		
		Group group =service.queryAgentById(id);
		
		msg.setStatus("0");
		msg.setResult(group);
		
		return Json(msg);
		
	}*/
	
	
	@RequestMapping("/insAgentGroup")
	public String insAgentGroup(HttpServletRequest req){
		
		return "agent/agent_group_add";
	}

	

	@RequestMapping(value ="/addAgentGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAgentGourp(HttpServletRequest req,Group group){
		
		Message msg =createMsg();
		service.addAgentGroup(group);
		
		
		
		msg.setStatus("0");
		msg.setResult("增加成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(group),LogRemark.ADDAGENTGROUP);
		
		
		return Json(msg);
	}
	
	
	@RequestMapping(value ="/updAgentGroup", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAgentGroup(HttpServletRequest req,Group group){
		
		Message msg =new Message();
		
		Group g =service.getAgentGroup(group.getId());
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
		Group group =service.getAgentGroup(id);
		req.setAttribute("groupinfo", Json(group));

		return "agent/agent_group_upd";
	}
	

	@RequestMapping("/delAgentGroup")
	public String delAgentGroup(HttpServletRequest req,int id){
		
		Message msg =new Message();
		
		Group g =service.getAgentGroup(id);
		
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
		
		List<Group> list =service.getAgentGroupList();
		
		if(list.size()>0){
			msg.setStatus("0");
			msg.setResult(list);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	
	
}
