package com.manji.desystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonSheetVo {

    @ApiModelProperty(value = "工单ID")
    private String orId;

    @ApiModelProperty(value = "主题")
    private String orTheme;

    @ApiModelProperty(value = "抄送ID")
    private String orSendList;

    @ApiModelProperty(value = "工单类型ID")
    private String orSheetTypeId;

    @ApiModelProperty(value = "紧急程度")
    private String orPriority;

    @ApiModelProperty(value = "完成时间")
    private String orCompleteTime;

    @ApiModelProperty(value = "标签")
    private String orLabel;

    @ApiModelProperty(value = "图片")
    private String orSheetPics;

    @ApiModelProperty(value = "内容")
    private String orSheetContent;

    @ApiModelProperty(value = "关联会话ID")
    private String orConId;

    @ApiModelProperty(value = "进线电话")
    private String cusTel;

    @ApiModelProperty(value = "客户类型（商家、用户、非满集用户）")
    private String cusType;

    @ApiModelProperty(value = "客户姓名")
    private String cusName;

    @ApiModelProperty(value = "客户账号")
    private String cusAccount;

    @ApiModelProperty(value = "联系电话")
    private String cusMobile;

    @ApiModelProperty(value = "客户区域")
    private String cusArea;

    @ApiModelProperty(value = "用户注册时间")
    private String cusUserTime;

    @ApiModelProperty(value = "商家注册时间")
    private String cusShopTime;

    @ApiModelProperty(value = "账号状态 0正常,1待验证,2待审核,3锁定,4黑名单")
    private String cusStatus;

    @ApiModelProperty(value = "账户状态 1正常/0冻结/9异常")
    private String cusState;

    @ApiModelProperty(value = "处理人ID")
    private int orHandleId;

    @ApiModelProperty(value = "处理人姓名")
    private String orHandle;

    @ApiModelProperty(value = "处理人部门")
    private String orHandleDept;

}
