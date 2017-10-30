package com.manji.backstage.model.oper;

//dt_article_groupshop（集团店批量操作记录表）

public class ArticleGroupshop {
	int id;
	int groupshop_id;
	int shop_id;
	int groupshop_article_id;
	int article_id;
	int is_del;
	public int getId() {
		return id;
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
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	
}
