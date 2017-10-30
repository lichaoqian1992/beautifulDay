package org.tempuri.model;

/**
 * 修改单品闪购
 */
public class UpdateFlashPurchaseDto {

	private String purchase_code;
	private String activities_name;
	private String start_time;
	private String end_time;
	private Integer shop_count;
	private Integer visit_count;
	private Integer order_count;
	private Double purchase_money;
	private Integer purchase_types;
	private Integer state;
	private String remarks;
	private String purchaseCommodityDto;
	private String activeDetailsDto;
	public String getPurchase_code() {
		return purchase_code;
	}
	public void setPurchase_code(String purchase_code) {
		this.purchase_code = purchase_code;
	}
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
	public Integer getVisit_count() {
		return visit_count;
	}
	public void setVisit_count(Integer visit_count) {
		this.visit_count = visit_count;
	}
	public Integer getOrder_count() {
		return order_count;
	}
	public void setOrder_count(Integer order_count) {
		this.order_count = order_count;
	}
	public Double getPurchase_money() {
		return purchase_money;
	}
	public void setPurchase_money(Double purchase_money) {
		this.purchase_money = purchase_money;
	}
	public Integer getPurchase_types() {
		return purchase_types;
	}
	public void setPurchase_types(Integer purchase_types) {
		this.purchase_types = purchase_types;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPurchaseCommodityDto() {
		return purchaseCommodityDto;
	}
	public void setPurchaseCommodityDto(String purchaseCommodityDto) {
		this.purchaseCommodityDto = purchaseCommodityDto;
	}
	public String getActiveDetailsDto() {
		return activeDetailsDto;
	}
	public void setActiveDetailsDto(String activeDetailsDto) {
		this.activeDetailsDto = activeDetailsDto;
	}
	
}
