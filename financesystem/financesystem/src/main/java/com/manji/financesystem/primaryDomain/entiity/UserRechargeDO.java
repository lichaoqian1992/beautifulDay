package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * 用户充值记录
 * Created by Administrator on 2017/1/23.
 */
@Data
public class UserRechargeDO {

    /**用户ID*/
    private String userId;

    /**用户类型*/
    private String userRoleType;

    /**订单编号*/
    private String rechargeNo;

    /**生成时间*/
    private String addTime;

    /**完成时间*/
    private String completeTime;

    /**充值满意券*/
    private double voucher;
}
