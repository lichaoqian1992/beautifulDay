package com.uservice.Mapper;

import com.uservice.Dto.Department;
import com.uservice.Dto.User;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("select juser.*,jrole.role_name from jrole,j_user_role,juser  where jrole.dept_id=(#{departmentId}) and jrole.id=j_user_role.role_id and juser.id=j_user_role.user_id")
    List<User> findUserByDeptId(int departmentId);

}
