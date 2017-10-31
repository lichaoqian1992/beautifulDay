package com.uservice.Service;

import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Group;
import com.uservice.Dto.User;

import java.util.List;

/**
 * Created by pudding on 2017-8-24.(YYR)
 */
public interface IUserService {

    boolean regUser(String username,String password,String name,String job,String vsername,String mobile,String email,int role_id);

    JSONObject login(String username,String password,String code,String sign);

    boolean selectUserByUsername(String username);

    boolean selectUserByUsernameAndPassword(String username,String password);

    boolean selectpjtCodeByUsernameAndPassword(String username,String password,String code);

    User findUserByusernameAndpassword(String username,String password,String code);

    String findRolenameAndDeptname(int user_id);

    List<User> findUserByName(String name);

    boolean updatePassword(String password,int userId);

    boolean delectUsers(int userId);

     boolean updateUsersById(int id,String job,String mobile,String email);

    String findGroupByUserId(int userId);

    boolean updateGroupUserdft(int groupId,int userId);

    List<User> findExamineUser(int userId,String path);

    JSONObject tonkey(String tonkey);

    JSONObject getUser(String username,String password);
}
