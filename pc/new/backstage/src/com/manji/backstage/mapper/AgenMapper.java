package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;
@Resource
public interface AgenMapper {

	
//	dt_user_role_agentinfo	用户代理商信息表

	int countAgentInfo(AgentInfoVo vo);

	List<AgentInfo> queryAgentInfo(AgentInfoVo vo);

	AgentInfo getAgentInfo(int id);
	
	void addAgentInfo(AgentInfo ai);

	int updAgentInfo(AgentInfo ai);

	int delAgentInfo(int id);
	
//	dt_user_role_agentinfo_temp	用户代理商信息修改临存表

	int countAgentInfoTemp(AgentInfoTempVo vo);

	List<AgentInfoTemp> queryAgentInfoTemp(AgentInfoTempVo vo);
	
	AgentInfoTemp getAgentInfoTemp(int id);
	
	void addAgentInfoTemp(AgentInfoTemp ait);
	
	int updAgentInfoTemp(AgentInfoTemp ait);

	int delAgentInfoTemp(int id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
