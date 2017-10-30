package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-8-26.（YYR）
 */
@Data
public class Project {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("项目名")
    String pjt_name;

    @ApiModelProperty("项目唯一code")
    String pjt_code;

    @ApiModelProperty("是否开启项目")
    int is_hide;
}
