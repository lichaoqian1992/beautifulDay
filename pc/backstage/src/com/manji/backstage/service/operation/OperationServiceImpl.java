package com.manji.backstage.service.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.operation.OperationMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.model.operation.Advert;
import com.manji.backstage.model.operation.AdvertNavigation;
import com.manji.backstage.model.operation.ApoTown;
import com.manji.backstage.model.operation.ApoVillage;
import com.manji.backstage.model.operation.ApoCity;
import com.manji.backstage.model.operation.ApoCounty;
import com.manji.backstage.model.operation.ApoProvince;
import com.manji.backstage.model.operation.ApoScreen;
import com.manji.backstage.model.operation.ApoSfz;
import com.manji.backstage.model.operation.ApoSite;
import com.manji.backstage.model.operation.ApoSiteContent;
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
import com.manji.backstage.vo.operation.ApoTownVo;
import com.manji.backstage.vo.operation.ApoVillageVo;
import com.manji.backstage.vo.operation.ApoCountyVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;
import com.manji.backstage.vo.operation.ApoScreenVo;
import com.manji.backstage.vo.operation.ApoSfzVo;
import com.manji.backstage.vo.operation.ApoSiteContentVo;
import com.manji.backstage.vo.operation.ApoSiteVo;
import com.manji.backstage.vo.operation.AppVersionVo;
import com.manji.backstage.vo.operation.FilesVo;
import com.manji.backstage.vo.operation.MenuVo;
import com.manji.backstage.vo.operation.NavigationVo;
import com.manji.backstage.vo.operation.QrCodeVo;
import com.manji.backstage.vo.operation.ScreenVo;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationMapper mapper;
	
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

	
	
	@Override
	public Page<ApoProvince> queryApoProvince(ApoProvinceVo vo) {
		Page<ApoProvince> page =new Page<ApoProvince>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoProvince> dataList =mapper.queryApoProvince(vo);
		int totalCount =mapper.countApoProvince(vo);
		
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
		if(mapper.updApoProvince(ap)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoProvince(String province_id   ) {
		if(mapper.delApoProvince(province_id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override
	public Page<ApoCity> queryApoCity(ApoCityVo vo) {
		Page<ApoCity> page =new Page<ApoCity>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoCity> dataList =mapper.queryApoCity(vo);
		int totalCount =mapper.countApoCity(vo);
		
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
		if(mapper.updApoCity(ac)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoCity(String city_id) {
		if(mapper.delApoCity(city_id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override
	public Page<ApoCounty> queryApoCounty(ApoCountyVo vo) {
		Page<ApoCounty> page =new Page<ApoCounty>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoCounty> dataList =mapper.queryApoCounty(vo);
		int totalCount =mapper.countApoCounty(vo);
		
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
		if(mapper.updApoCounty(ac)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoCounty(String county_id) {
		if(mapper.delApoCounty(county_id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override
	public Page<ApoTown> queryApoTown(ApoTownVo vo) {
		Page<ApoTown> page =new Page<ApoTown>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoTown> dataList =mapper.queryApoTown(vo);
		int totalCount =mapper.countApoTown(vo);
		
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
		if(mapper.updApoTown(at)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoTown(String town_id) {
		if(mapper.delApoTown(town_id)>0){
			return true;	
		}
		return false;
	}
	
	
	@Override
	public Page<ApoVillage> queryApoVillage(ApoVillageVo vo) {
		Page<ApoVillage> page =new Page<ApoVillage>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ApoVillage> dataList =mapper.queryApoVillage(vo);
		int totalCount =mapper.countApoVillage(vo);
		
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
		if(mapper.updApoVillage(av)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delApoVillage(String code) {
		if(mapper.delApoVillage(code)>0){
			return true;	
		}
		return false;
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
		Page<ApoCity> page =new Page<ApoCity>();
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		List<ApoCity> dataList = mapper.findCityByProvinceId(vo);
		int totalCount =mapper.countCityByProvinceId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	

	@Override
	public Page<ApoCounty> findCountyByCityId(ApoCountyVo vo) {
		Page<ApoCounty> page =new Page<ApoCounty>();
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		List<ApoCounty> dataList = mapper.findCountyByCityId(vo);
		int totalCount =mapper.countCountyByCityId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	@Override
	public Page<ApoTown> findTownByCountyId(ApoTownVo vo) {
		Page<ApoTown> page =new Page<ApoTown>();
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		List<ApoTown> dataList = mapper.findTownByCountyId(vo);
		int totalCount =mapper.countTownByCountyId(vo);
		page.transform(totalCount, dataList);
		return page;
	}

	@Override
	public Page<ApoVillage> findVillageByTownId(ApoVillageVo vo) {
		Page<ApoVillage> page =new Page<ApoVillage>();
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		List<ApoVillage> dataList = mapper.findVillageByTownId(vo);
		int totalCount =mapper.countVillageByTownId(vo);
		page.transform(totalCount, dataList);
		return page;
	}
	
	@Override
	public Page<Files> queryFiles(FilesVo vo) {
		Page<Files> page =new Page<Files>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Files> dataList =mapper.queryFiles(vo);
		int totalCount =mapper.countFiles(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addFiles(Files files) {
		mapper.addFiles(files);
		
	}
	
	@Override
	public Files getFiles(int id) {
		
		return mapper.getFiles(id);
	}
	
	@Override
	public boolean updFiles(Files files) {
		if(mapper.updFiles(files)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delFiles(int id) {
		if(mapper.delFiles(id)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public List<Files> countFilesGroup(){
		List<Files> list =  mapper.countFilesGroup();
		return list;
	}
	
}
