package com.manji.datahost.service.sqlserver.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.datahost.mapper.sqlserver.ShopInfoMapper;
import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.shop.CommentManage;
import com.manji.datahost.model.sqlserver.shop.GoodsManage;
import com.manji.datahost.model.sqlserver.shop.ShopActivities;
import com.manji.datahost.model.sqlserver.shop.ShopInfo;
import com.manji.datahost.service.sqlserver.ShopInfoService;
import com.manji.datahost.vo.PageVo;

@Service
public class ShopInfoServiceImpl implements ShopInfoService{
	
	@Autowired
	private ShopInfoMapper mapper;

	@Override
	public ShopInfo getShopInfo(ShopInfo si) {
		
		return mapper.getShopInfo(si);
	}
	
	//优惠活动
	@Override
	public Page<ShopActivities> getShopActivities(PageVo vo) {
		Page<ShopActivities> page = new Page<ShopActivities>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countShopActivities(vo.getUser_id());
		List<ShopActivities> list = mapper.getShopActivities(vo);
		page.transform(count, vo.getPageSize(), list);
		return page;
	}

	//统计商品数
	@Override
	public int countGoods(Integer user_id,Integer status) {
		
		return mapper.countGoods(user_id,status);
	}

	//统计上架数
	@Override
	public int countPutaway(Integer user_id,Integer is_show) {

		return mapper.countPutaway(user_id,is_show);
	}

	//商品管理
	@Override
	public Page<GoodsManage> getGoodsManage(PageVo vo) {
		Page<GoodsManage> page = new Page<GoodsManage>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countGoodsManage(vo.getUser_id());
		List<GoodsManage> list = mapper.getGoodsManage(vo);
		page.transform(count, vo.getPageSize(), list);
		return page;
	}
	
	//商品分类
	@Override
	public String getCategoryTitle(String category_id) {
		
		return mapper.getCategoryTitle(category_id);
	}

	//商品评论
	@Override
	public Page<CommentManage> getCommentManage(PageVo vo) {
		Page<CommentManage> page = new Page<CommentManage>();
		page.setPageNumber(vo.getPageNumber());
		int count = mapper.countCommentManage(vo);
		List<CommentManage> list = mapper.getCommentManage(vo);
		page.transform(count, vo.getPageSize(), list);
		return page;
	}

	@Override
	public List<Map<String, String>> getShopCards(Integer user_id) {
		
		return mapper.getShopCards(user_id);
	}
	
}
