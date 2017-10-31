package com.manji.tesystem.controller.api.knowledge.requestbody;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class AddKnowledgeBody {
    @ApiModelProperty("标题")
    @NotBlank(message ="标题不能为空")
    String title;

    @ApiModelProperty("内容")
    @NotBlank(message ="内容不能为空")
    String content;

    @ApiModelProperty("分类")
    @NotBlank(message ="分类不能为空")
    String category;

    @ApiModelProperty("树ID")
    @NotNull(message ="树Id必传")
    Integer tree_id;

    @ApiModelProperty("状态 0:启用,1:禁用")
    @NotNull(message ="状态必传")
    Integer state;
}
