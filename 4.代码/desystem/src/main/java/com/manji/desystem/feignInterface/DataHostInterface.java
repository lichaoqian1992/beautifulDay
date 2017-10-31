package com.manji.desystem.feignInterface;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "datahost")
public interface DataHostInterface {

    /**
     * 根据账号查询客户相关信息
     *
     * @param userName
     * @param mobile
     * @param shopName
     * @return
     */
    @RequestMapping(value = "/getUserInfo")
    JSONObject getAccountByName(@RequestParam("user_name") String userName, @RequestParam("mobile") String mobile, @RequestParam("name") String shopName);

    /**
     * 获取商家信息
     *
     * @param userName
     * @param mobile
     * @param shopName
     * @return
     */
    @RequestMapping(value = "/getShopInfo")
    JSONObject getShopInfo(@RequestParam("user_name") String userName, @RequestParam("mobile") String mobile, @RequestParam("name") String shopName);

    /**
     * 获取合伙人信息
     *
     * @param userName
     * @param mobile
     * @param shopName
     * @return
     */
    @RequestMapping(value = "/getAgentInfo")
    JSONObject getAgentInfo(@RequestParam("user_name") String userName, @RequestParam("mobile") String mobile, @RequestParam("name") String shopName);

    /**
     * 获取其他信息
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getOtherInfo")
    JSONObject getOtherInfo(@RequestParam("mobile") String mobile);

    /**
     * 根据姓名或者店铺名称查询账号
     * @param personName
     * @return
     */
    @RequestMapping(value = "/getUserNameByPersonName")
    JSONObject getUserNameByPersonName(@RequestParam("person_name") String personName);

}
