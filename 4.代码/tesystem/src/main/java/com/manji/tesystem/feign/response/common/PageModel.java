package com.manji.tesystem.feign.response.common;

import lombok.Data;

@Data
public class PageModel {
    private int count;//总数据

    private int pageNum;//当前页

    private int totalPage;//总页数

    private int pageSize;//每页数量
}
