package com.manji.backstage.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.OperMapper;
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
@Service
public class OperServiceImpl implements OperService {
@Autowired
	private OperMapper mapper;
	
///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Page<AppVersion> queryAppVersion(AppVersionVo vo) {
		Page<AppVersion> page =new Page<AppVersion>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<AppVersion> dataList =mapper.queryAppVersion(vo);
		int totalCount =mapper.countAppVersion(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addAppVersion(AppVersion av) {
		mapper.addAppVersion(av);
		
	}

	@Override
	public AppVersion getAppVersion(int id) {
		
		return mapper.getAppVersion(id);
	}

	@Override
	public boolean updAppVersion(AppVersion av) {
		if(mapper.updAppVersion(av)>0){
		return true;	
		}
		return false;
	}

	@Override
	public boolean delAppVersion(int id) {
		if(mapper.delAppVersion(id)>0){
			return true;	
			}
			return false;
	}

	
	/**
	 * 二维码
	 */
	@Override
	public Page<QrCode> queryQrCode(QrCodeVo vo) {
		Page<QrCode> page =new Page<QrCode>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<QrCode> dataList =mapper.queryQrCode(vo);
		int totalCount =mapper.countQrCode(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addQrCode(QrCode qc) {
		mapper.addQrCode(qc);
		
	}

	@Override
	public QrCode getQrCode(int id) {
		return mapper.getQrCode(id);
	}

	@Override
	public boolean updQrCode(QrCode qc) {
		if(mapper.updQrCode(qc)>0){
			return true;	
			}
			return false;
	}

	@Override
	public boolean delQrCode(int id) {
		if(mapper.delQrCode(id)>0){
			return true;	
			}
			return false;
	}
	
	


	
	@Override
	public Page<Menu> queryMenu(MenuVo vo) {
		Page<Menu> page =new Page<Menu>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Menu> dataList =mapper.queryMenu(vo);
		int totalCount =mapper.countMenu(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addMenu(Menu menu) {
		mapper.addMenu(menu);
		
	}

	@Override
	public Menu getMenu(int id) {
		return mapper.getMenu(id);
	}

	@Override
	public boolean updMenu(Menu menu) {
		if(mapper.updMenu(menu)>0){
			return true;	
			}
			return false;
	}

	@Override
	public boolean delMenu(int id) {
		if(mapper.delMenu(id)>0){
			return true;	
			}
			return false;
	}

	
	

	///////////////////////////////////////////////////////////////
	@Override
	public Page<Screen> queryScreen(ScreenVo vo) {
		Page<Screen> page =new Page<Screen>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Screen> dataList =mapper.queryScreen(vo);
		int totalCount =mapper.countScreen(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addScreen(Screen screen) {
		mapper.addScreen(screen);
		
	}

	@Override
	public Screen getScreen(int id) {
		
		return mapper.getScreen(id);
	}

	@Override
	public boolean updScreen(Screen screen) {
		if(mapper.updScreen(screen)>0){
		return true;	
		}
		return false;
	}

	@Override
	public boolean delScreen(int id) {
		if(mapper.delScreen(id)>0){
			return true;	
			}
			return false;
	}
	
	//获取广告类型
	public List<AdvertNavigation> getAdvertType(){
		List<AdvertNavigation> an = mapper.getAdvertType();
		return an;
	}
	//根据parent_id获取title
	public String getTitle(int parent_id){
		String an = mapper.getTitle(parent_id);
		return an;
	}
	
	//根据title模糊查询
	public List<AdvertNavigation> getTitleId(String title){
		List<AdvertNavigation> an = mapper.getTitleId(title);
		return an;
	}
	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<AdvertNavigation> queryAdvertNavigation(AdvertNavigationVo vo) {
		Page<AdvertNavigation> page =new Page<AdvertNavigation>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<AdvertNavigation> dataList =mapper.queryAdvertNavigation(vo);
		int totalCount =mapper.countAdvertNavigation(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addAdvertNavigation(AdvertNavigation an) {
		mapper.addAdvertNavigation(an);
		
	}
	
	@Override
	public AdvertNavigation getAdvertNavigation(int id) {
		
		return mapper.getAdvertNavigation(id);
	}
	
	@Override
	public boolean updAdvertNavigation(AdvertNavigation an) {
		if(mapper.updAdvertNavigation(an)>0){
			return true;	
		}
		return false;
	}
	
	//00000000000000000000
	@Override
	public boolean updAdvertNavigation2(AdvertNavigation an) {
		if(mapper.updAdvertNavigation2(an)>0){
			return true;	
		}
		return false;
	}
	//00000000000000000000
	
	@Override
	public boolean delAdvertNavigation(int id) {
		if(mapper.delAdvertNavigation(id)>0){
			return true;	
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<Advert> queryAdvert(AdvertVo vo) {
		Page<Advert> page =new Page<Advert>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Advert> dataList =mapper.queryAdvert(vo);
		int totalCount =mapper.countAdvert(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addAdvert(Advert advert) {
		mapper.addAdvert(advert);
		
	}
	
	@Override
	public Advert getAdvert(int id) {
		
		return mapper.getAdvert(id);
	}
	
	@Override
	public boolean updAdvert(Advert advert) {
		if(mapper.updAdvert(advert)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delAdvert(int id) {
		if(mapper.delAdvert(id)>0){
			return true;	
		}
		return false;
	}
	

	
	///////////////////////////////////////////////////////////////
	@Override
	public Page<Navigation> queryNavigation(NavigationVo vo) {
		Page<Navigation> page =new Page<Navigation>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Navigation> dataList =mapper.queryNavigation(vo);
		int totalCount =mapper.countNavigation(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addNavigation(Navigation navigation) {
		mapper.addNavigation(navigation);
		
	}
	
	@Override
	public Navigation getNavigation(int id) {
		
		return mapper.getNavigation(id);
	}
	
	@Override
	public boolean updNavigation(Navigation navigation) {
		if(mapper.updNavigation(navigation)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delNavigation(int id) {
		if(mapper.delNavigation(id)>0){
			return true;	
		}
		return false;
	}

	
	
	
	
	//////

	@Override
	public Page<ActBusiness> queryActBusiness(ActBusinessVo vo) {
		Page<ActBusiness> page =new Page<ActBusiness>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusiness> dataList =mapper.queryActBusiness(vo);
		int totalCount =mapper.countActBusiness(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusiness getActBusiness(int id) {
		return mapper.getActBusiness(id);
	}

	@Override
	public boolean updActBusiness(ActBusiness ab) {
		if(mapper.updActBusiness(ab)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusiness(ActBusiness ab) {
		mapper.addActBusiness(ab);
	}

	@Override
	public boolean delActBusiness(int id) {
		if(mapper.delActBusiness(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActIndex> queryActIndex(ActIndexVo vo) {
		Page<ActIndex> page =new Page<ActIndex>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActIndex> dataList =mapper.queryActIndex(vo);
		int totalCount =mapper.countActIndex(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActIndex getActIndex(int id) {
		return mapper.getActIndex(id);
	}

	@Override
	public boolean updActIndex(ActIndex ai) {
		if(mapper.updActIndex(ai)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActIndex(ActIndex ai) {
		mapper.addActIndex(ai);
	}

	@Override
	public boolean delActIndex(int id) {
		if(mapper.delActIndex(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActIndUser> queryActIndUser(ActIndUserVo vo) {
		Page<ActIndUser> page =new Page<ActIndUser>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActIndUser> dataList =mapper.queryActIndUser(vo);
		int totalCount =mapper.countActIndUser(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActIndUser getActIndUser(int id) {
		return mapper.getActIndUser(id);
	}

	@Override
	public boolean updActIndUser(ActIndUser aiu) {
		if(mapper.updActIndUser(aiu)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActIndUser(ActIndUser aiu) {

		mapper.addActIndUser(aiu);
	}

	@Override
	public boolean delActIndUser(int id) {
		if(mapper.delActIndUser(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActCommon> queryActCommon(ActCommonVo vo) {
		Page<ActCommon> page =new Page<ActCommon>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActCommon> dataList =mapper.queryActCommon(vo);
		int totalCount =mapper.countActCommon(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActCommon getActCommon(int id) {
		return mapper.getActCommon(id);
	}

	@Override
	public boolean updActCommon(ActCommon ac) {
		if(mapper.updActCommon(ac)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActCommon(ActCommon ac) {
		mapper.addActCommon(ac);
		
	}

	@Override
	public boolean delActCommon(int id) {
		if(mapper.delActCommon(id)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public Page<ActShop> queryActShop(ActShopVo vo) {
		Page<ActShop> page =new Page<ActShop>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActShop> dataList =mapper.queryActShop(vo);
		int totalCount =mapper.countActShop(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActShop getActShop(int id) {
		return mapper.getActShop(id);
	}

	@Override
	public boolean updActShop(ActShop as) {
		if(mapper.updActShop(as)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActShop(ActShop as) {
		mapper.addActShop(as);
		
	}

	@Override
	public boolean delActShop(int id) {
		if(mapper.delActShop(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActShopGoods> queryActShopGoods(ActShopGoodsVo vo) {
		Page<ActShopGoods> page =new Page<ActShopGoods>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActShopGoods> dataList =mapper.queryActShopGoods(vo);
		int totalCount =mapper.countActShopGoods(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActShopGoods getActShopGoods(int id) {
		return mapper.getActShopGoods(id);
	}

	@Override
	public boolean updActShopGoods(ActShopGoods asg) {
		if(mapper.updActShopGoods(asg)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActShopGoods(ActShopGoods asg) {
		mapper.addActShopGoods(asg);
		
	}

	@Override
	public boolean delActShopGoods(int id) {
		if(mapper.delActShopGoods(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActSms> queryActSms(ActSmsVo vo) {
		Page<ActSms> page =new Page<ActSms>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActSms> dataList =mapper.queryActSms(vo);
		int totalCount =mapper.countActSms(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActSms getActSms(int id) {
		return mapper.getActSms(id);
	}

	@Override
	public boolean updActSms(ActSms as) {
		if(mapper.updActSms(as)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActSms(ActSms as) {
		mapper.addActSms(as);
		
	}

	@Override
	public boolean delActSms(int id) {
		if(mapper.delActSms(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActAdvert> queryActAdvert(ActAdvertVo vo) {
		Page<ActAdvert> page =new Page<ActAdvert>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActAdvert> dataList =mapper.queryActAdvert(vo);
		int totalCount =mapper.countActAdvert(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActAdvert getActAdvert(int id) {
		return mapper.getActAdvert(id);
	}

	@Override
	public boolean updActAdvert(ActAdvert aa) {
		if(mapper.updActAdvert(aa)){
			return true;
		}
		return false;
	}

	@Override
	public void addActAdvert(ActAdvert aa) {
		mapper.addActAdvert(aa);
		
	}

	@Override
	public boolean delActAdvert(int id) {
		if(mapper.delActAdvert(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActBusShop> queryActBusShop(ActBusShopVo vo) {
		Page<ActBusShop> page =new Page<ActBusShop>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusShop> dataList =mapper.queryActBusShop(vo);
		int totalCount =mapper.countActBusShop(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusShop getActBusShop(int id) {
		return mapper.getActBusShop(id);
	}

	@Override
	public boolean updActBusShop(ActBusShop abs) {
		if(mapper.updActBusShop(abs)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusShop(ActBusShop abs) {
		mapper.addActBusShop(abs);
		
	}

	@Override
	public boolean delActBusShop(int id) {
		if(mapper.delActBusShop(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActBusGoods> queryActBusGoods(ActBusGoodsVo vo) {
		Page<ActBusGoods> page =new Page<ActBusGoods>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusGoods> dataList =mapper.queryActBusGoods(vo);
		int totalCount =mapper.countActBusGoods(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusGoods getActBusGoods(int id) {
		return mapper.getActBusGoods(id);
	}

	@Override
	public boolean updActBusGoods(ActBusGoods abg) {
		if(mapper.updActBusGoods(abg)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusGoods(ActBusGoods abg) {
		mapper.addActBusGoods(abg);
		
	}

	@Override
	public boolean delActBusGoods(int id) {
		if(mapper.delActBusGoods(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActRecherge> queryActRecherge(ActRechergeVo vo) {
		Page<ActRecherge> page =new Page<ActRecherge>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActRecherge> dataList =mapper.queryActRecherge(vo);
		int totalCount =mapper.countActRecherge(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActRecherge getActRecherge(int id) {
		return mapper.getActRecherge(id);
	}

	@Override
	public boolean updActRecherge(ActRecherge ar) {
		if(mapper.updActRecherge(ar)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActRecherge(ActRecherge ar) {
		mapper.addActRecherge(ar);
		
	}

	@Override
	public boolean delActRecherge(int id) {
		if(mapper.delActRecherge(id)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public Page<ActRecUser> queryActRecUser(ActRecUserVo vo) {
		Page<ActRecUser> page =new Page<ActRecUser>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActRecUser> dataList =mapper.queryActRecUser(vo);
		int totalCount =mapper.countActRecUser(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ActRecUser getActRecUser(int id) {
		return mapper.getActRecUser(id);
	}
	
	@Override
	public boolean updActRecUser(ActRecUser aru) {
		if(mapper.updActRecUser(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addActRecUser(ActRecUser aru) {
		mapper.addActRecUser(aru);
		
	}
	
	@Override
	public boolean delActRecUser(int id) {
		if(mapper.delActRecUser(id)>0){
			return true;
		}
		return false;
	}
	

	//dt_advert_banner（广告位购买信息表）
	
	@Override
	public Page<AdvertBanner> queryAdvertBanner(AdvertBannerVo vo) {
		Page<AdvertBanner> page =new Page<AdvertBanner>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countAdvertBanner(vo);
		List<AdvertBanner> list =mapper.queryAdvertBanner(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addAdvertBanner(AdvertBanner og) {
		mapper.addAdvertBanner(og);
	}
	
	@Override
	public AdvertBanner getAdvertBanner(int id) {
		return mapper.getAdvertBanner(id);
	}
	
	@Override
	public boolean updAdvertBanner(AdvertBanner og) {
		if(mapper.updAdvertBanner(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delAdvertBanner(int id) {
		if(mapper.delAdvertBanner(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_advert_content(广告位放置内容表)
	
	@Override
	public Page<AdvertContent> queryAdvertContent(AdvertContentVo vo) {
		Page<AdvertContent> page =new Page<AdvertContent>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countAdvertContent(vo);
		List<AdvertContent> list =mapper.queryAdvertContent(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addAdvertContent(AdvertContent og) {
		mapper.addAdvertContent(og);
	}
	
	@Override
	public AdvertContent getAdvertContent(int id) {
		return mapper.getAdvertContent(id);
	}
	
	@Override
	public boolean updAdvertContent(AdvertContent og) {
		if(mapper.updAdvertContent(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delAdvertContent(int id) {
		if(mapper.delAdvertContent(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_attribute_field（内容扩展属性表）
	
	@Override
	public Page<ArticleAttributeField> queryArticleAttributeField(ArticleAttributeFieldVo vo) {
		Page<ArticleAttributeField> page =new Page<ArticleAttributeField>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleAttributeField(vo);
		List<ArticleAttributeField> list =mapper.queryArticleAttributeField(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleAttributeField(ArticleAttributeField og) {
		mapper.addArticleAttributeField(og);
	}
	
	@Override
	public ArticleAttributeField getArticleAttributeField(int id) {
		return mapper.getArticleAttributeField(id);
	}
	
	@Override
	public boolean updArticleAttributeField(ArticleAttributeField og) {
		if(mapper.updArticleAttributeField(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleAttributeField(int id) {
		if(mapper.delArticleAttributeField(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_attribute_value（内容扩展属性对应值记录表*会动态更新）
	
	@Override
	public Page<ArticleAttributeValue> queryArticleAttributeValue(ArticleAttributeValueVo vo) {
		Page<ArticleAttributeValue> page =new Page<ArticleAttributeValue>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleAttributeValue(vo);
		List<ArticleAttributeValue> list =mapper.queryArticleAttributeValue(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleAttributeValue(ArticleAttributeValue og) {
		mapper.addArticleAttributeValue(og);
	}
	
	@Override
	public ArticleAttributeValue getArticleAttributeValue(int id) {
		return mapper.getArticleAttributeValue(id);
	}
	
	@Override
	public boolean updArticleAttributeValue(ArticleAttributeValue og) {
		if(mapper.updArticleAttributeValue(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleAttributeValue(int id) {
		if(mapper.delArticleAttributeValue(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_goods_spec（文章派生的商品类所选则规格信息表）
	
	@Override
	public Page<ArticleGoodsSpec> queryArticleGoodsSpec(ArticleGoodsSpecVo vo) {
		Page<ArticleGoodsSpec> page =new Page<ArticleGoodsSpec>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleGoodsSpec(vo);
		List<ArticleGoodsSpec> list =mapper.queryArticleGoodsSpec(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleGoodsSpec(ArticleGoodsSpec og) {
		mapper.addArticleGoodsSpec(og);
	}
	
	@Override
	public ArticleGoodsSpec getArticleGoodsSpec(int article_id) {
		return mapper.getArticleGoodsSpec(article_id);
	}
	
	@Override
	public boolean updArticleGoodsSpec(ArticleGoodsSpec og) {
		if(mapper.updArticleGoodsSpec(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleGoodsSpec(int id) {
		if(mapper.delArticleGoodsSpec(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_groupshop（集团店批量操作记录表）
	
	@Override
	public Page<ArticleGroupshop> queryArticleGroupshop(ArticleGroupshopVo vo) {
		Page<ArticleGroupshop> page =new Page<ArticleGroupshop>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleGroupshop(vo);
		List<ArticleGroupshop> list =mapper.queryArticleGroupshop(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleGroupshop(ArticleGroupshop og) {
		mapper.addArticleGroupshop(og);
	}
	
	@Override
	public ArticleGroupshop getArticleGroupshop(int id) {
		return mapper.getArticleGroupshop(id);
	}
	
	@Override
	public boolean updArticleGroupshop(ArticleGroupshop og) {
		if(mapper.updArticleGroupshop(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleGroupshop(int id) {
		if(mapper.delArticleGroupshop(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_goods_groupshop（集团店商品规格批量操作记录表）
	
	@Override
	public Page<ArticleGoodsGroupshop> queryArticleGoodsGroupshop(ArticleGoodsGroupshopVo vo) {
		Page<ArticleGoodsGroupshop> page =new Page<ArticleGoodsGroupshop>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleGoodsGroupshop(vo);
		List<ArticleGoodsGroupshop> list =mapper.queryArticleGoodsGroupshop(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleGoodsGroupshop(ArticleGoodsGroupshop og) {
		mapper.addArticleGoodsGroupshop(og);
	}
	
	@Override
	public ArticleGoodsGroupshop getArticleGoodsGroupshop(int id) {
		return mapper.getArticleGoodsGroupshop(id);
	}
	
	@Override
	public boolean updArticleGoodsGroupshop(ArticleGoodsGroupshop og) {
		if(mapper.updArticleGoodsGroupshop(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleGoodsGroupshop(int id) {
		if(mapper.delArticleGoodsGroupshop(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_tags（热门文章TAG标签存储信息表）
	
	@Override
	public Page<ArticleTags> queryArticleTags(ArticleTagsVo vo) {
		Page<ArticleTags> page =new Page<ArticleTags>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleTags(vo);
		List<ArticleTags> list =mapper.queryArticleTags(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleTags(ArticleTags og) {
		mapper.addArticleTags(og);
	}
	
	@Override
	public ArticleTags getArticleTags(int id) {
		return mapper.getArticleTags(id);
	}
	
	@Override
	public boolean updArticleTags(ArticleTags og) {
		if(mapper.updArticleTags(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleTags(int id) {
		if(mapper.delArticleTags(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_article_tags_relation（热门文章TAG标签对应关系信息表）
	
	@Override
	public Page<ArticleTagsRelation> queryArticleTagsRelation(ArticleTagsRelationVo vo) {
		Page<ArticleTagsRelation> page =new Page<ArticleTagsRelation>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countArticleTagsRelation(vo);
		List<ArticleTagsRelation> list =mapper.queryArticleTagsRelation(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addArticleTagsRelation(ArticleTagsRelation og) {
		mapper.addArticleTagsRelation(og);
	}
	
	@Override
	public ArticleTagsRelation getArticleTagsRelation(int id) {
		return mapper.getArticleTagsRelation(id);
	}
	
	@Override
	public boolean updArticleTagsRelation(ArticleTagsRelation og) {
		if(mapper.updArticleTagsRelation(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delArticleTagsRelation(int id) {
		if(mapper.delArticleTagsRelation(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_channel_tags_relation（热门频道TAG标签对应关系信息表）
	
	@Override
	public Page<ChannelTagsRelation> queryChannelTagsRelation(ChannelTagsRelationVo vo) {
		Page<ChannelTagsRelation> page =new Page<ChannelTagsRelation>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countChannelTagsRelation(vo);
		List<ChannelTagsRelation> list =mapper.queryChannelTagsRelation(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addChannelTagsRelation(ChannelTagsRelation og) {
		mapper.addChannelTagsRelation(og);
	}
	
	@Override
	public ChannelTagsRelation getChannelTagsRelation(int id) {
		return mapper.getChannelTagsRelation(id);
	}
	
	@Override
	public boolean updChannelTagsRelation(ChannelTagsRelation og) {
		if(mapper.updChannelTagsRelation(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delChannelTagsRelation(int id) {
		if(mapper.delChannelTagsRelation(id)>0){
			return true;
		}
		return false;
	}

}
