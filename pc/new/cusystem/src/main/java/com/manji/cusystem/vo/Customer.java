package com.manji.cusystem.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/2.
 */
@Data
public class Customer {

    //用户资料信息

    @NotNull(message = "cus_tel不能为空")
    private String cus_tel;//进线号码

    @NotNull(message = "cus_type不能为空")
    private String cus_type;//客户分类

    @NotNull(message = "cus_name不能为空")
    private String cus_name;//客户姓名

    @NotNull(message = "cus_account不能为空")
    private String cus_account;//客户账号

    @NotNull(message = "cus_mobile不能为空")
    private String cus_mobile;//联系电话

    @NotNull(message = "cus_area不能为空")
    private String cus_area;//客户区域

    private String cus_rtime;//注册时间

    private int cus_status;//账号状态

    private int cus_state;//账户状态
}
