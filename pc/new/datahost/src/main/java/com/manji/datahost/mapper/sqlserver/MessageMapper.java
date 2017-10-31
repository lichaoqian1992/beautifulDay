package com.manji.datahost.mapper.sqlserver;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.manji.datahost.model.sqlserver.message.MessageObj;
import com.manji.datahost.model.sqlserver.message.SendMessage;

@Mapper
public interface MessageMapper {
	
	//统计电话或邮箱数量
	int getCount(@Param("type")String type,@Param("acceptType")String accetType);
	
	//发送短信
	int addMessage(SendMessage sm);
	
	//获取手机或邮箱信息
	List<Object> getMobileInfo(@Param("user_role")String user_role,@Param("type")String type);
	
	//发送对象
	MessageObj getMessageObj(String mobile);
	
	
}
