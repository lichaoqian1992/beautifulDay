package com.manji.messageserver.config;

import com.manji.messageserver.common.exception.AssertException;
import com.manji.messageserver.responseResult.BaseResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by pudding on 2016-12-12.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    public static final Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult exception(Exception e, WebRequest webRequest) {
        logger.error("全局错误消息------" + e.getMessage());
        BaseResult baseResult = new BaseResult();
        if (e instanceof AssertException) {
            AssertException assertException = (AssertException) e;
            baseResult.setFailResult(assertException.getErrorCode(),"",assertException.getErrorMessage());
            return baseResult;
        }
        baseResult.setFailResult("Service OR Controller Unknown internal Exception","服务器错误",e.getMessage());
        return baseResult;
    }
}
