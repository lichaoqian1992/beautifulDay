package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.LoginLog;
import com.manji.backstage.model.logger.Notice;
import com.manji.backstage.vo.logger.LoginLogVo;
import com.manji.backstage.vo.logger.NoticeVo;
import com.manji.backstage.model.logger.DeviceLog;
import com.manji.backstage.model.logger.EmailLog;
import com.manji.backstage.model.logger.FileDownLog;
import com.manji.backstage.model.logger.PageViewLog;
import com.manji.backstage.model.logger.SignLog;
import com.manji.backstage.model.logger.SmsLog;
import com.manji.backstage.model.logger.UserMessage;
import com.manji.backstage.model.logger.UserNotice;
import com.manji.backstage.model.logger.UserNoticeRead;
import com.manji.backstage.model.oper.Files;
import com.manji.backstage.service.LoggService;
import com.manji.backstage.vo.logger.DeviceLogVo;
import com.manji.backstage.vo.logger.EmailLogVo;
import com.manji.backstage.vo.logger.FileDownLogVo;
import com.manji.backstage.vo.logger.PageViewLogVo;
import com.manji.backstage.vo.logger.SignLogVo;
import com.manji.backstage.vo.logger.SmsLogVo;
import com.manji.backstage.vo.logger.UserMessageVo;
import com.manji.backstage.vo.logger.UserNoticeReadVo;
import com.manji.backstage.vo.logger.UserNoticeVo;
import com.manji.backstage.vo.operation.FilesVo;

public interface LoggService {

	// dt_user_login_log 用户登陆日志表

	Page<LoginLog> queryLoginLog(LoginLogVo vo);

	LoginLog getLoginLog(int id);

	boolean updLoginLog(LoginLog ll);

	boolean delLoginLog(int id);

	// dt_user_sign_log 用户签到日志表

	Page<SignLog> querySignLog(SignLogVo vo);

	SignLog getSignLog(int id);

	boolean updSignLog(SignLog sl);

	boolean delSignLog(int id);

	// dt_user_sms_log 用户短信发送日子表

	Page<SmsLog> querySmsLog(SmsLogVo vo);

	SmsLog getSmsLog(int id);

	void addSmsLog(SmsLog sl);

	boolean updSmsLog(SmsLog sl);

	boolean delSmsLog(int id);
	// dt_user_mail_log 用户邮件发送日志表

	Page<EmailLog> queryEmailLog(EmailLogVo vo);

	EmailLog getEmailLog(int id);

	void addEmailLog(EmailLog el);

	boolean updEmailLog(EmailLog el);

	boolean delEmailLog(int id);

	// dt_user_attach_log 用户附件下载记录日子表

	Page<FileDownLog> queryFileDownLog(FileDownLogVo vo);

	FileDownLog getFileDownLog(int id);

	boolean updFileDownLog(FileDownLog fdl);

	boolean delFileDownLog(int id);

	// dt_page_view_log 页面访问日子表

	Page<PageViewLog> queryPageViewLog(PageViewLogVo vo);

	PageViewLog getPageViewLog(long l);

	boolean updPageViewLog(PageViewLog pvl);

	boolean delPageViewLog(int id);

	// dt_user_online_log 用户在线记录日子表

	Page<DeviceLog> queryDeviceLog(DeviceLogVo vo);

	DeviceLog getDeviceLog(long l);

	boolean updDeviceLog(DeviceLog dl);

	boolean delDeviceLog(long id);

	// dt_notice 系统公告内容
	Page<Notice> queryNotice(NoticeVo vo);

	void addNotice(Notice notice);

	Notice getNotice(int id);

	boolean updNotice(Notice notice);

	boolean delNotice(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_message 用户站内短消息信息表
	Page<UserMessage> queryUserMessage(UserMessageVo vo);

	void addUserMessage(UserMessage mes);

	UserMessage getUserMessage(long id);

	boolean updUserMessage(UserMessage mes);

	boolean delUserMessage(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_notice 用户动态消息表
	Page<UserNotice> queryUserNotice(UserNoticeVo vo);

	void addUserNotice(UserNotice notice);

	UserNotice getUserNotice(int id);

	boolean updUserNotice(UserNotice notice);

	boolean delUserNotice(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Files> queryFiles(FilesVo vo);

	void addFiles(Files files);

	Files getFiles(int id);

	boolean updFiles(Files files);

	boolean delFiles(int id);

	List<Files> countFilesGroup();
	

	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	Page<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo);
	
	UserNoticeRead getUserNoticeRead(int id);
	
	boolean updUserNoticeRead(UserNoticeRead si);
	
	void addUserNoticeRead(UserNoticeRead si);
	
	boolean delUserNoticeRead(int id);

}
