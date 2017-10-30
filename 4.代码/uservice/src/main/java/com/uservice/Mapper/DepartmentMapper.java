package com.uservice.Mapper;

import com.alibaba.fastjson.JSONArray;
import com.uservice.Dto.Department;
import com.uservice.Dto.User;
import com.uservice.Dto.UserDept;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
@Mapper
@Component
public interface DepartmentMapper {

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Insert("INSERT INTO jdepartment (#{department})")
    @Lang(SimpleInsertLangDriver.class)
    Department insertDepartment(Department department);



    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from jdepartment")
    List<Department> findAllDepartment();

    /**
     * 通过部门id查询此部门下全部用户信息
     * @param departmentId
     * @return
     */
    @Select("select juser.*,jrole.role_name from jrole,j_user_role,juser  where jrole.dept_id=(#{0}) and jrole.id=j_user_role.role_id and juser.id=j_user_role.user_id")
    List<User> findUserByDeptId(int departmentId);

    /**
     * 通过部门id查询此部门下全部用户信息
     * @param departmentId
     * @return
     */
    @Select("select juser.*,jrole.role_name from jrole,j_user_role,juser  where jrole.dept_id=(#{0}) and jrole.id=j_user_role.role_id and juser.id=j_user_role.user_id and (juser.username like #{1} or juser.job like #{1} or juser.vsername like #{1})")
    List<User> findUserByDeptIdAndUser(int departmentId,String name);

    /**
     * 通过userId查询部门名称和真实姓名
     * @param userId
     * @return
     */
    @Select("select deptname,juser.vsername from juser LEFT JOIN j_user_role on juser.id=j_user_role.user_id LEFT JOIN jrole on jrole.id=j_user_role.role_id LEFT JOIN jdepartment on jdepartment.id=jrole.dept_id where juser.id=#{userId}")
    List<UserDept> findDeptNameByUserId(Integer userId);



}
