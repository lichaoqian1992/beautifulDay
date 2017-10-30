package com.manji.msgservice.controller.template;


import com.manji.msgservice.common.enums.CodeEnum;
import com.manji.msgservice.common.exception.BusinessDealException;
import com.manji.msgservice.common.result.BaseResult;
import com.manji.msgservice.common.utils.DateUtils;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.model.MsgTemplateExample;
import com.manji.msgservice.service.MsgTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 模板管理
 * @author huanghan
 *
 */
@Api(value = "/api-template", description = "模版管理")
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired MsgTemplateService msgTemplateService;
    /**
     *新增模版
     * {
         "bizType": "订位",
         "callName": "SMS00001",
         "deadTime": "23:59:59",
         "liveTime": "0:00:00",
         "tempType": "sms",
         "templateContent": "尊敬的{username}，您在{webname}的订单已发货，订单号为：{orderno}，请注意查收。",
         "templateName": "订单发货通知",
         "templateStatus": "open"
     }
     */
    @ApiOperation(value = "新增模版", notes = "新增模版")
    @PostMapping(value = "/add", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseResult add(@RequestBody @Validated AddRequstBody body) {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "新增成功");
        //其他参数校验
        if( !("inner".equals(body.getTempType()) || "sms".equals(body.getTempType()) || "mail".equals(body.getTempType())) ){
            throw new BusinessDealException("未知的模版类型");
        }
        if(!("open".equals(body.getTemplateStatus()) || "close".equals(body.getTemplateStatus())) ){
            throw new BusinessDealException("未知的模版状态");
        }
        //查询当前设置的调用别名是否已经存在
        MsgTemplateExample example = new MsgTemplateExample();
        example.or().andCallNameEqualTo(body.getCallName());
        int a = msgTemplateService.countByExample(example);
        if(a > 0){
            throw new BusinessDealException("当前设置的调用别名已存在。换一个吧");
        }
        MsgTemplate model = new MsgTemplate();
        BeanUtils.copyProperties(body,model);
        if(StringUtils.isNotBlank(body.getLiveTime()) && StringUtils.isNotBlank(body.getDeadTime())){
            model.setLiveTime(DateUtils.strToDate(body.getLiveTime(), "HH:mm:00"));
            model.setDeadTime(DateUtils.strToDate(body.getDeadTime(), "HH:mm:59"));
        }
        msgTemplateService.insert(model);
        return baseResult;
    }
    /**
     *编辑模版
     */
    @ApiOperation(value = "编辑模版", notes = "编辑模版")
    @PostMapping(value = "/edit", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseResult edit(@RequestBody @Validated EditRequstBody body) {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "修改成功");
        //其他参数校验
        if(StringUtils.isNotBlank(body.getTemplateStatus())){
            if(!("open".equals(body.getTemplateStatus()) || "close".equals(body.getTemplateStatus())) ){
                throw new BusinessDealException("未知的模版状态");
            }
        }
        MsgTemplate model = new MsgTemplate();
        BeanUtils.copyProperties(body,model);
        if(StringUtils.isNotBlank(body.getLiveTime()) && StringUtils.isNotBlank(body.getDeadTime())){
            model.setLiveTime(DateUtils.strToDate(body.getLiveTime(), "HH:mm:00"));
            model.setDeadTime(DateUtils.strToDate(body.getDeadTime(), "HH:mm:59"));
        }
        msgTemplateService.updateByPrimaryKeySelective(model);
        return baseResult;
    }
    /**
     *删除模版
     */
    @ApiOperation(value = "删除模版", notes = "删除模版")
    @PostMapping(value = "/deleted", produces = { MediaType.APPLICATION_JSON_VALUE })
    public BaseResult deleted(@RequestBody List<Long> templateIds) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "删除成功");
        if(null == templateIds){
            throw new BusinessDealException("请选择要删除的模版");
        }
        if(templateIds.size() <= 0){
            throw new BusinessDealException("请选择要删除的模版");
        }
        //msgTemplateService.deletedTemps(templateIds);
        return baseResult;
    }
}
