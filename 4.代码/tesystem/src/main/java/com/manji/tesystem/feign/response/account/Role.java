package com.manji.tesystem.feign.response.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/5.
 */
@Data
public class Role {
    @ApiModelProperty("部门名")
    private String dept_name;
    @ApiModelProperty("角色名")
    private String role_name;
}
