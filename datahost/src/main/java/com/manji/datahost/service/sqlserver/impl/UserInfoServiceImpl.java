package com.manji.datahost.service.sqlserver.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.sqlserver.UserInfoMapper;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.user.AgentRegister;
import com.manji.datahost.model.sqlserver.user.ShopRegister;
import com.manji.datahost.model.sqlserver.user.UserAccount;
import com.manji.datahost.model.sqlserver.user.UserAddress;
import com.manji.datahost.model.sqlserver.user.UserInfo;
import com.manji.datahost.model.sqlserver.user.UserOrder;
import com.manji.datahost.model.sqlserver.user.UserRegister;
import com.manji.datahost.model.sqlserver.user.UserVoucher;
import com.manji.datahost.service.sqlserver.UserInfoService;
import com.manji.datahost.vo.PageVo;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper mapper;

	@Override
	public UserInfo getUserInfo(UserInfo ui) {

		return mapper.getUserInfo(ui);
	}

	@Override
	public List<Map<String, String>> getUserType(Integer user_id) {

		return mapper.getUserType(user_id);
	}

	@Override
	public Page<UserAccount> getUserAccount(PageVo vo) {

		Page<UserAccount> page = new Page<UserAccount>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countUserAccount(vo.getUser_id());
		List<UserAccount> list = mapper.getUserAccount(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserVoucher> getUserVoucher(PageVo vo) {
		Page<UserVoucher> page = new Page<UserVoucher>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countUserVoucher(vo.getUser_id());
		List<UserVoucher> list = mapper.getUserVoucher(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserOrder> getUserOrder(PageVo vo) {
		Page<UserOrder> page = new Page<UserOrder>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countUserOrder(vo.getUser_id());
		List<UserOrder> list = mapper.getUserOrder(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserAddress> getUserAddress(PageVo vo) {
		Page<UserAddress> page = new Page<UserAddress>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countUserAddress(vo.getUser_id());
		List<UserAddress> list = mapper.getUserAddress(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public List<UserRegister> getUserRegister(String user_name) {

		return mapper.getUserRegister(user_name);
	}

	@Override
	public List<ShopRegister> getShopRegister(String user_name) {
		
		return mapper.getShopRegister(user_name);
	}

	@Override
	public List<AgentRegister> getAgentRegister(String user_name) {
		
		return mapper.getAgentRegister(user_name);
	}

	@Override
	public List<Object> getUserNameByPersonName(String person_name) {

		return mapper.getUserNameByPersonName(person_name);
	}

	
	
}
