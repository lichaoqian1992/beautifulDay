package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.ShopMapper;
import com.manji.backstage.model.agent.AgentGroup;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.shop.BusinessGroupshop;
import com.manji.backstage.model.shop.BusinessSms;
import com.manji.backstage.model.shop.Cooperate;
import com.manji.backstage.model.shop.OtherInfo;
import com.manji.backstage.model.shop.ScopeInfo;
import com.manji.backstage.model.shop.ShopBrand;
import com.manji.backstage.model.shop.ShopExpensesInfo;
import com.manji.backstage.model.shop.ShopInfo;
import com.manji.backstage.model.shop.ShopWuliumuban;
import com.manji.backstage.model.shop.ShopWuliumubanItem;
import com.manji.backstage.model.shop.ShopZisong;
import com.manji.backstage.model.shop.TempShopInfo;
import com.manji.backstage.model.shop.UserRoleShopinfoGroup;
import com.manji.backstage.model.shop.UserRoleShopinfoGrouprelation;
import com.manji.backstage.model.shop.UserRoleShopinfoMatter;
import com.manji.backstage.service.ShopService;
import com.manji.backstage.vo.shop.BusinessGroupshopVo;
import com.manji.backstage.vo.shop.BusinessSmsVo;
import com.manji.backstage.vo.shop.CooperateVo;
import com.manji.backstage.vo.shop.OtherInfoVo;
import com.manji.backstage.vo.shop.ScopeInfoVo;
import com.manji.backstage.vo.shop.ShopBrandVo;
import com.manji.backstage.vo.shop.ShopExpensesInfoVo;
import com.manji.backstage.vo.shop.ShopInfoVo;
import com.manji.backstage.vo.shop.ShopWuliumubanItemVo;
import com.manji.backstage.vo.shop.ShopWuliumubanVo;
import com.manji.backstage.vo.shop.ShopZisongVo;
import com.manji.backstage.vo.shop.TempShopInfoVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoGroupVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoGrouprelationVo;
import com.manji.backstage.vo.shop.UserRoleShopinfoMatterVo;
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper mapper;
	
	
	
	
//	dt_user_role_shopinfo	用户商家信息表
	@Override
	public Page<ShopInfo> queryShopInfo(ShopInfoVo vo) {
		Page<ShopInfo> page =new Page<ShopInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countShopInfo(vo);
		List<ShopInfo> list =mapper.queryShopInfo(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public ShopInfo getShopInfo(int id) {
		return mapper.getShopInfo(id);
	}

	@Override
	public boolean updShopInfo(ShopInfo si) {
		if(mapper.updShopInfo(si)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addShopInfo(ShopInfo si) {
		mapper.addShopInfo(si);
	}
	
	@Override
	public boolean delShopInfo(int id) {
		if(mapper.delShopInfo(id)>0){
			return true;
		}
		return false;
	}

//	dt_user_role_shopinfo_temp	用户商家信息修改林纯表
	
	@Override
	public Page<TempShopInfo> queryTempShopInfo(TempShopInfoVo vo) {
		Page<TempShopInfo> page =new Page<TempShopInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countTempShopInfo(vo);
		List<TempShopInfo> list =mapper.queryTempShopInfo(vo);
		page.transform(count, list);
		
		return page;
	}
	
	

	@Override
	public TempShopInfo getTempShopInfo(int id) {
		
		return mapper.getTempShopInfo(id);
	}

	@Override
	public boolean delTempShopInfo(int id) {
		if(mapper.delTempShopInfo(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addTempShopInfo(TempShopInfo TempShopInfo) {
		mapper.addTempShopInfo(TempShopInfo);
	}
	
	@Override
	public boolean updTempShopInfo(TempShopInfo TempShopInfo) {
		if(mapper.updTempShopInfo(TempShopInfo)>0){
			return true;
		}
		return false;
	}
	
	
//	dt_business_user	用户业务申购信息表

	
	@Override
	public Page<Cooperate> queryCooperate(CooperateVo vo) {
		Page<Cooperate> page =new Page<Cooperate>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countCooperate(vo);
		List<Cooperate> list =mapper.queryCooperate(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public void addCooperate(Cooperate coo) {
		mapper.addCooperate(coo);
	}

	@Override
	public Cooperate getCooperate(int id) {
		return mapper.getCooperate(id);
	}

	@Override
	public boolean updCooperate(Cooperate coo) {
		if(mapper.updCooperate(coo)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delCooperate(int id) {
		if(mapper.delCooperate(id)>0){
			return true;
		}
		return false;
	}

	
//	dt_user_role_shopinfo_other	用户商家其他信息素材表

	@Override
	public Page<OtherInfo> queryOtherInfo(OtherInfoVo vo) {
		Page<OtherInfo> page =new Page<OtherInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countOtherInfo(vo);
		List<OtherInfo> list =mapper.queryOtherInfo(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public void addOtherInfo(OtherInfo oi) {
		mapper.addOtherInfo(oi);
	}

	@Override
	public OtherInfo getOtherInfo(int id) {
		return mapper.getOtherInfo(id);
	}

	@Override
	public boolean updOtherInfo(OtherInfo oi) {
		if(mapper.updOtherInfo(oi)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delOtherInfo(int id) {
		if(mapper.delOtherInfo(id)>0){
			return true;
		}
		return false;
	}

//	dt_user_role_shopinfo_scope	用户商家经营分为表

	
	@Override
	public Page<ScopeInfo> queryScopeInfo(ScopeInfoVo vo) {
		Page<ScopeInfo> page =new Page<ScopeInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countScopeInfo(vo);
		List<ScopeInfo> list =mapper.queryScopeInfo(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public void addScopeInfo(ScopeInfo si) {
		mapper.addScopeInfo(si);
	}

	@Override
	public ScopeInfo getScopeInfo(int id) {
		return mapper.getScopeInfo(id);
	}

	@Override
	public boolean updScopeInfo(ScopeInfo si) {
		if(mapper.updScopeInfo(si)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delScopeInfo(int id) {
		if(mapper.delScopeInfo(id)>0){
			return true;
		}
		return false;
	}
	

	//dt_shop_brand(商家品牌)
	
	@Override
	public Page<ShopBrand> queryShopBrand(ShopBrandVo vo) {
		Page<ShopBrand> page =new Page<ShopBrand>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countShopBrand(vo);
		List<ShopBrand> list =mapper.queryShopBrand(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addShopBrand(ShopBrand og) {
		mapper.addShopBrand(og);
	}
	
	@Override
	public ShopBrand getShopBrand(int id) {
		return mapper.getShopBrand(id);
	}
	
	@Override
	public boolean updShopBrand(ShopBrand og) {
		if(mapper.updShopBrand(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delShopBrand(int id) {
		if(mapper.delShopBrand(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_shop_wuliumuban(商家物流模板)
	
	@Override
	public Page<ShopWuliumuban> queryShopWuliumuban(ShopWuliumubanVo vo) {
		Page<ShopWuliumuban> page =new Page<ShopWuliumuban>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countShopWuliumuban(vo);
		List<ShopWuliumuban> list =mapper.queryShopWuliumuban(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addShopWuliumuban(ShopWuliumuban og) {
		mapper.addShopWuliumuban(og);
	}
	
	@Override
	public ShopWuliumuban getShopWuliumuban(int id) {
		return mapper.getShopWuliumuban(id);
	}
	
	@Override
	public boolean updShopWuliumuban(ShopWuliumuban og) {
		if(mapper.updShopWuliumuban(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delShopWuliumuban(int id) {
		if(mapper.delShopWuliumuban(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_shop_wuliumuban_item(商家物流模板明细)
	
	@Override
	public Page<ShopWuliumubanItem> queryShopWuliumubanItem(ShopWuliumubanItemVo vo) {
		Page<ShopWuliumubanItem> page =new Page<ShopWuliumubanItem>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countShopWuliumubanItem(vo);
		List<ShopWuliumubanItem> list =mapper.queryShopWuliumubanItem(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addShopWuliumubanItem(ShopWuliumubanItem og) {
		mapper.addShopWuliumubanItem(og);
	}
	
	@Override
	public ShopWuliumubanItem getShopWuliumubanItem(int id) {
		return mapper.getShopWuliumubanItem(id);
	}
	
	@Override
	public boolean updShopWuliumubanItem(ShopWuliumubanItem og) {
		if(mapper.updShopWuliumubanItem(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delShopWuliumubanItem(int id) {
		if(mapper.delShopWuliumubanItem(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_shop_zisong(商家自送)
	
	@Override
	public Page<ShopZisong> queryShopZisong(ShopZisongVo vo) {
		Page<ShopZisong> page =new Page<ShopZisong>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countShopZisong(vo);
		List<ShopZisong> list =mapper.queryShopZisong(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addShopZisong(ShopZisong og) {
		mapper.addShopZisong(og);
	}
	
	@Override
	public ShopZisong getShopZisong(int id) {
		return mapper.getShopZisong(id);
	}
	
	@Override
	public boolean updShopZisong(ShopZisong og) {
		if(mapper.updShopZisong(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delShopZisong(int id) {
		if(mapper.delShopZisong(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_shop_expensesInfo（商家配送、退货信息表）
	
	@Override
	public Page<ShopExpensesInfo> queryShopExpensesInfo(ShopExpensesInfoVo vo) {
		Page<ShopExpensesInfo> page =new Page<ShopExpensesInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countShopExpensesInfo(vo);
		List<ShopExpensesInfo> list =mapper.queryShopExpensesInfo(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addShopExpensesInfo(ShopExpensesInfo og) {
		mapper.addShopExpensesInfo(og);
	}
	
	@Override
	public ShopExpensesInfo getShopExpensesInfo(int id) {
		return mapper.getShopExpensesInfo(id);
	}
	
	@Override
	public boolean updShopExpensesInfo(ShopExpensesInfo og) {
		if(mapper.updShopExpensesInfo(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delShopExpensesInfo(int id) {
		if(mapper.delShopExpensesInfo(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_business_sms(短信信息表)
	
	@Override
	public Page<BusinessSms> queryBusinessSms(BusinessSmsVo vo) {
		Page<BusinessSms> page =new Page<BusinessSms>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countBusinessSms(vo);
		List<BusinessSms> list =mapper.queryBusinessSms(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addBusinessSms(BusinessSms og) {
		mapper.addBusinessSms(og);
	}
	
	@Override
	public BusinessSms getBusinessSms(int id) {
		return mapper.getBusinessSms(id);
	}
	
	@Override
	public boolean updBusinessSms(BusinessSms og) {
		if(mapper.updBusinessSms(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delBusinessSms(int id) {
		if(mapper.delBusinessSms(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_business_groupshop(托管店铺开关表)
	
	@Override
	public Page<BusinessGroupshop> queryBusinessGroupshop(BusinessGroupshopVo vo) {
		Page<BusinessGroupshop> page =new Page<BusinessGroupshop>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countBusinessGroupshop(vo);
		List<BusinessGroupshop> list =mapper.queryBusinessGroupshop(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addBusinessGroupshop(BusinessGroupshop og) {
		mapper.addBusinessGroupshop(og);
	}
	
	@Override
	public BusinessGroupshop getBusinessGroupshop(int id) {
		return mapper.getBusinessGroupshop(id);
	}
	
	@Override
	public boolean updBusinessGroupshop(BusinessGroupshop og) {
		if(mapper.updBusinessGroupshop(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delBusinessGroupshop(int id) {
		if(mapper.delBusinessGroupshop(id)>0){
			return true;
		}
		return false;
	}
	

	//dt_user_role_shopinfo_group(商家集团信身份表)
	
	@Override
	public Page<UserRoleShopinfoGroup> queryUserRoleShopinfoGroup(UserRoleShopinfoGroupVo vo) {
		Page<UserRoleShopinfoGroup> page =new Page<UserRoleShopinfoGroup>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleShopinfoGroup(vo);
		List<UserRoleShopinfoGroup> list =mapper.queryUserRoleShopinfoGroup(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleShopinfoGroup(UserRoleShopinfoGroup og) {
		mapper.addUserRoleShopinfoGroup(og);
	}
	
	@Override
	public UserRoleShopinfoGroup getUserRoleShopinfoGroup(int id) {
		return mapper.getUserRoleShopinfoGroup(id);
	}
	
	@Override
	public boolean updUserRoleShopinfoGroup(UserRoleShopinfoGroup og) {
		if(mapper.updUserRoleShopinfoGroup(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleShopinfoGroup(int id) {
		if(mapper.delUserRoleShopinfoGroup(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_user_role_shopinfo_grouprelation(集团托管商家店铺关联表)
	
	@Override
	public Page<UserRoleShopinfoGrouprelation> queryUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelationVo vo) {
		Page<UserRoleShopinfoGrouprelation> page =new Page<UserRoleShopinfoGrouprelation>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleShopinfoGrouprelation(vo);
		List<UserRoleShopinfoGrouprelation> list =mapper.queryUserRoleShopinfoGrouprelation(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation og) {
		mapper.addUserRoleShopinfoGrouprelation(og);
	}
	
	@Override
	public UserRoleShopinfoGrouprelation getUserRoleShopinfoGrouprelation(int id) {
		return mapper.getUserRoleShopinfoGrouprelation(id);
	}
	
	@Override
	public boolean updUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation og) {
		if(mapper.updUserRoleShopinfoGrouprelation(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleShopinfoGrouprelation(int id) {
		if(mapper.delUserRoleShopinfoGrouprelation(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	//dt_user_role_shopinfo_matter(用户商家附加信息素材表)
	
	@Override
	public Page<UserRoleShopinfoMatter> queryUserRoleShopinfoMatter(UserRoleShopinfoMatterVo vo) {
		Page<UserRoleShopinfoMatter> page =new Page<UserRoleShopinfoMatter>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countUserRoleShopinfoMatter(vo);
		List<UserRoleShopinfoMatter> list =mapper.queryUserRoleShopinfoMatter(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addUserRoleShopinfoMatter(UserRoleShopinfoMatter og) {
		mapper.addUserRoleShopinfoMatter(og);
	}
	
	@Override
	public UserRoleShopinfoMatter getUserRoleShopinfoMatter(int id) {
		return mapper.getUserRoleShopinfoMatter(id);
	}
	
	@Override
	public boolean updUserRoleShopinfoMatter(UserRoleShopinfoMatter og) {
		if(mapper.updUserRoleShopinfoMatter(og)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delUserRoleShopinfoMatter(int id) {
		if(mapper.delUserRoleShopinfoMatter(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public List<ShopWuliumuban> findExpress() {
		// TODO Auto-generated method stub
		return mapper.findExpress();
	}

	@Override
	public List<Cooperate> findChannelList() {
		// TODO Auto-generated method stub
		return mapper.findChannelList();
	}

	
	
}
