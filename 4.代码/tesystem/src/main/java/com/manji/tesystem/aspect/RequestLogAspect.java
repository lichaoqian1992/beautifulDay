package com.manji.tesystem.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;

@Component
@Aspect
public class RequestLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.manji.tesystem.controller.api..*.*(..))")
    public void executeService(){
    }
    /**
     * 环绕通知
     * @param joinPoint
     */
    @Around("executeService()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Object rvt = null;
        long startTime = System.currentTimeMillis();
        Object[] objects = joinPoint.getArgs();
        //获取requestBody
        String requestBody = getRequestBody(objects);
        //logger.info("请求的类和方法：class:" + signature.getDeclaringTypeName() +",mathd:"+ signature.getName());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取请求路径地址
        String uri = request.getRequestURI().trim().replaceAll("/{2,10}", "/");
        //获取Parameter参数集
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            parameterMap.put(key,request.getParameter(key));
        }
        //获取Header参数集
        Enumeration<String> headerEnumeration = request.getHeaderNames();
        Map<String,String> headerMap = Maps.newHashMap();
        while (headerEnumeration.hasMoreElements()){
            String key = headerEnumeration.nextElement();
            headerMap.put(key,request.getHeader(key));
        }
        logger.warn("---------------------------------------------------------------------------------------------------------------------");
        logger.warn("请求信息:"+ request.getMethod() + "  " +uri +
                "  ---parameter:"+ JSON.toJSONString(parameterMap) +
                "  ---header:" + JSON.toJSONString(headerMap) +
                "  ---body:" + requestBody);
        rvt = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String returnInfo = JSON.toJSONString(rvt);
        long time = endTime - startTime;
        logger.warn("返回值：{}，耗时：{}ms", returnInfo, time);
        logger.warn("---------------------------------------------------------------------------------------------------------------------");
        return rvt;
    }

    /**
     * 获取requestBody
     * @param objects
     * @return
     */
    public String getRequestBody(Object[] objects)throws Exception{
        String requestBody = null;
        if (objects!=null && objects.length>0) {
            for (Object object : objects) {
                if (object != null) {
                    if ( !(object instanceof String || object instanceof HttpServletRequest || object instanceof HttpServletResponse) ) {
                        requestBody = JSONObject.toJSONString(object);
                    }
                }
            }
        }
        return requestBody;
    }
}
