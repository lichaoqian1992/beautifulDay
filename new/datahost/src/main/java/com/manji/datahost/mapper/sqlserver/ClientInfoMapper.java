package com.manji.datahost.mapper.sqlserver;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
import com.manji.datahost.vo.AccountVo;
import com.manji.datahost.vo.ActivitiesVo;
import com.manji.datahost.vo.ClientManageVo;
import com.manji.datahost.vo.GoodsManageVo;
import com.manji.datahost.vo.OrderBackVo;
import com.manji.datahost.vo.UserLoginVo;
import com.manji.datahost.vo.UserOrderVo;

@Mapper
public interface ClientInfoMapper {

	//查询用户信息
	List<UserManage> getUserManage(ClientManageVo vo);
	int countUserManage(ClientManageVo vo);
	
	//解冻，冻结账户
	int updAccountState(@Param("user_id")Integer user_id,@Param("state")int state);
	
	//查询商家信息
	List<ShopManage> getShopManage(ClientManageVo vo);
	int countShopManage(ClientManageVo vo);
	
	////////////用户详情////////////////
	
	//安全信息
	SafeInfo findSafeInfo(Integer user_id);
	
	//安全资料
	UserSafe getUserSafe(Integer user_id);
	
	//查询密码更新时间
	String getPasswordTime(@Param("user_id")Integer user_id,@Param("time")Integer time,@Param("type")String type);
	
	//用户信息
	UserInfo findUserInfo(Integer user_id);
	
	//账户信息
	List<UserAccount> findUserAccount(AccountVo vo);
	//总页数
	int countUserAccount(AccountVo vo);
	
	//满意券信息
	List<UserVoucher> findUserVoucher(AccountVo vo);
	int countUserVoucher(AccountVo vo);
	
	//订单
	List<UserOrder> findUserOrder(UserOrderVo vo);
	int countUserOrder(UserOrderVo vo);
	
	//退单
	List<UserBack> findOrderBack(OrderBackVo vo);
	int countOrderBack(OrderBackVo vo);
	
	//登录
	List<UserLogin> findUserLogin(UserLoginVo vo);
	int countUserLogin(UserLoginVo vo);
	
	//根据订单号查订单id
	Integer findOrderIdByOrderNo(String order_no);
	
	//信誉值
	Integer getShopReputation(Integer shop_id);
	
	//商家信誉信息
	ShopReputation findReputationInfo(Integer user_id);
	
	//商家活动
	List<ShopActivities> findShopActivities(ActivitiesVo vo);
	int countActivities(ActivitiesVo vo);
	
	//商品管理
	List<GoodsManage> findGoodsManage(GoodsManageVo vo);
	int countGoodsManage(GoodsManageVo vo);
	
	//自定义分类
	List<String> findSelfCategory(Integer user_id);
	
	//根据分类的Parent_id查询title
	List<Object> findTitleByParentId(Integer parent_id);
	
	//店铺评分
	Object findShopScore(Integer user_id);
	
	//商品信息
	ArticleInfo findArticleInfo(Integer article_id);
	
	//商品分类
	List<Map<String,Object>> findGoodsCategory(String[] class_list);
	
	//默认分类
	String findDefaultCategory(Integer article_id);
	
	//商品详情
	Map<String,Object> findGoodsDetail(Integer article_id);
	
	//物流公司
	Object findExpress(Integer expenses_companyId);
	
	//物流模板
	String findExpensesTemp(Integer expenses_tempId);
	
	//商品规格
	List<ArticleSpec> findArticleSpec(Integer article_id);
	
	//已售数量
	int countHavingSell(Integer goods_id);
	
	//商品图片
	String findGoodsPics(Integer article_id);
	
	//商品相册
	List<Map<String,String>> findGoodsAlbums(Integer article_id);
	
	//评价基本信息
	Map<String,String> findCommentInfo(Integer order_id);
	
	//评分信息
	List<Map<String,Object>> findCommentScore(Integer order_id);
	
	//评论内容
	List<Map<String,Object>> findCommentContent(Integer order_id);
	
	//商家id
	Integer findShopIdByUserId(Integer shop_id);
	
	//公司信息
	Map<String,String> findCompanyInfo(Integer user_id);
	
	//证件信息
	List<Map<String,String>> findCardInfo(Integer shop_id);
	
	//店铺证件信息
	Map<String,String> findShopCard(Integer shop_id);
	
}
