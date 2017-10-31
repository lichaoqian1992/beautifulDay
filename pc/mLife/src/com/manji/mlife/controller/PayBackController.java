package com.manji.mlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manji.mlife.Vo.PayBackVo;

@Controller
@RequestMapping("/payBack")
public class PayBackController {
	
	@RequestMapping("/syn")
	public String synPayBack(PayBackVo vo,HttpServletRequest req){
		
		
		
		//1.MD5解密。。
		
		
		//2.修改库表
		
		
		//3.返回信息展示
		
		
		String errcode =vo.getErrCode();
		System.out.println(errcode);
		if("0".equals(errcode)){//交易成功
			
			
			
			
			
			
			
			req.setAttribute("response", "success");
		}else{//交易失败
			
			
			
			req.setAttribute("response", "error");
		}
		
		
		
		
		return "payBack";
	}
	@RequestMapping("/asyn")
	public String asynPayBack(HttpServletRequest req){
		
		
		
		req.setAttribute("response", "success");
		
		return "asynPayBack";
		
	}
	
	
	
	

}
