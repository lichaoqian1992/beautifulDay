package com.manji.backstage.service.shop;


import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.shop.Business;
import com.manji.backstage.model.shop.BuyerInfo;
import com.manji.backstage.model.shop.CompContent;
import com.manji.backstage.model.shop.CompHandle;
import com.manji.backstage.model.shop.Complaint;
import com.manji.backstage.model.shop.Cooperate;
import com.manji.backstage.model.shop.OtherInfo;
import com.manji.backstage.model.shop.ScopeInfo;
import com.manji.backstage.model.shop.ShopInfo;
import com.manji.backstage.model.shop.TempShopInfo;
import com.manji.backstage.vo.shop.BusinessVo;
import com.manji.backstage.vo.shop.BuyerInfoVo;
import com.manji.backstage.vo.shop.CompContentVo;
import com.manji.backstage.vo.shop.CompHandleVo;
import com.manji.backstage.vo.shop.ComplaintVo;
import com.manji.backstage.vo.shop.CooperateVo;
import com.manji.backstage.vo.shop.OtherInfoVo;
import com.manji.backstage.vo.shop.ScopeInfoVo;
import com.manji.backstage.vo.shop.ShopInfoVo;
import com.manji.backstage.vo.shop.TempShopInfoVo;

public interface ShopService {


	Page<ShopInfo> queryShopInfo(ShopInfoVo vo);

	ShopInfo getShopInfo(int id);

	boolean updShopInfo(ShopInfo si);
	
	void addShopInfo(ShopInfo si);
	
	boolean delShopInfo(int id);

	Page<TempShopInfo> queryTempShopInfo(TempShopInfoVo vo);

	TempShopInfo getTempShopInfo(int id);

	boolean updTempShopInfo(TempShopInfo tsi);
	
	void addTempShopInfo(TempShopInfo tsi);
	
	boolean delTempShopInfo(int id);

	Page<Business> queryBusiness(BusinessVo vo);

	void addBusiness(Business bus);

	Business getBusiness(int id);

	boolean updBusiness(Business bus);

	boolean delBusiness(int id);

	Page<Cooperate> queryCooperate(CooperateVo vo);

	void addCooperate(Cooperate coo);

	Cooperate getCooperate(int id);

	boolean updCooperate(Cooperate coo);

	boolean delCooperate(int id);

	Page<OtherInfo> queryOtherInfo(OtherInfoVo vo);

	void addOtherInfo(OtherInfo oi);

	OtherInfo getOtherInfo(int id);

	boolean updOtherInfo(OtherInfo oi);

	boolean delOtherInfo(int id);

	Page<ScopeInfo> queryScopeInfo(ScopeInfoVo vo);

	void addScopeInfo(ScopeInfo si);

	ScopeInfo getScopeInfo(int id);

	boolean updScopeInfo(ScopeInfo si);

	boolean delScopeInfo(int id);

	Page<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo);

	BuyerInfo getBuyerInfo(int id);

	boolean updBuyerInfo(BuyerInfo bi);

	void addBuyerInfo(BuyerInfo bi);

	boolean delBuyerInfo(int id);

	Page<Complaint> queryComplaint(ComplaintVo vo);

	Complaint getComplaint(long id);

	boolean updComplaint(Complaint c);

	void addComplaint(Complaint c);

	boolean delComplaint(long id);

	Page<CompContent> queryCompContent(CompContentVo vo);

	CompContent getCompContent(long id);

	boolean updCompContent(CompContent cc);

	void addCompContent(CompContent cc);

	boolean delCompContent(long id);

	Page<CompHandle> queryCompHandle(CompHandleVo vo);

	CompHandle getCompHandle(long id);

	boolean updCompHandle(CompHandle ch);

	void addCompHandle(CompHandle ch);

	boolean delCompHandle(long id);
	
	
}
