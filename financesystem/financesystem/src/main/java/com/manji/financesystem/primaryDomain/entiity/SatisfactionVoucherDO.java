package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 满意券DO
 * Created by Administrator on 2017/1/22.
 */
@Data
public class SatisfactionVoucherDO {

    /**用户Id*/
    private String userId;

    /**用户姓名*/
    private String userName;

    /**账户类型*/
    private String roleType;

    /**账户状态*/
    private String status;

    /**满意券余额*/
    private double voucher;
}
