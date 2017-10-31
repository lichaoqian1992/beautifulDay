package com.manji.mlife.appController;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.common.MD5;
import com.manji.mlife.service.MobileFlowService;
import com.qianmi.open.api.ApiException;

@Controller
@RequestMapping("/app/mobileFlow")
public class AppMobileFlowController {
	@Autowired
	private MobileFlowService mobileFlowService;
	/**
     * 查询号码归属地
     * @author gaochao
     * @param mobileNo
     * @return
     * 2016年7月29日下午2:25:17
     * String
     * @throws ApiException 
     */
    @RequestMapping( value="/getPhoneInfo" ,produces = "text/html;charset=UTF-8" )
    @ResponseBody
    public String getPhoneInfo(String mobileNo) throws ApiException{
    	
    	//查询号码归属地
    	String Info = mobileFlowService.findPhoneaddr(mobileNo);
    	
    	return Info;
    }
    /**
	 * ajax请求
	 * qianmi.elife.mobile.flow.items.list2（【推荐】查询固定流量包的商品列表(含商品明细)） 
	 * 筛选进价最低的商品,设置零售价,返回
	 * @throws ApiException 
	 */
	@RequestMapping(value="/flowItems",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String flowItems(String mobileNo ,String flow ) throws ApiException{
		//获取零售价
		String	string=mobileFlowService.getflowItems(mobileNo,flow);
		
		return string;
	}
	/**
	 * 
	 * 创建流量订单
	 * qianmi.elife.mobile.flow.createBill（生成手机流量直充订单） 
	 * @throws ApiException 
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	@RequestMapping(value="/flowCreateBill",method=RequestMethod.GET)
	public String  flowCreateBill( String mobileNo ,String itemId ,HttpServletRequest request ,RedirectAttributes attr) throws ApiException, ServletException, IOException{
		
				
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");

		if(null ==userName||"".equals(userName)){
			
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
			
		}
		//创建流量充值订单
		Map<String,String> billMap=mobileFlowService.getFlowCreateBill(mobileNo,itemId, userName);
		
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
			attr.addAttribute("order_money", billMap.get("saleAmount"));
			attr.addAttribute("return_url", billMap.get("backSynURL"));
			attr.addAttribute("notify_url", billMap.get("backAsynURL"));
			attr.addAttribute("timestamp", timestamp);
			
			attr.addAttribute("sign", MD5.generateSign(attr));
			return "redirect:"+billMap.get("redirectURL");//支付页面
		
		}
	}
}
