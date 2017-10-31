package com.manji.backstage.model.oper;

//dt_article_tags（热门文章TAG标签存储信息表）

public class ArticleTags {
	int id;
	String title;
	int is_red;
	int sort_id;
	String add_time;
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
	public int getIs_red() {
		return is_red;
	}
	public void setIs_red(int is_red) {
		this.is_red = is_red;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	
}
