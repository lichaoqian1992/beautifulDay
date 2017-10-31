package com.manji.backstage.model.content;

public class ArticleGoods {

	int id;
	int article_id;
	String goods_no;
	String spec_ids;
	String spec_text;
	int stock_quantity;
	double market_price;
	double sell_price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	public String getSpec_ids() {
		return spec_ids;
	}
	public void setSpec_ids(String spec_ids) {
		this.spec_ids = spec_ids;
	}
	public String getSpec_text() {
		return spec_text;
	}
	public void setSpec_text(String spec_text) {
		this.spec_text = spec_text;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getSell_price() {
		return sell_price;
	}
	public void setSell_price(double sell_price) {
		this.sell_price = sell_price;
	}
	
	

}
