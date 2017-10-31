package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.errorcode.ErrorCodeEnum;
import com.manji.financesystem.requestParam.LoginSystemRequestParam;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import com.manji.financesystem.secondaryDomain.repository.UserRepository;
import com.manji.financesystem.utils.DecriptUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pudding on 2017-1-16.
 */
@RequestMapping
@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SystemlogRepository systemlogRepository;


    /**
     * 登路验证
     *
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "/loginSystem", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult loginSystem(LoginSystemRequestParam requestParam, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            //加密后的密码
            String password = DecriptUtil.SHA1(requestParam.getPassword());
            UsernamePasswordToken token = new UsernamePasswordToken(requestParam.getUsername(), password);
            subject.login(token);
            logger.info("sessionId={},sessionHost={},sessionTimeout={}", subject.getSession().getId(), subject.getSession().getHost(), subject.getSession().getTimeout());
            UserDO userDO = userRepository.modifyUserLoginInfo(requestParam.getUsername());
            request.getSession().setAttribute("user",userDO);
            //添加登录日志信息
            Systemlog log=new Systemlog();
            log.setUserId(userDO.getId());
            log.setUser_name(userDO.getUserName());
            log.setLog_info("账户:"+userDO.getUserName()+"在"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss").format(new Date())+"登录系统");
            String sussce=systemlogRepository.setSysteminfo(log);
            logger.info("系统日志录入"+sussce);
        } catch (Exception e) {
            return BaseResult.getFailResult(ErrorCodeEnum.LOGIN_ERROR.getCode(), ErrorCodeEnum.LOGIN_ERROR.getMessage());
        }
        return BaseResult.getSuccessResult("登录成功");
    }


}
