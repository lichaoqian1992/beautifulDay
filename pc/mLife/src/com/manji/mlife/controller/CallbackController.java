package com.manji.mlife.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.BackVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.model.Trade;
import com.manji.mlife.service.CallbackService;
import com.manji.mlife.service.DBService;
import com.manji.mlife.utils.LoadJson;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;

/**
 * 回调Controller
 * @author gaochao
 * 2016年8月19日下午12:18:25 
 * CallbackController
 */
@Controller
public class CallbackController {
	private static final Logger logger =Logger.getLogger(CallbackController.class);
	
	@Autowired
	private DBService service;
	@Autowired
	private  CallbackService callbackService;

	// 满集网回调接口
	// http:192.168.0.88:8080/mLife/synchronizationCallback 同步回调接口
	// http:192.168.0.88:8080/mLife/asynchronousCallback 异步回调接口
	/**
	 * 同步回调接口
	 * @param session
	 * @param order_no
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/synchronizationCallback")
	public String backSynURL(String session, String order_no, HttpServletRequest request , RedirectAttributes attr) throws ApiException {
		logger.debug("同步回调接口开始........");
		logger.debug("满集用户:" + session);
		logger.debug("满集返回:" + order_no);
		
		String userurl =callbackService.getUserUrl();
		Map<String, String> userInfo =getUserName(session,userurl);

		HttpSession httpSession =request.getSession();
		httpSession.setAttribute("user_name", userInfo.get("user_name"));
		httpSession.setAttribute("sessionId", session);

		String isMobile =request.getParameter("isMobole");
		Trade tradeInfo = service.queryTradeByNo(order_no);
		if (tradeInfo == null){
			return null;
		}
		
		// 获取千米支付订单编号
		String billId = tradeInfo.getTradeno();
		logger.info("满集返回订单" + JSONObject.fromObject(tradeInfo));
		
		if (tradeInfo.getPstate().equals("0")) {
			// 未支付状态状态
			String payUrl =callbackService.getPayUrl();
			String signKey =callbackService.getSignKey();
			Map<String, String> infoMap = new HashMap<String, String>();
			infoMap.put("action", "PartnerOrderCheck");
			infoMap.put("partner_channel", "mjLife");
			infoMap.put("order_no", order_no);
			infoMap.put("sign", MD5.sign("PartnerOrderCheck", "mjLife", order_no,signKey));
			String pay_status ="0";
			String out_order_no = "";
			String pay_date = "";
			String pay_money="";
			try {
				String loadJSON = LoadJson.loadJSON(payUrl, infoMap);	
				JSONObject obj = JSONObject.fromObject(loadJSON);
				logger.debug("---------------- 同步+返回满集的数据"+obj);
				
				if ("0".equals(obj.getString("ErrCode"))) {
					JSONObject orderObj = obj.getJSONObject("Data");
					// err_code = orderObj.getString("err_code");
					pay_money = orderObj.getString("pay_money");
					pay_status = orderObj.getString("pay_status");
					out_order_no = orderObj.getString("out_order_no");
					pay_date = orderObj.getString("pay_date");
				}
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e);
			}

			Double manji_money =Double.parseDouble(pay_money);
			Double qianmi_money=Double.parseDouble(tradeInfo.getPrice());
			
			if(manji_money!=qianmi_money){
				String errMsg = "支付金额错误";
				attr.addAttribute("errMsg",errMsg);
				return "redirect:/errInfo";
			}
			
			
			if (!"0".equals(pay_status)) {
				tradeInfo.setMjoder(out_order_no);// 满集订单号
				tradeInfo.setPtime(pay_date);// 交易日期
				tradeInfo.setPstate("1");
				tradeInfo.setState("1");
				service.updateTradeSelective(tradeInfo);
				logger.debug("-------更改流水Trade,向满集网支付成功");
			}else{
				logger.debug("-------更改流水Trade,向满集网支付失败");
				return "fail";
			}

			if ("0".equals(tradeInfo.getFlag())) {
				// 充值类
				logger.debug("-------调用千米付款接口--------");
				if(!callbackService.payChargeBill(billId)){
					tradeInfo.setPstate("2");
					tradeInfo.setState("2");
					service.updateTradeSelective(tradeInfo);
				}
			} else {
				// 票务类
				callbackService.payTrafficOrder(billId, tradeInfo.getType());
			}
		}

		if ("0".equals(tradeInfo.getFlag())) {
//			ChargeOrder chargeInfo = service.queryChargeBills(billId);
			if("0".equals(isMobile)){
				return "order";
			}else{
				return "appOrderBill";
			}
		} else {
//			List<TrafficOrder> trafficInfo = service.queryTrafficBills(billId);
			if("0".equals(isMobile)){
				return "order_traffic";
			}else{
				return "appOrderTicke";
			}
		}
	}

	/**
	 * 异步回调接口
	 * @param vo
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/asynchronousCallback")
	public String backAsynURL(BackVo vo, HttpServletRequest request , RedirectAttributes attr) throws ApiException {
		logger.debug("----------进了异步回调接口........");
		String signKey =callbackService.getSignKey();

		Map<String, String> map = new HashMap<String,String>();
		 
		// 获取数据方式2 
        Enumeration namesEnum = request.getParameterNames(); 
        while(namesEnum.hasMoreElements()){  
            String name = (String) namesEnum.nextElement();  
            String value = request.getParameter(name);  
            logger.debug("-----------请求参数"+name+" = "+value);  
            if(!name.endsWith("sign")){
                map.put(name, value);
            }
        }  
		
		boolean signflag = MD5.verify(map,signKey,vo);
		logger.debug("---------------+异步的签名状态"+signflag);
		
		boolean codeFlag = vo.getErr_code().equals("0");
		logger.debug("---------------+异步的支付状态"+codeFlag);
		if (signflag && codeFlag) {
			// 获取外部订单编号
			Trade tradeInfo = service.queryTradeByNo(vo.getOrder_no());
			
			Double manji_money =Double.parseDouble(vo.getPay_money());
			Double qianmi_money=Double.parseDouble(tradeInfo.getPrice());
			
			if(manji_money!=qianmi_money){
				String errMsg = "支付金额错误";
				attr.addAttribute("errMsg", errMsg);
				return "redirect:/errInfo";
			}
			
			// 获取千米支付订单编号
			String billId = tradeInfo.getTradeno();
			logger.debug("--------外部订单编号..........");
			if (!tradeInfo.getPstate().equals("0")) {
				logger.debug("--------支付成功（异步接口）--------");
				request.setAttribute("flag", "success");
			} else {
				logger.debug("--------异步更改流水支付状态--------");
				tradeInfo.setMjoder(vo.getOut_order_no());// 满集订单号
				tradeInfo.setPtime(vo.getPay_date());// 交易日期
				tradeInfo.setPstate("1");
				tradeInfo.setState("1");
				service.updateTradeSelective(tradeInfo);

				if ("0".equals(tradeInfo.getFlag())) {
					// 充值类
					logger.debug("-------异步调用千米付款接口--------");
					callbackService.payChargeBill(billId);
				} else {
					// 票务类
					callbackService.payTrafficOrder(billId, tradeInfo.getType());
				}

				request.setAttribute("flag", "success");
			}
		} else {
			request.setAttribute("flag", "fault");
		}
		return "asynBack";
	}
	
	public static Map<String, String> getUserName(String sessionId,String url) {
		Map<String, String> map = new HashMap<String, String>();
		// 获取用户信息
		Map<String, String> mapInfo = new HashMap<String, String>();
		mapInfo.put("action", "GetUserInfoBySessionId");
		mapInfo.put("sessionid", sessionId);
		try {
			String loadJSON = LoadJson.loadJSON(url, mapInfo);

			JSONObject obj = JSONObject.fromObject(loadJSON);
			// 判读获取的信息是否可用
			if (obj.getString("status").equals("1")) {
				JSONObject jsonObject = obj.getJSONObject("data");
				// 判断账户是否正常
				// "status": 0,// 账户状态,0正常,1待验证,2待审核,3锁定,4黑名单
				// "is_del": 0,//是否删除 0正常 1删除
				if (jsonObject.getString("status").equals("0") && jsonObject.getString("is_del").equals("0")) {
					// 保存用户信息
					map.put("user_name", jsonObject.getString("user_name"));
				}
			} else {
				

			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return map;
	}
	
}
