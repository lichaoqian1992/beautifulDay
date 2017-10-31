package com.manji.backstage.mapper.shop;

import java.util.List;

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
import com.manji.backstage.model.user.User;
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

public interface ShopMapper {


	int countShopInfo(ShopInfoVo vo);

	List<ShopInfo> queryShopInfo(ShopInfoVo vo);

	ShopInfo getShopInfo(int id);

	void addShopInfo(ShopInfo si);
	
	int updShopInfo(ShopInfo si);
	
	int delShopInfo(int id);
	
	

	int countTempShopInfo(TempShopInfoVo vo);

	List<TempShopInfo> queryTempShopInfo(TempShopInfoVo vo);

	TempShopInfo getTempShopInfo(int id);
	
	void addTempShopInfo(TempShopInfo tsi);

	int updTempShopInfo(TempShopInfo tsi);
	
	int delTempShopInfo(int id);
	
	

	int countBusiness(BusinessVo vo);

	List<Business> queryBusiness(BusinessVo vo);

	void addBusiness(Business bus);

	Business getBusiness(int id);

	int updBusiness(Business bus);

	int delBusiness(int id);

	int countCooperate(CooperateVo vo);

	List<Cooperate> queryCooperate(CooperateVo vo);

	void addCooperate(Cooperate coo);

	Cooperate getCooperate(int id);

	int updCooperate(Cooperate coo);

	int delCooperate(int id);

	
	int countOtherInfo(OtherInfoVo vo);

	List<OtherInfo> queryOtherInfo(OtherInfoVo vo);

	void addOtherInfo(OtherInfo oi);

	OtherInfo getOtherInfo(int id);

	int updOtherInfo(OtherInfo oi);

	int delOtherInfo(int id);

	int countScopeInfo(ScopeInfoVo vo);

	List<ScopeInfo> queryScopeInfo(ScopeInfoVo vo);

	void addScopeInfo(ScopeInfo si);

	ScopeInfo getScopeInfo(int id);

	int updScopeInfo(ScopeInfo si);

	int delScopeInfo(int id);

	
	int countBuyerInfo(BuyerInfoVo vo);

	List<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo);

	BuyerInfo getBuyerInfo(int id);

	int updBuyerInfo(BuyerInfo bi);

	void addBuyerInfo(BuyerInfo bi);

	int delBuyerInfo(int id);

	
	
	
	
	
	int countComplaint(ComplaintVo vo);

	List<Complaint> queryComplaint(ComplaintVo vo);

	Complaint getComplaint(long id);

	int updComplaint(Complaint c);

	void addComplaint(Complaint c);

	int delComplaint(long id);

	int countCompContent(CompContentVo vo);

	List<CompContent> queryCompContent(CompContentVo vo);

	CompContent getCompContent(long id);

	int updCompContent(CompContent cc);

	void addCompContent(CompContent cc);

	int delCompContent(long id);

	int countCompHandle(CompHandleVo vo);

	List<CompHandle> queryCompHandle(CompHandleVo vo);

	CompHandle getCompHandle(long id);

	int updCompHandle(CompHandle ch);

	void addCompHandle(CompHandle ch);

	int delCompHandle(long id);
	
	
}
