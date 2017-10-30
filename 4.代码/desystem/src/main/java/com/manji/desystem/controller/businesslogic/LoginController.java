package com.manji.desystem.controller.businesslogic;

import com.manji.desystem.common.enums.ErrorCodeEnums;
import com.manji.desystem.common.exception.BusinessDealException;
import com.manji.desystem.common.result.BaseObjectResult;
import com.manji.desystem.common.util.SessionUtil;
import com.manji.desystem.dao.account.Account;
import com.manji.desystem.service.FeignLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/api-account", description = "帐户管理")
@RequestMapping(value = "/account")
public class LoginController {

    @Autowired
    private FeignLoginService feignLoginService;

    @ApiOperation(value = "帐号登录")
    @PostMapping(value = "/login")
    public BaseObjectResult<Account> login(@RequestParam(required = true) String username, @RequestParam(required = true) String password) throws Exception {
        BaseObjectResult<Account> baseResult = new BaseObjectResult<Account>(ErrorCodeEnums.Success.getCode(), "登录成功");
        Account jsonObject = feignLoginService.login(username, password);
        if (0 != jsonObject.getResult()) {
            throw new BusinessDealException(jsonObject.getPrompt());
        }
        baseResult.setResult(jsonObject);

        //创建登录session
        SessionUtil.httpsession().setAttribute("account", jsonObject);
        SessionUtil.httpsession().setMaxInactiveInterval(30 * 60);

        return baseResult;
    }
}