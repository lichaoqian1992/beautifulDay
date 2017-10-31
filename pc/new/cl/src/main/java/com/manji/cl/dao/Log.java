package com.manji.cl.dao;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public class Log {

    private String cl_type;//日志类型   增删改查

    private String cl_content;//日志内容

    private int cl_operator_id;//操作人id

    private String cl_operator_name;//操作人名称

    private String cl_ctime;//操作时间

    private int cl_isdel;//是否删除
}
