package com.manji.financesystem.primaryDomain.entiity.extra;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/18.
 * 日报表和月报表
 */
@Data
public class MonthAndDailySheetDO {

    /**收入总金额*/
    private double incomeAmount;

    /**支出总金额*/
    private double expenditureAmount;

    /**期初余额*/
    private double beginAmount;

    /**期末余额*/
    private double endAmount;
}
