package com.manji.orService.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/8/30.
 */

@Data
public class AssignVo {

    //处理人
    @NotBlank(message = "参数[orHandleId]不能为空")
    private String orHandleId;
    @NotBlank(message = "参数[orHandle]不能为空")
    private String orHandle;
    @NotBlank(message = "参数[orHandleDept]不能为空")
    private String orHandleDept;

    //抄送人
    private String orSendList;
    //工单id
    @NotBlank(message = "参数[id]不能为空")
    private String id;
    //指派信息
    @NotBlank(message = "参数[content]不能为空")
    private String content;
    //图片
    private String pics;
}
