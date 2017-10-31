package com.manji.backstage.controller.logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.account.Account;
import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AmountLog;
import com.manji.backstage.model.logger.AuditLog;
import com.manji.backstage.model.logger.BalanceLog;
import com.manji.backstage.model.logger.CreditLog;
import com.manji.backstage.model.logger.DeviceLog;
import com.manji.backstage.model.logger.EmailLog;
import com.manji.backstage.model.logger.FileDownLog;
import com.manji.backstage.model.logger.FileFilterLog;
import com.manji.backstage.model.logger.KeyHashs;
import com.manji.backstage.model.logger.KeyWords;
import com.manji.backstage.model.logger.LoginLog;
import com.manji.backstage.model.logger.PageViewLog;
import com.manji.backstage.model.logger.PointLog;
import com.manji.backstage.model.logger.ReportLog;
import com.manji.backstage.model.logger.ReputationLog;
import com.manji.backstage.model.logger.RoleAudit;
import com.manji.backstage.model.logger.SignLog;
import com.manji.backstage.model.logger.SmsLog;
import com.manji.backstage.model.logger.VoucherLog;
import com.manji.backstage.model.logger.WordsFilterLog;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.service.logger.LoggerService;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.logger.AmountLogVo;
import com.manji.backstage.vo.logger.AuditLogVo;
import com.manji.backstage.vo.logger.CreditLogVo;
import com.manji.backstage.vo.logger.DeviceLogVo;
import com.manji.backstage.vo.logger.EmailLogVo;
import com.manji.backstage.vo.logger.FileDownLogVo;
import com.manji.backstage.vo.logger.FileFilterLogVo;
import com.manji.backstage.vo.logger.KeyHashsVo;
import com.manji.backstage.vo.logger.KeyWordsVo;
import com.manji.backstage.vo.logger.LoginLogVo;
import com.manji.backstage.vo.logger.PageViewLogVo;
import com.manji.backstage.vo.logger.PointLogVo;
import com.manji.backstage.vo.logger.ReportLogVo;
import com.manji.backstage.vo.logger.ReputationLogVo;
import com.manji.backstage.vo.logger.RoleAuditVo;
import com.manji.backstage.vo.logger.SignLogVo;
import com.manji.backstage.vo.logger.SmsLogVo;
import com.manji.backstage.vo.logger.VoucherLogVo;
import com.manji.backstage.vo.logger.WordsFilterLogVo;


@Controller
public class LoggerController<req> extends BaseController{
	
