package com.manji.finance.system;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pudding on 2017-4-17.
 */
public class RechargeConfigRepository extends DButils{

    /**
     * 通過用戶獲取提現權限對象
     * @param role
     * @return
     */
    public Record getRechargeConfig(String role){
        String sql ="select * from t_recharge_config where id=?";
        Record record=mysql.findFirst(sql,role);
        return record;
    }


}
