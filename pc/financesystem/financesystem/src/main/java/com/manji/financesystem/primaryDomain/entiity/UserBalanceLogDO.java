package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-1-17.
 * 账户结算DO
 */
@Data
public class UserBalanceLogDO {

    private long id;

    /***用户Id*/
    private Integer userId;

    /***用户角色*/
    private String roleType;

    /***用户角色值*/
    private Integer roleValue;

    /***订单来源*/
    private Integer orderPlatform;

    /***订单类型*/
    private String orderType;

    /***订单编号*/
    private String orderNo;

    /***记录标识*/
    private String balanceHash;

    /***订单标题*/
    private String orderTitle;

    /***查看地址*/
    private String orderUrl;

    /***结算金额*/
    private Double balanceAmount;

    /***结算代金券*/
    private Double balanceVoucher;

    /***结算积分*/
    private Double balancePoint;

    /***预期结算时间*/
    private Date willBalanceDate;

    /***实际结算时间*/
    private Date realBalanceDate;

    /***结算状态*/
    private String balanceState;

    /***记录写入时间*/
    private Date addTime;

    /***是否删除*/
    private String isDel;

    /***检查时间*/
    private Long censorDate;

    /***检查用户*/
    private String censorUser;











}
