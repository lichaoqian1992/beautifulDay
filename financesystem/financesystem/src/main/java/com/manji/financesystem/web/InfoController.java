package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.OrderOnlinePayDO;
import com.manji.financesystem.primaryDomain.entiity.UserBalanceLogDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoDetailDO;
import com.manji.financesystem.primaryDomain.entiity.extra.UserAccountInfoQueryDO;
import com.manji.financesystem.primaryDomain.repository.*;
import com.manji.financesystem.requestParam.UserAccountInfoDetailQueryRequestParam;
import com.manji.financesystem.requestParam.UserAccountInfoQueryRequestParam;
import com.manji.financesystem.responseData.AccountInfoResult;
import com.manji.financesystem.service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pudding on 2017-1-16.
 */
@RequestMapping("/info")
@Controller
public class InfoController {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private OrderOnlinePayRepository orderOnlinePayRepository;

    @Autowired
    private UserBalanceLogRepository userBalanceLogRepository;

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;

    @Autowired
    private UserAccountInfoRepository userAccountInfoRepository;

    @Autowired
    private UserAmountLogRepository userAmountLogRepository;


    @RequestMapping("/dataCollect.html")
    public String dataCollectHtml() {
        return "layout/index";
    }

    @RequestMapping("/accountInfo.html")
    public String accountInfoHtml(Model model) {
        List<AccountInfoResult> accountInfo = accountInfoService.getAccountInfo();
        model.addAttribute("accountInfo", accountInfo);
        return "info/accountInfo";
    }

    /**
     * 查询第三方支付的交易明细
     *
     * @param page
     * @param paymentId
     * @return
     */
    @RequestMapping("/getOnlinePayAccountInfoList/{page}/{paymentId}")
    @ResponseBody
    public BaseResult getOnlinePayAccountInfoList(@PathVariable int page, @PathVariable int paymentId) {
        PagedQueryResult<OrderOnlinePayDO> result = new PagedQueryResult<OrderOnlinePayDO>();
        List<OrderOnlinePayDO> orderOnlinePayList = orderOnlinePayRepository.getAccountListByPaymentId(page, paymentId);
        result.setPage(page);
        result.setTotalCount(orderOnlinePayRepository.getAccountListByPaymentIdCount(paymentId));
        result.setResultList(orderOnlinePayList);
        result.setSuccessResult("查询成功");
        return result;
    }


    /**
     * 查询提现手续费交易明细
     *
     * @param page
     * @return
     */
    @RequestMapping("/getBalanceLogList/{page}")
    @ResponseBody
    public BaseResult getBalanceLogList(@PathVariable int page) {
        PagedQueryResult<UserBalanceLogDO> result = new PagedQueryResult<UserBalanceLogDO>();
        List<UserBalanceLogDO> list = userBalanceLogRepository.getBalanceLogList(page);
        result.setPage(page);
        result.setTotalCount(userBalanceLogRepository.getBalanceLogAllCount());
        result.setResultList(list);
        result.setSuccessResult("查询成功");
        return result;
    }


    /**
     * 查询提现手续费明细
     *
     * @param page
     * @return
     */
    @RequestMapping("/getUserWithdrawalsList/{page}")
    @ResponseBody
    public BaseResult getUserWithdrawalsList(@PathVariable int page,String code) {
        PagedQueryResult<UserWithdrawalsDO> result = new PagedQueryResult<UserWithdrawalsDO>();
        List<UserWithdrawalsDO> list = userWithdrawalsRepository.getUserWithdrawalsList(page,code);
        result.setPage(page);
        result.setTotalCount(userWithdrawalsRepository.getUserWithdrawalsAllCount(code));
        result.setResultList(list);
        result.setSuccessResult("查询成功");
        return result;
    }


    /**
     * 获取用户余额
     *
     * @param page
     * @param param
     * @param model
     * @return
     */
    @RequestMapping("/queryShopsAccountInfo.html/{page}")
    public String queryShopsAccountInfo(@PathVariable Integer page, UserAccountInfoQueryRequestParam param, Model model) {
        PagedQueryResult result = new PagedQueryResult();
        List<UserAccountInfoQueryDO> list = userAccountInfoRepository.queryUserAccountInfoList(param, page);
        Integer count = userAccountInfoRepository.queryUserAccountInfoCount(param);
        result.setPage(page);
        result.setTotalCount(count);
        result.setResultList(list);
        result.setSuccessResult("查询成功");
        System.out.println(result);
        model.addAttribute("userAccountResult", result);
        return "info/shopsAccountInfo";
    }

    /**
     * 获取用户余额 JSON
     *
     * @param page
     * @param param
     * @return
     */
    @RequestMapping("/queryShopsAccountInfoJson/{page}")
    @ResponseBody
    public PagedQueryResult queryShopsAccountInfoJson(@PathVariable Integer page, UserAccountInfoQueryRequestParam param) {
        PagedQueryResult result = new PagedQueryResult();
        List<UserAccountInfoQueryDO> list = userAccountInfoRepository.queryUserAccountInfoList(param, page);
        Integer count = userAccountInfoRepository.queryUserAccountInfoCount(param);
        result.setPage(page);
        result.setTotalCount(count);
        result.setResultList(list);
        result.setSuccessResult("查询成功");
        return result;
    }


    /**
     * 查询用户余额变动明细
     *
     * @param page  页数
     * @param param 查询条件
     * @return
     */
    @RequestMapping("/queryUserAccountInfoDetailJson/{userName}/{page}")
    @ResponseBody
    public PagedQueryResult queryUserAccountInfoDetailJson(@PathVariable int page, @PathVariable String userName, UserAccountInfoDetailQueryRequestParam param) {
        PagedQueryResult<UserAccountInfoDetailDO> result = new PagedQueryResult<UserAccountInfoDetailDO>();
        List<UserAccountInfoDetailDO> list = userAmountLogRepository.queryUserAccountInfoDetail(page, userName, param);
        Integer count = userAmountLogRepository.queryUserAccountInfoDetailCount(page, userName, param);
        result.setTotalCount(count);
        result.setPage(page);
        result.setResultList(list);
        result.setSuccessResult("查询成功");
        return result;
    }


}
