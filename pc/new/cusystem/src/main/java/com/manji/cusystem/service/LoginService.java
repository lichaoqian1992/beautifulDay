package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface LoginService {

    BaseResult checkUser(String userName,String password);
}
