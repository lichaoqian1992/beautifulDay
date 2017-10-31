package com.manji.cusystem.vo.common;

import lombok.Data;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public class LogVo {

    private String cus_type;//日志类型   增删改查

    private String cus_content;//日志内容

    private int cus_user_id;//操作人id

    private String cus_user_name;//操作人名称

    private String cus_ctime;//操作时间

    private int cus_isdel;//是否删除
}
