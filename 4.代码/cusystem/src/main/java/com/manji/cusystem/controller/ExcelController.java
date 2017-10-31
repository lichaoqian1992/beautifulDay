package com.manji.cusystem.controller;

import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.ConversationService;
import com.manji.cusystem.vo.conversation.AddConversationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/9/9.
 */
@RestController
public class ExcelController extends BaseController{

    @Autowired
    private ConversationService service;

    @RequestMapping("/toExcel")
    public ResponseEntity<InputStreamResource> toExcel(AddConversationVo vo, HttpServletRequest request) throws IOException {

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){

            Account user = (Account)result.getResult();
            String filePath = service.toExcel(vo,user);
            super.setPath(filePath);
        }
        return downLoad();
    }
}
