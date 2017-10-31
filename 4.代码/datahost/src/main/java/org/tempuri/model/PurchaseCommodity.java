package org.tempuri.model;

import java.math.BigDecimal;

public class PurchaseCommodity {

	private Integer shop_id;
	private Integer article_id;
	private Integer category_id;
	private String spec_ids;
	private Integer stock_number;
	private Integer pre_stock;
	private Integer sales_volumeinfo;
	private BigDecimal commodity_pirce;
	private BigDecimal active_price;
	private Integer limit;
	private Integer active_inventory;
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getSpec_ids() {
		return spec_ids;
	}
	public void setSpec_ids(String spec_ids) {
		this.spec_ids = spec_ids;
	}
	public Integer getStock_number() {
		return stock_number;
	}
	public void setStock_number(Integer stock_number) {
		this.stock_number = stock_number;
	}
	public Integer getPre_stock() {
		return pre_stock;
	}
	public void setPre_stock(Integer pre_stock) {
		this.pre_stock = pre_stock;
	}
	public Integer getSales_volumeinfo() {
		return sales_volumeinfo;
	}
	public void setSales_volumeinfo(Integer sales_volumeinfo) {
		this.sales_volumeinfo = sales_volumeinfo;
	}
	public BigDecimal getCommodity_pirce() {
		return commodity_pirce;
	}
	public void setCommodity_pirce(BigDecimal commodity_pirce) {
		this.commodity_pirce = commodity_pirce;
	}
	public BigDecimal getActive_price() {
		return active_price;
	}
	public void setActive_price(BigDecimal active_price) {
		this.active_price = active_price;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getActive_inventory() {
		return active_inventory;
	}
	public void setActive_inventory(Integer active_inventory) {
		this.active_inventory = active_inventory;
	}
	
}
