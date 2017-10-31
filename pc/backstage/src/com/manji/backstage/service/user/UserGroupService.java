package com.manji.backstage.service.user;

import java.util.List;

import com.manji.backstage.model.user.Group;
import com.manji.backstage.dto.user.UserGroupCountsDto;
import com.manji.backstage.model.user.User;

public interface UserGroupService {
	List<Group> countGroupId();
	
	/**
	 * 查询id,group_id
	 * @return
	 */
	List<Group> findUserGroup();
	/**
	 * 查询用户组别记录
	 * @return
	 */
	List<Group> userGroupList();
	/**
	 * 新增用户组别
	 * @param group
	 */
	void addUserGroup(Group group);
	/**
	 * 修改用户组别记录
	 * @param group
	 * @return
	 */
	boolean updUserGroup(Group group);
	/**
	 * 删除用户组别
	 * @param id
	 * @return
	 */
	boolean delUserGroup(int id);
	
	
	List<UserGroupCountsDto> userGroupCounts();
	
	List<UserGroupCountsDto> lastUserGroupCounts();	
	
	List<User> getUsersOnlyGroup();
	
	boolean chgUserGroup(User user);
	
	Group getUserGroup(int id);
	
	List<Group> queryUserById(String id);
	List<Group> getUserGroupList();
	
	
}
