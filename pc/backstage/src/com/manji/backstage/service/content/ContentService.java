package com.manji.backstage.service.content;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.ActAdvert;
import com.manji.backstage.model.content.ActBusGoods;
import com.manji.backstage.model.content.ActBusShop;
import com.manji.backstage.model.content.ActBusiness;
import com.manji.backstage.model.content.ActCommon;
import com.manji.backstage.model.content.ActIndUser;
import com.manji.backstage.model.content.ActIndex;
import com.manji.backstage.model.content.ActRecUser;
import com.manji.backstage.model.content.ActRecherge;
import com.manji.backstage.model.content.ActShop;
import com.manji.backstage.model.content.ActShopGoods;
import com.manji.backstage.model.content.ActSms;
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
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.model.content.Channel;
import com.manji.backstage.model.content.ChannelField;
import com.manji.backstage.model.content.ChannelSite;
import com.manji.backstage.model.content.ChannelSpec;
import com.manji.backstage.vo.content.ActAdvertVo;
import com.manji.backstage.vo.content.ActBusGoodsVo;
import com.manji.backstage.vo.content.ActBusShopVo;
import com.manji.backstage.vo.content.ActBusinessVo;
import com.manji.backstage.vo.content.ActCommonVo;
import com.manji.backstage.vo.content.ActIndUserVo;
import com.manji.backstage.vo.content.ActIndexVo;
import com.manji.backstage.vo.content.ActRecUserVo;
import com.manji.backstage.vo.content.ActRechergeVo;
import com.manji.backstage.vo.content.ActShopGoodsVo;
import com.manji.backstage.vo.content.ActShopVo;
import com.manji.backstage.vo.content.ActSmsVo;
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
import com.manji.backstage.vo.content.BusiTempVo;
import com.manji.backstage.vo.content.ChannelFieldVo;
import com.manji.backstage.vo.content.ChannelSiteVo;
import com.manji.backstage.vo.content.ChannelSpecVo;
import com.manji.backstage.vo.content.ChannelVo;

public interface ContentService {

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

	Page<BusiTemp> queryBusiTemp(BusiTempVo vo);

	BusiTemp getBusiTemp(int id);

	boolean updBusiTemp(BusiTemp bt);

	void addBusiTemp(BusiTemp bt);

	boolean delBusiTemp(int id);
	
	
	
	
	
	

	Page<ActBusiness> queryActBusiness(ActBusinessVo vo);

	ActBusiness getActBusiness(int id);

	boolean updActBusiness(ActBusiness ab);

	void addActBusiness(ActBusiness ab);

	boolean delActBusiness(int id);

	Page<ActIndex> queryActIndex(ActIndexVo vo);

	ActIndex getActIndex(int id);

	boolean updActIndex(ActIndex ai);

	void addActIndex(ActIndex ai);

	boolean delActIndex(int id);

	Page<ActIndUser> queryActIndUser(ActIndUserVo vo);

	ActIndUser getActIndUser(int id);

	boolean updActIndUser(ActIndUser aiu);

	void addActIndUser(ActIndUser aiu);

	boolean delActIndUser(int id);

	Page<ActCommon> queryActCommon(ActCommonVo vo);

	ActCommon getActCommon(int id);

	boolean updActCommon(ActCommon ac);

	void addActCommon(ActCommon ac);

	boolean delActCommon(int id);

	Page<ActShop> queryActShop(ActShopVo vo);

	ActShop getActShop(int id);

	boolean updActShop(ActShop as);

	void addActShop(ActShop as);

	boolean delActShop(int id);

	Page<ActShopGoods> queryActShopGoods(ActShopGoodsVo vo);

	ActShopGoods getActShopGoods(int id);

	boolean updActShopGoods(ActShopGoods asg);

	void addActShopGoods(ActShopGoods asg);

	boolean delActShopGoods(int id);

	Page<ActSms> queryActSms(ActSmsVo vo);

	ActSms getActSms(int id);

	boolean updActSms(ActSms as);

	void addActSms(ActSms as);

	boolean delActSms(int id);

	Page<ActAdvert> queryActAdvert(ActAdvertVo vo);

	ActAdvert getActAdvert(int id);

	boolean updActAdvert(ActAdvert aa);

	void addActAdvert(ActAdvert aa);

	boolean delActAdvert(int id);

	Page<ActBusShop> queryActBusShop(ActBusShopVo vo);

	ActBusShop getActBusShop(int id);

	boolean updActBusShop(ActBusShop abs);

	void addActBusShop(ActBusShop abs);

	boolean delActBusShop(int id);

	Page<ActBusGoods> queryActBusGoods(ActBusGoodsVo vo);

	ActBusGoods getActBusGoods(int id);

	boolean updActBusGoods(ActBusGoods abg);

	void addActBusGoods(ActBusGoods abg);

	boolean delActBusGoods(int id);

	Page<ActRecherge> queryActRecherge(ActRechergeVo vo);

	ActRecherge getActRecherge(int id);

	boolean updActRecherge(ActRecherge ar);

	void addActRecherge(ActRecherge ar);

	boolean delActRecherge(int id);

	Page<Channel> queryChannel(ChannelVo vo);

	Channel getChannel(int id);

	boolean updChannel(Channel cha);

	void addChannel(Channel cha);

	boolean delChannel(int id);
	
	Page<ChannelField> queryChannelField(ChannelFieldVo vo);
	
	ChannelField getChannelField(int id);
	
	boolean updChannelField(ChannelField cf);
	
	void addChannelField(ChannelField cf);
	
	boolean delChannelField(int id);
	
	Page<ChannelSpec> queryChannelSpec(ChannelSpecVo vo);
	
	ChannelSpec getChannelSpec(int id);
	
	boolean updChannelSpec(ChannelSpec cs);
	
	void addChannelSpec(ChannelSpec cs);
	
	boolean delChannelSpec(int id);
	
	
	Page<ChannelSite> queryChannelSite(ChannelSiteVo vo);
	
	ChannelSite getChannelSite(int id);
	
	boolean updChannelSite(ChannelSite cs);
	
	void addChannelSite(ChannelSite cs);
	
	boolean delChannelSite(int id);
	
	
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
	
	Page<ActRecUser> queryActRecUser(ActRecUserVo vo);
	
	ActRecUser getActRecUser(int id);
	
	boolean updActRecUser(ActRecUser aru);
	
	void addActRecUser(ActRecUser aru);
	
	boolean delActRecUser(int id);
	
	
	

	
}
