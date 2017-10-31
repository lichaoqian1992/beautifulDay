package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.requestParam.SystemPushRequestParam;
import com.manji.financesystem.requestParam.UserAndRoleRequestParam;
import com.manji.financesystem.requestParam.UserInfoRequestParam;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.SystemPushDO;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.entity.extra.UserAndRoleDO;
import com.manji.financesystem.secondaryDomain.entity.extra.UserAndRoleInfoDO;
import com.manji.financesystem.secondaryDomain.repository.SystemPushRepository;
import com.manji.financesystem.service.SystemPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/4.
 */
@RequestMapping("/systemPush")
@Controller
public class SystemPushController {

    @Autowired
    private SystemPushRepository systemPushRepository;

    @Autowired
    private SystemPushService systemPushService;

    @RequestMapping("/systemPush.html")
    public String systemPush(){

        return "system/systemPush";
    }
    /**
     * 权限管理页面
     */
    @RequestMapping("/privilegeManagement.html")
    public String privilegeManagement(){

        return "system/privilegeManagement";
    }
    @RequestMapping("/userInfo.html")
    public String systemUserInfo(){

        return "system/userInfo";
    }
    @RequestMapping("/test.html")
    public String systemTest(){

        return "system/test";
    }

    /**
     * 查询要发送短信的联系人的先关信息
     * @return
     */
    @RequestMapping("/getSystemInfo")
    @ResponseBody
    public BaseResult getSystemInfo(){

        BaseQueryResult<SystemPushDO> baseQueryResult=new BaseQueryResult<SystemPushDO>();
        List<SystemPushDO> list = systemPushRepository.getSystemInfo();
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping("/saveSystemInfo")
    @ResponseBody
    public BaseResult saveSystemInfo(SystemPushRequestParam param){
        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.saveSystemPush(param);
        if(status == "SUCCESS"){
            baseQueryResult.setSuccessResult("SUCCESS");
        }else if(status == "EXIST"){
            baseQueryResult.setSuccessResult("EXIST");
        }else if(status == "NOT EXIST"){
            baseQueryResult.setSuccessResult("NOT EXIST");
        }else{
            baseQueryResult.setSuccessResult("ERROR");
        }
        return baseQueryResult;
    }

    /**
     * 修改用户信息
     * @return
     */
    @RequestMapping("/modifySystemInfo")
    @ResponseBody
    public BaseResult modifySystemInfo(SystemPushRequestParam param){
        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.modifySystemInfo(param);
        if(status == "SUCCESS"){
            baseQueryResult.setSuccessResult("SUCCESS");
        }else{
            baseQueryResult.setSuccessResult("ERROR");
        }
        return baseQueryResult;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping("/deleteSystemInfo")
    @ResponseBody
    public BaseResult deleteSystemInfo(SystemPushRequestParam param){

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.deleteSystemInfo(param);
        if(status == "SUCCESS"){
            baseQueryResult.setSuccessResult("SUCCESS");
        }else{
            baseQueryResult.setSuccessResult("ERROR");
        }
        return baseQueryResult;
    }

    /**
     * 推送短信消息
     * @return
     */
    @RequestMapping("/sendMessage")
    @ResponseBody
    public BaseResult sendMessage(SystemPushRequestParam param) throws ParseException, UnknownHostException {
        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        List<UserSmsLogDO> list = new ArrayList<UserSmsLogDO>();
        InetAddress id=null;
        Date date = new Date();
        if(param != null){
            //处理参数
            String[] userId = param.getUserIdList().split(",");
            String[] userRoleType = param.getUserRoleType().split(",");
            String[] userRoleValue = param.getUserRoleValue().split(",");
            String[] mobile = param.getMobile().split(",");
            for(int i=0;i<userId.length;i++){
                UserSmsLogDO userSmsLogDO = new UserSmsLogDO();
                userSmsLogDO.setUserId(Integer.parseInt(userId[i]));
                userSmsLogDO.setUserRoleType(userRoleType[i]);
                userSmsLogDO.setUserRoleValue(Integer.parseInt(userRoleValue[i]));
                userSmsLogDO.setType("平台交易信息推送");
                userSmsLogDO.setUserIp(id.getLocalHost().getHostAddress());
                userSmsLogDO.setContent(param.getContent());
                userSmsLogDO.setStatus(0);
                userSmsLogDO.setMobile(mobile[i]);
                userSmsLogDO.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                userSmsLogDO.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                list.add(userSmsLogDO);
            }
        }
        systemPushRepository.addInfo(list);

        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }

    /**
     * 进入显示今日平台交易流水量
     * @return
     */
    @RequestMapping("/showMessage")
    @ResponseBody
    public BaseResult showMessage() throws ParseException {

        ObjectBaseResult obj = new ObjectBaseResult();
        String message = systemPushService.getMessage();
        obj.setSuccessResult(message);
        return obj;
    }

    /**
     * 根据登录人查询用户信息
     * @return
     */
    @RequestMapping("/queryUserInfo")
    @ResponseBody
    public BaseResult queryUserInfo(String userName){

        BaseQueryResult<UserDO> result = new BaseQueryResult<UserDO>();
        List<UserDO> list = systemPushRepository.queryUserInfo(userName);
        result.setResultList(list);
        result.setSuccessResult("SUCCESS");
        return result;
    }

    /**
     * 新增用户
     * @param param
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public BaseResult addUser(UserInfoRequestParam param){

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.saveUser(param);

        baseQueryResult.setSuccessResult(status);
        return baseQueryResult;
    }

    /**
     * 修改用户
     * @param param
     * @return
     */
    @RequestMapping("/modifyUser")
    @ResponseBody
    public BaseResult modifyUser(UserInfoRequestParam param){

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.modifyUser(param);

        baseQueryResult.setSuccessResult(status);
        return baseQueryResult;
    }

    /**
     * 重置用户密码
     * @param param
     * @return
     */
    @RequestMapping("/resetUserPassword")
    @ResponseBody
    public BaseResult resetUserPassword(UserInfoRequestParam param){

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        String status = systemPushRepository.resetUserPassword(param);

        baseQueryResult.setSuccessResult(status);
        return baseQueryResult;
    }
    /**
     * 获取角色值
     * @return
     */
    @RequestMapping("/getRole")
    @ResponseBody
    public BaseResult getRole(){
        BaseQueryResult<RechargeConfigDO> baseQueryResult=new BaseQueryResult<RechargeConfigDO>();
        List<RechargeConfigDO> list = systemPushRepository.getRole();
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;

    }

    /**
     * 查询页面的权限信息
     * @param
     * @return
     */
    @RequestMapping("/queryJurisdiction")
    @ResponseBody
    public BaseResult queryJurisdiction(){

        BaseQueryResult<UserAndRoleDO> result = new BaseQueryResult<UserAndRoleDO>();
        List<UserAndRoleDO> list = systemPushRepository.queryUserAndRole();
        result.setResultList(list);
        result.setSuccessResult("SUCCESS");
        return result;
    }

    /**
     * 查询用户的职位、权限信息
     * @return
     */
    @RequestMapping("/queryUserAndRoleInfo")
    @ResponseBody
    public BaseResult queryUserAndRoleInfo(){
        BaseQueryResult<UserAndRoleInfoDO> result = new BaseQueryResult<UserAndRoleInfoDO>();
        List<UserAndRoleInfoDO> list = systemPushRepository.queryUserAndRoleInfo();
        result.setResultList(list);
        result.setSuccessResult("SUCCESS");
        return result;
    }

    /**
     * 权限绑定
     * @return
     */
    @RequestMapping("/binding")
    @ResponseBody
    public BaseResult binding(UserAndRoleRequestParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        String status = systemPushRepository.binding(param);
        obj.setSuccessResult(status);
        return obj;
    }

    /**
     * 解除绑定
     * @param param
     * @return
     */
    @RequestMapping("/unBinding")
    @ResponseBody
    public BaseResult unBinding(UserAndRoleRequestParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        String status = systemPushRepository.unBinding(param);
        obj.setSuccessResult(status);
        return obj;
    }
}
