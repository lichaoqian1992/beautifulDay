package com.manji.backstage.mapper.order;

import java.util.List;

import com.manji.backstage.model.order.OrderAdvert;
import com.manji.backstage.model.order.OrderBackInfo;
import com.manji.backstage.model.order.OrderBookInfo;
import com.manji.backstage.model.order.OrderBusiness;
import com.manji.backstage.model.order.OrderCallback;
import com.manji.backstage.model.order.OrderComment;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.model.order.OrderDistributioninfo;
import com.manji.backstage.model.order.OrderGoodInfo;
import com.manji.backstage.model.order.OrderGoods;
import com.manji.backstage.model.order.OrderOnlinePay;
import com.manji.backstage.model.order.OrderPartner;
import com.manji.backstage.model.order.OrderSettlement;
import com.manji.backstage.model.order.OrderSms;
import com.manji.backstage.model.order.OrderVirtualinfo;
import com.manji.backstage.model.order.Orders;
import com.manji.backstage.vo.order.OrderAdvertVo;
import com.manji.backstage.vo.order.OrderBackInfoVo;
import com.manji.backstage.vo.order.OrderBookInfoVo;
import com.manji.backstage.vo.order.OrderBusinessVo;
import com.manji.backstage.vo.order.OrderCallbackVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;
import com.manji.backstage.vo.order.OrderCommentVo;
import com.manji.backstage.vo.order.OrderDistributioninfoVo;
import com.manji.backstage.vo.order.OrderGoodInfoVo;
import com.manji.backstage.vo.order.OrderGoodsVo;
import com.manji.backstage.vo.order.OrderOnlinePayVo;
import com.manji.backstage.vo.order.OrderPartnerVo;
import com.manji.backstage.vo.order.OrderSettlementVo;
import com.manji.backstage.vo.order.OrderSmsVo;
import com.manji.backstage.vo.order.OrderVirtualinfoVo;
import com.manji.backstage.vo.order.OrdersVo;


public interface OrderMapper {
	
	int countOrderGoods(OrderGoodsVo vo);
	
	List<OrderGoods> queryOrderGoods(OrderGoodsVo vo);

	void addOrderGoods(OrderGoods og);

	OrderGoods getOrderGoods(long id);

	int updOrderGoods(OrderGoods og);

	int delOrderGoods(long id);
	
	////////////////////////////////////////
	
	int countOrderBookInfo(OrderBookInfoVo vo);
	
	List<OrderBookInfo> queryOrderBookInfo(OrderBookInfoVo vo);
	
	void addOrderBookInfo(OrderBookInfo obi);
	
	OrderBookInfo getOrderBookInfo(long id);
	
	int updOrderBookInfo(OrderBookInfo obi);
	
	int delOrderBookInfo(long id);
	
	////////////////////////////////////////
	
	int countOrderDistributioninfo(OrderDistributioninfoVo vo);
	
	List<OrderDistributioninfo> queryOrderDistributioninfo(OrderDistributioninfoVo vo);
	
	void addOrderDistributioninfo(OrderDistributioninfo od);
	
	OrderDistributioninfo getOrderDistributioninfo(long id);
	
	int updOrderDistributioninfo(OrderDistributioninfo od);
	
	int delOrderDistributioninfo(long id);
	
	////////////////////////////////////////
	
	int countOrderVirtualinfo(OrderVirtualinfoVo vo);
	
	List<OrderVirtualinfo> queryOrderVirtualinfo(OrderVirtualinfoVo vo);
	
	void addOrderVirtualinfo(OrderVirtualinfo ov);
	
	OrderVirtualinfo getOrderVirtualinfo(long id);
	
	int updOrderVirtualinfo(OrderVirtualinfo ov);
	
	int delOrderVirtualinfo(long id);
	
	////////////////////////////////////////
	
	int countOrderGoodInfo(OrderGoodInfoVo vo);
	
	List<OrderGoodInfo> queryOrderGoodInfo(OrderGoodInfoVo vo);
	
	void addOrderGoodInfo(OrderGoodInfo ogi);
	
	OrderGoodInfo getOrderGoodInfo(long id);
	
	int updOrderGoodInfo(OrderGoodInfo ogi);
	
	int delOrderGoodInfo(long id);
	
	////////////////////////////////////////
	
	int countOrderSms(OrderSmsVo vo);
	
	List<OrderSms> queryOrderSms(OrderSmsVo vo);
	
	void addOrderSms(OrderSms os);
	
	OrderSms getOrderSms(long id);
	
