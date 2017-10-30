package com.ackservice.Util;

import com.ackservice.Dto.PageResult;

/**
 * Created by Administrator on 2017/9/1.(YYR)
 */
public class PageUtils {

    public static PageResult page = new PageResult();

    public static PageResult pageInit(int pageNum, int pageSize, int count){

        page.setCount(count);
        page.setPageSize(pageSize);
        page.setPageNum(pageNum);
        page.setTotalPage(count%pageSize>0?(count/pageSize+1):count/pageSize);

        return page;
    }
}
