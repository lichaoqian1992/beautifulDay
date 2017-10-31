package com.manji.financesystem.requestParam;

import lombok.Data;

/**
 * 用户编号和权限编号
 * Created by Administrator on 2017/3/13.
 */
@Data
public class UserAndRoleRequestParam {

    private String userId;

    private String userName;

    private String roleId;

    private String creater;
}
