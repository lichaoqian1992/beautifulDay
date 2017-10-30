package com.manji.backstage.model.shop;

//dt_shop_wuliumuban_item(商家物流模板明细)
public class ShopWuliumubanItem {
	int id;
	int shop_id;
	int mb_id;
	String send_area;
	int type;
	double start;
	double start_price;
	double augment;
	double augment_price;
	int is_free;
	double free_price;
	public double getAugment() {
		return augment;
	}
	public void setAugment(double augment) {
		this.augment = augment;
	}
	public double getFree_price() {
		return free_price;
	}
	public void setFree_price(double free_price) {
		this.free_price = free_price;
	}
	int is_all;
	String add_time;
	String update_time;
	String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getMb_id() {
		return mb_id;
	}
	public void setMb_id(int mb_id) {
		this.mb_id = mb_id;
	}
	public String getSend_area() {
		return send_area;
	}
	public void setSend_area(String send_area) {
		this.send_area = send_area;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getStart() {
		return start;
	}
	public void setStart(double start) {
		this.start = start;
	}
	public double getStart_price() {
		return start_price;
	}
	public void setStart_price(double start_price) {
		this.start_price = start_price;
	}
	public double getAugment_price() {
		return augment_price;
	}
	public void setAugment_price(double augment_price) {
		this.augment_price = augment_price;
	}
	public int getIs_free() {
		return is_free;
	}
	public void setIs_free(int is_free) {
		this.is_free = is_free;
	}
	public int getIs_all() {
		return is_all;
	}
	public void setIs_all(int is_all) {
		this.is_all = is_all;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
