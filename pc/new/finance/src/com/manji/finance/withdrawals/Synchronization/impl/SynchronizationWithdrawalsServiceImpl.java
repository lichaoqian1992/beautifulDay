package com.manji.finance.withdrawals.Synchronization.impl;

import com.manji.finance.withdrawals.Synchronization.SynchronizationWithdrawalsService;
import com.manji.finance.withdrawals.Synchronization.UserwithdrawalsService;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsDto;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsRepository;

import java.util.List;

/**
 * Created by pudding on 2017-3-16.(同步提现)
 */
public class SynchronizationWithdrawalsServiceImpl implements SynchronizationWithdrawalsService {


    private UserWithdrawalsRepository userWithdrawalsRepository =new UserWithdrawalsRepository();

    private UserwithdrawalsService userwithdrawalsService=new UserwithdrawalsServiceImpl();

    private WithdrawalsRepository withdrawalsRepository=new WithdrawalsRepository();

    private SynchronizationExceptionRepository synchronizationExceptionRepository=new SynchronizationExceptionRepository();

    /**
     * 同步数据到本地数据库
     * @return
     */
    @Override
    public String SynchronizationWithdrawals() {
       StringBuffer isok=new StringBuffer();
        //测试单条数据
      /* UserWithdrawalsDO w=new UserWithdrawalsDO();
        w.setId(Long.parseLong("162"));
        w.setUserId("1169581");
        w.setStatus("0");
        w.setTotalMoney(369.93);
        w.setAccountId(2445457);
        w.setWithdrawalsNo("_WITHDRAWALS_20170309000001854");
        w.setCommission(1.00);
        w.setAdd_time("2017-03-09 00:00:01.853");
        w.setAmount(369.93);
        w.setUserRoleTyoe("Shop");
        w.setBankArea(null);
        //检查是否是异常订单
        TUserWithdrawals tw=userwithdrawalsService.WhetherAbnormal(w);
        //插入数据
        tUserWithdrawalsRepository.insertTUserWithdrawals(w,tw.getExceptions(),tw.getAlter());*/

        //获取未处理数据
       List<UserWithdrawalsDO> userWithdrawals=userWithdrawalsRepository.tfindUserWithdrawals();

        for (int i=0;i<userWithdrawals.size();i++){
            try {
                //检查是否是异常订单
               WithdrawalsDto tw=userwithdrawalsService.WhetherAbnormal(userWithdrawals.get(i));

                //插入数据
                withdrawalsRepository.insertTUserWithdrawals(userWithdrawals.get(i),tw.getExceptions(),tw.getAlter());

            }catch (Exception e){
                synchronizationExceptionRepository.insertWithdrawalslog(userWithdrawals.get(i).getWithdrawalsNo());
                isok.append(userWithdrawals.get(i).getWithdrawalsNo());
            }
        }
        return isok.toString();
    }





}
