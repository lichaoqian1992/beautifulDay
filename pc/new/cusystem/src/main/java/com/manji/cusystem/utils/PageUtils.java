package com.manji.cusystem.utils;

import com.manji.cusystem.base.PageResult;

/**
 * Created by Administrator on 2017/9/1.
 */
public class PageUtils {

    public static PageResult page = new PageResult();

    public static PageResult pageInit(int pageNum, int pageSize, int count){

        page.setCount(count);
        page.setPageSize(pageSize);
        page.setPageNum(pageNum+1);
        page.setTotalPage(count%pageSize>0?(count/pageSize+1):count/pageSize);

        return page;
    }
}
