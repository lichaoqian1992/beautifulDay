package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

/**
 * 同步提现表
 * Created by pudding on 2017-3-16.
 */
@Data
public class TUserWithdrawals {

    private Long id;

    /***用户ID*/
    private String userId;

    /***用户角色类型*/
    private String userRoleTyoe;

    /***用户角色值*/
    private Integer userRoleValue;

    /***提现账户ID*/
    private Integer accountId;

    /***提现订单号*/
    private String withdrawalsNo;

    /***银行卡名称*/
    private String bankName;

    /***银行卡卡号*/
    private String bankCard;

    /***银行卡地区*/
    private String bankArea;

    /***详细开户地址*/
    private String bankAddress;

    /***提现金额*/
    private Double amount;

    /***提现状态*/
    private String status;

    /***生成时间*/
    private String add_time;

    /***完成时间*/
    private String complete_time;

    /***三方支付交易号*/
    private String transactionNo;

    /***提现处理说明*/
    private String remark;

    /***是否删除*/
    private String isDel;

    /***提现手续费*/
    private Double commission;

    /***开户姓名*/
    private String bank_user;

    /***提现满意券*/
    private Double voucher;

    /***提现总金额*/
    private Double totalMoney;

    /**是否是异常订单：0不是,1是**/
    private int exceptions;

    /**是否审核过0不是,1是**/
    private int examine;

    /**异常提示信息**/
    private String alter;
}
