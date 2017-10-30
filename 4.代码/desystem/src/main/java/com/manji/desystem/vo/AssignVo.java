package com.manji.desystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class AssignVo {


    @ApiModelProperty(value = "处理人ID",required = true)
    @NotBlank(message = "参数[orHandleId]不能为空")
    private String orHandleId;

    @ApiModelProperty(value = "处理人姓名",required = true)
    @NotBlank(message = "参数[orHandle]不能为空")
    private String orHandle;

    @ApiModelProperty(value = "处理人部门",required = true)
    @NotBlank(message = "参数[orHandleDept]不能为空")
    private String orHandleDept;

    @ApiModelProperty(value = "抄送人ID")
    private String orSendList;

    @ApiModelProperty(value = "工单ID",required = true)
    @NotBlank(message = "参数[id]不能为空")
    private String id;

    @ApiModelProperty(value = "指派内容",required = true)
    @NotBlank(message = "参数[content]不能为空")
    private String content;

    @ApiModelProperty(value = "图片路径")
    private String pics;

}
