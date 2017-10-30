package com.manji.backstage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.DeviceLog;
import com.manji.backstage.model.logger.EmailLog;
import com.manji.backstage.model.logger.FileDownLog;
import com.manji.backstage.model.logger.LoginLog;
import com.manji.backstage.model.logger.Notice;
import com.manji.backstage.model.logger.PageViewLog;
import com.manji.backstage.model.logger.SignLog;
import com.manji.backstage.model.logger.SmsLog;
import com.manji.backstage.model.logger.UserMessage;
import com.manji.backstage.model.logger.UserNotice;
import com.manji.backstage.model.logger.UserNoticeRead;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.oper.Files;
import com.manji.backstage.service.LoggService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.logger.DeviceLogVo;
import com.manji.backstage.vo.logger.EmailLogVo;
import com.manji.backstage.vo.logger.FileDownLogVo;
import com.manji.backstage.vo.logger.LoginLogVo;
import com.manji.backstage.vo.logger.NoticeVo;
import com.manji.backstage.vo.logger.PageViewLogVo;
import com.manji.backstage.vo.logger.SignLogVo;
import com.manji.backstage.vo.logger.SmsLogVo;
import com.manji.backstage.vo.logger.UserMessageVo;
import com.manji.backstage.vo.logger.UserNoticeReadVo;
import com.manji.backstage.vo.logger.UserNoticeVo;
import com.manji.backstage.vo.operation.FilesVo;
@Controller
public class LoggController extends BaseController {

