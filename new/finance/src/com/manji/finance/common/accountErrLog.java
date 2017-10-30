package com.manji.finance.common;

import com.manji.finance.base.ITask;
import com.manji.finance.order.accountErrInfo.accountErrInfoService;

/**
 * Created by pudding on 2017-8-10.(检测账户)
 */
public class accountErrLog implements ITask{
    @Override
    public void stop() {

    }

    @Override
    public void run() {

        accountErrInfoService accountErrInfo=new accountErrInfoService();
        //开始检测..
        accountErrInfo.runExepion();

    }
}
