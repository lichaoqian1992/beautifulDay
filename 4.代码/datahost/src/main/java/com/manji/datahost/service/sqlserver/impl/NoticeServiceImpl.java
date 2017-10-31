package com.manji.datahost.service.sqlserver.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manji.datahost.mapper.sqlserver.NoticeMapper;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.notice.UserNotice;
import com.manji.datahost.model.sqlserver.notice.UserNoticeRead;
import com.manji.datahost.model.sqlserver.notice.UserNoticeReadVo;
import com.manji.datahost.model.sqlserver.notice.UserNoticeVo;
import com.manji.datahost.service.sqlserver.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper mapper;

	@Override
	public Page<UserNotice> getUserNotice(UserNoticeVo vo) {
		
		Page<UserNotice> page = new Page<UserNotice>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserNotice(vo);
		List<UserNotice> list = mapper.getUserNotice(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public void addUserNotice(UserNotice un) {
		
		mapper.addUserNotice(un);
	}

	@Override
	public int updUserNotice(UserNotice un) {

		return mapper.updUserNotice(un);
	}

	@Override
	public int delUserNotice(int id) {

		return mapper.delUserNotice(id);
	}

	@Override
	public Page<UserNoticeRead> getUserNoticeRead(UserNoticeReadVo vo) {

		Page<UserNoticeRead> page = new Page<UserNoticeRead>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserNoticeRead(vo);
		List<UserNoticeRead> list = mapper.getUserNoticeRead(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public void addUserNoticeRead(UserNoticeRead unr) {

		mapper.addUserNoticeRead(unr);
	}

	@Override
	public int updUserNoticeRead(UserNoticeRead unr) {

		return mapper.updUserNoticeRead(unr);
	}

	@Override
	public int delUserNoticeRead(int id) {

		return mapper.delUserNoticeRead(id);
	}


	
}
