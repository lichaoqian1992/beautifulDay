package com.manji.financesystem.primaryDomain.entiity;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/19.
 */
@Data
public class EveryIndustryManagementSituationDO {

    /**消费金额*/
    private double amountMoney;

    /**商品类别ID*/
    private int category_id;

    /**类别名称*/
    private String title;
}
