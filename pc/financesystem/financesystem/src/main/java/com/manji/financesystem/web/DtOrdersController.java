package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.primaryDomain.entiity.ShopInfoDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.entiity.extra.OrderAndShopInfoDO;
import com.manji.financesystem.primaryDomain.repository.ShopInfoRepository;
import com.manji.financesystem.requestParam.ShopInfoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 订单管理模块
 * Created by Administrator on 2017/2/24.
 */
@RequestMapping("/dtOrders")
@Controller
public class DtOrdersController {

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @RequestMapping("/dtOrders.html")
    public String dtOrders(){

        return "orders/dtOrders";
    }

    @RequestMapping("/shoperInfo.html")
    public String shoperInfo(){

        return "orders/shoperInfo";
    }

    /**
     * 根据商家名称查询商家详细信息
     * @param param
     * @return
     */
    @RequestMapping("/getCompanyInfo")
    @ResponseBody
    public BaseResult getCompanyInfo(ShopInfoParam param){

        BaseQueryResult<ShopInfoDO> baseQueryResult=new BaseQueryResult<ShopInfoDO>();
        List<ShopInfoDO> list = shopInfoRepository.getShopInfo(param);
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }

    /**
     * 查询总数据
     * @param param
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public BaseResult getCount(ShopInfoParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        ShopInfoDO shopInfoDO= shopInfoRepository.getCount(param);
        obj.setObj(shopInfoDO);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }

    /**
     * 显示商家的订单信息和商家部分信息
     * @param param
     * @return
     */
    @RequestMapping("/getShopOrderInfo")
    @ResponseBody
    public BaseResult getShopOrderInfo(ShopInfoParam param){
        BaseQueryResult<OrderAndShopInfoDO> obj = new BaseQueryResult<OrderAndShopInfoDO>();
        List<OrderAndShopInfoDO> list = shopInfoRepository.getShopOrderInfo(param);
        obj.setResultList(list);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }

    /**
     * 查询总数据
     * @param param
     * @return
     */
    @RequestMapping("/getOrderCount")
    @ResponseBody
    public BaseResult getOrderCount(ShopInfoParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        OrderAndShopInfoDO orderAndShopInfoDO = shopInfoRepository.getOrderCount(param);
        obj.setObj(orderAndShopInfoDO);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }
}
