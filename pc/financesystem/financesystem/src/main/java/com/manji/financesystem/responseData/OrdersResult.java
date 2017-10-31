package com.manji.financesystem.responseData;

import lombok.Data;

/**
 * Created by Administrator on 2017/1/25.
 */
@Data
public class OrdersResult {

    /**所有订单*/
    private double amountOrders;

    /**订单金额*/
    private double amountMoney;

    /**成功订单*/
    private double successOrders;

    /**成功订单金额*/
    private double successMoney;
}
