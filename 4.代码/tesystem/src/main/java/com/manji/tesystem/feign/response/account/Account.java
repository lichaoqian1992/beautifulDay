package com.manji.tesystem.feign.response.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * 登录返回的账户信息
 */
@Data
public class Account {
    @ApiModelProperty("角色")
    private List<Role> role;
    @ApiModelProperty("菜单")
    private List<Menu> menu;
    @ApiModelProperty("用户")
    private User user;
    @ApiModelProperty("sessionid")
    private String sessionId;
    private int result;
    //private String code;
    @ApiModelProperty("提示语")
    private String prompt;
}
