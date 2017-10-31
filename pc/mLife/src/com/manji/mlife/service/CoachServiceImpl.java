package com.manji.mlife.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.CoachLineVo;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.CoachCity;
import com.qianmi.open.api.domain.elife.CoachCountyDetail;
import com.qianmi.open.api.domain.elife.CoachDestStation;
import com.qianmi.open.api.domain.elife.CoachLine;
import com.qianmi.open.api.domain.elife.CoachStartStation;
import com.qianmi.open.api.domain.elife.TicketTrade;
import com.qianmi.open.api.request.CoachCreateBillRequest;
import com.qianmi.open.api.request.CoachDestStationsListRequest;
import com.qianmi.open.api.request.CoachItemsListRequest;
import com.qianmi.open.api.request.CoachLinesListRequest;
import com.qianmi.open.api.request.CoachStartStationsListRequest;
import com.qianmi.open.api.response.CoachCreateBillResponse;
import com.qianmi.open.api.response.CoachDestStationsListResponse;
import com.qianmi.open.api.response.CoachItemsListResponse;
import com.qianmi.open.api.response.CoachLinesListResponse;
import com.qianmi.open.api.response.CoachStartStationsListResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class CoachServiceImpl implements CoachService {
	private static final Logger logger = Logger.getLogger(CoachServiceImpl.class);
	
	@Autowired
	private DBService service;
	
	@SuppressWarnings("rawtypes")
	@Override
	public String getStartStation() throws ApiException {
		String coachStartStationJson = "";
		// 平台基础信息
		Map<String, String> map = service.getBasePara();
		
		// 获取火车票站点信息
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		CoachStartStationsListRequest req = new CoachStartStationsListRequest();
		CoachStartStationsListResponse coachStartStationsListResponse = client.execute(req, map.get("accessToken"));
		
		logger.info("CoachStartStationsListResponse"+JSONObject.fromObject(coachStartStationsListResponse).toString());
		List<CoachStartStation> list = coachStartStationsListResponse.getStations();
		List<Map> stationList = new ArrayList<Map>();
		if (null != list) {
			//筛选站点信息
			for (int i = 0; i < list.size(); i++) {
				CoachStartStation station = list.get(i);
				List<CoachCity> cities = station.getCities();
				if (null !=cities) {
					for (int j = 0; j < cities.size(); j++) {
						CoachCity city = cities.get(j);
						Map<String, String> tempMap = new HashMap<String, String>();
						tempMap.put("name", city.getCity().getName());
						tempMap.put("code", city.getCity().getCode());
						tempMap.put("isCanOrder", city.getCity().getIsCanOrder());
						stationList.add(tempMap);
						List<CoachCountyDetail> s = city.getCounties();
						for (int k = 0; k < s.size(); k++) {
							CoachCountyDetail detail = s.get(k);
							Map<String, String> detailMap = new HashMap<String, String>();
							detailMap.put("name", detail.getName());
							detailMap.put("code", detail.getCode());
							detailMap.put("isCanOrder", detail.getIsCanOrder());
							stationList.add(detailMap);
						}
					}
				}
			}
		}

		coachStartStationJson = JSONArray.fromObject(stationList).toString();
		return coachStartStationJson;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getEndStation(String startCity) throws ApiException {
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		CoachDestStationsListRequest req = new CoachDestStationsListRequest();
		req.setStartStation(startCity);
		CoachDestStationsListResponse coachDestStationsListResponse = client.execute(req, map.get("accessToken"));
		logger.info(JSONObject.fromObject(coachDestStationsListResponse));

		List<CoachDestStation> list = coachDestStationsListResponse.getStations();
		List<Map> stationList = new ArrayList<Map>();
		if (null != list) {
			for(CoachDestStation destStation : list){
				Map<String ,String> destMap = new HashMap<String, String>();
				destMap.put("name", destStation.getName());
				destMap.put("code", destStation.getCode());
				stationList.add(destMap);
			}
		}
		String coachEndStationJson =JSONArray.fromObject(stationList).toString();
		return coachEndStationJson;	
	}

	@Override
	public String getLines(String startCity, String endCity, String date) throws ApiException {
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		CoachLinesListRequest req = new CoachLinesListRequest();
		req.setFrom(startCity);
		req.setTo(endCity);
		req.setDate(date);
		CoachLinesListResponse coachLinesListResponse = client.execute(req, map.get("accessToken"));
		logger.info("getLines"+JSONObject.fromObject(coachLinesListResponse).toString());
		List<CoachLine> coachList = coachLinesListResponse.getCoachLines();

		String coachLinesJson = JSONArray.fromObject(coachList).toString();
		logger.info("调用getLines返回车次信息:"+coachLinesJson);
		return coachLinesJson;
	}

	@Override
	public Map<String, String> createOrder(CoachLineVo vo, Map<String,String> infoMap) throws ApiException {
		Map<String, String> billMap =new HashMap<String,String>();
		try{
			Map<String, String> map = service.getBasePara();
			String acccessToken = map.get("accessToken");
			// 1.查询汽车票标准商品列表
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
			CoachItemsListRequest itemReq = new CoachItemsListRequest();
			CoachItemsListResponse itemResponse = client.execute(itemReq, acccessToken);
			logger.info("-----查询汽车票商品编号:"+JSONObject.fromObject(itemResponse).toString());
			
			// 汽车票商品编号
			String itemId = "";
			if (itemResponse != null){
				itemId =itemResponse.getItems().get(0).getItemId();
			}
			
			// 2.创建汽车票订单
			CoachCreateBillRequest req = new CoachCreateBillRequest();
			req.setStationCode(vo.getStationCode());
			req.setCoachNO(vo.getCoachNO());
			req.setDptStation(vo.getDptStation());
			req.setArrStation(vo.getArrStation());
			req.setDptDateTime(vo.getDptDateTime());
			req.setContactName(infoMap.get("contactName"));
			req.setIdnumber(infoMap.get("idNumber"));
			req.setContactTel(infoMap.get("contactTel"));
			req.setDeparture(vo.getDeparture());
			req.setDestination(vo.getDestination());
			req.setItemIdCoach(itemId);
	//		req.setItemIdInsur("5511002");
	//		req.setSeatPrice("31");
			req.setPassagers(infoMap.get("passengers"));
			CoachCreateBillResponse response = client.execute(req, acccessToken);
	
			logger.info("-----发送汽车票订单:"+JSONObject.fromObject(response).toString());
			
			TicketTrade info =response.getTicketTrade();
			if(info != null){
				info.setTotalPayCash(vo.getTicketPrice());
				billMap =service.insertTrafficData(info, "Coach", infoMap.get("userName"));
				billMap.put("errCode", "0");
			}else{
				billMap.put("redirectURL","/errInfo");
				billMap.put("errCode", "1");
				billMap.put("errMsg", "预定车票失败");
			}
		} catch (ApiException a) {
			logger.error(a.getErrCode()+a.getErrMsg());
			billMap.put("errCode", "1");
			billMap.put("errMsg", a.getErrMsg());
		}
		
		return billMap;
	}

}
