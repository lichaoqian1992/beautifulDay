package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Department;
import com.uservice.Dto.User;
import com.uservice.Service.Impl.DepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pudding on 2017-8-31.(YYR)
 */

@RequestMapping(value="/dept")
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /**
     *添加部门接口
     * @return
     */
    @ApiOperation(value="添加部门")
    @ApiImplicitParam(name = "deptname", value = "部门名称", required = true, dataType = "String")
    @RequestMapping("/insertDept")
    @ResponseBody
    boolean insertDept(@RequestParam("dept_name") String deptname){

        boolean isok=departmentService.insertDepartment(deptname);
        return isok;
    }

    /**
     * 查看全部部门
     * @return
     */
    @ApiOperation(value="查看全部部门")
    @RequestMapping("/findAllDept")
    @ResponseBody
    JSONArray findAllDept(){

        List<Department> departmentList=departmentService.findAllDepartment();
        String j= JSON.toJSONString(departmentList);
        JSONArray jsonObject=JSONObject.parseArray(j);
        return jsonObject;
    }

    /**
     * 通过部门id查询此部门下全部用户信息
     * @param dept_id
     * @return
     */
    @ApiOperation(value="通过部门查询此部门下全部账户信息")
    @ApiImplicitParam(name = "dept_id", value = "部门Id", required = true, dataType = "int")
    @RequestMapping("/findUserByDeptId")
    @ResponseBody
    JSONArray findUserByDeptId(@RequestParam("dept_id")int dept_id){

        JSONArray userList=departmentService.findUserByDeptId(dept_id);

        return userList;
    }

}
