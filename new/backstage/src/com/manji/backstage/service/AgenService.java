package com.manji.backstage.service;

import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;

public interface AgenService {

	
	
//	dt_user_role_agentinfo	用户代理商信息表

	Page<AgentInfo> queryAgentInfo(AgentInfoVo vo);

	AgentInfo getAgentInfo(int id);

	void addAgentInfo(AgentInfo ai);
	
	boolean updAgentInfo(AgentInfo agi);

	boolean delAgentInfo(int id);
	
//	dt_user_role_agentinfo_temp	用户代理商信息修改临存表

	Page<AgentInfoTemp> queryAgentInfoTemp(AgentInfoTempVo vo);

	AgentInfoTemp getAgentInfoTemp(int id);
	
	void addAgentInfoTemp(AgentInfoTemp ait);
	
	boolean updAgentInfoTemp(AgentInfoTemp ait);

	boolean delAgentInfoTemp(int id);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
