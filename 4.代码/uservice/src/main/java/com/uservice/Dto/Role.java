package com.uservice.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
@Data
public class Role {
    @ApiModelProperty("ID")
    int id;

    @ApiModelProperty("部门ID")
    int dept_id;

    @ApiModelProperty("角色名称")
    String role_name;

    @ApiModelProperty("添加时间")
    Date add_time;
}
