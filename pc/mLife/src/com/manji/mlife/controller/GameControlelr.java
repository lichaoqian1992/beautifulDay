package com.manji.mlife.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.CardVo;
import com.manji.mlife.Vo.GameVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.GameService;
import com.qianmi.open.api.ApiException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/game")
public class GameControlelr {

	@Autowired
	private GameService service;
	
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	@ResponseBody 
	public String gameList() throws ApiException{
		
		String str =service.getGame();
		
		return str;
	}
	
	@RequestMapping(value = "/getCardList", method = RequestMethod.GET)
	@ResponseBody 
	public String gameCardList() throws ApiException{
		
		String str =service.getCard();
		
		return str;
	}
	
	
	@RequestMapping("/gameBill")
	public String gameBill(String gameId,String gameName,HttpServletRequest req) throws ApiException{
		System.out.println(gameId);
		System.out.println(gameName);
		String itemListJson =service.getItemList(gameId);
		req.setAttribute("gameId", gameId);
		req.setAttribute("gameName", gameName);
		req.setAttribute("itemListJson", itemListJson);
		
		
		HttpSession session =req.getSession();
		String errMsg=(String) session.getAttribute("errMsg");
		
		if(null !=errMsg &&!"".equals(errMsg)){
		req.setAttribute("errMsg", errMsg);
		}
		
		return "gameBill";
	}
	
	
	
	
	
	@RequestMapping("/cardBill")
	public String cardBill(String itemId,String itemName,HttpServletRequest req) throws ApiException{
		System.out.println(itemId);
		System.out.println(itemName);
		
		HttpSession session=req.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		
		
		String itemJson =service.getItem(itemId);

		req.setAttribute("itemId", itemId);
		req.setAttribute("itemName", itemName);
		req.setAttribute("itemJson", itemJson);
		return "gameCard";
		
	}
	
	
	@RequestMapping(value = "/createGameBill", method = RequestMethod.POST)
	public String createGameBill(GameVo vo,HttpServletRequest request, RedirectAttributes attr) throws ApiException{
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("user_name");
		if(null ==userName||"".equals(userName)){
			return "redirect:/";
		}
		//生成订单信息
		Map<String,String> billMap =service.createGameBill(vo,userName);
		
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
	

	
	@RequestMapping(value = "/createCardBill", method = RequestMethod.POST)
	public String createCardBill(CardVo vo, HttpServletRequest request, RedirectAttributes attr) throws ApiException{
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("name");
		
		//生成订单信息
		Map<String,String> billMap =service.createCardBill(vo,userName);
		
		//重定向参数
		attr.addAttribute("order_cate", "OuterOrderPay");	
		attr.addAttribute("partner_channel", "mjLife");
		attr.addAttribute("ismobile", "0");
		attr.addAttribute("session", sessionId);
		attr.addAttribute("order_no", billMap.get("outerId"));
		attr.addAttribute("order_title", billMap.get("itemName"));
		attr.addAttribute("order_money", billMap.get("actPrice"));
		attr.addAttribute("return_url", billMap.get("backSynURL"));
		attr.addAttribute("notify_url", billMap.get("backAsynURL"));
		
		return "redirect:"+billMap.get("redirectURL");//支付页面
	}
	
	

	
	
	@RequestMapping(value = "/gameServer", method = RequestMethod.GET)
	@ResponseBody 
	public String gameServer(String classId,String itemId) throws ApiException{
		
		String str =service.getServer(classId,itemId);
		
		return str;
	}
	
	
	
	
	
	
	
	
}
