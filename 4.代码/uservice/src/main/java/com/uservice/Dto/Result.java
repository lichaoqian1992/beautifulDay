package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-8-25.（YYR）
 */
@Data
public class Result {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("登录码")
    String code;

    @ApiModelProperty("返回登录信息")
    String prompt;

    @ApiModelProperty("返回码")
    int result;
}
