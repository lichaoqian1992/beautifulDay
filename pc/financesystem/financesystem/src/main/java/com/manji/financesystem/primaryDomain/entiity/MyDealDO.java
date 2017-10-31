package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 我审批过的充值订单
 * Created by Administrator on 2017/3/11.
 */
@Data
public class MyDealDO {

    private String orderNo;

    private double rechargeMoney;

    private int userId;

    private String personApproving;

    private String checkStatus;

    private String checkTime;

    private String remark;
}
