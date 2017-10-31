package com.manji.backstage.mapper.message;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.message.UserMessage;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.vo.message.NoticeVo;
import com.manji.backstage.vo.message.UserMessageVo;
import com.manji.backstage.vo.message.UserNoticeVo;
@Resource
public interface MessageMapper {

	List<Notice> queryNotice(NoticeVo vo);

	int countNotice(NoticeVo vo);

	void addNotice(Notice notice);

	Notice getNotice(int id);

	int updNotice(Notice notice);

	int delNotice(int id); 

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	List<UserMessage> queryUserMessage(UserMessageVo vo);

	int countUserMessage(UserMessageVo vo);

	void addUserMessage(UserMessage mes);

	UserMessage getUserMessage(long id);

	int updUserMessage(UserMessage mes);

	int delUserMessage(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	int countUserNotice(UserNoticeVo vo);

	List<UserNotice> queryUserNotice(UserNoticeVo vo);

	void addUserNotice(UserNotice notice);

	UserNotice getUserNotice(int id);

	int updUserNotice(UserNotice notice);

	int delUserNotice(int id);

	
	
	
}
