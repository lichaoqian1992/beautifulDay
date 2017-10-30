package com.manji.datahost.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSON;

/**
 * 统一处理参数异常
 */
@ControllerAdvice
public class GlobalException {
	
	 @ExceptionHandler(MissingServletRequestParameterException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public void processMethod(MissingServletRequestParameterException ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
	        Message messageReturn = new Message(ResultEmuns.PARAM.getCode(),"参数["+ ex.getParameterName() + "]不能为空",null);
	        response.setContentType("application/json;charset=utf-8");
	        response.getWriter().write(JSON.toJSONString(messageReturn));
	        response.getWriter().close();
	        response.flushBuffer();
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public void processMethod(MethodArgumentNotValidException ex, HttpServletRequest request , HttpServletResponse response) throws IOException {
	        Message messageReturn = new Message(ResultEmuns.PARAM.getCode(),ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),null);
	        response.setContentType("application/json;charset=utf-8");
	        response.getWriter().write(JSON.toJSONString(messageReturn));
	        response.getWriter().close();
	        response.flushBuffer();
	    }

}