	@Autowired
	private LoggerService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("logger");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	
	
	
	/**
	 * 登陆日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selLoginLog")
	public String selLoginLog(HttpServletRequest req){
		return "logger/logger_login_list";
	}
	/**
	 * 查询登陆日志
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/queryLoginLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryLoginLog(HttpServletRequest req,LoginLogVo vo){
		
		Message msg =createMsg();
		
		Page<LoginLog> page=service.queryLoginLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/readLoginLog")
	public String readLoginLog(HttpServletRequest req,int id){
		
		LoginLog ll =service.getLoginLog(id);
		req.setAttribute("loginloginfo", Json(ll));
		return "logger/logger_login_upd";
	}
	@RequestMapping(value ="/updLoginLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updLoginLog(HttpServletRequest req,LoginLog ll){
		Message msg =createMsg();
		LoginLog l =service.getLoginLog(ll.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(l),LogRemark.UPDLOGINLOG);
		if(service.updLoginLog(ll)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delLoginLog")
	public String delLoginLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		LoginLog l =service.getLoginLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(l),LogRemark.DELLOGINLOG);
		if(service.delLoginLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selLoginLog";
	}
	
	/**
	 * 签到日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selSignLog")
	public String selSignLog(HttpServletRequest req){
		return "logger/logger_sign_list";
	}
	@RequestMapping(value ="/querySignLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySignLog(HttpServletRequest req,SignLogVo vo){
		Message msg =createMsg();
		
		Page<SignLog> page=service.querySignLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	
	@RequestMapping("/readSignLog")
	public String readSignLog(HttpServletRequest req,int id){
		SignLog sl =service.getSignLog(id);
		req.setAttribute("signloginfo", Json(sl));
		return "logger/logger_sign_upd";
	}
	@RequestMapping(value ="/updSignLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSignLog(HttpServletRequest req, SignLog sl){
		Message msg =createMsg();
		SignLog signlog =service.getSignLog(sl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(signlog),LogRemark.UPDSIGNLOG);
		if(service.updSignLog(sl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delSignLog")
	public String delSignLog(HttpServletRequest req, int id){
		
		Message msg =createMsg();
		SignLog agi =service.getSignLog(id);
		
		saveLog(createLog(req),LogRemark.DEL,Json(agi),LogRemark.DELSIGNLOG);
		if(service.delSignLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selSignLog";
	}
	
	
	
	
	/**
	 * 短信发送日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selSmsLog")
	public String selSmsLog(HttpServletRequest req){
		
		return "logger/logger_sms_list";
	}
	@RequestMapping(value ="/querySmsLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySmsLog(HttpServletRequest req,SmsLogVo vo){
		Message msg =createMsg();
		
		Page<SmsLog> page=service.querySmsLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insSmsLog")
	public String insSmsLog(HttpServletRequest req){
		return "logger/logger_sms_add";
	}
	@RequestMapping(value = "/addSmsLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSmsLog(HttpServletRequest req,SmsLog sl){
		Message msg = createMsg();
		service.addSmsLog(sl);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(sl),LogRemark.ADDSMSLOG);
		return Json(msg);
	}
	
	@RequestMapping("/readSmsLog")
	public String readSmsLog(HttpServletRequest req,int id){
		SmsLog sl =service.getSmsLog(id);
		req.setAttribute("smsloginfo", Json(sl));
		return "logger/logger_sms_upd";
	}	
	@RequestMapping(value ="/updSmsLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSmsLog(HttpServletRequest req,SmsLog sl){
		Message msg =createMsg();
		SmsLog smslog =service.getSmsLog(sl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(smslog),LogRemark.UPDSMSLOG);
		if(service.updSmsLog(sl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delSmsLog")
	public String delSmsLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		SmsLog sl =service.getSmsLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(sl),LogRemark.DELSMSLOG);
		if(service.delSmsLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selSmsLog";
	}
	
	
	
	/**
	 * 邮件发送日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selEmailLog")
	public String selEmailLog(HttpServletRequest req){
		
		return "logger/logger_email_list";
	}
	@RequestMapping(value ="/queryEmailLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryEmailLog(HttpServletRequest req,EmailLogVo vo){
		Message msg =createMsg();
		
		Page<EmailLog> page=service.queryEmailLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	@RequestMapping("/readEmailLog")
 	public String readEmailLog(HttpServletRequest req,int id){
		EmailLog sl =service.getEmailLog(id);
		req.setAttribute("emailloginfo", Json(sl));
		return "logger/logger_email_upd";
	}	
	
	@RequestMapping("/insEmailLog")
	public String insEmailLog(HttpServletRequest req){
		return "logger/logger_email_add";
	}
	@RequestMapping(value = "/addEmailLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addEmailLog(HttpServletRequest req,EmailLog el){
		Message msg = createMsg();
		service.addEmailLog(el);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(el),LogRemark.ADDEMAILLOG);
		return Json(msg);
	}
	
	@RequestMapping(value ="/updEmailLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updEmailLog(HttpServletRequest req,EmailLog el){
		Message msg =createMsg();
		EmailLog email =service.getEmailLog(el.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(email),LogRemark.UPDEMAILLOG);
		if(service.updEmailLog(el)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delEmailLog")
	public String delEmailLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		EmailLog email =service.getEmailLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(email),LogRemark.DELEMAILLOG);
		if(service.delEmailLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selEmailLog";
	}
	
	/**
	 * 附件下载日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selFileDownLog")
	public String selFileDownLog(HttpServletRequest req){
		
		return "logger/logger_filedown_list";
	}
	@RequestMapping(value ="/queryFileDownLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryFileDownLog(HttpServletRequest req,FileDownLogVo vo){
		Message msg =createMsg();
		
		Page<FileDownLog> page=service.queryFileDownLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readFileDownLog")
 	public String readFileDownLog(HttpServletRequest req,int id){
		FileDownLog sl =service.getFileDownLog(id);
		req.setAttribute("filedownloginfo", Json(sl));
		return "logger/logger_filedown_upd";
	}	
	@RequestMapping(value ="/updFileDownLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updFileDownLog(HttpServletRequest req,FileDownLog el){
		Message msg =createMsg();
		FileDownLog fdl =service.getFileDownLog(el.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(fdl),LogRemark.UPDFILEDOWNLOG);
		if(service.updFileDownLog(el)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delFileDownLog")
	public String delFileDownLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		FileDownLog fdl =service.getFileDownLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(fdl),LogRemark.DELFILEDOWNLOG);
		if(service.delFileDownLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	
	
	/**
	 * 余额日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selAmountLog")
	public String selAmountLog(HttpServletRequest req){
		
		return "logger/logger_amount_list";
	}
	@RequestMapping(value ="/queryAmountLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAmountLog(HttpServletRequest req,AmountLogVo vo ){
		Message msg =createMsg();
		
		Page<AmountLog> page=service.queryAmountLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readAmountLog")
	public String readAmountLog(HttpServletRequest req,int id){
		AmountLog al =service.getAmountLog(id);
		req.setAttribute("amountloginfo", Json(al));
		return "logger/logger_amount_upd";
	}
	@RequestMapping("/delAmountLog")
	public String delAmountLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		AmountLog al =service.getAmountLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(al),LogRemark.DELAMOUNTLOG);
		if(service.delAmountLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAmountLog";
	}
	@RequestMapping(value ="/updAmountLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAmountLog(HttpServletRequest req,AmountLog al){
		Message msg =createMsg();
		AmountLog amount =service.getAmountLog(al.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(amount),LogRemark.UPDAMOUNTLOG);
		if(service.updAmountLog(al)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	/**
	 * 满意券日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selVoucherLog")
	public String selVoucherLog(HttpServletRequest req){
		
		return "logger/logger_voucher_list";
	}
	
	@RequestMapping(value ="/queryVoucherLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryVoucher(HttpServletRequest req,VoucherLogVo vo){
		Message msg =createMsg();
		
		Page<VoucherLog> page=service.queryAmountLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/readVoucherLog")
	public String readVoucherLog(HttpServletRequest req,int id){
		VoucherLog vl =service.getVoucherLog(id);
		req.setAttribute("voucherloginfo", Json(vl));
		return "logger/logger_voucher_upd";
		
	}
	@RequestMapping("/delVoucherLog")
	public String delVoucherLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		VoucherLog vl =service.getVoucherLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(vl),LogRemark.DELVOUCHERLOG);
		if(service.delVoucherLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selVoucherLog";
	}
	@RequestMapping(value ="/updVoucherLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updVoucherLog(HttpServletRequest req,VoucherLog vl){
		Message msg =createMsg();
		VoucherLog voucher =service.getVoucherLog(vl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(voucher),LogRemark.UPDVOUCHERLOG);
		if(service.updVoucherLog(vl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	
	
	
	
	
	
	/**
	 * 积分日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selPointLog")
	public String selPointLog(HttpServletRequest req){
		System.out.println("hello");
		
		List<PointLog> list=service.pointLogList();
		
		req.setAttribute("pointloglist", Array(list));
		return "logger/logger_point_list";
	}
	
	/**
	 * 查询积分日志
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/queryPointLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPointLog(HttpServletRequest req,PointLogVo vo){
		Message msg =createMsg();
		Page<PointLog> page=service.queryPointLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/readPointLog")
	public String readPointLog(HttpServletRequest req,int id){
		PointLog pl =service.getPointLog(id);
		req.setAttribute("pointloginfo", Json(pl));
		return "logger/logger_point_upd";
	}
	@RequestMapping("/delPointLog")
	public String delPointLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		PointLog pl =service.getPointLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(pl),LogRemark.DELPOINTLOG);
		if(service.delPointLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect :/selPointLog";
	}
	@RequestMapping(value ="/updPointLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPointLog(HttpServletRequest req,PointLog pl){
		Message msg =createMsg();
		PointLog point =service.getPointLog(pl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(point),LogRemark.UPDPOINTLOG);
		if(service.updPointLog(pl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	/**
	 * 信用日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selCreditLog")
	public String selCreditLog(HttpServletRequest req){
		
		return "logger/logger_credit_list";
	}
	@RequestMapping(value ="/queryCreditLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCreditLog(HttpServletRequest req,CreditLogVo vo){
		Message msg =createMsg();
		
		Page<CreditLog> page=service.queryCreditLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readCreditLog")
	public String readCreditLog(HttpServletRequest req,int id){
		CreditLog cl =service.getCreditLog(id);
		req.setAttribute("creditloginfo", Json(cl));
		return "logger/logger_credit_lsit";
	}
	@RequestMapping("/delCreditLog")
	public String delCreditLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		CreditLog cl =service.getCreditLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(cl),LogRemark.DELCREDITLOG);
		if(service.delCreditLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect :/selCreditLog";
	}
	@RequestMapping(value ="/updCreditLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCreditLog(HttpServletRequest req,CreditLog cl){
		Message msg =createMsg();
		CreditLog credit =service.getCreditLog(cl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(credit),LogRemark.UPDCREDITLOG);
		if(service.updCreditLog(cl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	/**
	 * 信誉日志
	 * @param req
	 * @return
	 */
	@RequestMapping("/selReputationLog")
	public String selReputationLog(HttpServletRequest req){
		
		return "logger/logger_reputation_list";
	}
	@RequestMapping(value ="/queryReputationLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryReputationLog(HttpServletRequest req,ReputationLogVo vo){
		Message msg =createMsg();
		
		Page<ReputationLog> page=service.queryReputationLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readReputationLog")
	public String readReputationLog(HttpServletRequest req,int id){
		ReputationLog rl =service.getReputationLog(id);
		req.setAttribute("reputationofinfo", Json(rl));
		return "logger/logger_reputation_upd";
		
	}
	@RequestMapping(value ="/updReputationLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updReputationLog(HttpServletRequest req,ReputationLog rl){
		Message msg =createMsg();
		ReputationLog agt =service.getReputationLog(rl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDREPUTATIONLOG);

		if(service.updReputationLog(rl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delReputationLog")
	public String delReputationLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		ReputationLog rl =service.getReputationLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(rl),LogRemark.DELREPUTATIONLOG);
		if(service.delReputationLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selReputationLog";
	}
	
	
	
		
	@RequestMapping("/selPageViewLog")
	public String selPageViewLog(HttpServletRequest req){
		
		return "logger/logger_pageview_list";
	}
	
	@RequestMapping(value ="/queryPageViewLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPageViewLog(HttpServletRequest req,PageViewLogVo vo){
		Message msg =createMsg();
		
		Page<PageViewLog> page=service.queryPageViewLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readPageViewLog")
	public String readPageViewLog(HttpServletRequest req,int id){
		PageViewLog pvl =service.getPageViewLog(id);
		req.setAttribute("pageviewloginfo", Json(pvl));
		return "logger/logger_pageview_upd";
	}
	@RequestMapping("/delPageViewLog")
	public String delPageViewLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		PageViewLog pvl =service.getPageViewLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(pvl),LogRemark.DELPAGEVIEWLOG);
		if(service.delPageViewLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selPageviewLog";
	}
	@RequestMapping(value ="/updPageViewLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPageViewLog(HttpServletRequest req,PageViewLog pvl){
		Message msg =createMsg();
		PageViewLog agt =service.getPageViewLog(pvl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDPAGEVIEWLOG);
		if(service.updPageViewLog(pvl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	
	
	@RequestMapping("/selDeviceLog")
	public String selDeviceLog(HttpServletRequest req){
		
		return "logger/logger_device_list";
	}
	
	@RequestMapping(value ="/queryDeviceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryDeviceLog(HttpServletRequest req,DeviceLogVo vo){
		Message msg =createMsg();
		
		Page<DeviceLog> page=service.queryDeviceLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readDeviceLog")
	public String readDeviceLog(HttpServletRequest req,long id){
		DeviceLog dl =service.getDeviceLog(id);
		req.setAttribute("deviceloginfo", Json(dl));
		return "logger/logger_device_upd";
	}
	@RequestMapping("/delDeviceLog")
	public String delDeviceLog(HttpServletRequest req, long id){
		Message msg =createMsg();
		DeviceLog dl =service.getDeviceLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(dl),LogRemark.DELDEVICELOG);
		if(service.delDeviceLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selDeviceLog";
	}
	@RequestMapping(value ="/updDeviceLog", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updDeviceLog(HttpServletRequest req,DeviceLog dl){
		Message msg =createMsg();
		DeviceLog device =service.getDeviceLog(dl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(device),LogRemark.UPDDEVICELOG);
		if(service.updDeviceLog(dl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	
	
	
	
	@RequestMapping("/selKeyWords")
	public String keyWordsList(HttpServletRequest req){
		
		/*List<Keywords> keywordsList =service.getKeywordsList();
		
		req.setAttribute("keywordslist", Json(keywordsList));*/	
		
		return "logger/logger_keyword_list";
	}
	
	@RequestMapping(value="/queryKeyWords",method = RequestMethod.GET)
	@ResponseBody
	public String queryKeyWords(HttpServletRequest req,KeyWordsVo vo){
		Message msg =createMsg();
		
		Page<KeyWords> page =service.queryKeyWords(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insKeyWords")
	public String insertKeyWords(HttpServletRequest req){
		return "logger/logger_keyword_add";
	}
	
	@RequestMapping("/addKeyWords")
	public String add(HttpServletRequest req,KeyWords keywords){
		Message msg =createMsg();
		
		
		service.addKeyWords(keywords);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(keywords),LogRemark.ADDKEYWORDS);
		
		return Json(msg);
	}
	@RequestMapping("/readKeyWords")
	public String readKeyWords(HttpServletRequest req,int id){
		KeyWords keywords =service.getKeyWordsList(id);
		req.setAttribute("keywordsinfo",Array(keywords));
		return "logger/logger_keyword_upd";
		
	}
	
	@RequestMapping("/updKeyWords")
	public String updKeyWords(HttpServletRequest req,KeyWords keywords){
		Message msg =createMsg();
		KeyWords kw =service.getKeyWordsList(keywords.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(kw),LogRemark.UPDKEYWORDS);
		
		
		if(service.updKeyWords(keywords)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delKeyWords")
	public String delKeywords(HttpServletRequest req,int id){
		Message msg =createMsg();
		KeyWords agt =service.getKeyWordsList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELKEYWORDS);
		if(service.delKeyWords(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selKeyWords";
	}
	
	
	@RequestMapping("/selKeyHashs")
	public String KeyHashsList(HttpServletRequest req){
		
			
		return "logger/logger_keyhashs_list";
	}
	
	@RequestMapping(value="/queryKeyHashs",method = RequestMethod.GET)
	@ResponseBody
	public String queryKeyHashs(HttpServletRequest req,KeyHashsVo vo){
		Message msg =createMsg();
		
		Page<KeyHashs> page =service.queryKeyHashs(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insKeyHashs")
	public String insertKeyHashs(HttpServletRequest req){
		return "logger/logger_keyhashs_add";
	}
	
	@RequestMapping("/addKeyHashs")
	@ResponseBody
	public String add(HttpServletRequest req,KeyHashs keyhashs){
		Message msg =createMsg();
		
		service.addKeyHashs(keyhashs);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(keyhashs),LogRemark.ADDKEYHASHS);
		return Json(msg);
	}
	@RequestMapping("/readKeyHashs")
	public String readKeyHashs(HttpServletRequest req,int id){
		KeyHashs keyhashs =service.getKeyHashsList(id);
		req.setAttribute("keyhashsinfo",Array(keyhashs));
		return "logger/logger_keyhashs_upd";
		
	}
	
	@RequestMapping("/updKeyHashs")
	public String updKeyHashs(HttpServletRequest req,KeyHashs keyhashs){
		Message msg =createMsg();
		KeyHashs agt =service.getKeyHashsList(keyhashs.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDKEYHASHS);
		if(service.updKeyHashs(keyhashs)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delKeyHashs")
	public String delKeyHashs(HttpServletRequest req,int id){
		Message msg =createMsg();
		KeyHashs keyhashs =service.getKeyHashsList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(keyhashs),LogRemark.DELKEYHASHS);
		if(service.delKeyHashs(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selKeyHashs";
	}
	
	@RequestMapping("/selWordsFilterLog")
	public String WordsFilterLogList(HttpServletRequest req){
		
				
		return "logger/logger_wordsfilterlog_list";
	}
	
	@RequestMapping(value="/queryWordsFilterLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryWordsFilterLog(HttpServletRequest req,WordsFilterLogVo vo){
		Message msg =createMsg();
		
		Page<WordsFilterLog> page =service.queryWordsFilterLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insWordsFilterLog")
	public String insertWordsFilterLog(HttpServletRequest req){
		return "logger/logger_wordsfilterlog_add";
	}
	
	@RequestMapping("/addWordsFilterLog")
	public String add(HttpServletRequest req,WordsFilterLog wfl){
		Message msg =createMsg();
		
		service.addWordsFilterLog(wfl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(wfl),LogRemark.ADDWORDSFILETERLOG);
		return Json(msg);
	}
	@RequestMapping("/readWordsFilterLog")
	public String readWordsFilterLog(HttpServletRequest req,int id){
		WordsFilterLog wordsfilterlog =service.getWordsFilterLogList(id);
		req.setAttribute("wordsfilterloginfo",Array(wordsfilterlog));
		return "logger/logger_wordsfilterlog_upd";
		
	}
	
	@RequestMapping("/updWordsFilterLog")
	public String updWordsFilterLog(HttpServletRequest req,WordsFilterLog wfl){
		Message msg =createMsg();
		WordsFilterLog agt =service.getWordsFilterLogList(wfl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDWORDSFILETERLOG);
		if(service.updWordsFilterLog(wfl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delWordsFilterLog")
	public String delWordsFilterLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		WordsFilterLog wfl =service.getWordsFilterLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(wfl),LogRemark.DELWORDSFILETERLOG);
		if(service.delWordsFilterLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selWordsFilterLog";
	}
	
	
	@RequestMapping("/selFileFilterLog")
	public String FileFilterLogList(HttpServletRequest req){
		
		return "logger/logger_filefilterlog_list";
	}
	
	@RequestMapping(value="/queryFileFilterLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryFileFilterLog(HttpServletRequest req,FileFilterLogVo vo){
		Message msg =createMsg();
		
		Page<FileFilterLog> page =service.queryFileFilterLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insFileFilterLog")
	public String insertFileFilterLog(HttpServletRequest req){
		return "logger/logger_filefilterlog_add";
	}
	
	@RequestMapping("/addFileFilterLog")
	public String add(HttpServletRequest req,FileFilterLog ffl){
		Message msg =createMsg();
		
		service.addFileFilterLog(ffl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(ffl),LogRemark.ADDFILTERFILELOG);
		return Json(msg);
	}
	@RequestMapping("/readFileFilterLog")
	public String readFileFilterLog(HttpServletRequest req,int id){
		FileFilterLog ffl =service.getFileFilterLogList(id);
		req.setAttribute("filefilterloginfo",Array(ffl));
		return "logger/logger_filefilterlog_upd";
		
	}
	
	@RequestMapping("/updFileFilterLog")
	public String updFileFilterLog(HttpServletRequest req,FileFilterLog ffl){
		Message msg =createMsg();
		FileFilterLog filefilter =service.getFileFilterLogList(ffl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(filefilter),LogRemark.UPDFILTERFILELOG);
		if(service.updFileFilterLog(ffl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delFileFilterLog")
	public String delFileFilterLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		FileFilterLog filefilter =service.getFileFilterLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(filefilter),LogRemark.DELFILTERFILELOG);
		if(service.delFileFilterLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selFileFilterLog";
	}
	
	@RequestMapping("/selReportLog")
	public String ReportLogList(HttpServletRequest req){
		
		return "logger/logger_reportlog_list";
	}
	
	@RequestMapping(value="/queryReportLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryReportLog(HttpServletRequest req,ReportLogVo vo){
		Message msg =createMsg();
		
		Page<ReportLog> page =service.queryReportLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insReportLog")
	public String insertReportLog(HttpServletRequest req){
		return "logger/logger_reportlog_add";
	}
	
	@RequestMapping("/addReportLog")
	public String add(HttpServletRequest req,ReportLog rl){
		Message msg =createMsg();
		
		service.addReportLog(rl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(rl),LogRemark.ADDREPORTLOG);

		return Json(msg);
	}
	@RequestMapping("/readReportLog")
	public String readReportLog(HttpServletRequest req,int id){
		ReportLog rl =service.getReportLogList(id);
		req.setAttribute("reportloginfo",Array(rl));
		return "logger/logger_reportlog_upd";
		
	}
	
	@RequestMapping("/updReportLog")
	public String updReportLog(HttpServletRequest req,ReportLog rl){
		Message msg =createMsg();
		ReportLog report =service.getReportLogList(rl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(report),LogRemark.UPDREPORTLOG);
		if(service.updReportLog(rl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delReportLog")
	public String delReportLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		ReportLog report =service.getReportLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(report),LogRemark.DELREPORTLOG);
		if(service.delReportLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selReportLog";
	}
	
	
	

	@RequestMapping("/selBalanceLog")
	public String setlBalanceLog(HttpServletRequest req){
		
		List<BalanceLog> balanceLogList =service.getBalanceLogList();
		req.setAttribute("balanceloglist", Array(balanceLogList));
		return "logger/logger_balance_list";
		
	}
		
	@RequestMapping("/selRoleAudit")
	public String selRoleAudit(){
		
		return "logger/logger_roleaudit_list";
	}
	@RequestMapping(value="/queryRoleAudit",method = RequestMethod.GET)
	@ResponseBody
	public String queryRoleAudit(HttpServletRequest req ,RoleAuditVo vo){
		Message msg =createMsg();
		
		Page<RoleAudit> page =service.queryRoleAudit(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readRoleAudit")
	public String readRoleAudit(HttpServletRequest req,int id){
		
		RoleAudit ra =service.getRoleAudit(id);
		req.setAttribute("roleaudit", Json(ra));
		return "logger/logger_roleaudit_upd";
		
	}
	@RequestMapping(value="/updRoleAudit",method = RequestMethod.GET)
	@ResponseBody
	public String updRoleAudit(HttpServletRequest req,RoleAudit ra){
		
		Message msg =createMsg();
		RoleAudit role =service.getRoleAudit(ra.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(role),LogRemark.UPDROLEAUDIT);
		if(service.updRoleAudit(ra)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
		
	}
	
	@RequestMapping("/insRoleAudit")
	public String insRoleAudit(HttpServletRequest req){
		return "logger/logger_roleaudit_add";
	}
	
	@RequestMapping(value="/addRoleAudit",method = RequestMethod.GET)
	@ResponseBody
	public String addRoleAudit(HttpServletRequest req,RoleAudit ra){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(ra),LogRemark.ADDROLEAUDIT);
		if(service.addRoleAudit(ra)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delRoleAudit")
	public String delRoleAudit(HttpServletRequest req,int id){
		Message msg =createMsg();
		RoleAudit agt =service.getRoleAudit(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELROLEAUDIT);
		if(service.delRoleAudit(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selRoleAudit";
	}
	@RequestMapping("/selAuditLog")
	public String selAuditLog(){
		
		return "logger/logger_auditlog_list";
		
		
	}
	@RequestMapping(value="/queryAuditLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryAuditLog(HttpServletRequest req,AuditLogVo vo){
		Message msg =createMsg();
		
		Page<AuditLog> page =service.queryAuditLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/readAuditLog")
	public String readAuditLog(HttpServletRequest req,int id){
		
		AuditLog al =service.getAuditLog(id);
		
		req.setAttribute("auditlog", Json(al));
		
		return "logger/logger_auditlog_upd";
	}
	@RequestMapping(value="/updAuditLog",method = RequestMethod.GET)
	@ResponseBody
	public String updAuditLog(HttpServletRequest req,AuditLog al){
		Message msg =createMsg();
		AuditLog agt =service.getAuditLog(al.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDROLEAUDITLOG);
		if(service.updAuditLog(al)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
		
	}
	@RequestMapping("/insAuditLog")
	public String insAuditLog(HttpServletRequest req){
		return "logger/logger_auditlog_add";
	}
	
	@RequestMapping(value="/addAuditLog",method = RequestMethod.GET)
	@ResponseBody
	public String addAuditLog(HttpServletRequest req,AuditLog al){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(al),LogRemark.ADDROLEAUDITLOG);

		al.setAdd_time(TimeUtils.getCurrentTime());
		if(service.addAuditLog(al)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
		
	}
	@RequestMapping("/delAuditLog")
	public String delAuditLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		AuditLog agt =service.getAuditLog(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELROLEAUDITLOG);
		if(service.delAuditLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selAuditLog";
	}
	
	
}
