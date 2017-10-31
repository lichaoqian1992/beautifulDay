package com.manji.mlife.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.GasCardVo;
import com.manji.mlife.common.OuterId;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.GasCardInfo;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.GasCardCreateBillRequest;
import com.qianmi.open.api.request.GasCardGetAccountInfoRequest;
import com.qianmi.open.api.request.GasCardItemListRequest;
import com.qianmi.open.api.response.GasCardCreateBillResponse;
import com.qianmi.open.api.response.GasCardGetAccountInfoResponse;
import com.qianmi.open.api.response.GasCardItemListResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GasCardServiceImpl implements GasCardService {

	

	
//	// 测试使用
//	private static final String url = "http://gw.api.qianmi.com/api";
//	private static final String appKey = "10001127";
//	private static final String appSecret = "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
//	private static final String accessToken = "d03dcdd828be44f68878de940732d785";
	
	private static final Logger logger =Logger.getLogger(GasCardServiceImpl.class);
	
	@Autowired	
	 private DBService service;
	
	@Override
	public String queryCom(String operator) throws ApiException {
		//判断供应商中文名称
		if("cnpc".equals(operator)){
			operator="中石油";
		}else{
			operator="中石化";
		}
		System.out.println(operator);
		String gasJson ="";
		//平台数据
		Map<String, String> map = service.getBasePara();
		
		
		//查询商品列表
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		GasCardItemListRequest req = new GasCardItemListRequest();
		GasCardItemListResponse response = client.execute(req, map.get("accessToken"));
		
		logger.info("GasCardItemListResponse"+JSONObject.fromObject(response).toString());
		
		List<AdminItem> list =response.getItems();
		if(null !=list){
			//根据供应商名称筛选商品列表
			for(int i=0;i<list.size();i++){
			if((list.get(i).getItemName().indexOf(operator))>=0){
			}else{
				list.remove(i);
				i--;
			}
			}
			gasJson =JSONArray.fromObject(list).toString();
		}
		return gasJson;
	}

	@Override
	public String queryAccount(String province, String operator, String gasCardNo) throws ApiException {
		
		String cardState ="";
		//平台基础信息
		Map<String, String> map = service.getBasePara();
		
		
		//查询商品列表
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		GasCardGetAccountInfoRequest req = new GasCardGetAccountInfoRequest();
		req.setProvince(province);
		req.setOperator(operator);
		req.setGasCardNo(gasCardNo);
		GasCardGetAccountInfoResponse response = client.execute(req, map.get("accessToken"));
		
		
		logger.info("-----查询加油卡账号信息:"+JSONObject.fromObject(response));
		GasCardInfo info =response.getGasCardInfo();
		if(null !=info){
			cardState =JSONObject.fromObject(info).toString();
		}
		
		return cardState;
	}

	@Override
	public Map<String, String> creatBill(GasCardVo vo,String userName) throws ApiException {
		Map<String, String >billMap =new HashMap<String, String>();
		//平台基础信息
		Map<String, String> map = service.getBasePara();
		String outerId = OuterId.getOuterId();
		
		try{
			
		
			//查询商品列表
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
			GasCardCreateBillRequest req = new GasCardCreateBillRequest();
			req.setItemId(vo.getItemId());
			req.setGasCardTel(vo.getGasCardTel());
			req.setGasCardName(vo.getGasCardName());
			req.setRechargeAmount(vo.getRechargeAmount());
			req.setGasCardNo(vo.getGasCardNo());
			req.setProvince(vo.getProvince());
			req.setOuterTid(outerId);
			GasCardCreateBillResponse response = client.execute(req, map.get("accessToken"));
			
			logger.info("-----生成交友卡充值订单:"+JSONObject.fromObject(response));
			
			OrderDetailInfo info =response.getOrderDetailInfo();
			
			if(null !=info){
				info.setSaleAmount(vo.getRechargeAmount());
				billMap =service.insertData(info, "GasCard", userName,outerId);
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

	
	
}
