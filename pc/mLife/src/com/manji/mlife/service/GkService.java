package com.manji.mlife.service;

import java.util.Map;

import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.GhkdOrderInfo;

/**
 * 
 * @author gaochao
 * 2016年7月8日下午5:29:57
 * GkService
 * 固话宽带充值
 */
public interface GkService {
	/**
	 * 获取省市的固话宽带商品列表
	 * @author gaochao
	 * @param provincevid
	 * @param cityvid
	 * @return
	 * 2016年8月18日下午3:00:01
	 * String
	 *
	 */
	String getGhkdItemListRequest(String provincevid);
	/**
	 * 创建固话宽带充值订单
	 * @author gaochao
	 * @param itemId
	 * @param account
	 * @param userName 
	 * @return
	 * 2016年8月18日下午3:04:02
	 * String
	 * @throws ApiException 
	 * 
	 */
	Map<String, String> getGhkdCreateBill(String itemId, String account, String userName) throws ApiException;
	
	/***
	 * //异步获取固话宽带商品面值
	 * @author gaochao
	 * @param itemId
	 * @return
	 * 2016年8月22日下午4:22:21
	 * String
	 * @throws ApiException 
	 *
	 */
	String getItemInfo(String itemId) throws ApiException;

}
