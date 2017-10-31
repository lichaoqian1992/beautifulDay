package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-3-11.
 */
@Data
public class WithdrawalsDailyDo {

    private int id;

    //提现订单号
    private String WithdrawalsNo;

    //审批用户id
    private  int  UserId;

    //审批时间
    private String CareteTime;

    //订单金钱
    private  Double TotalMoney;



}
