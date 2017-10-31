package com.manji.mlife.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.common.OuterId;
import com.manji.mlife.common.StringUtil;
import com.manji.mlife.common.TransformUnit;
import com.manji.mlife.controller.BaseController;
import com.manji.mlife.mapper.ChargeOrderMapper;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.mapper.TradeMapper;
import com.manji.mlife.mapper.TrafficOrderMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.TicketTrade;
@Service
public class DBServiceImpl extends BaseController  implements DBService{
	@Autowired
	private  SimpleCodeMapper codeMapper;
	@Autowired
	private  TrafficOrderMapper trafficMapper;
	@Autowired
	private  ChargeOrderMapper chargeMapper;
	@Autowired
	private  TradeMapper tradeMapper;
	
	private static final Logger logger =Logger.getLogger(DBServiceImpl.class);
	
	@Override
	public Map<String, String> getBasePara() {
		String url =codeMapper.getValue("qianmiURL");
		String appKey =codeMapper.getValue("appKey");
		String appSecret =codeMapper.getValue("appSecret");
		String accessToken=codeMapper.getValue("accessToken");
		Map<String, String> baseMap =new HashMap<String, String>();
		
		baseMap.put("url", url);
		baseMap.put("appKey", appKey);
		baseMap.put("appSecret", appSecret);
		baseMap.put("accessToken", accessToken);
		
		return baseMap;
	}

	@Override
	public String getSignKey() {
		String key =codeMapper.getValue("signKey");
		return key;
	}
	
	/**
	 * 充值类流水+订单
	 * @author gaochao
	 * @param info
	 * @param orderType
	 * @param userName
	 * @return
	 * 2016年8月24日上午10:37:11
	 *
	 */
	@Override
	public Map<String, String> insertData(OrderDetailInfo info, String orderType, String userName,String outerId) {
		Map<String,String> map =new HashMap<String,String>();
		
		String redirectURL =codeMapper.getValue("redirectURL");
		String backSynURL=codeMapper.getValue("backSynURL");
		String backAsynURL=codeMapper.getValue("backAsynURL");
		
		Trade tradeInfo = TransformUnit.transformChargeTrade(info);
		tradeInfo.setOuterid(outerId);
		tradeInfo.setType(orderType);
		tradeInfo.setUsername(userName);
		tradeInfo.setFlag("0");
		
		ChargeOrder order = TransformUnit.transformChargeOrder(info);
		order.setOuterid(outerId);
		
		//插入数据库
		tradeMapper.insertSelective(tradeInfo);
		chargeMapper.insertSelective(order);
		
		map.put("outerId", outerId);
		map.put("itemName", info.getItemName());
		map.put("saleAmount", info.getSaleAmount());
		map.put("backSynURL", backSynURL);
		map.put("backAsynURL", backAsynURL);
		map.put("redirectURL", redirectURL);
		logger.debug(redirectURL);
		return map;
	}
	
	/**
	 * 票务类流水+订单
	 * @author gaochao
	 * @param info
	 * @param orderType
	 * @param username
	 * @return
	 * 2016年8月24日上午10:37:39
	 *
	 */
	@Override
	public Map<String, String> insertTrafficData(TicketTrade info, String orderType, String userName) {
		String redirectURL =codeMapper.getValue("redirectURL");
		String backSynURL=codeMapper.getValue("backSynURL");
		String backAsynURL=codeMapper.getValue("backAsynURL");
		
	    // 流水
		Trade tradeInfo = TransformUnit.transformTicketTrade(info);
		// 订单号
		String outerTid = OuterId.getOuterId();
		tradeInfo.setOuterid(outerTid);
		tradeInfo.setType(orderType);
		tradeInfo.setUsername(userName);
		tradeInfo.setFlag("1");
		
		// 插入数据库
		tradeMapper.insertSelective(tradeInfo);
		List<TrafficOrder> trafficOrderList = TransformUnit.transformTicketOrder(info);
		for (TrafficOrder trafficOrder : trafficOrderList) {
			trafficMapper.insertSelective(trafficOrder);
		}
		
        Map<String,String> map =new HashMap<String,String>();
		map.put("outerTid", outerTid);
		map.put("itemName", info.getTitle());
		map.put("totalPayCash", info.getTotalPayCash());
		map.put("backSynURL", backSynURL);
		map.put("backAsynURL", backAsynURL);
		map.put("redirectURL", redirectURL);
		//map.put("tradeNo", info.getTradeNo());
		return map;
	}
	
	/**
	 * //根据订单号查询充值订单
	 * @author gaochao
	 * @param tradNo
	 * @return
	 * 2016年8月24日下午2:27:05
	 *
	 */
	@Override
	public ChargeOrder queryChargeBills(String billId) {
		// 参数校验
		if (StringUtil.isNullOrEmpty(billId)){
			return null;
		}
		
		ChargeOrder chargeOrder = chargeMapper.selectByPrimaryKey(billId);
		return chargeOrder;
	}
	
	/**
	 * 修改充值类订单
	 * @author gaochao
	 * @param record
	 * 2016年8月24日下午2:39:59
	 *
	 */
	@Override
	public void updateChargeOrderSelective(ChargeOrder record) {
		if (record == null){
			return;
		}
		
		chargeMapper.updateByPrimaryKeySelective(record);
	}
	
