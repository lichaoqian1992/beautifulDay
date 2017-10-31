package com.manji.backstage.service;

import java.util.List;

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

public interface ShopService {

	
//	dt_user_role_shopinfo	用户商家信息表

	Page<ShopInfo> queryShopInfo(ShopInfoVo vo);

	ShopInfo getShopInfo(int id);

	boolean updShopInfo(ShopInfo si);
	
	void addShopInfo(ShopInfo si);
	
	boolean delShopInfo(int id);

//	dt_user_role_shopinfo_temp	用户商家信息修改林纯表

	
	Page<TempShopInfo> queryTempShopInfo(TempShopInfoVo vo);

	TempShopInfo getTempShopInfo(int id);

	boolean updTempShopInfo(TempShopInfo tsi);
	
	void addTempShopInfo(TempShopInfo tsi);
	
	boolean delTempShopInfo(int id);
	
	
//	dt_business_user	用户业务申购信息表

	
	
	Page<Cooperate> queryCooperate(CooperateVo vo);

	void addCooperate(Cooperate coo);

	Cooperate getCooperate(int id);

	boolean updCooperate(Cooperate coo);

	boolean delCooperate(int id);


//	dt_user_role_shopinfo_other	用户商家其他信息素材表

	
	Page<OtherInfo> queryOtherInfo(OtherInfoVo vo);

	void addOtherInfo(OtherInfo oi);

	OtherInfo getOtherInfo(int id);

	boolean updOtherInfo(OtherInfo oi);

	boolean delOtherInfo(int id);

//	dt_user_role_shopinfo_scope	用户商家经营分为表
	
	Page<ScopeInfo> queryScopeInfo(ScopeInfoVo vo);

	void addScopeInfo(ScopeInfo si);

	ScopeInfo getScopeInfo(int id);

	boolean updScopeInfo(ScopeInfo si);

	boolean delScopeInfo(int id);
	

	//dt_shop_brand(商家品牌)
	
	Page<ShopBrand> queryShopBrand(ShopBrandVo vo);
	
	ShopBrand getShopBrand(int id);
	
	boolean updShopBrand(ShopBrand si);
	
	void addShopBrand(ShopBrand si);
	
	boolean delShopBrand(int id);
	
	//dt_shop_wuliumuban(商家物流模板)
	
	Page<ShopWuliumuban> queryShopWuliumuban(ShopWuliumubanVo vo);
	
	ShopWuliumuban getShopWuliumuban(int id);
	
	boolean updShopWuliumuban(ShopWuliumuban si);
	
	void addShopWuliumuban(ShopWuliumuban si);
	
	boolean delShopWuliumuban(int id);
	
	//dt_shop_wuliumuban_item(商家物流模板明细)
	
	Page<ShopWuliumubanItem> queryShopWuliumubanItem(ShopWuliumubanItemVo vo);
	
	ShopWuliumubanItem getShopWuliumubanItem(int id);
	
	boolean updShopWuliumubanItem(ShopWuliumubanItem si);
	
	void addShopWuliumubanItem(ShopWuliumubanItem si);
	
	boolean delShopWuliumubanItem(int id);
	
	//dt_shop_zisong(商家自送)
	
	Page<ShopZisong> queryShopZisong(ShopZisongVo vo);
	
	ShopZisong getShopZisong(int id);
	
	boolean updShopZisong(ShopZisong si);
	
	void addShopZisong(ShopZisong si);
	
	boolean delShopZisong(int id);
	
	//dt_shop_expensesInfo（商家配送、退货信息表）
	
	Page<ShopExpensesInfo> queryShopExpensesInfo(ShopExpensesInfoVo vo);
	
	ShopExpensesInfo getShopExpensesInfo(int id);
	
	boolean updShopExpensesInfo(ShopExpensesInfo si);
	
	void addShopExpensesInfo(ShopExpensesInfo si);
	
	boolean delShopExpensesInfo(int id);


	//dt_business_sms(短信信息表)
	
	Page<BusinessSms> queryBusinessSms(BusinessSmsVo vo);
	
	BusinessSms getBusinessSms(int id);
	
	boolean updBusinessSms(BusinessSms si);
	
	void addBusinessSms(BusinessSms si);
	
	boolean delBusinessSms(int id);
	
	//dt_business_groupshop(托管店铺开关表)
	
	Page<BusinessGroupshop> queryBusinessGroupshop(BusinessGroupshopVo vo);
	
	BusinessGroupshop getBusinessGroupshop(int id);
	
	boolean updBusinessGroupshop(BusinessGroupshop si);
	
	void addBusinessGroupshop(BusinessGroupshop si);
	
	boolean delBusinessGroupshop(int id);
	
	//dt_user_role_shopinfo_group(商家集团信身份表)
	
	Page<UserRoleShopinfoGroup> queryUserRoleShopinfoGroup(UserRoleShopinfoGroupVo vo);
	
	UserRoleShopinfoGroup getUserRoleShopinfoGroup(int id);
	
	boolean updUserRoleShopinfoGroup(UserRoleShopinfoGroup si);
	
	void addUserRoleShopinfoGroup(UserRoleShopinfoGroup si);
	
	boolean delUserRoleShopinfoGroup(int id);
	
	//dt_user_role_shopinfo_grouprelation(集团托管商家店铺关联表)
	
	Page<UserRoleShopinfoGrouprelation> queryUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelationVo vo);
	
	UserRoleShopinfoGrouprelation getUserRoleShopinfoGrouprelation(int id);
	
	boolean updUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation si);
	
	void addUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation si);
	
	boolean delUserRoleShopinfoGrouprelation(int id);
	
	//dt_user_role_shopinfo_matter(用户商家附加信息素材表)
	
	Page<UserRoleShopinfoMatter> queryUserRoleShopinfoMatter(UserRoleShopinfoMatterVo vo);
	
	UserRoleShopinfoMatter getUserRoleShopinfoMatter(int id);
	
	boolean updUserRoleShopinfoMatter(UserRoleShopinfoMatter si);
	
	void addUserRoleShopinfoMatter(UserRoleShopinfoMatter si);
	
	boolean delUserRoleShopinfoMatter(int id);
	
	List<ShopWuliumuban> findExpress();
	
	List<Cooperate> findChannelList();
}
