package com.manji.backstage.model.order;


/**
 * 商品类订单详情   dt_order_goods
 * @author Administrator
 *
 */
public class OrderGoods {

	
	long id;
	
	int article_id;
	long order_id;
	int goods_id;
	String goods_no;
	String goods_title;
	String img_url;
	String spec_text;
	double goods_price;
	double real_price;
	int quantity;
	int point;
	int state;
	int is_back;
	long back_order_id;
	int back_quantity;
	int goods_comment;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_no() {
		return goods_no;
	}
	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}
	public String getGoods_title() {
		return goods_title;
	}
	public void setGoods_title(String goods_title) {
		this.goods_title = goods_title;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getSpec_text() {
		return spec_text;
	}
	public void setSpec_text(String spec_text) {
		this.spec_text = spec_text;
	}
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getIs_back() {
		return is_back;
	}
	public void setIs_back(int is_back) {
		this.is_back = is_back;
	}
	public long getBack_order_id() {
		return back_order_id;
	}
	public void setBack_order_id(long back_order_id) {
		this.back_order_id = back_order_id;
	}
	public int getBack_quantity() {
		return back_quantity;
	}
	public void setBack_quantity(int back_quantity) {
		this.back_quantity = back_quantity;
	}
	public int getGoods_comment() {
		return goods_comment;
	}
	public void setGoods_comment(int goods_comment) {
		this.goods_comment = goods_comment;
	}
	
	
	
	
	
	
}
