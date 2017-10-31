package com.manji.backstage.service.agent;

import com.manji.backstage.model.agent.AgentInfo;
import com.manji.backstage.model.agent.AgentInfoTemp;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.Notice;
import com.manji.backstage.vo.agent.AgentInfoTempVo;
import com.manji.backstage.vo.agent.AgentInfoVo;
import com.manji.backstage.vo.message.NoticeVo;

public interface AgentService {

	Page<AgentInfo> queryAgentInfo(AgentInfoVo vo);

	AgentInfo getAgentInfo(int id);

	void addAgentInfo(AgentInfo ai);
	
	boolean updAgentInfo(AgentInfo agi);

	boolean delAgentInfo(int id);
	
	

	Page<AgentInfoTemp> queryAgentInfoTemp(AgentInfoTempVo vo);

	AgentInfoTemp getAgentInfoTemp(int id);
	
	void addAgentInfoTemp(AgentInfoTemp ait);
	
	boolean updAgentInfoTemp(AgentInfoTemp ait);

	boolean delAgentInfoTemp(int id);
	
	
	Page<Notice> queryAgentNotice(NoticeVo vo);

	void addAgentNotice(Notice notice);

	Notice getAgentNotice(int id);

	boolean updAgentNotice(Notice notice);

	boolean delAgentNotice(int id);

}
