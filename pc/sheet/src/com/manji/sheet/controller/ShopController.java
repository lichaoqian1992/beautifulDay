package com.manji.sheet.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.manji.sheet.intercepter.AppIntercepter;
import com.manji.sheet.model.bean.Account;
import com.manji.sheet.service.ShopAppService;
import com.manji.sheet.utils.GetAccountUtils;
import com.manji.sheet.utils.PicUtils;
import com.manji.sheet.utils.WorkerNumberUtil;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudding on 2017-6-19.(YYR 商家APP控制器)
 */
@Before(AppIntercepter.class)
public class ShopController extends Controller {

    ShopAppService shopAppService=new ShopAppService();

    //进入举报管理页面
    public void ReportManagement(){

        render("report/report_manage.html");
    }




     /**
     * 进入发起举报页面
     */
    public void LaunchAreport(){
        //查询举报员工的全部类型
        List<Record> typeList=shopAppService.findSheetTypeByRMJ();
        setAttr("typeList",typeList);
        render("report/Ureport/report_center.html");
    }


    //发起工单
    public void saveSheet(){
        //获取账户
        Account account=getSessionAttr("Account");
        String path="";
        List<UploadFile> files=getFiles();
        if(files.size()>0){
            for(UploadFile x : files){
                path+= PicUtils.postPic(x.getFile())+",";
            }
        }
        if (!path.equals("")){
            path= path.substring(0,path.length()-1);
        }
        String short_title=getPara("short_title");
        String code_type=getPara("code_type");
        String descr=getPara("descr");
        if (descr.equals("")){
            descr="此商家没有填写举报信息!";
        }
        String status="3";//默认状态为处理中
        //查询商家信息
        Record shopinfo=shopAppService.findShopinfo(account.getUser_id());
        String sheet_no=WorkerNumberUtil.GeneratWorkerNumber(short_title); //生成工单号
        shopAppService.saveSheets(sheet_no,status,code_type,account.getUser_id(),shopinfo.getStr("name"),"1",shopinfo.getStr("mobile"),"04_01","1","0");//生成工单
        Record record=shopAppService.findSheetBySheetNo(sheet_no);//获取工单
        shopAppService.insertSheetInfo(record.get("id").toString(),descr,path,account);
        shopAppService.insertSheetbusiness(record.get("id").toString());
        shopAppService.insertdetail(record.get("id").toString());
        shopAppService.insertlog(record.get("id").toString(),descr,path);

        redirect("/shopApp/Myreport");
    }


    /**
     * 我被举报
     */
    public void IwasReported(){

        Account account=getSessionAttr("Account");//获取session里存入的用户
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (account!=null){
            List<Record> recordList=shopAppService.IwasReported(account.getUser_id());
            //把状态转换成String类型到前端判断
            for (int i=0;i<recordList.size();i++){
                String status=recordList.get(i).get("status").toString();
                String start_time=time.format(recordList.get(i).getDate("start_time"));
                recordList.get(i).set("status",status);
                recordList.get(i).set("start_time",start_time);
            }
            setAttr("recordList",recordList);
        }

        render("report/Ireport/i_reported.html");
    }


    /**
     * 我的举报
     */
    public void Myreport(){
        Account account=getSessionAttr("Account");//获取session里存入的用户
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (account!=null){
            List<Record> recordList=shopAppService.MyReported(account.getUser_id());
            //把状态转换成String类型到前端判断
            for (int i=0;i<recordList.size();i++){
                String status=recordList.get(i).get("status").toString();
                recordList.get(i).set("status",status);
                String start_time=time.format(recordList.get(i).getDate("start_time"));
                recordList.get(i).set("start_time",start_time);
            }
            setAttr("recordList",recordList);
        }
        render("report/Ureport/my_report.html");
    }

    /**
     * 点击单个工单时(查询对应工单详细信息)(我被举报)
     */
    public void findSheetInfo(){
        String id=getPara("id");
        //查询出工单写详细信息
        Record sheet=shopAppService.findSheetById(id);
        //查询正式质料
        Record info=shopAppService.findSInfo(id);
        //查询有无补充质料
        List<Record> sheetInfo=shopAppService.findSheetInfo(id);
        if (sheet.get("status").toString().equals("5")||sheet.get("status").toString().equals("4")){//如果此订单已完成查询出处理说明
            Record sheetDetail=shopAppService.findSheetDetail(id);//查询工单详细信息
            setAttr("sheetDetail",sheetDetail);
        }
        //把状态转换成String类型到前端判断
        String status=sheet.get("status").toString();
        sheet.set("status",status);

        //查询流程日志
        List<Record> log=shopAppService.findsheetLogs(id);

        //查询出举报类型
        Record type=shopAppService.findTypeBytypeCode(sheet.getStr("type_code"));

        //查询出举报对象
        Record object=shopAppService.findObjectBytypeshortTitle(type.getStr("short_title"));

        setAttr("object",object);
        setAttr("type",type);
        setAttr("logList",log);
        setAttr("info",info);
        setAttr("sheetInfo",sheetInfo);
        setAttr("sheet",sheet);

        render("report/Ireport/report_business_Completed.html");
    }

