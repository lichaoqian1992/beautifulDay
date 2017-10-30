package com.manji.cusystem.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.vo.OrderVo;
import com.manji.cusystem.vo.conversation.OrderAndConVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/8/30.
 */
@FeignClient(value="datahost")//value:表示调用的是eureka上面的那个服务，后面的url表示服务地址 ,url = "${datahost.service.url}"
public interface DataHostFeignService {

    @RequestMapping(value = "/getRegister",method = RequestMethod.GET)//value:表示服务上面的接口名称
    String getUserRegister(@RequestParam("user_name") String user_name,@RequestParam("type") String type);

    /**
     * 根据sessionId获取账户信息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/getSession")
    String getAccount(@RequestParam("sessionId") String sessionId);

    /**
     * 根据账号查询客户相关信息
     * @param cus_name
     * @return
     */
    @RequestMapping(value = "/getUserInfo")
    JSONObject getAccountByName(@RequestParam("user_name") String cus_name, @RequestParam("mobile") String mobile, @RequestParam("name") String name);

    /**
     * 获取商家信息
     * @param cus_name
     * @param mobile
     * @param name
     * @return
     */
    @RequestMapping(value = "/getShopInfo")
    JSONObject getShopInfo(@RequestParam("user_name") String cus_name, @RequestParam("mobile") String mobile, @RequestParam("name") String name);

    /**
     * 获取合伙人信息
     * @param cus_name
     * @param mobile
     * @param name
     * @return
     */
    @RequestMapping(value = "/getAgentInfo")
    JSONObject getAgentInfo(@RequestParam("user_name") String cus_name, @RequestParam("mobile") String mobile, @RequestParam("name") String name);

    /**
     * 获取其他信息
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getOtherInfo")
    JSONObject getOtherInfo(@RequestParam("mobile") String mobile);
    /**
     * 查询用户的流水、满意券、订单、收货地址、工单、来电记录
     */
    @RequestMapping(value = "/getUserDetail")
    JSONObject getUserAccount(@RequestParam("mobile") String mobile,@RequestParam("user_id") String user_id, @RequestParam("type") String type, @RequestParam("pageSize") String pageSize, @RequestParam("pageNumber") String pageNumber);

    /**
     * 查询商家的流水、满意券、订单、收货地址、工单、来电记录
     */
    @RequestMapping(value = "/getShopDetail")
    JSONObject getShopDetail(@RequestParam("mobile") String mobile,@RequestParam("user_id") String user_id, @RequestParam("type") String type, @RequestParam("pageSize") String pageSize, @RequestParam("pageNumber") String pageNumber);

    /**
     * 查询代发货的订单信息
     * @param jsonToString
     * @return
     */
    @RequestMapping(value = "/getOrderInfo")
    JSONObject findOrderAndCon(@RequestParam("jsonToString") String jsonToString);

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping(value = "/getOrderDetail")
    JSONObject findOrderDetail(@RequestParam("order_id") int orderId);

    /**
     * 查询退单详情
     * @return
     */
    @RequestMapping(value = "/getOrderBackDetail")
    JSONObject getOrderBackDetail(@RequestParam("order_no") String orderId);

    /**
     * 查询接收对象的数量
     * @param acceptType
     * @return
     */
    @RequestMapping(value = "/getCount")
    JSONObject getCount(@RequestParam("acceptType") String acceptType,@RequestParam("type")String type);

    /**
     * 发送短信
     * @param acceptType
     * @return
     */
    @RequestMapping(value = "/TimingTaskSend")
    JSONObject sendMessage(@RequestParam("pass") String acceptType,@RequestParam("mobiles")String mobile,@RequestParam("content") String content);

    /**
     * 获取电话号码或者邮箱
     * @param cusAcceptType  可能都是电话号码以逗号分割，或者Buyer\Shop\Agent
     * @return
     */
    @RequestMapping(value = "/getMobileInfo")
    JSONObject getMobileInfo(@RequestParam("user_role") String cusAcceptType,@RequestParam("type")String type);

    /**
     * 代理商详情
     * @param mobile
     * @param
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/getAgentDetail")
    JSONObject getAgentDetail(@RequestParam("mobile") String mobile,@RequestParam("user_id") String user_id, @RequestParam("type") String type, @RequestParam("pageSize") String pageSize, @RequestParam("pageNumber") String pageNumber);

    /**
     * 其他详情
     * @param mobile
     * @param
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/getOtherDetail")
    JSONObject getOtherDetail(@RequestParam("mobile") String mobile,@RequestParam("user_id") String user_id, @RequestParam("type") String type, @RequestParam("pageSize") String pageSize, @RequestParam("pageNumber") String pageNumber);

    /**
     * 根据姓名或者店铺名称查询账号
     * @param personName
     * @return
     */
    @RequestMapping(value = "/getUserNameByPersonName")
    JSONObject getUserNameByPersonName(@RequestParam("person_name") String personName);

    /**
     * 查询用户列表
     * @param pageNum
     * @param pageSize
     * @param rstime
     * @param retime
     * @param lstime
     * @param letime
     * @param other
     * @return
     */
    @RequestMapping(value = "/getUserManage")
    JSONObject findAccountUser(@RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("reg_start_time") String rstime,
                           @RequestParam("reg_end_time") String retime, @RequestParam("login_start_time") String lstime, @RequestParam("login_end_time") String letime,
                           @RequestParam("key_word") String other);


