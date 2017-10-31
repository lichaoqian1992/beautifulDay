package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.OrdergoodInfoDo;
import com.manji.financesystem.secondaryDomain.entity.Systemlog;
import com.manji.financesystem.secondaryDomain.repository.SystemlogRepository;
import com.manji.financesystem.utils.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by pudding on 2017-3-3.
 */
@RequestMapping("/system")
@Controller
public class SystemlogController {

    @Autowired
    private SystemlogRepository  systemlogRepository;


    @RequestMapping("/Systemlog.html")
    public String findhtml(){
        return  "/system/Systemlog";
    }


    /**
     * 获取日志表记录
     * @return
     */
    @RequestMapping("/findAllSystemlog")
    @ResponseBody
    public BaseResult getOrdergoodInfo(int page){
        PagedQueryResult<Systemlog> Systemloglist=new PagedQueryResult<Systemlog>();
        Systemloglist.setResultList(systemlogRepository.getSystemInfo(page));
        Systemloglist.setTotalCount(systemlogRepository.getcount());
        return Systemloglist;
    }


}
