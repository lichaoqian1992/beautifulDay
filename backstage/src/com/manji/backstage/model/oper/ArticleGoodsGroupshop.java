package com.manji.backstage.model.oper;

//dt_article_goods_groupshop（集团店商品规格批量操作记录表）

public class ArticleGoodsGroupshop {
	int id;
	int groupshop_id;
	int shop_id;
	int groupshop_article_id;
	int groupshop_goods_id;
	int shop_article_id;
	int shop_goods_id;
	int is_del;
	public int getId() {
		return id;
	}
	
	public int getShop_article_id() {
		return shop_article_id;
	}

	public void setShop_article_id(int shop_article_id) {
		this.shop_article_id = shop_article_id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getGroupshop_id() {
		return groupshop_id;
	}
	public void setGroupshop_id(int groupshop_id) {
		this.groupshop_id = groupshop_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getGroupshop_article_id() {
		return groupshop_article_id;
	}
	public void setGroupshop_article_id(int groupshop_article_id) {
		this.groupshop_article_id = groupshop_article_id;
	}
	public int getGroupshop_goods_id() {
		return groupshop_goods_id;
	}
	public void setGroupshop_goods_id(int groupshop_goods_id) {
		this.groupshop_goods_id = groupshop_goods_id;
	}
	public int getShop_goods_id() {
		return shop_goods_id;
	}
	public void setShop_goods_id(int shop_goods_id) {
		this.shop_goods_id = shop_goods_id;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
