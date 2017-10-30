package com.manji.desystem.dao.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class User {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("真实姓名")
    private String vsername;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("工号")
    private String job;//工号
    @ApiModelProperty("移动电话")
    private String mobile;
}
