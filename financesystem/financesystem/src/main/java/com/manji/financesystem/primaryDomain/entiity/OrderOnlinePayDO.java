package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-1-17.
 * 订单在线支付信息DO
 */
@Data
public class OrderOnlinePayDO {

    private Long id;

    /***付款用户id*/
    private Integer userId;

    /***付款用户角色*/
    private String userRoleType;

    /***付款用户角色值*/
    private Integer userRoleValue;

    /***订单类型*/
    private String orderType;

    /***订单id列表*/
    private String orderIdList;

    /***使用代金券数量*/
    private Double useVoucher;

    /***支付方式名称*/
    private String paymentName;

    /***支付类型表id*/
    private Integer paymentId;

    /***添加时间*/
    private Date addTime;

    /***支付回调时间*/
    private Date notifyTime;

    /***支付成功的单号*/
    private String transactionNo;

    /***支付成功的金额*/
    private Double transactionMoney;

    /***支付用户标识*/
    private String transactionIdentity;

    /***支付回调数据*/
    private String transactionTxt;

    /***支付状态*/
    private Integer status;

    /***是否删除*/
    private Double isDel;

    /***支付成功同步回调地址*/
    private String returnUrl;

    /***支付类型*/
    private String paymentType;

    /***期望支付的金额*/
    private Double payment_money;

    /***支付成功异步通知地址*/
    private String notifyUrl;

    /***合作通道*/
    private String partnerChannel;

    /***检查时间*/
    private Long censorDate;

    /***检查用户*/
    private String censorUser;

    /***备注*/
    private String remark;

}
