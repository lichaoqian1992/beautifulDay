package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

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
import com.manji.backstage.model.oper.Files;
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
@Resource
public interface LoggMapper {

	// dt_user_login_log 用户登陆日志表
	List<LoginLog> queryLoginLog(LoginLogVo vo);

	int countLoginLog(LoginLogVo vo);

	LoginLog getLoginLog(int id);

	int updLoginLog(LoginLog ll);

	int delLoginLog(int id);

	// dt_user_sign_log 用户签到日志表

	List<SignLog> querySignLog(SignLogVo vo);

	int countSignLog(SignLogVo vo);

	SignLog getSignLog(int id);

	int updSignLog(SignLog sl);

	int delSignLog(int id);
	// dt_user_sms_log 用户短信发送日子表

	List<SmsLog> querySmsLog(SmsLogVo vo);

	int countSmsLog(SmsLogVo vo);

	SmsLog getSmsLog(int id);

	void addSmsLog(SmsLog sl);

	int updSmsLog(SmsLog sl);

	int delSmsLog(int id);

	// dt_user_mail_log 用户邮件发送日志表
	List<EmailLog> queryEmailLog(EmailLogVo vo);

	int countEmailLog(EmailLogVo vo);

	EmailLog getEmailLog(int id);

	void addEmailLog(EmailLog el);

	int updEmailLog(EmailLog el);

	int delEmailLog(int id);
	// dt_user_attach_log 用户附件下载记录日子表

	List<FileDownLog> queryFileDownLog(FileDownLogVo vo);

	int countFileDownLog(FileDownLogVo vo);

	FileDownLog getFileDownLog(int id);

	int updFileDownLog(FileDownLog fdl);

	int delFileDownLog(int id);

	// dt_page_view_log 页面访问日子表

	List<PageViewLog> queryPageViewLog(PageViewLogVo vo);

	int countPageViewLog(PageViewLogVo vo);

	PageViewLog getPageViewLog(long id);

	int updPageViewLog(PageViewLog pvl);

	int delPageViewLog(int id);
	// dt_user_online_log 用户在线记录日子表

	List<DeviceLog> queryDeviceLog(DeviceLogVo vo);

	int countDeviceLog(DeviceLogVo vo);

	DeviceLog getDeviceLog(long id);

	int updDeviceLog(DeviceLog dl);

	int delDeviceLog(long id);

	// dt_user_message 用户站内短消息信息表
	List<Notice> queryNotice(NoticeVo vo);

	int countNotice(NoticeVo vo);

	void addNotice(Notice notice);

	Notice getNotice(int id);

	int updNotice(Notice notice);

	int delNotice(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_message 用户站内短消息信息表
	List<UserMessage> queryUserMessage(UserMessageVo vo);

	int countUserMessage(UserMessageVo vo);

	void addUserMessage(UserMessage mes);

	UserMessage getUserMessage(long id);

	int updUserMessage(UserMessage mes);

	int delUserMessage(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_notice 用户动态消息表

	int countUserNotice(UserNoticeVo vo);

	List<UserNotice> queryUserNotice(UserNoticeVo vo);

	void addUserNotice(UserNotice notice);

	UserNotice getUserNotice(int id);

	int updUserNotice(UserNotice notice);

	int delUserNotice(int id);

	// 上传文件
	List<Files> countFilesGroup();

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<Files> queryFiles(FilesVo vo);

	int countFiles(FilesVo vo);

	void addFiles(Files files);

	Files getFiles(int id);

	int updFiles(Files files);

	int delFiles(int id);

	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	List<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo);
	
	int countUserNoticeRead(UserNoticeReadVo vo);
	
	UserNoticeRead getUserNoticeRead(int id);
	
	int updUserNoticeRead(UserNoticeRead art);
	
	void addUserNoticeRead(UserNoticeRead art);
	
	int delUserNoticeRead(int id);

}
