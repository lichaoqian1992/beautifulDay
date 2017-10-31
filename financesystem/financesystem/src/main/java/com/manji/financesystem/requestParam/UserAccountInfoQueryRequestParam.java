package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * Created by pudding on 2017-1-22.
 * 用户账号信息查询的param
 */
@Data
public class UserAccountInfoQueryRequestParam {

    /***用户名*/
    private String userName;

    /***用户类型*/
    private String roleType;

    /***用户状态*/
    private String state;

}
