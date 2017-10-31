package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/8.
 */
@Data
public class InteriorRechargeDetailDO {

    /**
     * 充值订单号
     */
    private String orderNo;
    /**
     * 充值单号
     */
    private String excelNo;
    /**
     * 业务单号
     */
    private String tranSN;
    /**
     * OA编号
     */
    private int oaNo;
    /**
     * 用户标识
     */
    private String userKey;
    /**
     * 充值金额
     */
    private double rechargeMoney;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 角色类型
     */
    private String roleType;
    /**
     * 角色值
     */
    private int roleValue;
    /**
     * 充值类型
     */
    private String rechargeType;
    /**
     * 充值状态
     */
    private String status;
    /**
     * 发布人（出纳身份）
     */
    private String personRelease;
    /**
     * 审核人（资金部负责人、财务经理、财务副总裁、董事长）
     */
    private String personApproving;
    /**
     * 确认人（会计身份）
     */
    private String personMakeSure;
    /**
     * 审核状态
     */
    private String checkStatus;
    /**
     *审核时间
     */
    private String checkTime;
    /**
     * 审批意见
     */
    private String checkReason;
    /**
     * excel名称
     */
    private String excelName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 报批时间
     */
    private String approvalTime;
    /**
     * 备注
     */
    private String remark;

    private String oprationType;
}
