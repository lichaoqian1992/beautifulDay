package com.manji.msgservice.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {  
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
	
    @Around("execution(* com.manji.msgservice.service.impl..*.*(..))")
    public Object process(ProceedingJoinPoint jp) throws Throwable {
    	Object rvt = null;
		long startTime = System.currentTimeMillis();
		String methodInfo = jp.getTarget().getClass().getSimpleName() + "."+ jp.getSignature().getName();
		String paramInfo = JSON.toJSONString(jp.getArgs());
		LOGGER.info("调用方法：{}，入参：{}", methodInfo, paramInfo);
		rvt = jp.proceed();
		long endTime = System.currentTimeMillis();
		String returnInfo = JSON.toJSONString(rvt);
		long time = endTime - startTime;
		LOGGER.info("调用方法：{}，返回值：{}，耗时：{}ms", methodInfo, returnInfo, time);
		LOGGER.info("-----------------------------------------------------------------------------------------");
		return rvt;
    }
} 