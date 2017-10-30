package com.manji.cusystem.dao.common;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * 登录返回的账户信息
 */
@Data
public class Account {

    private String userId;

    private String userName;

    private String realName;

    private String email;

    private String job;//工号

    private String mobile;

    private List<Role> role;

    private List<Menu> menu;

    private String sessionId;

}
