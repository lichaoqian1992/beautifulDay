package com.manji.desystem.controller.common;

import com.alibaba.fastjson.JSON;
import com.manji.desystem.common.enums.ErrorCodeEnums;
import com.manji.desystem.common.exception.BusinessDealException;
import com.manji.desystem.common.exception.UserNullException;
import com.manji.desystem.common.result.BaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一处理参数异常
 */
@ControllerAdvice
public class ControllerAdviceTest {

    /**
     * 参数异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult messageReturn = new BaseResult(ErrorCodeEnums.InvalidParameter.getCode(), "参数[" + ex.getParameterName() + "]不能为空");
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(messageReturn));
        response.getWriter().close();
        response.flushBuffer();
    }

    /**
     * 参数异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult messageReturn = new BaseResult(ErrorCodeEnums.InvalidParameter.getCode(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(messageReturn));
        response.getWriter().close();
        response.flushBuffer();
    }

    /**
     * 参数异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(BindException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult messageReturn = new BaseResult(ErrorCodeEnums.InvalidParameter.getCode(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(messageReturn));
        response.getWriter().close();
        response.flushBuffer();
    }

    /**
     * 自定义异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(BusinessDealException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(BusinessDealException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult messageReturn = new BaseResult(ErrorCodeEnums.UnknownError.getCode(), ex.getMessage());
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(messageReturn));
        response.getWriter().close();
        response.flushBuffer();
    }

    /**
     * 自定义异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(UserNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(UserNullException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult messageReturn = new BaseResult(ErrorCodeEnums.UserNull.getCode(), ex.getMessage());
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(messageReturn));
        response.getWriter().close();
        response.flushBuffer();
    }

    /**
     * 系统异常
     *
     * @param ex
     * @param request
     * @param response
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void processMethod(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult baseResult = new BaseResult(ErrorCodeEnums.BUSSINESS_HANDLE_ERROR.getCode(), "系统异常", ex.toString());
        ex.printStackTrace();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(baseResult));
        response.getWriter().close();
        response.flushBuffer();
    }

}