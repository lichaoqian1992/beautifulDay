package com.manji.cusystem.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/4.
 */
@FeignClient(name="uservice" ,url = "${uservice.service.url}")//name:表示调用的是那个服务，后面的url表示服务地址
public  interface UServiceFeignService {

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @param code
     * @param sign
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    JSONObject checkUser(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("code") String code, @RequestParam("sign") String sign);

    @RequestMapping(value = "/user/logins")
    JSONObject logins(@RequestParam("sessionId") String sessionId);

    @RequestMapping(value = "/dept/findAllDept")
    JSONArray findAllDept();

    @RequestMapping(value = "/dept/findUserByDeptId")
    JSONArray findDeptUser(@RequestParam("dept_id") String deptId);

    @RequestMapping(value = "/user/findExamineUser")
    JSONArray findExaminePeople(@RequestParam("userId") String userId, @RequestParam("path") String path);

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @param name
     * @param job
     * @param vsername
     * @param mobile
     * @param email
     * @param role_id
     * @return
     */
    @RequestMapping(value = "/user/registerUser", method = RequestMethod.GET)
    boolean RegisteredUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("job") String job, @RequestParam("vsername") String vsername, @RequestParam("mobile") String mobile, @RequestParam("email") String email, @RequestParam("role_id") int role_id);

    /**
     * 通过账号密码获取用户
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/user/getUser", method = RequestMethod.GET)
    JSONObject getUser(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 修改用户
     *
     * @param id
     * @param job
     * @param mobile
     * @param email
     * @return
     */
    @RequestMapping(value = "/user/updateUsersById", method = RequestMethod.GET)
    boolean updateUsersById(@RequestParam("id") int id, @RequestParam("job") String job, @RequestParam("mobile") String mobile, @RequestParam("email") String email);

    /**
     * 将账户移出组
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/group/deleteGroupUser", method = RequestMethod.GET)
    boolean deleteGroupUser(@RequestParam("userId") int userId);

    /**
     * 用户绑定组(注册模块)
     *
     * @param groupId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/group/bindGroupUser", method = RequestMethod.GET)
    boolean bindGroupUser(@RequestParam("groupId") int groupId, @RequestParam("userId") int userId);

    /**
     * 设置默认组
     *
     * @param userId
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/user/updateGroupUserdft", method = RequestMethod.GET)
    boolean updateGroupUserdft(@RequestParam("userId") int userId, @RequestParam("groupId") int groupId);

    /**
     * 通过部门查询全部用户信息
     *
     * @param dept_id
     * @return
     */
    @RequestMapping(value = "/dept/findUserByDeptId", method = RequestMethod.GET)
    JSONArray findUserByDeptId(@RequestParam("dept_id") int dept_id);


    /**
     * 查询全部角色信息
     *
     * @param dept_id
     * @return
     */
    @RequestMapping(value = "/role/findRoleByDept", method = RequestMethod.GET)
    JSONArray findRoleByDept(@RequestParam("dept_id") int dept_id);


    /**
     * 添加角色
     *
     * @param dept_id
     * @param role_name
     * @return
     */
    @RequestMapping(value = "/role/insertRole", method = RequestMethod.GET)
    boolean insertRole(@RequestParam("dept_id") int dept_id, @RequestParam("role_name") String role_name);

    /**
     * 通过部门和角色名称查询角色
     *
     * @param dept_id
     * @param role_name
     * @return
     */
    @RequestMapping(value = "/role/findRoleBydeptandname", method = RequestMethod.GET)
    JSONObject findRoleBydeptandname(@RequestParam("dept_id") int dept_id, @RequestParam("role_name") String role_name);

    /**
     * 校验此角色是否已经存在
     *
     * @param dept_id
     * @param role_name
     * @return
     */
    @RequestMapping(value = "/role/findRoleBydeptandnameCount", method = RequestMethod.GET)
    int findRoleBydeptandnameCount(@RequestParam("dept_id") int dept_id, @RequestParam("role_name") String role_name);


    /**
     * 角色绑定权限
     *
     * @param roleId
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/menu/insertRoleMenu", method = RequestMethod.GET)
    boolean insertRoleMenu(@RequestParam("roleId") int roleId, @RequestParam("menuId") Integer[] menuId);

    /**
     * 角色解绑权限
     * @param roleId
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/menu/deleteRoleMenu", method = RequestMethod.GET)
    boolean deleteRoleMenu(@RequestParam("roleId") int roleId, @RequestParam("menuId") Integer[] menuId);

    /**
     * 验证此角色是否绑定了此权限
     * @param roleId
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/menu/getRoleMenuCount", method = RequestMethod.GET)
    boolean getRoleMenuCount(@RequestParam("roleId") int roleId, @RequestParam("menuId") int menuId);

    /**
     * 通过项目code查询项目下全部权限
     *
     * @param pjt_code
     * @return
     */
    @RequestMapping(value = "/menu/findMenus", method = RequestMethod.GET)
    JSONArray findMenus(@RequestParam("pjt_code") String pjt_code);



    /**
     * 通过项目code查询项目下全部组
     *
     * @param dept_id
     * @return
     */
    @RequestMapping(value = "/group/findGroupByDeptId", method = RequestMethod.GET)
    JSONArray findGroupByDeptId(@RequestParam("dept_id") int dept_id);

    /**
     * 添加客户组
     *
     * @param dept_id
     * @param group_name
     * @return
     */
    @RequestMapping(value = "/group/insertGroup", method = RequestMethod.GET)
    boolean insertGroup(@RequestParam("dept_id") int dept_id, @RequestParam("group_name") String group_name);


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/user/deleteUsers")
    boolean delUser(@RequestParam("userId") String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    @RequestMapping(value = "/user/updatePassword")
    boolean updatePassword(@RequestParam("userId") String userId, @RequestParam("password") String password);

    /**
     * 修改用户
     *
     * @param userId
     * @param job
     * @param mobile
     * @param email
     * @param groups_id
     * @param dft_group
     * @return
     */
    @RequestMapping(value = "/user/updateUser")
    boolean updateUser(@RequestParam("userId")String userId, @RequestParam("job")String job, @RequestParam("mobile")String mobile, @RequestParam("email")String email, @RequestParam("groups_id")int[] groups_id, @RequestParam("dft_group")String dft_group);


}
