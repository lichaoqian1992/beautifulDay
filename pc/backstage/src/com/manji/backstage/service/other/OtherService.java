package com.manji.backstage.service.other;

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

public interface OtherService {
	
	//2.3.6.dt_user_code(用户随机码信息表)
	
	Page<UserCode> queryUserCode(UserCodeVo vo);
	
	UserCode getUserCode(int id);
	
	boolean updUserCode(UserCode si);
	
	void addUserCode(UserCode si);
	
	boolean delUserCode(int id);
	
	//2.3.33.dt_user_fixed (买家批量充值记录表)
	
	Page<UserFixed> queryUserFixed(UserFixedVo vo);

	UserFixed getUserFixed(long id);

	boolean updUserFixed(UserFixed si);
	
	void addUserFixed(UserFixed si);
	
	boolean delUserFixed(long id);
	
	//2.3.3.dt_user_from_value（用户标识申请信息表）

	Page<UserFromValue> queryUserFromValue(UserFromValueVo vo);
	
	UserFromValue getUserFromValue(int id);
	
	boolean updUserFromValue(UserFromValue si);
	
	void addUserFromValue(UserFromValue si);
	
	boolean delUserFromValue(int id);
	
	//2.3.5.dt_user_group_price（用户分组商品价格信息表）
	
	Page<UserGroupPrice> queryUserGroupPrice(UserGroupPriceVo vo);
	
	UserGroupPrice getUserGroupPrice(int id);
	
	boolean updUserGroupPrice(UserGroupPrice si);
	
	void addUserGroupPrice(UserGroupPrice si);
	
	boolean delUserGroupPrice(int id);
	
	//2.3.31.dt_user_often_use (用户经常使用信息表)
	
	Page<UserOftenUse> queryUserOftenUse(UserOftenUseVo vo);
	
	UserOftenUse getUserOftenUse(int id);
	
	boolean updUserOftenUse(UserOftenUse si);
	
	void addUserOftenUse(UserOftenUse si);
	
	boolean delUserOftenUse(int id);
	
	//2.8.7.dt_user_role_buyerinfo（用户买家信息表）
	
	Page<UserRoleBuyerinfo> queryUserRoleBuyerinfo(UserRoleBuyerinfoVo vo);
	
	UserRoleBuyerinfo getUserRoleBuyerinfo(int id);
	
	boolean updUserRoleBuyerinfo(UserRoleBuyerinfo si);
	
	void addUserRoleBuyerinfo(UserRoleBuyerinfo si);
	
	boolean delUserRoleBuyerinfo(int id);
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	Page<UserWechat> queryUserWechat(UserWechatVo vo);
	
	UserWechat getUserWechat(int id);
	
	boolean updUserWechat(UserWechat si);
	
	void addUserWechat(UserWechat si);
	
	boolean delUserWechat(int id);
	
	//4.17.1.dt_order_queue (账户结算主表)
	
	Page<OrderQueue> queryOrderQueue(OrderQueueVo vo);
	
	OrderQueue getOrderQueue(int id);
	
	boolean updOrderQueue(OrderQueue si);
	
	void addOrderQueue(OrderQueue si);
	
	boolean delOrderQueue(int id);
	
	//4.16.1.dt_order_log(账户结算主表)
	
	Page<OrderLog> queryOrderLog(OrderLogVo vo);
	
	OrderLog getOrderLog(int id);
	
	boolean updOrderLog(OrderLog si);
	
	void addOrderLog(OrderLog si);
	
	boolean delOrderLog(int id);
	
	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
	Page<UserOauth> queryUserOauth(UserOauthVo vo);
	
	UserOauth getUserOauth(int id);
	
	boolean updUserOauth(UserOauth si);
	
	void addUserOauth(UserOauth si);
	
	boolean delUserOauth(int id);
	
	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	Page<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo);
	
	UserNoticeRead getUserNoticeRead(int id);
	
	boolean updUserNoticeRead(UserNoticeRead si);
	
	void addUserNoticeRead(UserNoticeRead si);
	
	boolean delUserNoticeRead(int id);
	
	//4.16.2.dt_user_balance_detail(账户结算子表)
	
	Page<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo);
	
	UserBalanceDetail getUserBalanceDetail(int id);
	
	boolean updUserBalanceDetail(UserBalanceDetail si);
	
	void addUserBalanceDetail(UserBalanceDetail si);
	
	boolean delUserBalanceDetail(int id);
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	
	Page<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo);
	
	UserBalanceLog getUserBalanceLog(int id);
	
	boolean updUserBalanceLog(UserBalanceLog si);
	
	void addUserBalanceLog(UserBalanceLog si);
	
	boolean delUserBalanceLog(int id);
	
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
	
	
}
