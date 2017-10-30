package com.manji.finance.withdrawals.Users;

import com.jfinal.plugin.activerecord.Record;

/**
 * Created by pudding on 2017-4-21.
 */
public class UsersService {
    UsersRepository usersRepository=new UsersRepository();
    /**
     * 根据用户id查询用户对象
     * @return
     */
    public Record getUserByUserid(Integer userid) {
        return usersRepository.getUserByUserid(userid);
    }

    /**
     * 通过角色id查询用户信息
     */
    public Record findUserByCid(int cid){
        return usersRepository.findUserByCid(cid);
    }

    /**
     * 通过用户账号查询
     */
    public Record findUserByUserName(String username){return usersRepository.findUserByUserName(username);}
}
