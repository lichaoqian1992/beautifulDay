package com.manji.orService.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    /**
     * 获取session对象
     *
     * @return
     * @throws Exception
     */
    public static HttpSession httpsession() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    /**
     * 获取request对象
     *
     * @return
     * @throws Exception
     */
    public static HttpServletRequest request() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }


}