    /**
     * 点击单个工单时(查询对应工单详细信息)(我的举报)
     */
    public void findmySheetInfo(){
        String id=getPara("id");
        //查询出工单写详细信息
        Record sheet=shopAppService.findSheetById(id);
        //查询正式质料
        Record info=shopAppService.findSInfo(id);
        //查询有无补充质料
        List<Record> sheetInfo=shopAppService.findSheetInfo(id);
        if (sheet.get("status").toString().equals("5")||sheet.get("status").toString().equals("4")){//如果此订单已完成查询出处理说明
            Record sheetDetail=shopAppService.findSheetDetail(id);//查询工单详细信息
            setAttr("sheetDetail",sheetDetail);
        }
        //把状态转换成String类型到前端判断
        String status=sheet.get("status").toString();
        sheet.set("status",status);

        //查询流程日志
        List<Record> log=shopAppService.findsheetLog(id);

        //查询出举报类型
        Record type=shopAppService.findTypeBytypeCode(sheet.getStr("type_code"));

        //查询出举报对象
        Record object=shopAppService.findObjectBytypeshortTitle(type.getStr("short_title"));

        setAttr("object",object);
        setAttr("type",type);
        setAttr("logList",log);
        setAttr("info",info);
        setAttr("sheetInfo",sheetInfo);
        setAttr("sheet",sheet);

        render("report/Ureport/report_Completed.html");
    }


    /**
     * 进入补充质料界面
     */
    public void updateinfo(){
        String id=getPara("id");
        String imy=getPara("imy");
        setAttr("id",id);
        setAttr("imy",imy);
        render("report/update.html");
    }

    /**
     * 补充质料
     */
    public void insertinfo(){
        //获取账户
        Account account=getSessionAttr("Account");
        String path="";
        List<UploadFile> files=getFiles();
        if(files.size()>0){
            for(UploadFile x : files){
                path+= PicUtils.postPic(x.getFile())+",";
            }
        }
        if (!path.equals("")){
            path= path.substring(0,path.length()-1);
        }
        String sheet_id=getPara("sheet_id");
        String imy=getPara("imy");
        String descr=getPara("descr");
        if (descr.equals("")){
            descr="此商家没有填写举报信息!";
        }
        try{
            shopAppService.insertSheetInfo(sheet_id,descr,path,account);
        }catch (Exception e){

        }
        //判断是我的举报还是我被举报
        if (imy.equals("MY")){
            redirect("/shopApp/findmySheetInfo?id="+sheet_id+"");
        }else if (imy.equals("I")){
            redirect("/shopApp/findSheetInfo?id="+sheet_id+"");
        }else{
            redirect("/shopApp/finddispute?id="+sheet_id+"");
        }

    }


    //进入我要建议页面
    public void Proposal(){
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Account account=getSessionAttr("Account");
        List<Record> recordList=shopAppService.findAllopinion(account.getUser_id());
        //把状态转换成String类型到前端判断
        for (int i=0;i<recordList.size();i++){
            String status=recordList.get(i).get("status").toString();
            recordList.get(i).set("status",status);

            String start_time=time.format(recordList.get(i).getDate("start_time"));
            recordList.get(i).set("start_time",start_time);
        }
        setAttr("recordList",recordList);
        render("opinion/my_suggest.html");
    }

    //进入发起建议页面
    public void insertOpinion(){
        //查询建议的全部类型
        List<Record> typeList=shopAppService.findSheetTypeByFEB();
        setAttr("typeList",typeList);
        render("opinion/opinion_suggest.html");
    }

