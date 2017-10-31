package com.manji.mlife.service;

import java.util.List;
import java.util.Map;

import com.manji.mlife.Vo.GasCardVo;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.AdminItem;

public interface GasCardService {
	
	/**
	 * 根据供应商查询商品列表
	 * @param operator
	 * @return
	 * @throws ApiException
	 */
	String queryCom(String operator)throws ApiException;
	/**
	 * 查询加油卡卡号信息
	 * @param province
	 * @param operator
	 * @param gasCardNo
	 * @return
	 * @throws ApiException
	 */
	String queryAccount(String province ,String operator, String gasCardNo) throws ApiException;
	/**
	 * 生成加油卡缴费订单
	 * @param vo
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> creatBill(GasCardVo vo,String userName) throws ApiException;
	
	
}
