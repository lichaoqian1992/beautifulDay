package com.uservice.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Project;
import com.uservice.Dto.Role;
import com.uservice.Mapper.ProjectMapper;
import com.uservice.Mapper.RoleMapper;
import com.uservice.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 添加角色
     * @param dept_id
     * @param role_name
     * @return
     */
    @Override
    public boolean insertRole(int dept_id,String role_name,String pjt_code) {
        boolean isok;
        Role role=new Role();
        role.setDept_id(dept_id);
        role.setRole_name(role_name);
        role.setAdd_time(new Date());
        try {
           isok= roleMapper.insertRole(role);
            if (isok){
                Project project=projectMapper.findProjectByCode(pjt_code);
                role=roleMapper.findRoleBydeptandname(dept_id,role_name);
                roleMapper.bindRolePoject(project.getId(),role.getId());
            }
        }catch (Exception e){
            return false;
        }
        return isok;
    }

    /**
     * 通过部门查询此部门下全部角色
     * @param dept_id
     * @return
     */
    @Override
    public List<Role> findRoleByDept(int dept_id) {
        return roleMapper.findRoleByDept(dept_id);
    }

    /**
     * 角色绑定权限
     * @param roleId
     * @param Menu_id
     * @return
     */
    @Override
    public boolean bindRoleMenu(int roleId, int Menu_id) {
        boolean isok;
        try {
            isok=roleMapper.bindRoleMenu(roleId,Menu_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 通过部门和角色名称查询角色
     * @param dept_id
     * @param role_name
     * @return
     */
    @Override
    public JSONObject findRoleBydeptandname(int dept_id, String role_name) {

        Role role=roleMapper.findRoleBydeptandname(dept_id,role_name);//查询角色
        String j=JSONObject.toJSONString(role);
        JSONObject jsonObject=JSONObject.parseObject(j);
        return jsonObject;
    }

    /**
     * 校验角色是否存在
     * @param dept_id
     * @param role_name
     * @return
     */
    @Override
    public int findRoleBydeptandnamecount(int dept_id, String role_name) {

        return roleMapper.findRoleBydeptandnameCount(dept_id,role_name);
    }

    /**
     * 修改角色名称
     * @param roleId
     * @param roleName
     * @return
     */
    @Override
    public boolean updateRole(int roleId, String roleName) {
        boolean isok;
        try{
            isok=roleMapper.updateRole(roleId,roleName);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }
}
