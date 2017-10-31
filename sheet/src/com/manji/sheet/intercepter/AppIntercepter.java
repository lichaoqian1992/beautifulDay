package com.manji.sheet.intercepter;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.bean.MessageEvaluate;
import com.manji.sheet.utils.GetAccountUtils;

public class AppIntercepter implements Interceptor{

    @Override
    public void intercept(Invocation inv) {
        //获取当前控制器

        Controller controller = inv.getController();

        controller.getResponse().setHeader("Access-Control-Allow-Origin","*");
        controller.getResponse().setHeader("Access-Control-Allow-Methods", "*");
        controller.getResponse().setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
        controller.getResponse().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
        controller.getResponse().setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        controller.getResponse().setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0")

        //先判断是否已经存入了
                Account sessAccount=controller.getSessionAttr("Account");//获取session里面的账户信息
                if (sessAccount==null){//还没有存放账户信息

                    String sessionid=controller.getPara("sessionId");
                    //获取用户信息
                    Account userinfo=GetAccountUtils.getAccount(sessionid);

                    if (userinfo.getUser_id()==null||userinfo.getUser_id().equals("")){
                        //没查到用户信息
                        String path=controller.getRequest().getRequestURI();
                        if (path.contains("shopApp")){
                            controller.render("/WEB-INF/shopApp/error.html");
                        }else if(path.contains("evaluate")){
                            MessageEvaluate message=new MessageEvaluate();
                            message.setState(0);
                            message.setMessage("sessionId异常");
                            controller.renderJson(message);
                        }else{
                            controller.render("/WEB-INF/app/error.html");
                        }
                     }else{
                         //将用户信息存入session
                        controller.setSessionAttr("Account",userinfo);
                        inv.invoke();
                        }
                 }else{
                	//判断一哈，当前传入的session的信息和之前的是否一样
                	 String sessionid=controller.getPara("sessionId");//入口都需要传入sessionId
                	 if(sessionid != null){
                		 Account userinfo2=GetAccountUtils.getAccount(sessionid);
                    	 if(!userinfo2.getUser_id().equals(sessAccount.getUser_id())){
                    		 controller.setSessionAttr("Account",userinfo2);
                    	 } 
                	 }
                    inv.invoke();
                }

    }



}
