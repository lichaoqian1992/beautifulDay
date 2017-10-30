package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.Article;
import com.manji.backstage.model.content.ArticleAlbums;
import com.manji.backstage.model.content.ArticleCategory;
import com.manji.backstage.model.content.ArticleCategoryCount;
import com.manji.backstage.model.content.ArticleCategoryField;
import com.manji.backstage.model.content.ArticleCategorySpec;
import com.manji.backstage.model.content.ArticleCategoryUser;
import com.manji.backstage.model.content.ArticleComment;
import com.manji.backstage.model.content.ArticleContent;
import com.manji.backstage.model.content.ArticleCount;
import com.manji.backstage.model.content.ArticleGoods;
import com.manji.backstage.model.content.ArticleInfo;
import com.manji.backstage.model.content.ArticleScreen;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.vo.content.ArticleAlbumsVo;
import com.manji.backstage.vo.content.ArticleCategoryCountVo;
import com.manji.backstage.vo.content.ArticleCategoryFieldVo;
import com.manji.backstage.vo.content.ArticleCategorySpecVo;
import com.manji.backstage.vo.content.ArticleCategoryUserVo;
import com.manji.backstage.vo.content.ArticleCategoryVo;
import com.manji.backstage.vo.content.ArticleCommentVo;
import com.manji.backstage.vo.content.ArticleContentVo;
import com.manji.backstage.vo.content.ArticleCountVo;
import com.manji.backstage.vo.content.ArticleGoodsVo;
import com.manji.backstage.vo.content.ArticleInfoVo;
import com.manji.backstage.vo.content.ArticleScreenVo;
import com.manji.backstage.vo.content.ArticleVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;

public interface ArtiService {
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	Page<Article> queryArticle(ArticleVo vo);

	Article getArticle(int id);

	boolean updArticle(Article art);

	void addArticle(Article art);

	boolean delArticle(int id);

	Page<ArticleInfo> queryArticleInfo(ArticleInfoVo vo);

	ArticleInfo getArticleInfo(int id);

	boolean updArticleInfo(ArticleInfo ai);

	void addArticleInfo(ArticleInfo ai);

	boolean delArticleInfo(int id);

	Page<ArticleContent> queryArticleContent(ArticleContentVo vo);

	ArticleContent getArticleContent(int id);

	boolean updArticleContent(ArticleContent ac);

	void addArticleContent(ArticleContent ac);

	boolean delArticleContent(int id);

	Page<ArticleCount> queryArticleCount(ArticleCountVo vo);

	ArticleCount getArticleCount(int id);

	boolean updArticleCount(ArticleCount ac);

	void addArticleCount(ArticleCount ac);

	boolean delAtricleCount(int id);

	Page<ArticleComment> queryArticleComment(ArticleCommentVo vo);

	ArticleComment getArticleComment(int id);

	boolean updArticleComment(ArticleComment ac);

	void addArticleComment(ArticleComment ac);

	boolean delArticleComment(int id);

	Page<ArticleAlbums> queryArticleAlbums(ArticleAlbumsVo vo);

	ArticleAlbums getArticleAlbums(int id);

	boolean updArticleAlbums(ArticleAlbums aa);

	void addArticleAlbums(ArticleAlbums aa);

	boolean delArticleAlbums(int id);

	Page<ArticleGoods> queryArticleGoods(ArticleGoodsVo vo);

	ArticleGoods getArticleGoods(int id);

	boolean updArticleGoods(ArticleGoods ag);

	void addArticleGoods(ArticleGoods ag);

	boolean delArticleGoods(int id);

	Page<ArticleScreen> queryArticleScreen(ArticleScreenVo vo);

	ArticleScreen getArticleScreen(long id);

	boolean updArticleScreen(ArticleScreen as);

	void addArticleScreen(ArticleScreen as);

	boolean delArticleScreen(int id);
	
	
	
	Page<ArticleCategory> queryArticleCategory(ArticleCategoryVo vo);
	
	ArticleCategory getArticleCategory(int id);
	
	boolean updArticleCategory(ArticleCategory ac);
	
	void addArticleCategory(ArticleCategory ac);
	
	boolean delArticleCategory(int id);
	
	Page<ArticleCategoryCount> queryArticleCategoryCount(ArticleCategoryCountVo vo);
	
	ArticleCategoryCount getArticleCategoryCount(int id);
	
	boolean updArticleCategoryCount(ArticleCategoryCount ac);
	
	void addArticleCategoryCount(ArticleCategoryCount ac);
	
	boolean delArticleCategoryCount(int id);
	
	Page<ArticleCategoryUser> queryArticleCategoryUser(ArticleCategoryUserVo vo);
	
	ArticleCategoryUser getArticleCategoryUser(int id);
	
	boolean updArticleCategoryUser(ArticleCategoryUser aru);
	
	void addArticleCategoryUser(ArticleCategoryUser aru);
	
	boolean delArticleCategoryUser(int id);
	
	Page<ArticleCategoryField> queryArticleCategoryField(ArticleCategoryFieldVo vo);
	
	ArticleCategoryField getArticleCategoryField(int id);
	
	boolean updArticleCategoryField(ArticleCategoryField acf);
	
	void addArticleCategoryField(ArticleCategoryField acf);
	
	boolean delArticleCategoryField(int id);
	
	Page<ArticleCategorySpec> queryArticleCategorySpec(ArticleCategorySpecVo vo);
	
	ArticleCategorySpec getArticleCategorySpec(int id);
	
	boolean updArticleCategorySpec(ArticleCategorySpec acs);
	
	void addArticleCategorySpec(ArticleCategorySpec acs);
	
	boolean delArticleCategorySpec(int id);
	
	
	
	///////////////////////////////////////////////////////

	
	Page<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo);
	
	void addOrderCommentFalse(OrderCommentFalse ocf);
	
	OrderCommentFalse getOrderCommentFalse(int id);
	
	boolean updOrderCommentFalse(OrderCommentFalse ocf);
	
	boolean delOrderCommentFalse(int id);
	
	
	Page<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo);
	
	void addOrderCommentTem(OrderCommentTem oct);
	
	OrderCommentTem getOrderCommentTem(int id);
	
	boolean updOrderCommentTem(OrderCommentTem oct);
	
	boolean delOrderCommentTem(int id);
	
	//文件传输
	String sendPostReq(String fileName,String base64Str);
	
	

}
