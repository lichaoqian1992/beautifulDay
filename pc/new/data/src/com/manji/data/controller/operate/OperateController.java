package com.manji.data.controller.operate;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.service.operate.OperateService;
import com.manji.data.utils.ExcelUtils;

import java.io.File;

public class OperateController extends Controller {

    private OperateService operateService = new OperateService();
    private int pageSize = 20;

    /**
     * 商家销售信息页面
     */
    public void shopInformationView() {
        render("shopInformation.html");
    }

    /**
     *  商家销售信息（未销售）
     */
    public void shopNoSaleView() {
        render("shopNoSale.html");
    }

    /**
     *  商家入驻信息查询
     */
    public void shopSettledView() {
        render("shopSettled.html");
    }


    /**
     * 商家销售信息数据
     */
    public void shopInformationData() {

        int pageNumber = getParaToInt("pageNumber", 1);
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String startTime = getPara("startTime", "");
        String endTime = getPara("endTime", "");
        Page<Record> shopInformation = operateService.shopInformationData(pageNumber,pageSize,shopName,personName,mobile,startTime,endTime);

        renderJson(shopInformation);
    }

    /**
     *  商家销售信息数据（未销售）
     */
    public void shopNoSaleData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String time = getPara("time", "");
        Page<Record> shopInformation = operateService.shopNoSaleData(pageNumber,pageSize,shopName,personName,mobile,time);

        renderJson(shopInformation);
    }

    /**
     *   商家入驻信息查询
     */
    public void shopSettledData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String time = getPara("time", "");
        Page<Record> shopInformation = operateService.shopSettledData(pageNumber,pageSize,shopName,personName,mobile,time);

        renderJson(shopInformation);
    }



    /**
     * 导出toExcel(商家销售信息数据)
     */
    public void toShopInformationExcel() {

        ExcelUtils e = new ExcelUtils();
        String path = "";
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String startTime = getPara("startTime", "");
        String endTime = getPara("endTime", "");
        path = e.toShopInformationExcel(shopName,personName,mobile,startTime, endTime);

        renderFile(new File(path));
    }

    /**
     * 导出toExcel(商家销售信息数据（未销售）)
     */
    public void toshopNoSaleExcel() {

        ExcelUtils e = new ExcelUtils();
        String path = "";
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String time = getPara("time", "");
        path = e.toshopNoSaleExcel(shopName,personName,mobile,time);

        renderFile(new File(path));
    }

    /**
     * 导出toExcel(导出商家入驻信息查询)
     */
    public void toShopSettledDataExcel() {

        ExcelUtils e = new ExcelUtils();
        String path = "";
        String shopName = getPara("shopName", "");
        String personName = getPara("personName", "");
        String mobile = getPara("mobile", "");
        String time = getPara("time", "");
        path = e.toShopSettledDataExcel(shopName,personName,mobile,time);

        renderFile(new File(path));
    }
}
