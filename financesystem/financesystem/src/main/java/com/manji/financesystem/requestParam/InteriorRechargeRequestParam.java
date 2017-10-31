package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/9.
 */
@Data
public class InteriorRechargeRequestParam {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 充值订单号
     */
    private String rechargeOrderNo;
    /**
     * 充值类型
     */
    private String rechargeType;
    /**
     * 充值金额
     */
    private double accountMoney;
    /**
     * 充值状态
     */
    private String status;
    /**
     * 审批状态
     */
    private String checkStatus;
    /**
     * 发布人
     */
    private String peopleRelease;
    /**
     * 审核人
     */
    private String checkPeople;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 订单集合
     */
    private String idList;
    /**
     * 用户ID集合
     */
    private String moneyList;
    private String userIdList;
    private String rechargeTypeList;
    private String roleTypeList;
    private String roleValueList;
    private String transnList;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 验证码
     */
    private String Yzm;
    /**时间戳*/
    private long nowTime;
    /**
     * 用于储存是   报批还是撤销
     */
    private String operationType;
}
