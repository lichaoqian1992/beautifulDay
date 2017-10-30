package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.SystemsettingsService;
import lombok.experimental.PackagePrivate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by pudding on 2017-9-6.(YYR)
 */
@RestController
@RequestMapping(value = "/system")
public class SystemsettingsContoller extends BaseController{

    @Autowired
    SystemsettingsService systemsettingsService;


    /**
     *注册用户
     */
    @RequestMapping(value = "/addUser")
    public String RegisteredUser(@Param("sessionId")String sessionId,@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("name")String name, @RequestParam("job")String job, @RequestParam("vsername")String vsername, @RequestParam("mobile")String mobile, @RequestParam("email")String email, @RequestParam("role_id")int role_id,@RequestParam("dft_group") int dft_group,@RequestParam("groups_id") int[] groups_id){
        //int[] groups_id= {1,2,3,4};
        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")) {
            result = systemsettingsService.RegisteredUser(username, password, name, job, vsername, mobile, email, role_id, groups_id, dft_group);
        }
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 删除用户
     * @param sessionId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delUser")
    public String delUser(@Param("sessionId")String sessionId,@RequestParam("userId")String userId){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")) {
            result = systemsettingsService.delUser(userId);
        }

        return JSONObject.toJSON(result).toString();

    }

    /**
     * 重置密码
     * @param sessionId
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(@Param("sessionId")String sessionId,@RequestParam("userId")String userId,@Param("password") String password){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result = systemsettingsService.updatePassword(userId,password);
        }
        return JSONObject.toJSON(result).toString();
    }


    /**
     * 修改用户
     * @param sessionId
     * @param userId
     * @param job
     * @param mobile
     * @param email
     * @param dft_group
     * @param groups_id
     * @return
     */
    @RequestMapping(value = "/updateUser")
    public String updateUser(@Param("sessionId")String sessionId,@RequestParam("userId")int userId,@RequestParam("job")String job,@RequestParam("mobile")String mobile,@RequestParam("email")String email,@RequestParam("dft_group")int dft_group,int[] groups_id){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result = systemsettingsService.updateUser(userId,job,mobile,email,groups_id,dft_group);
        }
        return JSONObject.toJSON(result).toString();

    }


    /**
     * 查询当前部门下全部用户
     * @return
     */
    @RequestMapping(value = "/findUsers")
    public String findUsers(@Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result=systemsettingsService.findUserByDeptId();
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }


    /**
     * 添加角色
     * @return
     */
    @RequestMapping(value = "/addRoles")
    public String addRoles(@RequestParam("roleName")String roleName,@RequestParam("menuId")Integer[] menuId){

        BaseResult result=systemsettingsService.insertRole(roleName,menuId);
        return JSONObject.toJSON(result).toString();
    }


    /**
     * 查询全部角色
     * @return
     */
    @RequestMapping(value = "/Roles")
    public String findRoleByDept(){

        BaseResult result=systemsettingsService.findRoleByDept();
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 查询全部权限
     */
    @RequestMapping(value = "/Menus")
    public String findMenus(){

        BaseResult result=systemsettingsService.findMenus();
        return JSONObject.toJSON(result).toString();
    }





    /**
     * 添加组
     * @param group_name
     * @return
     */
    @RequestMapping(value = "/addGroup")
    public String addGroup(@RequestParam("group_name")String group_name,@Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result=systemsettingsService.addGroup(group_name);
        }
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 查询全部组
     * @return
     */
    @RequestMapping(value = "/Groups")
    public String findGroups(@Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result=systemsettingsService.findGroups();
        }
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 修改角色绑定的权限
     * @param roleId
     * @param menuId 改变的权限
     * @return
     */
    @RequestMapping(value = "/updateRoleMenu")
    public String updateRoleMenu(@Param("roleId")int roleId,@Param("menuId") int[] menuId){

        BaseResult result =systemsettingsService.updateRoleMenu(roleId,menuId);
        return JSONObject.toJSON(result).toString();
    }


}
