package com.manji.financesystem.secondaryDomain.entity.extra;

import lombok.Data;

/**
 * 审批人和审批总金额
 * Created by Administrator on 2017/2/27.
 */
@Data
public class RechargeMoneyAndPersonDO {
    /**审批总金额*/
    private double acountMoney;

    /**审批人电话号码*/
    private String mobile;
}
