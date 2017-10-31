package com.uservice.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uservice.Dto.Result;
import com.uservice.Dto.User;
import com.uservice.Service.IResultService;
import com.uservice.Service.IUserService;
import com.uservice.Util.MD5Utils;
import com.uservice.Util.ValidationUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-8-24.(YYR)
 */
@RequestMapping(value="/user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 注册用户接口
     * @param username
     * @param password
     * @param name
     * @param vsername
     * @param mobile
     * @param email
     * @return
     */
    @ApiOperation(value="注册账户")
    @RequestMapping(value = "/registerUser",method = RequestMethod.GET)
    @ResponseBody
    boolean reg(@RequestParam("username")String username,@RequestParam("password")String password,@RequestParam("name")String name,@RequestParam("job")String job,@RequestParam("vsername")String vsername,@RequestParam("mobile")String mobile,@RequestParam("email")String email,@RequestParam("role_id")int role_id){

        boolean isok=userService.regUser(username,password,name,job,vsername,mobile,email,role_id);//增加用户
        return isok;
    }

//    @ApiOperation(value="重置全部账户密码为111111")
//    @RequestMapping(value = "/updatePasswrodAll",method = RequestMethod.GET)
//    @ResponseBody
//    void PasswrodAll(){
//        userService.updatePassword();
//    }


    /**
     * 登录接口
     * @param username
     * @param password
     * @param code
     * @param sign
     * @return
     */
    @ApiOperation(value="登录")
    @RequestMapping(value ="/login",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject
    login(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("code") String code,@RequestParam("sign") String sign){

        JSONObject resultjsonObject=userService.login(username,password,code,sign);

        return resultjsonObject;
    }

    @ApiOperation(value="通过Id查询账户详细信息")
    @RequestMapping(value ="/getUserById",method = RequestMethod.GET)
    @ResponseBody
    JSONObject getUserById(@RequestParam("userId") int userId,@RequestParam("deptId") int deptId){

        return userService.getUserById(userId,deptId);
    }

    /**
     * 登录验证SessionId是否存在
     * @param sessionId
     * @return
     */
    @ApiOperation(value="登录验证")
    @RequestMapping(value ="/logins",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject
    logins(@RequestParam("sessionId") String sessionId){

        JSONObject jsonObject=userService.tonkey(sessionId);

        return jsonObject;
    }

    @ApiOperation(value="修改账户信息")
    @RequestMapping(value ="/updateUsersById",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateUsersById(@RequestParam("id")int id,@RequestParam("job")String job,@RequestParam("mobile")String mobile,@RequestParam("email")String email){

        return userService.updateUsersById(id,job,mobile,email);
    }



    /**
     * 通过账号密码获取账户信息
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value="通过账号密码获取账户信息")
    @RequestMapping(value ="/getUser",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUser(@RequestParam("username") String username,@RequestParam("password") String password){

        JSONObject jsonObject=userService.getUser(username,password);
        return jsonObject;
    }

    /**
     * 通过用户名字缩写模糊查询用户
     * @param name
     * @return
     */
    @ApiOperation(value="通过用户名字缩写模糊查询用户")
    @RequestMapping(value ="/findUserByName",method = RequestMethod.GET)
    @ResponseBody
    public  List<User>
    findUserByName(@RequestParam("name") String name){

        List<User> userList=userService.findUserByName(name);

        return userList;
    }



    /**
     * 修改密码
     * @param userId
     * @param password
     * @return
     */
    @ApiOperation(value="修改密码")
    @RequestMapping(value ="/updatePassword",method = RequestMethod.GET)
    @ResponseBody
    public boolean
    updatePassword(@RequestParam("userId") int userId,@RequestParam("password") String password){

        boolean isok= userService.updatePassword(password,userId);
        return isok;
    }


    @ApiOperation(value="修改账户绑定的角色")
    @RequestMapping(value ="/updateUser_Role",method = RequestMethod.GET)
    @ResponseBody
    public boolean updateUser_Role(@RequestParam("userId") int userId,@RequestParam("roleId") int roleId){

        boolean isok=userService.updateUser_Role(roleId,userId);
        return isok;
    }


    /***
     * 删除用户
     * @param userId
     * @return
     */
    @ApiOperation(value="删除用户")
    @RequestMapping(value ="/deleteUsers",method = RequestMethod.GET)
    @ResponseBody
    public boolean
    deleteUsers(@RequestParam("userId") int userId){

        boolean isok=userService.delectUsers(userId);
        return isok;
    }


    /**
     * 修改默认组
     * @param userId
     * @param groupId
     * @return
     */
    @ApiOperation(value="设置默认组")
    @RequestMapping(value ="/updateGroupUserdft",method = RequestMethod.GET)
    @ResponseBody
    public boolean
    updateGroupUserdft(@RequestParam("userId") int userId,@RequestParam("groupId") int groupId){

       boolean isok= userService.updateGroupUserdft(groupId,userId);
        return isok;
    }


    /**
     * 查询当前user默认小组下的全部审核人
     * @param userId
     * @param path
     * @return
     */
    @ApiOperation(value="查询当前账户默认小组下的全部审核人")
    @RequestMapping(value ="/findExamineUser",method = RequestMethod.GET)
    @ResponseBody
    public List<User> findExamineUser(@RequestParam("userId") int userId,@RequestParam("path") String path){

        List<User> userList=userService.findExamineUser(userId,path);

        return userList;
    }


}
