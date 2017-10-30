package com.manji.msgservice.controller.bizType;

import com.manji.msgservice.common.enums.CodeEnum;
import com.manji.msgservice.common.result.BaseListResult;
import com.manji.msgservice.model.MsgBizType;
import com.manji.msgservice.service.MsgBizTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 批量控制器
 * 
 * @author Administrator
 *
 */
@RestController
@Api(value = "/api-bizType", description = "业务分类")
@RequestMapping("/bizType")
public class BizTypeController {

	@Autowired MsgBizTypeService msgBizTypeService;

	@ApiOperation(value="查询")
	@GetMapping("/query")
	public BaseListResult<MsgBizType> query(){
		BaseListResult<MsgBizType> baseResult=new BaseListResult<>(CodeEnum.SUCCESS.getCode(),"查询成功");
		List<MsgBizType> list = msgBizTypeService.selectByExample(null);
		baseResult.setResult(list);
		return baseResult;
	}
}
