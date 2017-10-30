package com.manji.cusystem.vo;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
public class OrderVo {

    private Integer pageNumber;
    private Integer pageSize;
    private String start_time;
    private String end_time;
    private String keyword;
    private String type;
}
