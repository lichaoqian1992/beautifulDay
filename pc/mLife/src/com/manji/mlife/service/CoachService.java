package com.manji.mlife.service;


import java.util.Map;

import com.manji.mlife.Vo.CoachLineVo;
import com.qianmi.open.api.ApiException;

public interface CoachService {
	
	
	/**
	 * 得到出发站点集合
	 * @return
	 * @throws ApiException
	 */
	String getStartStation() throws ApiException;
	
	/**
	 * 根据出发站点得到到达站点集合
	 * @param startCity
	 * @return
	 * @throws ApiException
	 */
	String getEndStation(String startCity) throws ApiException;
	
	/**
	 * 查询汽车票行程
	 * @param startCity
	 * @param endCity
	 * @param date
	 * @return
	 * @throws ApiException
	 */
	String getLines(String startCity, String endCity ,String date) throws ApiException;
	
	/**
	 * 创建汽车票订单
	 * @param vo
	 * @return
	 * @throws ApiException
	 */
	Map<String, String> createOrder(CoachLineVo vo,Map<String, String>infoMap) throws ApiException;
	
//	Map<String, String> payOrder (String billId) throws ApiException;
}
