package com.manji.finance.withdrawals.RechargeConfig;

import com.jfinal.plugin.activerecord.Record;

/**
 * Created by pudding on 2017-4-24.
 */
public class RechargeConfigService {

    RechargeConfigRepository rechargeConfigRepository=new RechargeConfigRepository();


    /**
     * 通过id查询审核人
     */
    public Record getRechargeConfigbyId(int id){
        return rechargeConfigRepository.getRechargeConfigbyId(id);
    }
}
