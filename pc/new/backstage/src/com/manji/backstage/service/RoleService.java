package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.AuditLog;
import com.manji.backstage.model.logger.RoleAudit;
import com.manji.backstage.model.logger.UserNoticeRead;
import com.manji.backstage.model.monitor.Manager;
import com.manji.backstage.model.monitor.ManagerLog;
import com.manji.backstage.model.monitor.ManagerRecharge;
import com.manji.backstage.model.monitor.ManagerRole;
import com.manji.backstage.model.monitor.ManagerRoleValue;
import com.manji.backstage.model.role.RoleOperatorNavigation;
import com.manji.backstage.model.role.RoleOperatorType;
import com.manji.backstage.model.role.RoleOperatorTypeValue;
import com.manji.backstage.model.role.UserRole;
import com.manji.backstage.model.role.UserRoleBusiness;
import com.manji.backstage.model.role.UserRoleGroup;
import com.manji.backstage.model.role.UserRoleMedal;
import com.manji.backstage.model.shop.ShopGroup;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserGroup;
import com.manji.backstage.service.RoleService;
import com.manji.backstage.vo.logger.AuditLogVo;
import com.manji.backstage.vo.logger.RoleAuditVo;
import com.manji.backstage.vo.logger.UserNoticeReadVo;
import com.manji.backstage.vo.monitor.ManagerLogVo;
import com.manji.backstage.vo.monitor.ManagerRechargeVo;
import com.manji.backstage.vo.monitor.ManagerRoleValueVo;
import com.manji.backstage.vo.monitor.ManagerRoleVo;
import com.manji.backstage.vo.monitor.ManagerVo;
import com.manji.backstage.vo.role.RoleOperatorNavigationVo;
import com.manji.backstage.vo.role.RoleOperatorTypeValueVo;
import com.manji.backstage.vo.role.RoleOperatorTypeVo;
import com.manji.backstage.vo.role.UserRoleBusinessVo;
import com.manji.backstage.vo.role.UserRoleGroupVo;
import com.manji.backstage.vo.role.UserRoleMedalVo;
import com.manji.backstage.vo.role.UserRoleVo;

public interface RoleService {

	Page<RoleAudit> queryRoleAudit(RoleAuditVo vo);

	RoleAudit getRoleAudit(int id);

	boolean updRoleAudit(RoleAudit ra);

	boolean addRoleAudit(RoleAudit ra);

	boolean delRoleAudit(int id);

	Page<AuditLog> queryAuditLog(AuditLogVo vo);

	AuditLog getAuditLog(int id);

	boolean updAuditLog(AuditLog al);

	boolean addAuditLog(AuditLog al);

	boolean delAuditLog(int id);

	//管理员
	
	Page<Manager> queryManager(ManagerVo vo);
	
	void addManager(Manager Manager);
	
	Manager getManager(int id);
	
	boolean updManager(Manager Manager);
	
	boolean delManager(int id);
	
	
	Page<ManagerLog> queryManagerLog(ManagerLogVo vo);
	
	void addManagerLog(ManagerLog ManagerLog);
	
	ManagerLog getManagerLog(int id);
	
	boolean updManagerLog(ManagerLog ManagerLog);
	
