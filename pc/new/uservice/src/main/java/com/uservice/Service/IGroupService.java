package com.uservice.Service;

import com.uservice.Dto.Group;

import java.util.List;

/**
 * Created by pudding on 2017-9-7.(YYR)
 */
public interface IGroupService {

    boolean insertGroup(String group_name,int dept_id);

    List<Group> findGroupByDeptId(int dept_id);

    boolean bindGroupUser(int userId,int groupId);

    List<Group> findGroupByUserIdDft(int userId);

    boolean deleteGroupUser(int userId);
}
