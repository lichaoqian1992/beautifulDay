package com.manji.datahost.mapper.sqlserver;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.manji.datahost.model.sqlserver.shop.CommentManage;
import com.manji.datahost.model.sqlserver.shop.GoodsManage;
import com.manji.datahost.model.sqlserver.shop.ShopActivities;
import com.manji.datahost.model.sqlserver.shop.ShopInfo;
import com.manji.datahost.vo.PageVo;


@Mapper
public interface ShopInfoMapper {

	//查询商家信息
	ShopInfo getShopInfo(ShopInfo si);
	
	//商家活动
	List<ShopActivities> getShopActivities(PageVo vo);
	int countShopActivities(Integer user_id);
	
	//商品总数(可根据状态查询)
	int countGoods(@Param("user_id")Integer user_id,@Param("status")Integer status);
	
	//商品上架下架数量
	int countPutaway(@Param("user_id")Integer user_id,@Param("is_show")Integer is_show);
	
	//商品管理
	List<GoodsManage> getGoodsManage(PageVo vo);
	String getCategoryTitle(String category_id);
	int countGoodsManage(Integer user_id);
	
	//评价管理
	List<CommentManage> getCommentManage(PageVo vo);
	int countCommentManage(PageVo vo);
	
	//资质证件
	List<Map<String,String>> getShopCards(Integer user_id);
	//订单状态
	//List<ShopOrder> getShopOrder(Integer user_id);
	
	//收货地址
	//List<ShopAddress> getShopAddress(Integer user_id);
	
	//注册时间
	//List<ShopRegister> getShopRegister(String user_name);
}
