package com.manji.msgservice.controller.sms;

import com.manji.msgservice.request.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

public class SmsRquestBody extends BaseParam {
    private Long userId;
    private String roleType;
    private Long roleValue;

    @NotBlank(message ="调用别名不能为空")
    private String callName;
    @NotBlank(message ="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "用户ID")
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @ApiModelProperty(value = "角色类型")
    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    @ApiModelProperty(value = "角色类型")
    public Long getRoleValue() {
        return roleValue;
    }
    public void setRoleValue(Long roleValue) {
        this.roleValue = roleValue;
    }
    @ApiModelProperty(value = "调用别名")
    public String getCallName() {
        return callName;
    }
    public void setCallName(String callName) {
        this.callName = callName;
    }
    @ApiModelProperty(value = "发送的手机号 多个用 英文的逗号分割")
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
