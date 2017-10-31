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
import com.manji.mlife.service.CatvService;
import com.qianmi.open.api.ApiException;


/**
 * 
 * @author gaochao
 * 2016年7月6日上午9:52:23
 * CatvController
 * 有线电视
 */
@Controller
@RequestMapping("/catv")
public class CatvController{
	@Autowired
	public CatvService catvService;

	//查询有限电视缴费的事业单位
	@RequestMapping( value="/unitsList",method=RequestMethod.GET  , produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String unitsList(String provinceVid,HttpServletRequest request) throws ApiException{
		//查询有限电视缴费的事业单位
	    String string=catvService.getUnitsList(provinceVid);
	  
		return string;
		
	}
	
	//qianmi.elife.catv.createBill（生成有线电视直充订单） 
	@RequestMapping(value="/catvCreateBill")
	public String catvCreateBill(String rechargeAmount,String itemId,String rechargeAccount ,HttpServletRequest request,RedirectAttributes attr) throws ApiException{
		//判断登录状态
		//获取sessionId 

		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		//生成有线电视直充订单
		Map<String, String>	billMap=catvService.getCatvCreateBill(rechargeAmount,itemId,rechargeAccount,userName);
		
		if(billMap.size() > 0 && billMap.get("errCode").equals("1")){
			attr.addAttribute("errMsg",billMap.get("errMsg"));
			return "redirect:"+billMap.get("redirectURL");//错误页面
		}else{
			//重定向参数
			long timestamp = System.currentTimeMillis();
			
			attr.addAttribute("order_cate", "OuterOrderPay");	
			attr.addAttribute("partner_channel", "mjLife");
			attr.addAttribute("ismobile", "0");
			attr.addAttribute("session", sessionId);
			attr.addAttribute("order_no", billMap.get("outerId"));
			attr.addAttribute("order_title", billMap.get("itemName"));
			attr.addAttribute("order_money", billMap.get("saleAmount"));
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL");//支付页面
		}
	}
	
}
