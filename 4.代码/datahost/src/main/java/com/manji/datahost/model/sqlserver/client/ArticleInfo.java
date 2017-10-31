package com.manji.datahost.model.sqlserver.client;

public class ArticleInfo {
	
	private Integer article_id;
	private Integer category_id;
	private String title;
	private String img_url;
	private String class_list;
	
	public String getClass_list() {
		return class_list;
	}
	public void setClass_list(String class_list) {
		this.class_list = class_list;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
}
