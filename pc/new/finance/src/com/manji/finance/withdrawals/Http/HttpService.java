package com.manji.finance.withdrawals.Http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 * Created by pudding on 2017-4-21.(生成treemap请求参数)
 */
public class HttpService {

        //冻结账户接口处理
        public TreeMap<String, String> getHttpTreeMap(String action,String alert,String role_type,String role_value,String userid){
            TreeMap<String, String> map = new TreeMap<String, String>();
            String noncestr = String.valueOf(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String tranSN = dateFormat.format(new Date()).toString();
            map.put("accessKey", "javamanager");
            map.put("action", action);
            map.put("noncestr", noncestr);
            map.put("remark", alert);
            map.put("roleType", role_type);
            map.put("roleValue", role_value);
            map.put("tranSN", tranSN + "2");
            map.put("userId", userid);
            return map;
        }


        //修改提现单接口处理
        public TreeMap<String, String> getHttpTreeMaps(String id,String remark,String stats){
            String noncestr = String.valueOf(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String tranSN = dateFormat.format(new Date()).toString();
            TreeMap<String, String> map = new TreeMap<String, String>();
            map.put("accessKey", "javamanager");
            map.put("action", "UserWithdrawals");
            map.put("idList", id);
            map.put("noncestr", noncestr);
            map.put("remark", remark);
            map.put("status", stats);
            map.put("tranSN", tranSN + "1");
            return map;
        }
        
        //修改结算状态接口处理
        public TreeMap<String, String> getSettleTreeMap(String orderNo,String orderType){
        	String noncestr = String.valueOf(System.currentTimeMillis());
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        	String tranSN = dateFormat.format(new Date()).toString();
        	TreeMap<String, String> map = new TreeMap<String, String>();
        	map.put("accessKey", "javamanager");
        	map.put("action", "AccountBalanceNow");
        	map.put("noncestr", noncestr);
        	map.put("orderNo", orderNo);
        	map.put("orderType", orderType);
        	map.put("tranSN", tranSN + "3");
        	return map;
        }

    //修改账户提现手续费状态处理
    public TreeMap<String,String> getCounterFeeTreeMap(String accinfoid,String stus){
        String noncestr = String.valueOf(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tranSN = dateFormat.format(new Date()).toString();
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("accessKey", "javamanager");
        map.put("action", "SetWithdrawalsCommission");
        map.put("commissionLev", stus);
        map.put("noncestr", noncestr);
        map.put("tranSN", tranSN + "4");
        map.put("userAccountinfoId", accinfoid);
        return map;
    }









}
