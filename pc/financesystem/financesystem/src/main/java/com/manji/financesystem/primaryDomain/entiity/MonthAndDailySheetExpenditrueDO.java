package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/18.
 * 支出总金额
 */
@Data
public class MonthAndDailySheetExpenditrueDO {

    /**查询日期的支出总金额*/
    private double expenditureAmount;

    /**查询日期前一天的支出总金额*/
    private double expenditureYesterdayAmount;

    /**查询日期后一天的支出总金额*/
    private double expenditureTomorrowAmount;
}
