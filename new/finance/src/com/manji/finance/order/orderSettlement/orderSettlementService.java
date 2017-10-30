package com.manji.finance.order.orderSettlement;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * Created by pudding on 2017-8-18.(YYR)
 */
public class orderSettlementService {

    orderSettlementRepository orderSettlementRepository=new orderSettlementRepository();

    /**
     * 查询全部有结算记录的商家页面(用于展示)
     */
    public Page<Record> findShopInfoByorder(int num,String ShopName){
        //查询全部有结算记录的商家
        Page<Record> page=orderSettlementRepository.findAllShopOrder(num,ShopName);
        for (int i=0;i<page.getList().size();i++){
            //查询此商家是否提过现
            if (orderSettlementRepository.findWcount(page.getList().get(i).get("user_id").toString())>0){
                //查询最后一次提现后的结算订单数量
                page.getList().get(i).set("ordersNum",orderSettlementRepository.findOrderByW(page.getList().get(i).get("user_id").toString()));
            }else{
                page.getList().get(i).set("ordersNum",orderSettlementRepository.findOrderByUserid(page.getList().get(i).get("user_id").toString()));
            }

        }
        return page;
    }


    /**
     * 点击详情时查看此商家最后一笔提现已结算的订单信息
     */
    public Page<Record> findAllSettlement(int num,String userId){
        //查询此商家是否提过现
        Page<Record> recordPage=new Page<Record>();
        if (orderSettlementRepository.findWcount(userId)>0){
            recordPage=orderSettlementRepository.findAllSettlement(num,userId);
        }else{
            recordPage=orderSettlementRepository. findAllSettlements(num,userId);
        }
        return recordPage;
    }


}
