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

import com.manji.mlife.Vo.FineVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.FineService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/app/fine")
public class AppFineController {
	
	@Autowired
	private FineService service;
	
	/**
	 * 根据城市查询交通罚款商品信息
	 * @param province
	 * @param City
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getItem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody 
	public String getItem(String province, String City) throws ApiException{
		
		String fineItemJson ="";
		
		Map<String, String> map =service.queryItem(province,City);
		
		fineItemJson =JSONObject.fromObject(map).toString();
		
		return fineItemJson;
	}
	
	
	
	/**
	 * 生成交通罚款订单发送满集网
	 * @param vo
	 * @param attr
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping("/createBill")
	public String createBill(FineVo vo, HttpServletRequest request, RedirectAttributes attr) throws ApiException{
		
		//校验登录名和JSON
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
		
		if(billMap.get("errCode").equals("1")){
			
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
			attr.addAttribute("order_title", billMap.get("itemName").replace(" ", ""));
			attr.addAttribute("order_money", billMap.get("saleAmount"));
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL");//支付页面
		
		}
	}
	

}
