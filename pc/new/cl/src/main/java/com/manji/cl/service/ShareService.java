package com.manji.cl.service;

import com.manji.cl.base.BaseResult;

import java.text.ParseException;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface ShareService {

    BaseResult myShare(String id,String type) throws ParseException;
}