    /**
     * 查询商家列表
     * @param pageNum
     * @param pageSize
     * @param rstime
     * @param retime
     * @param lstime
     * @param letime
     * @param other
     * @return
     */
    @RequestMapping(value = "/getShopManage")
    JSONObject findAccountShop(@RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("reg_start_time") String rstime,
                               @RequestParam("reg_end_time") String retime, @RequestParam("login_start_time") String lstime, @RequestParam("login_end_time") String letime,
                               @RequestParam("key_word") String other);


    /**
     * 获取用户详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserManageDetail")
    JSONObject getUserManageDetail(@RequestParam("user_id") int userId);

    /**
     * 获取商家详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getShopManageDetail")
    JSONObject getShopManageDetail(@RequestParam("shop_id") int userId);

    /**
     * 获取满意券和账户流水
     * @param userId
     * @param user_type
     * @param pageNum
     * @param pageSize
     * @param stime
     * @param etime
     * @param type
     * @return
     */
    @RequestMapping(value = "/findUserAccount")
    JSONObject getAccountSerial(@RequestParam("user_id") int userId, @RequestParam("user_type") String user_type, @RequestParam("pageNumber") int pageNum,
                                @RequestParam("pageSize") int pageSize, @RequestParam("start_time") String stime,@RequestParam("end_time") String etime,
                                @RequestParam("type") String type);

    /**
     * 订单列表
     * @param userId
     * @param status
     * @param pay_status
     * @param order_no
     * @param stime
     * @param etime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findUserOrder")
    JSONObject findUserOrder(@RequestParam("user_id")int userId, @RequestParam("status") String status, @RequestParam("payment_status") String pay_status,
                             @RequestParam("order_no") String order_no, @RequestParam("start_time") String stime, @RequestParam("end_time") String etime,
                             @RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize);

    /**
     * 退单信息
     * @param userId
     * @param status
     * @param type
     * @param order_no
     * @param stime
     * @param etime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/findOrderBack")
    JSONObject findOrderBack(@RequestParam("user_id")int userId, @RequestParam("status") String status, @RequestParam("back_goods")String type,
                             @RequestParam("order_no") String order_no, @RequestParam("start_time") String stime, @RequestParam("end_time") String etime,
                             @RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize);

    /**
     * 用户登录日志
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param stime
     * @param etime
     * @return
     */
    @RequestMapping(value = "/findUserLogin")
    JSONObject getUserLoginLogs(@RequestParam("user_id")int userId, @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNum,
                                @RequestParam("start_time") String stime, @RequestParam("end_time") String etime);


    /**
     * 商家活动
     * @param status
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param stime
     * @param etime
     * @param type
     * @param order_no
     * @return
     */
    @RequestMapping(value = "/findShopActivities")
    JSONObject findShopActivities(@RequestParam("status")String status, @RequestParam("user_id")int userId, @RequestParam("pageNumber")int pageNum,
                                  @RequestParam("pageSize") int pageSize, @RequestParam("start_time") String stime, @RequestParam("end_time") String etime,
                                  @RequestParam("act_type") String type, @RequestParam("title") String order_no);

    /**
     * 商家评价管理
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param stime
     * @param etime
     * @return
     */
    @RequestMapping(value = "/findCommentManage")
    JSONObject findCommentManage(@RequestParam("user_id") int userId, @RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize,
                                 @RequestParam("start_time") String stime, @RequestParam("end_time") String etime);

    /**
     * 商品管理
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param type    系统分类
     * @param pay_status  自定义分类
     * @param status     商品状态
     * @return
     */
    @RequestMapping(value = "/findGoodsManage")
    JSONObject findGoodsManage(@RequestParam("user_id") int userId, @RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize,
                               @RequestParam("title") String type, @RequestParam("self_title") String pay_status, @RequestParam("is_show") String status);


    /**
     * 商品详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findGoodsDetail")
    JSONObject findGoodsDetail(@RequestParam("article_id") int userId);

    /**
     * 图文详情
     * @param userId
     * @return
     */
    @RequestMapping(value = "/findGoodsPics")
    JSONObject findGoodsPics(@RequestParam("article_id") int userId);
    /**
     * 商品管理数量统计
     * @param userId
     * @return
     */
    @RequestMapping(value = "/totalArticle")
    JSONObject totalArticle(@RequestParam("user_id") int userId);

    /**
     * 资质公司信息
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/findAptitudeInfo")
    JSONObject findAptitudeInfo(@RequestParam("shop_id") String shopId);

    /**
     * 店铺详情
     * @param shopId
     * @return
     */
    @RequestMapping(value = "/findShopCard")
    JSONObject findShopCard(@RequestParam("shop_id") String shopId);

    /**
     * 评价详情
     * @param order_id
     * @return
     */
    @RequestMapping(value = "/findCommentInfo")
    JSONObject findCommentInfo(@RequestParam("order_id")int order_id);

    /**
     * 重置商家密码
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/UserRePassword")
    JSONObject updatePassword(@RequestParam("account") String account,@RequestParam("newPassword") String password);

    /**
     * 冻结账户
     * @param userId
     * @param roleType
     * @param roleValue
     * @param mobile
     * * @param content
     * @return
     */
    @RequestMapping(value = "/AccountStateFrozen")
    JSONObject thawAccount(@RequestParam("user_id") String userId, @RequestParam("role_type") String roleType,
                           @RequestParam("role_value") String roleValue, @RequestParam("mobile") String mobile, @RequestParam("content") String content);

    /**
     * 解冻账户
     * @param userId
     * @param roleType
     * @param roleValue
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/AccountStateUnFrozen")
    JSONObject frozenAccount(@RequestParam("user_id") String userId, @RequestParam("role_type") String roleType,
                             @RequestParam("role_value") String roleValue, @RequestParam("mobile") String mobile);
}
