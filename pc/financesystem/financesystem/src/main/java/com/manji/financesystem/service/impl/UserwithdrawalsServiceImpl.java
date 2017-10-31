package com.manji.financesystem.service.impl;

import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.repository.UserAccountInfoRepository;
import com.manji.financesystem.primaryDomain.repository.UserWithdrawalsRepository;
import com.manji.financesystem.secondaryDomain.entity.TUserWithdrawals;
import com.manji.financesystem.service.AbnormalwithdrawalService;
import com.manji.financesystem.service.UserwithdrawalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pudding on 2017-3-3.
 */
@Service
public class UserwithdrawalsServiceImpl implements UserwithdrawalsService {

    @Autowired
    private AbnormalwithdrawalService abnormalwithdrawalService;


    /**
     * 判断异常订单
     * @param userWithdrawals
     * @return
     */
    @Override
    public TUserWithdrawals WhetherAbnormal(UserWithdrawalsDO userWithdrawals) {
        TUserWithdrawals newWithdrawals=new TUserWithdrawals();

        int exceptions=0;
        StringBuffer alter=new StringBuffer();
        if (!abnormalwithdrawalService.isAbnormalwithdrawal(userWithdrawals)){
            alter.append("当日在平台无任何消费下连续充值\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.GreaterThanTheAmountOfConsumption(userWithdrawals)){
            alter.append("单笔交易提现金额大于消费金额\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.CashWithdrawalRule(userWithdrawals)){
            alter.append("交易订单没有扣点，没扣满意卷，或默认的服务费扣点\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.WithinHours(userWithdrawals)){
            alter.append("新注册用户24小时内发起提现\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.DaysLanding(userWithdrawals)){
            alter.append("七天以上未登陆过的用户\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.AbnormalAreaLogin(userWithdrawals)){
            alter.append("异常登录地区发起提现的\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.ThereHasNeverBeenATransaction(userWithdrawals)){
            alter.append("从未有过交易发起提现的\n");
            exceptions=1;
        }
        if (!abnormalwithdrawalService.AbnormalPayment(userWithdrawals)){
            alter.append("账户收支记录异常发起提现的\n");
            exceptions=1;
        }

        newWithdrawals.setAlter(alter.toString());
        newWithdrawals.setExceptions(exceptions);
        return newWithdrawals;
    }


}