    //发起建议
    public void saveOpinion(){
        //获取账户
        Account account=getSessionAttr("Account");
        String path="";
        List<UploadFile> files=getFiles();
        if(files.size()>0){
            for(UploadFile x : files){
                path+= PicUtils.postPic(x.getFile())+",";
            }
        }
        if (!path.equals("")){
            path= path.substring(0,path.length()-1);
        }
        String short_title=getPara("short_title");
        String code_type=getPara("code_type");
        String descr=getPara("descr");
        if (descr.equals("")){
            descr="此商家没有填写建议信息!";
        }
        String status="3";//默认状态为处理中
        String sheet_no=WorkerNumberUtil.GeneratWorkerNumber(short_title); //生成工单号

        //查询商家信息
        Record shopinfo=shopAppService.findShopinfo(account.getUser_id());
        shopAppService.saveSheets(sheet_no,status,code_type,account.getUser_id(),shopinfo.getStr("name"),"1",shopinfo.getStr("mobile"),"04_02","1","0");//生成工单
        Record record=shopAppService.findSheetBySheetNo(sheet_no);//获取工单
        shopAppService.insertSheetInfo(record.get("id").toString(),descr,path,account);
        shopAppService.insertSheetbusiness(record.get("id").toString());
        shopAppService.insertdetailopinion(record.get("id").toString());
        shopAppService.insertlog(record.get("id").toString(),descr,path);

        redirect("/shopApp/Proposal");
    }


    /**
     * 点击单个建议时(查询对应工单详细信息)(我的建议)
     */
    public void findopinion(){
        String id=getPara("id");
        //查询出工单写详细信息
        Record sheet=shopAppService.findSheetById(id);
        //查询正式质料
        Record info=shopAppService.findSInfo(id);
        //查询有无补充质料
        List<Record> sheetInfo=shopAppService.findSheetInfo(id);
        if (sheet.get("status").toString().equals("5")){//如果此订单已完成查询出处理说明
            Record sheetDetail=shopAppService.findSheetDetail(id);//查询工单详细信息
            setAttr("sheetDetail",sheetDetail);
        }
        //把状态转换成String类型到前端判断
        String status=sheet.get("status").toString();
        sheet.set("status",status);

        //查询流程日志
        List<Record> log=shopAppService.findsheetLog(id);

        //查询出举报类型
        Record type=shopAppService.findTypeBytypeCode(sheet.getStr("type_code"));

        //查询出举报对象
        Record object=shopAppService.findObjectBytypeshortTitle(type.getStr("short_title"));

        setAttr("object",object);
        setAttr("type",type);
        setAttr("logList",log);
        setAttr("info",info);
        setAttr("sheetInfo",sheetInfo);
        setAttr("sheet",sheet);

        render("opinion/opinion_suggest_Completed.html");
    }

    //进入交易纠纷页面
    public void TransactionDisputes(){
        Account account=getSessionAttr("Account");
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询全部交易纠纷
        List<Record> disputeList= shopAppService.findAlldispute(account.getUser_id());
        for (int i=0;i<disputeList.size();i++){
            Record order=shopAppService.findOrderByid(disputeList.get(i).get("order_id").toString());
            disputeList.get(i).set("order_no",order.getStr("order_no"));
            disputeList.get(i).set("goods_title",order.getStr("goods_title"));
            disputeList.get(i).set("img_url",order.getStr("img_url"));
        }
        //把状态转换成String类型到前端判断
        for (int i=0;i<disputeList.size();i++){
            String status=disputeList.get(i).get("status").toString();
            disputeList.get(i).set("status",status);
            String start_time=time.format(disputeList.get(i).getDate("start_time"));
            disputeList.get(i).set("start_time",start_time);
        }
        //查询待处理交易纠纷
        List<Record> ddisputeList=shopAppService.findDdispute(account.getUser_id());
        for (int i=0;i<ddisputeList.size();i++){
            Record order=shopAppService.findOrderByid(ddisputeList.get(i).get("order_id").toString());
            ddisputeList.get(i).set("order_no",order.getStr("order_no"));
            ddisputeList.get(i).set("goods_title",order.getStr("goods_title"));
            ddisputeList.get(i).set("img_url",order.getStr("img_url"));
        }
        //把状态转换成String类型到前端判断
        for (int i=0;i<ddisputeList.size();i++){
            String status=ddisputeList.get(i).get("status").toString();
            ddisputeList.get(i).set("status",status);
            String start_time=time.format(ddisputeList.get(i).getDate("start_time"));
            ddisputeList.get(i).set("start_time",start_time);
        }
        //查询已完成交易纠纷
        List<Record> wdisputeList=shopAppService.findWdispute(account.getUser_id());
        for (int i=0;i<wdisputeList.size();i++){
            Record order=shopAppService.findOrderByid(wdisputeList.get(i).get("order_id").toString());
            wdisputeList.get(i).set("order_no",order.getStr("order_no"));
            wdisputeList.get(i).set("goods_title",order.getStr("goods_title"));
            wdisputeList.get(i).set("img_url",order.getStr("img_url"));
        }
        //把状态转换成String类型到前端判断
        for (int i=0;i<wdisputeList.size();i++){
            String status=wdisputeList.get(i).get("status").toString();
            wdisputeList.get(i).set("status",status);
            String start_time=time.format(wdisputeList.get(i).getDate("start_time"));
            wdisputeList.get(i).set("start_time",start_time);
        }
        setAttr("disputeList",disputeList);
        setAttr("ddisputeList",ddisputeList);
        setAttr("wdisputeList",wdisputeList);

        render("dispute/dispute_manage.html");
    }

