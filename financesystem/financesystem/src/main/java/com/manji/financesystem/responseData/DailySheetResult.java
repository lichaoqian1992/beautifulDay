package com.manji.financesystem.responseData;

import com.manji.financesystem.common.BaseResult;
import lombok.Data;

/**
 * Created by Administrator on 2017/1/18.
 */
@Data
public class DailySheetResult extends BaseResult{

    /**收入总金额*/
    private double incomeAmount;

    /**支出总金额*/
    private double expenditureAmount;

    /**期初余额*/
    private double beginAmount;

    /**期末余额*/
    private double endAmount;

    /**开始日期*/
    private String startTime;
}
