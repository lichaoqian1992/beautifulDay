package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.RoleMapper;
import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.base.Page;
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
import com.manji.backstage.service.RoleService;
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

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper mapper;

	
	
	@Override
	public Page<RoleAudit> queryRoleAudit(RoleAuditVo vo) {
		Page<RoleAudit> page =new Page<RoleAudit>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<RoleAudit> dataList =mapper.queryRoleAudit(vo);
		int totalCount =mapper.countRoleAudit(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public RoleAudit getRoleAudit(int id) {
		
		return mapper.getRoleAudit(id);
	}

	@Override
	public boolean updRoleAudit(RoleAudit ra) {
		
		if(mapper.updRoleAudit(ra)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addRoleAudit(RoleAudit ra) {
		mapper.addRoleAudit(ra);
		return true;
	}

	@Override
	public boolean delRoleAudit(int id) {
		if(mapper.delRoleAudit(id)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public Page<AuditLog> queryAuditLog(AuditLogVo vo) {
		Page<AuditLog> page =new Page<AuditLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<AuditLog> dataList =mapper.queryAuditLog(vo);
		int totalCount =mapper.countAuditLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public AuditLog getAuditLog(int id) {

		return mapper.getAuditLog(id);
	}

	@Override
	public boolean updAuditLog(AuditLog al) {

		if(mapper.updAuditLog(al)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean addAuditLog(AuditLog al) {
		
		mapper.addAuditLog(al);
		return true;
	}

	@Override
	public boolean delAuditLog(int id) {
		if(mapper.delAuditLog(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<Manager> queryManager(ManagerVo vo) {
		Page<Manager> page =new Page<Manager>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Manager> dataList =mapper.queryManager(vo);
		int totalCount =mapper.countManager(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	@Override
	public void addManager(Manager manager) {
		mapper.addManager(manager);
		
	}
	
	@Override
	public Manager getManager(int id) {
		
		return mapper.getManager(id);
	}
	
	@Override
	public boolean updManager(Manager manager) {
		if(mapper.updManager(manager)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManager(int id) {
		if(mapper.delManager(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerLog> queryManagerLog(ManagerLogVo vo) {
		Page<ManagerLog> page =new Page<ManagerLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerLog> dataList =mapper.queryManagerLog(vo);
		int totalCount =mapper.countManagerLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerLog(ManagerLog ml) {
		mapper.addManagerLog(ml);
		
	}
	
	@Override
	public ManagerLog getManagerLog(int id) {
		
		return mapper.getManagerLog(id);
	}
	
	@Override
	public boolean updManagerLog(ManagerLog ml) {
		if(mapper.updManagerLog(ml)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerLog(int id) {
		if(mapper.delManagerLog(id)>0){
			return true;	
		}
		return false;
	}

	
	@Override               
	public Page<ManagerRecharge> queryManagerRecharge(ManagerRechargeVo vo) {
		Page<ManagerRecharge> page =new Page<ManagerRecharge>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRecharge> dataList =mapper.queryManagerRecharge(vo);
		int totalCount =mapper.countManagerRecharge(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRecharge(ManagerRecharge mr) {
		mapper.addManagerRecharge(mr);
		
	}
	
	@Override
	public ManagerRecharge getManagerRecharge(int id) {
		
		return mapper.getManagerRecharge(id);
	}
	
	@Override
	public boolean updManagerRecharge(ManagerRecharge mr) {
		if(mapper.updManagerRecharge(mr)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRecharge(int id) {
		if(mapper.delManagerRecharge(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerRole> queryManagerRole(ManagerRoleVo vo) {
		Page<ManagerRole> page =new Page<ManagerRole>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRole> dataList =mapper.queryManagerRole(vo);
		int totalCount =mapper.countManagerRole(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRole(ManagerRole mr) {
		mapper.addManagerRole(mr);
		
	}
	
	@Override
	public ManagerRole getManagerRole(int id) {
		
		return mapper.getManagerRole(id);
	}
	
	@Override
	public boolean updManagerRole(ManagerRole mr) {
		if(mapper.updManagerRole(mr)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRole(int id) {
		if(mapper.delManagerRole(id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override               
	public Page<ManagerRoleValue> queryManagerRoleValue(ManagerRoleValueVo vo) {
		Page<ManagerRoleValue> page =new Page<ManagerRoleValue>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ManagerRoleValue> dataList =mapper.queryManagerRoleValue(vo);
		int totalCount =mapper.countManagerRoleValue(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addManagerRoleValue(ManagerRoleValue mrv) {
		mapper.addManagerRoleValue(mrv);
		
	}
	
	@Override
	public ManagerRoleValue getManagerRoleValue(int id) {
		
		return mapper.getManagerRoleValue(id);
	}
	
	@Override
	public boolean updManagerRoleValue(ManagerRoleValue mrv) {
		if(mapper.updManagerRoleValue(mrv)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delManagerRoleValue(int id) {
		if(mapper.delManagerRoleValue(id)>0){
			return true;	
		}
		return false;
	}
	
	//agent
	@Override
	public List<AgentGroup> countAgentGroup(){
		List<AgentGroup> list =  mapper.countAgentGroup();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<AgentGroup> selAgentGroups() {

		
		
		return mapper.selAgentGroups();
	}
	
	@Override
	public List<AgentGroup> findAgentGroup() {
		
		
		
		return mapper.findAgentGroup();
	}

	@Override
	public void addAgentGroup(AgentGroup group) {

		mapper.addAgentGroup(group);
	}

	@Override
	public boolean updAgentGroup(AgentGroup group) {

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
	public AgentGroup getAgentGroup(int id) {
		return mapper.getAgentGroup(id);
	}

	@Override
	public AgentGroup queryAgentById(String id) {
		// TODO Auto-generated method stub
		return mapper.queryAgentById(Integer.valueOf(id));
	}

	@Override
	public List<AgentGroup> getAgentGroupList() {
		return mapper.getAgentGroupList();
	}
	
	@Override
	public List<ShopGroup> countShopGroup(){
		List<ShopGroup> list =  mapper.countShopGroup();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<ShopGroup> selShopGroups() {

		return mapper.selShopGroups();
	}

	@Override
	public List<ShopGroup> findShopGroup() {

		return mapper.findShopGroup();
	}
	
	@Override
	public void addShopGroup(ShopGroup group) {

		mapper.addShopGroup(group);
		
	}


	@Override
	public boolean updShopGroup(ShopGroup group) {

		if(mapper.updShopGroup(group)>0){
			return true;
		}
		 
		return false;
	}


	@Override
	public boolean delShopGroup(int id) {
		
		if(mapper.delShopGroup(id)>0){
			return true;
		}

		return false;
	}




	


	@Override
	public ShopGroup getShopGroup(int id) {
		return mapper.getShopGroup(id);
	}


	@Override
	public List<ShopGroup> queryShopById(String id) {
		return mapper.queryShopById(Integer.valueOf(id));
	}
	

	@Override
	public List<ShopGroup> getShopGroupList() {
		return mapper.getShopGroupList();
	}
	
	
	
	//user
	@Override
	public List<UserGroup> countGroupId(){
		List<UserGroup> list =  mapper.countGroupId();
		System.out.println(list);
		return list;
	}
	
	@Override
	public List<UserGroup> findUserGroup(){
		List<UserGroup> list = mapper.findUserGroup();
		return list;
	}
	
	@Override
	public List<UserGroup> userGroupList() {
		

		List<UserGroup> list =mapper.userGroupList();

		return list;
	}

	@Override
	public void addUserGroup(UserGroup group) {

		mapper.addUserGroup(group);
		
		
	}

	@Override
	public boolean updUserGroup(UserGroup group) {

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
	public UserGroup getUserGroup(int id) {
		
		return mapper.getUserGroup(id);
	}

	
	//2.8.1.dt_role_operator_type（角色后台管理操作员类别信息表）
	@Override
	public Page<RoleOperatorType> queryRoleOperatorType(RoleOperatorTypeVo vo) {
		Page<RoleOperatorType> page =new Page<RoleOperatorType>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countRoleOperatorType(vo);
		List<RoleOperatorType> list =mapper.queryRoleOperatorType(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addRoleOperatorType(RoleOperatorType og) {
		mapper.addRoleOperatorType(og);
	}
	
	@Override
	public RoleOperatorType getRoleOperatorType(int id) {
		return mapper.getRoleOperatorType(id);
	}
	
	@Override
	public boolean updRoleOperatorType(RoleOperatorType og) {
		if(mapper.updRoleOperatorType(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delRoleOperatorType(int id) {
		if(mapper.delRoleOperatorType(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.8.2.dt_role_operator_type_value（角色后台管理操作员类别对应权限值信息表）
	@Override
	public Page<RoleOperatorTypeValue> queryRoleOperatorTypeValue(RoleOperatorTypeValueVo vo) {
		Page<RoleOperatorTypeValue> page =new Page<RoleOperatorTypeValue>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countRoleOperatorTypeValue(vo);
		List<RoleOperatorTypeValue> list =mapper.queryRoleOperatorTypeValue(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addRoleOperatorTypeValue(RoleOperatorTypeValue og) {
		mapper.addRoleOperatorTypeValue(og);
	}
	
	@Override
	public RoleOperatorTypeValue getRoleOperatorTypeValue(int id) {
		return mapper.getRoleOperatorTypeValue(id);
	}
	
	@Override
	public boolean updRoleOperatorTypeValue(RoleOperatorTypeValue og) {
		if(mapper.updRoleOperatorTypeValue(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delRoleOperatorTypeValue(int id) {
		if(mapper.delRoleOperatorTypeValue(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.8.4.dt_user_role（用户角色信息表）
	@Override
	public Page<UserRole> queryUserRole(UserRoleVo vo) {
		Page<UserRole> page =new Page<UserRole>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRole(vo);
		List<UserRole> list =mapper.queryUserRole(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRole(UserRole og) {
		mapper.addUserRole(og);
	}
	
	@Override
	public UserRole getUserRole(int id) {
		return mapper.getUserRole(id);
	}
	
	@Override
	public boolean updUserRole(UserRole og) {
		if(mapper.updUserRole(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRole(int id) {
		if(mapper.delUserRole(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.8.3.dt_role_operator_navigation（角色后台管理总菜单信息表）
	@Override
	public Page<RoleOperatorNavigation> queryRoleOperatorNavigation(RoleOperatorNavigationVo vo) {
		Page<RoleOperatorNavigation> page =new Page<RoleOperatorNavigation>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countRoleOperatorNavigation(vo);
		List<RoleOperatorNavigation> list =mapper.queryRoleOperatorNavigation(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addRoleOperatorNavigation(RoleOperatorNavigation og) {
		mapper.addRoleOperatorNavigation(og);
	}
	
	@Override
	public RoleOperatorNavigation getRoleOperatorNavigation(int id) {
		return mapper.getRoleOperatorNavigation(id);
	}
	
	@Override
	public boolean updRoleOperatorNavigation(RoleOperatorNavigation og) {
		if(mapper.updRoleOperatorNavigation(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delRoleOperatorNavigation(int id) {
		if(mapper.delRoleOperatorNavigation(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	@Override
	public Page<UserRoleBusiness> queryUserRoleBusiness(UserRoleBusinessVo vo) {
		Page<UserRoleBusiness> page =new Page<UserRoleBusiness>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleBusiness(vo);
		List<UserRoleBusiness> list =mapper.queryUserRoleBusiness(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleBusiness(UserRoleBusiness og) {
		mapper.addUserRoleBusiness(og);
	}
	
	@Override
	public UserRoleBusiness getUserRoleBusiness(int id) {
		return mapper.getUserRoleBusiness(id);
	}
	
	@Override
	public boolean updUserRoleBusiness(UserRoleBusiness og) {
		if(mapper.updUserRoleBusiness(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleBusiness(int id) {
		if(mapper.delUserRoleBusiness(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_user_role_medal(用户角色荣誉勋章信息表)
	@Override
	public Page<UserRoleMedal> queryUserRoleMedal(UserRoleMedalVo vo) {
		Page<UserRoleMedal> page =new Page<UserRoleMedal>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleMedal(vo);
		List<UserRoleMedal> list =mapper.queryUserRoleMedal(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleMedal(UserRoleMedal og) {
		mapper.addUserRoleMedal(og);
	}
	
	@Override
	public UserRoleMedal getUserRoleMedal(int id) {
		return mapper.getUserRoleMedal(id);
	}
	
	@Override
	public boolean updUserRoleMedal(UserRoleMedal og) {
		if(mapper.updUserRoleMedal(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleMedal(int id) {
		if(mapper.delUserRoleMedal(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_user_role_group(用户角色分组信息表)
	@Override
	public Page<UserRoleGroup> queryUserRoleGroup(UserRoleGroupVo vo) {
		Page<UserRoleGroup> page =new Page<UserRoleGroup>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleGroup(vo);
		List<UserRoleGroup> list =mapper.queryUserRoleGroup(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleGroup(UserRoleGroup og) {
		mapper.addUserRoleGroup(og);
	}
	
	@Override
	public UserRoleGroup getUserRoleGroup(int id) {
		return mapper.getUserRoleGroup(id);
	}
	
	@Override
	public boolean updUserRoleGroup(UserRoleGroup og) {
		if(mapper.updUserRoleGroup(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleGroup(int id) {
		if(mapper.delUserRoleGroup(id)>0){
			return true;
		}
		return false;
	}
	
	
	
}
