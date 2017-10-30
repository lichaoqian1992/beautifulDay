package com.manji.cusystem.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/4.
 */
@Data
public class Reason {

    private int cus_id;//主键 id，自增长

    private String cus_code;//编码

    private String cus_title;//标题。描述

    private String cus_short_title;//简称

    private String cus_merge_title;//标题

    private String cus_layer;//层级

    private int cus_status;//状态
}
