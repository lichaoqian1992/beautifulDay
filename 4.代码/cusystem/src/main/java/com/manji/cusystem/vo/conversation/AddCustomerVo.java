package com.manji.cusystem.vo.conversation;

import lombok.Data;


/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class AddCustomerVo {

    private String cus_id;

    private String cus_tel;//进线号码

    private String cus_type;//客户分类

    private String cus_name;//客户姓名

    private String cus_account;//客户账号

    private String cus_mobile;//联系电话

    private String cus_area;//客户区域

    private String cus_user_time;//用户注册时间

    private String cus_shop_time;//用户注册时间

    private int cus_status;//账号状态

    private int cus_state;//账户状态
}
