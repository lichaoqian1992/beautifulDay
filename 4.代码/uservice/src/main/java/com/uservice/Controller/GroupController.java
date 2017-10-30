package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uservice.Dto.Group;
import com.uservice.Dto.User;
import com.uservice.Service.IGroupService;
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
    @RequestMapping(value = "/insertGroup",method = RequestMethod.GET)
    @ResponseBody
    boolean insertGroup(@RequestParam("dept_id")int dept_id, @RequestParam("group_name")String group_name){

        boolean isok=groupService.insertGroup(group_name,dept_id);
        return isok;
    }

    @ApiOperation(value="删除分组")
    @RequestMapping(value = "/deleteGroup",method = RequestMethod.GET)
    @ResponseBody
    boolean deleteGroup(@RequestParam("id")int id){

        boolean isok=groupService.deleteGroup(id);
        return isok;
    }
    /**
     * 查询部门下全部组
     * @param dept_id
     * @return
     */
    @ApiOperation(value="查询部门下全部组")
    @RequestMapping(value = "/findGroupByDeptId",method = RequestMethod.GET)
    @ResponseBody
    PageInfo<Group> findGroupByDeptId(@RequestParam("dept_id")int dept_id,@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<Group> groupList=groupService.findGroupByDeptId(dept_id);
        PageInfo<Group> pageInfo = new PageInfo<>(groupList);
        return pageInfo;
    }

    /**
     * 用户绑定组
     * @param groupId
     * @param userId
     * @return
     */
    @ApiOperation(value="用户绑定组")
    @RequestMapping(value = "/bindGroupUser",method = RequestMethod.GET)
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
    @RequestMapping(value = "/deleteGroupUser",method = RequestMethod.GET)
    @ResponseBody
    boolean deleteGroupUser(@RequestParam("userId")int userId){

        boolean isok=groupService.deleteGroupUser(userId);
        return isok;
    }

    @ApiOperation(value="通过组id查询全部人员信息")
    @RequestMapping(value = "/findGroupUserInfo",method = RequestMethod.GET)
    @ResponseBody
    List<User> findGroupUserInfo(@RequestParam("groupId")int groupId){
        return groupService.findGroupUserInfo(groupId);
    }


    @ApiOperation(value="修改组名称")
    @RequestMapping(value = "/updateGroup",method = RequestMethod.GET)
    @ResponseBody
    boolean updateGroup(@RequestParam("groupName")String groupName,@RequestParam("groupId")int groupId){

        return groupService.updateGroup(groupName,groupId);
    }

    @ApiOperation(value="用户解绑组")
    @RequestMapping(value = "/deleteGroupUserByGroupId",method = RequestMethod.GET)
    @ResponseBody
    boolean deleteGroupUserByGroupId(@RequestParam("userId")int userId,@RequestParam("groupId")int groupId){

        return groupService.deleteGroupUserByGroupId(userId,groupId);
    }







}
