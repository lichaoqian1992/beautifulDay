package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.Channel;
import com.manji.backstage.model.content.ChannelField;
import com.manji.backstage.model.content.ChannelSite;
import com.manji.backstage.model.content.ChannelSpec;
import com.manji.backstage.model.oper.ApoCity;
import com.manji.backstage.model.oper.ApoCounty;
import com.manji.backstage.model.oper.ApoProvince;
import com.manji.backstage.model.oper.ApoScreen;
import com.manji.backstage.model.oper.ApoSfz;
import com.manji.backstage.model.oper.ApoSite;
import com.manji.backstage.model.oper.ApoSiteContent;
import com.manji.backstage.model.oper.ApoTown;
import com.manji.backstage.model.oper.ApoVillage;
import com.manji.backstage.model.oper.Category;
import com.manji.backstage.model.property.Banktype;
import com.manji.backstage.model.property.Config;
import com.manji.backstage.model.property.Distribution;
import com.manji.backstage.model.property.EmailTemplate;
import com.manji.backstage.model.property.Express;
import com.manji.backstage.model.property.Groups;
import com.manji.backstage.model.property.MbArticle;
import com.manji.backstage.model.property.MbCategory;
import com.manji.backstage.model.property.MbChannelMatter;
import com.manji.backstage.model.property.MbGoodsMatter;
import com.manji.backstage.model.property.MbImages;
import com.manji.backstage.model.property.MbShopMatter;
import com.manji.backstage.model.property.MbYewu;
import com.manji.backstage.model.property.Medal;
import com.manji.backstage.model.property.Payment;
import com.manji.backstage.model.property.Scorerule;
import com.manji.backstage.model.property.SmsTemplate;
import com.manji.backstage.model.property.UserOauthApp;
import com.manji.backstage.model.shop.Business;
import com.manji.backstage.vo.content.ChannelFieldVo;
import com.manji.backstage.vo.content.ChannelSiteVo;
import com.manji.backstage.vo.content.ChannelSpecVo;
import com.manji.backstage.vo.content.ChannelVo;
import com.manji.backstage.vo.operation.ApoCityVo;
import com.manji.backstage.vo.operation.ApoCountyVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;
import com.manji.backstage.vo.operation.ApoScreenVo;
import com.manji.backstage.vo.operation.ApoSfzVo;
import com.manji.backstage.vo.operation.ApoSiteContentVo;
import com.manji.backstage.vo.operation.ApoSiteVo;
import com.manji.backstage.vo.operation.ApoTownVo;
import com.manji.backstage.vo.operation.ApoVillageVo;
import com.manji.backstage.vo.property.BanktypeVo;
import com.manji.backstage.vo.property.ConfigVo;
import com.manji.backstage.vo.property.DistributionVo;
import com.manji.backstage.vo.property.EmailTemplateVo;
import com.manji.backstage.vo.property.ExpressVo;
import com.manji.backstage.vo.property.GroupsVo;
import com.manji.backstage.vo.property.MbArticleVo;
import com.manji.backstage.vo.property.MbCategoryVo;
import com.manji.backstage.vo.property.MbChannelMatterVo;
import com.manji.backstage.vo.property.MbGoodsMatterVo;
import com.manji.backstage.vo.property.MbImagesVo;
import com.manji.backstage.vo.property.MbShopMatterVo;
import com.manji.backstage.vo.property.MbYewuVo;
import com.manji.backstage.vo.property.MedalVo;
import com.manji.backstage.vo.property.PaymentVo;
import com.manji.backstage.vo.property.ScoreruleVo;
import com.manji.backstage.vo.property.SmsTemplateVo;
import com.manji.backstage.vo.property.UserOauthAppVo;
import com.manji.backstage.vo.shop.BusinessVo;

public interface PropService {

	/////////////////////////////////////////////////

	Page<SmsTemplate> querySmsTemplate(SmsTemplateVo vo);

	SmsTemplate getSmsTemplate(int id);

	boolean updSmsTemplate(SmsTemplate st);

	boolean delSmsTemplate(int id);

	void addSmsTemplate(SmsTemplate st);

	/////////////////////////////////////////////////

