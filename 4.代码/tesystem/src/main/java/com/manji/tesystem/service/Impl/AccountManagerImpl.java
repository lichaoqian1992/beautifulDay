package com.manji.tesystem.service.Impl;

import com.manji.tesystem.common.utils.MD5Utils;
import com.manji.tesystem.feign.response.account.Account;
import com.manji.tesystem.feign.account.AccountFeignService;
import com.manji.tesystem.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountManagerImpl implements AccountManager {
    @Value("${project.code}")
    private String projectCode;
    @Autowired
    private AccountFeignService accountFeignService;

    /**
     * 登录
     * @return
     */
    @Override
    public Account login(String username, String password) throws Exception{
        String sign = MD5Utils.formatUpper32(username + MD5Utils.formatUpper32(password) + projectCode + "manji");
        return accountFeignService.login(username,MD5Utils.formatUpper32(password),projectCode,sign);
    }

    /**
     * 校验用户有效性
     *
     * @param sessionId
     * @return
     */
    @Override
    public Account checkUser(String sessionId) throws Exception{
        return accountFeignService.checkUser(sessionId);
    }
}
