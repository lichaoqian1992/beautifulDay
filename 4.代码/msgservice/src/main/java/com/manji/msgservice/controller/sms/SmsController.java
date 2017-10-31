package com.manji.msgservice.controller.sms;

import com.manji.msgservice.common.enums.CodeEnum;
import com.manji.msgservice.common.exception.BusinessDealException;
import com.manji.msgservice.common.result.BaseResult;
import com.manji.msgservice.common.utils.TemplateReplace;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.model.MsgTemplateExample;
import com.manji.msgservice.service.MsgBizTypeService;
import com.manji.msgservice.service.MsgSmsRecService;
import com.manji.msgservice.service.MsgTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 短信发送
 * 
 * @author huanghan
 *
 */
@Api(value = "/api-sms", description = "短信发送")
@RestController
@RequestMapping("/sms")
public class SmsController {

	@Autowired MsgTemplateService msgTemplateService;

	@Autowired MsgSmsRecService msgSmsRecService;


	@ApiOperation(value="发送", notes = "参数说明：" +
			"<br/>templateParamJson：模版参数 如：{\"username\":\"13628420127\",\"webname\":\"牛逼的商城\",\"orderno\":\"2013232323232\"}" +
			"json参数中的key要与模版参数的key对应哟。顺序无所谓的")
	@PostMapping("/send")
	public BaseResult send(@RequestBody @Validated SmsRquestBody body,@RequestParam String templateParamJson) throws Exception {
		BaseResult baseResult=new BaseResult(CodeEnum.SUCCESS.getCode(),"申请发送成功");
		//发送出去的时间类型：timely:及时。。。temptime:模版定义的时间段。。。customize：自定义时间段
		if( !("timely".equals(body.getSendTimeType()) || "temptime".equals(body.getSendTimeType()) || "customize".equals(body.getSendTimeType())) ){
			throw new BusinessDealException("未知的发出时间类型");
		}
		MsgTemplateExample templateExample = new MsgTemplateExample();
		templateExample.or().andCallNameEqualTo(body.getCallName());
		MsgTemplate msgTemplate = msgTemplateService.selectOneByExample(templateExample);
		// 判断模板是否存在
		if (null == msgTemplate) {
			throw new BusinessDealException("模版不存在呢");
		}
		if(!"sms".equals(msgTemplate.getTempType())){
			throw new BusinessDealException("你掉错接口了，这是发送短信接口，这个模版不是发送短信模版");
		}
		if(!"open".equals(msgTemplate.getTemplateStatus())){
			throw new BusinessDealException("调用了一个被停用的模版");
		}
		// 生成发送内容
		String content = TemplateReplace.getReplaceString(templateParamJson,msgTemplate.getTemplateContent());
		msgSmsRecService.sendSmsBiz(msgTemplate, body ,content);
		return baseResult;
	}
}
