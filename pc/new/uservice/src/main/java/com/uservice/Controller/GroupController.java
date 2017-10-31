package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Group;
import com.uservice.Service.IGroupService;
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
 * Created by pudding on 2017-9-7.(YYR)
 */
@RequestMapping(value="/group")
@Controller
public class GroupController {

    @Autowired
    private IGroupService groupService;

    /**
     * 添加组
     * @param dept_id
     * @param group_name
     * @return
     */
    @ApiOperation(value="添加组")
    @RequestMapping("/insertGroup")
    @ResponseBody
    boolean insertGroup(@RequestParam("dept_id")int dept_id, @RequestParam("group_name")String group_name){

        boolean isok=groupService.insertGroup(group_name,dept_id);
        return isok;
    }

    /**
     * 查询部门下全部组
     * @param dept_id
     * @return
     */
    @ApiOperation(value="查询部门下全部组")
    @ApiImplicitParam(name = "dept_id", value = "部门Id", required = true, dataType = "int")
    @RequestMapping("/findGroupByDeptId")
    @ResponseBody
    JSONArray findGroupByDeptId(@RequestParam("dept_id")int dept_id){

        List<Group> groupList=groupService.findGroupByDeptId(dept_id);
        String j=JSON.toJSONString(groupList);
        JSONArray jsonObject= JSONObject.parseArray(j);
        return jsonObject;
    }

    /**
     * 用户绑定组
     * @param groupId
     * @param userId
     * @return
     */
    @ApiOperation(value="用户绑定组")
    @RequestMapping("/bindGroupUser")
    @ResponseBody
    boolean bindGroupUser(@RequestParam("groupId")int groupId,@RequestParam("userId")int userId){

        boolean isok=groupService.bindGroupUser(userId,groupId);
        return isok;
    }

    /**
     * 用户移除组
     * @return
     */
    @ApiOperation(value="用户移除组")
    @RequestMapping("/deleteGroupUser")
    @ResponseBody
    boolean deleteGroupUser(@RequestParam("userId")int userId){

        boolean isok=groupService.deleteGroupUser(userId);
        return isok;
    }





}
