package com.manji.cusystem.vo.sheets;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/15.
 */
@Data
public class AssignSheetVo {

    //处理人相关
    @NotNull(message = "指派人id  orHandleId不能为空")
    private String orHandleId;
    @NotNull(message = "指派人名称  orHandle不能为空")
    private String orHandle;
    @NotNull(message = "指派人部门  orHandleDept不能为空")
    private String orHandleDept;

    //抄送人
    @NotNull(message = "指派人id列表  orSendList不能为空")
    private String orSendList;
    //工单id
    @NotNull(message = "工单id不能为空")
    private String id;
    //指派信息
    @NotNull(message = "指派内容  content不能为空")
    private String content;
}
