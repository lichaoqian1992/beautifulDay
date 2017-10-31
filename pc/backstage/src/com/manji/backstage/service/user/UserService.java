package com.manji.backstage.service.user;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.user.BankType;
import com.manji.backstage.model.user.CompanyInfo;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.UserFavorites;
import com.manji.backstage.vo.user.BankTypeVo;
import com.manji.backstage.vo.user.CompanyInfoVo;
import com.manji.backstage.vo.user.PersonInfoVo;
import com.manji.backstage.vo.user.RecAddrVo;
import com.manji.backstage.vo.user.UserFavoritesVo;
import com.manji.backstage.vo.user.UserVo;

public interface UserService {

	
	
	
	
	Page<User> queryUser(UserVo vo);

	void addUser(User user);

	User getUser(int id);

	boolean updUser(User user);

	boolean delUser(int id);
	

	
	
	
	
	Page<RecAddr> queryRecAddr(RecAddrVo vo);

	void addRecAddr(RecAddr ra);

	RecAddr getRecAddr(int id);

	boolean updRecAddr(RecAddr ra);

	boolean delRecAddr(int id);

	
	
	Page<UserFavorites> queryUserFavorites(UserFavoritesVo vo);

	void addUserFavorites(UserFavorites ra);

	UserFavorites getUserFavorites(int id);

	boolean updUserFavorites(UserFavorites ra);

	boolean delUserFavorites(int id);
	
	
	
	Page<PersonInfo> queryPersonInfo(PersonInfoVo vo);

	void addPersonInfo(PersonInfo pi);

	PersonInfo getPersonInfo(int id);

	boolean updPersonInfo(PersonInfo pi);

	boolean delPersonInfo(int id);

	
	
	Page<CompanyInfo> queryCompanyInfo(CompanyInfoVo vo);

	void addCompanyInfo(CompanyInfo ci);

	CompanyInfo getCompanyInfo(int id);

	boolean updCompanyInfo(CompanyInfo ci);

	boolean delCompanyInfo(int id);

	
	
	
	
	Page<BankType> queryBankType(BankTypeVo vo);

	void addBankType(BankType bt);

	BankType getBankType(int id);

	boolean updBankType(BankType bt);

	boolean delBankType(int id);


}
