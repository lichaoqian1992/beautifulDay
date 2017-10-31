package com.manji.orService.dao.account;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * 登录返回的账户信息
 */
@Data
public class Account {
    private List<Role> role;
    private List<Menu> menu;
    private User user;
    private String sessionId;
    private int result;
    private String code;
    private String prompt;
    private String id;
    private String userId;

    public String getUserId() {
        return id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
