package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 订单信息
 * Created by Administrator on 2017/1/23.
 */
@Data
public class OrdersDo {

    /***订单编号*/
    private String orderNo;

     /***订单标题*/
    private String orderTitle;

     /***用户编号*/
    private String userId;

    /***用户类型*/
    private String userRoleType;

    /***满意券*/
    private double voucher;

    /***下单时间*/
    private String addTime;

    /***完成时间*/
    private String completeTime;
}
