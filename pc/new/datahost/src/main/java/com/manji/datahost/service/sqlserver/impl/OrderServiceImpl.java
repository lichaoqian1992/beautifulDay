package com.manji.datahost.service.sqlserver.impl;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.sqlserver.OrderMapper;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.order.Order;
import com.manji.datahost.model.sqlserver.order.OrderBack;
import com.manji.datahost.model.sqlserver.order.OrderBackInfo;
import com.manji.datahost.model.sqlserver.order.OrderDetail;
import com.manji.datahost.model.sqlserver.order.OrderPrice;
import com.manji.datahost.model.sqlserver.order.OrderSpec;
import com.manji.datahost.service.sqlserver.OrderService;
import com.manji.datahost.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper mapper;

	@Override
	public Page<Order> getOrders(OrderVo vo) {
		
		Page<Order> page = new Page<Order>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countOrder(vo);
		List<Order> list = mapper.getOrders(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}


	@Override
	public Page<OrderBack> getOrderBack(OrderVo vo) {

		Page<OrderBack> page = new Page<OrderBack>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countOrderBack(vo);
		List<OrderBack> list = mapper.getOrderBack(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}


	@Override
	public OrderDetail getOrderDetail(Integer order_id) {

		return mapper.getUserShopInfo(order_id);
	}


	@Override
	public List<OrderSpec> getOrderSpec(Integer order_id) {

		return mapper.getOrderSpec(order_id);
	}


	@Override
	public OrderPrice getOrderPrice(Integer order_id) {

		return mapper.getOrderPrice(order_id);
	}


	@Override
	public OrderBackInfo getOrderBackInfo(String order_no) {

		return mapper.getOrderBackInfo(order_no);
	}


	@Override
	public Integer getOrderIdByBackNo(String back_order_no) {

		return mapper.getOrderIdByBackNo(back_order_no);
	}

}
