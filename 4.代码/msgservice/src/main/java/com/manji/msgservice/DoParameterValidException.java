package com.manji.msgservice;

import com.alibaba.fastjson.JSON;
import com.manji.msgservice.common.enums.CodeEnum;
import com.manji.msgservice.common.exception.BusinessDealException;
import com.manji.msgservice.common.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 统一处理@RequestParam(required = true) 和 @RequestBody @Validated 抛出的验证exception
 */
@ControllerAdvice
public class DoParameterValidException {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(Exception.class)
    public void processMethod(Exception ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
        //@RequestParam(required = true)异常
        if(ex instanceof  MissingServletRequestParameterException){
            MissingServletRequestParameterException ex1 = (MissingServletRequestParameterException)ex;
            BaseResult baseResult=new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(),"参数["+ex1.getParameterName() + "]不能为空");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(baseResult));
            response.getWriter().close();
            response.flushBuffer();
            String returnInfo = JSON.toJSONString(baseResult);
            logger.warn("返回值：{}", returnInfo);
            logger.warn("---------------------------------------------------------------------------------------------------------------------");
        }
        //@RequestBody @Validated 异常
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex1 = (MethodArgumentNotValidException)ex;
            BaseResult baseResult=new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(),ex1.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(baseResult));
            response.getWriter().close();
            response.flushBuffer();
            String returnInfo = JSON.toJSONString(baseResult);
            logger.warn("返回值：{}", returnInfo);
            logger.warn("---------------------------------------------------------------------------------------------------------------------");
        }
        //处理自定义异常
        if(ex instanceof BusinessDealException){
            BusinessDealException ex1 = (BusinessDealException)ex;
            BaseResult baseResult=new BaseResult(CodeEnum.BUSSINESS_HANDLE_ERROR.getCode(),ex1.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(baseResult));
            response.getWriter().close();
            response.flushBuffer();
            String returnInfo = JSON.toJSONString(baseResult);
            logger.warn("返回值：{}", returnInfo);
            logger.warn("---------------------------------------------------------------------------------------------------------------------");
        }
        else {
            ex.printStackTrace();
            logger.error("系统异常，{}", ex.getMessage());
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw, true));
            BaseResult baseResult=new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(),"系统异常",sw.toString());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(baseResult));
            response.getWriter().close();
            response.flushBuffer();
            String returnInfo = JSON.toJSONString(baseResult);
            logger.warn("返回值：{}", returnInfo);
            logger.warn("---------------------------------------------------------------------------------------------------------------------");
        }
    }
}
