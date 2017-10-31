package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * Created by pudding on 2017-2-3.
 * 用户正常充值的DO
 */
@Data
public class UserNormalRechargeDO {

    /***用户ID*/
    private String userId;

    /***第三方单号*/
    private String transactionNo;

    /***支付方式*/
    private String paymentName;

    /***支付金额*/
    private Double transactionMoney;

    /***付款用户*/
    private String userName;

    /***状态*/
    private String status;

    /***付款时间*/
    private String addTime;

    /***完成时间*/
    private String notifyTime;

    /***备注*/
    private String remark;





}
