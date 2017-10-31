package com.manji.datahost.model.sqlserver.shop;

import javax.validation.constraints.NotNull;

/**
 * 商品管理
 * @author Administrator
 *
 */
public class GoodsManage {
	
	private String title;
	private Integer category_id;
	private String self_title;
	private String name;
	private String goods_describe;
	private Double sell_price;
	private Integer is_show;
	@NotNull(message = "参数[user_id]不能为空")
	private Integer user_id;
	private String class_list;
	private String category_title;
	private String update_time;
	private Integer article_id;
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCategory_title() {
		return category_title;
	}
	public void setCategory_title(String category_title) {
		this.category_title = category_title;
	}
	public String getClass_list() {
		return class_list;
	}
	public void setClass_list(String class_list) {
		this.class_list = class_list;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getSelf_title() {
		return self_title;
	}
	public void setSelf_title(String self_title) {
		this.self_title = self_title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoods_describe() {
		return goods_describe;
	}
	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}
	public Double getSell_price() {
		return sell_price;
	}
	public void setSell_price(Double sell_price) {
		this.sell_price = sell_price;
	}
	public Integer getIs_show() {
		return is_show;
	}
	public void setIs_show(Integer is_show) {
		this.is_show = is_show;
	}
	
	
}
