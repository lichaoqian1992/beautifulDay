package com.manji.mlife.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.TrainLineVo;
import com.manji.mlife.Vo.TrainVo;
import com.manji.mlife.common.OuterId;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.TicketTrade;
import com.qianmi.open.api.request.TrainInsursListRequest;
import com.qianmi.open.api.request.TrainItemsListRequest;
import com.qianmi.open.api.request.TrainLinesListRequest;
import com.qianmi.open.api.request.TrainOrderCreateRequest;
import com.qianmi.open.api.request.TrainStationsListRequest;
import com.qianmi.open.api.response.TrainInsursListResponse;
import com.qianmi.open.api.response.TrainItemsListResponse;
import com.qianmi.open.api.response.TrainLinesListResponse;
import com.qianmi.open.api.response.TrainOrderCreateResponse;
import com.qianmi.open.api.response.TrainStationsListResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class TrainServiceImpl implements TrainService {

	
	@Autowired
	private DBService service;
	
	// 测试使用
//	private static final String url = "http://gw.api.qianmi.com/api";
//	private static final String appKey = "10001127";
//	private static final String appSecret = "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
//	private static final String accessToken = "d03dcdd828be44f68878de940732d785";

	private static final Logger logger = Logger.getLogger(TrainServiceImpl.class);
	
	@Override
	public String getStation() throws ApiException {
		
		
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		TrainStationsListRequest req = new TrainStationsListRequest();
		TrainStationsListResponse trainStationsListResponse = client.execute(req, map.get("accessToken"));
		
		logger.info("TrainStationsListResponse"+JSONObject.fromObject(trainStationsListResponse).toString());
		
		String stationJson =JSONArray.fromObject(trainStationsListResponse.getStations()).toString();
		
		return stationJson;
	}

	@Override
	public String getTicket(String startCity, String endCity, String date) throws ApiException {
		String ticketJson = "";
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		TrainLinesListRequest req = new TrainLinesListRequest();
		req.setFrom(startCity);
		req.setTo(endCity);
		req.setDate(date);
		TrainLinesListResponse trainLinesListResponse = client.execute(req, map.get("accessToken"));
		logger.info(JSONObject.fromObject(trainLinesListResponse).toString());
		if(trainLinesListResponse.getTrainlines() != null){
			ticketJson =JSONArray.fromObject(trainLinesListResponse.getTrainlines()).toString();
		}
		
		return ticketJson;
	}

	@Override
	public Map<String, String> createOrder(TrainLineVo vo, Map<String,String> infoMap,String totalPrice) throws ApiException {
		
		Map<String, String> billMap =new HashMap<String, String>();

		Map<String, String> map = service.getBasePara();
		try{
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		TrainItemsListRequest req2 = new TrainItemsListRequest();
		TrainItemsListResponse response2 = client.execute(req2, map.get("accessToken"));
		
		logger.info("TrainItemsListResponse"+JSONObject.fromObject(response2).toString());
		
		String itemId =response2.getItems().get(0).getItemId();
		
		
		
		TrainOrderCreateRequest req = new TrainOrderCreateRequest();
		req.setFrom(vo.getFrom());
		req.setTo(vo.getTo());
		req.setDate(vo.getDate());
		req.setTrainNumber(vo.getTrainNumber());
		req.setStartTime(vo.getStartTime());
		req.setContactName(infoMap.get("contactName"));
		req.setContactTel(infoMap.get("contactTel"));
		req.setPassagers(infoMap.get("passenger"));
		req.setItemIdTrain(itemId);
		if(!"0".equals(infoMap.get("insuranceId"))){
			req.setItemIdInsur(infoMap.get("insuranceId"));
		}
		
		TrainOrderCreateResponse response = client.execute(req, map.get("accessToken"));
		
		logger.info("TrainOrderCreateResponse"+JSONObject.fromObject(response).toString());
		
		TicketTrade info =response.getTicketTrade();
		
		if(info !=null){
			info.setTotalPayCash(totalPrice);
			billMap =service.insertTrafficData(info, "Train", infoMap.get("userName"));
			billMap.put("errCode", "0");
		}else{
			billMap.put("redirectURL","/errInfo");
			billMap.put("errCode", "1");
			billMap.put("errMsg", response.getSubMsg());
		}
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			billMap.put("errCode", "1");
			billMap.put("errMsg", a.getErrMsg());
		}
		return billMap;
	}
	
	
	
	
	
	


	@Override
	public String cancleOrder(String tradeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsurance() throws ApiException {
		String insuranceJson ="";
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		TrainInsursListRequest req = new TrainInsursListRequest();
		TrainInsursListResponse response = client.execute(req, map.get("accessToken"));
		
		logger.info("TrainInsursListResponse"+JSONObject.fromObject(response).toString());
		
		List<AdminItem> insurance =response.getItems();
		
		if(insurance !=null){
			insuranceJson =JSONArray.fromObject(insurance).toString();
		}
		
		return insuranceJson;
	}

}
