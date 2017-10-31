package com.manji.datahost.mapper.sqlserver;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.manji.datahost.model.sqlserver.order.Order;
import com.manji.datahost.model.sqlserver.order.OrderBack;
import com.manji.datahost.model.sqlserver.order.OrderBackInfo;
import com.manji.datahost.model.sqlserver.order.OrderDetail;
import com.manji.datahost.model.sqlserver.order.OrderPrice;
import com.manji.datahost.model.sqlserver.order.OrderSpec;
import com.manji.datahost.vo.OrderVo;

@Mapper
public interface OrderMapper {
	
	//订单外呼
	List<Order> getOrders(OrderVo vo);
	int countOrder(OrderVo vo);
	
	//退单信息
	List<OrderBack> getOrderBack(OrderVo vo);
	int countOrderBack(OrderVo vo);
	
	//订单用户和商家信息
	OrderDetail getUserShopInfo(Integer order_id);
	
	//订单规格信息
	List<OrderSpec> getOrderSpec(Integer order_id);
	
	//订单价格信息
	OrderPrice getOrderPrice(Integer order_id);
	
	//退单详情
	OrderBackInfo getOrderBackInfo(String order_no);
	
	//根据退单号查订单号
	Integer getOrderIdByBackNo(String back_order_no);
}
