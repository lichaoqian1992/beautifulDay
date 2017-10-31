package com.manji.backstage.service.operation;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.operation.Advert;
import com.manji.backstage.model.operation.AdvertNavigation;
import com.manji.backstage.model.operation.ApoCity;
import com.manji.backstage.model.operation.ApoCounty;
import com.manji.backstage.model.operation.ApoProvince;
import com.manji.backstage.model.operation.ApoScreen;
import com.manji.backstage.model.operation.ApoSfz;
import com.manji.backstage.model.operation.ApoSite;
import com.manji.backstage.model.operation.ApoSiteContent;
import com.manji.backstage.model.operation.ApoTown;
import com.manji.backstage.model.operation.ApoVillage;
import com.manji.backstage.model.operation.AppVersion;
import com.manji.backstage.model.operation.Category;
import com.manji.backstage.model.operation.Files;
import com.manji.backstage.model.operation.Menu;
import com.manji.backstage.model.operation.Navigation;
import com.manji.backstage.model.operation.QrCode;
import com.manji.backstage.model.operation.Screen;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.vo.operation.AdvertNavigationVo;
import com.manji.backstage.vo.operation.AdvertVo;
import com.manji.backstage.vo.operation.ApoCityVo;
import com.manji.backstage.vo.operation.ApoCountyVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;
import com.manji.backstage.vo.operation.ApoScreenVo;
import com.manji.backstage.vo.operation.ApoSfzVo;
import com.manji.backstage.vo.operation.ApoSiteContentVo;
import com.manji.backstage.vo.operation.ApoSiteVo;
import com.manji.backstage.vo.operation.ApoTownVo;
import com.manji.backstage.vo.operation.ApoVillageVo;
import com.manji.backstage.vo.operation.AppVersionVo;
import com.manji.backstage.vo.operation.FilesVo;
import com.manji.backstage.vo.operation.MenuVo;
import com.manji.backstage.vo.operation.NavigationVo;
import com.manji.backstage.vo.operation.QrCodeVo;
import com.manji.backstage.vo.operation.ScreenVo;

public interface OperationService {

	
	List<Category> getFirstCategory();
	
	List<Category> getSecondCategory(int id);
	
	List<Category> getThirdCategory(int id);
	
	
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
	
	
	Page<ApoSfz> queryApoSfz(ApoSfzVo vo);
	
	void addApoSfz(ApoSfz av);
	
	ApoSfz getApoSfz(String code);
	
	boolean updApoSfz(ApoSfz av);
	
	boolean delApoSfz(String code);

	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<AdvertNavigation> queryAdvertNavigation(AdvertNavigationVo vo);
	
	void addAdvertNavigation(AdvertNavigation an);
	
	AdvertNavigation getAdvertNavigation(int id);
	
	boolean updAdvertNavigation(AdvertNavigation an);
	
	boolean delAdvertNavigation(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<Advert> queryAdvert(AdvertVo vo);
	
	void addAdvert(Advert advert);
	
	Advert getAdvert(int id);
	
	boolean updAdvert(Advert advert);
	
	boolean delAdvert(int id);
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Page<Navigation> queryNavigation(NavigationVo vo);
	
	void addNavigation(Navigation navigation);
	
	Navigation getNavigation(int id);
	
	boolean updNavigation(Navigation navigation);
	
	boolean delNavigation(int id);
	
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
	
	Page<Files> queryFiles(FilesVo vo);
	
	void addFiles(Files files);
	
	Files getFiles(int id);
	
	boolean updFiles(Files files);
	
	boolean delFiles(int id);
	
	List<Files> countFilesGroup();
}
