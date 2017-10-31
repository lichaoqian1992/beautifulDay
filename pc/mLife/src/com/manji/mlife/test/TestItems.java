package com.manji.mlife.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.manji.mlife.common.MD5;
import com.manji.mlife.utils.LoadJson;
import com.manji.mlife.utils.MD5util;
import com.manji.mlife.utils.WeatherLoadJson;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.common.QmcsMessage;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.CatvCreateBillRequest;
import com.qianmi.open.api.request.QmcsMessagesConsumeRequest;
import com.qianmi.open.api.request.QmcsUserPermitRequest;
import com.qianmi.open.api.request.RechargeBasePayBillRequest;
import com.qianmi.open.api.request.RechargeOrderInfoRequest;
import com.qianmi.open.api.response.CatvCreateBillResponse;
import com.qianmi.open.api.response.QmcsMessagesConsumeResponse;
import com.qianmi.open.api.response.QmcsUserPermitResponse;
import com.qianmi.open.api.response.RechargeBasePayBillResponse;
import com.qianmi.open.api.response.RechargeOrderInfoResponse;

import net.sf.json.JSONObject;

public class TestItems {
	public static void main(String[] args) throws ApiException, UnsupportedEncodingException  
    {
	
		
		//移动S1609081978943
		/*OpenClient client = new DefaultOpenClient("http://gw.api.qianmi.com/api", "10001127", "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX");

		RechargeBasePayBillRequest req = new RechargeBasePayBillRequest();
		//支付接口提供订单编号
		req.setBillId("S1609082117940");
		RechargeBasePayBillResponse response = client.execute(req, "d03dcdd828be44f68878de940732d785");
		OrderDetailInfo info = response.getOrderDetailInfo();
		System.out.println(info);*/
		
		/*OpenClient client = new DefaultOpenClient("http://gw.api.qianmi.com/api", "10001127", "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX");
		RechargeOrderInfoRequest req = new RechargeOrderInfoRequest();
		req.setBillId("S1609082117940");
		RechargeOrderInfoResponse response = client.execute(req, "d03dcdd828be44f68878de940732d785");
		
		OrderDetailInfo orderDetailInfo = response.getOrderDetailInfo();
		
		System.out.println(orderDetailInfo);
		*/
		//"http://apis.baidu.com/apistore/weatherservice/cityinfo";
		
//		QmcsMessagesConsumeRequest req = new QmcsMessagesConsumeRequest();
//		req.setQuantity(100);
//		QmcsMessagesConsumeResponse response = client.execute(req, "d03dcdd828be44f68878de940732d785");
//		List<QmcsMessage> qmcsMessages = response.getQmcsMessages();
//		System.out.println();
//		String citynameurl="http://apis.baidu.com/apistore/weatherservice/cityinfo";
//		Map<String, String> cityip=new HashMap<String, String>();
//		cityip.put("cityname", "重庆");
//		String jsoncity = WeatherLoadJson.request(citynameurl, cityip);
//		JSONObject objcity = JSONObject.fromObject(jsoncity);
//		System.out.println("----------城市信息"+objcity);
//		String errMsg = objcity.get("retMsg").toString();
		
		
		
		/*String url = "http://192.168.0.78/tools.ashx";
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("action", "PartnerOrderCheck");
		infoMap.put("partner_channel", "mjLife");
		infoMap.put("order_no", "1473333956894818990");
		infoMap.put("sign", MD5.sign("PartnerOrderCheck", "mjLife", "1473333956894818990","manjiwang"));
		String loadJSON = LoadJson.loadJSON(url, infoMap);
		
		JSONObject obj = JSONObject.fromObject(loadJSON);
		System.out.println("---------------- 同步+返回满集的数据"+obj);*/
		String prestr = "name=zhangsan&password=123&flag=0manjiwang";
		String s = MD5util.getMD5(prestr);
		System.out.println(s);
		if(s.equalsIgnoreCase("fc6b67d577660651a8bce49f31079c10")){
			System.out.println("true");
		}
	}

}
