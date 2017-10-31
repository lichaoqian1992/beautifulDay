package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * Created by pudding on 2017-1-22.
 * 用户账号余额信息DO
 */
@Data
public class UserAccountInfoQueryDO {

    /***用户ID*/
    private String userId;

    /***用户名*/
    private String userName;

    /***角色类型*/
    private String roleType;

    /***余额*/
    private Double amount;
    /***可提现余额*/
    private Double allowAmount;

    /***账号状态*/
    private String state;


}
