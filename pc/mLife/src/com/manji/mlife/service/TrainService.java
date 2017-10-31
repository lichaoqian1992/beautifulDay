package com.manji.mlife.service;

import java.util.Map;

import com.manji.mlife.Vo.TrainLineVo;
import com.manji.mlife.Vo.TrainVo;
import com.qianmi.open.api.ApiException;

public interface TrainService {
	/**
	 * 获得火车站列表
	 * @return
	 * @throws ApiException
	 */
	String getStation() throws ApiException ;
	
	/**
	 * 查询火车票
	 * @param startCity
	 * @param endCity
	 * @param date
	 * @return
	 * @throws ApiException
	 */
	String getTicket(String startCity, String endCity, String date) throws ApiException ;
	/**
	 * 获得保险商品信息
	 * @return
	 * @throws ApiException
	 */
	String getInsurance()throws ApiException ;
	
	/**
	 * 创建火车票订单信息
	 * @param vo
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> createOrder(TrainLineVo vo , Map<String, String>infoMap,String totalPrice)  throws ApiException;
	

	
	String cancleOrder(String tradeNo);
	
	
}
