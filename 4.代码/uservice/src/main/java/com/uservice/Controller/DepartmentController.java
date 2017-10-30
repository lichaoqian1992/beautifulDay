package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uservice.Dto.Department;
import com.uservice.Dto.Group;
import com.uservice.Dto.User;
import com.uservice.Service.Impl.DepartmentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/insertDept",method = RequestMethod.GET)
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
    @RequestMapping(value = "/findAllDept",method = RequestMethod.GET)
    @ResponseBody
    List<Department> findAllDept(){

        List<Department> departmentList=departmentService.findAllDepartment();

        return departmentList;
    }

    /**
     * 通过部门id查询此部门下全部用户信息
     * @param dept_id
     * @return
     */
    @ApiOperation(value="通过部门查询此部门下全部账户信息")
    @RequestMapping(value = "/findUserByDeptId",method = RequestMethod.GET)
    @ResponseBody
    PageInfo<User> findUserByDeptId(@RequestParam("dept_id")int dept_id,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,@RequestParam("name")String name){

        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=departmentService.findUserByDeptId(dept_id,name);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @ApiOperation(value="通过UserId查询账户真实姓名和部门名")
    @RequestMapping(value = "/findDeptNameByUserId",method = RequestMethod.GET)
    @ResponseBody
    JSONArray findDeptNameByUserId(@RequestParam("userId")int userId){
        JSONArray jsonArray=departmentService.findDeptNameByUserId(userId);
        return jsonArray;
    }


}
