package com.manji.finance.withdrawals.Users;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pudding on 2017-4-21.(用户表)
 */
public class UsersRepository extends DButils{

    /**
     * 根据用户id查询用户对象
     * @return
     */
    public Record getUserByUserid(int  userid) {
        String sql = "select * from dt_users where id=?;";
        return DButils.sqlserver.findFirst(sql, userid);
    }

    /**
     * 通过角色id查询用户信息
     */
    public Record findUserByCid(int cid){
        String sql="select * from t_user where T_CONFIG_ID=?";
        return mysql.findFirst(sql,cid);
    }

    /**
     * 通过用户名查询用户信息
     */
    public Record findUserByUserName(String username){
        String sql="select * from dt_users where user_name=?";
        return sqlserver.findFirst(sql,username);
    }
}
