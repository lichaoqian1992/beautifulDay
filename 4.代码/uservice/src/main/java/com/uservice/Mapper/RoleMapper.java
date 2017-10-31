package com.uservice.Mapper;

import com.uservice.Dto.Role;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.（YYR）
 */
@Mapper
@Component
public interface RoleMapper {

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Insert("INSERT INTO jrole (#{role})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertRole(Role role);

    /**
     * 修改角色名称
     */
    @Update("update jrole set role_name=#{1} where id=#{0}")
    boolean updateRole(Integer roleId,String roleName);

    /**
     * 通过userId查询角色信息
     * @param userId
     * @return
     */
    @Select("SELECT jrole.* from jrole LEFT JOIN j_user_role on j_user_role.role_id=jrole.id LEFT JOIN juser on juser.id=j_user_role.user_id where juser.id=#{0} and jrole.dept_id=#{1}")
    Role findRoleByUserId(Integer userId,int deptId);
    /**
     * 通过部门查询此部门下全部角色
     * @param dept_id
     * @return
     */
    @Select("SELECT * FROM jrole where dept_id=(#{dept_id})")
    List<Role> findRoleByDept(int dept_id);

    /**
     * 通过部门id和角色名称查询角色
     * @param dept_id
     * @param role_name
     * @return
     */
    @Select("SELECT * FROM jrole where dept_id=(#{0}) and role_name=(#{1})")
    Role findRoleBydeptandname(int dept_id,String role_name);

    /**
     * 校验角色是否存在
     * @param dept_id
     * @param role_name
     * @return
     */
    @Select("SELECT count(*) FROM jrole where dept_id=(#{0}) and role_name=(#{1})")
    int findRoleBydeptandnameCount(int dept_id,String role_name);

    /**
     * 角色绑定权限
     * @param roleId
     * @param menu_id
     * @return
     */
    @Insert("INSERT INTO j_role_menu VALUES(null,#{0},#{1})")
    boolean bindRoleMenu(int roleId,int menu_id);

    /**
     * 角色绑定项目
     * @param pojectId
     * @param roleId
     * @return
     */
    @Insert("INSERT INTO j_role_poject VALUES(null,#{0},#{1})")
    boolean bindRolePoject(int pojectId,int roleId);




}
