package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.feign.UServiceFeignService;
import com.manji.cusystem.service.SystemsettingsService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.MD5util;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-9-6.(系统设置业务逻辑层)(YYR)
 */
@Service
public class SystemsettingsServiceImpl implements SystemsettingsService{

    private Logger logger = Logger.getLogger(SystemsettingsServiceImpl.class);

    @Autowired
    private UServiceFeignService uServiceFeignService;

    private static final int deptId=5;//定义客户部门ID

    private static final String pjt_code="0001";//定义项目code

    BaseResult baseResult=new BaseResult();

    /**
     * 注册用户分配组
     * @param username
     * @param password
     * @param name
     * @param job
     * @param vsername
     * @param mobile
     * @param email
     * @param role_id
     * @param groups_id
     * @return
     */
    @Override
    @Transactional
    public BaseResult RegisteredUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id,int[] groups_id,int dft_group) {

        boolean userisok=uServiceFeignService.RegisteredUser(username, MD5util.getMD5(password),name,job,vsername,mobile,email,role_id);//注册用户

        if (userisok){//如果注册成功了开始绑定组

            JSONObject userjson=uServiceFeignService.getUser(username,MD5util.getMD5(password));//获取刚刚注册成功的账户

            boolean isgroup=true;
            //获取刚刚注册的账户
            for (int i=0;i<groups_id.length;i++){
                isgroup=uServiceFeignService.bindGroupUser(groups_id[i],userjson.getInteger("id")); //绑定组
                if (!isgroup){
                    break;
                }
            }
            if (!isgroup){//验证分组时是否发生错误
                baseResult.setCode("500");
                baseResult.setContent("绑定组时失败");
                return baseResult;
            }

            boolean isdft=uServiceFeignService.updateGroupUserdft(userjson.getInteger("id"),dft_group);//设置默认组
            if (!isdft){//验证设置默认组时是否发生错误
                baseResult.setCode("500");
                baseResult.setContent("设置默认组时失败");
                return baseResult;
            }

            baseResult.setCode("200");
            baseResult.setContent("注册成功");
        }else{//注册账户失败
            baseResult.setCode("500");
            baseResult.setContent("注册账号失败");
        }
        baseResult.setResult("");
        return baseResult;
    }

    /**
     * 查询全部角色
     * @return
     */
    @Override
    public BaseResult findRoleByDept() {
        try {
            JSONArray jsonArray=uServiceFeignService.findRoleByDept(deptId);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部角色失败");
        }
        return baseResult;
    }

    /**
     * 添加角色
     * @param rolename
     * @param menu_id
     * @return
     */
    @Override
    public BaseResult insertRole(String rolename,Integer[] menu_id) {
        try{
            int roleCount=uServiceFeignService.findRoleBydeptandnameCount(deptId,rolename);
            if (roleCount!=0){
                baseResult.setCode("500");
                baseResult.setContent("添加角色失败,已经有此角色了");
                return baseResult;
            }

            boolean isokrole=uServiceFeignService.insertRole(deptId,rolename);//添加角色
            if (!isokrole){//添加角色失败
                baseResult.setCode("500");
                baseResult.setContent("添加角色失败");
                return baseResult;
            }
            JSONObject role=uServiceFeignService.findRoleBydeptandname(deptId,rolename);//获取刚才添加的角色

            boolean isok=uServiceFeignService.insertRoleMenu(role.getInteger("id"),menu_id);//角色绑定权限
            if (!isok){
                baseResult.setCode("500");
                baseResult.setContent("绑定权限时失败");
                return baseResult;
            }
            baseResult.setCode("200");
            baseResult.setContent("添加角色成功");
            return baseResult;

        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加角色失败");
        }
        return baseResult;
    }

    /**
     * 查询全部权限
     * @return
     */
    @Override
    public BaseResult findMenus() {
        try {
            JSONArray jsonArray=uServiceFeignService.findMenus(pjt_code);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部权限失败");
        }
        return baseResult;
    }

    /**
     * 修改权限
     * @param roleId
     * @param menuId
     * @return
     */
    @Override
    public BaseResult updateRoleMenu(int roleId, int[] menuId) {

        List<Integer> insert=new ArrayList<Integer>();
        List<Integer> delete=new ArrayList<Integer>();

        for (int i=0;i<menuId.length;i++){

            if (menuId[i]<0){//如果是负数表示删除
                int id=0-menuId[i];
                delete.add(id);
            }else{
                if (uServiceFeignService.getRoleMenuCount(roleId,menuId[i])){
                    continue;//已经绑定了此数据
                }else{
                    insert.add(menuId[i]);
                }
            }
        }

        try{
            Integer[] darray = new Integer[delete.size()];
            Integer[] d=delete.toArray(darray);
            uServiceFeignService.deleteRoleMenu(roleId,d);//批量解绑

            Integer[] iarray = new Integer[insert.size()];
            Integer[] i=delete.toArray(iarray);
            uServiceFeignService.insertRoleMenu(roleId,i);//批量新增

            baseResult.setCode("200");
            baseResult.setContent("操作成功");
        }catch (Exception e){
            baseResult.setCode("500");
            baseResult.setContent("操作失败");
            e.printStackTrace();
        }
        return baseResult;
    }

    /**
     * 查询全部组
     * @return
     */
    @Override
    public BaseResult findGroups() {
        try {
            JSONArray jsonArray=uServiceFeignService.findGroupByDeptId(deptId);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部组失败");
        }
        return baseResult;
    }

    /**
     * 添加组
     * @param group_name
     * @return
     */
    @Override
    public BaseResult addGroup(String group_name) {
        boolean isok;
        try{
            isok=uServiceFeignService.insertGroup(deptId,group_name);
            if (isok){
                baseResult.setCode("200");
                baseResult.setContent("添加组成功");
            }else{
                baseResult.setCode("500");
                baseResult.setContent("添加组失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("添加组失败");
        }
        return baseResult;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public BaseResult delUser(String userId) {

        if(userId == null || "".equals(userId)){
            baseResult.setCode("404");
            baseResult.setContent("参数userId不能为空");
            baseResult.setResult("");
            return baseResult;
        }

        logger.info("=========开始执行delUser接口，调用时间："+new DatesUtils().time()+",参数userId="+userId);

        boolean flag = uServiceFeignService.delUser(userId);
        if(flag){
            baseResult.setCode("200");
            baseResult.setContent("删除成功");
            baseResult.setResult("");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("删除失败");
            baseResult.setResult("");
        }
        logger.info("=========结束开始执行delUser接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }

    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public BaseResult updatePassword(String userId, String password) {

        if(userId == null || "".equals(userId) || password == null || "".equals(password)){
            baseResult.setCode("404");
            baseResult.setContent("参数userId,password不能为空");
            baseResult.setResult("");
            return baseResult;
        }

        logger.info("=========开始执行updatePassword接口，调用时间："+new DatesUtils().time()+",参数userId="+userId+"password="+password);

        boolean flag = uServiceFeignService.updatePassword(userId,MD5util.getMD5(password));
        baseResult = detailResult(flag);
        logger.info("=========结束开始执行updatePassword接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }

    /**
     * 修改用户
     * @param userId
     * @param job
     * @param mobile
     * @param email
     * @param groups_id
     * @param dft_group
     * @return
     */
    @Override
    public BaseResult updateUser(int userId, String job, String mobile, String email, int[] groups_id, int dft_group) {

        logger.info("=========开始执行updatePassword接口，调用时间："+new DatesUtils().time()+",参数userId="+userId+job+mobile+email+groups_id+dft_group);

        //1.修改用户信息
        boolean userisok=uServiceFeignService.updateUsersById(userId,job,mobile,email);
        if (!userisok){
            baseResult.setCode("500");
            baseResult.setContent("修改账户失败");
            return baseResult;
        }
        //2.将此用户的绑定组全部先移出
        boolean delg=uServiceFeignService.deleteGroupUser(userId);
        if (!delg){
            baseResult.setCode("500");
            baseResult.setContent("移出组失败");
            return baseResult;
        }

        boolean isgroup=true;
        for (int i=0;i<groups_id.length;i++){
            isgroup=uServiceFeignService.bindGroupUser(groups_id[i],userId); //绑定组
            if (!isgroup){
                break;
            }
        }
        if (!isgroup){//验证分组时是否发生错误
            baseResult.setCode("500");
            baseResult.setContent("绑定组时失败");
            return baseResult;
        }

        boolean isdft=uServiceFeignService.updateGroupUserdft(userId,dft_group);//设置默认组
        if (!isdft){//验证设置默认组时是否发生错误
            baseResult.setCode("500");
            baseResult.setContent("设置默认组时失败");
            return baseResult;
        }

        baseResult.setCode("200");
        baseResult.setContent("修改账户成功");
        logger.info("=========结束开始执行updatePassword接口，结束调用时间："+new DatesUtils().time()+",返回值result="+JSONObject.toJSONString(baseResult));
        return baseResult;
    }



    /**
     * 查询客服中心下全部员工
     * @return
     */
    @Override
    public BaseResult findUserByDeptId() {
        try {
            JSONArray jsonArray=uServiceFeignService.findUserByDeptId(deptId);
            baseResult.setCode("200");
            baseResult.setContent("操作成功");
            baseResult.setResult(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
            baseResult.setCode("500");
            baseResult.setContent("查询全部客服失败");
            baseResult.setResult("异常类型："+e.getClass().getName().toString());
        }

        return baseResult;
    }

    /**
     * 统一处理返回结果
     * @param flag
     * @return
     */
    public BaseResult detailResult(boolean flag){

        if(flag){
            baseResult.setCode("200");
            baseResult.setContent("修改成功");
            baseResult.setResult("");
        }else{
            baseResult.setCode("500");
            baseResult.setContent("修改失败，请联系技术人员");
            baseResult.setResult("");
        }

        return baseResult;
    }


}
