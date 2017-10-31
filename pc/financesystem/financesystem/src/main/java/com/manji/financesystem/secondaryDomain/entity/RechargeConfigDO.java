package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/8.
 */
@Data
public class RechargeConfigDO {

    private int id;
    /**
     * 审批人
     */
    private String userName;
    /**
     * 单笔最小金额
     */
    private double singleMinMoney;
    /**
     * 单笔最大金额
     */
    private double singleMaxMoney;
    /**
     * 总金额
     */
    private double totalMoney;

    /**
     * 提現单笔最小金额
     */
    private double t_singleMinMoney;
    /**
     * 提現单笔最大金额
     */
    private double t_singleMaxMoney;
    /**
     * 提現每日总金额
     */
    private double t_totalMoney;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
