package com.manji.mlife.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.manji.mlife.Vo.MobileVo;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.response.MobileFlowItemsList2Response;

/**
 * 流量充值
 * @author gaochao
 * 2016年7月11日下午4:38:43
 * MobileService
 *
 */

public interface MobileService {

	/**
	 * 根据号码得到归属地和运营商信息
	 * @param mobileNo
	 * @return
	 * @throws ApiException
	 */
	Map<String,String> getArea(String mobileNo) throws ApiException;
	/**
	 * 获得直充商品信息
	 * @param mobileNo
	 * @param amount
	 * @return
	 * @throws ApiException
	 */
	Map<String,String> getDirectItem(String mobileNo, String amount) throws ApiException;
	/**
	 * 创建手机话费订单
	 * @param itemId
	 * @param mobileNo
	 * @param amount
	 * @param outerId
	 * @return
	 * @throws ApiException
	 */
	Map<String,String> createBill(MobileVo vo,String userName) throws ApiException;
	/**
	 * 支付订单
	 * @param billId
	 * @return
	 * @throws ApiException
	 */
	
	
}
