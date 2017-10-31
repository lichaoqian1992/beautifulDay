package com.manji.backstage.service.other;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.other.OtherMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.other.OrderLog;
import com.manji.backstage.model.other.OrderQueue;
import com.manji.backstage.model.other.RoleOperatorNavigation;
import com.manji.backstage.model.other.RoleOperatorType;
import com.manji.backstage.model.other.RoleOperatorTypeValue;
import com.manji.backstage.model.other.UserBalanceDetail;
import com.manji.backstage.model.other.UserBalanceLog;
import com.manji.backstage.model.other.UserCode;
import com.manji.backstage.model.other.UserFixed;
import com.manji.backstage.model.other.UserFromValue;
import com.manji.backstage.model.other.UserGroupPrice;
import com.manji.backstage.model.other.UserNoticeRead;
import com.manji.backstage.model.other.UserOauth;
import com.manji.backstage.model.other.UserOftenUse;
import com.manji.backstage.model.other.UserRole;
import com.manji.backstage.model.other.UserRoleBusiness;
import com.manji.backstage.model.other.UserRoleBuyerinfo;
import com.manji.backstage.model.other.UserWechat;
import com.manji.backstage.vo.other.OrderLogVo;
import com.manji.backstage.vo.other.OrderQueueVo;
import com.manji.backstage.vo.other.RoleOperatorNavigationVo;
import com.manji.backstage.vo.other.RoleOperatorTypeValueVo;
import com.manji.backstage.vo.other.RoleOperatorTypeVo;
import com.manji.backstage.vo.other.UserBalanceDetailVo;
import com.manji.backstage.vo.other.UserBalanceLogVo;
import com.manji.backstage.vo.other.UserCodeVo;
import com.manji.backstage.vo.other.UserFixedVo;
import com.manji.backstage.vo.other.UserFromValueVo;
import com.manji.backstage.vo.other.UserGroupPriceVo;
import com.manji.backstage.vo.other.UserNoticeReadVo;
import com.manji.backstage.vo.other.UserOauthVo;
import com.manji.backstage.vo.other.UserOftenUseVo;
import com.manji.backstage.vo.other.UserRoleBusinessVo;
import com.manji.backstage.vo.other.UserRoleBuyerinfoVo;
import com.manji.backstage.vo.other.UserRoleVo;
import com.manji.backstage.vo.other.UserWechatVo;

@Service
public class OtherServiceImpl implements OtherService{

	@Autowired
	private OtherMapper mapper;
	
