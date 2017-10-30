package com.manji.tesystem.service;

import com.manji.tesystem.feign.response.account.Account;

public interface AccountManager {
    /**
     * 登录接口
     * @return
     */
    public Account login(String username, String password)throws Exception;

    /**
     * 校验用户有效性
     * @param sessionId
     * @return
     */
    public Account checkUser(String sessionId)throws Exception;
}
