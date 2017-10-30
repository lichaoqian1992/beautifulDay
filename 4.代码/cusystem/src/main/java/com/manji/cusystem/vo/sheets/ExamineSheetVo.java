package com.manji.cusystem.vo.sheets;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2017/9/13.
 */
@Data
public class ExamineSheetVo {

    //工单id
    @NotNull(message = "工单id不能为空")
    private String id;
    //审核结果
    @NotNull(message = "审核结果examineResult不能为空")
    private String examineResult;//1：通过  2：不通过
    //服务评价
    @NotNull(message = "服务评价serviceEvaluation不能为空")
    private String serviceEvaluation;//1：好，2：中 3：差
    //是否解决
    @NotNull(message = "是否解决isSolve不能为空")
    private String isSolve;//1：已解决 2：未解决
    //描述
    @NotNull(message = "描述content不能为空")
    private String content;
}
