package com.manji.tesystem.feign.account;

import com.manji.tesystem.feign.response.account.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="uservice")
public interface AccountFeignService {
    /**
     * 用户登录
     * @param userName
     * @param password
     * @param code
     * @param sign
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    Account login(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("code") String code, @RequestParam("sign") String sign);

    /**
     * 校验用户有效性
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/user/logins")
    Account checkUser(@RequestParam("sessionId") String sessionId);
}
