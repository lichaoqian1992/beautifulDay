package com.manji.mlife.service;

import java.util.List;
import java.util.Map;

import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.TicketTrade;


public interface DBService {

	/**
	 * 平台基础参数
	 * @return
	 */
	Map<String,String> getBasePara ();

	Map<String,String> getTokenInfo();
	
	void updateToken(String token,String refreshToken);
	
	/**
	 * 查询加密秘钥
	 * @return
	 */
	String getSignKey();
	
	/**
	 * 插入充值订单数据
	 * @param info
	 * @param orderType
	 * @param username
	 * @return
	 */
	Map<String, String> insertData(OrderDetailInfo info, String orderType,String username,String outerId);
	
	/**
	 * 插入票务订单数据
	 * @param info
	 * @param orderType
	 * @param username
	 * @return
	 */
	Map<String, String> insertTrafficData(TicketTrade info, String orderType,String username);
	
	/**
	 * 根据订单号查询充值订单
	 * @param tradNo
	 * @return
	 */
	ChargeOrder queryChargeBills(String tradNo);
	
	/**
	 * 根据订单号查询票务订单
	 * @param tradNo
	 * @return
	 */
	List<TrafficOrder> queryTrafficBills(String tradNo);
	
	/**
	 * 修改充值类订单
	 * @author gaochao
	 * @param tradNo
	 * @return
	 * 2016年8月24日下午2:33:36
	 * Trade
	 *
	 */
	void updateChargeOrderSelective(ChargeOrder record);
	/**
	 * 查询流水
	 * @author gaochao
	 * @param tradNo
	 * @return
	 * 2016年8月24日下午3:00:26
	 * Trade
	 *
	 */
	Trade queryTradeByNo(String tradNo);
	
	/**
	 * 根据平台订单编号查询流水
	 * @author gaochao
	 * @param record
	 * 2016年8月24日下午3:01:31
	 * void
	 *
	 */
	void updateTradeSelective(Trade record);

	boolean comfirmBill(String billId,String recharge_state,String time);
	
	boolean comfirmTrafficBill(String trade_no, String ticket_state, String time, String reason);
	
	//upodate票务
	void updateTrafficSelective(TrafficOrder trafficOrder);
	
	/**
	 * 根据千米订单编号查询流水
	 * @author gaochao
	 * @param outerid
	 * @return
	 * 2016年8月24日下午3:16:54
	 *
	 */
	Trade queryTradeByNoTradeNo(String tradeNo);
	
}
