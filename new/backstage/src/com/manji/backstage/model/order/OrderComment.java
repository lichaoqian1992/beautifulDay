package com.manji.backstage.model.order;


/**
 * 订单评论  dt_order_comment
 * @author Administrator
 *
 */
public class OrderComment {

	int id;
	int order_id;
	int shop_user_id;
	String shop_user_role_type;
	int shop_user_role_value;
	int user_id;
	String user_role_type;
	int user_role_value;
	String user_ip;
	int review_score;
	int service_review_score;
	int pack_review_score;
	int distribution_review_score;
	int is_lock;
	String add_time;
	String reply_area;
	String local_area;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getShop_user_id() {
		return shop_user_id;
	}
	public void setShop_user_id(int shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	public String getShop_user_role_type() {
		return shop_user_role_type;
	}
	public void setShop_user_role_type(String shop_user_role_type) {
		this.shop_user_role_type = shop_user_role_type;
	}
	public int getShop_user_role_value() {
		return shop_user_role_value;
	}
	public void setShop_user_role_value(int shop_user_role_value) {
		this.shop_user_role_value = shop_user_role_value;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_role_type() {
		return user_role_type;
	}
	public void setUser_role_type(String user_role_type) {
		this.user_role_type = user_role_type;
	}
	public int getUser_role_value() {
		return user_role_value;
	}
	public void setUser_role_value(int user_role_value) {
		this.user_role_value = user_role_value;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public int getReview_score() {
		return review_score;
	}
	public void setReview_score(int review_score) {
		this.review_score = review_score;
	}
	public int getService_review_score() {
		return service_review_score;
	}
	public void setService_review_score(int service_review_score) {
		this.service_review_score = service_review_score;
	}
	public int getPack_review_score() {
		return pack_review_score;
	}
	public void setPack_review_score(int pack_review_score) {
		this.pack_review_score = pack_review_score;
	}
	public int getDistribution_review_score() {
		return distribution_review_score;
	}
	public void setDistribution_review_score(int distribution_review_score) {
		this.distribution_review_score = distribution_review_score;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getReply_area() {
		return reply_area;
	}
	public void setReply_area(String reply_area) {
		this.reply_area = reply_area;
	}
	public String getLocal_area() {
		return local_area;
	}
	public void setLocal_area(String local_area) {
		this.local_area = local_area;
	}
	
	
	
	
	
}
