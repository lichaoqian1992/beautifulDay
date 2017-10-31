package com.manji.mlife.controller;


import com.manji.mlife.service.DBService;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.response.TokenResponse;
import com.qianmi.open.api.tool.util.OAuthUtils;
import com.qianmi.open.api.tool.util.QianmiContext;

import org.springframework.beans.factory.annotation.Autowired;
//import com.qianmi.open.sdk.web.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
	
	@Autowired
	private DBService service;
	
	
	 /**
     * 显示授权页面
     * @param model
     * @return
     */
    @RequestMapping("/auth")
    public String showPage(Model model,HttpServletRequest request) {
    	
    	String password =request.getParameter("password");
    			
		if("manjishenghuo".equals(password)){
			Map<String, String> map =service.getBasePara();
	    	
	        model.addAttribute("appKey", map.get("appKey"));
	        
	        return "platform/auth";
		}else{
			
			return "redirect:/";
		}
    	
    }
    
    
    @RequestMapping("/callback")
    public String getToken(String code, Model model) {
        try {
        	Map<String, String> map =service.getTokenInfo();
        	
            QianmiContext context = OAuthUtils.getToken(map.get("appKey"), map.get("appSecret"),map.get("accessToken"));
            TokenResponse response = context.getTokenResponse();
            if (response.isSuccess()) {
                updateToken(response);
                startRefreshToken();
            } else {
                model.addAttribute("errMsg", response.getErrorCode() + ": " + response.getMsg());
                return "platform/auth-fail";
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return "platform/auth-success";
    }
    
    private void updateToken(TokenResponse response) {

        service.updateToken(response.getAccessToken(), response.getRefreshToken());
    }
    
    private void startRefreshToken() {
        // 刷新Token任务
        TimerTask refreshTokenTask = new TimerTask() {
            @Override
            public void run() {
                try {
                	Map<String, String> map =service.getTokenInfo();
                    QianmiContext context = OAuthUtils.refreshToken(map.get("appKey"), map.get("appSecret"),map.get("accessToken"));
                    updateToken(context.getTokenResponse());
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        };
        // 每天7点刷新Token，每隔12个小时刷新一次
        new Timer("refresh-token-timer", true).schedule(refreshTokenTask, getFirstTime(), 12 * 60 * 60 * 1000);
    }
    
    private Date getFirstTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        if (date.after(new Date())) {
            return date;
        }
        calendar.add(Calendar.HOUR, 12);
        return calendar.getTime();
    }

}
