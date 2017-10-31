package com.manji.financesystem.secondaryDomain.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by pudding on 2017-1-13.
 */
@Data
public class PermissionDO {

    private int id;

    /***
     * 权限名称
     */
    private String permissionName;

    /***
     * 权限描述
     */
    private String description;

    /***
     * 角色ID
     */
    private Integer roleId;

    /***
     * 创建时间
     */
    private Date createTime;

}