	@Autowired
	private LoggService service;
	@Autowired
	private LoggersService logService;

	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("logg");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}

	// dt_user_login_log 用户登陆日志表

	@RequestMapping("/selLoginLog")
	public String selLoginLog(HttpServletRequest req) {
		return "logger/logger_login_list";
	}

	/**
	 * 查询登陆日志
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryLoginLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryLoginLog(HttpServletRequest req, LoginLogVo vo) {

		Message msg = createMsg();

		Page<LoginLog> page = service.queryLoginLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/readLoginLog")
	public String readLoginLog(HttpServletRequest req, int id) {

		LoginLog ll = service.getLoginLog(id);
		req.setAttribute("loginloginfo", Json(ll));
		return "logger/logger_login_upd";
	}

	@RequestMapping(value = "/updLoginLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updLoginLog(HttpServletRequest req, LoginLog ll) {
		Message msg = createMsg();
		LoginLog l = service.getLoginLog(ll.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(l), LogRemark.UPDLOGINLOG);
		if (service.updLoginLog(ll)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/delLoginLog")
	public String delLoginLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		LoginLog l = service.getLoginLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(l), LogRemark.DELLOGINLOG);
		if (service.delLoginLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selLoginLog";
	}

	// dt_user_sign_log 用户签到日志表

	@RequestMapping("/selSignLog")
	public String selSignLog(HttpServletRequest req) {
		return "logger/logger_sign_list";
	}

	@RequestMapping(value = "/querySignLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySignLog(HttpServletRequest req, SignLogVo vo) {
		Message msg = createMsg();

		Page<SignLog> page = service.querySignLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/readSignLog")
	public String readSignLog(HttpServletRequest req, int id) {
		SignLog sl = service.getSignLog(id);
		req.setAttribute("signloginfo", Json(sl));
		return "logger/logger_sign_upd";
	}

	@RequestMapping(value = "/updSignLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSignLog(HttpServletRequest req, SignLog sl) {
		Message msg = createMsg();
		SignLog signlog = service.getSignLog(sl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(signlog), LogRemark.UPDSIGNLOG);
		if (service.updSignLog(sl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delSignLog")
	public String delSignLog(HttpServletRequest req, int id) {

		Message msg = createMsg();
		SignLog agi = service.getSignLog(id);

		saveLog(createLog(req), LogRemark.DEL, Json(agi), LogRemark.DELSIGNLOG);
		if (service.delSignLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selSignLog";
	}

	// dt_user_sms_log 用户短信发送日子表

	@RequestMapping("/selSmsLog")
	public String selSmsLog(HttpServletRequest req) {

		return "logger/logger_sms_list";
	}

	@RequestMapping(value = "/querySmsLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySmsLog(HttpServletRequest req, SmsLogVo vo) {
		Message msg = createMsg();

		Page<SmsLog> page = service.querySmsLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insSmsLog")
	public String insSmsLog(HttpServletRequest req) {
		return "logger/logger_sms_add";
	}

	@RequestMapping(value = "/addSmsLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSmsLog(HttpServletRequest req, SmsLog sl) {
		Message msg = createMsg();
		service.addSmsLog(sl);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(sl), LogRemark.ADDSMSLOG);
		return Json(msg);
	}

	@RequestMapping("/readSmsLog")
	public String readSmsLog(HttpServletRequest req, int id) {
		SmsLog sl = service.getSmsLog(id);
		req.setAttribute("smsloginfo", Json(sl));
		return "logger/logger_sms_upd";
	}

	@RequestMapping(value = "/updSmsLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updSmsLog(HttpServletRequest req, SmsLog sl) {
		Message msg = createMsg();
		SmsLog smslog = service.getSmsLog(sl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(smslog), LogRemark.UPDSMSLOG);
		if (service.updSmsLog(sl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delSmsLog")
	public String delSmsLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		SmsLog sl = service.getSmsLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(sl), LogRemark.DELSMSLOG);
		if (service.delSmsLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selSmsLog";
	}

	// dt_user_mail_log 用户邮件发送日志表

	@RequestMapping("/selEmailLog")
	public String selEmailLog(HttpServletRequest req) {

		return "logger/logger_email_list";
	}

	@RequestMapping(value = "/queryEmailLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryEmailLog(HttpServletRequest req, EmailLogVo vo) {
		Message msg = createMsg();

		Page<EmailLog> page = service.queryEmailLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readEmailLog")
	public String readEmailLog(HttpServletRequest req, int id) {
		EmailLog sl = service.getEmailLog(id);
		req.setAttribute("emailloginfo", Json(sl));
		return "logger/logger_email_upd";
	}

	@RequestMapping("/insEmailLog")
	public String insEmailLog(HttpServletRequest req) {
		return "logger/logger_email_add";
	}

	@RequestMapping(value = "/addEmailLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addEmailLog(HttpServletRequest req, EmailLog el) {
		Message msg = createMsg();
		service.addEmailLog(el);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(el), LogRemark.ADDEMAILLOG);
		return Json(msg);
	}

	@RequestMapping(value = "/updEmailLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updEmailLog(HttpServletRequest req, EmailLog el) {
		Message msg = createMsg();
		EmailLog email = service.getEmailLog(el.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(email), LogRemark.UPDEMAILLOG);
		if (service.updEmailLog(el)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delEmailLog")
	public String delEmailLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		EmailLog email = service.getEmailLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(email), LogRemark.DELEMAILLOG);
		if (service.delEmailLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selEmailLog";
	}

	// dt_user_attach_log 用户附件下载记录日子表

	@RequestMapping("/selFileDownLog")
	public String selFileDownLog(HttpServletRequest req) {

		return "logger/logger_filedown_list";
	}

	@RequestMapping(value = "/queryFileDownLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryFileDownLog(HttpServletRequest req, FileDownLogVo vo) {
		Message msg = createMsg();

		Page<FileDownLog> page = service.queryFileDownLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readFileDownLog")
	public String readFileDownLog(HttpServletRequest req, int id) {
		FileDownLog sl = service.getFileDownLog(id);
		req.setAttribute("filedownloginfo", Json(sl));
		return "logger/logger_filedown_upd";
	}

	@RequestMapping(value = "/updFileDownLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updFileDownLog(HttpServletRequest req, FileDownLog el) {
		Message msg = createMsg();
		FileDownLog fdl = service.getFileDownLog(el.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(fdl), LogRemark.UPDFILEDOWNLOG);
		if (service.updFileDownLog(el)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delFileDownLog")
	public String delFileDownLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		FileDownLog fdl = service.getFileDownLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(fdl), LogRemark.DELFILEDOWNLOG);
		if (service.delFileDownLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_page_view_log 页面访问日子表

	@RequestMapping("/selPageViewLog")
	public String selPageViewLog(HttpServletRequest req) {

		return "logger/logger_pageview_list";
	}

	@RequestMapping(value = "/queryPageViewLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryPageViewLog(HttpServletRequest req, PageViewLogVo vo) {
		Message msg = createMsg();

		Page<PageViewLog> page = service.queryPageViewLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readPageViewLog")
	public String readPageViewLog(HttpServletRequest req, int id) {
		PageViewLog pvl = service.getPageViewLog(id);
		req.setAttribute("pageviewloginfo", Json(pvl));
		return "logger/logger_pageview_upd";
	}

	@RequestMapping("/delPageViewLog")
	public String delPageViewLog(HttpServletRequest req, int id) {
		Message msg = createMsg();
		PageViewLog pvl = service.getPageViewLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(pvl), LogRemark.DELPAGEVIEWLOG);
		if (service.delPageViewLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selPageviewLog";
	}

	@RequestMapping(value = "/updPageViewLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updPageViewLog(HttpServletRequest req, PageViewLog pvl) {
		Message msg = createMsg();
		PageViewLog agt = service.getPageViewLog(pvl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(agt), LogRemark.UPDPAGEVIEWLOG);
		if (service.updPageViewLog(pvl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_user_online_log 用户在线记录日子表

	@RequestMapping("/selDeviceLog")
	public String selDeviceLog(HttpServletRequest req) {

		return "logger/logger_device_list";
	}

	@RequestMapping(value = "/queryDeviceLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryDeviceLog(HttpServletRequest req, DeviceLogVo vo) {
		Message msg = createMsg();

		Page<DeviceLog> page = service.queryDeviceLog(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/readDeviceLog")
	public String readDeviceLog(HttpServletRequest req, long id) {
		DeviceLog dl = service.getDeviceLog(id);
		req.setAttribute("deviceloginfo", Json(dl));
		return "logger/logger_device_upd";
	}

	@RequestMapping("/delDeviceLog")
	public String delDeviceLog(HttpServletRequest req, long id) {
		Message msg = createMsg();
		DeviceLog dl = service.getDeviceLog(id);
		saveLog(createLog(req), LogRemark.DEL, Json(dl), LogRemark.DELDEVICELOG);
		if (service.delDeviceLog(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selDeviceLog";
	}

	@RequestMapping(value = "/updDeviceLog", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updDeviceLog(HttpServletRequest req, DeviceLog dl) {
		Message msg = createMsg();
		DeviceLog device = service.getDeviceLog(dl.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(device), LogRemark.UPDDEVICELOG);
		if (service.updDeviceLog(dl)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	// dt_notice 系统公告内容

	@RequestMapping("/selNotice")
	public String selNotice(HttpServletRequest req) {

		return "message/message_notice_list";
	}

	@RequestMapping(value = "/queryNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryNotice(HttpServletRequest req, NoticeVo vo) {

		Message msg = createMsg();

		Page<Notice> page = service.queryNotice(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insNotice")
	public String insNotice(HttpServletRequest req) {

		return "message/message_notice_add";
	}

	@RequestMapping(value = "/addNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addNotice(HttpServletRequest req, Notice notice) {
		Message msg = createMsg();
		service.addNotice(notice);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(notice), LogRemark.ADDNOTICE);

		return Json(msg);
	}

	@RequestMapping("/readNotice")
	public String readNotice(HttpServletRequest req, int id) {
		Notice notice = service.getNotice(id);
		req.setAttribute("noticeinfo", Json(notice));
		return "message/message_notice_upd";
	}

	@RequestMapping(value = "/updNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNotice(HttpServletRequest req, Notice notice) {
		Message msg = createMsg();

		Notice n = service.getNotice(notice.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(n), LogRemark.UPDNOTICE);

		notice.setUpdate_time(TimeUtils.getCurrentTime());
		if (service.updNotice(notice)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/delNotice")
	public String delNotice(HttpServletRequest req, int id) {
		Message msg = createMsg();

		Notice n = service.getNotice(id);
		saveLog(createLog(req), LogRemark.DEL, Json(n), LogRemark.DELNOTICE);

		if (service.delNotice(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		// return Json(msg);
		req.setAttribute("msginfo", Json(msg));
		return "redirect:/selNotice";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_message 用户站内短消息信息表

	@RequestMapping("/selUserMessage")
	public String selUserMessage(HttpServletRequest req) {

		return "message/message_user_list";
	}

	@RequestMapping(value = "/queryUserMessage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserMessage(HttpServletRequest req, UserMessageVo vo) {
		Message msg = createMsg();

		Page<UserMessage> page = service.queryUserMessage(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insUserMessage")
	public String insUserMessage(HttpServletRequest req) {

		return "message/message_user_add";
	}

	@RequestMapping(value = "/addUserMessage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserMessage(HttpServletRequest req, UserMessage mes) {
		Message msg = createMsg();
		service.addUserMessage(mes);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(mes), LogRemark.ADDUSERNOTICE);
		return Json(msg);
	}

	@RequestMapping("/readUserMessage")
	public String readUserMessage(HttpServletRequest req, long id) {
		UserMessage mes = service.getUserMessage(id);
		req.setAttribute("usermessageinfo", Json(mes));
		return "message/message_user_upd";
	}

	@RequestMapping(value = "/updUserMessage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserMessage(HttpServletRequest req, UserMessage mes) {
		Message msg = createMsg();

		UserMessage m = service.getUserMessage(mes.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(m), LogRemark.UPDUSERNOTICE);

		if (service.updUserMessage(mes)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delUserMessage")
	public String delUserMessage(HttpServletRequest req, int id) {
		Message msg = createMsg();

		UserMessage m = service.getUserMessage(id);
		saveLog(createLog(req), LogRemark.DEL, Json(m), LogRemark.DELUSERNOTICE);

		if (service.delUserMessage(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "redirect:/selUserMessage";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	// dt_user_notice 用户动态消息表

	@RequestMapping("/selUserNotice")
	public String selUserNotice(HttpServletRequest req) {

		return "message/message_usernotice_list";
	}

	@RequestMapping(value = "/queryUserNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserNotice(HttpServletRequest req, UserNoticeVo vo) {

		Message msg = createMsg();

		Page<UserNotice> page = service.queryUserNotice(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);

	}

	@RequestMapping("/insUserNotice")
	public String insUserNotice(HttpServletRequest req) {

		return "message/message_usernotice_add";
	}

	@RequestMapping(value = "/addUserNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserNotice(HttpServletRequest req, UserNotice un) {
		Message msg = createMsg();
		un.setAdd_time(TimeUtils.getCurrentTime());
		service.addUserNotice(un);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(un), LogRemark.ADDUSERNOTICE);
		return Json(msg);
	}

	@RequestMapping("/readUserNotice")
	public String readUserNotice(HttpServletRequest req, int id) {
		UserNotice usernotice = service.getUserNotice(id);
		req.setAttribute("usernoticeinfo", Json(usernotice));
		return "message/message_usernotice_upd";
	}

	@RequestMapping(value = "/updUserNotice", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserNotice(HttpServletRequest req, UserNotice notice) {
		Message msg = createMsg();
		UserNotice u = service.getUserNotice(notice.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(u), LogRemark.UPDUSERNOTICE);
		if (service.updUserNotice(notice)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/delUserNotice")
	public String delUserNotice(HttpServletRequest req, int id) {
		Message msg = createMsg();
		UserNotice u = service.getUserNotice(id);
		saveLog(createLog(req), LogRemark.DEL, Json(u), LogRemark.DELUSERNOTICE);

		if (service.delUserNotice(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selUserNotice";
	}

	@RequestMapping("/selFiles")
	public String selFiles(HttpServletRequest req) {

		return "operation/operation_files_list";
	}

	@RequestMapping(value = "/queryFiles", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryFiles(HttpServletRequest req, FilesVo vo) {
		Message msg = createMsg();

		Page<Files> page = service.queryFiles(vo);

		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insFiles")
	public String insFiles(HttpServletRequest req) {

		return "operation/operation_files_add";
	}

	@RequestMapping(value = "/addFiles", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addFiles(HttpServletRequest req, Files files) {
		Message msg = createMsg();
		files.setFile_uptime(TimeUtils.getCurrentTime());
		service.addFiles(files);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(files), LogRemark.ADDFILES);
		return Json(msg);

	}

	@RequestMapping("/readFiles")
	public String readFiles(HttpServletRequest req, int id) {
		Files files = service.getFiles(id);
		req.setAttribute("filesinfo", Json(files));
		return "operation/operation_files_upd";
	}

	@RequestMapping(value = "/updFiles", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updFiles(HttpServletRequest req, Files files) {
		Message msg = createMsg();
		Files f = service.getFiles(files.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(f), LogRemark.UPDFILES);
		if (service.updFiles(files)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);

	}

	@RequestMapping("/delFiles")
	public String delFiles(HttpServletRequest req, int id) {
		Message msg = createMsg();

		Files f = service.getFiles(id);
		saveLog(createLog(req), LogRemark.DEL, Json(f), LogRemark.DELFILES);
		if (service.delFiles(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selFiles";
	}

	@RequestMapping(value = "/selFilesPie")

	public String countFilesGroup(HttpServletRequest req) {
		List<Files> list = service.countFilesGroup();

		req.setAttribute("filepielist", Array(list));
		return "operation/operation_filepie_list";
	}
	

	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	@RequestMapping("/selUserNoticeRead")
	public String selUserNoticeRead(HttpServletRequest req){
		
		return "logger/logger_noticeread_list";
	}
	@RequestMapping(value ="/queryUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryUserNoticeRead(HttpServletRequest req,UserNoticeReadVo vo){
		Message msg =createMsg();
		
		Page<UserNoticeRead> page =service.queryUserNoticeRead(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insUserNoticeRead")
	public String insUserNoticeRead(HttpServletRequest req){
		
		return "logger/logger_noticeread_add";
	}
	@RequestMapping(value ="/addUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addUserNoticeRead(HttpServletRequest req,UserNoticeRead og){
		Message msg =createMsg();
		service.addUserNoticeRead(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDNOTICEREAD);
		return Json(msg);
		
	}
	@RequestMapping("/readUserNoticeRead")
	public String readUserNoticeRead(HttpServletRequest req,int id){
		UserNoticeRead og =service.getUserNoticeRead(id);
		req.setAttribute("noticeread", Json(og));
		return "logger/logger_noticeread_upd";
	}
	@RequestMapping(value ="/updUserNoticeRead", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updUserNoticeRead(HttpServletRequest req,UserNoticeRead og){
		Message msg =createMsg();
		UserNoticeRead o =service.getUserNoticeRead(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDNOTICEREAD);
		if(service.updUserNoticeRead(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delUserNoticeRead")
	public String delUserNoticeRead(HttpServletRequest req,int id){
		
		UserNoticeRead o =service.getUserNoticeRead(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELNOTICEREAD);
		
		if(service.delUserNoticeRead(id)){
			
		}
		return "redirect:/selUserNoticeRead";
	}
	

}
