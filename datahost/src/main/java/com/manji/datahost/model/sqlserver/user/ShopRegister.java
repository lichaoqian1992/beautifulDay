package com.manji.datahost.model.sqlserver.user;
/**
 * 用户注册信息
 * @author Administrator
 *
 */
public class ShopRegister {

	private String reg_time;
	private String mobile;
	private Integer status;
	private Integer state;
	private String role_type;
	private String name;
	private String area;
	private String add_time;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
