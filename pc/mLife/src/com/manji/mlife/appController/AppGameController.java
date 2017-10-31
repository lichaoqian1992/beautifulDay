package com.manji.mlife.appController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.manji.mlife.Vo.GameVo;
import com.manji.mlife.common.FileRead;
import com.manji.mlife.common.MD5;
import com.manji.mlife.service.GameService;
import com.qianmi.open.api.ApiException;

@Controller
@RequestMapping("/app/game")
public class AppGameController {

	@Autowired
	private GameService service;

	/**
	 * 得到游戏的列表
	 * 
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getList(HttpServletRequest request) throws ApiException {

		String listJson = service.getGame();

		return listJson;
	}

	@RequestMapping("/gameBill")
	public void gameBill(String gameId, String gameName, HttpServletRequest req, HttpServletResponse res)
			throws ApiException {
		
//		System.out.println(gameId);
//		System.out.println(gameName);

		// 获得session用户信息
		HttpSession session = req.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		String userName = (String) session.getAttribute("userName");

		String itemListJson = service.getItemList(gameId);

		req.setAttribute("gameId", gameId);
		req.setAttribute("gameName", gameName);
		req.setAttribute("itemListJson", itemListJson);
		
		//获得返回文件内容
		String fileName = "appGameBill";
		String path = req.getRealPath("/");
		String htmlTxt = FileRead.getFileContent(path, fileName);
//		System.out.println(htmlTxt);
		//替换参数
		htmlTxt = htmlTxt.replaceAll("#mLife_UserName", userName);
		htmlTxt = htmlTxt.replaceAll("#mLife_SessionId", sessionId);
		htmlTxt = htmlTxt.replaceAll("#mLife_GameId", gameId);
		htmlTxt = htmlTxt.replaceAll("#mLife_GameName", gameName);
		htmlTxt = htmlTxt.replaceAll("#mLife_GameItemJson", itemListJson);

//		System.out.println(htmlTxt);
		//返回页面信息
		try {
			res.setContentType("text/html");
			res.setCharacterEncoding("utf-8");
			res.getWriter().write(htmlTxt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 生成游戏订单
	 * 
	 * @param vo
	 * @param request
	 * @param attr
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/createGameBill", method = RequestMethod.POST)
	public String createGameBill(GameVo vo, HttpServletRequest request, RedirectAttributes attr) throws ApiException {

		//用户信息参数
		HttpSession session=request.getSession();
		String sessionId =(String) session.getAttribute("sessionId");
		String userName =(String) session.getAttribute("userName");
		
		if(null ==userName||"".equals(userName)){
			
			String errMsg ="登录超时，请重新登录。";
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
			
		}
		// 生成订单信息
		
			
		Map<String, String> billMap = service.createGameBill(vo, userName);
		
		if("1".equals(billMap.get("errCode"))){
			
			String errMsg =billMap.get("errMsg");
			
			session.setAttribute("errMsg", errMsg);
			return "redirect:/errInfo";
		}else{
			// 重定向参数
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
			return "redirect:" + billMap.get("redirectURL");// 支付页面
		}
		
	}
}
