package com.manji.backstage.service;


import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.order.OrderLog;
import com.manji.backstage.model.order.OrderQueue;
import com.manji.backstage.model.shop.BuyerInfo;
import com.manji.backstage.model.user.CompanyInfo;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserBankType;
import com.manji.backstage.model.user.UserCode;
import com.manji.backstage.model.user.UserFavorites;
import com.manji.backstage.model.user.UserFixed;
import com.manji.backstage.model.user.UserFromValue;
import com.manji.backstage.model.user.UserGroupPrice;
import com.manji.backstage.model.user.UserOauth;
import com.manji.backstage.model.user.UserOftenUse;
import com.manji.backstage.model.user.UserRoleBuyerinfo;
import com.manji.backstage.model.user.UserWechat;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.vo.order.OrderLogVo;
import com.manji.backstage.vo.order.OrderQueueVo;
import com.manji.backstage.vo.shop.BuyerInfoVo;
import com.manji.backstage.vo.user.CompanyInfoVo;
import com.manji.backstage.vo.user.PersonInfoVo;
import com.manji.backstage.vo.user.RecAddrVo;
import com.manji.backstage.vo.user.UserBankTypeVo;
import com.manji.backstage.vo.user.UserCodeVo;
import com.manji.backstage.vo.user.UserFavoritesVo;
import com.manji.backstage.vo.user.UserFixedVo;
import com.manji.backstage.vo.user.UserFromValueVo;
import com.manji.backstage.vo.user.UserGroupPriceVo;
import com.manji.backstage.vo.user.UserOauthVo;
import com.manji.backstage.vo.user.UserOftenUseVo;
import com.manji.backstage.vo.user.UserRoleBuyerinfoVo;
import com.manji.backstage.vo.user.UserVo;
import com.manji.backstage.vo.user.UserWechatVo;
import com.manji.backstage.vo.user.VerifyVo;

public interface UserService {

	

	
	
	
	Page<Verify> queryVerify(VerifyVo vo);

	void addVerify(Verify verify);

	Verify getVerify(int id);

	boolean updVerify(Verify verify);

	boolean delVerify(int id);
	
//	dt_users	用户信息表

	Page<User> queryUser(UserVo vo);

	void addUser(User user);

	User getUser(int id);

	boolean updUser(User user);

	boolean delUser(int id);
	
	
//	dt_user_addr_book	用户收获地址新标

	
	Page<RecAddr> queryRecAddr(RecAddrVo vo);

	void addRecAddr(RecAddr ra);

	RecAddr getRecAddr(int id);

	boolean updRecAddr(RecAddr ra);

	boolean delRecAddr(int id);

//	dt_user_favorites	用户收藏信息表

	
	Page<UserFavorites> queryUserFavorites(UserFavoritesVo vo);

	void addUserFavorites(UserFavorites ra);

	UserFavorites getUserFavorites(int id);

	boolean updUserFavorites(UserFavorites ra);

	boolean delUserFavorites(int id);
	
//	dt_user_personinfo	用户个人信息表

	
	Page<PersonInfo> queryPersonInfo(PersonInfoVo vo);

	void addPersonInfo(PersonInfo pi);

	PersonInfo getPersonInfo(int id);

	boolean updPersonInfo(PersonInfo pi);

	boolean delPersonInfo(int id);

//	dt_user_companyinfo	用户个人信息表

	
	Page<CompanyInfo> queryCompanyInfo(CompanyInfoVo vo);

	void addCompanyInfo(CompanyInfo ci);

	CompanyInfo getCompanyInfo(int id);

	boolean updCompanyInfo(CompanyInfo ci);

	boolean delCompanyInfo(int id);

	
	
//	dt_user_banktype	用户绑定银行卡

	
	Page<UserBankType> queryUserBankType(UserBankTypeVo vo);

	void addUserBankType(UserBankType bt);

	UserBankType getUserBankType(int id);

	boolean updUserBankType(UserBankType bt);

	boolean delUserBankType(int id);
	
	
//	dt_user_role_buyerinfo	用户买家信息表
	Page<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo);

	BuyerInfo getBuyerInfo(int id);

	boolean updBuyerInfo(BuyerInfo bi);

	void addBuyerInfo(BuyerInfo bi);

	boolean delBuyerInfo(int id);

	
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
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	Page<UserWechat> queryUserWechat(UserWechatVo vo);
	
	UserWechat getUserWechat(int id);
	
	boolean updUserWechat(UserWechat si);
	
	void addUserWechat(UserWechat si);
	
	boolean delUserWechat(int id);
	
	
	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
	Page<UserOauth> queryUserOauth(UserOauthVo vo);
	
	UserOauth getUserOauth(int id);
	
	boolean updUserOauth(UserOauth si);
	
	void addUserOauth(UserOauth si);
	
	boolean delUserOauth(int id);
	

	
}
