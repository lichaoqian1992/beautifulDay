package com.manji.financesystem.secondaryDomain.entity.extra;

import lombok.Data;

/**
 * Created by pudding on 2017-2-6.
 */
@Data
public class UserRechargeDetailDO {
    private String rechargeOrderNo;
    private String userId;
    private String roleType;
    private String status;
    private String createTime;
    private String excelNo;
    private String rechargeType;
    private String excelName;
    private String money;
    private String userKey;
    private String remark;
}
