package com.manji.backstage.service.impl;

import com.manji.backstage.service.LoggService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.LoggMapper;
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
import com.manji.backstage.service.LoggersService;
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
@Service
public class LoggServiceImpl implements LoggService {
@Autowired
	private LoggMapper mapper;

	// dt_user_login_log 用户登陆日志表

	@Override
	public Page<LoginLog> queryLoginLog(LoginLogVo vo) {

		Page<LoginLog> page = new Page<LoginLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<LoginLog> dataList = mapper.queryLoginLog(vo);
		int totalCount = mapper.countLoginLog(vo);

		page.transform(totalCount, dataList);

		return page;

	}

	@Override
	public LoginLog getLoginLog(int id) {
		return mapper.getLoginLog(id);
	}

	@Override
	public boolean updLoginLog(LoginLog ll) {
		if (mapper.updLoginLog(ll) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delLoginLog(int id) {
		if (mapper.delLoginLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_user_sign_log 用户签到日志表

	@Override
	public Page<SignLog> querySignLog(SignLogVo vo) {
		Page<SignLog> page = new Page<SignLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<SignLog> dataList = mapper.querySignLog(vo);
		int totalCount = mapper.countSignLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public SignLog getSignLog(int id) {
		return mapper.getSignLog(id);
	}

	@Override
	public boolean updSignLog(SignLog sl) {
		if (mapper.updSignLog(sl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delSignLog(int id) {
		if (mapper.delSignLog(id) > 0) {
			return true;
		}
		return false;
	}
	// dt_user_sms_log 用户短信发送日子表

	@Override
	public Page<SmsLog> querySmsLog(SmsLogVo vo) {
		Page<SmsLog> page = new Page<SmsLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<SmsLog> dataList = mapper.querySmsLog(vo);
		int totalCount = mapper.countSmsLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	// dt_user_mail_log 用户邮件发送日志表

	@Override
	public Page<EmailLog> queryEmailLog(EmailLogVo vo) {
		Page<EmailLog> page = new Page<EmailLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<EmailLog> dataList = mapper.queryEmailLog(vo);
		int totalCount = mapper.countEmailLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	// dt_user_attach_log 用户附件下载记录日子表

	@Override
	public Page<FileDownLog> queryFileDownLog(FileDownLogVo vo) {
		Page<FileDownLog> page = new Page<FileDownLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<FileDownLog> dataList = mapper.queryFileDownLog(vo);
		int totalCount = mapper.countFileDownLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public SmsLog getSmsLog(int id) {
		return mapper.getSmsLog(id);
	}

	@Override
	public EmailLog getEmailLog(int id) {
		return mapper.getEmailLog(id);
	}

	@Override
	public FileDownLog getFileDownLog(int id) {
		return mapper.getFileDownLog(id);
	}

	@Override
	public boolean updSmsLog(SmsLog sl) {
		if (mapper.updSmsLog(sl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updEmailLog(EmailLog el) {
		if (mapper.updEmailLog(el) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updFileDownLog(FileDownLog fdl) {
		if (mapper.updFileDownLog(fdl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addSmsLog(SmsLog sl) {
		mapper.addSmsLog(sl);
	}

	@Override
	public void addEmailLog(EmailLog el) {
		mapper.addEmailLog(el);
	}

	@Override
	public Page<PageViewLog> queryPageViewLog(PageViewLogVo vo) {
		Page<PageViewLog> page = new Page<PageViewLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<PageViewLog> dataList = mapper.queryPageViewLog(vo);
		int totalCount = mapper.countPageViewLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public PageViewLog getPageViewLog(long id) {
		return mapper.getPageViewLog(id);
	}

	@Override
	public boolean updPageViewLog(PageViewLog pvl) {
		if (mapper.updPageViewLog(pvl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delPageViewLog(int id) {
		if (mapper.delPageViewLog(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<DeviceLog> queryDeviceLog(DeviceLogVo vo) {
		Page<DeviceLog> page = new Page<DeviceLog>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<DeviceLog> dataList = mapper.queryDeviceLog(vo);
		int totalCount = mapper.countDeviceLog(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public DeviceLog getDeviceLog(long id) {
		return mapper.getDeviceLog(id);
	}

	@Override
	public boolean updDeviceLog(DeviceLog dl) {
		if (mapper.updDeviceLog(dl) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delDeviceLog(long id) {
		if (mapper.delDeviceLog(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delSmsLog(int id) {
		if (mapper.delSmsLog(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delEmailLog(int id) {
		if (mapper.delEmailLog(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delFileDownLog(int id) {
		if (mapper.delFileDownLog(id) > 0) {
			return true;
		}
		return false;
	}

	// dt_notice 系统公告内容

	@Override
	public Page<Notice> queryNotice(NoticeVo vo) {

		Page<Notice> page = new Page<Notice>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<Notice> dataList = mapper.queryNotice(vo);
		int totalCount = mapper.countNotice(vo);

		page.transform(totalCount, dataList);

		return page;

	}

	@Override
	public void addNotice(Notice notice) {
		mapper.addNotice(notice);
	}

	@Override
	public Notice getNotice(int id) {
		return mapper.getNotice(id);
	}

	@Override
	public boolean updNotice(Notice notice) {
		if (mapper.updNotice(notice) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delNotice(int id) {
		if (mapper.delNotice(id) > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// dt_user_message 用户站内短消息信息表
	@Override
	public Page<UserMessage> queryUserMessage(UserMessageVo vo) {
		Page<UserMessage> page = new Page<UserMessage>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<UserMessage> dataList = mapper.queryUserMessage(vo);
		int totalCount = mapper.countUserMessage(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addUserMessage(UserMessage mes) {
		mapper.addUserMessage(mes);
	}

	@Override
	public UserMessage getUserMessage(long id) {

		return mapper.getUserMessage(id);
	}

	@Override
	public boolean updUserMessage(UserMessage mes) {
		if (mapper.updUserMessage(mes) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserMessage(int id) {
		if (mapper.delUserMessage(id) > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	// dt_user_notice 用户动态消息表
	@Override
	public Page<UserNotice> queryUserNotice(UserNoticeVo vo) {
		Page<UserNotice> page = new Page<UserNotice>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<UserNotice> dataList = mapper.queryUserNotice(vo);
		int totalCount = mapper.countUserNotice(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addUserNotice(UserNotice notice) {
		mapper.addUserNotice(notice);
	}

	@Override
	public UserNotice getUserNotice(int id) {

		return mapper.getUserNotice(id);
	}

	@Override
	public boolean updUserNotice(UserNotice notice) {
		if (mapper.updUserNotice(notice) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserNotice(int id) {
		if (mapper.delUserNotice(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<Files> queryFiles(FilesVo vo) {
		Page<Files> page = new Page<Files>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<Files> dataList = mapper.queryFiles(vo);
		int totalCount = mapper.countFiles(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addFiles(Files files) {
		mapper.addFiles(files);

	}

	@Override
	public Files getFiles(int id) {

		return mapper.getFiles(id);
	}

	@Override
	public boolean updFiles(Files files) {
		if (mapper.updFiles(files) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delFiles(int id) {
		if (mapper.delFiles(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Files> countFilesGroup() {
		List<Files> list = mapper.countFilesGroup();
		return list;
	}
	
	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	@Override
	public Page<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo) {
		Page<UserNoticeRead> page =new Page<UserNoticeRead>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserNoticeRead(vo);
		List<UserNoticeRead> list =mapper.queryUserNoticeRead(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserNoticeRead(UserNoticeRead og) {
		mapper.addUserNoticeRead(og);
	}
	
	@Override
	public UserNoticeRead getUserNoticeRead(int id) {
		return mapper.getUserNoticeRead(id);
	}
	
	@Override
	public boolean updUserNoticeRead(UserNoticeRead og) {
		if(mapper.updUserNoticeRead(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserNoticeRead(int id) {
		if(mapper.delUserNoticeRead(id)>0){
			return true;
		}
		return false;
	}
	
	

}
