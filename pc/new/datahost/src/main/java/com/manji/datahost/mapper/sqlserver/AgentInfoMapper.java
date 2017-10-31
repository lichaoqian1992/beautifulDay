package com.manji.datahost.mapper.sqlserver;


import org.apache.ibatis.annotations.Mapper;

import com.manji.datahost.model.sqlserver.agent.AgentInfo;


@Mapper
public interface AgentInfoMapper {
	
	//代理商信息
	AgentInfo getAgentInfo(AgentInfo ui);

}
