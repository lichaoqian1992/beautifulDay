package com.manji.datahost.model.sqlserver.client;

/**
 * 商家信誉信息
 * @author Administrator
 *
 */
public class ShopReputation {

	private Integer shop_id;
	private Integer score;
	private String grade;
	private String pc_logo;
	private String name;
	private String company_name;
	
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPc_logo() {
		return pc_logo;
	}
	public void setPc_logo(String pc_logo) {
		this.pc_logo = pc_logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
}
