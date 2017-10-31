package com.manji.datahost.service.sqlserver;



import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.notice.UserNotice;
import com.manji.datahost.model.sqlserver.notice.UserNoticeRead;
import com.manji.datahost.model.sqlserver.notice.UserNoticeReadVo;
import com.manji.datahost.model.sqlserver.notice.UserNoticeVo;

public interface NoticeService {

	Page<UserNotice> getUserNotice(UserNoticeVo vo);
	void addUserNotice(UserNotice un);
	int updUserNotice(UserNotice un);
	int delUserNotice(int id);
	
	Page<UserNoticeRead> getUserNoticeRead(UserNoticeReadVo vo);
	void addUserNoticeRead(UserNoticeRead unr);
	int updUserNoticeRead(UserNoticeRead unr);
	int delUserNoticeRead(int id);
	
}
