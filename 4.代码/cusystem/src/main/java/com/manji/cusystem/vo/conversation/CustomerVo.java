package com.manji.cusystem.vo.conversation;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/2.
 */
@Data
public class CustomerVo {

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

    private String cus_user_time;//用户注册时间

    private String cus_shop_time;//用户注册时间

    private int cus_status;//账号状态

    private int cus_state;//账户状态

    @NotNull(message = "参数cus_ltime不能为空")
    private String cus_ltime;//进线时间

    @NotNull(message = "cus_from不能为空")
    private String cus_from;//渠道来源

    @NotNull(message = "cus_way不能为空")
    private String cus_way;//来电方式   呼出/呼入

    @NotNull(message = "cus_reasonid不能为空")
    private  String cus_reasonid;//来电原因id

    @NotNull(message = "cus_reason不能为空")
    private String cus_reason;//来电原因

    private String cus_remark;//备注

    private String cus_ctime;//创建时间

    private String cus_isdel;//是否删除

    private String cus_info_id;//资料id
}
