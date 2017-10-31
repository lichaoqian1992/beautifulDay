package com.manji.desystem.service.impl;

import com.manji.desystem.dao.account.Account;
import com.manji.desystem.feignInterface.LoginInterface;
import com.manji.desystem.service.FeignLoginService;
import com.manji.desystem.common.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FeignLoginServiceImpl implements FeignLoginService {

    @Value("${project.code}")
    private String projectCode;
    @Autowired
    private LoginInterface loginInterface;

    /**
     * 登录
     * @return
     */
    @Override
    public Account login(String username, String password) throws Exception{
        String sign = MD5Utils.formatUpper32(username + MD5Utils.formatUpper32(password) + projectCode + "manji");
        return loginInterface.login(username,MD5Utils.formatUpper32(password),projectCode,sign);
    }

    /**
     * 校验用户有效性
     *
     * @param sessionId
     * @return
     */
    @Override
    public Account checkUser(String sessionId) throws Exception{
        return loginInterface.checkUser(sessionId);
    }
}