package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * 商家基本信息和商家订单的信息
 * Created by Administrator on 2017/2/25.
 */
@Data
public class OrderAndShopInfoDO {
    /**商家ID*/
    private String userId;
    /**商家名称*/
    private String userName;
    /**联系电话*/
    private String mobile;
    /**店铺所在区域*/
    private String area;
    /**提点比例*/
    private double percentage;

    /**订单编号*/
    private String orderNo;
    /**订单类型*/
    private String orderType;
    /**订单标题*/
    private String orderTitle;
    /**订单金额*/
    private double orderAmount;
    /**应付总金额*/
    private double payableAmount;
    /**实付总金额*/
    private double realAmount;
    /**订单状态*/
    private String status;
    /**支付状态*/
    private String paymentStatus;
    /**结算状态*/
    private String settlementStatus;
    /**结算时间*/
    private String settlementTime;
    /**总数据*/
    private int countNum;
}
