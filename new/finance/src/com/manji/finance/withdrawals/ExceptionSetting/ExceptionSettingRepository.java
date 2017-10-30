package com.manji.finance.withdrawals.ExceptionSetting;


import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;
import com.manji.finance.withdrawals.ExceptionSetting.ExceptionSettingDto;

/**
 * Created by pudding on 2017-4-17. 异常条件设置表
 */
public class ExceptionSettingRepository extends DButils{

    /**
     * 查询异常条件一规则
     */
    public double getException_one(){
        String sql="select exception_one from t_exception_setting where id=1";
        return mysql.queryDouble(sql);
    }
    public double getException_one_tow(){
        String sql="select exception_one_tow from t_exception_setting where id=1";
        return mysql.queryDouble(sql);
    }

    /**
     * 查询异常条件二规则
     */
    public double getException_tow(){
        String sql="select exception_tow from t_exception_setting where id=1";
        return mysql.queryDouble(sql);
    }

    /**
     * 查询异常条件三规则
     */
    public double getException_three(){
        String sql="select exception_three from t_exception_setting where id=1";
        return mysql.queryDouble(sql);
    }

    /**
     * 查询异常条件四规则
     */
    public double getException_four(){
        String sql="select exception_four from t_exception_setting where id=1";
        return mysql.queryDouble(sql);
    }

    /**
     * 查询提现规则
     */
    public Record getExceptionSetting(){
       // String sql="select * from t_exception_setting where id=1";
        return  mysql.findById("t_exception_setting",1);
    }

    /**
     * 管理员修改提现规则
     */
    public int updateExceptionSetting(ExceptionSettingDto exceptionSetting){
        try {
            String sql="update t_exception_setting SET exception_one=?,exception_one_tow=?,exception_tow=?,exception_three=?,exception_four=? where id=1";
            return mysql.update(sql,new Object[]{exceptionSetting.getException_one(),exceptionSetting.getException_one_tow(),exceptionSetting.getException_tow(),exceptionSetting.getException_three(),exceptionSetting.getException_four()});
        }catch (Exception e){
            return 0;
        }

    }

}
