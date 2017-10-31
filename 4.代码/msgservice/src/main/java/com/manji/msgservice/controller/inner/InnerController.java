package com.manji.msgservice.controller.inner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manji.msgservice.common.enums.CodeEnum;
import com.manji.msgservice.common.exception.BusinessDealException;
import com.manji.msgservice.common.result.BaseResult;
import com.manji.msgservice.common.utils.TemplateReplace;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.model.MsgTemplateExample;
import com.manji.msgservice.service.MsgTemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 站内信
 * @author Administrator
 *
 */
@Api(value = "/api-inner", description = "站内信")
@RestController
@RequestMapping("/inner")
public class InnerController {

	@Autowired MsgTemplateService msgTemplateService;

	@ApiOperation(value="发送")
	@PostMapping("/send")
	public BaseResult send(@RequestBody @Validated InnerRquestBody body , @RequestParam String templateParamJson) throws Exception {
		BaseResult baseResult=new BaseResult(CodeEnum.SUCCESS.getCode(),"执行成功");

		MsgTemplateExample templateExample = new MsgTemplateExample();
		templateExample.or().andCallNameEqualTo(body.getCallName());
		MsgTemplate msgTemplate = msgTemplateService.selectOneByExample(templateExample);
		// 判断模板是否存在
		if (null == msgTemplate) {
			throw new BusinessDealException("模版不存在呢");
		}
		if(!"inner".equals(msgTemplate.getTempType())){
			throw new BusinessDealException("你掉错接口了，这是站内信发送接口，这个模版不是站内信模版");
		}
		if(!"open".equals(msgTemplate.getTemplateStatus())){
			throw new BusinessDealException("调用了一个被停用的模版");
		}
		// 生成发送内容
		String content = TemplateReplace.getReplaceString(templateParamJson,msgTemplate.getTemplateContent());

		return baseResult;
	}
}
