package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

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
@Resource
public interface PropMapper {

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<SmsTemplate> querySmsTemplate(SmsTemplateVo vo);

	int countSmsTemplate(SmsTemplateVo vo);

	void addSmsTemplate(SmsTemplate st);

	SmsTemplate getSmsTemplate(int id);

	int updSmsTemplate(SmsTemplate st);

	int delSmsTemplate(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo);

	int countEmailTemplate(EmailTemplateVo vo);

	void addEmailTemplate(EmailTemplate et);

	EmailTemplate getEmailTemplate(int id);

	int updEmailTemplate(EmailTemplate et);

	int delEmailTemplate(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	int countConfig(ConfigVo vo);

	List<Config> queryConfig(ConfigVo vo);

	Config getConfig(int id);

	int updConfig(Config conf);

	int delConfig(int id);

	void addConfig(Config conf);

	List<Config> getConfigCategory();
	///////////////////////////////////////////////////////////////////////////////////////////////

	int countDistribution(DistributionVo vo);

	List<Distribution> queryDistribution(DistributionVo vo);

	void addDistribution(Distribution dist);

	Distribution getDistribution(int id);

	int updDistribution(Distribution dist);

	int delDistribution(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////
	int countExpress(ExpressVo vo);

	List<Express> queryExpress(ExpressVo vo);

	void addExpress(Express ex);

	Express getExpress(int id);

	int updExpress(Express ex);

	int delExpress(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	int countPayment(PaymentVo vo);

	List<Payment> queryPayment(PaymentVo vo);

	void addPayment(Payment pay);

	Payment getPayment(int id);

	int updPayment(Payment pay);

	int delPayment(int id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	int countMedal(MedalVo vo);

	List<Medal> queryMedal(MedalVo vo);

	void addMedal(Medal medal);

	Medal getMedal(int id);

	int updMedal(Medal medal);

	int delMedal(int id);

	//////////////////////////////
	// dt_business_type 系统业务库

	int countBusiness(BusinessVo vo);

	List<Business> queryBusiness(BusinessVo vo);

	void addBusiness(Business bus);

	Business getBusiness(int id);

	int updBusiness(Business bus);

	int delBusiness(int id);

	// 地理信息

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoProvince> queryApoProvince(ApoProvinceVo vo);

	int countApoProvince(ApoProvinceVo vo);

	void addApoProvince(ApoProvince ap);

	ApoProvince getApoProvince(String province_id);

	int updApoProvince(ApoProvince ap);

	int delApoProvince(String province_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoCity> queryApoCity(ApoCityVo vo);

	int countApoCity(ApoCityVo vo);

	void addApoCity(ApoCity ac);

	ApoCity getApoCity(String city_id);

	int updApoCity(ApoCity ac);

	int delApoCity(String city_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoCounty> queryApoCounty(ApoCountyVo vo);

	int countApoCounty(ApoCountyVo vo);

	void addApoCounty(ApoCounty ac);

	ApoCounty getApoCounty(String county_id);

	int updApoCounty(ApoCounty ac);

	int delApoCounty(String county_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoTown> queryApoTown(ApoTownVo vo);

	int countApoTown(ApoTownVo vo);

	void addApoTown(ApoTown at);

	ApoTown getApoTown(String town_id);

	int updApoTown(ApoTown at);

	int delApoTown(String town_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoVillage> queryApoVillage(ApoVillageVo vo);

	int countApoVillage(ApoVillageVo vo);

	void addApoVillage(ApoVillage av);

	ApoVillage getApoVillage(String code);

	int updApoVillage(ApoVillage av);

	int delApoVillage(String code);

	///////////////////////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoProvince> findProvince();

	List<ApoCity> findCityByProvince(String province_id);

	List<ApoCounty> findCountyByCity(String city_id);

	List<ApoTown> findTownByCounty(String town_id);

	List<ApoVillage> findVillageByTown(String village_id);

	///////////////////////////////////////////////////////////////////////////////////////////////

	List<ApoCity> findCityByProvinceId(ApoCityVo vo);

	List<ApoCounty> findCountyByCityId(ApoCountyVo vo);

	List<ApoTown> findTownByCountyId(ApoTownVo vo);

	List<ApoVillage> findVillageByTownId(ApoVillageVo vo);

	///////////////////////////////////////////////////////////////////////////////////////////////

	int countCityByProvinceId(ApoCityVo vo);

	int countCountyByCityId(ApoCountyVo vo);

	int countTownByCountyId(ApoTownVo vo);

	int countVillageByTownId(ApoVillageVo vo);
	
	
	//sfz
	
	
	List<ApoSfz> queryApoSfz(ApoSfzVo vo);
	
	int countApoSfz(ApoSfzVo vo);
	
	void addApoSfz(ApoSfz as);
	
	ApoSfz getApoSfz(String code);
	
	int updApoSfz(ApoSfz as);
	
	int delApoSfz(String code);
	
	
	

	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	List<ApoSite> queryApoSite(ApoSiteVo vo);
	
	int countApoSite(ApoSiteVo vo);
	
	void addApoSite(ApoSite as);
	
	ApoSite getApoSite(int id);
	
	int updApoSite(ApoSite as);
	
	int delApoSite(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	List<ApoSiteContent> queryApoSiteContent(ApoSiteContentVo vo);
	
	int countApoSiteContent(ApoSiteContentVo vo);
	
	void addApoSiteContent(ApoSiteContent asc);
	
	ApoSiteContent getApoSiteContent(int id);
	
	int updApoSiteContent(ApoSiteContent asc);
	
	int delApoSiteContent(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	List<ApoScreen> queryApoScreen(ApoScreenVo vo);
	
	int countApoScreen(ApoScreenVo vo);
	
	void addApoScreen(ApoScreen as);
	
	ApoScreen getApoScreen(int id);
	
	int updApoScreen(ApoScreen as);
	
	int delApoScreen(int id);
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
//	=======================================================
	
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
	
///////////////////////////////////////////////

	List<Category> getGoodsFirstCategory();
	
	List<Category> getCategorysByParentId(int parent_id);
	
	Category getCategoryById(int id);

	

	//dt_mb_category(用户自定义分类模板表)
	
	List<MbCategory> queryMbCategory(MbCategoryVo vo);
	
	int countMbCategory(MbCategoryVo vo);
	
	MbCategory getMbCategory(int id);
	
	int updMbCategory(MbCategory art);
	
	void addMbCategory(MbCategory art);
	
	int delMbCategory(int id);
	
	//dt_mb_article(用户发布内容模板表)
	
	List<MbArticle> queryMbArticle(MbArticleVo vo);
	
	int countMbArticle(MbArticleVo vo);
	
	MbArticle getMbArticle(int id);
	
	int updMbArticle(MbArticle art);
	
	void addMbArticle(MbArticle art);
	
	int delMbArticle(int id);
	
	//dt_mb_images(内容所用图片模板表)
	
	List<MbImages> queryMbImages(MbImagesVo vo);
	
	int countMbImages(MbImagesVo vo);
	
	MbImages getMbImages(int id);
	
	int updMbImages(MbImages art);
	
	void addMbImages(MbImages art);
	
	int delMbImages(int id);
	
	//dt_mb_yewu(分类所用业务模板表)
	
	List<MbYewu> queryMbYewu(MbYewuVo vo);
	
	int countMbYewu(MbYewuVo vo);
	
	MbYewu getMbYewu(int id);
	
	int updMbYewu(MbYewu art);
	
	void addMbYewu(MbYewu art);
	
	int delMbYewu(int id);
	
	//dt_mb_goods_matter(用户发布内容购买须知等模板表)
	
	List<MbGoodsMatter> queryMbGoodsMatter(MbGoodsMatterVo vo);
	
	int countMbGoodsMatter(MbGoodsMatterVo vo);
	
	MbGoodsMatter getMbGoodsMatter(int id);
	
	int updMbGoodsMatter(MbGoodsMatter art);
	
	void addMbGoodsMatter(MbGoodsMatter art);
	
	int delMbGoodsMatter(int id);
	
	//dt_mb_shop_matter(店铺总须知模板表)
	
	List<MbShopMatter> queryMbShopMatter(MbShopMatterVo vo);
	
	int countMbShopMatter(MbShopMatterVo vo);
	
	MbShopMatter getMbShopMatter(int id);
	
	int updMbShopMatter(MbShopMatter art);
	
	void addMbShopMatter(MbShopMatter art);
	
	int delMbShopMatter(int id);
	
	//dt_mb_channel_matter(店铺所用各业务频道须知等模板表)
	
	List<MbChannelMatter> queryMbChannelMatter(MbChannelMatterVo vo);
	
	int countMbChannelMatter(MbChannelMatterVo vo);
	
	MbChannelMatter getMbChannelMatter(int id);
	
	int updMbChannelMatter(MbChannelMatter art);
	
	void addMbChannelMatter(MbChannelMatter art);
	
	int delMbChannelMatter(int id);
	

	
	//dt_banktype（支持绑定银行卡/三方账户类型信息表）
	
	List<Banktype> queryBanktype(BanktypeVo vo);
	
	int countBanktype(BanktypeVo vo);
	
	Banktype getBanktype(int id);
	
	int updBanktype(Banktype art);
	
	void addBanktype(Banktype art);
	
	int delBanktype(int id);
	
	
	//dt_user_oauth_app（用户第三方登录类型信息表）
	
	List<UserOauthApp> queryUserOauthApp(UserOauthAppVo vo);
	
	int countUserOauthApp(UserOauthAppVo vo);
	
	UserOauthApp getUserOauthApp(int id);
	
	int updUserOauthApp(UserOauthApp art);
	
	void addUserOauthApp(UserOauthApp art);
	
	int delUserOauthApp(int id);
	
	
	//2.1.22.	dt_groups(系统分组信息表)
	
	List<Groups> queryGroups(GroupsVo vo);
	
	int countGroups(GroupsVo vo);
	
	Groups getGroups(int id);
	
	int updGroups(Groups art);
	
	void addGroups(Groups art);
	
	int delGroups(int id);
	
	//03.04.10 信誉规则
	List<Scorerule> queryRules(ScoreruleVo vo);
	
	int countRules(ScoreruleVo vo);
	
	Scorerule getRules(int id);
	
	int updRules(Scorerule art);
	
	void addRules(Scorerule art);
	
	int delRules(int id);
	
	
	
}
