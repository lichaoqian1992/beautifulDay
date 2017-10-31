package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * 查询的是当天的和当月的内部充值总和
 * Created by Administrator on 2017/2/4.
 */
@Data
public class UserInsideRechargeDMCountDO {

    /**
     * 当日的充值总和
     */
    private double dayCount;
    /**
     * 当月的充值总和
     */
    private double monthCount;
}