	int updOrderSms(OrderSms os);
	
	int delOrderSms(long id);
	
	////////////////////////////////////////
	
	int countOrderAdvert(OrderAdvertVo vo);
	
	List<OrderAdvert> queryOrderAdvert(OrderAdvertVo vo);
	
	void addOrderAdvert(OrderAdvert oa);
	
	OrderAdvert getOrderAdvert(long id);
	
	int updOrderAdvert(OrderAdvert oa);
	
	int delOrderAdvert(long id);
	
	////////////////////////////////////////
	
	int countOrderBusiness(OrderBusinessVo vo);
	
	List<OrderBusiness> queryOrderBusiness(OrderBusinessVo vo);
	
	void addOrderBusiness(OrderBusiness ob);
	
	OrderBusiness getOrderBusiness(long id);
	
	int updOrderBusiness(OrderBusiness ob);
	
	int delOrderBusiness(long id);
	
	////////////////////////////////////////
	
	int countOrderBackInfo(OrderBackInfoVo vo);
	
	List<OrderBackInfo> queryOrderBackInfo(OrderBackInfoVo vo);
	
	void addOrderBackInfo(OrderBackInfo obi);
	
	OrderBackInfo getOrderBackInfo(long id);
	
	int updOrderBackInfo(OrderBackInfo obi);
	
	int delOrderBackInfo(long id);
	
	////////////////////////////////////////
	
	int countOrders(OrdersVo vo);
	
	List<Orders> queryOrders(OrdersVo vo);
	
	void addOrders(Orders orders);
	
	Orders getOrders(long id);
	
	int updOrders(Orders orders);
	
	int delOrders(long id);
	
	////////////////////////////////////////
	
	int countOrderCallback(OrderCallbackVo vo);
	
	List<OrderCallback> queryOrderCallback(OrderCallbackVo vo);
	
	void addOrderCallback(OrderCallback oc);
	
	OrderCallback getOrderCallback(long id);
	
	int updOrderCallback(OrderCallback oc);
	
	int delOrderCallback(long id);
	
	////////////////////////////////////////
	
	int countOrderComment(OrderCommentVo vo);
	
	List<OrderComment> queryOrderComment(OrderCommentVo vo);
	
	void addOrderComment(OrderComment oc);
	
	OrderComment getOrderComment(int id);
	
	int updOrderComment(OrderComment oc);
	
	int delOrderComment(int id);
	
	////////////////////////////////////////
	
	int countOrderSettlement(OrderSettlementVo vo);
	
	List<OrderSettlement> queryOrderSettlement(OrderSettlementVo vo);
	
	void addOrderSettlement(OrderSettlement os);
	
	OrderSettlement getOrderSettlement(long id);
	
	int updOrderSettlement(OrderSettlement os);
	
	int delOrderSettlement(long id);
	
	////////////////////////////////////////
	
	int countOrderOnlinePay(OrderOnlinePayVo vo);
	
	List<OrderOnlinePay> queryOrderOnlinePay(OrderOnlinePayVo vo);
	
	void addOrderOnlinePay(OrderOnlinePay oop);
	
	OrderOnlinePay getOrderOnlinePay(int id);
	
	int updOrderOnlinePay(OrderOnlinePay oop);
	
	int delOrderOnlinePay(int id);
	
	////////////////////////////////////////
	
	int countOrderPartner(OrderPartnerVo vo);
	
	List<OrderPartner> queryOrderPartner(OrderPartnerVo vo);
	
	void addOrderPartner(OrderPartner op);
	
	OrderPartner getOrderPartner(int id);
	
	int updOrderPartner(OrderPartner op);
	
	int delOrderPartner(int id);
	
	////////////////////////////////////////
	
	int countOrderCommentFalse(OrderCommentFalseVo vo);
	
	List<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo);
	
	void addOrderCommentFalse(OrderCommentFalse ocf);
	
	OrderCommentFalse getOrderCommentFalse(int id);
	
	int updOrderCommentFalse(OrderCommentFalse ocf);
	
	int delOrderCommentFalse(int id);
	
	////////////////////////////////////////
	
	int countOrderCommentTem(OrderCommentTemVo vo);
	
	List<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo);
	
	void addOrderCommentTem(OrderCommentTem oct);
	
	OrderCommentTem getOrderCommentTem(int id);
	
	int updOrderCommentTem(OrderCommentTem oct);
	
	int delOrderCommentTem(int id);
	
	////////////////////////////////////////
	
	

}
