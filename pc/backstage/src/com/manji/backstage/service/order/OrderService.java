package com.manji.backstage.service.order;

import com.manji.backstage.model.base.Page;
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
import com.manji.backstage.model.user.User;
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
import com.manji.backstage.vo.user.UserVo;

public interface OrderService {

	Page<OrderGoods> queryOrderGoods(OrderGoodsVo vo);

	void addOrderGoods(OrderGoods og);

	OrderGoods getOrderGoods(long l);

	boolean updOrderGoods(OrderGoods og);

	boolean delOrderGoods(long id);
	
	
	Page<OrderBookInfo> queryOrderBookInfo(OrderBookInfoVo vo);

	void addOrderBookInfo(OrderBookInfo obi);

	OrderBookInfo getOrderBookInfo(long l);

	boolean updOrderBookInfo(OrderBookInfo obi);

	boolean delOrderBookInfo(long id);
	
	
	Page<OrderDistributioninfo> queryOrderDistributioninfo(OrderDistributioninfoVo vo);
	
	void addOrderDistributioninfo(OrderDistributioninfo od);
	
	OrderDistributioninfo getOrderDistributioninfo(long l);
	
	boolean updOrderDistributioninfo(OrderDistributioninfo od);
	
	boolean delOrderDistributioninfo(long id);
	
	
	Page<OrderVirtualinfo> queryOrderVirtualinfo(OrderVirtualinfoVo vo);
	
	void addOrderVirtualinfo(OrderVirtualinfo ov);
	
	OrderVirtualinfo getOrderVirtualinfo(long l);
	
	boolean updOrderVirtualinfo(OrderVirtualinfo ov);
	
	boolean delOrderVirtualinfo(long id);
	
	
	Page<OrderGoodInfo> queryOrderGoodInfo(OrderGoodInfoVo vo);
	
	void addOrderGoodInfo(OrderGoodInfo ogi);
	
	OrderGoodInfo getOrderGoodInfo(long l);
	
	boolean updOrderGoodInfo(OrderGoodInfo ogi);
	
	boolean delOrderGoodInfo(long id);
	
	
	Page<OrderSms> queryOrderSms(OrderSmsVo vo);
	
	void addOrderSms(OrderSms os);
	
	OrderSms getOrderSms(long l);
	
	boolean updOrderSms(OrderSms os);
	
	boolean delOrderSms(long id);
	
	
	Page<OrderAdvert> queryOrderAdvert(OrderAdvertVo vo);
	
	void addOrderAdvert(OrderAdvert oa);
	
	OrderAdvert getOrderAdvert(long id);
	
	boolean updOrderAdvert(OrderAdvert oa);
	
	boolean delOrderAdvert(long id);
	
	
	Page<OrderBusiness> queryOrderBusiness(OrderBusinessVo vo);
	
	void addOrderBusiness(OrderBusiness ob);
	
	OrderBusiness getOrderBusiness(long l);
	
	boolean updOrderBusiness(OrderBusiness ob);
	
	boolean delOrderBusiness(long id);
	
	
	Page<OrderBackInfo> queryOrderBackInfo(OrderBackInfoVo vo);
	
	void addOrderBackInfo(OrderBackInfo obi);
	
	OrderBackInfo getOrderBackInfo(long l);
	
	boolean updOrderBackInfo(OrderBackInfo obi);
	
	boolean delOrderBackInfo(long id);
	
	
	Page<Orders> queryOrders(OrdersVo vo);
	
	void addOrders(Orders orders);
	
	Orders getOrders(long l);
	
	boolean updOrders(Orders orders);
	
	boolean delOrders(long id);
	
	
	Page<OrderCallback> queryOrderCallback(OrderCallbackVo vo);
	
	void addOrderCallback(OrderCallback oc);
	
	OrderCallback getOrderCallback(long id);
	
	boolean updOrderCallback(OrderCallback oc);
	
	boolean delOrderCallback(long id);
	
	
	Page<OrderComment> queryOrderComment(OrderCommentVo vo);
	
	void addOrderComment(OrderComment OrderComment);
	
	OrderComment getOrderComment(int id);
	
	boolean updOrderComment(OrderComment OrderComment);
	
	boolean delOrderComment(int id);
	
	
	Page<OrderSettlement> queryOrderSettlement(OrderSettlementVo vo);
	
	void addOrderSettlement(OrderSettlement os);
	
	OrderSettlement getOrderSettlement(long l);
	
	boolean updOrderSettlement(OrderSettlement os);
	
	boolean delOrderSettlement(long id);
	
	
	Page<OrderOnlinePay> queryOrderOnlinePay(OrderOnlinePayVo vo);
	
	void addOrderOnlinePay(OrderOnlinePay oop);
	
	OrderOnlinePay getOrderOnlinePay(int id);
	
	boolean updOrderOnlinePay(OrderOnlinePay oop);
	
	boolean delOrderOnlinePay(int id);
	
	
	Page<OrderPartner> queryOrderPartner(OrderPartnerVo vo);
	
	void addOrderPartner(OrderPartner op);
	
	OrderPartner getOrderPartner(int id);
	
	boolean updOrderPartner(OrderPartner op);
	
	boolean delOrderPartner(int id);
	
	
	Page<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo);
	
	void addOrderCommentFalse(OrderCommentFalse ocf);
	
	OrderCommentFalse getOrderCommentFalse(int id);
	
	boolean updOrderCommentFalse(OrderCommentFalse ocf);
	
	boolean delOrderCommentFalse(int id);
	
	
	Page<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo);
	
	void addOrderCommentTem(OrderCommentTem oct);
	
	OrderCommentTem getOrderCommentTem(int id);
	
	boolean updOrderCommentTem(OrderCommentTem oct);
	
	boolean delOrderCommentTem(int id);
	
	
}
