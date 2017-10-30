package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;

/**
 * Created by pudding on 2017-9-6.(YYR)
 */
public interface SystemsettingsService {

    BaseResult findUserByDeptId(int pageNum, int pageSize,String name);

    BaseResult RegisteredUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id,int[] groups_id,int dft_group);

    BaseResult findRoleByDept(int pageNum, int pageSize);

    BaseResult insertRole(String rolename,int[] menu_id);

    BaseResult findMenus();

    BaseResult findGroups(int pageNum, int pageSize);

    BaseResult addGroup(String group_name);

    BaseResult delUser(String userId);

    BaseResult updatePassword(String userId, String password);

    BaseResult updateUser(int userId, String job, String mobile, String email, int[] groups_id, int dft_group,int roleId);

    BaseResult delGroup(String groupId);

    BaseResult findUserByGroup(String groupId);

    BaseResult editGroup(String groupId, String groupName, String userId);

    BaseResult findUserById(String userId);

    BaseResult findMenuByRoleId(int roleId);

    BaseResult updateRoles(int roleId, String roleName, int[] menuId, int[] delMenuId);

    BaseResult findMenu(int roleId);
}
