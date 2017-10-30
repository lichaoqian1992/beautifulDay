package com.manji.datahost.mapper.sqlserver;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.manji.datahost.model.sqlserver.user.AgentRegister;
import com.manji.datahost.model.sqlserver.user.ShopRegister;
import com.manji.datahost.model.sqlserver.user.UserAccount;
import com.manji.datahost.model.sqlserver.user.UserAddress;
import com.manji.datahost.model.sqlserver.user.UserInfo;
import com.manji.datahost.model.sqlserver.user.UserOrder;
import com.manji.datahost.model.sqlserver.user.UserRegister;
import com.manji.datahost.model.sqlserver.user.UserVoucher;
import com.manji.datahost.vo.PageVo;

@Mapper
public interface UserInfoMapper {
	
	//用户信息
	UserInfo getUserInfo(UserInfo ui);

	//用户角色类型
	List<Map<String,String>> getUserType(Integer user_id);
	
	//资金流水
	List<UserAccount> getUserAccount(PageVo vo);
	int countUserAccount(Integer user_id);
	
	//满意券流水
	List<UserVoucher> getUserVoucher(PageVo vo);
	int countUserVoucher(Integer user_id);
	
	//订单
	List<UserOrder> getUserOrder(PageVo vo);
	int countUserOrder(Integer user_id);
	
	//收货地址
	List<UserAddress> getUserAddress(PageVo vo);
	int countUserAddress(Integer user_id);
	
	//用户注册信息
	List<UserRegister> getUserRegister(String user_name);
	
	//商家注册信息
	List<ShopRegister> getShopRegister(String user_name);
	
	//代理商注册信息
	List<AgentRegister> getAgentRegister(String user_name);
	
	//根据真实姓名查用户名
	List<Object> getUserNameByPersonName(String person_name);
}
