package com.manji.backstage.service.agent;

import java.util.List;

import com.manji.backstage.model.agent.Group;

public interface AgentGroupService {

	List<Group> countAgentGroup();
	
	List<Group> selAgentGroups();
	
	List<Group> findAgentGroup();
	
	void addAgentGroup(Group group);
	
	boolean updAgentGroup(Group group);
	
	boolean delAgentGroup(int id);

	boolean shopAgentCounts(String title);

	Group getAgentGroup(int id);

	Group queryAgentById(String id);

	List<Group> getAgentGroupList();
	
	
	
}