	Page<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo);

	EmailTemplate getEmailTemplate(int id);

	boolean updEmailTemplate(EmailTemplate et);

	boolean delEmailTemplate(int id);

	void addEmailTemplate(EmailTemplate et);

	///////////////////////////////////////////////////////////////////////////////////////////////

	void addConfig(Config conf);

	Config getConfig(int id);

	boolean updConfig(Config conf);

	boolean delConfig(int id);

	Page<Config> queryConfig(ConfigVo vo);
	
	List<Config> getConfigCategory();

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Distribution> queryDistribution(DistributionVo vo);

	void addDistribution(Distribution dist);

	Distribution getDistribution(int id);

	boolean updDistribution(Distribution dist);

	boolean delDistribution(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<Express> queryExpress(ExpressVo vo);

	void addExpress(Express ex);

	Express getExpress(int id);

	boolean updExpress(Express ex);

	boolean delExpress(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	Page<Payment> queryPayment(PaymentVo vo);

	void addPayment(Payment pay);

	Payment getPayment(int id);

	boolean updPayment(Payment pay);

	boolean delPayment(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	Page<Medal> queryMedal(MedalVo vo);

	void addMedal(Medal medal);

	Medal getMedal(int id);

	boolean updMedal(Medal medal);

	boolean delMedal(int id);

	///////////////////////////////////////////////
	// dt_business_type 系统业务库

	Page<Business> queryBusiness(BusinessVo vo);

	void addBusiness(Business bus);

	Business getBusiness(int id);

	boolean updBusiness(Business bus);

	boolean delBusiness(int id);

	// 地理信息

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoProvince> queryApoProvince(ApoProvinceVo vo);

	void addApoProvince(ApoProvince ap);

	ApoProvince getApoProvince(String province_id);

	boolean updApoProvince(ApoProvince ap);

	boolean delApoProvince(String province_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoCity> queryApoCity(ApoCityVo vo);

	void addApoCity(ApoCity ac);

	ApoCity getApoCity(String city_id);

	boolean updApoCity(ApoCity ac);

	boolean delApoCity(String city_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoCounty> queryApoCounty(ApoCountyVo vo);

	void addApoCounty(ApoCounty ac);

	ApoCounty getApoCounty(String county_id);

	boolean updApoCounty(ApoCounty ac);

	boolean delApoCounty(String county_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoTown> queryApoTown(ApoTownVo vo);

	void addApoTown(ApoTown at);

	ApoTown getApoTown(String town_id);

	boolean updApoTown(ApoTown at);

	boolean delApoTown(String town_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoVillage> queryApoVillage(ApoVillageVo vo);

	void addApoVillage(ApoVillage av);

	ApoVillage getApoVillage(String code);

	boolean updApoVillage(ApoVillage av);

	boolean delApoVillage(String code);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoProvince> findProvince();

	List<ApoCity> findCityByProvince(String province_id);

	List<ApoCounty> findCountyByCity(String city_id);

	List<ApoTown> findTownByCounty(String town_id);

	List<ApoVillage> findVillageByTown(String village_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	Page<ApoCity> findCityByProvinceId(ApoCityVo vo);

	Page<ApoCounty> findCountyByCityId(ApoCountyVo vo);

	Page<ApoTown> findTownByCountyId(ApoTownVo vo);

	Page<ApoVillage> findVillageByTownId(ApoVillageVo vo);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<ApoSfz> queryApoSfz(ApoSfzVo vo);
	
	void addApoSfz(ApoSfz av);
	
	ApoSfz getApoSfz(String code);
	
	boolean updApoSfz(ApoSfz av);
	
	boolean delApoSfz(String code);
	
	
	
	/////////////////////////////////////////////////////////////////

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<ApoSite> queryApoSite(ApoSiteVo vo);
	
	void addApoSite(ApoSite as);
	
	ApoSite getApoSite(int id);
	
	boolean updApoSite(ApoSite as);
	
	boolean delApoSite(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<ApoSiteContent> queryApoSiteContent(ApoSiteContentVo vo);
	
	void addApoSiteContent(ApoSiteContent asc);
	
	ApoSiteContent getApoSiteContent(int id);
	
	boolean updApoSiteContent(ApoSiteContent asc);
	
	boolean delApoSiteContent(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<ApoScreen> queryApoScreen(ApoScreenVo vo);
	
	void addApoScreen(ApoScreen as);
	
	ApoScreen getApoScreen(int id);
	
	boolean updApoScreen(ApoScreen as);
	
	boolean delApoScreen(int id);
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
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
	
	
	////////////////////////////////////////////

	List<Category> getFirstCategory();
	
	List<Category> getSecondCategory(int id);
	
	List<Category> getThirdCategory(int id);


	//dt_mb_category(用户自定义分类模板表)
	
	Page<MbCategory> queryMbCategory(MbCategoryVo vo);
	
	MbCategory getMbCategory(int id);
	
	boolean updMbCategory(MbCategory si);
	
	void addMbCategory(MbCategory si);
	
	boolean delMbCategory(int id);
	
	//dt_mb_article(用户发布内容模板表)
	
	Page<MbArticle> queryMbArticle(MbArticleVo vo);
	
	MbArticle getMbArticle(int id);
	
	boolean updMbArticle(MbArticle si);
	
	void addMbArticle(MbArticle si);
	
	boolean delMbArticle(int id);
	
	////dt_mb_images(内容所用图片模板表)
	
	Page<MbImages> queryMbImages(MbImagesVo vo);
	
	MbImages getMbImages(int id);
	
	boolean updMbImages(MbImages si);
	
	void addMbImages(MbImages si);
	
	boolean delMbImages(int id);
	
	//dt_mb_yewu(分类所用业务模板表)
	
	Page<MbYewu> queryMbYewu(MbYewuVo vo);
	
	MbYewu getMbYewu(int id);
	
	boolean updMbYewu(MbYewu si);
	
	void addMbYewu(MbYewu si);
	
	boolean delMbYewu(int id);
	
	//dt_mb_goods_matter(用户发布内容购买须知等模板表)
	
	Page<MbGoodsMatter> queryMbGoodsMatter(MbGoodsMatterVo vo);
	
	MbGoodsMatter getMbGoodsMatter(int id);
	
	boolean updMbGoodsMatter(MbGoodsMatter si);
	
	void addMbGoodsMatter(MbGoodsMatter si);
	
	boolean delMbGoodsMatter(int id);
	
	//dt_mb_shop_matter(店铺总须知模板表)
	
	Page<MbShopMatter> queryMbShopMatter(MbShopMatterVo vo);
	
	MbShopMatter getMbShopMatter(int id);
	
	boolean updMbShopMatter(MbShopMatter si);
	
	void addMbShopMatter(MbShopMatter si);
	
	boolean delMbShopMatter(int id);
	
	//dt_mb_channel_matter(店铺所用各业务频道须知等模板表)
	
	Page<MbChannelMatter> queryMbChannelMatter(MbChannelMatterVo vo);
	
	MbChannelMatter getMbChannelMatter(int id);
	
	boolean updMbChannelMatter(MbChannelMatter si);
	
	void addMbChannelMatter(MbChannelMatter si);
	
	boolean delMbChannelMatter(int id);

	//dt_banktype（支持绑定银行卡/三方账户类型信息表）
	
	Page<Banktype> queryBanktype(BanktypeVo vo);
	
	Banktype getBanktype(int id);
	
	boolean updBanktype(Banktype si);
	
	void addBanktype(Banktype si);
	
	boolean delBanktype(int id);
	
	//dt_user_oauth_app（用户第三方登录类型信息表）
	
	Page<UserOauthApp> queryUserOauthApp(UserOauthAppVo vo);
	
	UserOauthApp getUserOauthApp(int id);
	
	boolean updUserOauthApp(UserOauthApp si);
	
	void addUserOauthApp(UserOauthApp si);
	
	boolean delUserOauthApp(int id);
	
	//dt_groups(系统分组信息表)
	
	Page<Groups> queryGroups(GroupsVo vo);
	
	Groups getGroups(int id);
	
	boolean updGroups(Groups si);
	
	void addGroups(Groups si);
	
	boolean delGroups(int id);
	
	//信誉规则
	Page<Scorerule> queryRules(ScoreruleVo vo);
	Scorerule getRules(int id);
	boolean updRules(Scorerule si);
	void addRules(Scorerule si);
	boolean delRules(int id);

}
