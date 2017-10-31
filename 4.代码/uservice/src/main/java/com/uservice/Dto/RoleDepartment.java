package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
@Data
public class RoleDepartment {

    @ApiModelProperty("角色名称")
    String role_name;
    @ApiModelProperty("部门名称")
    String dept_name;
}
