package com.manji.mlife.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.common.MD5;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.TrafficOrder;
import com.manji.mlife.utils.LoadJson;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.TicketTrade;
import com.qianmi.open.api.request.AirOrderPayRequest;
import com.qianmi.open.api.request.CoachOrderPayRequest;
import com.qianmi.open.api.request.RechargeBasePayBillRequest;
import com.qianmi.open.api.request.TrainOrderPayRequest;
import com.qianmi.open.api.response.AirOrderPayResponse;
import com.qianmi.open.api.response.CoachOrderPayResponse;
import com.qianmi.open.api.response.RechargeBasePayBillResponse;
import com.qianmi.open.api.response.TrainOrderPayResponse;

import net.sf.json.JSONObject;

@Service
public class CallbackServiceImpl implements CallbackService {

	private static final Logger logger = Logger.getLogger(CallbackServiceImpl.class);
	
	@Autowired
	private DBService service;
	@Autowired
	private  SimpleCodeMapper   codeMapper;
	
	//充值类
	@Override
	public boolean payChargeBill(String billId) {
		
		System.out.println("-----------+进入千米支付方法-----------"+billId);
		
		Map<String,String> map =service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		RechargeBasePayBillRequest req = new RechargeBasePayBillRequest();
		//支付接口提供订单编号
		req.setBillId(billId);
		RechargeBasePayBillResponse response;
		try {
			response = client.execute(req, map.get("accessToken"));
			OrderDetailInfo info = response.getOrderDetailInfo();
			
			System.out.println("------------------+千米返回的支付信息"+info);
			logger.info("RechargeBasePayBillRequest"+JSONObject.fromObject(response).toString());
		
			if(info!=null){
				//根据订单号查询充值订单
				ChargeOrder chargeOrder = service.queryChargeBills(billId);
				//payState	String	1	订单付款状态 0 未付款1 已付款
				//rechargeState	String	1	订单充值状态 0充值中 1成功 9撤销
				chargeOrder.setPaystate(info.getPayState());//
				chargeOrder.setRechargestate(info.getRechargeState());
				//保存充值类订单
				service.updateChargeOrderSelective(chargeOrder);
				
			}else{
				
				ChargeOrder chargeOrder = service.queryChargeBills(billId);
				//payState	String	1	订单付款状态 0 未付款1 已付款
				//rechargeState	String	1	订单充值状态 0充值中 1成功 9撤销
				chargeOrder.setPaystate("2");//
				chargeOrder.setRechargestate("2");
				//保存充值类订单
				
				

				
				//退款
				
				String payUrl =codeMapper.getValue("payURL");
				String signKey =codeMapper.getValue("signKey");
				
				Map<String, String> infoMap = new HashMap<String, String>();
				infoMap.put("action", "PartnerOrderBack");
				infoMap.put("partner_channel", "mjLife");
				infoMap.put("order_no", chargeOrder.getOuterid());
				infoMap.put("sign", MD5.sign("PartnerOrderBack", "mjLife", chargeOrder.getOuterid(),signKey));
				String loadJSON = null;
				try {
					loadJSON = LoadJson.loadJSON(payUrl, infoMap);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				JSONObject obj = JSONObject.fromObject(loadJSON);

				if ("0".equals(obj.getString("ErrCode"))) {
					
				}else{
					chargeOrder.setPaystate("3");//
					
				}
				System.out.println("---------------- 同步+返回满集的数据"+obj);
				
				service.updateChargeOrderSelective(chargeOrder);
				
				
				return false;
			}
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	/**
	 * 票务类
	 * @author gaochao
	 * @param tradNo
	 * 2016年8月24日下午2:37:55
	 * @throws ApiException 
	 *
	 */
	@Override
	public void payTrafficOrder(String tradNo,String type) throws ApiException {
		
		System.out.println("--------------进入票务类向千米付款-------------------");
		//Air飞机 Train火车 Coach汽车
		Map<String,String> map =service.getBasePara();

		TicketTrade ticketTrade=null;
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		switch (type) {
		
		case "Air":
			AirOrderPayRequest req = new AirOrderPayRequest();
			req.setTradeNo(tradNo);
			AirOrderPayResponse response = client.execute(req, map.get("accessToken"));
			logger.info("AirOrderPayResponse"+JSONObject.fromObject(response).toString());

			ticketTrade = response. getTicketTrade();
			break;
			
		case "Train":
			
			TrainOrderPayRequest req1 = new TrainOrderPayRequest();
			req1.setTradeNo(tradNo);
			TrainOrderPayResponse response1 = client.execute(req1, map.get("accessToken"));
			
			logger.info("TrainOrderPayResponse"+JSONObject.fromObject(response1).toString());

			ticketTrade= response1.getTicketTrade();
			break;

		default:
			 
			CoachOrderPayRequest req2 = new CoachOrderPayRequest();
			req2.setTradeNo(tradNo);
			CoachOrderPayResponse response2 = client.execute(req2, map.get("accessToken"));
			
			logger.info("CoachOrderPayResponse"+JSONObject.fromObject(response2).toString());

			ticketTrade = response2.getTicketTrade();
			
			break;
		}
		
		
		if(ticketTrade!=null){
			System.out.println("---------------批量更改飞机票订单状态-----------------");
			//根据订单号查询票务订单
			  List<TrafficOrder> trafficOrders = service.queryTrafficBills(tradNo);
			  //state	Number	2	订单状态0-预定完成，待支付，1-已完成，9-已取消
			  //billState	String	1	支付状态 0：未支付，1：已支付
			  //etime	String	2015-01-01 17:01:02	完成时间
			  //billTime	String	2015-01-01 17:01:02	支付时间
			  // 订单展示状态 0:交易中,9:出票失败,1:出票成功,6:退票中,7:退票失败,10:已退票,11:已退款
			  for (int i = 0; i < trafficOrders.size(); i++) {
				  //批量更改飞机票订单状态
				  
				  TrafficOrder trafficOrder = trafficOrders.get(i);
				  
				  trafficOrder.setState(ticketTrade.getTicketOrders().get(i).getState().toString());
				  //upddate 修改票务
				  service.updateTrafficSelective(trafficOrder);
			}
			 
			
		}
		
	}

	@Override
	public String getUserUrl() {

		String url =codeMapper.getValue("UserURL");

		return url;
	}

	@Override
	public String getPayUrl() {
		
		String url =codeMapper.getValue("payURL");
		
		return url;
	}

	@Override
	public String getSignKey() {
		
		String key =codeMapper.getValue("signKey");
		
		return key;
	}
	@Override
	public boolean PartnerOrderBack(String  outerId){
		
		boolean b=false;
		
		String payUrl =codeMapper.getValue("payURL");
		String signKey =codeMapper.getValue("signKey");
		
		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("action", "PartnerOrderBack");
		infoMap.put("partner_channel", "mjLife");
		infoMap.put("order_no", outerId);
		infoMap.put("sign", MD5.sign("PartnerOrderBack", "mjLife", outerId,signKey));
		String loadJSON = null;
		try {
			loadJSON = LoadJson.loadJSON(payUrl, infoMap);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject obj = JSONObject.fromObject(loadJSON);

		if ("0".equals(obj.getString("ErrCode"))) {
				b=true;
		}
		System.out.println("---------------- 同步+返回满集的数据"+obj);
		
		return b;
	}
	
	
	
	
	
}
