package com.manji.cusystem.vo.conversation;

import lombok.Data;


/**
 * Created by Administrator on 2017/8/31.
 * 新增会话信息的参数类
 */
@Data
public class ConversationVo{

    private String cus_id;

    private String cus_user_id;

    private String cus_user_name;

    private String cus_code;//话务员工号

    private String cus_ltime;//进线时间

    private String cus_from;//渠道来源

    private String cus_way;//来电方式   呼出/呼入

    private  String cus_reasonid;//原因id

    private String cus_reason;//来电原因

    private String cus_remark;//备注

    private String cus_result;//处理结果

    private String cus_ctime;//创建时间

    private String cus_isdel;//是否删除

}
