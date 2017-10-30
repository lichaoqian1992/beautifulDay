package com.uservice.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Department;
import com.uservice.Dto.User;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */
public interface IDepartmentService {

    List<Department> findAllDepartment();

    boolean insertDepartment(String deptname);

    List<User> findUserByDeptId(int departmentId,String name);

    JSONArray findDeptNameByUserId(Integer userId);
}
