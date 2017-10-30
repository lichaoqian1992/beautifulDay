package com.manji.finance.withdrawals.Withdrawalslog;

import com.manji.finance.withdrawals.Withdrawalslog.WithdrawalslogRepository;

/**
 * Created by pudding on 2017-4-21.(提现操作日志)
 */
public class WithdrawalslogService {

    WithdrawalslogRepository withdrawalslogRepositoryRepository=new WithdrawalslogRepository();

    /**
     * 添加操作提现日志
     */
    public void insertWithdrawalslog(int user_id, Long w_id, String witdrawals_no, String cont, String user_name, String alert){
        withdrawalslogRepositoryRepository.insertWithdrawalslog(user_id,w_id,witdrawals_no,cont,user_name,alert);
    }


}
