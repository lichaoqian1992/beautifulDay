package com.manji.mlife.service;

import java.util.Map;

import com.qianmi.open.api.ApiException;

/**
 * 充值流量接口
 * @author gaochao
 * 2016年8月18日上午11:54:26
 * MobileFlowService
 *
 */
public interface MobileFlowService {

//查询号码归属地
public	String findPhoneaddr(String mobileNo) throws ApiException;

//获取零售价
public String getflowItems(String mobileNo, String flow) throws ApiException;

//创建流量充值订单
Map<String, String> getFlowCreateBill(String mobileNo, String itemId, String userName) throws ApiException;
	

}
