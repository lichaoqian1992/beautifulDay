package com.uservice.Service.Impl;

import com.uservice.Dto.Group;
import com.uservice.Dto.GroupUser;
import com.uservice.Dto.User;
import com.uservice.Mapper.GroupMapper;
import com.uservice.Service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pudding on 2017-9-7.(YYR)
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    /**
     * 添加组
     * @param group_name
     * @param dept_id
     * @return
     */
    @Override
    public boolean insertGroup(String group_name, int dept_id) {
        boolean isok;
        Group group=new Group();
        group.setGroup_name(group_name);
        group.setDept_id(dept_id);
        try{
             isok=groupMapper.insertGroup(group);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return isok;
    }

    /**
     * 查询部门下全部组
     * @param dept_id
     * @return
     */
    @Override
    public List<Group> findGroupByDeptId(int dept_id) {

        List<Group> groupList=groupMapper.findGroupByDeptId(dept_id);//查询全部组
        for (int i=0;i<groupList.size();i++){
            groupList.get(i).setGroupCount(groupMapper.findGroupCount(groupList.get(i).getId()));//查询本组人数
        }
        return groupList;
    }

    /**
     * 用户绑定组
     * @param groupId
     * @param userId
     * @return
     */
    @Override
    public boolean bindGroupUser(int userId, int groupId) {
        boolean isok;
        try{
            isok=groupMapper.bindGroupUser(userId,groupId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 查询用户默认组
     * @param userId
     * @return
     */
    @Override
    public List<Group> findGroupByUserIdDft(int userId) {

        List<Group> g=groupMapper.findGroupByUserIdDft(userId);
        return g;
    }

    /**
     * 账户移出组
     * @param userId
     * @return
     */
    @Override
    public boolean deleteGroupUser(int userId) {
        boolean isok;
        try{
            isok=groupMapper.deleteGroupUser(userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 删除组
     * @param id
     * @return
     */
    @Override
    public boolean deleteGroup(int id) {

        boolean isok;
        try{
            isok=groupMapper.deleteGroup(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 通过组id查询此小组下全部人员信息
     * @param groupId
     * @return
     */
    @Override
    public List<User> findGroupUserInfo(int groupId) {
        return groupMapper.findGroupUserInfo(groupId);
    }

    /**
     * 修改组名称
     * @param groupName
     * @param groupId
     * @return
     */
    @Override
    public boolean updateGroup(String groupName, Integer groupId) {
        boolean isok;
        try{
            isok=groupMapper.updateGroup(groupName,groupId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 用户解绑组
     * @param userId
     * @param groupId
     * @return
     */
    @Override
    public boolean deleteGroupUserByGroupId(int userId, int groupId) {

        boolean isok;
        try{
            GroupUser groupUser=groupMapper.findGroupUser(groupId,userId);//获取当前绑定表
            if (groupUser.getDft()==1){//如果删除的组是默认组
                isok=groupMapper.deleteGroupUserByGroupId(userId,groupId);//解绑组
                if (isok){//解绑成功后
                    List<GroupUser> groupUsers= groupMapper.findGroupUserByUser(userId);//查询此用户全部分组

                    if (groupUsers.size()==0){//判断还有绑定的其他组没有
                        isok=groupMapper.bindGroupUserDft(userId,4);//如果没有添加到默认工作台并且设置为默认组
                    }else{//如果有
                        isok=groupMapper.SetDefaultGroup(groupUsers.get(0).getId());//设置查询出来的第一个为默认分组
                    }
                }
            }else{
                isok=groupMapper.deleteGroupUserByGroupId(userId,groupId);//解绑组
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }
}
