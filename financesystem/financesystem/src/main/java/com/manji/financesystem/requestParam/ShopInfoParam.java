package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * 商家查询条件参数
 * Created by Administrator on 2017/2/24.
 */
@Data
public class ShopInfoParam {
    /**商家名称*/
    private String shopName;
    /**页码*/
    private int pageNum;
    /**订单状态*/
    private String orderStatus;
}
