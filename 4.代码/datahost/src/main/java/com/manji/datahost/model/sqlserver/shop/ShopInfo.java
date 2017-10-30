package com.manji.datahost.model.sqlserver.shop;

/**
 * 商家信息
 * @author Administrator
 *
 */
public class ShopInfo {

	private String name;
	private String shop_mobile;
	private Integer is_balance;
	private Integer dpkg;
	private String add_time;
	private String area;
	private Integer user_id;
	private Integer status;
	private String business_mobile;
	private String channel_title;
	private String mobile;
	private String user_name;
	private Double float_amount;
	private Double allow_amount;
	private Integer state;
	private String main_business;
	private Integer shop_id;
	private String role_type;
	private Integer role_value;
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public Integer getRole_value() {
		return role_value;
	}
	public void setRole_value(Integer role_value) {
		this.role_value = role_value;
	}
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public String getMain_business() {
		return main_business;
	}
	public void setMain_business(String main_business) {
		this.main_business = main_business;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShop_mobile() {
		return shop_mobile;
	}
	public void setShop_mobile(String shop_mobile) {
		this.shop_mobile = shop_mobile;
	}
	public Integer getIs_balance() {
		return is_balance;
	}
	public void setIs_balance(Integer is_balance) {
		this.is_balance = is_balance;
	}
	public Integer getDpkg() {
		return dpkg;
	}
	public void setDpkg(Integer dpkg) {
		this.dpkg = dpkg;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBusiness_mobile() {
		return business_mobile;
	}
	public void setBusiness_mobile(String business_mobile) {
		this.business_mobile = business_mobile;
	}
	public String getChannel_title() {
		return channel_title;
	}
	public void setChannel_title(String channel_title) {
		this.channel_title = channel_title;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Double getFloat_amount() {
		return float_amount;
	}
	public void setFloat_amount(Double float_amount) {
		this.float_amount = float_amount;
	}
	public Double getAllow_amount() {
		return allow_amount;
	}
	public void setAllow_amount(Double allow_amount) {
		this.allow_amount = allow_amount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
