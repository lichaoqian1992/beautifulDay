package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

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
@Resource
public interface ArtiMapper {

	

	List<Article> queryArticle(ArticleVo vo);

	int countArticle(ArticleVo vo);

	Article getArticle(int id);

	int updArticle(Article art);

	void addArticle(Article art);

	int delArticle(int id);



	List<ArticleInfo> queryArticleInfo(ArticleInfoVo vo);

	int countArticleInfo(ArticleInfoVo vo);

	ArticleInfo getArticleInfo(int id);

	int updArticleInfo(ArticleInfo ai);

	void addArticleInfo(ArticleInfo ai);

	int delArticleInfo(int id);




	List<ArticleContent> queryArticleContent(ArticleContentVo vo);

	int countArticleContent(ArticleContentVo vo);

	ArticleContent getArticleContent(int id);

	int updArticleContent(ArticleContent ac);

	void addArticleContent(ArticleContent ac);

	int delArticleContent(int id);





	List<ArticleCount> queryArticleCount(ArticleCountVo vo);

	int countArticleCount(ArticleCountVo vo);

	ArticleCount getArticleCount(int id);

	int updArticleCount(ArticleCount ac);

	void addArticleCount(ArticleCount ac);

	int delArticleCount(int id);






	List<ArticleComment> queryArticleComment(ArticleCommentVo vo);

	int countArticleComment(ArticleCommentVo vo);

	ArticleComment getArticleComment(int id);

	int updArticleComment(ArticleComment ac);

	void addArticleComment(ArticleComment ac);

	int delArticleComment(int id);






	List<ArticleAlbums> queryArticleAlbums(ArticleAlbumsVo vo);

	int countArticleAlbums(ArticleAlbumsVo vo);

	ArticleAlbums getArticleAlbums(int id);

	int updArticleAlbums(ArticleAlbums aa);

	void addArticleAlbums(ArticleAlbums aa);

	int delArticleAlbums(int id);






	List<ArticleGoods> queryArticleGoods(ArticleGoodsVo vo);

	int countArticleGoods(ArticleGoodsVo vo);

	ArticleGoods getArticleGoods(int id);

	int updArticleGoods(ArticleGoods ag);

	void addArticleGoods(ArticleGoods ag);

	int delArticleGoods(int id);






	List<ArticleScreen> queryArticleScreen(ArticleScreenVo vo);

	int countArticleScreen(ArticleScreenVo vo);

	ArticleScreen getArticleScreen(long id);

	int updArticleScreen(ArticleScreen as);

	void addArticleScreen(ArticleScreen as);

	int delArticleScreen(int id);

	

	
	List<ArticleCategory> queryArticleCategory(ArticleCategoryVo vo);
	
	int countArticleCategory(ArticleCategoryVo vo);
	
	ArticleCategory getArticleCategory(int id);
	
	int updArticleCategory(ArticleCategory ac);
	
	void addArticleCategory(ArticleCategory ac);
	
	int delArticleCategory(int id);
	
	
	List<ArticleCategoryCount> queryArticleCategoryCount(ArticleCategoryCountVo vo);
	
	int countArticleCategoryCount(ArticleCategoryCountVo vo);
	
	ArticleCategoryCount getArticleCategoryCount(int id);
	
	int updArticleCategoryCount(ArticleCategoryCount aru);
	
	void addArticleCategoryCount(ArticleCategoryCount aru);
	
	int delArticleCategoryCount(int id);
	
	
	List<ArticleCategoryUser> queryArticleCategoryUser(ArticleCategoryUserVo vo);
	
	int countArticleCategoryUser(ArticleCategoryUserVo vo);
	
	ArticleCategoryUser getArticleCategoryUser(int id);
	
	int updArticleCategoryUser(ArticleCategoryUser acu);
	
	void addArticleCategoryUser(ArticleCategoryUser acu);
	
	int delArticleCategoryUser(int id);
	
 	
	List<ArticleCategoryField> queryArticleCategoryField(ArticleCategoryFieldVo vo);
	
	int countArticleCategoryField(ArticleCategoryFieldVo vo);
	
	ArticleCategoryField getArticleCategoryField(int id);
	
	int updArticleCategoryField(ArticleCategoryField acf);
	
	void addArticleCategoryField(ArticleCategoryField acf);
	
	int delArticleCategoryField(int id);
	
	
	List<ArticleCategorySpec> queryArticleCategorySpec(ArticleCategorySpecVo vo);
	
	int countArticleCategorySpec(ArticleCategorySpecVo vo);
	
	ArticleCategorySpec getArticleCategorySpec(int id);
	
	int updArticleCategorySpec(ArticleCategorySpec acs);
	
	void addArticleCategorySpec(ArticleCategorySpec acs);
	
	int delArticleCategorySpec(int id);
	

	////////////////////////////////////////
	
	int countOrderCommentFalse(OrderCommentFalseVo vo);
	
	List<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo);
	
	void addOrderCommentFalse(OrderCommentFalse ocf);
	
	OrderCommentFalse getOrderCommentFalse(int id);
	
	int updOrderCommentFalse(OrderCommentFalse ocf);
	
	int delOrderCommentFalse(int id);
	
	////////////////////////////////////////
	
	int countOrderCommentTem(OrderCommentTemVo vo);
	
	List<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo);
	
	void addOrderCommentTem(OrderCommentTem oct);
	
	OrderCommentTem getOrderCommentTem(int id);
	
	int updOrderCommentTem(OrderCommentTem oct);
	
	int delOrderCommentTem(int id);
	
	////////////////////////////////////////
	
	
	
	
	
}
