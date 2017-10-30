package com.manji.tesystem.controller;

import com.manji.tesystem.common.result.BaseResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@ApiIgnore
@Controller
public class ErrorController {

	
	@ResponseBody
	@RequestMapping(value = "/404",method={RequestMethod.POST,RequestMethod.GET}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseResult FTF(HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("404", "该接口不存在");
		return baseResult;
	}
	@ResponseBody
	@RequestMapping(value = "/500",method={RequestMethod.POST,RequestMethod.GET}, produces = { MediaType.APPLICATION_JSON_VALUE })
	public BaseResult WBAI(HttpServletRequest request) {
		BaseResult baseResult = new BaseResult("500", "服务器端异常");
		return baseResult;
	}
}

