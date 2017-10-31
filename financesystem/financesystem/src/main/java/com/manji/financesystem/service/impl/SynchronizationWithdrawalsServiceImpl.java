package com.manji.financesystem.service.impl;

import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.repository.UserWithdrawalsRepository;
import com.manji.financesystem.secondaryDomain.entity.TUserWithdrawals;
import com.manji.financesystem.secondaryDomain.repository.TUserWithdrawalsRepository;
import com.manji.financesystem.service.SynchronizationWithdrawalsService;
import com.manji.financesystem.service.UserwithdrawalsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pudding on 2017-3-16.
 */
@Service
public class SynchronizationWithdrawalsServiceImpl implements SynchronizationWithdrawalsService{
    @Autowired
    private TUserWithdrawalsRepository tUserWithdrawalsRepository;

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;

    @Autowired
    private UserwithdrawalsService userwithdrawalsService;


    /**
     * 同步数据到本地数据库
     * @return
     */
    @Override
    public String SynchronizationWithdrawals() {
        String is="success";
        StringBuffer isok=new StringBuffer();
        //获取未处理数据
        List<UserWithdrawalsDO> userWithdrawals=userWithdrawalsRepository.tfindUserWithdrawals();

        for (int i=0;i<userWithdrawals.size();i++){
            try {
                //检查是否是异常订单
                //TUserWithdrawals tw=userwithdrawalsService.WhetherAbnormal(userWithdrawals.get(i));

                //插入数据
                tUserWithdrawalsRepository.insertTUserWithdrawals(userWithdrawals.get(i),0,"");

            }catch (Exception e){
                is="err";
                isok.append("\n提现订单编号为:+"+userWithdrawals.get(i).getWithdrawalsNo()+"插入错误。\n");
            }
        }
        return is+isok.toString();
    }



}
