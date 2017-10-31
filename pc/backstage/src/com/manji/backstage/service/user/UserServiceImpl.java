package com.manji.backstage.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.user.UserMapper;
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
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	
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

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
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

	@Override
	public Page<BankType> queryBankType(BankTypeVo vo) {
		Page<BankType> page =new Page<BankType>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countBankType(vo);
		List<BankType> list =mapper.queryBankType(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addBankType(BankType bt) {
		mapper.addBankType(bt);
	}

	@Override
	public BankType getBankType(int id) {
		
		return mapper.getBankType(id);
	}

	@Override
	public boolean updBankType(BankType bt) {
		if(mapper.updBankType(bt)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delBankType(int id) {
		if(mapper.delBankType(id)>0){
			return true;
		}
		return false;
	}


}
