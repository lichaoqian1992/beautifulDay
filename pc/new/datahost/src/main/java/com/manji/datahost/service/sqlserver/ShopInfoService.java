package com.manji.datahost.service.sqlserver;

import java.util.List;
import java.util.Map;

import com.manji.datahost.model.sqlserver.base.Page;
import com.manji.datahost.model.sqlserver.shop.CommentManage;
import com.manji.datahost.model.sqlserver.shop.GoodsManage;
import com.manji.datahost.model.sqlserver.shop.ShopActivities;
import com.manji.datahost.model.sqlserver.shop.ShopInfo;
import com.manji.datahost.vo.PageVo;

public interface ShopInfoService {

	//查询商家信息
	ShopInfo getShopInfo(ShopInfo si);
	
	//商家活动
	Page<ShopActivities> getShopActivities(PageVo vo);
	
	//商品总数(可根据状态查询)
	int countGoods(Integer user_id,Integer status);
	
	//商品上架下架数量
	int countPutaway(Integer user_id,Integer is_show);
	
	//商品管理
	Page<GoodsManage> getGoodsManage(PageVo vo);
	String getCategoryTitle(String category_id);
	
	//评价管理
	Page<CommentManage> getCommentManage(PageVo vo);
	
	//资质证件
	List<Map<String,String>> getShopCards(Integer user_id);
}
