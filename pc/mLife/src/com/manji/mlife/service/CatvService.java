package com.manji.mlife.service;

import java.util.Map;

import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;

/**
 * 有线电视
 * @author gaochao
 * 2016年7月11日下午4:41:19
 * CatvService
 *
 */
public interface CatvService {

	/**
	 * //查询有限电视缴费的事业单位
	 * @author gaochao
	 * @param provinceVid
	 * @param cityVid
	 * @return
	 * 2016年8月18日下午3:46:35
	 * String
	 *
	 */
	String getUnitsList(String provinceVid);
	/**
	 * //生成有线电视直充订单
	 * @author gaochao
	 * @param rechargeAmount
	 * @param itemId
	 * @param rechargeAccount
	 * 2016年8月18日下午3:49:39
	 * void
	 * @param userName 
	 * @return 
	 * @throws ApiException 
	 *
	 */
	Map<String, String> getCatvCreateBill(String rechargeAmount, String itemId, String rechargeAccount, String userName) throws ApiException;
	
}
