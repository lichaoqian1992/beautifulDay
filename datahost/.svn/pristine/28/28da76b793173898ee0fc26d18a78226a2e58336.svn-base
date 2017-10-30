package com.manji.datahost.service.mysql;

import java.util.List;

import com.manji.datahost.model.sqlserver.message.MessageObj;
import com.manji.datahost.model.sqlserver.message.SendMessage;

public interface MessageService {

	//统计电话，邮箱数量
	int getCount(String type,String accetType);
	
	//发送短信
	int addMessage(SendMessage sm);
	
	//获取手机或邮箱信息
	List<Object> getMobileInfo(String user_role,String type);
	
	//发送对象
	MessageObj getMessageObj(String mobile);
}
