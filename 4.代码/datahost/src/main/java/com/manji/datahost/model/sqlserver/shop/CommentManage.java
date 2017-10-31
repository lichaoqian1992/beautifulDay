package com.manji.datahost.model.sqlserver.shop;

/**
 * 评论管理
 * @author Administrator
 *
 */
public class CommentManage {
	
	private Integer order_id;
	private String user_name;
	private Integer service_review_score;
	private Integer pack_review_score;
	private Integer distribution_review_score;
	private String order_no;
	private String add_time;
	private String content;
	private String pics;
	private Integer status;
	private String state;
	
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getService_review_score() {
		return service_review_score;
	}
	public void setService_review_score(Integer service_review_score) {
		this.service_review_score = service_review_score;
	}
	public Integer getPack_review_score() {
		return pack_review_score;
	}
	public void setPack_review_score(Integer pack_review_score) {
		this.pack_review_score = pack_review_score;
	}
	public Integer getDistribution_review_score() {
		return distribution_review_score;
	}
	public void setDistribution_review_score(Integer distribution_review_score) {
		this.distribution_review_score = distribution_review_score;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
