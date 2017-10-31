package com.manji.mlife.service;

import java.util.Map;

import com.manji.mlife.Vo.WaterCoalVo;
import com.qianmi.open.api.ApiException;

public interface WaterCoalService {

	
	
	String queryProps(String province, String city, String mode)throws ApiException;
	
	/**
	 * 查询账单信息
	 * @param itemId
	 * @param account
	 * @return
	 * @throws ApiException
	 */
	String queryBill(String account, String itemId) throws ApiException;
	/**
	 * 查询缴费公司详细信息
	 * @param itemId
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> queryItem(String itemId)throws ApiException;
	/**
	 * 生成订单信息
	 * @param itemId
	 * @param account
	 * @param price
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> createBill(WaterCoalVo vo,String userName)throws ApiException;
	
	

	
	
	
	
}
