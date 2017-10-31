package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.shop.BuyerInfo;
import com.manji.backstage.model.user.UserBankType;
import com.manji.backstage.model.user.UserCode;
import com.manji.backstage.model.user.CompanyInfo;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserFavorites;
import com.manji.backstage.model.user.UserFixed;
import com.manji.backstage.model.user.UserFromValue;
import com.manji.backstage.model.user.UserGroupPrice;
import com.manji.backstage.model.user.UserOauth;
import com.manji.backstage.model.user.UserOftenUse;
import com.manji.backstage.model.user.UserRoleBuyerinfo;
import com.manji.backstage.model.user.UserWechat;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.vo.shop.BuyerInfoVo;
import com.manji.backstage.vo.user.UserBankTypeVo;
import com.manji.backstage.vo.user.UserCodeVo;
import com.manji.backstage.vo.user.CompanyInfoVo;
import com.manji.backstage.vo.user.PersonInfoVo;
import com.manji.backstage.vo.user.RecAddrVo;
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
@Resource
public interface UserMapper {

	
	
	
	int countVerify(VerifyVo vo);

	List<Verify> queryVerify(VerifyVo vo);

	void addVerify(Verify verify);

	Verify getVerify(int id);

	int updVerify(Verify verify);

	int delVerify(int id);
	
	
//	dt_users	用户信息表

	int countUser(UserVo vo);
	
	List<User> queryUser(UserVo vo);

	void addUser(User user);

	User getUser(int id);

	int updUser(User user);

	int delUser(int id);
	
//	dt_user_addr_book	用户收获地址新标

	int countRecAddr(RecAddrVo vo);

	List<RecAddr> queryRecAddr(RecAddrVo vo);

	void addRecAddr(RecAddr ra);

	RecAddr getRecAddr(int id);

	int updRecAddr(RecAddr ra);

	int delRecAddr(int id);
	
	
//	dt_user_favorites	用户收藏信息表

	int countUserFavorites(UserFavoritesVo vo);
	
	List<UserFavorites> queryUserFavorites(UserFavoritesVo vo);
	
	void addUserFavorites(UserFavorites ra);
	
	UserFavorites getUserFavorites(int id);
	
	int updUserFavorites(UserFavorites ra);
	
	int delUserFavorites(int id);

//	dt_user_personinfo	用户个人信息表

	
	
	int countPersonInfo(PersonInfoVo vo);

	List<PersonInfo> queryPersonInfo(PersonInfoVo vo);

	void addPersonInfo(PersonInfo pi);

	PersonInfo getPersonInfo(int id);

	int updPersonInfo(PersonInfo pi);

	int delPersonInfo(int id);

//	dt_user_companyinfo	用户个人信息表

	
	int countCompanyInfo(CompanyInfoVo vo);

	List<CompanyInfo> queryCompanyInfo(CompanyInfoVo vo);

	void addCompanyInfo(CompanyInfo ci);

	CompanyInfo getCompanyInfo(int id);

	int updCompanyInfo(CompanyInfo ci);

	int delCompanyInfo(int id);

//	dt_user_banktype	用户绑定银行卡

	
	int countUserBankType(UserBankTypeVo vo);

	List<UserBankType> queryUserBankType(UserBankTypeVo vo);

	void addUserBankType(UserBankType bt);

	UserBankType getUserBankType(int id);

	int updUserBankType(UserBankType bt);

	int delUserBankType(int id);

//	dt_user_role_buyerinfo	用户买家信息表
	int countBuyerInfo(BuyerInfoVo vo);

	List<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo);

	BuyerInfo getBuyerInfo(int id);

	int updBuyerInfo(BuyerInfo bi);

	void addBuyerInfo(BuyerInfo bi);

	int delBuyerInfo(int id);
	
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
	
	
	//2.3.2.dt_user_wechat（用户微信信息表）
	
	List<UserWechat> queryUserWechat(UserWechatVo vo);
	
	int countUserWechat(UserWechatVo vo);
	
	UserWechat getUserWechat(int id);
	
	int updUserWechat(UserWechat art);
	
	void addUserWechat(UserWechat art);
	
	int delUserWechat(int id);


	//2.3.18.dt_user_oauth（用户第三方登录授权信息表）
	
	List<UserOauth> queryUserOauth(UserOauthVo vo);
	
	int countUserOauth(UserOauthVo vo);
	
	UserOauth getUserOauth(int id);
	
	int updUserOauth(UserOauth art);
	
	void addUserOauth(UserOauth art);
	
	int delUserOauth(int id);
	
	
	
	
}
