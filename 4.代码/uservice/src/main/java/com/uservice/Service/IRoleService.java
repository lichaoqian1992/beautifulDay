package com.uservice.Service;

import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Role;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.
 */
public interface IRoleService {

    boolean insertRole(int dept_id,String role_name,String pjt_code);

    List<Role> findRoleByDept(int dept_id);

    boolean bindRoleMenu(int roleId,int Menu_id);

    JSONObject findRoleBydeptandname(int dept_id,String role_name);

    int findRoleBydeptandnamecount(int dept_id,String role_name);

    boolean updateRole(int roleId,String roleName);
}
