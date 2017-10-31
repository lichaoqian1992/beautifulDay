package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * 查询提现记录的时候参数实体类
 * Created by Administrator on 2017/2/17.
 */
@Data
public class WithDrawalsParam {

    /**
     * 提现订单号
     */
    private String withDrawalsId;
    /**
     * 账户类型
     */
    private String accountType;
    /**
     * 提现状态
     */
    private String status;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 是否异常
     */
    private int exceptions;
}
