package com.manji.cusystem.vo.message;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/10.
 */
@Data
public class MessageVo {

    private String cusId;

    @NotNull(message = "短信(邮件)类型cusType不能为空")
    private String cusType;//短信类型
    @NotNull(message = "接收对象cusAcceptType不能为空")
    private String cusAcceptType;//接收对象

    private String cusTheme;//邮件主题
    @NotNull(message = "短信(邮件)内容cusContent不能为空")
    private String cusContent;//短信内容
    @NotNull(message = "短信(邮件)数量cusCount不能为空")
    private String cusCount;//短信数量
    @NotNull(message = "类型cusKind不能为空")
    private String cusKind;//邮件还是短信
    private String cusUrl;//邮件图片地址

    private String cusTime;//创建时间
    private String cusSendTime;//发送时间
    private String cusStatus;//发送状态

}
