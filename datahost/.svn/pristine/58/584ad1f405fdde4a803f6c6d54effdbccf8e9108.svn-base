package com.manji.datahost.service.sqlserver.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manji.datahost.mapper.sqlserver.ClientInfoMapper;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.client.ArticleInfo;
import com.manji.datahost.model.sqlserver.client.ArticleSpec;
import com.manji.datahost.model.sqlserver.client.SafeInfo;
import com.manji.datahost.model.sqlserver.client.ShopManage;
import com.manji.datahost.model.sqlserver.client.ShopReputation;
import com.manji.datahost.model.sqlserver.client.UserBack;
import com.manji.datahost.model.sqlserver.client.UserLogin;
import com.manji.datahost.model.sqlserver.client.UserManage;
import com.manji.datahost.model.sqlserver.client.UserSafe;
import com.manji.datahost.model.sqlserver.shop.GoodsManage;
import com.manji.datahost.model.sqlserver.shop.ShopActivities;
import com.manji.datahost.model.sqlserver.user.UserAccount;
import com.manji.datahost.model.sqlserver.user.UserInfo;
import com.manji.datahost.model.sqlserver.user.UserOrder;
import com.manji.datahost.model.sqlserver.user.UserVoucher;
import com.manji.datahost.service.sqlserver.ClientInfoService;
import com.manji.datahost.vo.AccountVo;
import com.manji.datahost.vo.ActivitiesVo;
import com.manji.datahost.vo.ClientManageVo;
import com.manji.datahost.vo.GoodsManageVo;
import com.manji.datahost.vo.OrderBackVo;
import com.manji.datahost.vo.UserLoginVo;
import com.manji.datahost.vo.UserOrderVo;

@Service
public class ClientInfoServiceImpl implements ClientInfoService{
	
	@Autowired
	private ClientInfoMapper mapper;

