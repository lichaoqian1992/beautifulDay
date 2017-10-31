package com.uservice.Mapper;

import com.uservice.Dto.Group;
import com.uservice.Dto.GroupUser;
import com.uservice.Dto.User;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-9-7.（YYR）
 */
@Mapper
@Component
public interface GroupMapper {

    /**
     * 通过userId查询组
     * @param userId
     * @return
     */
    @Select("SELECT jgroup.id,jgroup.group_name FROM juser LEFT JOIN j_group_user on juser.id=j_group_user.user_id LEFT JOIN jgroup on jgroup.id=j_group_user.group_id where juser.id=(#{userId})")
    List<Group> findGroupByUserId(int userId);

    /**
     * 查询默认组
     * @param userId
     * @return
     */
    @Select("select jgroup.id,jgroup.group_name from juser,j_group_user,jgroup where juser.id=j_group_user.user_id and jgroup.id=j_group_user.group_id and j_group_user.dft=1 and juser.id=(#{userId})")
    List<Group> findGroupByUserIdDft(int userId);

    /**
     * 查询此部门下全部组
     * @param deptId
     * @return
     */
    @Select("SELECT jgroup.id,jgroup.group_name,jgroup.dept_id FROM jgroup WHERE dept_id=(#{deptId})")
    List<Group> findGroupByDeptId(int deptId);


    List<Group> findGroupByDeptIdByPage(int deptId,int pageNum,int pageSize);


    /**
     * 添加组
     * @param group
     * @return
     */
    @Insert("INSERT INTO jgroup (#{group})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertGroup(Group group);

    /**
     * 修改组名称
     * @param id
     * @return
     */
    @Update("UPDATE jgroup SET group_name=#{0} where id=#{1}")
    boolean updateGroup(String group_name,Integer id);

    /**
     * 删除组
     * @param id
     * @return
     */
    @Delete("delete from jgroup where id=#{id}")
    boolean deleteGroup(Integer id);


    /**
     * 用户绑定组
     * @param groupId
     * @param userId
     * @return
     */
    @Insert("INSERT INTO j_group_user VALUES(null,#{0},#{1},0)")
    boolean bindGroupUser(int userId,int groupId);

    /**
     * 用户新增默认组
     * @param groupId
     * @param userId
     * @return
     */
    @Insert("INSERT INTO j_group_user VALUES(null,#{0},#{1},1)")
    boolean bindGroupUserDft(int userId,int groupId);


    /**
     * 修改j_group_user
     * @param id
     * @return
     */
    @Update("update j_group_user set dft=1 where id=(#{id})")
    boolean SetDefaultGroup(int id);

    /**
     * 通过userId groupId 查询绑定表
     * @param userId
     * @param groupId
     * @return
     */
    @Select("SELECT * FROM j_group_user where group_id=(#{0}) and user_id=(#{1})")
    GroupUser findGroupUser(int groupId, int userId);

    /**
     * 通过userId 查询绑定表
     * @param userId
     * @return
     */
    @Select("SELECT * FROM j_group_user where user_id=(#{0})")
    List<GroupUser> findGroupUserByUser(int userId);

    /**
     * 查询本组人数
     * @param groupId
     * @return
     */
    @Select("select count(*) from j_group_user,jgroup,juser where  j_group_user.user_id=juser.id and jgroup.id=j_group_user.group_id and j_group_user.group_id=(#{group_id})")
    int findGroupCount(int groupId);


    /**
     * 查询本组下全部人员信息
     * @param groupId
     * @return
     */
    @Select("select juser.*,jrole.role_name from j_group_user,jgroup,juser,jrole,j_user_role where j_user_role.user_id=juser.id and j_user_role.role_id=jrole.id and j_group_user.user_id=juser.id and jgroup.id=j_group_user.group_id and j_group_user.group_id=(#{group_id})")
    List<User> findGroupUserInfo(int groupId);


    /**
     * 查询本组下有审核权限的审核人
     * @param groupId
     * @param path
     * @return
     */
    @Select("select juser.*,jdepartment.deptname,jgroup.group_name,jrole.role_name from j_group_user,jgroup,juser,j_user_role,jrole,j_role_menu,jmenu,jdepartment\n" +
            " where   j_group_user.user_id=juser.id and jgroup.id=j_group_user.group_id and juser.id=j_user_role.user_id and jrole.id=j_user_role.role_id and jrole.id=j_role_menu.role_id and j_role_menu.menu_id=jmenu.id\n" +
            "and j_group_user.group_id=(#{0}) and jmenu.path=(#{1}) and jdepartment.id=jrole.dept_id ")
    List<User> findshUserByGroup(int groupId,String path);


    /**
     * 账户移出组
     * @param userId
     * @return
     */
    @Delete("delete from j_group_user where user_id=#{userId}")
    boolean deleteGroupUser(int userId);


    /**
     *用户解绑组
     */
    @Delete("delete from j_group_user where user_id=#{0} and group_id=#{1}")
    boolean deleteGroupUserByGroupId(int userId,int groupId);




}
