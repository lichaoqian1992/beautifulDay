package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * 订单退单详情
 * Created by Administrator on 2017/1/23.
 */
@Data
public class OrderBackInfoDO {

    /**用户ID*/
    private String userId;

    /**用户类型*/
    private String userRoleType;

    /**退单编号*/
    private String orderNO;

    /**生成时间*/
    private String addTime;

    /**充值满意券*/
    private double voucher;


}
