package com.manji.finance.withdrawals.Pay;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;
import com.manji.finance.utils.DecriptUtil;
import com.manji.finance.utils.TimeUtils;

public class ReconciliationSercive {

	/**
	 * 单笔对账生成报文
	 * @param merch_date	     原交易日期
	 * @param merch_prod      商户二级分类     专车---快车---出租车
	 * @param merch_serial    交易流水号
	 * @return
	 */
    public String getXml(String merch_date,String merch_prod,String merch_serial){
    	/*<xml>
	    	<merch_date>20170606</merch_date>
	    	<merch_prod>专车</merch_prod>
	    	<merch_serial>old1234567890123456789</merch_serial>
	    </xml>*/
    	StringBuffer xml = new StringBuffer("<xml>");
    	xml.append("<merch_date>").append(merch_date).append("</merch_date>")
    		.append("<merch_prod>").append(merch_prod).append("</merch_prod>")
    		.append("<merch_serial>").append(merch_serial).append("</merch_serial>").append("</xml>");
    	
    	return xml.toString();
    }
    /**
     * 对总账生成报文
     * @param tx_date       商户交易日期
     * @param merch_prod    商户二级分类         专车---快车---出租车
     * @return
     */
    public String getXml2(String tx_date,String merch_prod){
    	/*<xml>
	    	< tx_date >20160808</ tx_date >
	    	<merch_prod>出租车</merch_prod>
	    </xml>*/
    	StringBuffer xml = new StringBuffer("<xml>");
    	xml.append("<tx_date>").append(tx_date).append("</tx_date>")
    		.append("<merch_prod>").append(merch_prod).append("</merch_prod>").append("</xml>");
    	
    	return xml.toString();
    }
    /**
     * 生成报文数据
     * @param xml      业务报文数据
     * @param map      请求的参数
     * @return
     */
    public String buildMessage(String xml,Map<String,String> map){
    	 
    	//1.使用base64加密业务报文数据作为BUSDAT的值
    	String busdat = PayUtil.encode(xml.getBytes());
    	
    	//2.按照a-z的顺序组合请求参数，先进行SHA-256加密然后进行base64加密得到签名
    	StringBuffer parm = new StringBuffer("BUSDAT=").append(busdat).append("&COMMID=").append(map.get("COMMID")).append("&NTBNBR=").append(map.get("NTBNBR"))
    			.append("&SIGTIM=").append(map.get("SIGTIM"))
    			.append("&TRSCOD=").append(map.get("TRSCOD")).append("&"+PayUtil.SECRET_KEY);
    	String sigdat = PayUtil.encode(DecriptUtil.SHA256(parm.toString()).getBytes());//签名数据
    	//3.生成的报文数据
    	String PKG = "{\"NTBNBR\":\""+map.get("NTBNBR")+"\",\"TRSCOD\":\""+map.get("TRSCOD")+"\",\"DATLEN\":\""+busdat.length()+"\",\"COMMID\":\""+map.get("COMMID")+"\",\"BUSDAT\":\""+busdat+"\",\"SIGTIM\":\""+map.get("SIGTIM")+"\",\"SIGDAT\":\""+sigdat+"\"}";
    	return PKG;
    }
    /**
     * 解析处理对账返回的数据
     * @param backData     返回的消息
     */
    public String analysisPkg(String backData,String type,String postXml){
    	//backData = "{\"NTBNBR\":\"N0004949\",\"TRSCOD\":\"CMDZ\",\"DATLEN\":\"352\",\"COMMID\":\"001\",\"RETCOD\":\"S\",\"BUSDAT\":\"PHhtbD4NCjxyZXNwY29kPkNNQk1COTk8L3Jlc3Bjb2Q+DQo8cmVzcG1zZz7mn6Xor6LkuqTmmJPmiJDlip88L3Jlc3Btc2c+DQo8dHJuX2FtdD4yMDAwPC90cm5fYW10Pg0KPGJhbmtfZGF0ZT4yMDE1MDkyMDwvYmFua19kYXRlPg0KPGJhbmtfc2VyaWFsPjEyMzQ1Nzg5MEFCQ0RFRjEyMzQ1Njc4OTA8L2Jhbmtfc2VyaWFsPg0KPGJhbmtfY29kZT5DTUJNQjk5PC9iYW5rX2NvZGU+DQo8YmFua19tc2c+5Lqk5piT5oiQ5YqfPC9iYW5rX21zZz4NCjwveG1sPg==\",\"SIGTIM\":\"201511100944574733\",\"SIGDAT\":\"sbLplPfNcRzWRerEoPVCAE9wwwPI6Mg776ZJsH9zmvc2/XEMKD1bgw1TXfTRHwAAdHJmjb2ztWN64muqXH4ONrYqhZ5+GtVhIwLSaS8r0p7Wdsl+n+uGdz9ZaoGY0/z7oP8rCGjieopFvUO4OWAiJ1qZ5czRbZNX49qm8+2FcJU=\"}";
    	//backData = "{\"NTBNBR\":\"N0004949\",\"TRSCOD\":\"CMHD\",\"DATLEN\":\"212\",\"COMMID\":\"001\",\"RETCOD\":\"S\",\"BUSDAT\":\"PHhtbD4NCgk8cmVzcGNvZD5DTUJNQjk5PC9yZXNwY29kPg0KCTxyZXNwbXNnPuS6pOaYk+aIkOWKnzwvcmVzcG1zZz4NCgk8bWVyY2hfcHJvZD7kuJPovaY8L21lcmNoX3Byb2Q+DQoJPGZrX251bT4xMDAwPC9ma19udW0+DQoJPGZrX2FtdD41MDAwMDAwPC9ma19hbXQ+DQo8L3htbD4=\",\"SIGTIM\":\"201511100944574733\",\"SIGDAT\":\"sbLplPfNcRzWRerEoPVCAE9wwwPI6Mg776ZJsH9zmvc2/XEMKD1bgw1TXfTRHwAAdHJmjb2ztWN64muqXH4ONrYqhZ5+GtVhIwLSaS8r0p7Wdsl+n+uGdz9ZaoGY0/z7oP8rCGjieopFvUO4OWAiJ1qZ5czRbZNX49qm8+2FcJU=\"}";
    	try {
    		
    		Record single = new Record();
    		Record count = new Record();
    		
    		int i = backData.indexOf("\"BUSDAT\":\"")+"\"BUSDAT\":\"".length();
    		
    		//1.得到BUSDAT里面的数据
        	String busdat = backData.substring(i, backData.indexOf("\",\"SIGTIM\""));
        	
        	//2.base64解密
        	byte[] a = PayUtil.decode(busdat);
        	
        	//3.得到返回的数据XMl
        	String backXml = new String(a,"UTF-8").replace(" ", "");
			
			if(backXml.length() > 0){
				Map<String,String> map = new HashMap<String,String>();
				if(type.equals("单笔对账")){
					map = getSingle(backXml);//单笔对账
				}else if(type.equals("对总账")){
					map = getCount(backXml);//对总账
				}
	        	if(map.get("respcod").equals("CMBMB99")){
	        		//表示交易查询成功
	        		//保存返回的交易信息
	        		String ntbnbr = "";
	        		String trscod = "";
	        		String datlen = "";
	        		String commid = "";
	        		String retcod = "";
	        		String errmsg = "";
	        		String sigtime = "";
	        		String sigdat = "";
	        		String bank_serial = "";
	        		if(backData.indexOf("\"NTBNBR\":\"") != -1){
	        			ntbnbr = backData.substring(backData.indexOf("\"NTBNBR\":\"")+"\"NTBNBR\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"NTBNBR\":\"")));
	        		}else{
	        			ntbnbr = "无";
	        		}
	        		if(backData.indexOf("\"TRSCOD\":\"") != -1){
	        			trscod = backData.substring(backData.indexOf("\"TRSCOD\":\"")+"\"TRSCOD\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"TRSCOD\":\"")));
	        		}else{
	        			trscod = "无";
	        		}
	        		if(backData.indexOf("\"DATLEN\":\"") != -1){
	        			datlen = backData.substring(backData.indexOf("\"DATLEN\":\"")+"\"DATLEN\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"DATLEN\":\"")));
	        		}else{
	        			datlen = "无";
	        		}
	        		if(backData.indexOf("\"COMMID\":\"") != -1){
	        			commid = backData.substring(backData.indexOf("\"COMMID\":\"")+"\"COMMID\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"COMMID\":\"")));
	        		}else{
	        			commid = "无";
	        		}
	        		if(backData.indexOf("\"RETCOD\":\"") != -1){
	        			retcod = backData.substring(backData.indexOf("\"RETCOD\":\"")+"\"RETCOD\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"RETCOD\":\"")));
	        		}else{
	        			retcod = "无";
	        		}
	        		if(backData.indexOf("\"ERRMSG\":\"") != -1){
	        			errmsg = backData.substring(backData.indexOf("\"ERRMSG\":\"")+"\"ERRMSG\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"ERRMSG\":\"")));
	        		}else{
	        			errmsg = "无";
	        		}
	        		if(backData.indexOf("\"SIGTIM\":\"") != -1){
	        			sigtime = backData.substring(backData.indexOf("\"SIGTIM\":\"")+"\"SIGTIM\":\"".length(),backData.indexOf("\",\"",backData.indexOf("\"SIGTIM\":\"")));
	        		}else{
	        			sigtime = "无";
	        		}
	        		if(backData.indexOf("\"SIGDAT\":\"") != -1){
	        			sigdat = backData.substring(backData.indexOf("\"SIGDAT\":\"")+"\"SIGDAT\":\"".length(),backData.indexOf("\"}"));
	        		}else{
	        			sigdat = "无";
	        		}
	        		Record record = new Record().set("message", backData).set("ntbnbr", ntbnbr).set("trscod", trscod)
	        				.set("datlen", datlen).set("commid", commid).set("retcod", retcod).set("errmsg", errmsg)
	        				.set("sigtime", sigtime).set("sigdat", sigdat).set("busdat", busdat).set("back_xml", backXml).set("post_xml", postXml)
	        				.set("type", type).set("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	        		if(type.equals("单笔对账")){
	        			String single_respcod = backXml.substring(backXml.indexOf("<respcod>")+"<respcod>".length(),backXml.indexOf("</respcod>"));
	        			String single_respmsg = backXml.substring(backXml.indexOf("<respmsg>")+"<respmsg>".length(),backXml.indexOf("</respmsg>"));
	        			String single_trn_amt = backXml.substring(backXml.indexOf("<trn_amt>")+"<trn_amt>".length(),backXml.indexOf("</trn_amt>"));
	        			String single_bank_date = backXml.substring(backXml.indexOf("<bank_date>")+"<bank_date>".length(),backXml.indexOf("</bank_date>"));
	        			String single_bank_serial = backXml.substring(backXml.indexOf("<bank_serial>")+"<bank_serial>".length(),backXml.indexOf("</bank_serial>"));
	        			String single_bank_code = backXml.substring(backXml.indexOf("<bank_code>")+"<bank_code>".length(),backXml.indexOf("</bank_code>"));
	        			String single_bank_msg = backXml.substring(backXml.indexOf("<bank_msg>")+"<bank_msg>".length(),backXml.indexOf("</bank_msg>"));
	        			single.set("respcod", single_respcod).set("respmsg", single_respmsg).set("trn_amt", single_trn_amt)
	        					.set("bank_date", single_bank_date).set("bank_serial", single_bank_serial).set("bank_code", single_bank_code)
	        					.set("bank_msg", single_bank_msg).set("commid", commid);
	        		}else{
	        			String count_respcod = backXml.substring(backXml.indexOf("<respcod>")+"<respcod>".length(),backXml.indexOf("</respcod>"));
	        			String count_respmsg = backXml.substring(backXml.indexOf("<respmsg>")+"<respmsg>".length(),backXml.indexOf("</respmsg>"));
	        			String count_merch_prod = backXml.substring(backXml.indexOf("<merch_prod>")+"<merch_prod>".length(),backXml.indexOf("</merch_prod>"));
	        			String count_fk_num = backXml.substring(backXml.indexOf("<fk_num>")+"<fk_num>".length(),backXml.indexOf("</fk_num>"));
	        			String count_fk_amt = backXml.substring(backXml.indexOf("<fk_amt>")+"<fk_amt>".length(),backXml.indexOf("</fk_amt>"));
	        			String create_time = postXml.substring(postXml.indexOf("<tx_date>")+"<tx_date>".length(),postXml.indexOf("</tx_date>"));
	        			count = new Record().set("respcod", count_respcod).set("respmsg", count_respmsg).set("merch_prod", count_merch_prod)
	        					.set("fk_num", count_fk_num).set("fk_amt", count_fk_amt).set("commid", commid).set("create_time", create_time);
	        		}		
	        		if(findBankSerial(postXml) != null && findBankSerial(postXml).size() > 0){
	        			return "已记录";
	        		}else{
	        			Boolean flag = DButils.mysql.save("t_reconc", record);	
	        			if(type.equals("单笔对账")){
	        				DButils.mysql.save("t_back_single", single);	
	        			}else{
	        				DButils.mysql.save("t_back_count", count);
	        			}
	        			if(flag){
		        			System.out.println("保存成功！");
		        			return backXml;
		        		}else{
		        			System.out.println("保存失败！");
		        		}
	        		}
	        	}
			}else{
				String message = backData.substring(backData.indexOf("\"ERRMSG\":\"")+"\"ERRMSG\":\"".length(),backData.indexOf("\",\"BUSDAT\""));
				
				return message;
			}
        	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 处理代发单笔对账返回的数据值
     * @param xmlData
     * @return
     */
    public Map<String,String> getSingle(String backXml){
    	
    	Map<String,String> map = new HashMap<String,String>();
    	String respcod = backXml.substring(backXml.indexOf("<respcod>")+"<respcod>".length(), backXml.indexOf("</respcod>"));
		//System.out.println(backXml);
		String respmsg = backXml.substring(backXml.indexOf("<respmsg>")+"<respmsg>".length(), backXml.indexOf("</respmsg>"));
		String trn_amt = backXml.substring(backXml.indexOf("<trn_amt>")+"<trn_amt>".length(), backXml.indexOf("</trn_amt>"));
		String bank_date = backXml.substring(backXml.indexOf("<bank_date>")+"<bank_date>".length(), backXml.indexOf("</bank_date>"));
		String bank_serial = backXml.substring(backXml.indexOf("<bank_serial>")+"<bank_serial>".length(), backXml.indexOf("</bank_serial>"));
		String bank_code = backXml.substring(backXml.indexOf("<bank_code>")+"<bank_code>".length(), backXml.indexOf("</bank_code>"));
		String bank_msg = backXml.substring(backXml.indexOf("<bank_msg>")+"<bank_msg>".length(), backXml.indexOf("</bank_msg>"));
		//System.out.println(respcod+" "+respmsg+" "+trn_amt+" "+bank_date+" "+bank_serial+" "+bank_code+" "+bank_msg);
		map.put("respcod", respcod);
		map.put("respmsg", respmsg);
		map.put("trn_amt", trn_amt);
		map.put("bank_date", bank_date);
		map.put("bank_serial", bank_serial);
		map.put("bank_code", bank_code);
		map.put("bank_msg", bank_msg);
		
		return map;
    }
    /**
     * 处理代发对总账返回的数据值
     * @param xmlData
     * @return
     */
    public Map<String,String> getCount(String backXml){
    	Map<String,String> map = new HashMap<String,String>();
    	String respcod = backXml.substring(backXml.indexOf("<respcod>")+"<respcod>".length(), backXml.indexOf("</respcod>"));
		String respmsg = backXml.substring(backXml.indexOf("<respmsg>")+"<respmsg>".length(), backXml.indexOf("</respmsg>"));
		String merch_prod = backXml.substring(backXml.indexOf("<merch_prod>")+"<merch_prod>".length(), backXml.indexOf("</merch_prod>"));
		String fk_num = backXml.substring(backXml.indexOf("<fk_num>")+"<fk_num>".length(), backXml.indexOf("</fk_num>"));
		String fk_amt = backXml.substring(backXml.indexOf("<fk_amt>")+"<fk_amt>".length(), backXml.indexOf("</fk_amt>"));
		System.out.println(respcod+" "+respmsg+" "+merch_prod+" "+fk_num+" "+fk_amt);
		map.put("respcod", respcod);
		map.put("respmsg", respmsg);
		map.put("merch_prod", merch_prod);
		map.put("fk_num", fk_num);
		map.put("fk_amt", fk_amt);
		
		return map;
    }
    /**
     * 查询银行交易流水信息
     * @param bank_serial
     * @return
     */
    public List<Record> findBankSerial(String postXml){
    	
    	return DButils.mysql.find("select * from t_reconc where post_xml='"+postXml+"'");
    }
    /**
     * 保存明细账文件的地址
     * 文件名称：hgkda-2017051617.CMDX.tar.gz    
     */
    public void addFileInfo(){
    	String url = "D:/circlemes/download/素材.rar";
    	Record record = new Record().set("url", url).set("name", "hgkda-2017051617.CMDX.tar").set("create_time", TimeUtils.getTimeUtilDay());
    	
    	DButils.mysql.save("t_file", record);
    }
    /**
     * 查询文件记录信息
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    public Page<Record> findFile(String startTime,String endTime,int page){
    	StringBuffer sql = new StringBuffer("from t_file where 1=1");
    	if(startTime != null && !"".equals(startTime)){
    		sql.append(" and create_time >='"+startTime+"'");
    	}
    	if(endTime != null && !"".equals(endTime)){
    		sql.append(" and create_time <='"+endTime+"'");
    	}
    	sql.append(" order by create_time desc");
    	return DButils.mysql.paginate(page, 20, "select *", sql.toString());
    }
    /**
     * 查询对总账信息
     * @return
     */
    public Page<Record> findCount(int pageNum){
    	
    	StringBuffer sql = new StringBuffer("from t_back_count where 1=1");
    	return DButils.mysql.paginate(pageNum, 20, "select *", sql.toString());
    }
    /**
     * 查询单笔对账信息
     * @return
     */
    public Page<Record> findSingle(int pageNum){
    	
    	StringBuffer sql = new StringBuffer("from t_back_single where 1=1");
    	return DButils.mysql.paginate(pageNum, 20, "select *", sql.toString());
    }
}
