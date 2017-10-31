package com.manji.finance.withdrawals.Withdrawalslog;

import com.manji.finance.utils.DButils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-4-21(提现操作日志类).
 */
public class WithdrawalslogRepository extends DButils {

    /**
     * 添加提现操作日志
     */
    public void insertWithdrawalslog(int user_id, Long w_id, String witdrawals_no, String cont, String user_name, String alert){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String date=df.format(new Date());
        String sql="INSERT t_widrawals_log VALUES(null,?,?,?,?,?,?,?)";
        mysql.update(sql,witdrawals_no,user_id,date,user_name,w_id,cont,alert);
    }


}
