package com.manji.backstage.mapper.agent;

import java.util.List;


import org.springframework.stereotype.Service;

import com.manji.backstage.model.agent.Group;

public interface AgentGroupMapper {

	List<Group> countAgentGroup();
	
	List<Group> selAgentGroups();

	List<Group> findAgentGroup();
	
	void addAgentGroup(Group group);
	
	int updAgentGroup(Group group);
	
	int delAgentGroup(int id);

	int shopAgentCounts(String title);

	Group getAgentGroup(int id);

	Group queryAgentById(int id);

	List<Group> getAgentGroupList();
	
}
