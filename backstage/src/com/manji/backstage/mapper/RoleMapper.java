package com.manji.backstage.mapper;
import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.logger.AuditLog;
import com.manji.backstage.model.logger.RoleAudit;
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
import com.manji.backstage.vo.logger.AuditLogVo;
import com.manji.backstage.vo.logger.RoleAuditVo;
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
@Resource
public interface RoleMapper {

	
	

	List<RoleAudit> queryRoleAudit(RoleAuditVo vo);

	int countRoleAudit(RoleAuditVo vo);

	RoleAudit getRoleAudit(int id);

	int updRoleAudit(RoleAudit ra);

	void addRoleAudit(RoleAudit ra);

	int delRoleAudit(int id);

	List<AuditLog> queryAuditLog(AuditLogVo vo);

	int countAuditLog(AuditLogVo vo);

	AuditLog getAuditLog(int id);

	int updAuditLog(AuditLog al);

	void addAuditLog(AuditLog al);

	int delAuditLog(int id);
	
	
	//管理员
	
int countManager(ManagerVo vo);
	
	List<Manager> queryManager(ManagerVo vo);
	
	void addManager(Manager manager);
	
	Manager getManager(int id);
	
	int updManager(Manager manager);
	
	int delManager(int id);
	
	
	int countManagerLog(ManagerLogVo vo);
	
	List<ManagerLog> queryManagerLog(ManagerLogVo vo);
	
	void addManagerLog(ManagerLog ManagerLog);
	
	ManagerLog getManagerLog(int id);
	
	int updManagerLog(ManagerLog ManagerLog);
	
	int delManagerLog(int id);
	
	
	int countManagerRecharge(ManagerRechargeVo vo);
	
	List<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo);
	
	void addManagerRecharge(ManagerRecharge ManagerRecharge);
	
	ManagerRecharge getManagerRecharge(int id);
	
	int updManagerRecharge(ManagerRecharge ManagerRecharge);
	
	int delManagerRecharge(int id);
	
	
	int countManagerRole(ManagerRoleVo vo);
	
	List<ManagerRole> queryManagerRole(ManagerRoleVo vo);
	
	void addManagerRole(ManagerRole ManagerRole);
	
	ManagerRole getManagerRole(int id);
	
	
	int updManagerRole(ManagerRole ManagerRole);
	
	int delManagerRole(int id);
	
	
	int countManagerRoleValue(ManagerRoleValueVo vo);
	
	List<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo);
	
	void addManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	ManagerRoleValue getManagerRoleValue(int id);
	
	int updManagerRoleValue(ManagerRoleValue ManagerRoleValue);
	
	int delManagerRoleValue(int id);
	
	//agent
	List<AgentGroup> countAgentGroup();
	
	List<AgentGroup> selAgentGroups();

	List<AgentGroup> findAgentGroup();
	
	void addAgentGroup(AgentGroup group);
	
	int updAgentGroup(AgentGroup group);
	
	int delAgentGroup(int id);

	int shopAgentCounts(String title);

	AgentGroup getAgentGroup(int id);

	AgentGroup queryAgentById(int id);

	List<AgentGroup> getAgentGroupList();
	
	//shop
	
	List<ShopGroup> countShopGroup();
	
	List<ShopGroup> selShopGroups();
	
	List<ShopGroup> findShopGroup();
	
	void addShopGroup(ShopGroup group);
	
	int updShopGroup(ShopGroup group);
	
	int delShopGroup(int id);
	
	
	


	ShopGroup getShopGroup(int id);

	List<ShopGroup> queryShopById(int id);
	
	List<ShopGroup> getShopGroupList();	
	
	
	
	//user
	List<UserGroup> countGroupId();
	
	List<UserGroup> findUserGroup();

	List<UserGroup> userGroupList();

	void addUserGroup(UserGroup group);

	int updUserGroup(UserGroup group);

	int delUserGroup(int id);


	List<User> getUsersOnlyGroup();
	
	int chgUserGroup(User user);
	
	UserGroup getUserGroup(int id);
	

	//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
	
	List<RoleOperatorType> queryRoleOperatorType(RoleOperatorTypeVo vo);
	
	int countRoleOperatorType(RoleOperatorTypeVo vo);
	
	RoleOperatorType getRoleOperatorType(int id);
	
	int updRoleOperatorType(RoleOperatorType art);
	
	void addRoleOperatorType(RoleOperatorType art);
	
	int delRoleOperatorType(int id);
	
	//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
	
	List<RoleOperatorTypeValue> queryRoleOperatorTypeValue(RoleOperatorTypeValueVo vo);
	
	int countRoleOperatorTypeValue(RoleOperatorTypeValueVo vo);
	
	RoleOperatorTypeValue getRoleOperatorTypeValue(int id);
	
	int updRoleOperatorTypeValue(RoleOperatorTypeValue art);
	
	void addRoleOperatorTypeValue(RoleOperatorTypeValue art);
	
	int delRoleOperatorTypeValue(int id);
	
	//2.8.4.dt_user_role（用户角色信息表）
	
	List<UserRole> queryUserRole(UserRoleVo vo);
	
	int countUserRole(UserRoleVo vo);
	
	UserRole getUserRole(int id);
	
	int updUserRole(UserRole art);
	
	void addUserRole(UserRole art);
	
	int delUserRole(int id);
	
	//2.8.3.dt_role_operator_navigation（角色后台管理总菜单信息表）
	
	List<RoleOperatorNavigation> queryRoleOperatorNavigation(RoleOperatorNavigationVo vo);
	
	int countRoleOperatorNavigation(RoleOperatorNavigationVo vo);
	
	RoleOperatorNavigation getRoleOperatorNavigation(int id);
	
	int updRoleOperatorNavigation(RoleOperatorNavigation art);
	
	void addRoleOperatorNavigation(RoleOperatorNavigation art);
	
	int delRoleOperatorNavigation(int id);
	
	//dt_user_role_medal(用户角色荣誉勋章信息表)
	
	List<UserRoleMedal> queryUserRoleMedal(UserRoleMedalVo vo);
	
	int countUserRoleMedal(UserRoleMedalVo vo);
	
	UserRoleMedal getUserRoleMedal(int id);
	
	int updUserRoleMedal(UserRoleMedal art);
	
	void addUserRoleMedal(UserRoleMedal art);
	
	int delUserRoleMedal(int id);
	
	//dt_user_role_group(用户角色分组信息表)
	
	List<UserRoleGroup> queryUserRoleGroup(UserRoleGroupVo vo);
	
	int countUserRoleGroup(UserRoleGroupVo vo);
	
	UserRoleGroup getUserRoleGroup(int id);
	
	int updUserRoleGroup(UserRoleGroup art);
	
	void addUserRoleGroup(UserRoleGroup art);
	
	int delUserRoleGroup(int id);
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	
	List<UserRoleBusiness> queryUserRoleBusiness(UserRoleBusinessVo vo);
	
	int countUserRoleBusiness(UserRoleBusinessVo vo);
	
	UserRoleBusiness getUserRoleBusiness(int id);
	
	int updUserRoleBusiness(UserRoleBusiness art);
	
	void addUserRoleBusiness(UserRoleBusiness art);
	
	int delUserRoleBusiness(int id);
	
	
}
