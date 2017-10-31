package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/3.
 */
@Data
public class UserInsideRechargeDO {

    /**用户Id*/
    private String userId;

    /**用户名称*/
    private String userName;

    /**充值金额*/
    private double amount;

    /**充值状态*/
    private String status;

    /**添加时间*/
    private String addTime;

    /**备注*/
    private String remark;

    /**是否删除*/
    private String isDel;

    /**充值类型*/
    private String category;
}
