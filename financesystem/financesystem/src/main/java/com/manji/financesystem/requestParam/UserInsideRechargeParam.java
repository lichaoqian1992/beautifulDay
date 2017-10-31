package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/3.
 */
@Data
public class UserInsideRechargeParam {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 充值状态
     */
    private String status;
    /**
     * 当前页
     */
    private int pageNum;
}
