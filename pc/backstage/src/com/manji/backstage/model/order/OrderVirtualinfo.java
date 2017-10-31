package com.manji.backstage.model.order;


/**
 * 虚拟券订单详情   dt_order_virtualinfo
 * @author Administrator
 *
 */
public class OrderVirtualinfo {

	long id;
	int  article_id;
	long order_id;
	int goods_id;
	String ticket_no;
	String add_time;
	String expired_time;
	int state;
	String update_time;
	int sms_send_state;
	String sms_send_time;
	long back_order_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getExpired_time() {
		return expired_time;
	}
	public void setExpired_time(String expired_time) {
		this.expired_time = expired_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public int getSms_send_state() {
		return sms_send_state;
	}
	public void setSms_send_state(int sms_send_state) {
		this.sms_send_state = sms_send_state;
	}
	public String getSms_send_time() {
		return sms_send_time;
	}
	public void setSms_send_time(String sms_send_time) {
		this.sms_send_time = sms_send_time;
	}
	public long getBack_order_id() {
		return back_order_id;
	}
	public void setBack_order_id(long back_order_id) {
		this.back_order_id = back_order_id;
	}
	
	
	
	
	
	
	
	
	
}
