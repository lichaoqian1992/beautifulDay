package com.manji.backstage.controller.message;

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
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.message.UserMessage;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.message.MessageService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.message.NoticeVo;
import com.manji.backstage.vo.message.UserMessageVo;
import com.manji.backstage.vo.message.UserNoticeVo;
@Controller
public class MessageController extends BaseController{

	@Autowired
	private  MessageService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("message");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	@RequestMapping("/selNotice")
	public String selNotice(HttpServletRequest req){
		
		return "message/message_notice_list";
	}
	@RequestMapping(value ="/queryNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryNotice(HttpServletRequest req,NoticeVo vo){
		
		Message msg =createMsg();
		
		Page<Notice> page =service.queryNotice(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insNotice")
	public String insNotice(HttpServletRequest req){
		
		return "message/message_notice_add";
	}
	@RequestMapping(value ="/addNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addNotice(HttpServletRequest req,Notice notice){
		Message msg =createMsg();
		service.addNotice(notice);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(notice),LogRemark.ADDNOTICE);
		
		return Json(msg);
	}
	@RequestMapping("/readNotice")
	public String readNotice(HttpServletRequest req,int id){
		Notice notice =service.getNotice(id);
		req.setAttribute("noticeinfo", Json(notice));
		return "message/message_notice_upd";
	}
	@RequestMapping(value ="/updNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNotice(HttpServletRequest req,Notice notice){
		Message msg =createMsg();
		
		Notice n =service.getNotice(notice.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(n),LogRemark.UPDNOTICE);
		
		notice.setUpdate_time(TimeUtils.getCurrentTime());
		if(service.updNotice(notice)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/delNotice")
	public String delNotice(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		Notice n =service.getNotice(id);
		saveLog(createLog(req),LogRemark.DEL,Json(n),LogRemark.DELNOTICE);
		
		if(service.delNotice(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
//		return Json(msg);
		req.setAttribute("msginfo", Json(msg));
		return "redirect:/selNotice";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selUserMessage")
	public String selUserMessage(HttpServletRequest req ){
		
		return "message/message_user_list";
	}
	@RequestMapping(value ="/queryUserMessage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserMessage(HttpServletRequest req ,UserMessageVo vo){
		Message msg =createMsg();
		
		Page<UserMessage> page =service.queryUserMessage(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insUserMessage")
	public String insUserMessage(HttpServletRequest req ){
		
		return "message/message_user_add";
	}
	@RequestMapping(value ="/addUserMessage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserMessage(HttpServletRequest req,UserMessage mes){
		Message msg =createMsg();
		service.addUserMessage(mes);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(mes),LogRemark.ADDUSERNOTICE);
		return Json(msg);
	}
	@RequestMapping("/readUserMessage")
	public String readUserMessage(HttpServletRequest req ,long id){
		UserMessage mes =service.getUserMessage(id);
		req.setAttribute("usermessageinfo", Json(mes));
		return "message/message_user_upd";
	}
	@RequestMapping(value ="/updUserMessage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserMessage(HttpServletRequest req ,UserMessage mes){
		Message msg =createMsg();
		
		UserMessage m =service.getUserMessage(mes.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDUSERNOTICE);
		
		if(service.updUserMessage(mes)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserMessage")
	public String delUserMessage(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		UserMessage m =service.getUserMessage(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELUSERNOTICE);
		
		if(service.delUserMessage(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selUserMessage";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/selUserNotice")
	public String selUserNotice(HttpServletRequest req ){
		
		return "message/message_usernotice_list";
	}
	@RequestMapping(value ="/queryUserNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserNotice(HttpServletRequest req,UserNoticeVo vo ){
		
		Message msg =createMsg();
		
		Page<UserNotice> page =service.queryUserNotice(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insUserNotice")
	public String insUserNotice(HttpServletRequest req ){
		
		return "message/message_usernotice_add";
	}
	@RequestMapping(value ="/addUserNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserNotice(HttpServletRequest req , UserNotice un){
		Message msg =createMsg();
		un.setAdd_time(TimeUtils.getCurrentTime());
		service.addUserNotice(un);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(un),LogRemark.ADDUSERNOTICE);
		return Json(msg);
	}
	@RequestMapping("/readUserNotice")
	public String readUserNotice(HttpServletRequest req ,int id){
		UserNotice usernotice =service.getUserNotice(id);
		req.setAttribute("usernoticeinfo", Json(usernotice));
		return "message/message_usernotice_upd";
	}
	@RequestMapping(value ="/updUserNotice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserNotice(HttpServletRequest req ,UserNotice notice){
		Message msg =createMsg();
		UserNotice u =service.getUserNotice(notice.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(u),LogRemark.UPDUSERNOTICE);
		if(service.updUserNotice(notice)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserNotice")
	public String delUserNotice(HttpServletRequest req ,int id){
		Message msg =createMsg();
		UserNotice u =service.getUserNotice(id);
		saveLog(createLog(req),LogRemark.DEL,Json(u),LogRemark.DELUSERNOTICE);
		
		if(service.delUserNotice(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selUserNotice";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
}
