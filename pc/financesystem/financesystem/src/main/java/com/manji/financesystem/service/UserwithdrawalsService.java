package com.manji.financesystem.service;

import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.secondaryDomain.entity.TUserWithdrawals;

/**
 * Created by pudding on 2017-3-3.
 */
public interface UserwithdrawalsService {

    /**
     * 判断异常订单
     * @param userWithdrawals
     * @return
     */
    TUserWithdrawals WhetherAbnormal(UserWithdrawalsDO userWithdrawals);
}
