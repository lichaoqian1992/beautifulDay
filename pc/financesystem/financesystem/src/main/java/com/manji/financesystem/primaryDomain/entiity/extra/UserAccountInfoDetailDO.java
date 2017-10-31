package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * Created by pudding on 2017-1-23.
 * 用户余额变动明细
 */
@Data
public class UserAccountInfoDetailDO {

    /***用户账号余额变动ID*/
    private String userAccountDetailId;

    /***用户名*/
    private String userName;

    /***角色类型*/
    private String userRoleType;

    /***账户ID*/
    private String accountId;

    /***增减值*/
    private Double value;

    /***订单号*/
    private String orderNo;

    /***操作类型*/
    private String type;

    /***变动前金额*/
    private Double oldValue;

    /***变动后金额*/
    private Double newValue;

    /***第三方支付订单号*/
    private String transactionNo;

    /***添加时间*/
    private String addTime;




}
