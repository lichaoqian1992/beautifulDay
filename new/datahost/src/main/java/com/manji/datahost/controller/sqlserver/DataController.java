package com.manji.datahost.controller.sqlserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.notice.UserNotice;
import com.manji.datahost.model.sqlserver.notice.UserNoticeRead;
import com.manji.datahost.model.sqlserver.notice.UserNoticeReadVo;
import com.manji.datahost.model.sqlserver.notice.UserNoticeVo;
import com.manji.datahost.service.sqlserver.NoticeService;
import com.manji.datahost.utils.Message;
import com.manji.datahost.utils.ResultEmuns;

@RestController
public class DataController {
	
	@Autowired
	private NoticeService service;
	
	@GetMapping("getUserNotice")
	public Message getUserNotice(UserNoticeVo vo){
		
		Message msg = new Message();
		Page<UserNotice> userNotice= service.getUserNotice(vo);
		
		if(userNotice.getDataList().size() > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(userNotice);
		}else{
			msg.setCode(ResultEmuns.NODATA.getCode());
			msg.setMessage(ResultEmuns.NODATA.getMessage());
		}
		
		return msg;
	}
	
	@GetMapping("updUserNotice")
	public Message updUserNotice(UserNotice un){
		
		Message msg = new Message();
		int count = service.updUserNotice(un);
		if(count > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(ResultEmuns.ERROR.getMessage());
		}
		
		return msg;
	}
	
	@GetMapping("addUserNotice")
	public Message addUserNotice(UserNotice un){
		
		Message msg = new Message();
		service.addUserNotice(un);
		
		return msg;
	}
	
	@GetMapping("delUserNotice")
	public Message delUserNotice(int id){
		
		Message msg = new Message();
		int count = service.delUserNotice(id);
		if(count > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(ResultEmuns.ERROR.getMessage());
		}
		
		return msg;
	}
	
	/////////////////////////////////////
	
	@GetMapping("getUserNoticeRead")
	public Message getUserNoticeRead(UserNoticeReadVo vo){
		
		Message msg = new Message();
		Page<UserNoticeRead> userNotice= service.getUserNoticeRead(vo);
		
		if(userNotice.getDataList().size() > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(userNotice);
		}else{
			msg.setCode(ResultEmuns.NODATA.getCode());
			msg.setMessage(ResultEmuns.NODATA.getMessage());
		}
		
		return msg;
	}
	
	@GetMapping("updUserNoticeRead")
	public Message updUserNoticeRead(UserNoticeRead un){
		
		Message msg = new Message();
		int count = service.updUserNoticeRead(un);
		if(count > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(ResultEmuns.ERROR.getMessage());
		}
		
		return msg;
	}
	
	@GetMapping("addUserNoticeRead")
	public Message addUserNoticeRead(UserNoticeRead un){
		
		Message msg = new Message();
		service.addUserNoticeRead(un);
		
		return msg;
	}
	
	@GetMapping("delUserNoticeRead")
	public Message delUserNoticeRead(int id){
		
		Message msg = new Message();
		int count = service.delUserNoticeRead(id);
		if(count > 0){
			msg.setCode(ResultEmuns.SUCCESS.getCode());
			msg.setMessage(ResultEmuns.SUCCESS.getMessage());
			msg.setResult(count);
		}else{
			msg.setCode(ResultEmuns.ERROR.getCode());
			msg.setMessage(ResultEmuns.ERROR.getMessage());
		}
		
		return msg;
	}
}
