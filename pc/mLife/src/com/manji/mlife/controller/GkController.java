package com.manji.mlife.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.common.MD5;
import com.manji.mlife.service.GkService;
import com.qianmi.open.api.ApiException;

/**
 * 固话宽带充值
 * @author gaochao
 *
 */
@Controller
@RequestMapping("/gk")
public class GkController{
	
	@Autowired
	private GkService gkService;
	
	//获取省市的固话宽带商品列表
	@RequestMapping(value="/GhkdItemListRequest" ,method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String  GhkdItemListRequest(String provincevid){
		
		//获取省市的固话宽带商品列表
		String string=gkService.getGhkdItemListRequest(provincevid);
		System.out.println(string);
		return string;
	}
	
	
	 /**                  
	  * 异步获取固话宽带商品面值
	  * @author gaochao
	  * @param itemId
	  * @return
	  * @throws ApiException
	  * 2016年8月22日下午4:20:15
	  * String
	  *
	  */
	@RequestMapping(value="/getItemInfo" ,method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getItemInfo(String itemId) throws ApiException{
		
		//异步获取固话宽带商品面值
		String string=gkService.getItemInfo(itemId);
		
		
		return string;
	}
	
	/**
	 * qianmi.elife.directRecharge.ghkd.createBill（创建固话宽带充值订单） 
	 * @author gaochao
	 * @param itemId
	 * @param account
	 * @param request
	 * @return
	 * @throws ApiException
	 * 2016年7月8日下午5:28:40
	 * String
	 *                      
	 */                     
	@RequestMapping(value="/ghkdCreateBill" ,method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String ghkdCreateBill(String itemId ,String account, HttpServletRequest request,RedirectAttributes attr) throws ApiException{

		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/"; 
		}
		
		//创建固话宽带充值订单
		Map<String,String> billMap=gkService.getGhkdCreateBill(itemId,account, userName);
		
				//重定向参数
				long timestamp = System.currentTimeMillis();
		
				attr.addAttribute("ismobile", "0");
				attr.addAttribute("notify_url", billMap.get("backAsynURL"));
				attr.addAttribute("order_cate", "OuterOrderPay");
				attr.addAttribute("order_money", billMap.get("saleAmount"));
				attr.addAttribute("order_no", billMap.get("outerId"));
				attr.addAttribute("order_title", billMap.get("itemName").replace(" ", ""));//将空格替换成空，不然url解析的时候空格会变成加号，导致解析的时候出错
				attr.addAttribute("partner_channel", "mjLife");
				attr.addAttribute("return_url", billMap.get("backSynURL"));
				attr.addAttribute("session", sessionId);
				attr.addAttribute("timestamp", timestamp);
				
				attr.addAttribute("sign", MD5.generateSign(attr));
				
				return "redirect:"+billMap.get("redirectURL");//支付页面
		
	}
	
	
}
