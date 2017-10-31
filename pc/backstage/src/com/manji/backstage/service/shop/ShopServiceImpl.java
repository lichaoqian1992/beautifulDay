package com.manji.backstage.service.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.shop.ShopMapper;
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
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopMapper mapper;

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
	
	@Override
	public Page<Business> queryBusiness(BusinessVo vo) {
		Page<Business> page =new Page<Business>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countBusiness(vo);
		List<Business> list =mapper.queryBusiness(vo);
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
		if(mapper.updBusiness(bus)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delBusiness(int id) {
		if(mapper.delBusiness(id)>0){
			return true;
		}
		return false;
	}

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

	@Override
	public Page<BuyerInfo> queryBuyerInfo(BuyerInfoVo vo) {
		Page<BuyerInfo> page =new Page<BuyerInfo>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countBuyerInfo(vo);
		List<BuyerInfo> list =mapper.queryBuyerInfo(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public BuyerInfo getBuyerInfo(int id) {
		
		return mapper.getBuyerInfo(id);
	}

	@Override
	public boolean updBuyerInfo(BuyerInfo bi) {

		if(mapper.updBuyerInfo(bi)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addBuyerInfo(BuyerInfo bi) {
		mapper.addBuyerInfo(bi);
	}

	@Override
	public boolean delBuyerInfo(int id) {
		if(mapper.delBuyerInfo(id)>0){
			return true;
		}
		return false;
	}

	
	
	
	
	
	@Override
	public Page<Complaint> queryComplaint(ComplaintVo vo) {
		Page<Complaint> page =new Page<Complaint>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countComplaint(vo);
		List<Complaint> list =mapper.queryComplaint(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public Complaint getComplaint(long id) {
		
		return mapper.getComplaint(id);
	}

	@Override
	public boolean updComplaint(Complaint c) {
		if(mapper.updComplaint(c)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addComplaint(Complaint c) {
		mapper.addComplaint(c);
	}

	@Override
	public boolean delComplaint(long id) {
		if(mapper.delComplaint(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<CompContent> queryCompContent(CompContentVo vo) {
		Page<CompContent> page =new Page<CompContent>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countCompContent(vo);
		List<CompContent> list =mapper.queryCompContent(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public CompContent getCompContent(long id) {
		return mapper.getCompContent(id);
	}

	@Override
	public boolean updCompContent(CompContent cc) {
		if(mapper.updCompContent(cc)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addCompContent(CompContent cc) {
		mapper.addCompContent(cc);
	}

	@Override
	public boolean delCompContent(long id) {
		if(mapper.delCompContent(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<CompHandle> queryCompHandle(CompHandleVo vo) {
		Page<CompHandle> page =new Page<CompHandle>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countCompHandle(vo);
		List<CompHandle> list =mapper.queryCompHandle(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public CompHandle getCompHandle(long id) {
		
		return mapper.getCompHandle(id);
	}

	@Override
	public boolean updCompHandle(CompHandle ch) {
		if(mapper.updCompHandle(ch)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addCompHandle(CompHandle ch) {
		mapper.addCompHandle(ch);
	}

	@Override
	public boolean delCompHandle(long id) {
		if(mapper.delCompHandle(id)>0){
			return true;
		}
		return false;
	}
	
	
	
}
