package com.manji.backstage.service.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.message.MessageMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.model.message.UserMessage;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.vo.message.NoticeVo;
import com.manji.backstage.vo.message.UserMessageVo;
import com.manji.backstage.vo.message.UserNoticeVo;
@Service
public class MessageServiceImpl implements MessageService {

	
	@Autowired
	private MessageMapper mapper;

	@Override
	public Page<Notice> queryNotice(NoticeVo vo) {

		Page<Notice> page =new Page<Notice>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Notice> dataList =mapper.queryNotice(vo);
		int totalCount =mapper.countNotice(vo);
		
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
		if(mapper.updNotice(notice)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delNotice(int id) {
		if(mapper.delNotice(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<UserMessage> queryUserMessage(UserMessageVo vo) {
		Page<UserMessage> page =new Page<UserMessage>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<UserMessage> dataList =mapper.queryUserMessage(vo);
		int totalCount =mapper.countUserMessage(vo);
		
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
		if(mapper.updUserMessage(mes)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserMessage(int id) {
		if(mapper.delUserMessage(id)>0){
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Page<UserNotice> queryUserNotice(UserNoticeVo vo) {
		Page<UserNotice> page =new Page<UserNotice>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<UserNotice> dataList =mapper.queryUserNotice(vo);
		int totalCount =mapper.countUserNotice(vo);
		
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
		if(mapper.updUserNotice(notice)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserNotice(int id) {
		if(mapper.delUserNotice(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
}
