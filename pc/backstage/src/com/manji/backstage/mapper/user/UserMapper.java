package com.manji.backstage.mapper.user;

import java.util.List;

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

public interface UserMapper {

	

	
	
	int countUser(UserVo vo);
	
	List<User> queryUser(UserVo vo);

	void addUser(User user);

	User getUser(int id);

	int updUser(User user);

	int delUser(int id);
	
	
	int countRecAddr(RecAddrVo vo);

	List<RecAddr> queryRecAddr(RecAddrVo vo);

	void addRecAddr(RecAddr ra);

	RecAddr getRecAddr(int id);

	int updRecAddr(RecAddr ra);

	int delRecAddr(int id);
	
	
	
	int countUserFavorites(UserFavoritesVo vo);
	
	List<UserFavorites> queryUserFavorites(UserFavoritesVo vo);
	
	void addUserFavorites(UserFavorites ra);
	
	UserFavorites getUserFavorites(int id);
	
	int updUserFavorites(UserFavorites ra);
	
	int delUserFavorites(int id);

	
	
	
	int countPersonInfo(PersonInfoVo vo);

	List<PersonInfo> queryPersonInfo(PersonInfoVo vo);

	void addPersonInfo(PersonInfo pi);

	PersonInfo getPersonInfo(int id);

	int updPersonInfo(PersonInfo pi);

	int delPersonInfo(int id);

	
	
	int countCompanyInfo(CompanyInfoVo vo);

	List<CompanyInfo> queryCompanyInfo(CompanyInfoVo vo);

	void addCompanyInfo(CompanyInfo ci);

	CompanyInfo getCompanyInfo(int id);

	int updCompanyInfo(CompanyInfo ci);

	int delCompanyInfo(int id);

	
	
	int countBankType(BankTypeVo vo);

	List<BankType> queryBankType(BankTypeVo vo);

	void addBankType(BankType bt);

	BankType getBankType(int id);

	int updBankType(BankType bt);

	int delBankType(int id);

	

	
}