	/***
	 * 查询票务订单
	 * @author gaochao
	 * @param tradNo
	 * @return
	 * 2016年8月24日下午4:12:34
	 *
	 */
	@Override
	public List<TrafficOrder> queryTrafficBills(String tradNo) {
		// 参数校验
		if (StringUtil.isNullOrEmpty(tradNo)){
			return null;
		}
		
		List<TrafficOrder> TrafficOrders = trafficMapper.selectByPrimaryKey2(tradNo);
		return TrafficOrders;
	}

	/**
	 * 保存票务
	 * @author gaochao
	 * @param trafficOrder
	 * 2016年8月25日下午3:17:48
	 *
	 */
	@Override
	public void updateTrafficSelective(TrafficOrder trafficOrder) {
		trafficMapper.updateByPrimaryKeySelective(trafficOrder);	
	}
	
	/**
	 * 根据外部订单编号查询流水
	 * @author gaochao
	 * @param outerid
	 * @return
	 * 2016年8月24日下午3:16:54
	 */
	@Override
	public Trade queryTradeByNo(String outerid) {
		Trade tradeInfo =tradeMapper.selectByPrimaryKey(outerid);
		return tradeInfo;
	}
	
	/**
	 * 根据千米订单编号查询流水
	 * @author gaochao
	 * @param outerid
	 * @return
	 * 2016年8月24日下午3:16:54
	 *
	 */
	@Override
	public Trade queryTradeByNoTradeNo(String tradeNo) {
		Trade tradeInfo =tradeMapper.selectByPrimaryKeyTradeNo(tradeNo);
		return tradeInfo;
	}
	
	/**
	 * 修改流水
	 * @author gaochao
	 * @param record
	 * 2016年8月24日下午3:17:05
	 *
	 */
	@Override
	public void updateTradeSelective(Trade record) {	
		tradeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Map<String, String> getTokenInfo() {
		Map<String, String> map =new HashMap<String, String>();
		
		String appKey =codeMapper.getValue("appKey");
		String appSecret =codeMapper.getValue("appSecret");
		String accessToken=codeMapper.getValue("accessToken");
		String refreshToken = codeMapper.getValue("refreshToken");
	
		map.put("appKey", appKey);
		map.put("appSecret", appSecret);
		map.put("accessToken", accessToken);
		map.put("refreshToken", refreshToken);
		
		
		return map;
	}

	@Override
	public void updateToken(String token, String refreshToken) {	
		codeMapper.updateToken(token);
		codeMapper.updateRefreshToken(refreshToken);	
	}
	
	/**
	 * 千米返回充值类
	 * @author gaochao
	 * @param billId
	 * @param recharge_state
	 * @param time
	 * @return
	 * 2016年9月18日上午11:29:03
	 *
	 */
	@Override
	public boolean comfirmBill(String billId, String rechargeState, String time) {
		logger.info("---------更改充值状态Trade........");
		Trade tradeInfo =tradeMapper.selectByPrimaryKeyTradeNo(billId);

		//订单充值状态 0充值中 1成功 9撤销
		if("0".equals(rechargeState)){
			tradeInfo.setState("1");
		}else if("1".equals(rechargeState)){
			tradeInfo.setState("2");
		}else if("9".equals(rechargeState)){
			/**
			 * 退钱给满集网账户,退款成功;改变订单的状态为已退款
			 * ************主表*******************
			 */
			// boolean partnerOrderBack = PartnerOrderBack(tradeInfo.getOuterid());
			tradeInfo.setState("3");
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		tradeInfo.setEtime(simpleDateFormat.format(new Date()));
		tradeMapper.updateByPrimaryKeySelective(tradeInfo);
		logger.info("---------更改流水Trade........");
		
		ChargeOrder billInfo =chargeMapper.selectByPrimaryKey(billId);
		billInfo.setPaystate("1");//
		billInfo.setRechargestate(rechargeState);
		chargeMapper.updateByPrimaryKeySelective(billInfo);
		logger.info("---------更改订单ChargeOrder........");

		return false;
	}

	/**
	 * 千米返回票务类
	 * @author gaochao
	 * @param billId
	 * @param ticket_state
	 * @param time
	 * @param reason
	 * @return
	 * 2016年9月18日上午11:29:22
	 *
	 */
	@Override
	public boolean comfirmTrafficBill(String billId, String ticketState, String time, String reason) {
		logger.info("---------千米返回状态更改票务类充值状态Trade........");
		
		Trade tradeInfo =tradeMapper.selectByPrimaryKeyTradeNo(billId);
		String tradeNo =tradeInfo.getTradeno();
		//改票务类订单状态：0-预定中，2-预定完成，待支付，1-已完成，9-已取消
		
		if("0".equals(ticketState)){
			tradeInfo.setState("1");
		}else if("2".equals(ticketState)){
			tradeInfo.setState("0");
		}else if("1".equals(ticketState)){
			tradeInfo.setState("2");
		}else if("9".equals(ticketState)){
			tradeInfo.setState("3");
		}
		
		tradeInfo.setState(ticketState);
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		tradeInfo.setEtime(simpleDateFormat.format(new Date()));
		tradeMapper.updateByPrimaryKey(tradeInfo);
		
		List<TrafficOrder> trafficOrders = trafficMapper.selectByPrimaryKey2(tradeNo);
		for (int i = 0; i < trafficOrders.size(); i++) {
			  // 批量更改飞机票订单状态
			  TrafficOrder trafficOrder = trafficOrders.get(i);
			  trafficOrder.setState(ticketState);
			  // upddate 修改票务
			  trafficMapper.updateByPrimaryKeySelective(trafficOrder);	
		}

		return false;
	}
	
}
