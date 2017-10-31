package com.manji.backstage.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.dto.user.UserGroupCountsDto;
import com.manji.backstage.mapper.user.UserGroupMapper;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.model.user.User;
import com.manji.backstage.utils.TimeUtils;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	
	@Autowired
	private UserGroupMapper mapper;
	

	@Override
	public List<Group> countGroupId(){
		List<Group> list =  mapper.countGroupId();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<Group> findUserGroup(){
		List<Group> list = mapper.findUserGroup();
		return list;
	}
	
	@Override
	public List<Group> userGroupList() {
		

		List<Group> list =mapper.userGroupList();

		return list;
	}

	@Override
	public void addUserGroup(Group group) {

		mapper.addUserGroup(group);
		
		
	}

	@Override
	public boolean updUserGroup(Group group) {

		if(mapper.updUserGroup(group)>0){
			return true;
		}
		
		
		return false;
	}

	@Override
	public boolean delUserGroup(int id) {

		if(mapper.delUserGroup(id)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserGroupCountsDto> userGroupCounts(){
		
		return mapper.userGroupCounts();
	}
	
	@Override
	public List<UserGroupCountsDto> lastUserGroupCounts(){
		
		String date =TimeUtils.getDate();
		
		return mapper.lastUserGroupCounts(date);
	}
	
	@Override
	public List<User> getUsersOnlyGroup(){
		
		return mapper.getUsersOnlyGroup();
	}
	
	@Override
	public boolean chgUserGroup(User user){
		
		if(mapper.chgUserGroup(user)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public Group getUserGroup(int id) {
		// TODO Auto-generated method stub
		return mapper.getUserGroup(id);
	}

	@Override
	public List<Group> queryUserById(String id) {
		// TODO Auto-generated method stub
		return mapper.queryUserById(Integer.valueOf(id));
	}

	@Override
	public List<Group> getUserGroupList() {
		return mapper.getUserGroupList();
	}
	
	
}
