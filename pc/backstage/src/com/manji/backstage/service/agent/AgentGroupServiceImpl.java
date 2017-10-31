package com.manji.backstage.service.agent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.agent.AgentGroupMapper;
import com.manji.backstage.model.agent.Group;

	@Service
	public class AgentGroupServiceImpl implements AgentGroupService {

	@Autowired
	private AgentGroupMapper mapper;
	
	
	@Override
	public List<Group> countAgentGroup(){
		List<Group> list =  mapper.countAgentGroup();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<Group> selAgentGroups() {

		
		
		return mapper.selAgentGroups();
	}
	
	@Override
	public List<Group> findAgentGroup() {
		
		
		
		return mapper.findAgentGroup();
	}

	@Override
	public void addAgentGroup(Group group) {

		mapper.addAgentGroup(group);
	}

	@Override
	public boolean updAgentGroup(Group group) {

		if(mapper.updAgentGroup(group)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delAgentGroup(int id) {
		
		if(mapper.delAgentGroup(id)>0){
			return true;
		}

		return false;
	}

	@Override
	public boolean shopAgentCounts(String title) {
		if(mapper.shopAgentCounts(title)>1){
			return true;
		}
		return false;
	}

	@Override
	public Group getAgentGroup(int id) {
		return mapper.getAgentGroup(id);
	}

	@Override
	public Group queryAgentById(String id) {
		// TODO Auto-generated method stub
		return mapper.queryAgentById(Integer.valueOf(id));
	}

	@Override
	public List<Group> getAgentGroupList() {
		return mapper.getAgentGroupList();
	}
	
}
