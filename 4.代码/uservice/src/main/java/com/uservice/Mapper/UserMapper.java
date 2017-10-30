package com.uservice.Mapper;

import com.uservice.Dto.RoleDepartment;
import com.uservice.Dto.User;
import com.uservice.MybatisUtil.SimpleInsertLangDriver;
import com.uservice.MybatisUtil.SimpleUpdateLangDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pudding on 2017-8-24.(YYR)
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 查询全部账户
     * @return
     */
    @Select("select * from juser")
    List<User> findAllUser();

    @Select("select * from juser where id= #{userId}")
    User findUserById(int userId);

    /**
     * 通过账号密码查询出账户
     * @param username
     * @param password
     * @return
     */
    @Select("select * from juser where username = #{username} and password=#{password}")
    User findUserByusernameAndpassword(@Param("username") String username,@Param("password") String password);

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Insert("INSERT INTO juser (#{user})")
    @Lang(SimpleInsertLangDriver.class)
    boolean insertUsers(User user);

    

    /**
     * 绑定角色
     * @param user_id
     * @param role_id
     * @return
     */
    @Insert("INSERT INTO j_user_role VALUES(null,#{0},#{1})")
    boolean insertUser_Role(int user_id,int role_id);

    /**
     * 修改账户的角色
     */
    @Update("update j_user_role set role_id=#{0} where user_id=#{1}")
    boolean updateUser_Role(int role_id,int user_id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Update("UPDATE juser (#{user}) WHERE id = #{id}")
    @Lang(SimpleUpdateLangDriver.class)
    boolean updateUsersById(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Delete("delete from juser WHERE id= #{id}")
    boolean delectUsersById(int id);

    /**
     * 删除关联角色表
     * @param user_id
     * @return
     */
    @Delete("delete from j_user_role WHERE user_id=#{user_id}")
    boolean delectUserRole(int user_id);

    /**
     * 验证账户是否存在
     * @param username
     * @return
     */
    @Select("SELECT COUNT(*) FROM juser where username=(#{username})")
    int selectUserByUsername(String username);

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT COUNT(*) FROM juser where username=(#{0}) and password=(#{1})")
    int selectUserByUsernameAndPassword(String username,String password);

    /**
     * 取到此用户对应权限下的对应项目code
     * @param username
     * @param password
     * @return
     */
    @Select("select p.pjt_code from juser u LEFT JOIN j_user_role ur on u.id=ur.user_id LEFT JOIN jrole r on r.id=ur.role_id LEFT JOIN j_role_poject rp on rp.role_id=r.id LEFT JOIN jproject p on rp.project_id=p.id  where u.username=(#{0}) and u.password=(#{1}) and p.is_hide=0;")
    List<String> selectpjtCodeByUsernameAndPassword(String username, String password);

    /**
     * 通过userId查询此用户所在部门和对应角色
     * @param user_id
     * @return
     */
    @Select("select r.role_name,d.deptname as dept_name from j_user_role ur LEFT JOIN jrole r on r.id=ur.role_id LEFT JOIN jdepartment d on d.id=r.dept_id where ur.user_id=(#{user_id});")
    List<RoleDepartment> findRolenameAndDeptname(int user_id);

    /**
     * 通过用户名字缩写模糊查询用户
     * @param name
     * @return
     */
    @Select("select jdepartment.deptname,juser.* from juser LEFT JOIN j_user_role on j_user_role.user_id=juser.id LEFT JOIN jrole on jrole.id=j_user_role.role_id LEFT JOIN jdepartment on jdepartment.id=jrole.dept_id where name like (#{name}) or vsername like (#{name})")
    List<User> findUserByName(String name);

    /**
     * 批量修改密码为初始密码(重置全部账户密码)
     */
    @Update("update juser set password=#{0} where id=#{1}")
    void updatePassword(String password,int id);




}
