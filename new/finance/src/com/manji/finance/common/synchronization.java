package com.manji.finance.common;

import com.manji.finance.base.ITask;
import com.manji.finance.withdrawals.Synchronization.SynchronizationWithdrawalsService;
import com.manji.finance.withdrawals.Synchronization.impl.SynchronizationWithdrawalsServiceImpl;
import org.apache.poi.util.SystemOutLogger;

/**
 * Created by pudding on 2017-4-22.(同步提现)
 */
public class synchronization  implements ITask{
    @Override
    public void stop() {
    }

    @Override
    public void run() {
        SynchronizationWithdrawalsService synchronizationWithdrawalsService=new SynchronizationWithdrawalsServiceImpl();
        synchronizationWithdrawalsService.SynchronizationWithdrawals();//调用同步方法
    }
}
