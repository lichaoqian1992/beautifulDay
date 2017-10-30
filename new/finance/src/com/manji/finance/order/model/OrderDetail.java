package com.manji.finance.order.model;

import com.jfinal.plugin.activerecord.Model;

public class OrderDetail extends Model<OrderDetail>{
	
	/**
	 * 方便对数据库的操作
	 */
	public static final OrderDetail od = new OrderDetail().dao();
}
