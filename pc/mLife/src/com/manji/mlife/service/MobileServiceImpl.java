package com.manji.mlife.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;


import com.manji.mlife.Vo.MobileVo;
import com.manji.mlife.common.OuterId;

import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;

import com.qianmi.open.api.domain.elife.MobileItemInfo;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.PhoneInfo;

import com.qianmi.open.api.request.RechargeMobileCreateBillRequest;
import com.qianmi.open.api.request.RechargeMobileGetItemInfoRequest;
import com.qianmi.open.api.request.RechargeMobileGetPhoneInfoRequest;

import com.qianmi.open.api.response.RechargeMobileCreateBillResponse;
import com.qianmi.open.api.response.RechargeMobileGetItemInfoResponse;
import com.qianmi.open.api.response.RechargeMobileGetPhoneInfoResponse;

import net.sf.json.JSONObject;
/**
 * 
 * @author gaochao
 * 2016年7月8日下午3:26:13
 * MobileServiceImpl
 *
 */
@Service
public class MobileServiceImpl implements MobileService {
	
	@Autowired	
	private DBService service;
	
	private static final Logger logger =Logger.getLogger(MobileServiceImpl.class);
	
	// 测试使用
//	private static final String url = "http://gw.api.qianmi.com/api";
//	private static final String appKey = "10001127";
//	private static final String appSecret = "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
//	private static final String accessToken = "d03dcdd828be44f68878de940732d785";

	@Override
	public Map<String, String> getArea(String mobileNo) throws ApiException {
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		RechargeMobileGetPhoneInfoRequest req = new RechargeMobileGetPhoneInfoRequest();
		req.setPhoneNo(mobileNo);
		req.setRespType("area");
		RechargeMobileGetPhoneInfoResponse mobileGetPhoneInfoResponse = client.execute(req, map.get("accessToken"));
		logger.info("-----查询电话号码归属地:"+JSONObject.fromObject(mobileGetPhoneInfoResponse).toString());
		PhoneInfo info = mobileGetPhoneInfoResponse.getPhoneInfo();
		if (null != info) {
			map.put("errCode", "0");
			map.put("province", info.getProvince());
			map.put("operator", info.getOperator());
		}else{
			map.put("errCode", "1");
			map.put("errMsg", "查询失败");
		}
		return map;
	}

	@Override
	public Map<String, String> getDirectItem(String mobileNo, String amount) throws ApiException {

		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		RechargeMobileGetItemInfoRequest req = new RechargeMobileGetItemInfoRequest();
		req.setMobileNo(mobileNo);
		req.setRechargeAmount(amount);
		RechargeMobileGetItemInfoResponse mobileGetItemeRsponse = client.execute(req, map.get("accessToken"));
		//返回信息组装
		logger.info("-----生成话费充值信息:"+JSONObject.fromObject(mobileGetItemeRsponse).toString());
		MobileItemInfo info = mobileGetItemeRsponse.getMobileItemInfo();
		if(null !=info){
			map.put("errCode", "0");
			map.put("itemId", info.getItemId());
//			map.put("inPrice", info.getInPrice());
			map.put("rechargeAmount", info.getRechargeAmount());
			map.put("numberChoice", info.getNumberChoice());
//			map.put("province", info.getProvince());
//			map.put("city", info.getCity());
//			map.put("operator", info.getOperator());
			map.put("itemName", info.getItemName());
			map.put("mobileNo", info.getMobileNo());
			map.put("reverseFlag", info.getReverseFlag());
			map.put("advicePrice",info.getAdvicePrice());
		}else{
			map.put("errCode", "1");
			map.put("errMsg", mobileGetItemeRsponse.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, String> createBill(MobileVo vo,String userName) throws ApiException {
		
		//订单号
		String outerId = OuterId.getOuterId();
		
		Map<String, String> map = service.getBasePara();
		//生成千米订单
		try{
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		
	
		RechargeMobileCreateBillRequest req2 = new RechargeMobileCreateBillRequest();
		req2.setItemId(vo.getMobileItemId());
		req2.setMobileNo(vo.getMobileNo());
		req2.setRechargeAmount(vo.getMobileAmount());
		req2.setOuterTid(outerId);
		RechargeMobileCreateBillResponse rechargeMobileCreateBillResponse = client.execute(req2, map.get("accessToken"));
		
		logger.info("-----生成话费充值订单:"+JSONObject.fromObject(rechargeMobileCreateBillResponse).toString());
		//订单详情
		OrderDetailInfo info =rechargeMobileCreateBillResponse.getOrderDetailInfo();
		
		System.out.println(JSONObject.fromObject(info).toString());
		
		if(null !=info){
			
			map =service.insertData(info, "Mobile",userName,outerId);
			map.put("errCode", "0");
		}else{
			map.put("redirectURL","/errInfo");
			map.put("errCode", "1");
			map.put("errMsg", rechargeMobileCreateBillResponse.getSubMsg());
		}
		
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			map.put("errCode", "1");
			map.put("errMsg", a.getErrMsg());
		}
		return map;
	}

}
