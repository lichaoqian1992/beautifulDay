package com.manji.financesystem.service;

import com.manji.financesystem.responseData.AccountInfoResult;

import java.util.List;

/**
 * Created by pudding on 2017-1-17.
 * 账户信息Service
 */
public interface AccountInfoService {

    /**
     * 获取账号汇总信息
     * @return
     */
    public List<AccountInfoResult> getAccountInfo();




}