	@Override
	public Page<UserManage> getUserManage(ClientManageVo vo) {
		
		Page<UserManage> page = new Page<UserManage>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countUserManage(vo);
		List<UserManage> list = mapper.getUserManage(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public int updAccountState(Integer user_id, int state) {

		return mapper.updAccountState(user_id, state);
	}

	@Override
	public Page<ShopManage> getShopManage(ClientManageVo vo) {
		
		Page<ShopManage> page = new Page<ShopManage>();
		if(vo.getPageNumber() == 0 || "".equals(vo.getPageNumber())){
			vo.setPageNumber(1);
			page.setPageNumber(1);
		}else{
			page.setPageNumber(vo.getPageNumber());
		}
		int count = mapper.countShopManage(vo);
		List<ShopManage> list = mapper.getShopManage(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public String getPasswordTime(Integer user_id, Integer time,String type) {

		return mapper.getPasswordTime(user_id, time, type);
	}

	@Override
	public SafeInfo findSafeInfo(Integer user_id) {

		return mapper.findSafeInfo(user_id);
	}

	@Override
	public UserSafe getUserSafe(Integer user_id) {
		
		return mapper.getUserSafe(user_id);
	}

	@Override
	public UserInfo findUserInfo(Integer user_id) {

		return mapper.findUserInfo(user_id);
	}

	@Override
	public Page<UserAccount> findUserAccount(AccountVo vo) {

		Page<UserAccount> page = new Page<UserAccount>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserAccount(vo);
		List<UserAccount> list = mapper.findUserAccount(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserVoucher> findUserVoucher(AccountVo vo) {
		
		Page<UserVoucher> page = new Page<UserVoucher>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserVoucher(vo);
		List<UserVoucher> list = mapper.findUserVoucher(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserOrder> findUserOrder(UserOrderVo vo) {

		Page<UserOrder> page = new Page<UserOrder>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserOrder(vo);
		List<UserOrder> list = mapper.findUserOrder(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserBack> findOrderBack(OrderBackVo vo) {

		Page<UserBack> page = new Page<UserBack>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countOrderBack(vo);
		List<UserBack> list = mapper.findOrderBack(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<UserLogin> findUserLogin(UserLoginVo vo) {
		
		Page<UserLogin> page = new Page<UserLogin>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countUserLogin(vo);
		List<UserLogin> list = mapper.findUserLogin(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Integer findOrderIdByOrderNo(String order_no) {

		return mapper.findOrderIdByOrderNo(order_no);
	}

	@Override
	public Integer getShopReputation(Integer shop_id) {

		return mapper.getShopReputation(shop_id);
	}

	@Override
	public ShopReputation findReputationInfo(Integer user_id) {

		return mapper.findReputationInfo(user_id);
	}

	@Override
	public Page<ShopActivities> findShopActivities(ActivitiesVo vo) {
		
		Page<ShopActivities> page = new Page<ShopActivities>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countActivities(vo);
		List<ShopActivities> list = mapper.findShopActivities(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public Page<GoodsManage> findGoodsManage(GoodsManageVo vo) {
		
		Page<GoodsManage> page = new Page<GoodsManage>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countGoodsManage(vo);
		List<GoodsManage> list = mapper.findGoodsManage(vo);
		page.transform(count, vo.getPageSize(), list);
		
		return page;
	}

	@Override
	public List<String> findSelfCategory(Integer user_id) {

		return mapper.findSelfCategory(user_id);
	}

	@Override
	public List<Object> findTitleByParentId(Integer parent_id) {

		return mapper.findTitleByParentId(parent_id);
	}

	@Override
	public Object findShopScore(Integer user_id) {
		
		return mapper.findShopScore(user_id);
	}

	@Override
	public ArticleInfo findArticleInfo(Integer article_id) {

		return mapper.findArticleInfo(article_id);
	}

	@Override
	public List<Map<String, Object>> findGoodsCategory(String[] class_list) {

		return mapper.findGoodsCategory(class_list);
	}

	@Override
	public String findDefaultCategory(Integer article_id) {

		return mapper.findDefaultCategory(article_id);
	}

	@Override
	public Map<String, Object> findGoodsDetail(Integer article_id) {

		return mapper.findGoodsDetail(article_id);
	}

	@Override
	public Object findExpress(Integer expenses_companyId) {

		return mapper.findExpress(expenses_companyId);
	}

	@Override
	public String findExpensesTemp(Integer expenses_tempId) {

		return mapper.findExpensesTemp(expenses_tempId);
	}

	@Override
	public List<ArticleSpec> findArticleSpec(Integer article_id) {

		return mapper.findArticleSpec(article_id);
	}

	@Override
	public int countHavingSell(Integer goods_id) {

		return mapper.countHavingSell(goods_id);
	}

	@Override
	public String findGoodsPics(Integer article_id) {

		return mapper.findGoodsPics(article_id);
	}

	@Override
	public List<Map<String, String>> findGoodsAlbums(Integer article_id) {

		return mapper.findGoodsAlbums(article_id);
	}

	@Override
	public Map<String,String> findCommentInfo(Integer order_id) {

		return mapper.findCommentInfo(order_id);
	}

	@Override
	public List<Map<String, Object>> findCommentScore(Integer order_id) {

		return mapper.findCommentScore(order_id);
	}

	@Override
	public List<Map<String, Object>> findCommentContent(Integer order_id) {

		return mapper.findCommentContent(order_id);
	}

	@Override
	public Map<String, String> findCompanyInfo(Integer user_id) {

		return mapper.findCompanyInfo(user_id);
	}

	@Override
	public Integer findShopIdByUserId(Integer shop_id) {

		return mapper.findShopIdByUserId(shop_id);
	}

	@Override
	public List<Map<String, String>> findCardInfo(Integer shop_id) {

		return mapper.findCardInfo(shop_id);
	}

	@Override
	public Map<String, String> findShopCard(Integer shop_id) {
		
		return mapper.findShopCard(shop_id);
	}
	
}