	boolean delManagerLog(int id);
	
	
	Page<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo);
	
	void addManagerRecharge(ManagerRecharge ManagerRecharge);
	
	ManagerRecharge getManagerRecharge(int id);
	
	boolean updManagerRecharge(ManagerRecharge ManagerRecharge);
	
	boolean delManagerRecharge(int id);
	
	
	Page<ManagerRole> queryManagerRole(ManagerRoleVo vo);
	
	void addManagerRole(ManagerRole ManagerRole);
	
	ManagerRole getManagerRole(int id);
	
	boolean updManagerRole(ManagerRole ManagerRole);
	
	boolean delManagerRole(int id);
	
	
	Page<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo);
	
	void addManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	ManagerRoleValue getManagerRoleValue(int id);
	
	boolean updManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	boolean delManagerRoleValue(int id);
	
	
	//agent
	List<AgentGroup> countAgentGroup();
	
	List<AgentGroup> selAgentGroups();
	
	List<AgentGroup> findAgentGroup();
	
	void addAgentGroup(AgentGroup group);
	
	boolean updAgentGroup(AgentGroup group);
	
	boolean delAgentGroup(int id);

	boolean shopAgentCounts(String title);

	AgentGroup getAgentGroup(int id);

	AgentGroup queryAgentById(String id);

	List<AgentGroup> getAgentGroupList();
	
	
	//shop
	
	List<ShopGroup> countShopGroup();

	List<ShopGroup> selShopGroups();
	
	List<ShopGroup> findShopGroup();
	
	void addShopGroup(ShopGroup group);
	
	boolean updShopGroup(ShopGroup group);
	
	boolean delShopGroup(int id);
	

	ShopGroup getShopGroup(int id);

	List<ShopGroup> queryShopById(String id);

	
	List<ShopGroup> getShopGroupList();
	
	
	//user
	List<UserGroup> countGroupId();
	List<UserGroup> findUserGroup();

	List<UserGroup> userGroupList();

	void addUserGroup(UserGroup group);

	boolean updUserGroup(UserGroup group);

	boolean delUserGroup(int id);
	
	
	List<User> getUsersOnlyGroup();
	
	boolean chgUserGroup(User user);
	
	UserGroup getUserGroup(int id);
	
	
	//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
	
	Page<RoleOperatorType> queryRoleOperatorType(RoleOperatorTypeVo vo);
	
	RoleOperatorType getRoleOperatorType(int id);
	
	boolean updRoleOperatorType(RoleOperatorType si);
	
	void addRoleOperatorType(RoleOperatorType si);
	
	boolean delRoleOperatorType(int id);
	
	//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
	
	Page<RoleOperatorTypeValue> queryRoleOperatorTypeValue(RoleOperatorTypeValueVo vo);
	
	RoleOperatorTypeValue getRoleOperatorTypeValue(int id);
	
	boolean updRoleOperatorTypeValue(RoleOperatorTypeValue si);
	
	void addRoleOperatorTypeValue(RoleOperatorTypeValue si);
	
	boolean delRoleOperatorTypeValue(int id);
	
	//2.8.4.dt_user_role（用户角色信息表）
	
	Page<UserRole> queryUserRole(UserRoleVo vo);
	
	UserRole getUserRole(int id);
	
	boolean updUserRole(UserRole si);
	
	void addUserRole(UserRole si);
	
	boolean delUserRole(int id);
	
	//2.8.3.dt_role_operator_navigation（角色后台管理总菜单信息表）
	
	Page<RoleOperatorNavigation> queryRoleOperatorNavigation(RoleOperatorNavigationVo vo);
	
	RoleOperatorNavigation getRoleOperatorNavigation(int id);
	
	boolean updRoleOperatorNavigation(RoleOperatorNavigation si);
	
	void addRoleOperatorNavigation(RoleOperatorNavigation si);
	
	boolean delRoleOperatorNavigation(int id);
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	
	Page<UserRoleBusiness> queryUserRoleBusiness(UserRoleBusinessVo vo);
	
	UserRoleBusiness getUserRoleBusiness(int id);
	
	boolean updUserRoleBusiness(UserRoleBusiness si);
	
	void addUserRoleBusiness(UserRoleBusiness si);
	
	boolean delUserRoleBusiness(int id);
	
	
	//dt_user_role_medal(用户角色荣誉勋章信息表)
	
	Page<UserRoleMedal> queryUserRoleMedal(UserRoleMedalVo vo);
	
	UserRoleMedal getUserRoleMedal(int id);
	
	boolean updUserRoleMedal(UserRoleMedal si);
	
	void addUserRoleMedal(UserRoleMedal si);
	
	boolean delUserRoleMedal(int id);
	
	
	//dt_user_role_group(用户角色分组信息表)
	
	Page<UserRoleGroup> queryUserRoleGroup(UserRoleGroupVo vo);
	
	UserRoleGroup getUserRoleGroup(int id);
	
	boolean updUserRoleGroup(UserRoleGroup si);
	
	void addUserRoleGroup(UserRoleGroup si);
	
	boolean delUserRoleGroup(int id);
	
}
