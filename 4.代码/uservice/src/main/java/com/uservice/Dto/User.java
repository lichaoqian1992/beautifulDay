package com.uservice.Dto;

import com.uservice.MybatisUtil.Invisible;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * Created by pudding on 2017-8-24.（YYR）
 */
@ApiModel("User(账户模型)")
@Data
public class User {

    @ApiModelProperty("ID")
    Integer id;

    @ApiModelProperty("登录账号")
    String username;

    @ApiModelProperty("密码")
    String password;

    @ApiModelProperty("名字拼音")
    String name;

    @ApiModelProperty("工号")
    String job;

    @ApiModelProperty("真实姓名")
    String vsername;

    @ApiModelProperty("电话")
    String mobile;

    @ApiModelProperty("email")
    String email;

    @ApiModelProperty("注册时间")
    Date add_time;

    @ApiModelProperty("第一次登录时间")
    Date login_time;

    @ApiModelProperty("最后一次登录时间")
    Date last_login_time;

    @ApiModelProperty("登录次数")
    int count;

    @ApiModelProperty("角色名称")
    @Invisible
    String role_name;

    @ApiModelProperty("组名称")
    @Invisible
    String group_name;

    @ApiModelProperty("部门名称")
    @Invisible
    String deptname;

}
