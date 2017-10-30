package com.uservice.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.*;
import com.uservice.Mapper.*;
import com.uservice.Service.IOperationlogService;
import com.uservice.Service.IResultService;
import com.uservice.Service.IUserService;
import com.uservice.Util.MD5Utils;
import com.uservice.Util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by pudding on 2017-8-24.(YYR)
 */

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;//用户

    @Autowired
    private RoleMapper roleMapper;//角色

    @Autowired
    private IOperationlogService operationlogService;//日志

    @Autowired
    private ProjectMapper projectMapper;//项目

    @Autowired
    private IResultService resultService;//登录返回提示

    @Autowired
    private MenuMapper menuMapper;//权限

    @Autowired
    private GroupMapper groupMapper;//组

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 注册账户
     * @param username
     * @param password
     * @param name
     * @param vsername
     * @param job
     * @param mobile
     * @param email
     * @return
     */
    @Override
    public boolean regUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id) {
        int count=userMapper.selectUserByUsername(username);//验证登录名是否存在
        if (count>0){
            return false;//如果存在直接注册失败
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(MD5Utils.getMD5(username+password));//MD5加密
        user.setName(name);
        user.setVsername(vsername);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setAdd_time(new Date());
        user.setJob(job);

        Boolean flag=true;
        try {
            flag= userMapper.insertUsers(user);//增加用户
            if (flag){//如果增加成功
                User newUser=userMapper.findUserByusernameAndpassword(user.getUsername(),user.getPassword());
                boolean f=userMapper.insertUser_Role(newUser.getId(),role_id);//绑定角色
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return flag;
    }

    /**
     * 登录校验
     * @param username
     * @param password
     * @param code
     * @param sign
     * @return
     */
    public JSONObject login(String username,String password,String code,String sign){
        boolean isLogin=false;
        JSONObject jsonObject=JSONObject.parseObject("");
        List<MenuJson> muenJson=new ArrayList<MenuJson>();
        List<RoleDepartment> roleDept=new ArrayList<RoleDepartment>();
        List<Group> group=new ArrayList<Group>();
        User user=new User();
        Result result=null;
        List<Object> list=new ArrayList<Object>();
        list.add(username);
        list.add(password);
        list.add(code);

        Boolean signisok= ValidationUtils.Validation(list,sign);//验证秘钥是否正确

        if (signisok){//秘钥正确
            boolean usernameisok=selectUserByUsername(username);//验证账户是否存在

            if (usernameisok){
                boolean passwordisok=selectUserByUsernameAndPassword(username,password);//验证密码是否正确

                if (passwordisok){
                    boolean pjtcodeisok=selectpjtCodeByUsernameAndPassword(username,password,code);//验证是否有此项目权限

                    if (pjtcodeisok){//全部校验通过(登录成功)
                        isLogin=true;
                        result=resultService.findResultByid("1");//返回成功信息

                         user=findUserByusernameAndpassword(username,password,code);//开始处理信息


                        //开始查询此用户角色权限
                         muenJson=findMenuByUserId(user.getId(),1,code);//1:表示查询页面

                        //查询此用户的角色和部门
                        roleDept=findRolenameAndDeptname(user.getId());//账户所属部门和角色

                        //查询此用户所在组
                        group=findGroupByUserId(user.getId());//账户所在组


                    }else{//没有此项目权限
                        result=resultService.findResultByid("4");
                    }

                }else {//密码错误
                     result=resultService.findResultByid("3");
                }

            }else{//账户不存在
                result=resultService.findResultByid("2");
            }

        }else {//秘钥错误
            result=resultService.findResultByid("5");
        }

        String j= JSONObject.toJSONString(result);


        jsonObject=JSONObject.parseObject(j);
        jsonObject.put("menu",muenJson);//存储页面权限
        jsonObject.put("role",roleDept);//存储角色和部门
        jsonObject.put("user",user);//存储用户信息
        jsonObject.put("group",group);//存储用户所在组
        String tonkey="";
        if (isLogin){//验证登录是否成功
            //存储tonkey到redis
            tonkey= MD5Utils.getMD5(jsonObject.toJSONString()+new Date().toString());
            stringRedisTemplate.opsForValue().set(tonkey, jsonObject.toJSONString(),1800, TimeUnit.SECONDS);
        }
        jsonObject.put("sessionId",tonkey);
        return jsonObject;
    }

    /**
     * 验证tonkey
     * @param tonkey
     * @return
     */
    @Override
    public JSONObject tonkey(String tonkey) {
        Result result=null;
        JSONObject jsonObject=JSONObject.parseObject("");
        boolean exists=redisTemplate.hasKey(tonkey);//验证tonkey是否失效
        if(exists){
            stringRedisTemplate.expire(tonkey,1800, TimeUnit.SECONDS);//延迟寿命
            String json=stringRedisTemplate.opsForValue().get(tonkey);
            jsonObject=JSONObject.parseObject(json);
        }else{
            result=resultService.findResultByid("6");
            String j= JSONObject.toJSONString(result);
            jsonObject=JSONObject.parseObject(j);
        }

        return jsonObject;
    }


    /**
     * 验证账户是否存在
     * @param username
     * @return
     */
    @Override
    public boolean selectUserByUsername(String username) {
        Boolean isok=false;
        try {
            int count=userMapper.selectUserByUsername(username);
            if (count>0){
                isok=true;
            }
        }catch (Exception e){
            return false;
        }
        return isok;
    }

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean selectUserByUsernameAndPassword(String username, String password) {
        Boolean isok=false;
        try {
            int count=userMapper.selectUserByUsernameAndPassword(username,MD5Utils.getMD5(username+password));
            if (count>0){
                isok=true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 验证是否有项目权限
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean selectpjtCodeByUsernameAndPassword(String username, String password,String code) {
        Boolean isok=false;

        List<String> pjtcodeList=userMapper.selectpjtCodeByUsernameAndPassword(username,MD5Utils.getMD5(username+password));//查询此用户拥有的项目code

        for (int i=0;i<pjtcodeList.size();i++){
            if (pjtcodeList.get(i).equals(code)){
                isok=true;
            }
        }
        return isok;
    }

    /**
     * 处理用户信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByusernameAndpassword(String username, String password,String code) {
        //修改登录时间
        Date logintime=new Date();
        User user=userMapper.findUserByusernameAndpassword(username,MD5Utils.getMD5(username+password));
        user.setCount(user.getCount()+1);//登录次数加一
        user.setLast_login_time(logintime);
        if (user.getLogin_time()==null){//如果以前没有登录过
            user.setLogin_time(logintime);
        }
        Boolean upteisok=userMapper.updateUsersById(user);//修改用户登录次数

        Project project=projectMapper.findProjectByCode(code);//通过code查询对应系统对象

        Boolean insertlogisok=operationlogService.insertOperationlog(1,user.getVsername()+"登录了"+project.getPjt_name()+"。",user.getId(),logintime,null);//添加日志

        return user;
    }

    /**
     * 通过账号密码获取User信息
     * @param userId
     * @return
     */
    public JSONObject getUserById(int userId,int deptId){

        User user=userMapper.findUserById(userId);
        Role role=roleMapper.findRoleByUserId(userId,deptId);
        List<Group> groupList=groupMapper.findGroupByUserId(userId);
        List<Group> groupdft=groupMapper.findGroupByUserIdDft(userId);

        String j=JSONObject.toJSONString(user);//处理json
        JSONObject jsonObject=JSONObject.parseObject(j);
        jsonObject.put("role",role);
        jsonObject.put("groupList",groupList);
        jsonObject.put("groupdft",groupdft);
        return jsonObject;
    }
    /**
     * 通过账号密码获取User信息
     * @param username
     * @param password
     * @return
     */
    public JSONObject getUser(String username, String password){

        User user=userMapper.findUserByusernameAndpassword(username,MD5Utils.getMD5(username+password));//查询用户信息

        String j=JSONObject.toJSONString(user);//处理json
        JSONObject jsonObject=JSONObject.parseObject(j);
        return jsonObject;
    }




    /**
     * 查询用户本项目下的查询页面权限(登录成功后)
     * @param user_id
     * @param type_id
     * @param pjt_code
     * @return
     */
     public List<MenuJson> findMenuByUserId(int user_id,int type_id,String pjt_code){

         List<MenuJson> menuJsonList=menuMapper.findMenuByUserId(user_id,type_id,pjt_code);

         return menuJsonList;
     }


    /**
     * 查询部门名称和角色名称(登录成功后)
     * @param user_id
     * @return
     */
    @Override
    public List<RoleDepartment> findRolenameAndDeptname(int user_id) {
        List<RoleDepartment> roleDepartmentList=userMapper.findRolenameAndDeptname(user_id);

        return roleDepartmentList;
    }

    /**
     * 查询用户所在组(登录成功后)
     * @param userId
     * @return
     */
    @Override
    public  List<Group> findGroupByUserId(int userId) {
        List<Group> groupList=groupMapper.findGroupByUserId(userId);

        return groupList;
    }


    /**
     * 通过用户名字缩写模糊查询用户
     * @param name
     * @return
     */
    @Override
    public List<User> findUserByName(String name){
        name=name+"%";//用户like查询
        List<User> userList=userMapper.findUserByName(name);
        return userList;
    }

    /**
     * 修改密码
     * @param password
     * @param userId
     * @return
     */
    @Override
    public boolean updatePassword(String password,int userId) {
        boolean isok=false;
        try {
            User user=userMapper.findUserById(userId);
            String lpassword=user.getPassword();//获取原密码
            if (user!=null){
                user.setPassword(MD5Utils.getMD5(user.getUsername()+password));//加密新密码
                isok=userMapper.updateUsersById(user);//修改密码
                if (isok){
                    Boolean insertlogisok=operationlogService.insertOperationlog(3,user.getVsername()+"修改了密码。",user.getId(),new Date(),lpassword);//添加日志
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }


    /**
     *修改用户信息
     * @return
     */
    public boolean updateUsersById(int id,String job,String mobile,String email){
        boolean isok;
        User user=userMapper.findUserById(id);
        user.setJob(job);
        user.setMobile(mobile);
        user.setEmail(email);
        try{
            isok=userMapper.updateUsersById(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Override
    public boolean delectUsers(int userId) {
        boolean isok=false;
        try {
            groupMapper.deleteGroupUser(userId);//解绑组
            userMapper.delectUserRole(userId);//解绑角色
            isok=userMapper.delectUsersById(userId);//删除用户
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return isok;
    }

    /**
     * 修改默认审核组
     * @param groupId
     * @param userId
     * @return
     */
    @Override
    public boolean updateGroupUserdft(int groupId, int userId) {
        boolean isok;
        try {
            GroupUser GroupUserId=groupMapper.findGroupUser(groupId,userId);//查询组和用户关联表id
            isok=groupMapper.SetDefaultGroup(GroupUserId.getId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }

    /**
     * 查询此用户组下的全部审核人
     * @param userId
     * @param path
     * @return
     */
    @Override
    public List<User> findExamineUser(int userId, String path) {
        List<User> users=new ArrayList<User>();
        List<Group> groupList=groupMapper.findGroupByUserIdDft(userId);//查询此用户的默认组
        //考虑到万一会有多个默认组(实际只有一个)
        for (int i=0;i<groupList.size();i++){
            List<User> userList=groupMapper.findshUserByGroup(groupList.get(i).getId(),path);//查询出审核人
            users.addAll(userList);
        }
        return users;
    }


    /**
     * 系统初始全部默认密码为111111(重置全部账户密码)
     */
    @Override
    public void updatePassword() {

        List<User> userList=userMapper.findAllUser();
        for (int i=0;i<userList.size();i++){

            String username=userList.get(i).getUsername();
            String password=MD5Utils.getMD5("111111");
            int id=userList.get(i).getId();

            password=MD5Utils.getMD5(username+password);
            userMapper.updatePassword(password,id);
        }

    }

    /**
     * 修改账户绑定的角色
     * @param role_id
     * @param user_id
     * @return
     */
    @Override
    public boolean updateUser_Role(int role_id, int user_id) {
        boolean isok;
        try{
            isok=userMapper.updateUser_Role(role_id,user_id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return isok;
    }


}
