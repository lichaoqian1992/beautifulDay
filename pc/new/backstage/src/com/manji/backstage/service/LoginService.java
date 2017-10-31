package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.BacMenu;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.login.Permission;
import com.manji.backstage.model.login.Role;
import com.manji.backstage.model.login.RoleToUser;
import com.manji.backstage.model.login.User;
import com.manji.backstage.vo.login.LoggersVo;
import com.manji.backstage.vo.login.PermissionVo;

public interface LoginService {

	
	User checkUser(String userName, String password);
	
	
	List<String> getAuth(String userName);
	
	
	

	
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
	
	
	void addLoggers(Loggers log);
	
	Loggers getLoggers(int id);
	
	Page<Loggers> queryLoggers(LoggersVo vo);
	
	Data addData(String json);
	
	Data getData(int id);
	
	
	List<BacMenu> getLoginMenu(int userid);
	
}
