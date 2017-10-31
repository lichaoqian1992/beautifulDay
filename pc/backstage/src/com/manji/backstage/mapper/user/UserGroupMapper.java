package com.manji.backstage.mapper.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manji.backstage.dto.user.UserGroupCountsDto;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.model.user.User;

public interface UserGroupMapper {

	
	List<Group> countGroupId();
	
	List<Group> findUserGroup();
	/**
	 * 查询用户组别列表
	 * @return
	 */
	List<Group> userGroupList();
	/**
	 * 新增用户组别
	 * @param group
	 */
	void addUserGroup(Group group);
	/**
	 * 修改用户组别
	 * @param group
	 * @return
	 */
	int updUserGroup(Group group);
	/**
	 * 删除用户组别
	 * @param id
	 * @return
	 */
	int delUserGroup(int id);
	/**
	 * 根据用户组别统计用户数量
	 * @return
	 */
	List<UserGroupCountsDto> userGroupCounts();
	/**
	 * 根据用户组别统计当月新增用户数量
	 * @param date
	 * @return
	 */
	List<UserGroupCountsDto> lastUserGroupCounts(String date);
	
	
	List<User> getUsersOnlyGroup();
	
	int chgUserGroup(User user);
	
	Group getUserGroup(int id);
	
	List<Group> queryUserById(int id);
	List<Group> getUserGroupList();
	
	
	
}
