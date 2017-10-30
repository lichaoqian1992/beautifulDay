package com.manji.cusystem.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/8/31.
 * 新增会话信息的参数类
 */
@Data
public class ConversationVo{

    private  String cus_no;//会话单号

    @NotNull(message = "cus_user_id不能为空")
    private int cus_user_id;//话务员id

    @NotNull(message = "cus_user_name不能为空")
    private String cus_user_name;//话务员名称

    @NotNull(message = "cus_ltime不能为空")
    private String cus_ltime;//进线时间

    @NotNull(message = "cus_from不能为空")
    private String cus_from;//渠道来源

    @NotNull(message = "cus_way不能为空")
    private String cus_way;//来电方式   呼出/呼入

    @NotNull(message = "cus_reason不能为空")
    private String cus_reason;//来电原因

    private String cus_remark;//备注

    private String cus_ctime;//创建时间

    private String cus_isdel;//是否删除

}
