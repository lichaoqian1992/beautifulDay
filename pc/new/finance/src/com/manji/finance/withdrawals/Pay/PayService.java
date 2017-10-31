package com.manji.finance.withdrawals.Pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by pudding on 2017-6-5.(一网通业务逻辑)
 */
public class PayService {

    private final String NTBNBR="20164291";//企业网银编号

    private final String PAYEE_ACC="6225881990011313";//付款公司账户

    private final String PAYEE_NAME="果然11";//付款账户名称

    private final String BACK_ADDR="/pay/PayBKBD";//回调地址

    private final String CURRENCY_NO="RMB";//使用币种

    private final String CMFK="CMFK";//代发付款


    /**
     * 生成请求一网通参数
     * @param busdat
     * @return
     */
    public String getJSON(String busdat){

       StringBuffer json=new StringBuffer("{");
        //生成原文(用于加密)
       StringBuffer  originalText=new StringBuffer("{");


        //企业网银编号
        json.append("'NTBNBR':'"+NTBNBR+"',");
        originalText.append("'NTBNBR':'"+NTBNBR+"',");

        //交易码
        json.append("'TRSCOD':'"+CMFK+"',");
        originalText.append("'TRSCOD':'"+CMFK+"',");

        //进行base64加密
        String busdats= PayUtil.encode(busdat.getBytes());

        //字段BUSDAT长度
        Integer detlen= busdats.length();
        json.append("'DATLEN':'"+detlen+"',");
        originalText.append("'DATLEN':'"+detlen+"',");

        //通讯报文ID
        json.append("'COMMID':'"+001+"',");
        originalText.append("'COMMID':'"+001+"',");

        //业务数据包
        json.append("'BUSDAT':'"+busdats+"',");

        //签名时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssss");
        String sigtim = dateFormat.format(new Date()).toString();
        json.append("'SIGTIM':'"+sigtim+"',");
        originalText.append("'SIGTIM':'"+sigtim+"',");

        //最后才添加业务数据包
        originalText.append("'BUSDAT':'"+busdats+"'}");


        //生成签名
        String sigdat=PayUtil.sign(originalText.toString(),PayUtil.SECRET_KEY,PayUtil.CHARSET);
        //进行base64加密签名
        String sigdats= PayUtil.encode(sigdat.getBytes());
        json.append("'SIGDAT':'"+sigdats+"'");

        json.append("}");
        return json.toString();
    }



    /**
     * 获取业务数据包
     */
    public String getXML(String bank_name,String bank_addr,String acc_no,String acc_name,Double trn_amt,String merch_abs){
        StringBuffer xml=new StringBuffer("<xml>");
        //生成交易日期
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("YYYYMMDD");
        String merch_date = dateFormat1.format(new Date()).toString();
        xml.append("<merch_date>"+merch_date+"</merch_date>");
        //交易时间
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HHmmSS");
        String merch_time = dateFormat2.format(new Date()).toString();
        xml.append("<merch_time>"+merch_time+"</merch_time>");
        //商户交易流水号，同一交易日期唯一
        xml.append("<merch_serial>"+"Pay"+merch_date+merch_time+"</merch_serial>");
        //商户二级分类，专车，快车等，由商户提供
        xml.append("<merch_prod>提现</merch_prod>");
        //开户行名称
        xml.append("<bank_name>"+bank_name+"</bank_name>");
        //开户地址
        xml.append("<bank_addr>"+bank_addr+"</bank_addr>");
        //银行卡号
        xml.append("<acc_no>"+acc_no+"</acc_no>");
        //卡号名称
        xml.append("<acc_name>"+acc_name+"</acc_name>");
        //币种，目前只支持人民币 RMB
        xml.append("<currency_no>"+CURRENCY_NO+"</currency_no>");
        //交易金额，以分为单位，如2000表示20.00元
        xml.append("<trn_amt>"+trn_amt.toString()+"</trn_amt>");
        //付款公司账户
        xml.append("<payee_acc>"+PAYEE_ACC+"</payee_acc>");
        //付款账户名称
        xml.append("<payee_name>"+PAYEE_NAME+"</payee_name>");
        //业务摘要，由商户提供该笔业务的相关信息，如商户名称、员工/主播信息等
        xml.append("<merch_abs>"+merch_abs+"</merch_abs>");
        //回调地址
        xml.append("<back_addr>"+BACK_ADDR+"</back_addr>");

        xml.append("</xml>");
        return xml.toString();
    }




