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
@FeignClient(name="datahost" ,url = "${datahost.service.url}")//name:表示调用的是那个服务，后面的url表示服务地址
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
    JSONObject findOrderDetail(@RequestParam("order_id") String orderId);

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
    @RequestMapping(value = "/sendMessage")
    JSONObject sendMessage(@RequestParam("type") String acceptType,@RequestParam("mobile")String mobile,@RequestParam("content") String content);

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
}
