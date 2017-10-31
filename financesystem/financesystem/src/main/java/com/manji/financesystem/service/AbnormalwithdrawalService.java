package com.manji.financesystem.service;

import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;

/**
 * Created by pudding on 2017-3-13.
 */

//判定提现订单是否异常接口
public interface AbnormalwithdrawalService {

    //1.在某一时间段内（1-2小时）连续充值，在平台无任何消费记录且当天提现
    boolean isAbnormalwithdrawal(UserWithdrawalsDO Withdrawals);

    //2.单笔提现金额大于消费金额
    boolean GreaterThanTheAmountOfConsumption(UserWithdrawalsDO Withdrawals);

    //3.交易订单没有扣点，没扣满意卷，或默认的服务费扣点
    boolean CashWithdrawalRule(UserWithdrawalsDO Withdrawals);

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