	//2.3.6.dt_user_code(用户随机码信息表)
	@Override
	public Page<UserCode> queryUserCode(UserCodeVo vo) {
		Page<UserCode> page =new Page<UserCode>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserCode(vo);
		List<UserCode> list =mapper.queryUserCode(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addUserCode(UserCode og) {
		mapper.addUserCode(og);
	}

	@Override
	public UserCode getUserCode(int id) {
		return mapper.getUserCode(id);
	}

	@Override
	public boolean updUserCode(UserCode og) {
		if(mapper.updUserCode(og)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserCode(int id) {
		if(mapper.delUserCode(id)>0){
			return true;
		}
		return false;
	}

	
	
	//2.3.33.dt_user_fixed (买家批量充值记录表)
	@Override
	public Page<UserFixed> queryUserFixed(UserFixedVo vo) {
		Page<UserFixed> page =new Page<UserFixed>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserFixed(vo);
		List<UserFixed> list =mapper.queryUserFixed(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserFixed(UserFixed og) {
		mapper.addUserFixed(og);
	}
	
	@Override
	public UserFixed getUserFixed(long id) {
		return mapper.getUserFixed(id);
	}
	
	@Override
	public boolean updUserFixed(UserFixed og) {
		if(mapper.updUserFixed(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserFixed(long id) {
		if(mapper.delUserFixed(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.3.3.dt_user_from_value（用户标识申请信息表）
	@Override
	public Page<UserFromValue> queryUserFromValue(UserFromValueVo vo) {
		Page<UserFromValue> page =new Page<UserFromValue>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserFromValue(vo);
		List<UserFromValue> list =mapper.queryUserFromValue(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserFromValue(UserFromValue og) {
		mapper.addUserFromValue(og);
	}
	
	@Override
	public UserFromValue getUserFromValue(int id) {
		return mapper.getUserFromValue(id);
	}
	
	@Override
	public boolean updUserFromValue(UserFromValue og) {
		if(mapper.updUserFromValue(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserFromValue(int id) {
		if(mapper.delUserFromValue(id)>0){
			return true;
		}
		return false;
	}
	
	//2.3.5.dt_user_group_price（用户分组商品价格信息表）
	@Override
	public Page<UserGroupPrice> queryUserGroupPrice(UserGroupPriceVo vo) {
		Page<UserGroupPrice> page =new Page<UserGroupPrice>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserGroupPrice(vo);
		List<UserGroupPrice> list =mapper.queryUserGroupPrice(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserGroupPrice(UserGroupPrice og) {
		mapper.addUserGroupPrice(og);
	}
	
	@Override
	public UserGroupPrice getUserGroupPrice(int id) {
		return mapper.getUserGroupPrice(id);
	}
	
	@Override
	public boolean updUserGroupPrice(UserGroupPrice og) {
		if(mapper.updUserGroupPrice(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserGroupPrice(int id) {
		if(mapper.delUserGroupPrice(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.3.31.dt_user_often_use (用户经常使用信息表)
	@Override
	public Page<UserOftenUse> queryUserOftenUse(UserOftenUseVo vo) {
		Page<UserOftenUse> page =new Page<UserOftenUse>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserOftenUse(vo);
		List<UserOftenUse> list =mapper.queryUserOftenUse(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserOftenUse(UserOftenUse og) {
		mapper.addUserOftenUse(og);
	}
	
	@Override
	public UserOftenUse getUserOftenUse(int id) {
		return mapper.getUserOftenUse(id);
	}
	
	@Override
	public boolean updUserOftenUse(UserOftenUse og) {
		if(mapper.updUserOftenUse(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserOftenUse(int id) {
		if(mapper.delUserOftenUse(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.8.7.dt_user_role_buyerinfo（用户买家信息表）
	@Override
	public Page<UserRoleBuyerinfo> queryUserRoleBuyerinfo(UserRoleBuyerinfoVo vo) {
		Page<UserRoleBuyerinfo> page =new Page<UserRoleBuyerinfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleBuyerinfo(vo);
		List<UserRoleBuyerinfo> list =mapper.queryUserRoleBuyerinfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleBuyerinfo(UserRoleBuyerinfo og) {
		mapper.addUserRoleBuyerinfo(og);
	}
	
	@Override
	public UserRoleBuyerinfo getUserRoleBuyerinfo(int id) {
		return mapper.getUserRoleBuyerinfo(id);
	}
	
	@Override
	public boolean updUserRoleBuyerinfo(UserRoleBuyerinfo og) {
		if(mapper.updUserRoleBuyerinfo(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleBuyerinfo(int id) {
		if(mapper.delUserRoleBuyerinfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	@Override
	public Page<UserWechat> queryUserWechat(UserWechatVo vo) {
		Page<UserWechat> page =new Page<UserWechat>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserWechat(vo);
		List<UserWechat> list =mapper.queryUserWechat(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserWechat(UserWechat og) {
		mapper.addUserWechat(og);
	}
	
	@Override
	public UserWechat getUserWechat(int id) {
		return mapper.getUserWechat(id);
	}
	
	@Override
	public boolean updUserWechat(UserWechat og) {
		if(mapper.updUserWechat(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserWechat(int id) {
		if(mapper.delUserWechat(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//4.17.1.dt_order_ queue (账户结算主表)
	@Override
	public Page<OrderQueue> queryOrderQueue(OrderQueueVo vo) {
		Page<OrderQueue> page =new Page<OrderQueue>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderQueue(vo);
		List<OrderQueue> list =mapper.queryOrderQueue(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderQueue(OrderQueue og) {
		mapper.addOrderQueue(og);
	}
	
	@Override
	public OrderQueue getOrderQueue(int id) {
		return mapper.getOrderQueue(id);
	}
	
	@Override
	public boolean updOrderQueue(OrderQueue og) {
		if(mapper.updOrderQueue(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderQueue(int id) {
		if(mapper.delOrderQueue(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//4.16.1.dt_order_log(账户结算主表)
	@Override
	public Page<OrderLog> queryOrderLog(OrderLogVo vo) {
		Page<OrderLog> page =new Page<OrderLog>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderLog(vo);
		List<OrderLog> list =mapper.queryOrderLog(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderLog(OrderLog og) {
		mapper.addOrderLog(og);
	}
	
	@Override
	public OrderLog getOrderLog(int id) {
		return mapper.getOrderLog(id);
	}
	
	@Override
	public boolean updOrderLog(OrderLog og) {
		if(mapper.updOrderLog(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderLog(int id) {
		if(mapper.delOrderLog(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	@Override
	public Page<UserOauth> queryUserOauth(UserOauthVo vo) {
		Page<UserOauth> page =new Page<UserOauth>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserOauth(vo);
		List<UserOauth> list =mapper.queryUserOauth(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserOauth(UserOauth og) {
		mapper.addUserOauth(og);
	}
	
	@Override
	public UserOauth getUserOauth(int id) {
		return mapper.getUserOauth(id);
	}
	
	@Override
	public boolean updUserOauth(UserOauth og) {
		if(mapper.updUserOauth(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserOauth(int id) {
		if(mapper.delUserOauth(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	@Override
	public Page<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo) {
		Page<UserNoticeRead> page =new Page<UserNoticeRead>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserNoticeRead(vo);
		List<UserNoticeRead> list =mapper.queryUserNoticeRead(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserNoticeRead(UserNoticeRead og) {
		mapper.addUserNoticeRead(og);
	}
	
	@Override
	public UserNoticeRead getUserNoticeRead(int id) {
		return mapper.getUserNoticeRead(id);
	}
	
	@Override
	public boolean updUserNoticeRead(UserNoticeRead og) {
		if(mapper.updUserNoticeRead(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserNoticeRead(int id) {
		if(mapper.delUserNoticeRead(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//4.16.2.dt_user_balance_detail(账户结算子表)
	@Override
	public Page<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo) {
		Page<UserBalanceDetail> page =new Page<UserBalanceDetail>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserBalanceDetail(vo);
		List<UserBalanceDetail> list =mapper.queryUserBalanceDetail(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserBalanceDetail(UserBalanceDetail og) {
		mapper.addUserBalanceDetail(og);
	}
	
	@Override
	public UserBalanceDetail getUserBalanceDetail(int id) {
		return mapper.getUserBalanceDetail(id);
	}
	
	@Override
	public boolean updUserBalanceDetail(UserBalanceDetail og) {
		if(mapper.updUserBalanceDetail(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserBalanceDetail(int id) {
		if(mapper.delUserBalanceDetail(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	@Override
	public Page<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo) {
		Page<UserBalanceLog> page =new Page<UserBalanceLog>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserBalanceLog(vo);
		List<UserBalanceLog> list =mapper.queryUserBalanceLog(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserBalanceLog(UserBalanceLog og) {
		mapper.addUserBalanceLog(og);
	}
	
	@Override
	public UserBalanceLog getUserBalanceLog(int id) {
		return mapper.getUserBalanceLog(id);
	}
	
	@Override
	public boolean updUserBalanceLog(UserBalanceLog og) {
		if(mapper.updUserBalanceLog(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserBalanceLog(int id) {
		if(mapper.delUserBalanceLog(id)>0){
			return true;
		}
		return false;
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
	
	
}
