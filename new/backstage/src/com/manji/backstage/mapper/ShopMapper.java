package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.agent.AgentGroup;
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
@Resource
public interface ShopMapper {

	
//	dt_user_role_shopinfo	用户商家信息表
	int countShopInfo(ShopInfoVo vo);

	List<ShopInfo> queryShopInfo(ShopInfoVo vo);

	ShopInfo getShopInfo(int id);

	void addShopInfo(ShopInfo si);
	
	int updShopInfo(ShopInfo si);
	
	int delShopInfo(int id);
	
	
//	dt_user_role_shopinfo_temp	用户商家信息修改林纯表
	int countTempShopInfo(TempShopInfoVo vo);

	List<TempShopInfo> queryTempShopInfo(TempShopInfoVo vo);

	TempShopInfo getTempShopInfo(int id);
	
	void addTempShopInfo(TempShopInfo tsi);

	int updTempShopInfo(TempShopInfo tsi);
	
	int delTempShopInfo(int id);
	
	
//	dt_business_user	用户业务申购信息表

	
	int countCooperate(CooperateVo vo);

	List<Cooperate> queryCooperate(CooperateVo vo);

	void addCooperate(Cooperate coo);

	Cooperate getCooperate(int id);

	int updCooperate(Cooperate coo);

	int delCooperate(int id);

	
//	dt_user_role_shopinfo_other	用户商家其他信息素材表

	int countOtherInfo(OtherInfoVo vo);

	List<OtherInfo> queryOtherInfo(OtherInfoVo vo);

	void addOtherInfo(OtherInfo oi);

	OtherInfo getOtherInfo(int id);

	int updOtherInfo(OtherInfo oi);

	int delOtherInfo(int id);

	
	
//	dt_user_role_shopinfo_scope	用户商家经营分为表

	int countScopeInfo(ScopeInfoVo vo);

	List<ScopeInfo> queryScopeInfo(ScopeInfoVo vo);

	void addScopeInfo(ScopeInfo si);

	ScopeInfo getScopeInfo(int id);

	int updScopeInfo(ScopeInfo si);

	int delScopeInfo(int id);
	

	//dt_shop_brand(商家品牌)
	
	List<ShopBrand> queryShopBrand(ShopBrandVo vo);
	
	int countShopBrand(ShopBrandVo vo);
	
	ShopBrand getShopBrand(int id);
	
	int updShopBrand(ShopBrand art);
	
	void addShopBrand(ShopBrand art);
	
	int delShopBrand(int id);
	
	
	//dt_shop_wuliumuban(商家物流模板)
	
	List<ShopWuliumuban> queryShopWuliumuban(ShopWuliumubanVo vo);
	
	int countShopWuliumuban(ShopWuliumubanVo vo);
	
	ShopWuliumuban getShopWuliumuban(int id);
	
	int updShopWuliumuban(ShopWuliumuban art);
	
	void addShopWuliumuban(ShopWuliumuban art);
	
	int delShopWuliumuban(int id);
	
	
	//dt_shop_wuliumuban_item(商家物流模板明细)
	
	List<ShopWuliumubanItem> queryShopWuliumubanItem(ShopWuliumubanItemVo vo);
	
	int countShopWuliumubanItem(ShopWuliumubanItemVo vo);
	
	ShopWuliumubanItem getShopWuliumubanItem(int id);
	
	int updShopWuliumubanItem(ShopWuliumubanItem art);
	
	void addShopWuliumubanItem(ShopWuliumubanItem art);
	
	int delShopWuliumubanItem(int id);
	
	
	//dt_shop_zisong(商家自送)
	
	List<ShopZisong> queryShopZisong(ShopZisongVo vo);
	
	int countShopZisong(ShopZisongVo vo);
	
	ShopZisong getShopZisong(int id);
	
	int updShopZisong(ShopZisong art);
	
	void addShopZisong(ShopZisong art);
	
	int delShopZisong(int id);
	
	
	//dt_shop_expensesInfo（商家配送、退货信息表）
	
	List<ShopExpensesInfo> queryShopExpensesInfo(ShopExpensesInfoVo vo);
	
	int countShopExpensesInfo(ShopExpensesInfoVo vo);
	
	ShopExpensesInfo getShopExpensesInfo(int id);
	
	int updShopExpensesInfo(ShopExpensesInfo art);
	
	void addShopExpensesInfo(ShopExpensesInfo art);
	
	int delShopExpensesInfo(int id);
	
	
	//dt_business_sms(短信信息表)
	
	List<BusinessSms> queryBusinessSms(BusinessSmsVo vo);
	
	int countBusinessSms(BusinessSmsVo vo);
	
	BusinessSms getBusinessSms(int id);
	
	int updBusinessSms(BusinessSms art);
	
	void addBusinessSms(BusinessSms art);
	
	int delBusinessSms(int id);
	
	
	//dt_business_groupshop(托管店铺开关表)
	
	List<BusinessGroupshop> queryBusinessGroupshop(BusinessGroupshopVo vo);
	
	int countBusinessGroupshop(BusinessGroupshopVo vo);
	
	BusinessGroupshop getBusinessGroupshop(int id);
	
	int updBusinessGroupshop(BusinessGroupshop art);
	
	void addBusinessGroupshop(BusinessGroupshop art);
	
	int delBusinessGroupshop(int id);
	
	
	//dt_user_role_shopinfo_group(商家集团信身份表)
	
	List<UserRoleShopinfoGroup> queryUserRoleShopinfoGroup(UserRoleShopinfoGroupVo vo);
	
	int countUserRoleShopinfoGroup(UserRoleShopinfoGroupVo vo);
	
	UserRoleShopinfoGroup getUserRoleShopinfoGroup(int id);
	
	int updUserRoleShopinfoGroup(UserRoleShopinfoGroup art);
	
	void addUserRoleShopinfoGroup(UserRoleShopinfoGroup art);
	
	int delUserRoleShopinfoGroup(int id);
	
	
	//dt_user_role_shopinfo_grouprelation(集团托管商家店铺关联表)
	
	List<UserRoleShopinfoGrouprelation> queryUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelationVo vo);
	
	int countUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelationVo vo);
	
	UserRoleShopinfoGrouprelation getUserRoleShopinfoGrouprelation(int id);
	
	int updUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation art);
	
	void addUserRoleShopinfoGrouprelation(UserRoleShopinfoGrouprelation art);
	
	int delUserRoleShopinfoGrouprelation(int id);
	
	
	//dt_user_role_shopinfo_matter(用户商家附加信息素材表)
	
	List<UserRoleShopinfoMatter> queryUserRoleShopinfoMatter(UserRoleShopinfoMatterVo vo);
	
	int countUserRoleShopinfoMatter(UserRoleShopinfoMatterVo vo);
	
	UserRoleShopinfoMatter getUserRoleShopinfoMatter(int id);
	
	int updUserRoleShopinfoMatter(UserRoleShopinfoMatter art);
	
	void addUserRoleShopinfoMatter(UserRoleShopinfoMatter art);
	
	int delUserRoleShopinfoMatter(int id);

	List<ShopWuliumuban> findExpress();
	
	List<Cooperate> findChannelList();
}
