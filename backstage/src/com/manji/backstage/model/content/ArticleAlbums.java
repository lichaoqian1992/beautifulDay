package com.manji.backstage.model.content;

public class ArticleAlbums {

	int id;
	int article_id;
	String thumb_path;
	String original_path;
	String remark;
	String add_time;
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
	public String getThumb_path() {
		return thumb_path;
	}
	public void setThumb_path(String thumb_path) {
		this.thumb_path = thumb_path;
	}
	public String getOriginal_path() {
		return original_path;
	}
	public void setOriginal_path(String original_path) {
		this.original_path = original_path;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	
}
