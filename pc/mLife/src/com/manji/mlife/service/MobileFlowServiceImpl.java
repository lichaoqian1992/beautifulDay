package com.manji.mlife.service;

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
import com.qianmi.open.api.domain.elife.Item;
import com.qianmi.open.api.domain.elife.OrderDetailInfo;
import com.qianmi.open.api.domain.elife.PhoneInfo;
import com.qianmi.open.api.request.MobileFlowCreateBillRequest;
import com.qianmi.open.api.request.MobileFlowItemsList2Request;
import com.qianmi.open.api.request.RechargeMobileGetPhoneInfoRequest;
import com.qianmi.open.api.response.MobileFlowCreateBillResponse;
import com.qianmi.open.api.response.MobileFlowItemsList2Response;
import com.qianmi.open.api.response.RechargeMobileGetPhoneInfoResponse;

import net.sf.json.JSONObject;

/**
 * 
 * @author gaochao 2016年8月18日上午11:55:31 MobileFlowServiceImpl
 *
 */
@Service
public class MobileFlowServiceImpl implements MobileFlowService {

	// 平台公共service
	@Autowired
	private DBService service;

	private static final Logger logger = Logger.getLogger(MobileFlowServiceImpl.class);

	// 查询号码归属地
	@Override
	public String findPhoneaddr(String mobileNo) throws ApiException {

		// 获取公共数据
		Map<String, String> map = service.getBasePara();

		String Info = null;
		// 开启链接
		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		// 相应的接口请求
		RechargeMobileGetPhoneInfoRequest req = new RechargeMobileGetPhoneInfoRequest();
		// 设置参数
		req.setPhoneNo(mobileNo);
		req.setRespType("area");
		// 授权信息
		RechargeMobileGetPhoneInfoResponse response = client.execute(req, map.get("accessToken"));

		logger.info("RechargeMobileGetPhoneInfoRequest" + JSONObject.fromObject(response).toString());

		if (!response.isSuccess()) {
			Info = "未知归属地";
		} else {
			PhoneInfo phoneInfo = response.getPhoneInfo();
			// 省份
			String province = phoneInfo.getProvince();
			// 运营商
			String operator = phoneInfo.getOperator();
			Info = province + "   " + operator;
		}
		return Info;
	}

	// 获取零售价
	@Override
	public String getflowItems(String mobileNo, String flow) throws ApiException {

		Map<String, String> map = service.getBasePara();

		OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
		MobileFlowItemsList2Request req = new MobileFlowItemsList2Request();
		req.setFlow(flow);// 流量
		req.setMobileNo(mobileNo);// 充值号码

		String string = null;
		MobileFlowItemsList2Response response = client.execute(req, map.get("accessToken"));

		logger.info("MobileFlowItemsList2Response" + JSONObject.fromObject(response).toString());
		List<Item> items = response.getItems();
		if (items == null) {
			string = "0";
		} else {
			// 返回json 数据
			Item item = items.get(0);
			JSONObject fromObject = JSONObject.fromObject(item);
			string = fromObject.toString();
		}
		return string;
	}

	// 创建流量充值订单
	@Override
	public Map<String, String> getFlowCreateBill(String mobileNo, String itemId, String userName) throws ApiException {

		// 返回信息MAP
		Map<String, String> map = service.getBasePara();
		try {
				// 创建流量充值订单
				OpenClient client = new DefaultOpenClient(map.get("url"), map.get("appKey"), map.get("appSecret"));
				MobileFlowCreateBillRequest req = new MobileFlowCreateBillRequest();
				req.setItemId(itemId);
				req.setMobileNo(mobileNo);
				String outerId = OuterId.getOuterId();
				req.setOuterTid(outerId);// 外部订单编号
				MobileFlowCreateBillResponse responseInfo = client.execute(req, map.get("accessToken"));
	
				logger.info("-----生成流量充值订单:" + JSONObject.fromObject(responseInfo).toString());
	
				// 返回订单详情
				OrderDetailInfo info = responseInfo.getOrderDetailInfo();
				System.out.println(JSONObject.fromObject(info).toString());
				if (info != null) {
					
					map = service.insertData(info, "MobileFlow", userName, outerId);
					map.put("errCode", "0");
				} else {
					map.put("redirectURL","/errInfo");
					map.put("errCode", "1");
					map.put("errMsg", responseInfo.getSubMsg());
				}

			} catch (ApiException a) {
				a.printStackTrace();
				logger.info(a.getErrCode() + a.getErrMsg());
				map.put("errCode", "1");
				map.put("errMsg", a.getErrMsg());
			}
		return map;
	}

}
