package com.manji.backstage.model.property;

//dt_banktype（支持绑定银行卡/三方账户类型信息表）

public class Banktype {
	int id;
	String title;
	String img_url;
	String remark;
	int type;
	int poundage_type;
	double poundage_amount;
	double min_amount;
	double max_amount;
	int sort_id;
	int is_lock;
	String colour;
	String bin;
	public double getMin_amount() {
		return min_amount;
	}
	public void setMin_amount(double min_amount) {
		this.min_amount = min_amount;
	}
	public double getMax_amount() {
		return max_amount;
	}
	public void setMax_amount(double max_amount) {
		this.max_amount = max_amount;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPoundage_type() {
		return poundage_type;
	}
	public void setPoundage_type(int poundage_type) {
		this.poundage_type = poundage_type;
	}
	public double getPoundage_amount() {
		return poundage_amount;
	}
	public void setPoundage_amount(double poundage_amount) {
		this.poundage_amount = poundage_amount;
	}
	
}
