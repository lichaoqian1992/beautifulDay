package com.manji.cusystem.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.controller.BaseController;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.dao.common.Role;
import com.manji.cusystem.feign.UServiceFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 *
 * 校验登录的切面
 */
//@Aspect//描述为切面类
//@Configuration //spring-boot配置类
public class LoginAop {


    BaseController baseController = new BaseController();

    /**
     * 定义一个切入点.
     * 解释下：
     *
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    //@Pointcut("execution(* com.manji.cusystem.controller.business.ConversationController.*(..))")
    public void excudeService(){};

   // @Before("excudeService())")
    public BaseResult CheckLogin(){

        BaseResult result = new BaseResult();
        Account account = new Account();
        List<Role> roleList = new ArrayList<Role>();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String sessionId = request.getParameter("sessionId");

        /*if(sessionId == null || "".equals(sessionId)){
            result.setCode("404");
            result.setContent("参数sessionId不能为空");

        }else{
            System.out.println(sessionId);
            System.out.println("进入切面===================");
            JSONObject back = baseController.logins(sessionId);

            System.out.println(back+"===============");
            if(back.get("code").toString().equals("1000")) {

                //处理返回数据
                JSONObject user = (JSONObject) JSONObject.parse(back.getString("user"));
                account.setUserId(user.getString("id"));
                account.setUserName(user.getString("username"));
                account.setRealName(user.getString("vsername"));
                account.setMobile(user.getString("mobile"));
                account.setEmail(user.getString("email"));
                account.setJob(user.getString("job"));
                //处理角色信息
                JSONArray roles = (JSONArray) JSONArray.parse(back.getString("role"));
                for (int i = 0; i < roles.size(); i++) {
                    Role role = new Role();
                    JSONObject object = roles.getJSONObject(i);
                    role.setRoleName(object.getString("role_name"));
                    role.setDeptName(object.getString("dept_name"));
                    roleList.add(role);
                }
                account.setRole(roleList);
                result.setCode("200");
                result.setContent("校验通过");
                result.setResult(account);
            }else{
                result.setCode("404");
                result.setContent("登录已失效或sessionId不正确");
                return result;
            }
        }*/

        return result;
    }

}
