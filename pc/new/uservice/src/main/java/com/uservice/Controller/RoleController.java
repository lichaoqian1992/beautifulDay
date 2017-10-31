package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Role;
import com.uservice.Service.IRoleService;
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
@RequestMapping(value="/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     *添加角色接口
     * @return
     */
    @ApiOperation(value="添加角色")
    @RequestMapping("/insertRole")
    @ResponseBody
    boolean insertRole(@RequestParam("dept_id")int dept_id,@RequestParam("role_name")String role_name){

       boolean roleisok= roleService.insertRole(dept_id,role_name);
       return roleisok;
    }

    /**
     * 通过部门和角色名称查询角色
     * @param dept_id
     * @param role_name
     * @return
     */
    @ApiOperation(value="通过部门和角色名称查询角色")
    @RequestMapping("/findRoleBydeptandname")
    @ResponseBody
    JSONObject findRoleBydeptandname(@RequestParam("dept_id")int dept_id,@RequestParam("role_name")String role_name){

        JSONObject jsonObject=roleService.findRoleBydeptandname(dept_id,role_name);
        return jsonObject;
    }

    /**
     * 校验角色是否存在
     * @param dept_id
     * @param role_name
     * @return
     */
    @ApiOperation(value="校验角色是否存在",notes = "0表示不存在,1表示存在")
    @RequestMapping("/findRoleBydeptandnameCount")
    @ResponseBody
    int findRoleBydeptandnameCount(@RequestParam("dept_id")int dept_id,@RequestParam("role_name")String role_name){

        return roleService.findRoleBydeptandnamecount(dept_id,role_name);
    }

    /**
     * 通过部门id查询此部门下全部角色
     * @param dept_id
     * @return
     */
    @ApiOperation(value="通过部门查询此部门下全部角色")
    @RequestMapping("/findRoleByDept")
    @ResponseBody
    JSONArray findRoleByDept(@RequestParam("dept_id")int dept_id){

        List<Role> roleList=roleService.findRoleByDept(dept_id);
        String j= JSON.toJSONString(roleList);
        JSONArray jsonObject= JSONObject.parseArray(j);
        return jsonObject;
    }


    /**
     * 角色绑定权限
     * @param roleId
     * @param menuId
     * @return
     */
    @ApiOperation(value="角色绑定权限")
    @RequestMapping("/bindRoleMenu")
    @ResponseBody
    boolean bindRoleMenu(@RequestParam("roleId")int roleId,@RequestParam("menuId")int menuId){

        boolean isok=roleService.bindRoleMenu(roleId,menuId);
        return isok;
    }




}
