package com.manji.mlife.service;



import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.FineVo;
import com.manji.mlife.common.OuterId;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.ItemInfo;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.RechargeBaseGetItemInfoRequest;
import com.qianmi.open.api.request.TrafficFineCreateBillRequest;
import com.qianmi.open.api.request.TrafficFineItemListRequest;
import com.qianmi.open.api.response.RechargeBaseGetItemInfoResponse;
import com.qianmi.open.api.response.TrafficFineCreateBillResponse;
import com.qianmi.open.api.response.TrafficFineItemListResponse;

import net.sf.json.JSONObject;

@Service
public class FineServiceImpl implements FineService {

	// // 测试使用
	// private static final String url = "http://gw.api.qianmi.com/api";
	// private static final String appKey = "10001127";
	// private static final String appSecret =
	// "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
	// private static final String accessToken =
	// "d03dcdd828be44f68878de940732d785";

	private static final Logger logger = Logger.getLogger(FineServiceImpl.class);

	@Autowired
	private DBService service;

	@Override
	public Map<String, String> queryItem(String province, String city)  {

		Map<String, String> map = service.getBasePara();
		
		
		try{
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		TrafficFineItemListRequest req = new TrafficFineItemListRequest();
		req.setProvince(province);
		req.setCity(city);
		TrafficFineItemListResponse trafficFineItemListResponse = client.execute(req, map.get("accessToken"));

		logger.info("-----交通罚款缴费列表查询:"+JSONObject.fromObject(trafficFineItemListResponse).toString());

		List<AdminItem> list = trafficFineItemListResponse.getItems();

		if (null != list) {

			// 查询商品手续费
			RechargeBaseGetItemInfoRequest req2 = new RechargeBaseGetItemInfoRequest();
			req2.setItemId(list.get(0).getItemId());
			RechargeBaseGetItemInfoResponse rechargeBaseGetItemInfoResponse = client.execute(req2,
					map.get("accessToken"));

			logger.info("-----交通罚款缴费手续费查询:"
					+ JSONObject.fromObject(rechargeBaseGetItemInfoResponse).toString());
			ItemInfo info = rechargeBaseGetItemInfoResponse.getItemInfo();
			if (null != info && !("".equals(info))) {
				// 手续费
				String fineFee = info.getInPriceExpression();
				String[] strArr = new String[10];
				strArr = fineFee.split("=");
				fineFee = strArr[1];
				map.put("errCode","0");
				map.put("itemId", list.get(0).getItemId());
				map.put("itemName", list.get(0).getItemName());
				map.put("fineFee", fineFee);
			}
		}
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			map.put("errCode","1");
			map.put("errMsg",a.getErrMsg());
		}

		return map;
	}

	@Override
	public Map<String, String> createBill(FineVo vo, String userName)  {

		Map<String, String> map = service.getBasePara();
		String outerId = OuterId.getOuterId();
		
		try{
			
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
	
			// 生成千米订单
			TrafficFineCreateBillRequest req = new TrafficFineCreateBillRequest();
			req.setCarNo(vo.getCarNo());
			req.setEngineId(vo.getEngineId());
			req.setFineNo(vo.getFineNo());
			req.setFineFee(vo.getFineFee());
//			req.setDelayFee(vo.getDelayFee());//目前不支持缴纳含滞纳金，所以设置滞纳金为0
			req.setDelayFee("0");
			req.setCarType(vo.getCarType());
			req.setItemId(vo.getItemId());
			TrafficFineCreateBillResponse trafficFineCreateBillResponse = client.execute(req, map.get("accessToken"));
	
			logger.info("-----创建交通罚款缴费订单:" + JSONObject.fromObject(trafficFineCreateBillResponse).toString());
			OrderDetailInfo info = trafficFineCreateBillResponse.getOrderDetailInfo();
			if (null != info) {
				info.setSaleAmount(vo.getTotalFee());
				// 记录订单信息
				map = service.insertData(info, "Fine", userName, outerId);
				map.put("errCode", "0");
			}else{
				map.put("redirectURL","/errInfo");
				map.put("errCode", "1");
				map.put("errMsg", trafficFineCreateBillResponse.getSubMsg());
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
