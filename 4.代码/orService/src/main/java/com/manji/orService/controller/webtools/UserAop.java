package com.manji.orService.controller.webtools;

import com.alibaba.fastjson.JSON;
import com.manji.orService.common.Message;
import com.manji.orService.enums.ErrorCodeEnums;
import com.manji.orService.util.ValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//证明是一个配置文件(使用@Component也可以,因为点入后会发现@Configuration还是使用了@Component)
@Configuration
//证明是一个切面
@Aspect
public class UserAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution (* com.manji.orService.controller.SheetController.*(..))")
    public Object testAop(ProceedingJoinPoint pro) throws Throwable {

        long startTime = System.currentTimeMillis();
        //获取request请求提(需要时备用)
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //执行调用的方法
        Object proceed = pro.proceed();

        //方法执行完成后执行的方法
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        long endTime = System.currentTimeMillis();
        String returnInfo = JSON.toJSONString(proceed);
        long time = endTime - startTime;

        logger.warn("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {},返回值：{}，耗时：{}ms", url, method, uri, queryString,returnInfo, time);
        logger.warn("---------------------------------------------------------------------------------------------------------------------");
        return proceed;
    }
}
