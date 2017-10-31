package com.manji.backstage.service.login;

import java.util.List;

import com.manji.backstage.dto.login.UserRoleDto;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.vo.login.PermissionVo;

public interface AuthService {

//	List<User> userList();
//	List<User> queryUserList(String queryStr);
//	String queryUserPassword(String id);
//	boolean queryUserByName(String userName);
//	void addUser(String userName,String password);
//	boolean delUser(String user_id);
//	
//	List<UserRoleDto> userRoleList();
//	List<UserRoleDto> queryUserRoleList(UserRoleDto urd);
//	User getUserById(String user_id);
//	List<RoleToUser> getRoleByUserId(String user_id);
//	boolean updLocalUser(User user);
//	
//	List<Role> getRoleList();
//	
//	
//	List<Role> roleList();
//	boolean queryRoleByName(String roleName);
//	void addRole(String roleName);
//	boolean delRole(String role_id);
//	
//	
//	List<RoleToUser> getRURelaList();
//	List<RoleToUser> queryRURelaList(String userName,String roleName);
//	boolean addRoleToUser(RoleToUser rtu);
//	boolean delRoleToUser(String rtu_id);
//	
//	
//	List<Rights> rightsList();
//	List<Rights> queryRightsList(String queryStr);
//	
//	
//	List<RightsToRole> getRRRelaList();
//	List<RightsToRole> queryRRRelaList(String roleName);
	
	
	List<User> getLocalUserAndRole();
	List<Role> getLocalRoleByUser(int userId);
	User getLocalUser(int userId);
	boolean updLocalUser(User user);
	boolean delLocalRoleToUser(int id);
	void addLocalRoleToUser(RoleToUser rtu);
	Role getLocalRoleList(int id);
	void addLocalUser(User u);
	List<Role> getLocalRole();
	boolean updLocalRole(Role role);
	boolean checkRoleName(String roleName);
	void addLocalRole(Role r);
	boolean delLocalRole(int id);
	Role getLocalRole(int roleId);
	
	Page<Permission> queryLocalPermission(PermissionVo vo);
	Permission getPermission(int id);
	void addPermission(Permission per);
	boolean updPermission(Permission per);
	boolean delPermission(int id);
	
	Role getLocalRoleByUserId(int userId);
	List<RoleToUser> getURRelation(int userId);
	
	boolean checkUserName(String username);
	boolean delLocalUser(int user_id);
	RoleToUser getLocalRoleToUser(int id);
	
	boolean checkAuth(String userid,String url);
	
	
	
	
	
	
	
}
