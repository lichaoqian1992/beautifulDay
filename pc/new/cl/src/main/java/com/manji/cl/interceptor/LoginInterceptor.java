package com.manji.cl.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.dao.User;
import com.manji.cl.feigns.BServiceFeignService;
import com.manji.cl.feigns.DataHostFeignService;
import com.manji.cl.utils.GetAccountUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/8/29.
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Autowired
    private DataHostFeignService feignService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        /**
         * 对来自后台的请求统一进行日志处理
         */
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println(request.getParameterMap());
        logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));

        BaseResult result = new BaseResult();
        //1.先判断是否存在用户信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            //2.不存在看是否传有sessionId
            String sessionId = request.getParameter("sessionId");
            if(sessionId == null || sessionId.equals("")){
                result.setCode("404");
                result.setContent("sessionId不能为空");
                result.setResult("");
                request.setAttribute("result",result);
                response.sendRedirect(request.getContextPath()+"/");
                return false;
            }else{
                //3.拿取用户信息
                result = GetAccountUtils.getAccount(sessionId);
                if(result != null){
                    if(result.getResult() != null){
                        session.setAttribute("user",result.getResult());

                    }
                }
            }
        }else{
            //判断是否是同一个账号登录
            String sessionId = request.getParameter("sessionId");
            result = GetAccountUtils.getAccount(sessionId);
            System.out.println(result.getResult()+"++++++++++++++++++++++++++++++++++");
            if(result != null){
                if(result.getResult() != null) {
                    User u = (User) result.getResult();
                    if (!((User) session.getAttribute("user")).getName().equals(u.getName())) {
                        session.setAttribute("user", u);

                    }
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
