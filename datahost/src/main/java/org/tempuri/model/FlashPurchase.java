package org.tempuri.model;

/**
 * 添加单品闪购
 * @author Administrator
 *
 */
public class FlashPurchase {

	private String activities_name;
	private String start_time;
	private String end_time;
	private Integer shop_count;
	private String purchaseCommodity;
	public String getActivities_name() {
		return activities_name;
	}
	public void setActivities_name(String activities_name) {
		this.activities_name = activities_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Integer getShop_count() {
		return shop_count;
	}
	public void setShop_count(Integer shop_count) {
		this.shop_count = shop_count;
	}
	public String getPurchaseCommodity() {
		return purchaseCommodity;
	}
	public void setPurchaseCommodity(String purchaseCommodity) {
		this.purchaseCommodity = purchaseCommodity;
	}
	
}
