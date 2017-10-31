package com.manji.backstage.mapper.other;

import java.util.List;

import javax.annotation.Resource;

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

@Resource
public interface OtherMapper {

	//2.3.6.dt_user_code(用户随机码信息表)
	
	List<UserCode> queryUserCode(UserCodeVo vo);

	int countUserCode(UserCodeVo vo);

	UserCode getUserCode(int id);

	int updUserCode(UserCode art);

	void addUserCode(UserCode art);

	int delUserCode(int id);
	
	//2.3.33.dt_user_fixed (买家批量充值记录表)
	
	List<UserFixed> queryUserFixed(UserFixedVo vo);
	
	int countUserFixed(UserFixedVo vo);
	
	UserFixed getUserFixed(long id);
	
	int updUserFixed(UserFixed art);
	
	void addUserFixed(UserFixed art);
	
	int delUserFixed(long id);
	
	//2.3.3.dt_user_from_value（用户标识申请信息表）
	
	List<UserFromValue> queryUserFromValue(UserFromValueVo vo);
	
	int countUserFromValue(UserFromValueVo vo);
	
	UserFromValue getUserFromValue(int id);
	
	int updUserFromValue(UserFromValue art);
	
	void addUserFromValue(UserFromValue art);
	
	int delUserFromValue(int id);
	
	//2.3.5.dt_user_group_price（用户分组商品价格信息表）
	
	List<UserGroupPrice> queryUserGroupPrice(UserGroupPriceVo vo);
	
	int countUserGroupPrice(UserGroupPriceVo vo);
	
	UserGroupPrice getUserGroupPrice(int id);
	
	int updUserGroupPrice(UserGroupPrice art);
	
	void addUserGroupPrice(UserGroupPrice art);
	
	int delUserGroupPrice(int id);
	
	//2.3.31.dt_user_often_use (用户经常使用信息表)
	
	List<UserOftenUse> queryUserOftenUse(UserOftenUseVo vo);
	
	int countUserOftenUse(UserOftenUseVo vo);
	
	UserOftenUse getUserOftenUse(int id);
	
	int updUserOftenUse(UserOftenUse art);
	
	void addUserOftenUse(UserOftenUse art);
	
	int delUserOftenUse(int id);
	
	//2.8.7.dt_user_role_buyerinfo（用户买家信息表）
	
	List<UserRoleBuyerinfo> queryUserRoleBuyerinfo(UserRoleBuyerinfoVo vo);
	
	int countUserRoleBuyerinfo(UserRoleBuyerinfoVo vo);
	
	UserRoleBuyerinfo getUserRoleBuyerinfo(int id);
	
	int updUserRoleBuyerinfo(UserRoleBuyerinfo art);
	
	void addUserRoleBuyerinfo(UserRoleBuyerinfo art);
	
	int delUserRoleBuyerinfo(int id);
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	List<UserWechat> queryUserWechat(UserWechatVo vo);
	
	int countUserWechat(UserWechatVo vo);
	
	UserWechat getUserWechat(int id);
	
	int updUserWechat(UserWechat art);
	
	void addUserWechat(UserWechat art);
	
	int delUserWechat(int id);
	
	//4.17.1.dt_order_ queue (账户结算主表)
	
	List<OrderQueue> queryOrderQueue(OrderQueueVo vo);
	
	int countOrderQueue(OrderQueueVo vo);
	
	OrderQueue getOrderQueue(int id);
	
	int updOrderQueue(OrderQueue art);
	
	void addOrderQueue(OrderQueue art);
	
	int delOrderQueue(int id);
	
	//4.16.1.dt_order_log(账户结算主表)
	
	List<OrderLog> queryOrderLog(OrderLogVo vo);
	
	int countOrderLog(OrderLogVo vo);
	
	OrderLog getOrderLog(int id);
	
	int updOrderLog(OrderLog art);
	
	void addOrderLog(OrderLog art);
	
	int delOrderLog(int id);

	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
	List<UserOauth> queryUserOauth(UserOauthVo vo);
	
	int countUserOauth(UserOauthVo vo);
	
	UserOauth getUserOauth(int id);
	
	int updUserOauth(UserOauth art);
	
	void addUserOauth(UserOauth art);
	
	int delUserOauth(int id);
	
	
	//2.3.20.dt_user_notice_read（用户动态消息读取记录表）
	
	List<UserNoticeRead> queryUserNoticeRead(UserNoticeReadVo vo);
	
	int countUserNoticeRead(UserNoticeReadVo vo);
	
	UserNoticeRead getUserNoticeRead(int id);
	
	int updUserNoticeRead(UserNoticeRead art);
	
	void addUserNoticeRead(UserNoticeRead art);
	
	int delUserNoticeRead(int id);
	
	//4.15.1.dt_user_balance_log(账户结算主表)
	
	List<UserBalanceLog> queryUserBalanceLog(UserBalanceLogVo vo);
	
	int countUserBalanceLog(UserBalanceLogVo vo);
	
	UserBalanceLog getUserBalanceLog(int id);
	
	int updUserBalanceLog(UserBalanceLog art);
	
	void addUserBalanceLog(UserBalanceLog art);
	
	int delUserBalanceLog(int id);
	
	//4.16.2.	dt_user_balance_detail(账户结算子表)
	
	List<UserBalanceDetail> queryUserBalanceDetail(UserBalanceDetailVo vo);
	
	int countUserBalanceDetail(UserBalanceDetailVo vo);
	
	UserBalanceDetail getUserBalanceDetail(int id);
	
	int updUserBalanceDetail(UserBalanceDetail art);
	
	void addUserBalanceDetail(UserBalanceDetail art);
	
	int delUserBalanceDetail(int id);
	
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
	
	//2.8.18.dt_user_role_business（用户商务角色信息表）
	
	List<UserRoleBusiness> queryUserRoleBusiness(UserRoleBusinessVo vo);
	
	int countUserRoleBusiness(UserRoleBusinessVo vo);
	
	UserRoleBusiness getUserRoleBusiness(int id);
	
	int updUserRoleBusiness(UserRoleBusiness art);
	
	void addUserRoleBusiness(UserRoleBusiness art);
	
	int delUserRoleBusiness(int id);
	
	
	
}
