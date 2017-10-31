package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/17.
 * 收入日报表DO
 */
@Data
public class MonthAndDailySheetIncomeDO {

    /**收入总金额*/
    private double incomeAmount;

    /**前一天收入总金额*/
    private double incomeYesterdayAmount;

    /**后一天收入总金额*/
    private double incomeTomorrowAmount;

}
