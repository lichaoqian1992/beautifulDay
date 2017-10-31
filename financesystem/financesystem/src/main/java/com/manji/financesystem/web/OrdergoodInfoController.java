package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.primaryDomain.entiity.OrdergoodInfoDo;
import com.manji.financesystem.primaryDomain.repository.OrdergoodInfoRepository;
import com.manji.financesystem.utils.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by pudding on 2017-3-2.
 */
@RequestMapping("/withdrawals")
@Controller
public class OrdergoodInfoController {
    @Autowired
    private  OrdergoodInfoRepository  OrdergoodInfoRepository;


    /**
     * 查询全部发货记录信息
     * @return
     */
    @RequestMapping("/OrdergoodInfo")
    public ModelAndView getOrdergoodInfo(){
        List<OrdergoodInfoDo> OrdergoodInfolist= OrdergoodInfoRepository.findAllOrdergoodInfoDo(1);
        ModelAndView view=new ModelAndView("withdrawls/OrdergoodInfo");
        view.addObject("OrdergoodInfolist",OrdergoodInfolist);
        return view;
    }

}
