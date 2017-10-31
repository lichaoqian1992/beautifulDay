package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Ceated by pudding on 2017-1-17.
 * 用户账户信息DO
 */
@Data
public class UserAccountInfoDO {

    private Long id;

    /***用户ID*/
    private int userId;

    /**用户名称*/
    private String userName;

    /***角色类别*/
    private String roleType;

    /***用户角色值*/
    private Integer roleValue;

    /***余额*/
    private Double amount;

    /***积分*/
    private Double point;

    /***信誉值*/
    private Integer reputation;

    /***信用*/
    private Double credit;

    /***代金券*/
    private Double voucher;

    /***开启极速付*/
    private Integer isFastpay;

    /***账户状态*/
    private Integer state;

    /***状态描述*/
    private String remark;

    /***状态更新时间*/
    private Date updateTime;

    /***待结算余额*/
    private Double floatAmount;

    /***待结算代金券*/
    private Double floatVoucher;

    /***待结算积分*/
    private Double floatPoint;

    /***可提现余额*/
    private Double allowAmount;

    /***可提现代金券*/
    private Double allowVoucher;

    /***手续费规则*/
    private Integer withdrawalsCommission;





}
