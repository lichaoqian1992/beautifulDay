package com.manji.cusystem.vo;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/1.
 */
@Data
public class AddConversationVo extends PageVo {

    private String cus_from;//渠道来源

    private String cus_way;//来电方式   呼出/呼入

    private String cus_type;//客户分类

    private String stime;//开始时间

    private String etime;//结束时间
}
