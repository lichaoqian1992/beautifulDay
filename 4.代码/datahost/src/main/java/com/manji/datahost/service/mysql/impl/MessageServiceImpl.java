package com.manji.datahost.service.mysql.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manji.datahost.mapper.sqlserver.MessageMapper;
import com.manji.datahost.model.sqlserver.message.MessageObj;
import com.manji.datahost.model.sqlserver.message.SendMessage;
import com.manji.datahost.service.mysql.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper mapper;

	@Override
	public int getCount(String type, String accetType) {

		return mapper.getCount(type, accetType);
	}

	@Override
	public int addMessage(SendMessage sm) {

		return mapper.addMessage(sm);
	}

	@Override
	public MessageObj getMessageObj(String mobile) {

		return mapper.getMessageObj(mobile);
	}

	@Override
	public List<Object> getMobileInfo(String user_role,String type) {

		return mapper.getMobileInfo(user_role,type);
	}

}
