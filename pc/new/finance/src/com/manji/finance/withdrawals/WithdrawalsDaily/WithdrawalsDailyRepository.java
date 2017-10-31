package com.manji.finance.withdrawals.WithdrawalsDaily;

import com.manji.finance.utils.DButils;

import java.util.Date;

/**
 * Created by pudding on 2017-4-22.(审核记录表)
 */
public class WithdrawalsDailyRepository extends DButils{
    /**
     * 审核通过时添加到审核日志表
     * @param withdrawalsDailyDo
     */
    public void insertWithdrawalsDaily(WithdrawalsDailyDo withdrawalsDailyDo){
        String sql="INSERT INTO t_daily_cash_register VALUES(?,?,?,?,?,?);";
        mysql.update(sql,null,withdrawalsDailyDo.getWithdrawalsNo(),withdrawalsDailyDo.getUserId(),withdrawalsDailyDo.getCareteTime(),withdrawalsDailyDo.getTotalMoney(),withdrawalsDailyDo.getConfigId());
    }

    /**
     * 查询对应审核人今日已审核总条数
     */
    public long getWithdrawalsDailyCount(int cfid){
        String sql="select COUNT(*) from t_daily_cash_register where ConfigId=? and DAY (Carete_Time) = DAY(?);";
        return mysql.queryLong(sql,cfid,new Date());
    }


    /**
     * 查询对应审核人今日已审核金额
     */
    public Double getWithdrawalsDailySum(int cfid){
        String sql="select SUM(TotalMoney) from t_daily_cash_register where ConfigId=? and DAY (Carete_Time) = DAY(?);";
        Double i=mysql.queryDouble(sql,cfid,new Date());
        if (i==null||i==0){
            return 0.00;
        }
        return i;
    }

}
