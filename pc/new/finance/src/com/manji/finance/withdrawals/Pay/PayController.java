package com.manji.finance.withdrawals.Pay;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.manji.finance.index.MyInterceptor;
import com.manji.finance.utils.InterfaceUtil;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

import java.io.IOException;

/**
 * Created by pudding on 2017-6-8.(一网通控制器)
 */
public class PayController extends Controller {

    PayService payService=new PayService();

    /**
     * 银行代发结果通知
     */
    public void PayBK(){
        String json=getPara("json");
        String ntbnbr=getPara("NTBNBR");
        String trscod=getPara("TRSCOD");
        String datlen=getPara("DATLEN");
        String commid=getPara("COMMID");
        String busdat=getPara("BUSDAT");
        String sigtim=getPara("SIGTIM");
        String sigdat=getPara("SIGDAT");

        //执行的是(银行代发结果通知)
        if (trscod.equals("BKBD")){
            payService.payBKBD(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat);
        //执行的是(银行代发退票通知)
        }else{
            payService.payBKRK(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat);
        }
    }

        public static void main(String args[]) {
            String json="{\"NTBNBR\":\"N0004949\",\"TRSCOD\":\"BKBD\",\"DATLEN\":\"400\",\"COMMID\":\"0000256\",\"BUSDAT\":\"PHhtbD4NCjxtZXJjaF9wcm9kPumhuumjjui9pjwvbWVyY2hfcHJvZD4NCjxtZXJjaF9kYXRlPjIwMTYwODA0PC9tZXJjaF9kYXRlPg0KPG1lcmNoX3NlcmlhbD4yMDE2MDgwNDAwMDgzOTE1PC9tZXJjaF9zZXJpYWw+DQo8dHJuX2FtdD42NTwvdHJuX2FtdD4NCjxiYW5rX2NvZGU+Q01CTUI5OTwvYmFua19jb2RlPg0KPGJhbmtfbXNnPuS6pOaYk+aIkOWKnzwvYmFua19tc2c+DQo8YmFua19kYXRlPjIwMTYwMTI2PC9iYW5rX2RhdGU+DQo8YmFua19zZXJpYWw+ODIwWDYwODA0MDMxNDM1MDwvYmFua19zZXJpYWw+DQo8L3htbD4=\",\"SIGTIM\":\"2015092811020236\",\"SIGDAT\":\"gM1E3/jXR+VN+C4nSlOPV7A6FRC6FQv5idRj3eK2kEgwoAb/liLd9PfbebnRamnDf+4rREU7szryH06VoibdefuXMCOVofmmXtdtAYfLOCT8GDU2zKjJTXOINt6TWQhHvevoi7i8XrXYo80Mtqd6+/S3CWvOhgeE/k+ktwNVJ+Y=\"}";
            try {
                InterfaceUtil.PostAPI("http://localhost:8080/finance/pay/PayBKBD",json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
