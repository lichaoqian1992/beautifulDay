package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-9-23.
 */
@Data
public class UserDept {

    @ApiModelProperty("部门名称")
    private String deptname;

    @ApiModelProperty("真实姓名")
    private String vsername;
}
