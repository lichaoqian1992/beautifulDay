package com.manji.finance.withdrawals.Synchronization;

import com.manji.finance.withdrawals.Synchronization.impl.UserWithdrawalsDO;
import com.manji.finance.withdrawals.Withdrawals.WithdrawalsDto;

/**
 * Created by pudding on 2017-3-3.
 */
public interface UserwithdrawalsService {

    /**
     * 判断异常订单
     * @param userWithdrawals
     * @return
     */
    WithdrawalsDto WhetherAbnormal(UserWithdrawalsDO userWithdrawals);
}
