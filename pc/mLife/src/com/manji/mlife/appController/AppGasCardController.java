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

import com.manji.mlife.Vo.GasCardVo;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.GasCardService;
import com.qianmi.open.api.ApiException;

@Controller
@RequestMapping("/app/gasCard")
public class AppGasCardController {
	
	@Autowired
	private GasCardService service;
	
	
	/**
	 * 查询加油卡状态
	 * @param operator
	 * @param gasCardNo
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/queryCardInfo", method = RequestMethod.GET)
	@ResponseBody 
	public String confirmCard(String operator, String gasCardNo) throws ApiException{
		
		String province="全国";
		String cardStatue =service.queryAccount(province,operator,gasCardNo);
		
		return cardStatue;
	}
	
	
	
	/**
	 * 查询加油卡类标准商品列表
	 * @author gaochao
	 * @param operator
	 * @return
	 * @throws ApiException
	 * 2016年9月5日上午9:50:07
	 * String
	 *
	 */
	@RequestMapping(value = "/getCom", method = RequestMethod.GET)
	@ResponseBody 
	public String getCom(String operator) throws ApiException{
		
		String json =service.queryCom(operator);
		
//		System.out.println(json);
		
		return json;
	}
	
	
	/**
	 * 生成加油卡直充订单
	 * @author gaochao
	 * @param vo
	 * @param request
	 * @param attr
	 * @return
	 * @throws ApiException
	 * 2016年9月5日上午9:50:17
	 * String
	 *
	 */
	@RequestMapping("/createBill")
	public String createBill(GasCardVo vo, HttpServletRequest request,  RedirectAttributes attr) throws ApiException{
		
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");	
		
		if(null ==userName||"".equals(userName)){
			
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
			
		}
		
		Map<String,String> billMap =service.creatBill(vo,userName);
		
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
