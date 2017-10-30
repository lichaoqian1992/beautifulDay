package com.manji.desystem.service;

import com.manji.desystem.dao.account.Account;

public interface FeignLoginService {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    Account login(String username, String password) throws Exception;

    /**
     * 检测sessionId有效性
     * @param sessionId
     * @return
     * @throws Exception
     */
    Account checkUser(String sessionId) throws Exception;

}