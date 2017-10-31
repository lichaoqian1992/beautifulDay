package com.manji.financesystem.service.impl;

import com.manji.financesystem.common.AccountTypeEnum;
import com.manji.financesystem.primaryDomain.entiity.extra.AccountInfoDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserBalanceInfoDO;
import com.manji.financesystem.primaryDomain.repository.OrderOnlinePayRepository;
import com.manji.financesystem.primaryDomain.repository.UserBalanceLogRepository;
import com.manji.financesystem.primaryDomain.repository.UserWithdrawalsRepository;
import com.manji.financesystem.responseData.AccountInfoResult;
import com.manji.financesystem.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-1-17.
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private OrderOnlinePayRepository orderOnlinePayRepository;

    @Autowired
    private UserBalanceLogRepository userBalanceLogRepository;

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;

    @Override
    public List<AccountInfoResult> getAccountInfo() {
        List<AccountInfoResult> results=new ArrayList<AccountInfoResult>();

        //第三方支付的金额
        List<AccountInfoDO> onlinePayInfos = orderOnlinePayRepository.getOnlinePayInfo();
        //技术服务费的金额(商家交易额的提点收取的金额)
        //UserBalanceInfoDO userBalanceInfo = userBalanceLogRepository.getUserBalanceInfo();
        //平台收取提现手续费的金额(算上技术服务费)
        Double withdrawalFee = userWithdrawalsRepository.getWithdrawalFee();
        //平台支出满意券的金额
        Double voucher = userWithdrawalsRepository.getVoucher();


        for (AccountInfoDO accountInfoDO:onlinePayInfos) {
            AccountInfoResult accountInfoResult=new AccountInfoResult();
            accountInfoResult.setAccountName(accountInfoDO.getAccountName());
            accountInfoResult.setAmount(accountInfoDO.getAmount());
            accountInfoResult.setType(AccountTypeEnum.getByCode(String.valueOf(accountInfoDO.getPaymentId())));
            results.add(accountInfoResult);
        }

        //技术服务费
        /*AccountInfoResult accountInfoResult=new AccountInfoResult();
        accountInfoResult.setType(AccountTypeEnum.TECHNICAL_SERVICE_FEE);
        accountInfoResult.setAccountName(AccountTypeEnum.TECHNICAL_SERVICE_FEE.getMessage());
        accountInfoResult.setAmount(userBalanceInfo.getAmount());
        results.add(accountInfoResult);*/

        //提现手续费
        AccountInfoResult accountInfoResult1=new AccountInfoResult();
        accountInfoResult1.setType(AccountTypeEnum.WITHDRAWALS_FEE);
        accountInfoResult1.setAccountName(AccountTypeEnum.WITHDRAWALS_FEE.getMessage());
        accountInfoResult1.setAmount(withdrawalFee);
        results.add(accountInfoResult1);

        //提现满意券
        AccountInfoResult accountInfoResult2=new AccountInfoResult();
        accountInfoResult2.setType(AccountTypeEnum.VOUCHER);
        accountInfoResult2.setAccountName(AccountTypeEnum.VOUCHER.getMessage());
        accountInfoResult2.setAmount(voucher);
        results.add(accountInfoResult2);
        return results;
    }
}
