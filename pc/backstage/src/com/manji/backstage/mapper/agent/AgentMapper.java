package com.manji.backstage.mapper.agent;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;
import com.manji.backstage.vo.message.NoticeVo;
@Resource
public interface AgentMapper {

	int countAgentInfo(AgentInfoVo vo);

	List<AgentInfo> queryAgentInfo(AgentInfoVo vo);

	AgentInfo getAgentInfo(int id);
	
	void addAgentInfo(AgentInfo ai);

	int updAgentInfo(AgentInfo ai);

	int delAgentInfo(int id);
	
	

	int countAgentInfoTemp(AgentInfoTempVo vo);

	List<AgentInfoTemp> queryAgentInfoTemp(AgentInfoTempVo vo);
	
	AgentInfoTemp getAgentInfoTemp(int id);
	
	void addAgentInfoTemp(AgentInfoTemp ait);
	
	int updAgentInfoTemp(AgentInfoTemp ait);

	int delAgentInfoTemp(int id);

	
	
	List<Notice> queryAgentNotice(NoticeVo vo);

	int countAgentNotice(NoticeVo vo);

	void addAgentNotice(Notice notice);

	Notice getAgentNotice(int id);

	int updAgentNotice(Notice notice);

	int delAgentNotice(int id);

}
