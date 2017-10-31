package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 订单详细信息实体类
 * Created by Administrator on 2017/2/22.
 */
@Data
public class UserOrdersDO {
    /**订单编号*/
    private String orderNo;
    /**订单类型*/
    private String orderType;
    /**订单标题*/
    private String orderTitle;
    /**商家ID*/
    private String shopUserId;
    /**商家类型*/
    private String shopUserRoleType;
    /**用户ID*/
    private String userId;
    /**用户类型*/
    private String userRoleType;
    /**物流方式*/
    private String expressType;
    /**物流费用*/
    private String expressFee;
    /**订单留言*/
    private String message;
    /**订单备注*/
    private String remark;
    /**是否需要发票*/
    private String isInvoice;
    /**发票抬头*/
    private String invoiceTitle;
    /**订单金额*/
    private String orderAmount;
    /**订单代金券*/
    private String voucher;
    /**应付总金额*/
    private String payableAmount;
    /**实付总金额*/
    private String realAmount;
    /**订单状态*/
    private String status;
    /**下单时间*/
    private String addTime;
    /**提交时间*/
    private String confirmTime;
    /**失效时间*/
    private String invalidTime;
    /**完成时间*/
    private String completeTime;
    /**支付方式*/
    private String paymentId;
    /**支付手续费*/
    private String paymentFee;
    /**支付状态*/
    private String paymentStatus;
    /**支付时间*/
    private String paymentTime;
    /**结算状态*/
    private String settlementStatus;
    /**结算时间*/
    private String settlementTime;
    /**是否删除*/
    private String isDelete;
    /**拒单理由*/
    private String rejectRemark;
}
