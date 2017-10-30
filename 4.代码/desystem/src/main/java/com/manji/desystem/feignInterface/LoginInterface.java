package com.manji.desystem.feignInterface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.dao.account.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/10/13.
 */

@FeignClient(value = "uservice")
public interface LoginInterface {
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
    Account login(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("code") String code, @RequestParam("sign") String sign);

    /**
     * 校验用户有效性
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/user/logins")
    Account checkUser(@RequestParam("sessionId") String sessionId);

    /**
     * 查询所有部门
     * @return
     */
    @RequestMapping(value = "/dept/findAllDept")
    JSONArray findAllDept();

    /**
     * 根据姓名查询
     * @param realName
     * @return
     */
    @RequestMapping(value = "/user/findUserByName")
    JSONArray findByName(@RequestParam("name")String realName);

    /**
     * 查询处理人
     * @param userId
     * @param path
     * @return
     */
    @RequestMapping(value = "/user/findExamineUser")
    JSONArray findExaminePeople(@RequestParam("userId") String userId, @RequestParam("path") String path);

    /**
     * 通过部门查询全部用户信息
     * @param deptId
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @RequestMapping(value = "/dept/findUserByDeptId", method = RequestMethod.GET)
    JSONObject findUserByDeptId(@RequestParam("dept_id") int deptId, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("name") String name);

    /**
     * 根据用户id查询部门信息
     * @param personId
     * @return
     */
    @RequestMapping(value = "/dept/findDeptNameByUserId")
    JSONArray findByUserId(@RequestParam("userId") int personId);

}
