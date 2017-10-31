package com.manji.backstage.service.message;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.message.UserMessage;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.vo.message.NoticeVo;
import com.manji.backstage.vo.message.UserMessageVo;
import com.manji.backstage.vo.message.UserNoticeVo;

public interface MessageService {

	Page<Notice> queryNotice(NoticeVo vo);

	void addNotice(Notice notice);

	Notice getNotice(int id);

	boolean updNotice(Notice notice);

	boolean delNotice(int id);



	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	Page<UserMessage> queryUserMessage(UserMessageVo vo);
	
	void addUserMessage(UserMessage mes);

	UserMessage getUserMessage(long id);

	boolean updUserMessage(UserMessage mes);

	boolean delUserMessage(int id);

	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	Page<UserNotice> queryUserNotice(UserNoticeVo vo);

	void addUserNotice(UserNotice notice);

	UserNotice getUserNotice(int id);

	boolean updUserNotice(UserNotice notice);

	boolean delUserNotice(int id);

	
	
	
}
