package com.manji.mlife.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.Trade;
import com.manji.mlife.model.TrafficOrder;
import com.qianmi.open.api.domain.elife.OInsurance;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.TicketOrder;
import com.qianmi.open.api.domain.elife.TicketTrade;

public class TransformUnit {

	/**
	 * 充值类流水组装
	 * @param trade
	 * @param orderInfo
	 * @return
	 */
	public static Trade transformChargeTrade(OrderDetailInfo orderInfo){
		Trade trade = new Trade();
		trade.setTradeno(orderInfo.getBillId());
		trade.setCtime(orderInfo.getOrderTime());
		trade.setTitle(orderInfo.getItemName());
		trade.setPrice(orderInfo.getSaleAmount());
		trade.setPaycash(orderInfo.getSaleAmount());
		trade.setTel("");
		trade.setName(orderInfo.getRechargeAccount());
		trade.setState("0");
		trade.setPstate("0");
		trade.setPtime("");
		trade.setEtime(" ");
		
		return trade;
	}
	
	/**
	 * 充值类订单组装
	 * @param order
	 * @param orderInfo
	 * @return
	 */
	public static ChargeOrder transformChargeOrder(OrderDetailInfo orderInfo){
		ChargeOrder order = new ChargeOrder();
		order.setBillid(orderInfo.getBillId());//订单编号		
		order.setSaleamount(orderInfo.getSaleAmount());
		order.setOrderprofit(orderInfo.getOrderProfit());
		order.setOrdertime(orderInfo.getOrderTime());
		order.setOperatetime(orderInfo.getOperateTime());
		order.setPaystate(orderInfo.getPayState());
		order.setRechargeaccount(orderInfo.getRechargeAccount());
		order.setRechargestate(orderInfo.getRechargeState());
		order.setRevokemessage("");
		order.setActprice(orderInfo.getActPrice());
		order.setClasstype(orderInfo.getClassType());
		order.setItemcost(orderInfo.getItemCost());
		order.setOrdercost(orderInfo.getOrderCost());
		order.setItemnum(orderInfo.getItemNum());
		order.setItemname(orderInfo.getItemName());
		order.setSupid(orderInfo.getSupId());
		order.setSupqq(orderInfo.getSupQq());
		order.setSupnickname(orderInfo.getSupNickName());
		order.setSupcontactuser(orderInfo.getSupContactUser());
		order.setSupmobile(orderInfo.getSupMobile());
		order.setGamearea(orderInfo.getGameArea());
		order.setGameserver(orderInfo.getGameServer());
		order.setReceivemobile(orderInfo.getReceiveMobile());

		order.setBattleaccount(orderInfo.getBattleAccount());
		order.setTplid(orderInfo.getTplId());
		return order;
	}
	/**
	 * 票务类订单组装
	 * @param to
	 * @return
	 */
	 public static  List<TrafficOrder> transformTicketOrder(TicketTrade to){
		 List<TicketOrder> ticketOrders = to.getTicketOrders();
		 // 票务订单
		 List<TrafficOrder> ticks = new  ArrayList<TrafficOrder>();
	 	 for (TicketOrder tOrder : ticketOrders) {
	 		 TrafficOrder tr=new TrafficOrder();
	 	     tr.setTradno(to.getTradeNo());
	 	     tr.setTitle(to.getTitle());
	 		 tr.setOrderno(tOrder.getOrderNo());
	 	     tr.setItemid(tOrder.getItemId());
	 	     tr.setPassengername(tOrder.getPassengerName());
	 	     tr.setPassengertel(tOrder.getPassengerTel());
	 	     tr.setIdcardtype(tOrder.getIdcardType().toString());
	 	     tr.setIdcardno(tOrder.getIdcardNo());
	 	     tr.setTicketno(tOrder.getTicketNo());
	 	     tr.setSaleprice(tOrder.getSalePrice());
	 	     tr.setPaycash(tOrder.getPayCash());
	 	     tr.setFeedetail(tOrder.getFeeDetail());
	 	     tr.setSeattype(tOrder.getSeatType().toString());		 	    
	 	     tr.setSeatinfo(tOrder.getSeatInfo());
	 	     tr.setSaleorderno(tOrder.getSaleOrderNo());
		 	 tr.setState(tOrder.getState().toString());
	 	     tr.setRefundfee(to.getTotalRefundFee());//退款手续费
	 	     //保险
	 	     OInsurance ins = tOrder.getInsurance();
	 	     if(null != ins){
		 	     tr.setInsurancestate(ins.getState().toString());
		 	  	 tr.setInsurancefaceprice(ins.getFacePrice());
		 	  	 tr.setInsuranceitemcash(ins.getPayCash());
		 	  	 tr.setInsurancesaleprice(ins.getSalePrice());
		 	  	 tr.setInsurancesaleorderno(ins.getSaleOrderNo());
		 	  	 tr.setInsuranceinsuranceno(ins.getInsuranceNo());
		 	  	 tr.setInsuranceitemid(ins.getItemId());
		 	     tr.setInsuranceusertel(ins.getUserTel());
		 	     tr.setInsuranceusername(ins.getUserName());
		 	     tr.setInsuranceinsurancepolicyno(ins.getInsurancePolicyNo());
	 	     }
	 	    
	 	     tr.setSeatname(to.getStateName());//座位类型名称	
	 	     tr.setTrainno(to.getTrainNo());
	 	     tr.setStartstation(to.getStartStation());
	 	     tr.setReceviestation(to.getRecevieStation());
	 	     tr.setStarttime(to.getStartTime());
	 	     tr.setRecevietime(to.getRecevieTime());
	 	     tr.setContactname(to.getContactName());
	 	     tr.setContacttel(to.getContactTel());
	 	     tr.setOrdertype(to.getOrderType().toString());
	 	     tr.setTotalsaleprice(to.getTotalSalePrice());//建议零售价
	 	     tr.setTotalpaycash(to.getTotalPayCash());//实际支付的金额
	 	     tr.setTotalotherfee(to.getTotalOtherFee());//其它费用总和
	 	     tr.setTotalpurprice(to.getTotalPurPrice());//网点的采购价
	 	     tr.setTotalfaceprice(to.getTotalFacePrice());//票面价
	 	     tr.setTotalpaycash(to.getTotalPayCash());//实际支付的金额
	 	     tr.setTotalsaleprice(to.getTotalSalePrice());//建议零售价，单位元，支持两位小数
	 	     ticks.add(tr);
	 	 }
	 	 return ticks;
	}
	 
	/**
	 * 票务类流水组装
	 * @param tradeInfo
	 * @param info
	 * @return
	 */
	public static Trade transformTicketTrade(TicketTrade info) {
		Trade tradeInfo = new Trade();
		tradeInfo.setTradeno(info.getTradeNo());//订单编号
		tradeInfo.setCtime(info.getCtime());//创建时间
		tradeInfo.setTitle(info.getTitle());//标题
		tradeInfo.setPrice(info.getTotalPayCash());//面值
		tradeInfo.setPaycash(info.getTotalPayCash());//销售价
		tradeInfo.setState("0");//订单状态 0-预定完成，待支付，1-已完成，9-已取消
		tradeInfo.setPstate("0");//付款状态
		tradeInfo.setPtime("");//支付时间
		tradeInfo.setName(info.getContactName());//订票人姓名
		tradeInfo.setTel(info.getContactTel());//订票人电话
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tradeInfo.setEtime(sdf.format(new Date()));//完成时间
		
		return tradeInfo;
	}
	
}
