package com.manji.cusystem.vo.sheets;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/4.
 * 工单所需参数
 */
@Data
public class SheetsVo {

    /*Sheet*/
    private String orId;//主键
    @NotNull(message = "orTheme不能为空")
    private String orTheme;//主题 ===   二级原因名称
    @NotNull(message = "orPersonId不能为空")
    private String orPersonId;//处理人id
    @NotNull(message = "处理人id orHandleId不能为空")
    private String orHandleId;//处理人id
    @NotNull(message = "处理人姓名orHandle不能为空")
    private String orHandle;//处理人姓名
    @NotNull(message = "处理人部门orHandleDept不能为空")
    private String orHandleDept;//处理人部门
    private String orSendList;//抄送人id列表
    @NotNull(message = "orSheetTypeId不能为空")
    private String orSheetTypeId; //一级原因id
    @NotNull(message = "orPriority不能为空")
    private String orPriority;//紧急程度
    @NotNull(message = "orCompleteTime不能为空")
    private String orCompleteTime;//要求完成时间
    private String orLabel;//标签
    private String orSheetContent;//工单内容
    private String orIsUpload;//是否有上传文件
    private String orConId;//会话id

    /*ConSheet*/
    private String cusId;//客户id
    @NotNull(message = "cusTel不能为空")
    private String cusTel;//来电号码
    @NotNull(message = "cusType不能为空")
    private String cusType;//客户类型
    @NotNull(message = "cusName不能为空")
    private String cusName;//客户姓名
    @NotNull(message = "cusAccount不能为空")
    private String cusAccount;//客户账号
    @NotNull(message = "cusMobile不能为空")
    private String cusMobile;//联系电话
    private String cusArea;//客户区域
    private String cusUserTime;//用户注册时间
    private String cusShopTime;//商家注册时间
    @NotNull(message = "cusStatus不能为空")
    private String cusStatus;//账号状态
    @NotNull(message = "cusState不能为空")
    private String cusState;//账户状态
}
