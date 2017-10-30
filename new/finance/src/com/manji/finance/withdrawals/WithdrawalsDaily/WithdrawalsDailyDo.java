package com.manji.finance.withdrawals.WithdrawalsDaily;

import java.util.Date;

/**
 * Created by pudding on 2017-3-11.
 */
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

    //对应权限id
    private int ConfigId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWithdrawalsNo() {
        return WithdrawalsNo;
    }

    public void setWithdrawalsNo(String withdrawalsNo) {
        WithdrawalsNo = withdrawalsNo;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCareteTime() {
        return CareteTime;
    }

    public void setCareteTime(String careteTime) {
        CareteTime = careteTime;
    }

    public Double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        TotalMoney = totalMoney;
    }

    public int getConfigId() {
        return ConfigId;
    }

    public void setConfigId(int configId) {
        ConfigId = configId;
    }
}
