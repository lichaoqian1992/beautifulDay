package com.manji.cl.service;

import com.manji.cl.base.BaseResult;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface CountService {

    BaseResult getUserCount(String id,String stime,String etime);
}
