package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.PropMapper;
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
import com.manji.backstage.service.PropService;
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

@Service
public class PropServiceImpl implements PropService {
	@Autowired
	private PropMapper mapper;

	@Override
	public Page<SmsTemplate> querySmsTemplate(SmsTemplateVo vo) {

		Page<SmsTemplate> page = new Page<SmsTemplate>();
		if (vo.getIndex() == 0 || "".equals(vo.getIndex())) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countSmsTemplate(vo);
		List<SmsTemplate> dataList = mapper.querySmsTemplate(vo);

		page.transform(count, dataList);

		return page;
	}

	@Override
	public void addSmsTemplate(SmsTemplate st) {
		mapper.addSmsTemplate(st);
	}

	@Override
	public SmsTemplate getSmsTemplate(int id) {
		return mapper.getSmsTemplate(id);
	}

	@Override
	public boolean updSmsTemplate(SmsTemplate st) {
		if (mapper.updSmsTemplate(st) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delSmsTemplate(int id) {
		if (mapper.delSmsTemplate(id) > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addEmailTemplate(EmailTemplate et) {
		mapper.addEmailTemplate(et);
	}

	@Override
	public EmailTemplate getEmailTemplate(int id) {
		return mapper.getEmailTemplate(id);
	}

	@Override
	public boolean updEmailTemplate(EmailTemplate et) {

		if (mapper.updEmailTemplate(et) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delEmailTemplate(int id) {
		if (mapper.delEmailTemplate(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<EmailTemplate> queryEmailTemplate(EmailTemplateVo vo) {

		Page<EmailTemplate> page = new Page<EmailTemplate>();
		if (vo.getIndex() == 0 || "".equals(vo.getIndex())) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countEmailTemplate(vo);
		List<EmailTemplate> dataList = mapper.queryEmailTemplate(vo);

		page.transform(count, dataList);

		return page;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addConfig(Config conf) {
		mapper.addConfig(conf);
	}

	@Override
	public Config getConfig(int id) {
		return mapper.getConfig(id);
	}

	@Override
	public boolean updConfig(Config conf) {

		if (mapper.updConfig(conf) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delConfig(int id) {
		if (mapper.delConfig(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<Config> queryConfig(ConfigVo vo) {

		Page<Config> page = new Page<Config>();

		if (vo.getIndex() == 0 || "".equals(vo.getIndex())) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		int count = mapper.countConfig(vo);
		List<Config> dataList = mapper.queryConfig(vo);
		page.transform(count, dataList);

		return page;
	}
	
	//分类配置
	public List<Config> getConfigCategory(){
		
		List<Config> config = mapper.getConfigCategory();
		
		return config;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<Distribution> queryDistribution(DistributionVo vo) {

		Page<Distribution> page = new Page<Distribution>();

		if (vo.getIndex() == 0) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		int count = mapper.countDistribution(vo);
		List<Distribution> dataList = mapper.queryDistribution(vo);
		page.transform(count, dataList);

		return page;

	}

	@Override
	public void addDistribution(Distribution dist) {
		mapper.addDistribution(dist);
	}

	@Override
	public Distribution getDistribution(int id) {
		return mapper.getDistribution(id);
	}

	@Override
	public boolean updDistribution(Distribution dist) {

		if (mapper.updDistribution(dist) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delDistribution(int id) {

		if (mapper.delDistribution(id) > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<Express> queryExpress(ExpressVo vo) {
		Page<Express> page = new Page<Express>();

		if (vo.getIndex() == 0) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countExpress(vo);
		List<Express> dataList = mapper.queryExpress(vo);
		page.transform(count, dataList);

		return page;
	}

	@Override
	public void addExpress(Express ex) {
		mapper.addExpress(ex);
	}

	@Override
	public Express getExpress(int id) {

		return mapper.getExpress(id);
	}

	@Override
	public boolean updExpress(Express ex) {
		if (mapper.updExpress(ex) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delExpress(int id) {
		if (mapper.delExpress(id) > 0) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addPayment(Payment pay) {
		mapper.addPayment(pay);

	}

	@Override
	public Payment getPayment(int id) {
		return mapper.getPayment(id);
	}

	@Override
	public boolean updPayment(Payment pay) {
		if (mapper.updPayment(pay) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delPayment(int id) {
		if (mapper.delPayment(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<Payment> queryPayment(PaymentVo vo) {
		Page<Payment> page = new Page<Payment>();

		if (vo.getIndex() == 0) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countPayment(vo);
		List<Payment> dataList = mapper.queryPayment(vo);
		page.transform(count, dataList);

		return page;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<Medal> queryMedal(MedalVo vo) {
		Page<Medal> page = new Page<Medal>();

		if (vo.getIndex() == 0) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countMedal(vo);
		List<Medal> dataList = mapper.queryMedal(vo);
		page.transform(count, dataList);

		return page;
	}

	@Override
	public void addMedal(Medal medal) {
		mapper.addMedal(medal);
	}

	@Override
	public Medal getMedal(int id) {
		return mapper.getMedal(id);
	}

	@Override
	public boolean updMedal(Medal medal) {
		if (mapper.updMedal(medal) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delMedal(int id) {
		if (mapper.delMedal(id) > 0) {
			return true;
		}
		return false;
	}
	// dt_business_type 系统业务库

	@Override
	public Page<Business> queryBusiness(BusinessVo vo) {
		Page<Business> page = new Page<Business>();

		if (vo.getIndex() == 0 || "".equals(vo.getIndex())) {
			page.setIndex(1);
			vo.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		int count = mapper.countBusiness(vo);
		List<Business> list = mapper.queryBusiness(vo);
		page.transform(count, list);

		return page;
	}

	@Override
	public void addBusiness(Business bus) {
		mapper.addBusiness(bus);
	}

	@Override
	public Business getBusiness(int id) {
		return mapper.getBusiness(id);
	}

	@Override
	public boolean updBusiness(Business bus) {
		if (mapper.updBusiness(bus) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delBusiness(int id) {
		if (mapper.delBusiness(id) > 0) {
			return true;
		}
		return false;
	}

	// 地理信息

	@Override
	public Page<ApoProvince> queryApoProvince(ApoProvinceVo vo) {
		Page<ApoProvince> page = new Page<ApoProvince>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ApoProvince> dataList = mapper.queryApoProvince(vo);
		int totalCount = mapper.countApoProvince(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addApoProvince(ApoProvince ap) {
		mapper.addApoProvince(ap);

	}

	@Override
	public ApoProvince getApoProvince(String province_id) {

		return mapper.getApoProvince(province_id);
	}

	@Override
	public boolean updApoProvince(ApoProvince ap) {
		if (mapper.updApoProvince(ap) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApoProvince(String province_id) {
		if (mapper.delApoProvince(province_id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ApoCity> queryApoCity(ApoCityVo vo) {
		Page<ApoCity> page = new Page<ApoCity>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ApoCity> dataList = mapper.queryApoCity(vo);
		int totalCount = mapper.countApoCity(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addApoCity(ApoCity ac) {

		mapper.addApoCity(ac);

	}

	@Override
	public ApoCity getApoCity(String city_id) {

		return mapper.getApoCity(city_id);
	}

	@Override
	public boolean updApoCity(ApoCity ac) {
		if (mapper.updApoCity(ac) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApoCity(String city_id) {
		if (mapper.delApoCity(city_id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ApoCounty> queryApoCounty(ApoCountyVo vo) {
		Page<ApoCounty> page = new Page<ApoCounty>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ApoCounty> dataList = mapper.queryApoCounty(vo);
		int totalCount = mapper.countApoCounty(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addApoCounty(ApoCounty ac) {
		mapper.addApoCounty(ac);

	}

	@Override
	public ApoCounty getApoCounty(String county_id) {

		return mapper.getApoCounty(county_id);
	}

	@Override
	public boolean updApoCounty(ApoCounty ac) {
		if (mapper.updApoCounty(ac) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApoCounty(String county_id) {
		if (mapper.delApoCounty(county_id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ApoTown> queryApoTown(ApoTownVo vo) {
		Page<ApoTown> page = new Page<ApoTown>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ApoTown> dataList = mapper.queryApoTown(vo);
		int totalCount = mapper.countApoTown(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addApoTown(ApoTown at) {
		mapper.addApoTown(at);

	}

	@Override
	public ApoTown getApoTown(String town_id) {

		return mapper.getApoTown(town_id);
	}

	@Override
	public boolean updApoTown(ApoTown at) {
		if (mapper.updApoTown(at) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApoTown(String town_id) {
		if (mapper.delApoTown(town_id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ApoVillage> queryApoVillage(ApoVillageVo vo) {
		Page<ApoVillage> page = new Page<ApoVillage>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ApoVillage> dataList = mapper.queryApoVillage(vo);
		int totalCount = mapper.countApoVillage(vo);

		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public void addApoVillage(ApoVillage av) {
		mapper.addApoVillage(av);

	}

	@Override
	public ApoVillage getApoVillage(String code) {

		return mapper.getApoVillage(code);
	}

	@Override
	public boolean updApoVillage(ApoVillage av) {
		if (mapper.updApoVillage(av) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delApoVillage(String code) {
		if (mapper.delApoVillage(code) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ApoProvince> findProvince() {
		List<ApoProvince> ap = mapper.findProvince();
		return ap;
	}

	@Override
	public List<ApoCity> findCityByProvince(String province_id) {
		List<ApoCity> ac = mapper.findCityByProvince(province_id);
		return ac;
	}

	@Override
	public List<ApoCounty> findCountyByCity(String city_id) {
		List<ApoCounty> ac = mapper.findCountyByCity(city_id);
		return ac;
	}

	@Override
	public List<ApoTown> findTownByCounty(String county_id) {
		List<ApoTown> at = mapper.findTownByCounty(county_id);
		return at;
	}

	@Override
	public List<ApoVillage> findVillageByTown(String town_id) {
		List<ApoVillage> av = mapper.findVillageByTown(town_id);
		return av;
	}

	@Override
	public Page<ApoCity> findCityByProvinceId(ApoCityVo vo) {
		Page<ApoCity> page = new Page<ApoCity>();
		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		List<ApoCity> dataList = mapper.findCityByProvinceId(vo);
		int totalCount = mapper.countCityByProvinceId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	@Override
	public Page<ApoCounty> findCountyByCityId(ApoCountyVo vo) {
		Page<ApoCounty> page = new Page<ApoCounty>();
		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		List<ApoCounty> dataList = mapper.findCountyByCityId(vo);
		int totalCount = mapper.countCountyByCityId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	@Override
	public Page<ApoTown> findTownByCountyId(ApoTownVo vo) {
		Page<ApoTown> page = new Page<ApoTown>();
		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		List<ApoTown> dataList = mapper.findTownByCountyId(vo);
		int totalCount = mapper.countTownByCountyId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	@Override
	public Page<ApoVillage> findVillageByTownId(ApoVillageVo vo) {
		Page<ApoVillage> page = new Page<ApoVillage>();
		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}
		List<ApoVillage> dataList = mapper.findVillageByTownId(vo);
		int totalCount = mapper.countVillageByTownId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	
	

	
	
	
	@Override
	public Page<ApoSfz> queryApoSfz(ApoSfzVo vo) {
		Page<ApoSfz> page =new Page<ApoSfz>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoSfz> dataList =mapper.queryApoSfz(vo);
		int totalCount =mapper.countApoSfz(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addApoSfz(ApoSfz as) {
		mapper.addApoSfz(as);
		
	}
	
	@Override
	public ApoSfz getApoSfz(String code) {
		
		return mapper.getApoSfz(code);
	}
	
	@Override
	public boolean updApoSfz(ApoSfz as) {
		if(mapper.updApoSfz(as)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoSfz(String code) {
		if(mapper.delApoSfz(code)>0){
			return true;	
		}
		return false;
	}
	
	

	
	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<ApoSite> queryApoSite(ApoSiteVo vo) {
		Page<ApoSite> page =new Page<ApoSite>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoSite> dataList =mapper.queryApoSite(vo);
		int totalCount =mapper.countApoSite(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addApoSite(ApoSite as) {
		mapper.addApoSite(as);
		
	}
	
	@Override
	public ApoSite getApoSite(int id) {
		
		return mapper.getApoSite(id);
	}
	
	@Override
	public boolean updApoSite(ApoSite as) {
		if(mapper.updApoSite(as)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoSite(int id) {
		if(mapper.delApoSite(id)>0){
			return true;	
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<ApoSiteContent> queryApoSiteContent(ApoSiteContentVo vo) {
		Page<ApoSiteContent> page =new Page<ApoSiteContent>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoSiteContent> dataList =mapper.queryApoSiteContent(vo);
		int totalCount =mapper.countApoSiteContent(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addApoSiteContent(ApoSiteContent asc) {
		mapper.addApoSiteContent(asc);
		
	}
	
	@Override
	public ApoSiteContent getApoSiteContent(int id) {
		
		return mapper.getApoSiteContent(id);
	}
	
	@Override
	public boolean updApoSiteContent(ApoSiteContent asc) {
		if(mapper.updApoSiteContent(asc)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoSiteContent(int id) {
		if(mapper.delApoSiteContent(id)>0){
			return true;	
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<ApoScreen> queryApoScreen(ApoScreenVo vo) {
		Page<ApoScreen> page =new Page<ApoScreen>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoScreen> dataList =mapper.queryApoScreen(vo);
		int totalCount =mapper.countApoScreen(vo);
		page.setPageNum(5);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addApoScreen(ApoScreen as) {
		mapper.addApoScreen(as);
		
	}
	
	@Override
	public ApoScreen getApoScreen(int id) {
		
		return mapper.getApoScreen(id);
	}
	
	@Override
	public boolean updApoScreen(ApoScreen as) {
		if(mapper.updApoScreen(as)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoScreen(int id) {
		if(mapper.delApoScreen(id)>0){
			return true;	
		}
		return false;
	}
	
	

	
	
	@Override
	public Page<Channel> queryChannel(ChannelVo vo) {
		Page<Channel> page =new Page<Channel>();
		page.setPageNum(5);
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Channel> dataList =mapper.queryChannel(vo);
		int totalCount =mapper.countChannel(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public Channel getChannel(int id) {
		return mapper.getChannel(id);
	}
	
	@Override
	public boolean updChannel(Channel aru) {
		if(mapper.updChannel(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannel(Channel aru) {
		mapper.addChannel(aru);
		
	}
	
	@Override
	public boolean delChannel(int id) {
		if(mapper.delChannel(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelField> queryChannelField(ChannelFieldVo vo) {
		Page<ChannelField> page =new Page<ChannelField>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelField> dataList =mapper.queryChannelField(vo);
		int totalCount =mapper.countChannelField(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelField getChannelField(int id) {
		return mapper.getChannelField(id);
	}
	
	@Override
	public boolean updChannelField(ChannelField aru) {
		if(mapper.updChannelField(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelField(ChannelField aru) {
		mapper.addChannelField(aru);
		
	}
	
	@Override
	public boolean delChannelField(int id) {
		if(mapper.delChannelField(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelSpec> queryChannelSpec(ChannelSpecVo vo) {
		Page<ChannelSpec> page =new Page<ChannelSpec>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelSpec> dataList =mapper.queryChannelSpec(vo);
		int totalCount =mapper.countChannelSpec(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelSpec getChannelSpec(int id) {
		return mapper.getChannelSpec(id);
	}
	
	@Override
	public boolean updChannelSpec(ChannelSpec aru) {
		if(mapper.updChannelSpec(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelSpec(ChannelSpec aru) {
		mapper.addChannelSpec(aru);
		
	}
	
	@Override
	public boolean delChannelSpec(int id) {
		if(mapper.delChannelSpec(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelSite> queryChannelSite(ChannelSiteVo vo) {
		Page<ChannelSite> page =new Page<ChannelSite>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelSite> dataList =mapper.queryChannelSite(vo);
		int totalCount =mapper.countChannelSite(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelSite getChannelSite(int id) {
		return mapper.getChannelSite(id);
	}
	
	@Override
	public boolean updChannelSite(ChannelSite aru) {
		if(mapper.updChannelSite(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelSite(ChannelSite aru) {
		mapper.addChannelSite(aru);
		
	}
	
	@Override
	public boolean delChannelSite(int id) {
		if(mapper.delChannelSite(id)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<Category> getFirstCategory() {
		return mapper.getGoodsFirstCategory();
	}

	@Override
	public List<Category> getSecondCategory(int parent_id) {
		
		return mapper.getCategorysByParentId(parent_id);
	}

	@Override
	public List<Category> getThirdCategory(int parent_id) {
		
		return mapper.getCategorysByParentId(parent_id);
	}
	

	//dt_mb_category(用户自定义分类模板表)

	@Override
	public Page<MbCategory> queryMbCategory(MbCategoryVo vo) {
		Page<MbCategory> page =new Page<MbCategory>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbCategory(vo);
		List<MbCategory> list =mapper.queryMbCategory(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbCategory(MbCategory og) {
		mapper.addMbCategory(og);
	}
	
	@Override
	public MbCategory getMbCategory(int id) {
		return mapper.getMbCategory(id);
	}
	
	@Override
	public boolean updMbCategory(MbCategory og) {
		if(mapper.updMbCategory(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbCategory(int id) {
		if(mapper.delMbCategory(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_article(用户发布内容模板表)

	@Override
	public Page<MbArticle> queryMbArticle(MbArticleVo vo) {
		Page<MbArticle> page =new Page<MbArticle>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbArticle(vo);
		List<MbArticle> list =mapper.queryMbArticle(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbArticle(MbArticle og) {
		mapper.addMbArticle(og);
	}
	
	@Override
	public MbArticle getMbArticle(int id) {
		return mapper.getMbArticle(id);
	}
	
	@Override
	public boolean updMbArticle(MbArticle og) {
		if(mapper.updMbArticle(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbArticle(int id) {
		if(mapper.delMbArticle(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_images(内容所用图片模板表)

	@Override
	public Page<MbImages> queryMbImages(MbImagesVo vo) {
		Page<MbImages> page =new Page<MbImages>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbImages(vo);
		List<MbImages> list =mapper.queryMbImages(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbImages(MbImages og) {
		mapper.addMbImages(og);
	}
	
	@Override
	public MbImages getMbImages(int id) {
		return mapper.getMbImages(id);
	}
	
	@Override
	public boolean updMbImages(MbImages og) {
		if(mapper.updMbImages(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbImages(int id) {
		if(mapper.delMbImages(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_yewu(分类所用业务模板表)

	@Override
	public Page<MbYewu> queryMbYewu(MbYewuVo vo) {
		Page<MbYewu> page =new Page<MbYewu>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbYewu(vo);
		List<MbYewu> list =mapper.queryMbYewu(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbYewu(MbYewu og) {
		mapper.addMbYewu(og);
	}
	
	@Override
	public MbYewu getMbYewu(int id) {
		return mapper.getMbYewu(id);
	}
	
	@Override
	public boolean updMbYewu(MbYewu og) {
		if(mapper.updMbYewu(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbYewu(int id) {
		if(mapper.delMbYewu(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_goods_matter(用户发布内容购买须知等模板表)

	@Override
	public Page<MbGoodsMatter> queryMbGoodsMatter(MbGoodsMatterVo vo) {
		Page<MbGoodsMatter> page =new Page<MbGoodsMatter>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbGoodsMatter(vo);
		List<MbGoodsMatter> list =mapper.queryMbGoodsMatter(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbGoodsMatter(MbGoodsMatter og) {
		mapper.addMbGoodsMatter(og);
	}
	
	@Override
	public MbGoodsMatter getMbGoodsMatter(int id) {
		return mapper.getMbGoodsMatter(id);
	}
	
	@Override
	public boolean updMbGoodsMatter(MbGoodsMatter og) {
		if(mapper.updMbGoodsMatter(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbGoodsMatter(int id) {
		if(mapper.delMbGoodsMatter(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_shop_matter(店铺总须知模板表)

	@Override
	public Page<MbShopMatter> queryMbShopMatter(MbShopMatterVo vo) {
		Page<MbShopMatter> page =new Page<MbShopMatter>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbShopMatter(vo);
		List<MbShopMatter> list =mapper.queryMbShopMatter(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbShopMatter(MbShopMatter og) {
		mapper.addMbShopMatter(og);
	}
	
	@Override
	public MbShopMatter getMbShopMatter(int id) {
		return mapper.getMbShopMatter(id);
	}
	
	@Override
	public boolean updMbShopMatter(MbShopMatter og) {
		if(mapper.updMbShopMatter(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbShopMatter(int id) {
		if(mapper.delMbShopMatter(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_mb_channel_matter(店铺所用各业务频道须知等模板表)

	@Override
	public Page<MbChannelMatter> queryMbChannelMatter(MbChannelMatterVo vo) {
		Page<MbChannelMatter> page =new Page<MbChannelMatter>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countMbChannelMatter(vo);
		List<MbChannelMatter> list =mapper.queryMbChannelMatter(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addMbChannelMatter(MbChannelMatter og) {
		mapper.addMbChannelMatter(og);
	}
	
	@Override
	public MbChannelMatter getMbChannelMatter(int id) {
		return mapper.getMbChannelMatter(id);
	}
	
	@Override
	public boolean updMbChannelMatter(MbChannelMatter og) {
		if(mapper.updMbChannelMatter(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delMbChannelMatter(int id) {
		if(mapper.delMbChannelMatter(id)>0){
			return true;
		}
		return false;
	}
	

	//dt_banktype（支持绑定银行卡/三方账户类型信息表）
	
	@Override
	public Page<Banktype> queryBanktype(BanktypeVo vo) {
		Page<Banktype> page =new Page<Banktype>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countBanktype(vo);
		List<Banktype> list =mapper.queryBanktype(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addBanktype(Banktype og) {
		mapper.addBanktype(og);
	}
	
	@Override
	public Banktype getBanktype(int id) {
		return mapper.getBanktype(id);
	}
	
	@Override
	public boolean updBanktype(Banktype og) {
		if(mapper.updBanktype(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delBanktype(int id) {
		if(mapper.delBanktype(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_user_oauth_app（用户第三方登录类型信息表）
	
	@Override
	public Page<UserOauthApp> queryUserOauthApp(UserOauthAppVo vo) {
		Page<UserOauthApp> page =new Page<UserOauthApp>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserOauthApp(vo);
		List<UserOauthApp> list =mapper.queryUserOauthApp(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserOauthApp(UserOauthApp og) {
		mapper.addUserOauthApp(og);
	}
	
	@Override
	public UserOauthApp getUserOauthApp(int id) {
		return mapper.getUserOauthApp(id);
	}
	
	@Override
	public boolean updUserOauthApp(UserOauthApp og) {
		if(mapper.updUserOauthApp(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserOauthApp(int id) {
		if(mapper.delUserOauthApp(id)>0){
			return true;
		}
		return false;
	}
	
	//dt_groups(系统分组信息表)
	
	@Override
	public Page<Groups> queryGroups(GroupsVo vo) {
		Page<Groups> page =new Page<Groups>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countGroups(vo);
		List<Groups> list =mapper.queryGroups(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addGroups(Groups og) {
		mapper.addGroups(og);
	}
	
	@Override
	public Groups getGroups(int id) {
		return mapper.getGroups(id);
	}
	
	@Override
	public boolean updGroups(Groups og) {
		if(mapper.updGroups(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delGroups(int id) {
		if(mapper.delGroups(id)>0){
			return true;
		}
		return false;
	}
	
	
	//dt_groups(信誉规则)
	
	@Override
	public Page<Scorerule> queryRules(ScoreruleVo vo) {
		Page<Scorerule> page =new Page<Scorerule>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countRules(vo);
		List<Scorerule> list =mapper.queryRules(vo);
		
		page.transform(count, list);
		return page;
	}
	@Override
	public void addRules(Scorerule og) {
		mapper.addRules(og);
	}
	@Override
	public Scorerule getRules(int id) {
		return mapper.getRules(id);
	}
	@Override
	public boolean updRules(Scorerule og) {
		if(mapper.updRules(og)>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean delRules(int id) {
		if(mapper.delRules(id)>0){
			return true;
		}
		return false;
	}
	
	
}
