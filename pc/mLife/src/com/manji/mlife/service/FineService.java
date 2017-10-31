package com.manji.mlife.service;

import java.util.List;
import java.util.Map;

import com.manji.mlife.Vo.FineVo;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.AdminItem;

public interface FineService {

	/**
	 * 根据省市查询商品信息
	 * @param province
	 * @param City
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> queryItem(String province, String City) throws ApiException;
	
	/**
	 * 生成订单信息
	 * @param fine
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> createBill(FineVo fine,String userName) throws ApiException;
	
	/**
	 * 提交订单信息
	 * @param billId
	 * @return
	 * @throws ApiException
	 */

	
	
}
