package com.manji.cl.service;

import com.manji.cl.base.BaseResult;

import java.text.ParseException;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface UserService {

    BaseResult registerUser(String buyer_id,String user_id);

    BaseResult findUser(String id,String type) throws ParseException;
}
