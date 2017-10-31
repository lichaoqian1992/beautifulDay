package com.manji.backstage.mapper.content;

import java.util.List;

import javax.annotation.Resource;

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
@Resource
public interface ContentMapper {

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

	
	
	List<BusiTemp> queryBusiTemp(BusiTempVo vo);

	int countBusiTemp(BusiTempVo vo);

	BusiTemp getBusiTemp(int id);

	int updBusiTemp(BusiTemp bt);

	void addBusiTemp(BusiTemp bt);

	int delBusiTemp(int id);

	List<ActBusiness> queryActBusiness(ActBusinessVo vo);

	int countActBusiness(ActBusinessVo vo);

	ActBusiness getActBusiness(int id);

	int updActBusiness(ActBusiness ab);

	void addActBusiness(ActBusiness ab);

	int delActBusiness(int id);

	List<ActIndex> queryActIndex(ActIndexVo vo);

	int countActIndex(ActIndexVo vo);

	ActIndex getActIndex(int id);

	int updActIndex(ActIndex ai);

	void addActIndex(ActIndex ai);

	int delActIndex(int id);

	List<ActIndUser> queryActIndUser(ActIndUserVo vo);

	int countActIndUser(ActIndUserVo vo);

	ActIndUser getActIndUser(int id);

	int updActIndUser(ActIndUser aiu);

	void addActIndUser(ActIndUser aiu);

	int delActIndUser(int id);

	List<ActCommon> queryActCommon(ActCommonVo vo);

	int countActCommon(ActCommonVo vo);

	ActCommon getActCommon(int id);

	int updActCommon(ActCommon ac);

	void addActCommon(ActCommon ac);

	int delActCommon(int id);

	List<ActShop> queryActShop(ActShopVo vo);

	int countActShop(ActShopVo vo);

	ActShop getActShop(int id);

	int updActShop(ActShop as);

	void addActShop(ActShop as);

	int delActShop(int id);

	List<ActShopGoods> queryActShopGoods(ActShopGoodsVo vo);

	int countActShopGoods(ActShopGoodsVo vo);

	ActShopGoods getActShopGoods(int id);

	int updActShopGoods(ActShopGoods asg);

	void addActShopGoods(ActShopGoods asg);

	int delActShopGoods(int id);

	List<ActSms> queryActSms(ActSmsVo vo);

	int countActSms(ActSmsVo vo);

	ActSms getActSms(int id);

	int updActSms(ActSms as);

	void addActSms(ActSms as);

	int delActSms(int id);

	List<ActAdvert> queryActAdvert(ActAdvertVo vo);

	int countActAdvert(ActAdvertVo vo);

	ActAdvert getActAdvert(int id);

	boolean updActAdvert(ActAdvert aa);

	void addActAdvert(ActAdvert aa);

	int delActAdvert(int id);

	List<ActBusShop> queryActBusShop(ActBusShopVo vo);

	int countActBusShop(ActBusShopVo vo);

	ActBusShop getActBusShop(int id);

	int updActBusShop(ActBusShop abs);

	void addActBusShop(ActBusShop abs);

	int delActBusShop(int id);

	List<ActBusGoods> queryActBusGoods(ActBusGoodsVo vo);

	int countActBusGoods(ActBusGoodsVo vo);

	ActBusGoods getActBusGoods(int id);

	int updActBusGoods(ActBusGoods abg);

	void addActBusGoods(ActBusGoods abg);

	int delActBusGoods(int id);

	List<ActRecherge> queryActRecherge(ActRechergeVo vo);

	int countActRecherge(ActRechergeVo vo);

	ActRecherge getActRecherge(int id);

	int updActRecherge(ActRecherge ar);

	void addActRecherge(ActRecherge ar);

	int delActRecherge(int id);
	
	List<Channel> queryChannel(ChannelVo vo);

	int countChannel(ChannelVo vo);
	
	Channel getChannel(int id);
	
	int updChannel(Channel cha);

	void addChannel(Channel cha);
	
	int delChannel(int id);

	
	List<ChannelField> queryChannelField(ChannelFieldVo vo);
	
	int countChannelField(ChannelFieldVo vo);
	
	ChannelField getChannelField(int id);
	
	int updChannelField(ChannelField cf);
	
	void addChannelField(ChannelField cf);
	
	int delChannelField(int id);
	
	
	List<ChannelSpec> queryChannelSpec(ChannelSpecVo vo);
	
	int countChannelSpec(ChannelSpecVo vo);
	
	ChannelSpec getChannelSpec(int id);
	
	int updChannelSpec(ChannelSpec cs);
	
	void addChannelSpec(ChannelSpec cs);
	
	int delChannelSpec(int id);
	
	
	List<ChannelSite> queryChannelSite(ChannelSiteVo vo);
	
	int countChannelSite(ChannelSiteVo vo);
	
	ChannelSite getChannelSite(int id);
	
	int updChannelSite(ChannelSite cs);
	
	void addChannelSite(ChannelSite cs);
	
	int delChannelSite(int id);
	
	
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
	
	
	List<ActRecUser> queryActRecUser(ActRecUserVo vo);
	
	int countActRecUser(ActRecUserVo vo);
	
	ActRecUser getActRecUser(int id);
	
	int updActRecUser(ActRecUser aru);
	
	void addActRecUser(ActRecUser aru);
	
	int delActRecUser(int id);
	




	
	
	

}
