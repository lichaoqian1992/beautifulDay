package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by Administrator on 2017/3/1.
 */
@Data
public class UserInfoRequestParam {

    private int id;
    private String userName;
    private String roleName;
    private String password;
    private String realName;
    private String mobile;
    private String email;

    private String creater;
}
