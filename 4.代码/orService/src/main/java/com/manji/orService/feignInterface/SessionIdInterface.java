package com.manji.orService.feignInterface;


import com.alibaba.fastjson.JSONArray;
import com.manji.orService.dao.account.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value="uservice")
public interface SessionIdInterface {

    @RequestMapping(value = "/user/logins")
    Account sessionId(@RequestParam("sessionId") String sessionId);

    @RequestMapping(value = "/user/findUserByName")
    JSONArray findByName(@RequestParam("name")String realName);

}

