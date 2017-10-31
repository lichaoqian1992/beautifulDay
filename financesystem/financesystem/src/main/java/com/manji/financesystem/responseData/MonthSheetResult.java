package com.manji.financesystem.responseData;

import com.manji.financesystem.common.BaseResult;
import lombok.Data;

/**
 * Created by Administrator on 2017/1/18.
 */
@Data
public class MonthSheetResult extends BaseResult {

    /**收入总金额*/
    private double incomeMonthAmount;

    /**支出总金额*/
    private double expenditureMonthAmount;

    /**期初余额*/
    private double beginMonthAmount;

    /**期末余额*/
    private double endMonthAmount;

    /**开始日期*/
    private String startMonthTime;
}
