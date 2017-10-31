package com.manji.desystem.controller.interceptor;


import com.alibaba.fastjson.JSON;
import com.manji.desystem.common.enums.ErrorCodeEnums;
import com.manji.desystem.common.result.BaseResult;
import com.manji.desystem.common.util.SessionUtil;
import com.manji.desystem.dao.account.Account;
import com.manji.desystem.service.FeignLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntercetor extends HandlerInterceptorAdapter {

    @Autowired
    private FeignLoginService feignLoginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod _handler = ((HandlerMethod) handler);
            LoginAuth loginAuth = _handler.getMethodAnnotation(LoginAuth.class);
            if (loginAuth == null) {
                return true;
            }
            String sessionId = request.getParameter("sessionId");
            if (StringUtils.isBlank(sessionId)) {
                BaseResult baseResult = new BaseResult(ErrorCodeEnums.UserNull.getCode(), "请您先登录 ");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(baseResult));
                response.getWriter().close();
                return false;
            }
            Account account = feignLoginService.checkUser(sessionId);
            if (null == account) {
                BaseResult baseResult = new BaseResult(ErrorCodeEnums.UserNull.getCode(), "登录信息已失效，请您重新登录");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(baseResult));
                response.getWriter().close();
                return false;
            }
            if (0 != account.getResult()) {
                BaseResult baseResult = new BaseResult(ErrorCodeEnums.UserNull.getCode(), account.getPrompt());
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(baseResult));
                response.getWriter().close();
                return false;
            }

            //创建登录session
            SessionUtil.httpsession().setAttribute("account", account);
            SessionUtil.httpsession().setMaxInactiveInterval(30 * 60);
        }

        return true;
    }
}
