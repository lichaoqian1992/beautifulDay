package com.manji.finance.withdrawals.Withdrawals;

import com.jfinal.core.Controller;
import com.manji.finance.utils.MD5Utils;
import com.manji.finance.withdrawals.Withdrawalslog.WithdrawalslogService;

/**
 * Created by pudding on 2017-8-30.
 */
public class WithdrawalsHttpController extends Controller{

    WithdrawalsService withdrawalsService=new WithdrawalsService();

    WithdrawalslogService withdrawalslogService=new WithdrawalslogService();

    /**
     * 用户撤回提现通知接口
     */
    public void  RevocationNotification(){
        Long id=getParaToLong("id");
        String withdrawals_no=getPara("withdrawalsNo");
        String sign=getPara("sign");

        try {
            if (!sign.equalsIgnoreCase(MD5Utils.getMD5(id+withdrawals_no+"javamanager"))){
                setAttr("ErrCode","1");
            }else{
                int isok=withdrawalsService.revocationNotification(withdrawals_no);
                if (isok>0){
                    setAttr("ErrCode","0");
                    //添加到操作日志记录
                    withdrawalslogService.insertWithdrawalslog(1,id,withdrawals_no,"用户撤销提现","","");
                }else{
                    setAttr("ErrCode","4");
                }
            }
        }catch (Exception e){
            setAttr("ErrCode","4");
        }

        renderJson();
    }
}