    /**
     * 返回日志记录类
     */
    PayreturnLogRepository Payreturnlog=new PayreturnLogRepository();

    /**
     * 添加代发交易基础返回参数日志
     * @param returnjson
     * @return
     */
    public void insertPayreturnLog(String returnjson){
          Payreturnlog.insertPayreturnLog(returnjson);
    }

    /**
     * 处理银行代发结果通知
     * @param ntbnbr
     * @param trscod
     * @param datlen
     * @param commid
     * @param busdat
     * @param sigtim
     * @param sigdat
     */
    public void payBKBD(String ntbnbr,String trscod,String datlen,String commid,String busdat,String sigtim,String sigdat){
        //定义返回数据包内容
        String merch_prod="";
        String merch_date="";
        String merch_serial="";
        String trn_amt="";
        String bank_code="";
        String bank_msg="";
        String bank_date="";
        String bank_serial="";

        //如果业务数据包里没有内容
        if (busdat.equals("")){
            Payreturnlog.insertPayBKBDLog(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial
            ,trn_amt,bank_code,bank_msg,bank_date,bank_serial);
            return;
        }


        //处理解析业务数据包
        String xml=new String(PayUtil.decode(busdat));
        //转xml对象
        Document document= null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //获取根节点
        Element root = document.getRootElement();

        Element element1=root.element("merch_prod");
        merch_prod=element1.getStringValue();

        Element element2=root.element("merch_date");
        merch_date=element2.getStringValue();

        Element element3=root.element("merch_serial");
        merch_serial=element3.getStringValue();

        Element element4=root.element("trn_amt");
        trn_amt=element4.getStringValue();

        Element element5=root.element("bank_code");
        bank_code=element5.getStringValue();

        Element element6=root.element("bank_msg");
        bank_msg=element6.getStringValue();

        Element element7=root.element("bank_date");
        bank_date=element7.getStringValue();

        Element element8=root.element("bank_serial");
        bank_serial=element8.getStringValue();
        Payreturnlog.insertPayBKBDLog(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial
                ,trn_amt,bank_code,bank_msg,bank_date,bank_serial);
    }


    /**
     * 处理银行代发退票通知
     * @param ntbnbr
     * @param trscod
     * @param datlen
     * @param commid
     * @param busdat
     * @param sigtim
     * @param sigdat
     */
     public void payBKRK(String ntbnbr,String trscod,String datlen,String commid,String busdat,String sigtim,String sigdat){
         String merch_prod="";
         String merch_date="";
         String merch_serial="";
         String trn_amt="";
         String bank_code="";
         String bank_msg="";

         //如果业务数据包里没有内容
         if (busdat.equals("")){
             Payreturnlog.insertPayBKRKLog(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial
                     ,trn_amt,bank_code,bank_msg);
             return;
         }

         //处理解析业务数据包
         String xml=new String(PayUtil.decode(busdat));
         //转xml对象
         Document document= null;
         try {
             document = DocumentHelper.parseText(xml);
         } catch (DocumentException e) {
             e.printStackTrace();
         }
         //获取根节点
         Element root = document.getRootElement();

         Element element1=root.element("merch_prod");
         merch_prod=element1.getStringValue();

         Element element2=root.element("merch_date");
         merch_date=element2.getStringValue();

         Element element3=root.element("merch_serial");
         merch_serial=element3.getStringValue();

         Element element4=root.element("trn_amt");
         trn_amt=element4.getStringValue();

         Element element5=root.element("bank_code");
         bank_code=element5.getStringValue();

         Element element6=root.element("bank_msg");
         bank_msg=element6.getStringValue();

         Payreturnlog.insertPayBKRKLog(ntbnbr,trscod,datlen,commid,busdat,sigtim,sigdat,merch_prod,merch_date,merch_serial
                 ,trn_amt,bank_code,bank_msg);
     }





}
