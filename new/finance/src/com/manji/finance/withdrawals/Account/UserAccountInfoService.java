package com.manji.finance.withdrawals.Account;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.withdrawals.Account.UserAccountInfoRepository;

import java.util.List;

/**
 * Created by pudding on 2017-4-21.（账户信息类）
 */
public class UserAccountInfoService {

    UserAccountInfoRepository userAccountInfoRepository=new UserAccountInfoRepository();

    /**
     * 通过userid和roletype查询账户
     * @param userid
     * @param roletype
     * @return
     */
    public Record findAccountInfoAll(String userid, String roletype){
        return  userAccountInfoRepository.findAccountInfoAll(userid,roletype);
    }

    /**
     * 通过id查询此用户是否被冻结
     */
    public int getAccountInfostatc(String Accountid){
        return userAccountInfoRepository.getAccountInfostatc(Accountid);
    }


    /**
     * 处理已冻结账户id
     * @return
     */
    public String findstatcinfo(){
        List<Record> list=userAccountInfoRepository.findstatcinfo();
        String idlist="";
        for (int i=0;i<list.size();i++){
            if (i!=0){
                idlist+=","+list.get(i).getInt("id");
            }else{
                idlist+=list.get(i).getInt("id");
            }

        }
         String id="";
        return idlist;
    }


}
