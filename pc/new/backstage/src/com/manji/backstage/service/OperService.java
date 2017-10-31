package com.manji.backstage.service;

import java.util.List;

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
import com.manji.backstage.model.content.ArticleComment;
import com.manji.backstage.model.content.ArticleContent;
import com.manji.backstage.model.content.ArticleCount;
import com.manji.backstage.model.content.ArticleGoods;
import com.manji.backstage.model.content.ArticleInfo;
import com.manji.backstage.model.content.ArticleScreen;
import com.manji.backstage.model.oper.Advert;
import com.manji.backstage.model.oper.AdvertBanner;
import com.manji.backstage.model.oper.AdvertContent;
import com.manji.backstage.model.oper.AdvertNavigation;
import com.manji.backstage.model.oper.AppVersion;
import com.manji.backstage.model.oper.ArticleAttributeField;
import com.manji.backstage.model.oper.ArticleAttributeValue;
import com.manji.backstage.model.oper.ArticleGoodsGroupshop;
import com.manji.backstage.model.oper.ArticleGoodsSpec;
import com.manji.backstage.model.oper.ArticleGroupshop;
import com.manji.backstage.model.oper.ArticleTags;
import com.manji.backstage.model.oper.ArticleTagsRelation;
import com.manji.backstage.model.oper.ChannelTagsRelation;
import com.manji.backstage.model.oper.Menu;
import com.manji.backstage.model.oper.Navigation;
import com.manji.backstage.model.oper.QrCode;
import com.manji.backstage.model.oper.Screen;
import com.manji.backstage.service.OperService;
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
import com.manji.backstage.vo.content.ArticleCommentVo;
import com.manji.backstage.vo.content.ArticleContentVo;
import com.manji.backstage.vo.content.ArticleCountVo;
import com.manji.backstage.vo.content.ArticleGoodsVo;
import com.manji.backstage.vo.content.ArticleInfoVo;
import com.manji.backstage.vo.content.ArticleScreenVo;
import com.manji.backstage.vo.content.ArticleVo;
import com.manji.backstage.vo.operation.AdvertBannerVo;
import com.manji.backstage.vo.operation.AdvertContentVo;
import com.manji.backstage.vo.operation.AdvertNavigationVo;
import com.manji.backstage.vo.operation.AdvertVo;
import com.manji.backstage.vo.operation.AppVersionVo;
import com.manji.backstage.vo.operation.ArticleAttributeFieldVo;
import com.manji.backstage.vo.operation.ArticleAttributeValueVo;
import com.manji.backstage.vo.operation.ArticleGoodsGroupshopVo;
import com.manji.backstage.vo.operation.ArticleGoodsSpecVo;
import com.manji.backstage.vo.operation.ArticleGroupshopVo;
import com.manji.backstage.vo.operation.ArticleTagsRelationVo;
import com.manji.backstage.vo.operation.ArticleTagsVo;
import com.manji.backstage.vo.operation.ChannelTagsRelationVo;
import com.manji.backstage.vo.operation.MenuVo;
import com.manji.backstage.vo.operation.NavigationVo;
import com.manji.backstage.vo.operation.QrCodeVo;
import com.manji.backstage.vo.operation.ScreenVo;

public interface OperService {

	Page<AppVersion> queryAppVersion(AppVersionVo vo);

	void addAppVersion(AppVersion av);

	AppVersion getAppVersion(int id);

	boolean updAppVersion(AppVersion av);

