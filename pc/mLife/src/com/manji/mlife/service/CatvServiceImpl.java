package com.manji.mlife.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.common.OuterId;
import com.manji.mlife.common.TransformUnit;
import com.manji.mlife.controller.BaseController;
import com.manji.mlife.mapper.ChargeOrderMapper;
import com.manji.mlife.mapper.SimpleCodeMapper;
import com.manji.mlife.mapper.TradeMapper;
import com.manji.mlife.model.ChargeOrder;
import com.manji.mlife.model.Trade;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.CatvCreateBillRequest;
import com.qianmi.open.api.request.CatvItemsListRequest;
import com.qianmi.open.api.response.CatvCreateBillResponse;
import com.qianmi.open.api.response.CatvItemsListResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author gaochao
 * 2016年7月7日上午9:59:14
 * CatvServiceImpl
 * 有线电视
 */

@Service
public class CatvServiceImpl  implements CatvService{
	
	
	 //平台公共service
	 @Autowired	
	 private DBService service;
	 
	 private static final Logger logger =Logger.getLogger(GkServiceImpl.class);

	/**
	 * //查询有限电视缴费的事业单位
	 * @author gaochao
	 * @param provinceVid
	 * @param cityVid
	 * @return
	 * 2016年8月18日下午3:46:45
	 *
	 */
	@Override
	public String getUnitsList(String provinceVid) {
		
		Map<String, String> map = service.getBasePara();
		
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		
		CatvItemsListRequest req = new CatvItemsListRequest();
		
		req.setProvinceVid(provinceVid);
		req.setProjectId("c2682");//有线电视费-c2682
		CatvItemsListResponse response=null;
		JSONArray jsonArray=null;
		try {
			 response = client.execute(req, map.get("accessToken"));
			 
			logger.info("CatvItemsListResponse"+JSONObject.fromObject(response).toString());

			//返回的json数据
			List<AdminItem> items = response.getItems();
			jsonArray = new JSONArray();//new一个json数组  
			if(items!=null){
				    for (AdminItem item: items) {
					    JSONObject obj = new JSONObject();  
			            obj.put("vid",item.getItemId());  
			            obj.put("vname",item.getItemName());  
			            jsonArray.add(obj);
			            //循环new jsonObject
					}
			}
		    
		} catch (ApiException e) {
		
		}
	
		return jsonArray.toString();
	}
	
	/**
	 * 创建有线电视订单
	 * @author gaochao
	 * @param rechargeAmount
	 * @param itemId
	 * @param rechargeAccount
	 * @param userName
	 * @return
	 * @throws ApiException
	 * 2016年8月24日上午10:19:01
	 *
	 */
	@Override
	public Map<String, String> getCatvCreateBill(String rechargeAmount, String itemId, String rechargeAccount,String userName) throws ApiException {
		
		//返回信息MAP
		
		String outerId = OuterId.getOuterId();
		
		Map<String, String> map = service.getBasePara();
		
		Map<String, String> map1 = new HashMap<String,String>();

		try{
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		CatvCreateBillRequest req = new CatvCreateBillRequest();
		//创建有线电视订单
		req.setItemId(itemId);//标准商品编号
		req.setRechargeAmount(rechargeAmount);//充值金额，支持两位小数
		req.setRechargeAccount(rechargeAccount);//缴费账号或户号，卡号
		CatvCreateBillResponse response = client.execute(req, map.get("accessToken"));
		
		logger.info("-----有线电视充值订单:"+JSONObject.fromObject(response).toString());

		//拿到返回参数
		OrderDetailInfo info = response.getOrderDetailInfo();
		
		if(info!=null){
			info.setSaleAmount(rechargeAmount);
			map1 =service.insertData(info, "Catv",userName,outerId);
			map1.put("errCode", "0");
		}else{
			map1.put("redirectURL","/errInfo");
			map1.put("errCode", "1");
			map1.put("errMsg", response.getSubMsg());
		}
		
		}catch(ApiException a){
			a.printStackTrace();
			logger.info(a.getErrCode()+a.getErrMsg());
			map1.put("errCode", "1");
			map1.put("errMsg", a.getErrMsg());
		}
		return map1;
	}
	
	
}
