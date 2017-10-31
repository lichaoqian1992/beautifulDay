package com.manji.finance.withdrawals.Synchronization.impl;


import com.manji.finance.utils.DButils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-3-31.
 */


public class SynchronizationExceptionRepository extends DButils{

    /**
     * 添加提现日志
     * @param withdrawalsNo
     */
    public void insertWithdrawalslog(String withdrawalsNo){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql="INSERT into t_Synchronization_exception VALUES(?,?,?)";
        mysql.update(sql,null,withdrawalsNo,df.format(new Date()));
    }
}