	boolean delAppVersion(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<QrCode> queryQrCode(QrCodeVo vo);

	void addQrCode(QrCode qc);

	QrCode getQrCode(int id);

	boolean updQrCode(QrCode qc);

	boolean delQrCode(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Menu> queryMenu(MenuVo vo);

	void addMenu(Menu menu);

	Menu getMenu(int id);

	boolean updMenu(Menu menu);

	boolean delMenu(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Screen> queryScreen(ScreenVo vo);

	void addScreen(Screen screen);

	Screen getScreen(int id);

	boolean updScreen(Screen screen);

	boolean delScreen(int id);

	//查询广告类型
	List<AdvertNavigation> getAdvertType();
	
	//根据parent_id查询title
	String getTitle(int parent_id);
	///////////////////////////////////////////////////////////////////////////////////////////////

	//根据title模糊查询
	List<AdvertNavigation> getTitleId(String title);
	
	Page<AdvertNavigation> queryAdvertNavigation(AdvertNavigationVo vo);

	void addAdvertNavigation(AdvertNavigation an);

	AdvertNavigation getAdvertNavigation(int id);

	boolean updAdvertNavigation(AdvertNavigation an);
	//000000000000000000
	boolean updAdvertNavigation2(AdvertNavigation an);
	
	//00000000000
	
	boolean delAdvertNavigation(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Advert> queryAdvert(AdvertVo vo);

	void addAdvert(Advert advert);

	Advert getAdvert(int id);

	boolean updAdvert(Advert advert);

	boolean delAdvert(int id);

	////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Navigation> queryNavigation(NavigationVo vo);

	void addNavigation(Navigation navigation);

	Navigation getNavigation(int id);

	boolean updNavigation(Navigation navigation);

	boolean delNavigation(int id);

	

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

	Page<ActRecUser> queryActRecUser(ActRecUserVo vo);

	ActRecUser getActRecUser(int id);

	boolean updActRecUser(ActRecUser aru);

	void addActRecUser(ActRecUser aru);

	boolean delActRecUser(int id);


	//dt_advert_banner（广告位购买信息表）
	
	Page<AdvertBanner> queryAdvertBanner(AdvertBannerVo vo);
	
	AdvertBanner getAdvertBanner(int id);
	
	boolean updAdvertBanner(AdvertBanner si);
	
	void addAdvertBanner(AdvertBanner si);
	
	boolean delAdvertBanner(int id);
	
	//dt_advert_content(广告位放置内容表)
	
	Page<AdvertContent> queryAdvertContent(AdvertContentVo vo);
	
	AdvertContent getAdvertContent(int id);
	
	boolean updAdvertContent(AdvertContent si);
	
	void addAdvertContent(AdvertContent si);
	
	boolean delAdvertContent(int id);
	
	//dt_article_attribute_field（内容扩展属性表）
	
	Page<ArticleAttributeField> queryArticleAttributeField(ArticleAttributeFieldVo vo);
	
	ArticleAttributeField getArticleAttributeField(int id);
	
	boolean updArticleAttributeField(ArticleAttributeField si);
	
	void addArticleAttributeField(ArticleAttributeField si);
	
	boolean delArticleAttributeField(int id);
	
	//dt_article_attribute_value（内容扩展属性对应值记录表*会动态更新）
	
	Page<ArticleAttributeValue> queryArticleAttributeValue(ArticleAttributeValueVo vo);
	
	ArticleAttributeValue getArticleAttributeValue(int id);
	
	boolean updArticleAttributeValue(ArticleAttributeValue si);
	
	void addArticleAttributeValue(ArticleAttributeValue si);
	
	boolean delArticleAttributeValue(int id);
	
	//dt_article_goods_spec（文章派生的商品类所选则规格信息表）
	
	Page<ArticleGoodsSpec> queryArticleGoodsSpec(ArticleGoodsSpecVo vo);
	
	ArticleGoodsSpec getArticleGoodsSpec(int id);
	
	boolean updArticleGoodsSpec(ArticleGoodsSpec si);
	
	void addArticleGoodsSpec(ArticleGoodsSpec si);
	
	boolean delArticleGoodsSpec(int id);
	
	//dt_article_groupshop（集团店批量操作记录表）
	
	Page<ArticleGroupshop> queryArticleGroupshop(ArticleGroupshopVo vo);
	
	ArticleGroupshop getArticleGroupshop(int id);
	
	boolean updArticleGroupshop(ArticleGroupshop si);
	
	void addArticleGroupshop(ArticleGroupshop si);
	
	boolean delArticleGroupshop(int id);
	
	//dt_article_goods_groupshop（集团店商品规格批量操作记录表）
	
	Page<ArticleGoodsGroupshop> queryArticleGoodsGroupshop(ArticleGoodsGroupshopVo vo);
	
	ArticleGoodsGroupshop getArticleGoodsGroupshop(int id);
	
	boolean updArticleGoodsGroupshop(ArticleGoodsGroupshop si);
	
	void addArticleGoodsGroupshop(ArticleGoodsGroupshop si);
	
	boolean delArticleGoodsGroupshop(int id);
	
	//dt_article_tags（热门文章TAG标签存储信息表）
	
	Page<ArticleTags> queryArticleTags(ArticleTagsVo vo);
	
	ArticleTags getArticleTags(int id);
	
	boolean updArticleTags(ArticleTags si);
	
	void addArticleTags(ArticleTags si);
	
	boolean delArticleTags(int id);
	
	//dt_article_tags_relation（热门文章TAG标签对应关系信息表）
	
	Page<ArticleTagsRelation> queryArticleTagsRelation(ArticleTagsRelationVo vo);
	
	ArticleTagsRelation getArticleTagsRelation(int id);
	
	boolean updArticleTagsRelation(ArticleTagsRelation si);
	
	void addArticleTagsRelation(ArticleTagsRelation si);
	
	boolean delArticleTagsRelation(int id);
	
	//dt_channel_tags_relation（热门频道TAG标签对应关系信息表）
	
	Page<ChannelTagsRelation> queryChannelTagsRelation(ChannelTagsRelationVo vo);
	
	ChannelTagsRelation getChannelTagsRelation(int id);
	
	boolean updChannelTagsRelation(ChannelTagsRelation si);
	
	void addChannelTagsRelation(ChannelTagsRelation si);
	
	boolean delChannelTagsRelation(int id);
	
	
	
	
	
	
	
}
