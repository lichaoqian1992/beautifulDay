package com.manji.finance.withdrawals.RechargeConfig;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pudding on 2017-4-24.（金额配置类）
 */
public class RechargeConfigRepository extends DButils{

    /**
     * 通过id查询审核人
     */
    public Record getRechargeConfigbyId(int id){
        String sql="select * from t_recharge_config where id=?";
        return mysql.findFirst(sql,id);
    }

}
