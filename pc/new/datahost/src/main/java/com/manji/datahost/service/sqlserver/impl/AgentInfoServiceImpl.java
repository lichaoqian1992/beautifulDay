package com.manji.datahost.service.sqlserver.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.sqlserver.AgentInfoMapper;
import com.manji.datahost.model.sqlserver.agent.AgentInfo;
import com.manji.datahost.service.sqlserver.AgentInfoService;

@Service
public class AgentInfoServiceImpl implements AgentInfoService{
	
	@Autowired
	private AgentInfoMapper mapper;

	@Override
	public AgentInfo getAgentInfo(AgentInfo ui) {

		return mapper.getAgentInfo(ui);
	}

}
