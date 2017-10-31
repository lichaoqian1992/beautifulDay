package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * 日报表和月报表
 * Created by Administrator on 2017/3/17.
 */
@Data
public class DayOrMonthDetailDO {
    /**日期*/
    private String date;
    /**交易日期*/
    private String jyTime;
    /**订单编号*/
    private String orderNo;
    /**商家名称*/
    private String shopName;
    /**主营业务*/
    private String goodType;
    /**提点百分比*/
    private String percent;
    /**会员账号*/
    private String userName;
    /**交易类型*/
    private String orderType;
    /**支付类型*/
    private String payType;
    /**账户名*/
    private String bankName;
    /**交易状态*/
    private String orderStatus;
    /**订单金额*/
    private String orderMoney;
    /**实付金额*/
    private String realMoney;
    /**满意券*/
    private String voucher;
    /**手续费*/
    private double commission;
    /**退款单号*/
    private String backOrderNo;
    /**退款金额*/
    private double backMoney;
    /**退款状态*/
    private String backStatus;
    /**备注*/
    private String remark;
}
