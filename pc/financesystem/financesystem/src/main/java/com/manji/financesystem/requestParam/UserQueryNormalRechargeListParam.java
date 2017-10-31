package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by pudding on 2017-2-3.
 * 正常充值记录查询参数
 */
@Data
public class UserQueryNormalRechargeListParam {

    private String userName;

    private String status;

    private String startTime;

    private String endTime;
}
