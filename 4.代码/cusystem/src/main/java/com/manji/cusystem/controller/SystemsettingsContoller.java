package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.service.SystemsettingsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public String RegisteredUser(@Param("sessionId")String sessionId,@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("name")String name, @RequestParam("job")String job, @RequestParam("vsername")String vsername, @RequestParam("mobile")String mobile, @RequestParam("email")String email, @RequestParam("cus_way")int role_id,@RequestParam("dft_group") int dft_group,@RequestParam("groups_id") String groups_id){

        String[] sourceStrArray = groups_id.split(",");
        int[] groups_idArray=new int[sourceStrArray.length];
        for (int i=0;i<sourceStrArray.length;i++){
            groups_idArray[i]=Integer.parseInt(sourceStrArray[i]);
        }

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")) {
            result = systemsettingsService.RegisteredUser(username, password, name, job, vsername, mobile, email, role_id, groups_idArray, dft_group);
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
    public String updateUser(@Param("sessionId")String sessionId,@RequestParam("userId")int userId,@RequestParam("job")String job,@RequestParam("mobile")String mobile,
                             @RequestParam("email")String email,@RequestParam(value = "dft_group",required = false)int dft_group,@RequestParam(value = "groups_id")String groups_id,
                             @RequestParam("cus_way") int roleId){

        String[] sourceStrArray = groups_id.split(",");
        int[] groups_idArray=new int[sourceStrArray.length];
        for (int i=0;i<sourceStrArray.length;i++){
            groups_idArray[i]=Integer.parseInt(sourceStrArray[i]);
        }

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result = systemsettingsService.updateUser(userId,job,mobile,email,groups_idArray,dft_group,roleId);
        }
        return JSONObject.toJSON(result).toString();

    }


    /**
     * 查询当前部门下全部用户
     * @return
     */
    @RequestMapping(value = "/findUsers")
    public String findUsers(@Param("sessionId")String sessionId,HttpServletRequest request){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            int pageNum = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            String name = request.getParameter("other");
            result=systemsettingsService.findUserByDeptId(pageNum, pageSize,name);
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 根据id查询用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/findUserById")
    public String fidUserById(HttpServletRequest request){

        String userId = request.getParameter("userId");

        BaseResult result = systemsettingsService.findUserById(userId);

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }


    /**
     * 添加角色
     * @return
     */
    @RequestMapping(value = "/addRoles")
    public String addRoles(@RequestParam("sessionId")String sessionId,@RequestParam("roleName")String roleName,@RequestParam("menuId")int[] menuId){

        BaseResult result=systemsettingsService.insertRole(roleName,menuId);
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 查询某个角色的权限
     * @return
     */
    @RequestMapping(value = "/findMenuByRoleId")
    public String findMenuByRoleId(@Param("roleId") int roleId,@Param("sessionId") String sessionId){

        BaseResult result=systemsettingsService.findMenuByRoleId(roleId);
        return JSONObject.toJSON(result).toString();

    }


    /**
     * 查询全部角色
     * @return
     */
    @RequestMapping(value = "/Roles")
    public String findRoleByDept(HttpServletRequest request){

        int pageNum = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        BaseResult result=systemsettingsService.findRoleByDept(pageNum,pageSize);
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
     * 修改角色权限
     * @return
     */
    @RequestMapping(value = "/updateRoles")
    public String updateRoles(@RequestParam("roleId") int roleId,@RequestParam("roleName") String roleName,@RequestParam("sessionId") String sessionId,@RequestParam("menuId")int[] menuId,@RequestParam(value="delMenuId",required = false)int[] delMenuId){
        //@RequestParam("roleId") int roleId,@RequestParam("roleName") String roleName,@RequestParam("sessionId") String sessionId,@RequestParam("menuId")int[] menuId,@RequestParam("delMenuId")int[] delMenuId
        //System.out.println("roleId="+roleId+" roleName="+roleName+" sessionId="+sessionId+" menuid="+menuId+" delMenu=");
        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            result=systemsettingsService.updateRoles(roleId,roleName,menuId,delMenuId);

        }
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
    public String findGroups(@Param("sessionId")String sessionId,HttpServletRequest request){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            int pageNum = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            result=systemsettingsService.findGroups(pageNum,pageSize);
        }
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 删除分组
     * @param request
     * @return
     */
    @RequestMapping(value = "/delGroup")
    public String delGroup(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().toString().equals("200")){
           String groupId = request.getParameter("groupId");
            result=systemsettingsService.delGroup(groupId);
        }
        return JSONObject.toJSON(result).toString();


    }

    /**
     * 查询分组下面的人员
     * @param request
     * @return
     */
    @RequestMapping(value = "/findUserByGroup")
    public String findUserByGroup(HttpServletRequest request){

        String groupId = request.getParameter("groupId");

        BaseResult result = systemsettingsService.findUserByGroup(groupId);

        return JSONObject.toJSONString(result);
    }

    /**
     * 修改分组
     * @return
     */
    @RequestMapping(value = "/editGroup")
    public String editGroup(HttpServletRequest request){

        String groupId = request.getParameter("groupId");
        String groupName = request.getParameter("groupName");
        String userId = request.getParameter("userId");

        BaseResult result = systemsettingsService.editGroup(groupId,groupName,userId);

        return JSONObject.toJSONString(result);
    }


}
