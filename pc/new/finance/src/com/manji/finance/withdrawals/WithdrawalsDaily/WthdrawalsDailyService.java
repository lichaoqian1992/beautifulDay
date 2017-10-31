package com.manji.finance.withdrawals.WithdrawalsDaily;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-4-24.
 */
public class WthdrawalsDailyService {

    WithdrawalsDailyRepository WithdrawalsDailyRepository=new WithdrawalsDailyRepository();

    /**
     * 审核通过时添加到审核日志表
     */
    public void insertWithdrawalsDaily(String wno,int userid,Double tit,int cid){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WithdrawalsDailyDo wdd = new WithdrawalsDailyDo();
        wdd.setWithdrawalsNo(wno);
        wdd.setUserId(userid);
        wdd.setTotalMoney(tit);
        wdd.setConfigId(cid);
        wdd.setCareteTime(df.format(new Date()));
        WithdrawalsDailyRepository.insertWithdrawalsDaily(wdd);
    }

    /**
     * 查询对应审核人今日已审核金额
     */
    public Double getWithdrawalsDailySum(int cfid){
        return WithdrawalsDailyRepository.getWithdrawalsDailySum(cfid);
    }
}
