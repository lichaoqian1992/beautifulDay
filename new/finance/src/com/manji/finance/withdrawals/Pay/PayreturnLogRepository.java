package com.manji.finance.withdrawals.Pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manji.finance.utils.DButils;
import com.manji.finance.utils.TimeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.util.Date;

/**
 * Created by pudding on 2017-6-7.(一网通返回数据日志信息记录表)
 */
public class PayreturnLogRepository extends DButils {

    /**
     * 添加返回日志
     */
    public void insertPayreturnLog(String returnjson){
        JSONObject json= JSON.parseObject(returnjson);
        String ntbnbr=json.getString("NTBNBR");
        String trscod=json.getString("TRSCOD");
        String datlen=json.getString("DATLEN");
        String commid=json.getString("COMMID");
        String retcod=json.getString("RETCOD");
        //String busdat=json.getString("BUSDAT");
        String busdat="PHhtbD4NCjxtZXJjaF9kYXRlPjIwMTYwODA1PC9tZXJjaF9kYXRlPg0KPG1lcmNoX3RpbWU+MTcyNTQ3PC9tZXJjaF90aW1lPg0KPG1lcmNoX3NlcmlhbD4yMDE2MDgwNUNNQjE0NzAzODkxNDc1MTE8L21lcmNoX3NlcmlhbD4NCjxyZXNwY29kPkNNQk1COTk8L3Jlc3Bjb2Q+DQo8cmVzcG1zZz69u9LXs8m5pjwvcmVzcG1zZz4NCjwveG1sPg0K";
        String sigtim=json.getString("SIGTIM");
        String sigdat=json.getString("SIGDAT");
        String errmsg=json.getString("ERRMSG");
        //定义返回交易参数(业务数据)
        String merch_date="";
        String merch_time="";
        String merch_serial="";
        String respcod="";
        String respmsg="";
        //如果返回业务包是空的则不做处理业务操作
        if (busdat.equals("")){
        }else{
            //处理业务xml(业务数据)
            //用base64解密
            String xml= new String(PayUtil.decode(busdat));
            //转xml对象
            Document document= null;
            try {
                document = DocumentHelper.parseText(xml);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            //获取根节点
            Element root = document.getRootElement();


            Element element1=root.element("merch_date");
            merch_date=element1.getStringValue();

            Element element2=root.element("merch_time");
            merch_time=element2.getStringValue();

            Element element3=root.element("merch_serial");
            merch_serial=element3.getStringValue();

            Element element4=root.element("respcod");
            respcod=element4.getStringValue();

            Element element5=root.element("respmsg");
            respmsg=element5.getStringValue();
        }
        String sql="INSERT INTO t_Payreturn_log VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        mysql.update(sql,null,returnjson,ntbnbr,trscod,datlen,commid,retcod,busdat,sigtim,sigdat, TimeUtils.getTimeUtilSecond(),errmsg,merch_date,
                merch_time,merch_serial,respcod,respmsg);
    }


    //添加银行代发结果通知日志
    public void insertPayBKBDLog(String ntbnbr,String trscod,String datlen,String commid,String busdat,String sigtim,String sigdat,
            String merch_prod,String merch_date,String merch_serial,String trn_amt,
            String bank_code,String bank_msg,String bank_date,String bank_serial){

        String sql="INSERT INTO t_pay_bkbd VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        mysql.update(sql,null,ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial,trn_amt,bank_code,bank_msg,bank_date,bank_serial,TimeUtils.getTimeUtilSecond());
    }

    //添加银行代发退票通知
    public void insertPayBKRKLog(String ntbnbr,String trscod,String datlen,String commid,String busdat,String sigtim,String sigdat,
                                 String merch_prod,String merch_date,String merch_serial,String trn_amt,
                                 String bank_code,String bank_msg){
        String sql="INSERT INTO t_pay_bkrk VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        mysql.update(sql,null,ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial,trn_amt,bank_code,bank_msg,TimeUtils.getTimeUtilSecond());
    }

}
