package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;

/**
 * Created by pudding on 2017-9-6.(YYR)
 */
public interface SystemsettingsService {

    BaseResult findUserByDeptId();

    BaseResult RegisteredUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id,int[] groups_id,int dft_group);

    BaseResult findRoleByDept();

    BaseResult insertRole(String rolename,Integer[] menu_id);

    BaseResult findMenus();

    BaseResult findGroups();

    BaseResult addGroup(String group_name);

    BaseResult delUser(String userId);

    BaseResult updatePassword(String userId, String password);

    BaseResult updateUser(int userId, String job, String mobile, String email, int[] groups_id, int dft_group);

    BaseResult updateRoleMenu(int roleId,int[] menuId);
}
