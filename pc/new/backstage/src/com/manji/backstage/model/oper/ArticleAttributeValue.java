package com.manji.backstage.model.oper;

//2.5.15.	dt_article_attribute_value（内容扩展属性对应值记录表*会动态更新）

public class ArticleAttributeValue {
	int article_id;
	/*String sub_title;*/
	String source;
	String author;
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	/*public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}*/
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
