package com.manji.financesystem.responseData;

import lombok.Data;

/**
 * Created by pudding on 2017-2-4.
 * 充值当日当月累计统计
 */
@Data
public class UserNormalRechargeStatisticsResult {

    /***当然累计充值金额*/
    private Double toDayAmount;

    /***当月充值总金额*/
    private Double theCurrentMonthAmount;

}
