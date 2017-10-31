package com.manji.cusystem.dao.conversation;

import lombok.Data;

/**
 * Created by Administrator on 2017/9/6.
 * 订单外呼实体类
 *
 */
@Data
public class OrderAndCon {

    private String user_id;//下单人id

    private String user_name;//下单人账号

    private String order_id;//订单id

    private String order_no;//订单号

    private String order_amonut;//订单金额

    private String order_status;//订单状态

    private String add_time;//订单时间

    private String shop_name;//商家名称

    private String shop_tel;//商家电话

    private String last_time;//剩余时间

    private String contact_count;//联系次数

    private String is_intervention;//是否介入
}
