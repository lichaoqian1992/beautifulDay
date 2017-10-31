package com.manji.backstage.model.agent;


//商家组别表    dt_shop_groups
public class AgentGroup {
	
	int id ;             //自动编号
	String title;        //组别名称
	int grade;           //会员等级值
	int upgrade_exp;     //升级经验值
	double amount;       //默认预存款
	int point;           //默认积分
	int discount;        //购物折扣
	int is_default;      //是否默认组
	int is_upgrade;      //是否自动升级
	int is_lock;         //是否禁用
	
	
	
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getUpgrade_exp() {
		return upgrade_exp;
	}
	public void setUpgrade_exp(int upgrade_exp) {
		this.upgrade_exp = upgrade_exp;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getIs_default() {
		return is_default;
	}
	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}
	public int getIs_upgrade() {
		return is_upgrade;
	}
	public void setIs_upgrade(int is_upgrade) {
		this.is_upgrade = is_upgrade;
	}
	public int getIs_lock() {
		return is_lock;
	}
	public void setIs_lock(int is_lock) {
		this.is_lock = is_lock;
	}
	
	
	
	
	
	
	
	

}
