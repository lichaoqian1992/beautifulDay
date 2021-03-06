package com.manji.sheet.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.json.JFinalJson;
import com.manji.sheet.intercepter.AppIntercepter;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.model.bean.MessageEvaluate;
import com.manji.sheet.service.EvaluateService;
import com.manji.sheet.validator.evaluate.shopInterventionDetailsValidator;
import com.manji.sheet.validator.evaluate.shopInterventionValidator;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


@Before(AppIntercepter.class)
public class EvaluateController extends Controller {

    private static Logger logger = Logger.getLogger(EvaluateController.class);
    private EvaluateService evaluateservice=new EvaluateService();
    private static final String ERROR = "系统异常";
    private static final String DATAERROR = "参数异常";

    /**
     * 订单评论商家申请平台介入及补充资料
     */
    @Before(shopInterventionValidator.class)
    public void shopIntervention () {
        MessageEvaluate message=new MessageEvaluate();
        try{
            String content = getPara("content","");
            String orderId = getPara("orderId","");
            String pics = getPara("pics","");
            String source = getPara("source","");
            Account user = getSessionAttr("Account");
            String sheetId=null;

            if(!user.getUser_role_type().equals("Buyer")){
                sheetId=evaluateservice.sheetId(orderId,user);//判断订单是否申请纠纷
            }else{
                sheetId=evaluateservice.sheetIdUser(orderId);
            }

            if(sheetId==null){
                message=evaluateservice.shopIntervention(content,orderId,pics,source,user);//调用申请方法
            }else{
                message=evaluateservice.shopOrderCommentInterventionSubmit(content,pics,user,sheetId);//调用补充资料方法
            }
        }catch (Exception e){
            e.printStackTrace();
            message.setState(0);
            message.setMessage(ERROR);
        }

        logger(message); //记录日志

        String callback = getPara("callback","");
        if(!callback.equals("")){
            renderText(callback + "(" + JFinalJson.getJson().toJson(message).toString() + ")");
        }else{
            renderJson(message);
        }
    }

    /**
     * 订单评论纠纷详情
     */
    @Before(shopInterventionDetailsValidator.class)
    public void shopInterventionDetails(){
        MessageEvaluate message=new MessageEvaluate();

        try{
            String orderId = getPara("orderId","");
            Account user = getSessionAttr("Account");
            String sheetId="";
            if(!user.getUser_role_type().equals("Buyer")){
                sheetId=evaluateservice.sheetId(orderId,user);//判断订单是否申请纠纷
            }else{
                sheetId=evaluateservice.sheetIdUser(orderId);
            }

            if(sheetId!=null){
                message =evaluateservice.shopInterventionDetails(sheetId,user);
            }else{
                message.setState(0);
                message.setMessage(DATAERROR);
            }

        }catch (Exception e){
            e.printStackTrace();
            message.setState(0);
            message.setMessage(ERROR);
        }

        logger(message); //记录日志

        String callback = getPara("callback","");
        if(!callback.equals("")){
            renderText(callback + "(" + JFinalJson.getJson().toJson(message).toString() + ")");
        }else{
            renderJson(message);
        }
    }

    /**
     * logger
     */
    public void logger(MessageEvaluate message){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        logger.info("调用接口 : "+getRequest().getContextPath()+getRequest().getServletPath()+" ; 日志信息 ："+JFinalJson.getJson().toJson(message).toString()+"调用时间:"+df.format(new Date()));
    }
}
