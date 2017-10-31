package com.manji.mlife.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.manji.mlife.common.OuterId;
import com.manji.mlife.common.TransformUnit;
import com.manji.mlife.mapper.ChargeOrderMapper;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.mapper.TradeMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.Trade;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.GhkdOrderInfo;
import com.qianmi.open.api.domain.elife.ItemInfo;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.DirectRechargeGhkdCreateBillRequest;
import com.qianmi.open.api.request.DirectRechargeGhkdItemListRequest;
import com.qianmi.open.api.request.RechargeBaseGetItemInfoRequest;
import com.qianmi.open.api.request.RechargeOrderInfoRequest;
import com.qianmi.open.api.response.DirectRechargeGhkdCreateBillResponse;
import com.qianmi.open.api.response.DirectRechargeGhkdItemListResponse;
import com.qianmi.open.api.response.RechargeBaseGetItemInfoResponse;
import com.qianmi.open.api.response.RechargeOrderInfoResponse;

import net.sf.json.JSONObject;

/**
 * 固话宽带
 * @author gaochao
 * 2016年7月8日下午5:31:39
 * GkServiceImpl
 *
 */
@Service
public class GkServiceImpl implements GkService {
	
	 //平台公共service
	 @Autowired	
	 private DBService service;
	 

	 private static final Logger logger =Logger.getLogger(GkServiceImpl.class);
	
	
	/**
	 * 获取省市的固话宽带商品列表
	 * @author gaochao
	 * @param provincevid
	 * @param cityvid
	 * @return
	 * 2016年8月18日下午3:02:21
	 *
	 */
	@Override
	public String getGhkdItemListRequest(String provincevid) {
	   
		Map<String, String> map = service.getBasePara();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		DirectRechargeGhkdItemListRequest req = new DirectRechargeGhkdItemListRequest();
		req.setProvinceVid(provincevid);//省
		req.setPageSize(50);//返回数量
		
		List<Object>  all=new ArrayList<Object>(); 
		//电信集合
		List dianxin =new ArrayList<>();//电信 
		List dgh =new ArrayList<>();//固话
		List dkd =new ArrayList<>();//宽带
		
		List liantong =new ArrayList<>();//联通
		List lgh =new ArrayList<>();//固话
		List lkd =new ArrayList<>();//宽带
		String string="0";
		try {
			//重庆  电信 固话宽带新 账号  固话 直充任意充
			//重庆  电信 固话宽带新 账号  宽带 直充100元
			DirectRechargeGhkdItemListResponse response = client.execute(req, map.get("accessToken"));
			
			logger.info("DirectRechargeGhkdItemListResponse"+JSONObject.fromObject(response).toString());

			List<AdminItem> items = response.getItems();
			if(items!=null){
			for (AdminItem adminItem : items) {
				String itemName = adminItem.getItemName();
				String[] split = itemName.split(" ");//按空格分组
				//[重庆, , 电信, 固话宽带新, 账号, , 固话, 直充100元]
				if(split[2].equals("电信")){
					
					if(split[6].equals("固话")){
						dgh.add(adminItem);

					}else {
						//宽带
						dkd.add(adminItem);
					}
				}else{
					//联通
					if(split[6].equals("固话")){
						lgh.add(adminItem);
					}else {
						//宽带
						lkd.add(adminItem);
					}
					
				}
			}
			
			dianxin.add(dkd);
			dianxin.add(dgh);
			liantong.add(lkd);
			liantong.add(lgh);
			all.add(dianxin);
			all.add(liantong);
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(all);//list转化为json
			string = jsonArray.toString();

		  }
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return string;
	}
	
	
	
	
	
	//异步获取固话宽带商品面值
	@Override
	public String getItemInfo(String itemId) throws ApiException {
		
	    Map<String, String> map = service.getBasePara();

	    OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
	    
	    RechargeBaseGetItemInfoRequest req = new RechargeBaseGetItemInfoRequest();
	    req.setItemId(itemId);
	    
	    RechargeBaseGetItemInfoResponse response = client.execute(req, map.get("accessToken"));
	    
		logger.info("RechargeBaseGetItemInfoResponse"+JSONObject.fromObject(response).toString());

	    ItemInfo itemInfo = response.getItemInfo();
	    
	    String string="0";
	    if(itemInfo!=null){
	    	String stock = itemInfo.getStock();
	    	if(0<Integer.parseInt(stock)){
	    		//商品面值
	    		 string = JSONObject.fromObject(itemInfo).toString();
	    	}
	    }
	    return string;
	}

	/**
	 * 
	 * @author gaochao
	 * @param itemId
	 * @param account
	 * @return
	 * 2016年8月18日下午3:05:31
	 * @throws ApiException 
	 * 创建固话宽带充值订单
	 */
	@Override
	public  Map<String, String> getGhkdCreateBill(String itemId, String account,String userName) throws ApiException {
	
		Map<String, String> map = service.getBasePara();
		String outerId = OuterId.getOuterId();
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		try{
		DirectRechargeGhkdCreateBillRequest req = new DirectRechargeGhkdCreateBillRequest();
		req.setItemId(itemId);
		req.setAccount(account);
		req.setOuterTid(outerId);//外部订单编号
		DirectRechargeGhkdCreateBillResponse response = client.execute(req, map.get("accessToken"));
		
		logger.info("-----创建固话订单详情:"+JSONObject.fromObject(response).toString());

		GhkdOrderInfo ghkdOrderInfo = response.getGhkdOrderInfo();
		
		if(ghkdOrderInfo!=null){
			
			//查询订单详情
			String tid = ghkdOrderInfo.getTid();
			RechargeOrderInfoRequest req1 = new RechargeOrderInfoRequest();
			req1.setBillId(tid);
			RechargeOrderInfoResponse response1 = client.execute(req1, map.get("accessToken"));
			
			OrderDetailInfo info = response1.getOrderDetailInfo();
			
			logger.info("-----创建固话订单详情:"+JSONObject.fromObject(response1).toString());
			
			if(info!=null){
				
				map =service.insertData(info, "GK",userName,outerId);
				map.put("errCode", "0");
			}else{
				map.put("redirectURL","/errInfo");
				map.put("errCode", "1");
				map.put("errMsg", response1.getSubMsg());
			}
			
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
