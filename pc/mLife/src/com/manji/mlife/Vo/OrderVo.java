package com.manji.mlife.Vo;
/**
 * 该实体类用于对没有支付成功的充值订单实现继续付款
 * @author Administrator
 *
 */
public class OrderVo {
	String order_id;//订单编号
	String order_title;
	String order_money;
	String order_no;//充值号码
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getOrder_title() {
		return order_title;
	}
	public void setOrder_title(String order_title) {
		this.order_title = order_title;
	}
	public String getOrder_money() {
		return order_money;
	}
	public void setOrder_money(String order_money) {
		this.order_money = order_money;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
}
