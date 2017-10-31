package com.manji.mlife.service;

import java.util.List;

import com.manji.mlife.Vo.QueryVo;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.PageBean;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;

public interface OrderService {

	
	String getChargeDetail(String billId);
	
	String getTrafficDetail(String tradeNo);
	
	List<Trade> getChargeBills(QueryVo vo, int begin, int limit);
	
	String getTrafficBills(QueryVo vo , int begin , int limit,PageBean<Trade> pageBean);

	String getbillDetails(String tradeno);

	String getairPayBack(String tradeNo, String orderId, String returnType);

	Trade queryBytradeNo(String tradeNo);

	void updateStatus(Trade to);

	List<TrafficOrder> queryBychild(String tradeno);

	void updatechild(TrafficOrder trafficOrder);

	String gettrainPayBack(String newOrderId, String newOrderId2);
	//设置总记录数:
	int findCountCid(QueryVo vo);
	//订单详情
	String getChargeBillsShowDetail(QueryVo vo);

	int findCountCid2(QueryVo vo);

	List<ChargeOrder> queryByOuterId(String outerId);
	List<Trade> queryByOuterId2(String outerId);
	void deleteByKey(String orderId);
	void deleteByKey2(String orderId);

	void deleteByKey3(String tradeno);
	
}
