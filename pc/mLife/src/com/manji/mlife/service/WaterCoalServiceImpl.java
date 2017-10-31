package com.manji.mlife.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.mlife.Vo.WaterCoalVo;
import com.manji.mlife.common.OuterId;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.elife.AdminItem;
import com.qianmi.open.api.domain.elife.ItemProp;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.request.DirectRechargeLifeRechargeCreateBillRequest;
import com.qianmi.open.api.request.DirectRechargeWaterCoalGetAccountInfoRequest;
import com.qianmi.open.api.request.DirectRechargeWaterCoalItemListRequest;
import com.qianmi.open.api.request.DirectRechargeWaterCoalItemPropsListRequest;
import com.qianmi.open.api.response.DirectRechargeLifeRechargeCreateBillResponse;
import com.qianmi.open.api.response.DirectRechargeWaterCoalGetAccountInfoResponse;
import com.qianmi.open.api.response.DirectRechargeWaterCoalItemListResponse;
import com.qianmi.open.api.response.DirectRechargeWaterCoalItemPropsListResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class WaterCoalServiceImpl implements WaterCoalService {
	@Autowired	
	private DBService service;
	
	private static final Logger logger = Logger.getLogger(WaterCoalServiceImpl.class);

	// // 测试使用
	// private static final String url = "http://gw.api.qianmi.com/api";
	// private static final String appKey = "10001127";
	// private static final String appSecret =
	// "1BKWKw7HGDaHz1n5BXyIJRf9Ws059NkX";
	// private static final String accessToken =
	// "d03dcdd828be44f68878de940732d785";

	public String queryProps(String province, String city, String mode) throws ApiException {
		DirectRechargeWaterCoalItemListRequest req = new DirectRechargeWaterCoalItemListRequest();
		req.setProvince(province);
		req.setCity(city);
		req.setProjectId(mode);
		
		// 从数据库获取请求参数
		Map<String, String> reqParamMap = service.getBasePara();
		OpenClient client = new DefaultOpenClient(reqParamMap.get("url"), reqParamMap.get("appKey"), reqParamMap.get("appSecret"));
		DirectRechargeWaterCoalItemListResponse waterCoalItemListResponse = client.execute(req, reqParamMap.get("accessToken"));
		logger.info(JSONObject.fromObject(waterCoalItemListResponse).toString());
		List<AdminItem> list = waterCoalItemListResponse.getItems();

		String propJson = "";
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				String propName = list.get(i).getItemName();
				propName = propName.replace("  ", " ");
				String[] strArr = new String[10];
				strArr = propName.split(" ");
				list.get(i).setItemName(strArr[1]);
			}
			propJson = JSONArray.fromObject(list).toString();
		}
		return propJson;
	}

	@Override
	public Map<String, String> queryItem(String itemId) throws ApiException {
		Map<String, String> map = new HashMap<String, String>();

		return map;
	}

	@Override
	public String queryBill(String account, String itemId) throws ApiException {
		String waterCoalBillJson = "";
		try{
			// 1.从数据库获取请求参数
			Map<String, String> reqParamMap = service.getBasePara();
			OpenClient client = new DefaultOpenClient(reqParamMap.get("url"), reqParamMap.get("appKey"), reqParamMap.get("appSecret"));
			DirectRechargeWaterCoalItemPropsListRequest req = new DirectRechargeWaterCoalItemPropsListRequest();
			req.setItemId(itemId);
			DirectRechargeWaterCoalItemPropsListResponse waterCoalItemPropsListResponse = client.execute(req, reqParamMap.get("accessToken"));
	
			logger.info("DirectRechargeWaterCoalItemPropsListRequest"
					+ JSONObject.fromObject(waterCoalItemPropsListResponse).toString());
			List<ItemProp> itemProps = waterCoalItemPropsListResponse.getItemProps();
			
			// 2.获得缴费公司信息
			if (null != itemProps) {
				Map<String, String> propMap = new HashMap<String, String>();
				propMap.put("projectId", waterCoalItemPropsListResponse.getCid());
				for (int i = 0; i < itemProps.size(); i++) {
					ItemProp ip = itemProps.get(i);
					String type = ip.getType();
					if ("BRAND".equals(type)) {
						propMap.put("unitId", ip.getVid());
						propMap.put("unitName", ip.getVname());
					}else if ("PRVCIN".equals(type)) {
						propMap.put("province", ip.getVname());
					}else if ("CITY".equals(type)) {
						propMap.put("city", ip.getVname());
					}else if ("CITYIN".equals(type)) {
						propMap.put("cityId", ip.getVid());
					}else if ("SPECIAL".equals(type)) {
						propMap.put("modeId", ip.getVid());
					}
				}
				
				// 3. 查询账单信息
				DirectRechargeWaterCoalGetAccountInfoRequest reque = new DirectRechargeWaterCoalGetAccountInfoRequest();
				reque.setItemId(itemId);
				reque.setAccount(account);
				reque.setProjectId(propMap.get("projectId"));
				reque.setUnitId(propMap.get("unitId"));
				reque.setProvince(propMap.get("province"));
				reque.setCity(propMap.get("city"));
				reque.setUnitName(propMap.get("unitName"));
				reque.setModeId(propMap.get("modeId"));
				reque.setCityId(propMap.get("cityId"));
				reque.setModeType("2");
				DirectRechargeWaterCoalGetAccountInfoResponse waterCoalGetAccountInfoResponse = client.execute(reque,
						reqParamMap.get("accessToken"));
				logger.info("-----查询水电气费缴费账单信息:"
						+ JSONObject.fromObject(waterCoalGetAccountInfoResponse).toString());
				if ("1".equals(waterCoalGetAccountInfoResponse.getStatus())) {
					waterCoalBillJson = JSONArray.fromObject(waterCoalGetAccountInfoResponse.getWaterCoalBills())
							.toString();
				}else{
					String str =waterCoalGetAccountInfoResponse.getMessage();
					Map<String, String> errMap =new HashMap<String,String>();
					errMap.put("errMsg", str);
					errMap.put("errCode", "1");
					waterCoalBillJson =JSONObject.fromObject(errMap).toString();
				}
			}
		} catch (ApiException ex){
			Map<String, String> errMap =new HashMap<String,String>();
			errMap.put("errMsg", ex.getErrMsg());
			errMap.put("errCode", "1");
			waterCoalBillJson =JSONObject.fromObject(errMap).toString();	
			logger.error(ex.getErrCode() + ex.getErrMsg(), ex);
		}

		return waterCoalBillJson;
	}

	@Override
	public Map<String, String> createBill(WaterCoalVo vo, String userName) throws ApiException {
		Map<String, String> map = new HashMap<String, String>();
		try{
			String outerId = OuterId.getOuterId();
			DirectRechargeLifeRechargeCreateBillRequest req = new DirectRechargeLifeRechargeCreateBillRequest();
			req.setItemId(vo.getWaterCoalItemId());
			req.setItemNum(vo.getWaterCoalAmount());
			req.setRechargeAccount(vo.getWaterCoalAccount());
			req.setContractNo(vo.getContractNo());
			req.setCustomerName(vo.getCustomerName());
			req.setCustomerAddress(vo.getCustomerAddress());
			req.setBillCycle(vo.getBillCycle());
			
			map = service.getBasePara();
			OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
			DirectRechargeLifeRechargeCreateBillResponse lifeRechargeCreateBillResponse = client.execute(req, map.get("accessToken"));
	
			logger.info("DirectRechargeLifeRechargeCreateBillRequest"
					+ JSONObject.fromObject(lifeRechargeCreateBillResponse).toString());
	
			// 3.保存账单信息
			OrderDetailInfo info = lifeRechargeCreateBillResponse.getOrderDetailInfo();
			if (null != info) {
				info.setSaleAmount(vo.getWaterCoalAmount());
				map =service.insertData(info, "WaterCoal",userName,outerId);
				map.put("errCode", "0");
			}else{
				map.put("redirectURL","/errInfo");
				map.put("errCode", "1");
				map.put("errMsg", lifeRechargeCreateBillResponse.getSubMsg());
			}
		} catch (ApiException ex) {
			map.put("errCode", "1");
			map.put("errMsg", ex.getErrMsg());
			logger.error(ex.getErrCode() + ex.getErrMsg(), ex);
		} catch (Exception ex) {
			map.put("errCode", "1");
			map.put("errMsg", ex.getMessage());
			logger.error(ex.getMessage(), ex);
		}
		return map;
	}
}
