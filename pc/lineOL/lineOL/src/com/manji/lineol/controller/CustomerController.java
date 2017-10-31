package com.manji.lineol.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.lineol.common.result.BaseResult;
import com.manji.lineol.service.CustomerService;
import com.manji.lineol.vo.UserQueueInfoVo;
import com.manji.lineol.vo.UserShopQueueVo;

import net.sf.json.JSONObject;

@RequestMapping("/customer")
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	

	/**
	 * 用户查询
	 * 
	 * @param sessionId
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/customerQuery/{sessionId}/{shopId}")
	public String customerQuery(@PathVariable String sessionId, @PathVariable String shopId,
			HttpServletRequest request) {
		request.setAttribute("sessionId", sessionId);
		UserQueueInfoVo customerQueryMyInfo = customerService.customerQueryMyInfo(sessionId, shopId);
		if (customerQueryMyInfo == null) {
			UserShopQueueVo customerQuery = customerService.customerQuery(sessionId, shopId);
			request.setAttribute("shopQueueInfo", customerQuery);
			return "customer_book";
		} else {
			request.setAttribute("customerQueueInfo", customerQueryMyInfo);
			return "customer_booked";
		}
	}

	
	/**
	 * 用户预约
	 * @param shopId
	 * @param queueTypeAs
	 * @param userName
	 * @return
	 */
	@RequestMapping("/customerSubscribe/{shopId}/{queueTypeAs}/{userName}")
	@ResponseBody
	public BaseResult customerSubscribe(@PathVariable String shopId, @PathVariable String queueTypeAs,
			@PathVariable String userName) {
		return customerService.customerSubscribe(shopId, queueTypeAs, userName);

	}
	
	
	/**
	 * 取消用户预约
	 * @param shopId
	 * @param queueTypeAs
	 * @param userName
	 * @return
	 */
	@RequestMapping("/customerCancelSubscribe/{shopId}/{queueTypeAs}/{userName}")
	@ResponseBody
	public BaseResult customerCancelSubscribe(@PathVariable String shopId, @PathVariable String queueTypeAs,
			@PathVariable String userName){
		return customerService.customerCancelSubscribe(shopId, queueTypeAs, userName);
		
	}
	
	
	/**
	 * 查询用户所有预约信息 HTML
	 * @param sessionId
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAppShop/{sessionId}")
	public String toAppShop(@PathVariable String sessionId, HttpServletRequest request){
		request.setAttribute("sessionId", sessionId);
		return "customer_all_order";

	}
	
	
	/**
	 * 查询用户是否预约
	 * @param userName
	 * @return
	 */
	@RequestMapping("/queryUserWhetherBook/{shopId}/{userName}")
	@ResponseBody
	public BaseResult queryUserWhetherBook(@PathVariable String shopId,@PathVariable String userName){
		return customerService.queryUserWhetherBook(shopId,userName);
	}
	
	
	/**
	 * 查询用户的所有排队信息
	 * @param sessionId
	 * @return
	 */
	@RequestMapping("/queryUserQueueAllInfo/{sessionId}")
	@ResponseBody
	public BaseResult queryUserQueueAllInfo(@PathVariable String sessionId){
		return customerService.queryUserQueueAllInfo(sessionId);
	}
	
	
	
	
	
	
	
	
	
	
	

}
