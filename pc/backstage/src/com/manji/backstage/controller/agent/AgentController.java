package com.manji.backstage.controller.agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.service.agent.AgentService;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.message.NoticeVo;
@Controller
public class AgentController extends BaseController{
	@Autowired
	private AgentService service;
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


	@RequestMapping("/selAgentInfo")
	public String selAgentInfo(HttpServletRequest req){
		
		return "agent/agent_info_list";
	}
	
	@RequestMapping(value ="/queryAgentInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAgentInfo(HttpServletRequest req,AgentInfoVo vo){
		Message msg =createMsg();
		
		Page<AgentInfo> page =service.queryAgentInfo(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	@RequestMapping("/insAgentInfo")
	public String insAgentInfo(HttpServletRequest req){
		
		return "agent/agent_info_add";
	}
	
	@RequestMapping(value ="/addAgentInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAgentInfo(HttpServletRequest req,AgentInfo ai){
		Message msg =createMsg();
		service.addAgentInfo(ai);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ai),LogRemark.ADDAGENTINFO);
		
		return Json(msg);
	}
	@RequestMapping("/readAgentInfo")
	public String readAgentInfo(HttpServletRequest req,int id){
		
		AgentInfo agi =service.getAgentInfo(id);
		req.setAttribute("agentinfo", Json(agi));
		return "agent/agent_info_upd";
	}

	@RequestMapping(value ="/updAgentInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAgentInfo(HttpServletRequest req,AgentInfo ai){
		
		Message msg =createMsg();
		AgentInfo agi =service.getAgentInfo(ai.getId());
		
		saveLog(createLog(req),LogRemark.UPD,Json(agi),LogRemark.UPDAGENTINFO);
		
		if(service.updAgentInfo(ai)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	
	
	@RequestMapping("/delAgentInfo")
	public String delAgentInfo(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		AgentInfo agi =service.getAgentInfo(id);
		
		saveLog(createLog(req),LogRemark.DEL,Json(agi),LogRemark.DELAGENTINFO);
		if(service.delAgentInfo(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selAgentInfo";
	}
	@RequestMapping("/selAgentInfoTemp")
	public String selAgentInfoTemp(HttpServletRequest req){
		
		return "agent/agent_tempinfo_list";
	}
	@RequestMapping(value ="/queryAgentInfoTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAgentInfoTemp(HttpServletRequest req,AgentInfoTempVo vo){
		Message msg =createMsg();
		
		Page<AgentInfoTemp> page =service.queryAgentInfoTemp(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
		
	}
	@RequestMapping("/readAgentInfoTemp")
	public String readAgentInfoTemp(HttpServletRequest req,int id){
		
		AgentInfoTemp agt =service.getAgentInfoTemp(id);
		
		req.setAttribute("agentinfotemp", Json(agt));
		return "agent/agent_tempinfo_upd";
	}
	
	@RequestMapping("/insAgentInfoTemp")
	public String insAgentInfoTemp(HttpServletRequest req){
		
		
		return "agent/agent_tempinfo_add";
	}
	@RequestMapping(value="/addAgentInfoTemp",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	public String addAgentInfoTemp(HttpServletRequest req,AgentInfoTemp ait){
		
		Message msg = createMsg();
		service.addAgentInfoTemp(ait);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ait),LogRemark.ADDAGENTTEMPINFO);
		
		return Json(msg);
	}
	
	@RequestMapping(value ="/updAgentInfoTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAgentInfoTemp(HttpServletRequest req,AgentInfoTemp ait){
		Message msg =createMsg();
		
		AgentInfoTemp agt =service.getAgentInfoTemp(ait.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDAGENTTEMPINFO);
		
		if(service.updAgentInfoTemp(ait)){
			msg.setResult(ait);
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		


		
	}
	@RequestMapping("/delAgentInfoTemp")
	public String delAgentInfoTemp(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		AgentInfoTemp agt =service.getAgentInfoTemp(id);
		
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELAGENTTEMPINFO);
		
		if(service.delAgentInfoTemp(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}

		return "redirect:/selAgentInfoTemp";
	}
	
	
	

	@RequestMapping("/selAgentNotice")
	public String selAgentNotice(HttpServletRequest req){
		
		return "agent/agent_notice_list";
	}
	
	
	@RequestMapping(value ="/queryAgentNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAgentNotice(HttpServletRequest req,NoticeVo vo){
		
		Message msg =createMsg();
		
		Page<Notice> page =service.queryAgentNotice(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insAgentNotice")
	public String insAgentNotice(HttpServletRequest req){
		
		return "agent/agent_notice_add";
	}
	@RequestMapping(value ="/addAgentNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAgentNotice(HttpServletRequest req,Notice notice){
		Message msg =createMsg();
		notice.setUpdate_time(TimeUtils.getCurrentTime());
		service.addAgentNotice(notice);
		
		saveLog(createLog(req),LogRemark.ADD,Json(notice),LogRemark.ADDAGENTNOTICE);
		
		msg.setStatus("0");
		
		return Json(msg);
	}
	@RequestMapping("/readAgentNotice")
	public String readAgentNotice(HttpServletRequest req,int id){
		Notice notice =service.getAgentNotice(id);
		req.setAttribute("agentnoticeinfo", Json(notice));
		return "agent/agent_notice_upd";
	}
	@RequestMapping(value ="/updAgentNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAgentNotice(HttpServletRequest req,Notice notice){
		Message msg =createMsg();
		
		Notice not =service.getAgentNotice(notice.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(not),LogRemark.UPDAGENTNOTICE);
		
		notice.setUpdate_time(TimeUtils.getCurrentTime());
		
		if(service.updAgentNotice(notice)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/delAgentNotice")
	public String delAgentNotice(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		Notice not =service.getAgentNotice(id);
		saveLog(createLog(req),LogRemark.DEL,Json(not),LogRemark.DELAGENTNOTICE);
		
		if(service.delAgentNotice(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}

		return "redirect:/selAgentNotice";
	}
	
	
	
}
