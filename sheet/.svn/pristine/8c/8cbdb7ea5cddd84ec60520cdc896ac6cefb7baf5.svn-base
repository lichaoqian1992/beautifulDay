package com.manji.sheet.controller;

import com.jfinal.core.Controller;
import com.manji.sheet.model.bean.Account;

/**
 * Created by pudding on 2017-7-12.
 */
public class ErrController extends Controller{

    public void PC(){
        String code=getPara("code");
        if (code.equals("404")){
            render("html/common/404.html");
        }else{
            render("html/common/500.html");
        }
    }


    public void APP(){
        Account account=getSessionAttr("Account");
            if (account.getUser_role_type().equals("Buyer")){
                render("app/error.html");
            }else{
                render("shopApp/error.html");
        }
    }

}
