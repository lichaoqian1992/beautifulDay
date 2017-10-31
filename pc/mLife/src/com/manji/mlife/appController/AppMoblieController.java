package com.manji.mlife.appController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.MobileVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.MobileService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/mobile")
public class AppMoblieController {
	@Autowired
	private MobileService service;
	/**
	 * 查询号码的归属地信息
	 * @return
	 */
	@RequestMapping(value = "/getArea", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getArea(String mobileNo) throws ApiException{
		
		String mobileAreaJson ="";//返回的归属地信息 省份+移动商
		
		Map<String, String> areaMap =service.getArea(mobileNo);
		mobileAreaJson =JSONObject.fromObject(areaMap).toString();

		return mobileAreaJson;
	}
	/**
	 * 获得话费直充商品价格
	 * @param mobileNo
	 * @param amount
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value="/getPrice", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getPrice(String mobileNo, String amount)throws ApiException{
		
		String mobileItemJson ="";
		Map<String,String> itemMap =service.getDirectItem(mobileNo, amount);
		itemMap.put("errCode", "0");
		
		mobileItemJson =JSONObject.fromObject(itemMap).toString();
		

		return mobileItemJson;
	}
	/**
	 * 生成话费订单
	 * @param mobileNo
	 * @return
	 * @throws ApiException 
	 */
	@RequestMapping(value="/createBill", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String createBill(MobileVo vo ,HttpServletRequest request, RedirectAttributes attr) throws ApiException{

		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");

		if(null ==userName||"".equals(userName)){
			
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
			
		}
		
		//生成订单信息
		Map<String,String> billMap =service.createBill(vo,userName);
		
		if("1".equals(billMap.get("errCode"))){
			
			String errMsg =billMap.get("errMsg");
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}else{
				
			//重定向参数
			long timestamp = System.currentTimeMillis();
			
			attr.addAttribute("order_cate", "OuterOrderPay");	
			attr.addAttribute("partner_channel", "mjLife");
			attr.addAttribute("ismobile", "1");
			attr.addAttribute("session", sessionId);
			attr.addAttribute("order_no", billMap.get("outerId"));
			attr.addAttribute("order_title", billMap.get("itemName"));
			attr.addAttribute("order_money", vo.getMobilePrice());
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL");//支付页面
		}
	}
}
