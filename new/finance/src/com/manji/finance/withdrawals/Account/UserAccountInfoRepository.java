package com.manji.finance.withdrawals.Account;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

import java.util.List;

/**
 * Created by pudding on 2017-4-17.（账户信息类）
 */
public class UserAccountInfoRepository extends DButils{

    /**
     * 通过userid和roletype查询账户
     * @param userid
     * @param roletype
     * @return
     */
    public Record findAccountInfoAll(String userid, String roletype){
        Record record=sqlserver.findFirst("select * from dt_user_accountinfo where user_id=? and role_type=?",userid,roletype);
        return record;
    }

    /**
     * 通过userid查询账号
     */
    public Record findUsersAll(String userid){
        Record record=sqlserver.findFirst("select * from dt_users where id=?",userid);
        return record;
    }

    /**
     * 通过id查询此用户是否被冻结
     */
    public int getAccountInfostatc(String Accountid){
        String sql="select state from dt_user_accountinfo where  id=?;";
        Short s=sqlserver.queryShort(sql,Accountid);
        if (s==null){
            return 0;
        }
        Integer i=Integer.parseInt(s.toString());
        return i;
    }

    /**
     * 查询全部已被冻结的用户
     */
    public List<Record> findstatcinfo(){
        String sql="select * from dt_user_accountinfo where  state=0;";
        List<Record> list= sqlserver.find(sql);
        return list;
    }

}
