package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-8-26.（YYR）
 */
@Data
public class Department {

    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("部门名称")
    String deptname;
}