    /**
     * 点击单个交易纠纷时(查询对应工单详细信息)(交易纠纷)
     */
    public void finddispute(){
        String id=getPara("id");
        //查询出工单写详细信息
        Record sheet=shopAppService.findSheetById(id);
        //查询正式质料
        Record info=shopAppService.findSInfo(id);
        //查询有无补充质料
        List<Record> sheetInfo=shopAppService.findSheetInfo(id);
        if (sheet.get("status").toString().equals("5")||sheet.get("status").toString().equals("4")){//如果此订单已完成查询出处理说明
            Record sheetDetail=shopAppService.findSheetDetail(id);//查询工单详细信息
            setAttr("sheetDetail",sheetDetail);
        }
        //把状态转换成String类型到前端判断
        String status=sheet.get("status").toString();
        sheet.set("status",status);
        //查出对方用户头像,昵称(用于联系用户)
        Record Busers=shopAppService.findusers(info.get("submitter_id").toString());

        if (Busers.getStr("avatar")==null){
            info.set("avatar","");
        }else{
            info.set("avatar",Busers.getStr("avatar"));
        }
        info.set("nick_name",Busers.getStr("nick_name"));

        //查询流程日志
        List<Record> log=shopAppService.findsheetLogs(id);

        //查询出举报类型
        Record type=shopAppService.findTypeBytypeCode(sheet.getStr("type_code"));

        //查询出举报对象
        Record object=shopAppService.findObjectBytypeshortTitle(type.getStr("short_title"));

        //查询出用户详细信息
        Record b=shopAppService.findBuiness(id);
        Record users=shopAppService.findUsersInfoByOrderId(b.get("order_id").toString());
        
        Record detail = Db.findFirst("select * from dt_sheet_detail where sheet_id=?",id);

        //查询出退单信息
        Record backinfo=shopAppService.findbackinfoByOrderId(b.get("order_id").toString());
        //把状态转换成String类型到前端判断
        if (backinfo!=null){
            String backinfostatus=backinfo.get("status").toString();
            String back_goods=backinfo.get("back_goods").toString();
            backinfo.set("status",backinfostatus);
            backinfo.set("back_goods",back_goods);
        }

        setAttr("backinfo",backinfo);
        setAttr("users",users);
        setAttr("object",object);
        setAttr("type",type);
        setAttr("logList",log);
        setAttr("info",info);
        setAttr("sheetInfo",sheetInfo);
        setAttr("sheet",sheet);
        setAttr("detail", detail);
        render("dispute/trade_dispute_Completed.html");
    }

    //跳转到搜索页面
    public void search(){
        render("dispute/search.html");
    }


    //搜索交易纠纷
    public void findsheetsByorderNo(){
        Account account=getSessionAttr("Account");
        String order_no=getPara("order_no");

        //查询全部交易纠纷
        List<Record> disputeList= shopAppService.findsheetsByorderNo(order_no,account.getUser_id());

        for (int i=0;i<disputeList.size();i++){
            Record order=shopAppService.findOrderByid(disputeList.get(i).get("order_id").toString());
            disputeList.get(i).set("order_no",order.getStr("order_no"));
            disputeList.get(i).set("goods_title",order.getStr("goods_title"));
            disputeList.get(i).set("img_url",order.getStr("img_url"));
        }
        //把状态转换成String类型到前端判断
        for (int i=0;i<disputeList.size();i++){
            String status=disputeList.get(i).get("status").toString();

            disputeList.get(i).set("sus",status);
        }
        renderJson(disputeList);
    }

}
