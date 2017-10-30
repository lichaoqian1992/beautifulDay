package com.manji.backstage.model.oper;

//dt_article_tags_relation（热门文章TAG标签对应关系信息表）

public class ArticleTagsRelation {
	int id;
	String article_id;
	int tag_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArticle_id() {
		return article_id;
	}
	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	
}
