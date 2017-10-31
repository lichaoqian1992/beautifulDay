package com.manji.backstage.mapper.operation;

import java.util.List;

import javax.annotation.Resource;

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

@Resource
public interface OperationMapper {

	List<Category> getGoodsFirstCategory();
	
	List<Category> getCategorysByParentId(int parent_id);
	
	Category getCategoryById(int id);

	
	//上传文件
	List <Files> countFilesGroup();
	
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
		
		
	List<ApoSfz> queryApoSfz(ApoSfzVo vo);
	
	int countApoSfz(ApoSfzVo vo);
	
	void addApoSfz(ApoSfz as);
	
	ApoSfz getApoSfz(String code);
	
	int updApoSfz(ApoSfz as);
	
	int delApoSfz(String code);
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
	
	
	List<AdvertNavigation> queryAdvertNavigation(AdvertNavigationVo vo);
	
	int countAdvertNavigation(AdvertNavigationVo vo);
	
	void addAdvertNavigation(AdvertNavigation an);
	
	AdvertNavigation getAdvertNavigation(int id);
	
	int updAdvertNavigation(AdvertNavigation an);
	
	int delAdvertNavigation(int id);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	List<Advert> queryAdvert(AdvertVo vo);
	
	int countAdvert(AdvertVo vo);
	
	void addAdvert(Advert advert);
	
	Advert getAdvert(int id);
	
	int updAdvert(Advert advert);
	
	int delAdvert(int id);
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	List<Navigation> queryNavigation(NavigationVo vo);
	
	int countNavigation(NavigationVo vo);
	
	void addNavigation(Navigation navigation);
	
	Navigation getNavigation(int id);
	
	int updNavigation(Navigation navigation);
	
	int delNavigation(int id);
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	List<Files> queryFiles(FilesVo vo);
	
	int countFiles(FilesVo vo);
	
	void addFiles(Files files);
	
	Files getFiles(int id);
	
	int updFiles(Files files);
	
	int delFiles(int id);
	
}
