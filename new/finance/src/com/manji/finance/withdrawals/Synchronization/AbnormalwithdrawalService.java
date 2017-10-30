package com.manji.finance.withdrawals.Synchronization;

import com.manji.finance.withdrawals.Synchronization.impl.UserWithdrawalsDO;

/**
 * Created by pudding on 2017-3-13.
 */

//判定提现订单是否异常接口
public interface AbnormalwithdrawalService {


    //新异常1.在(2)小时内连续提现不得超过(3)次(不含)如果超过了允许的次数，系统将会判定为异常提现。
    boolean iswithdrawalone(UserWithdrawalsDO Withdrawals);


    //2.单笔提现金额大于消费金额
    boolean GreaterThanTheAmountOfConsumption(UserWithdrawalsDO Withdrawals);

    //4.新注册用户24小时内发起提现的
    boolean WithinHours(UserWithdrawalsDO Withdrawals);

    //5.七天以上未登陆过的用户
    boolean DaysLanding(UserWithdrawalsDO Withdrawals);

    //6.异常登录地区发起提现的
    boolean AbnormalAreaLogin(UserWithdrawalsDO Withdrawals);

    //7.从未有过交易发起提现的(交易包括  订单，充值，转账)
    boolean ThereHasNeverBeenATransaction(UserWithdrawalsDO Withdrawals);

    //8.账户收支记录异常发起提现的
    boolean AbnormalPayment(UserWithdrawalsDO Withdrawals);





}
