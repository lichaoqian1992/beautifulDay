package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by pudding on 2017-1-16.
 */
@Data
public class LoginSystemRequestParam {

    /***
     * 登录用户名
     */
    private String username;

    /***
     * 密码
     */
    private String password;
}
