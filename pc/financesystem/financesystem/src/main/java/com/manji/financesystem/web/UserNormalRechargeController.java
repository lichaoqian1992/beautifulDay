package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.extra.UserNormalRechargeDO;
import com.manji.financesystem.primaryDomain.repository.UserNormalRechargeRepository;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;
import com.manji.financesystem.service.UserNormalRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by pudding on 2017-2-3.
 * 用户正常充值Controller
 */
@RequestMapping("/rechargeManage")
@Controller
public class UserNormalRechargeController extends BaseController{

    @Autowired
    private UserNormalRechargeRepository userNormalRechargeRepository;

    @Autowired
    private UserNormalRechargeService userNormalRechargeService;



    /**
     * 充值记录页面(正常)
     *
     * @return
     */
    @RequestMapping("/normalRecharge.html")
    public String normalRechargeHtml() {
        return "/normalRecharge/normalRechargeRecord";
    }




    /**
     * 获取正常充值记录数据
     * @param page 当前页
     * @param userQueryNormalRechargeListParam 查询参数
     * @return
     */
    @RequestMapping("/queryNormalRechargeList/{page}")
    @ResponseBody
    public PagedQueryResult<UserNormalRechargeDO> queryNormalRechargeList(@PathVariable int page, UserQueryNormalRechargeListParam userQueryNormalRechargeListParam) {
        PagedQueryResult<UserNormalRechargeDO> result=new PagedQueryResult<UserNormalRechargeDO>();

        List<UserNormalRechargeDO> list = userNormalRechargeRepository.queryUserNormalRechargeRecordList(page, userQueryNormalRechargeListParam);
        int count = userNormalRechargeRepository.queryUserNormalRechargeRecordListCount(userQueryNormalRechargeListParam);
        result.setResultList(list);
        result.setPage(page);
        result.setTotalCount(count);
        result.setSuccessResult("查询成功");
        return result;
    }


    /**
     * 统计当日当月从中总金额
     * @return
     */
    @RequestMapping("/statisticsSumAmount")
    @ResponseBody
    public BaseResult statisticsSumAmount(){
        BaseResult baseResult = userNormalRechargeService.statisticsSumAmount();
        return baseResult;
    }


    /**
     * 导出Excel
     * @param userQueryNormalRechargeListParam
     * @return
     */
    @RequestMapping("/exportExcel")
    public ResponseEntity<InputStreamResource> exportExcel(UserQueryNormalRechargeListParam userQueryNormalRechargeListParam) throws IOException {
        String filePath = userNormalRechargeService.excel(userQueryNormalRechargeListParam);
        super.setPath(filePath);
        return downLoad();

    }





}
