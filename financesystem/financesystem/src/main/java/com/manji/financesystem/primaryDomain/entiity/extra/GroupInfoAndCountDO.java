package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * 集团账户信息和下面的子商家数量以及录入平台的数量
 * Created by Administrator on 2017/3/3.
 */
@Data
public class GroupInfoAndCountDO {
    /**集团ID*/
    private int userId;
    /**角色类型*/
    private String roleType;
    /**角色值*/
    private int roleValue;
    /**集团名称*/
    private String userName;
    /**商家总数*/
    private int shopCount;
    /**入驻总数*/
    private int settledCount;
    /**预付款金额*/
    private double notAllowAmount;
    /**管理类型*/
    private String managerType;
    /**集团状态*/
    private String groupStatus;
    /**添加时间*/
    private String addTime;
    /**更新时间*/
    private String updateTime;
    /**更新状态*/
    private String updateStatus;

}
