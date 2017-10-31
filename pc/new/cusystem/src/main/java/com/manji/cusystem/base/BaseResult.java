package com.manji.cusystem.base;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public class BaseResult{

    private String code;//返回的code

    private String content;//返回的内容描述

    private Object result;//返回的对象信息

    private PageResult page;
}
