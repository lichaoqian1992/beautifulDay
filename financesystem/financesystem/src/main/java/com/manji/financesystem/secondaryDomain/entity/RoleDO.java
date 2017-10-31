package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

/**
 * Created by pudding on 2017-1-13.
 */
@Data
public class RoleDO {

    private int id;
    /***
     * 角色名称
     */
    private String roleName;
    /***
     * 角色描述
     */
    private String desciption;
    /***
     * 角色创建时间
     */
    private String createTime;
}
