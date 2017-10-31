package com.manji.datahost.model.sqlserver.user;

/**
 * 用户满意券流水
 * @author Administrator
 *
 */
public class UserVoucher {
	
	private Integer user_Id;
	private Double value;
	private Double old_value;
	private Double new_value;
	private String add_time;
	private String order_no;
	private String type;
	private String remark;
	private Integer order_id;
	private String order_type;
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getOld_value() {
		return old_value;
	}
	public void setOld_value(Double old_value) {
		this.old_value = old_value;
	}
	public Double getNew_value() {
		return new_value;
	}
	public void setNew_value(Double new_value) {
		this.new_value = new_value;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
