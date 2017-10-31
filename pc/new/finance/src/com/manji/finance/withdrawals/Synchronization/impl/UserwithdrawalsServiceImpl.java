package com.manji.finance.withdrawals.Synchronization.impl;

import com.manji.finance.withdrawals.Synchronization.AbnormalwithdrawalService;
import com.manji.finance.withdrawals.Synchronization.UserwithdrawalsService;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsDto;

/**
 * Created by pudding on 2017-4-17.
 */
public class UserwithdrawalsServiceImpl implements UserwithdrawalsService {


    private AbnormalwithdrawalService abnormalwithdrawalService=new AbnormalwithdrawalServiceImpl();


    /**
     * 判断异常订单
     * @param userWithdrawals
     * @return
     */
    @Override
    public WithdrawalsDto WhetherAbnormal(UserWithdrawalsDO userWithdrawals) {
        WithdrawalsDto newWithdrawals=new WithdrawalsDto();

        int exceptions=0;
        StringBuffer alter=new StringBuffer();
        //判断是否是商家，如果是商家则不判定直接跳出
        if (userWithdrawals.getUserRoleTyoe().equals("Shop")){
            newWithdrawals.setAlter(alter.toString());
            newWithdrawals.setExceptions(exceptions);
            return newWithdrawals;
        }

      /*  if (!abnormalwithdrawalService.isAbnormalwithdrawal(userWithdrawals)){
           alter.append("当日在平台无任何消费下连续充值       ");
          exceptions=1;
        }*/
        if(!abnormalwithdrawalService.iswithdrawalone(userWithdrawals)){
            alter.append("连续提现       ");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.GreaterThanTheAmountOfConsumption(userWithdrawals)){
            alter.append("单笔交易提现金额大于消费金额       ");
            exceptions=1;
        }

        if (!abnormalwithdrawalService.WithinHours(userWithdrawals)){
            alter.append("新注册用户24小时内发起提现       ");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.DaysLanding(userWithdrawals)){
            alter.append("七天以上未登陆过的用户       ");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.AbnormalAreaLogin(userWithdrawals)){
            alter.append("异常登录地区发起提现的       ");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.ThereHasNeverBeenATransaction(userWithdrawals)){
            alter.append("从未有过交易发起提现的       ");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.AbnormalPayment(userWithdrawals)){
            alter.append("账户收支记录异常发起提现的       ");
            exceptions=1;
        }

        newWithdrawals.setAlter(alter.toString());
        newWithdrawals.setExceptions(exceptions);
        return newWithdrawals;
    }


}
