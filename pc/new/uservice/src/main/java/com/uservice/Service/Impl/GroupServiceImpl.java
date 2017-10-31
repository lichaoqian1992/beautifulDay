package com.uservice.Service.Impl;

import com.uservice.Dto.Group;
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
}
