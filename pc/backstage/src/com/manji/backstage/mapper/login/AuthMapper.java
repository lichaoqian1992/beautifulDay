package com.manji.backstage.mapper.login;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.dto.login.UserRoleDto;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.vo.login.PermissionVo;
@Resource
public interface AuthMapper {
	
	

	List<User> getLocalUsers();

	List<Role> getLocalRoleByUser(int id);

	User getLocalUser(int userId);

	int updLocalUser(User user);

	int delLocalRoleToUser(int id);

	void addLocalRoleToUser(RoleToUser rtu);

	Role getLocalRoleList(int id);

	void addLocalUser(User u);

	int updLocalRole(Role role);
	
	List<Role> getLocalRole();
	
	
	

	int checkRoleName(String roleName);

	void addLocalRole(Role r);

	int delLocalRole(int id);
	
	int countPermission(PermissionVo vo);

	List<Permission> queryLocalPermission(PermissionVo vo);
	
	Permission getPermission(int id);

	void addPermission(Permission per);
	
	int updPermission(Permission per);
	
	int delPermission(int id);
	

	Role getLocalRoleByUserId(int userId);

	List<RoleToUser> getURRelation(int userId);
	
	
	
	
	
	
	
	int checkUserName(String username);

	int delLocalUser(int user_id);

	RoleToUser getLocalRoleToUser(int id);

	String getPermissionModel(String url);
	
}
