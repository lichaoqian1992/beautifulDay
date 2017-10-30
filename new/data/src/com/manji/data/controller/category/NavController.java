package com.manji.data.controller.category;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.service.ShopInfoCenterService;

import java.util.List;

public class NavController extends Controller{

	ShopInfoCenterService service = new ShopInfoCenterService();

    /**
     * 查询一级PC首页导航
     */
    public void selPcHome() {

	    //List<Record> records = service.getShopInfo();

	   // renderJson(records);
    }



	
}
