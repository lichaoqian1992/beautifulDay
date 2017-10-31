package com.manji.backstage.mapper;

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
@Resource
public interface OperMapper {

///////////////////////////////////////////////////////////////////////////////////////////////
	
	
List<AppVersion> queryAppVersion(AppVersionVo vo);

int countAppVersion(AppVersionVo vo);

void addAppVersion(AppVersion av);

AppVersion getAppVersion(int id);

int updAppVersion(AppVersion av);

int delAppVersion(int id);

///////////////////////////////////////////////////////////////////////////////////////////////


List<QrCode> queryQrCode(QrCodeVo vo);

int countQrCode(QrCodeVo vo);

void addQrCode(QrCode qc);

QrCode getQrCode(int id);

int updQrCode(QrCode qc);

int delQrCode(int id);



//////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////


List<Menu> queryMenu(MenuVo vo);

int countMenu(MenuVo vo);

void addMenu(Menu menu);

Menu getMenu(int id);

int updMenu(Menu menu);

int delMenu(int id);

///////////////////////////////////////////////////////////////////////////////////////////////


List<Screen> queryScreen(ScreenVo vo);

int countScreen(ScreenVo vo);

void addScreen(Screen screen);

Screen getScreen(int id);

int updScreen(Screen screen);

int delScreen(int id);

///////////////////////////////////////////////////////////////////////////////////////////////

//获取导航中的类别
List<AdvertNavigation> getAdvertType();

//根据parent_id获取title
String getTitle(int parent_id);

//根据title模糊查询
List<AdvertNavigation> getTitleId(String title);

List<AdvertNavigation> queryAdvertNavigation(AdvertNavigationVo vo);

int countAdvertNavigation(AdvertNavigationVo vo);

void addAdvertNavigation(AdvertNavigation an);

AdvertNavigation getAdvertNavigation(int id);

int updAdvertNavigation(AdvertNavigation an);
//0000000000000000000
int updAdvertNavigation2(AdvertNavigation an);
//000000000000000000
int delAdvertNavigation(int id);

///////////////////////////////////////////////////////////////////////////////////////////////


List<Advert> queryAdvert(AdvertVo vo);

int countAdvert(AdvertVo vo);

void addAdvert(Advert advert);

Advert getAdvert(int id);

int updAdvert(Advert advert);

int delAdvert(int id);


////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////


List<Navigation> queryNavigation(NavigationVo vo);

int countNavigation(NavigationVo vo);

void addNavigation(Navigation navigation);

Navigation getNavigation(int id);

int updNavigation(Navigation navigation);

int delNavigation(int id);

	

/////////////////////////////////////////////////////////////////////////////////////////////////




/////////////////////////////////////////////////////
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




List<ActRecUser> queryActRecUser(ActRecUserVo vo);

int countActRecUser(ActRecUserVo vo);

ActRecUser getActRecUser(int id);

int updActRecUser(ActRecUser aru);

void addActRecUser(ActRecUser aru);

int delActRecUser(int id);



//dt_advert_banner（广告位购买信息表）

List<AdvertBanner> queryAdvertBanner(AdvertBannerVo vo);

int countAdvertBanner(AdvertBannerVo vo);

AdvertBanner getAdvertBanner(int id);

int updAdvertBanner(AdvertBanner art);

void addAdvertBanner(AdvertBanner art);

int delAdvertBanner(int id);


//dt_advert_content(广告位放置内容表)

List<AdvertContent> queryAdvertContent(AdvertContentVo vo);

int countAdvertContent(AdvertContentVo vo);

AdvertContent getAdvertContent(int id);

int updAdvertContent(AdvertContent art);

void addAdvertContent(AdvertContent art);

int delAdvertContent(int id);


//dt_article_attribute_field（内容扩展属性表）

List<ArticleAttributeField> queryArticleAttributeField(ArticleAttributeFieldVo vo);

int countArticleAttributeField(ArticleAttributeFieldVo vo);

ArticleAttributeField getArticleAttributeField(int id);

int updArticleAttributeField(ArticleAttributeField art);

void addArticleAttributeField(ArticleAttributeField art);

int delArticleAttributeField(int id);


//dt_article_attribute_value（内容扩展属性对应值记录表*会动态更新）

List<ArticleAttributeValue> queryArticleAttributeValue(ArticleAttributeValueVo vo);

int countArticleAttributeValue(ArticleAttributeValueVo vo);

ArticleAttributeValue getArticleAttributeValue(int id);

int updArticleAttributeValue(ArticleAttributeValue art);

void addArticleAttributeValue(ArticleAttributeValue art);

int delArticleAttributeValue(int id);


//dt_article_goods_spec（文章派生的商品类所选则规格信息表）

List<ArticleGoodsSpec> queryArticleGoodsSpec(ArticleGoodsSpecVo vo);

int countArticleGoodsSpec(ArticleGoodsSpecVo vo);

ArticleGoodsSpec getArticleGoodsSpec(int article_id);
	
int updArticleGoodsSpec(ArticleGoodsSpec art);

void addArticleGoodsSpec(ArticleGoodsSpec art);

int delArticleGoodsSpec(int article_id);


//dt_article_groupshop（集团店批量操作记录表）

List<ArticleGroupshop> queryArticleGroupshop(ArticleGroupshopVo vo);

int countArticleGroupshop(ArticleGroupshopVo vo);

ArticleGroupshop getArticleGroupshop(int id);

int updArticleGroupshop(ArticleGroupshop art);

void addArticleGroupshop(ArticleGroupshop art);

int delArticleGroupshop(int id);


//dt_article_goods_groupshop（集团店商品规格批量操作记录表）

List<ArticleGoodsGroupshop> queryArticleGoodsGroupshop(ArticleGoodsGroupshopVo vo);

int countArticleGoodsGroupshop(ArticleGoodsGroupshopVo vo);

ArticleGoodsGroupshop getArticleGoodsGroupshop(int id);

int updArticleGoodsGroupshop(ArticleGoodsGroupshop art);

void addArticleGoodsGroupshop(ArticleGoodsGroupshop art);

int delArticleGoodsGroupshop(int id);


//dt_article_tags（热门文章TAG标签存储信息表）

List<ArticleTags> queryArticleTags(ArticleTagsVo vo);

int countArticleTags(ArticleTagsVo vo);

ArticleTags getArticleTags(int id);

int updArticleTags(ArticleTags art);

void addArticleTags(ArticleTags art);

int delArticleTags(int id);


//dt_article_tags_relation（热门文章TAG标签对应关系信息表）

List<ArticleTagsRelation> queryArticleTagsRelation(ArticleTagsRelationVo vo);

int countArticleTagsRelation(ArticleTagsRelationVo vo);

ArticleTagsRelation getArticleTagsRelation(int id);

int updArticleTagsRelation(ArticleTagsRelation art);

void addArticleTagsRelation(ArticleTagsRelation art);

int delArticleTagsRelation(int id);


//dt_channel_tags_relation（热门频道TAG标签对应关系信息表）

List<ChannelTagsRelation> queryChannelTagsRelation(ChannelTagsRelationVo vo);

int countChannelTagsRelation(ChannelTagsRelationVo vo);

ChannelTagsRelation getChannelTagsRelation(int id);

int updChannelTagsRelation(ChannelTagsRelation art);

void addChannelTagsRelation(ChannelTagsRelation art);

int delChannelTagsRelation(int id);




}
