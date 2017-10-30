package com.manji.desystem.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ExamineVo {

    @ApiModelProperty(value = "工单ID",required = true)
    @NotBlank(message = "参数[orPlate]不能为空")
    private String id;

    @ApiModelProperty(value = "审核结果（1：通过  2：不通过）",required = true)
    @NotBlank(message = "参数[examineResult]不能为空")
    private String examineResult;

    @ApiModelProperty(value = "服务评价（1：好，2：中 3：差）",required = true)
    @NotBlank(message = "参数[serviceEvaluation]不能为空")
    private String serviceEvaluation;

    @ApiModelProperty(value = "是否解决（1：已解决 2：未解决）",required = true)
    @NotBlank(message = "参数[isSolve]不能为空")
    private String isSolve;

    @ApiModelProperty(value = "描述")
    private String content;

}
