package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.UserMapper;
import com.manji.backstage.model.base.Page;
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
import com.manji.backstage.service.UserService;
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
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper mapper;
	



	////////////////////////////////////////////////////////////////////////////////
	@Override
	public Page<Verify> queryVerify(VerifyVo vo) {
		Page<Verify> page =new Page<Verify>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countVerify(vo);
		List<Verify> list =mapper.queryVerify(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addVerify(Verify verify) {
		mapper.addVerify(verify);
	}

	@Override
	public Verify getVerify(int id) {
		return mapper.getVerify(id);
	}

	@Override
	public boolean updVerify(Verify verify) {
		if(mapper.updVerify(verify)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delVerify(int id) {
		if(mapper.delVerify(id)>0){
			return true;
		}
		return false;
	}

//	dt_users	用户信息表


	@Override
	public Page<User> queryUser(UserVo vo) {
		Page<User> page =new Page<User>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUser(vo);
		List<User> list =mapper.queryUser(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addUser(User user) {
		mapper.addUser(user);
	}

	@Override
	public User getUser(int id) {
		return mapper.getUser(id);
	}

	@Override
	public boolean updUser(User user) {
		if(mapper.updUser(user)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUser(int id) {
		if(mapper.delUser(id)>0){
			return true;
		}
		return false;
	}
	
	
//	dt_user_addr_book	用户收获地址新标

	
	@Override
	public Page<RecAddr> queryRecAddr(RecAddrVo vo) {
		Page<RecAddr> page =new Page<RecAddr>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countRecAddr(vo);
		List<RecAddr> list =mapper.queryRecAddr(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addRecAddr(RecAddr ra) {
		mapper.addRecAddr(ra);
		
	}

	@Override
	public RecAddr getRecAddr(int id) {
		return mapper.getRecAddr(id);
	}

	@Override
	public boolean updRecAddr(RecAddr ra) {
		if(mapper.updRecAddr(ra)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delRecAddr(int id) {
		if(mapper.delRecAddr(id)>0){
			return true;
		}
		
		return false;
	}
	
	
//	dt_user_favorites	用户收藏信息表

	@Override
	public Page<UserFavorites> queryUserFavorites(UserFavoritesVo vo) {
		Page<UserFavorites> page =new Page<UserFavorites>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserFavorites(vo);
		List<UserFavorites> list =mapper.queryUserFavorites(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addUserFavorites(UserFavorites uf) {
		mapper.addUserFavorites(uf);
		
	}

	@Override
	public UserFavorites getUserFavorites(int id) {
		return mapper.getUserFavorites(id);
	}

	@Override
	public boolean updUserFavorites(UserFavorites uf) {
		if(mapper.updUserFavorites(uf)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserFavorites(int id) {
		if(mapper.delUserFavorites(id)>0){
			return true;
		}
		
		return false;
	}

	
//	dt_user_personinfo	用户个人信息表

	@Override
	public Page<PersonInfo> queryPersonInfo(PersonInfoVo vo) {
		Page<PersonInfo> page =new Page<PersonInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countPersonInfo(vo);
		List<PersonInfo> list =mapper.queryPersonInfo(vo);
		
		page.transform(count, list);
		return page;
		
	}

	@Override
	public void addPersonInfo(PersonInfo pi) {
		mapper.addPersonInfo(pi);
		
	}

	@Override
	public PersonInfo getPersonInfo(int id) {
		return mapper.getPersonInfo(id);
	}

	@Override
	public boolean updPersonInfo(PersonInfo pi) {
		if(mapper.updPersonInfo(pi)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delPersonInfo(int id) {
		if(mapper.delPersonInfo(id)>0){
			return true;
		}
		return false;
	}

	
//	dt_user_companyinfo	用户个人信息表

	
	@Override
	public Page<CompanyInfo> queryCompanyInfo(CompanyInfoVo vo) {
		Page<CompanyInfo> page =new Page<CompanyInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countCompanyInfo(vo);
		List<CompanyInfo> list =mapper.queryCompanyInfo(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addCompanyInfo(CompanyInfo ci) {
		mapper.addCompanyInfo(ci);
		
	}

	@Override
	public CompanyInfo getCompanyInfo(int id) {
		
		return mapper.getCompanyInfo(id);
	}

	@Override
	public boolean updCompanyInfo(CompanyInfo ci) {
		if(mapper.updCompanyInfo(ci)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delCompanyInfo(int id) {
		if(mapper.delCompanyInfo(id)>0){
			return true;
		}
		return false;
	}

	
//	dt_user_banktype	用户绑定银行卡

	
	@Override
	public Page<UserBankType> queryUserBankType(UserBankTypeVo vo) {
		Page<UserBankType> page =new Page<UserBankType>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserBankType(vo);
		List<UserBankType> list =mapper.queryUserBankType(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addUserBankType(UserBankType bt) {
		mapper.addUserBankType(bt);
	}

	@Override
	public UserBankType getUserBankType(int id) {
		
		return mapper.getUserBankType(id);
	}

	@Override
	public boolean updUserBankType(UserBankType bt) {
		if(mapper.updUserBankType(bt)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserBankType(int id) {
		if(mapper.delUserBankType(id)>0){
			return true;
		}
		return false;
	}

	

//	dt_user_role_buyerinfo	用户买家信息表
	@Override
	public Page<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo) {
		Page<BuyerInfo> page =new Page<BuyerInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countBuyerInfo(vo);
		List<BuyerInfo> list =mapper.queryBuyerInfo(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public BuyerInfo getBuyerInfo(int id) {
		
		return mapper.getBuyerInfo(id);
	}

	@Override
	public boolean updBuyerInfo(BuyerInfo bi) {

		if(mapper.updBuyerInfo(bi)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addBuyerInfo(BuyerInfo bi) {
		mapper.addBuyerInfo(bi);
	}

	@Override
	public boolean delBuyerInfo(int id) {
		if(mapper.delBuyerInfo(id)>0){
			return true;
		}
		return false;
	}


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
	
	
	
}
