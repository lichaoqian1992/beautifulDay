package com.manji.tesystem.controller.api.account;

import com.manji.tesystem.common.result.BaseResult;
import com.manji.tesystem.interceptor.LoginAuth;
import com.manji.tesystem.common.enums.CodeEnum;
import com.manji.tesystem.common.exception.BusinessDealException;
import com.manji.tesystem.common.result.BaseObjectResult;
import com.manji.tesystem.feign.response.account.Account;
import com.manji.tesystem.service.AccountManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "/api-account", description = "帐户管理")
@RequestMapping(value="/account")
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired AccountManager accountManager;
    /**
     * 登录接口
     * @return
     */
    @ApiOperation(value="帐号登录")
    @PostMapping(value ="/login")
    public BaseObjectResult<Account> login(@RequestParam(required = true) String username,@RequestParam(required = true) String password ) throws Exception {
        BaseObjectResult<Account> baseResult = new BaseObjectResult<Account>(CodeEnum.SUCCESS.getCode(), "登录成功");
        Account jsonObject=accountManager.login(username,password);
        if(0!=jsonObject.getResult()){
            throw new BusinessDealException(jsonObject.getPrompt());
        }
        baseResult.setResult(jsonObject);
        return baseResult;
    }

    /**
     * 测试校验sessionid是否有效
     * @return
     */
    @LoginAuth
    @ApiOperation(value="测试校验sessionid是否有效")
    @PostMapping(value ="/test")
    public BaseResult test(@RequestHeader(required = false)String sessionId){
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "有效~~~~");
        return baseResult;
    }
}
