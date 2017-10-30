package com.manji.cusystem.dao;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 * 返回数据实体类
 */
@Data
public class Conversation{

    private int cus_id;

    private String cus_no;//话务单号

    private int cus_user_id;//话务员id

    private String cus_user_name;//话务员名称

    private String cus_code;//工号

    private String cus_tel;//进线号码

    private String cus_ltime;//进线时间

    private String cus_from;//渠道来源

    private String cus_way;//来电方式   呼出/呼入

    private String cus_type;//客户分类

    private String cus_name;//客户姓名

    private String cus_account;//客户账号

    private String cus_mobile;//联系电话

    private String cus_area;//客户区域

    private String cus_reasonid;//来电原因id

    private String cus_reason;//来电原因

    private String cus_remark;//备注

    private String cus_ctime;//创建时间

    private String cus_isdel;//是否删除

    private String cus_rtime;//注册时间

    private int cus_status;//账号状态

    private int cus_state;//账户状态

    private String ctype;//是否有工单

    private String cus_result;//处理结果

    private String cus_cid;//工单id

    private List<String> sheetNo;//工单编号

    private String isEdit;//是否可以编辑



}
