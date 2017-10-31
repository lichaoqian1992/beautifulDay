package com.manji.cusystem.base;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/1.
 */
@Data
public class PageResult {

    private int count;//总数据

    private int pageNum;//当前页

    private int totalPage;//总页数

    private int pageSize;//每页数量
}
