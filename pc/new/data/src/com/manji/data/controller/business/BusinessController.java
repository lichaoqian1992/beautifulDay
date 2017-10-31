package com.manji.data.controller.business;


import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.service.business.BusinessService;
import com.manji.data.utils.ExcelUtils;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

public class BusinessController extends Controller {

    private BusinessService businessService = new BusinessService();
    private int pageSize = 20;

    /**
     * 业务员日志页面
     */
    public void businessLogView() {
        render("businessLog.html");
    }


    /**
     * 商品筛选页面
     */
    public void shopScreenView() {
        List<Record> articleCategoryOne = businessService.articleCategoryOne();
        setAttr("articleCategoryOne", articleCategoryOne);
        render("shopScreen.html");
    }

    /**
     * 商家信息
     */
    public void shopInfoView() {
        List<Record> shopArticleAll = businessService.shopArticleAll();
        setAttr("shopArticleAll", shopArticleAll);
        render("shopInfo.html");
    }

    /**
     * 商家信息(查看详情数据接口)
     */
    public void shopInfoDetailsView() {

        int userId = getParaToInt("user_id", 1);

        Map<String, Object> shopInfoDetails = businessService.shopInfoDetails(userId);

        Record shopDetails = (Record) shopInfoDetails.get("shopDetails");
        List<Record> shopBusinessList = (List<Record>) shopInfoDetails.get("shopBusinessList");
        Record shopCompany = (Record) shopInfoDetails.get("shopCompany");
        Record enclosure = (Record) shopInfoDetails.get("shopEnclosure");


        setAttr("shopDetails", shopDetails);
        setAttr("shopBusinessList", shopBusinessList);
        setAttr("shopCompany", shopCompany);
        setAttr("enclosure", enclosure);

        render("shopDetails.html");
    }

    /**
     * 业务员日志数据查询
     */
    public void businessLogData() {

        int pageNumber = getParaToInt("pageNumber", 1);
        String businessName = getPara("businessName", "");
        String startTime = getPara("startTime", "");
        String endTime = getPara("endTime", "");

        Page<Record> businessLog = businessService.businessLogData(pageNumber, pageSize, businessName, startTime, endTime);
        renderJson(businessLog);
    }

    /**
     * 查询级数
     */
    public void levelData() {
        int screenId = getParaToInt("screenId", 0);

        List<Record> levelData = businessService.levelData(screenId);
        renderJson(levelData);
    }

    /**
     * 商品筛选数据查询
     */
    public void shopScreenData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int levelOne = getParaToInt("levelOne", 0);
        int levelTow = getParaToInt("levelTow", 0);
        int levelThere = getParaToInt("levelThere", 0);
        Page<Record> articleCategory = businessService.articleCategoryAll(pageNumber, pageSize, levelOne, levelTow, levelThere);
        renderJson(articleCategory);
    }


    /**
     * 商家信息(列表页数据)
     */
    public void shopInfoData() {

        int pageNumber = getParaToInt("pageNumber", 1);
        String userName = getPara("userName", "");
        String nickName = getPara("nickName", "");
        String startTime = getPara("startTime", "");
        String endTime = getPara("endTime", "");
        String mobile = getPara("mobile", "");
        String shopName = getPara("shopName", "");
        String screen = getPara("screen", "");


        Map<String, Object> shopInfoData = businessService.shopInfoData(pageNumber, pageSize,userName,nickName,startTime,endTime,mobile,shopName,screen);
        setAttr("shopInfoPage",(Page<Record>) shopInfoData.get("shopInfoPage"));
        setAttr("shopInfoListAll",(List<Record>) shopInfoData.get("shopInfoListAll"));
        renderJson();
    }

    /**
     * 导出toExcel(业务员日志信息)
     */
    public void toBusinessLogExcel() {
        ExcelUtils e = new ExcelUtils();
        String path = "";
        String businessName = getPara("businessName", "");
        String startTime = getPara("startTime", "");
        String endTime = getPara("endTime", "");
        path = e.toBusinessLogExcel(businessName, startTime, endTime);
        renderFile(new File(path));
    }

    /**
     * 导出toExcel(统计所有的三级分类商品有数量)
     */
    public void toArticleCategoryExcel() {
        ExcelUtils e = new ExcelUtils();
        String path = "";
        int levelOne = getParaToInt("levelOne", 0);
        int levelTow = getParaToInt("levelTow", 0);
        int levelThere = getParaToInt("levelThere", 0);
        path = e.toArticleCategoryExcel(levelOne, levelTow, levelThere);
        renderFile(new File(path));
    }
    
    public void test(){
    	
    	
    	Record r =Db.findFirst("SELECT  top 1 (SELECT ' '+user_name FROM dt_users for xml path ('') ) from dt_users");
    	
    	renderText(JSONObject.fromObject(r).toString());
		
    	
    }
    

    /**
     * 导出toExcel(统计所有的三级分类商品有数量)
     */
    public void toShopInfoDataExcel() {
        ExcelUtils e = new ExcelUtils();
        String path = "";
        String userName=getPara("userName","");
        String nickName=getPara("nickName","");
        String startTime=getPara("startTime","");
        String endTime=getPara("endTime","");
        String mobile=getPara("mobile","");
        String shopName=getPara("shopName","");
        String screen=getPara("screen","");

        path = e.toShopInfoDataExcel(userName,nickName,startTime,endTime,mobile,shopName,screen);
        renderFile(new File(path));
    }

}
