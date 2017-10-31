package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.primaryDomain.entiity.*;
import com.manji.financesystem.primaryDomain.entiity.extra.VoucherInfoDO;
import com.manji.financesystem.primaryDomain.repository.DailySheetRepository;
import com.manji.financesystem.primaryDomain.repository.EveryIndustryManagementSituationRepository;
import com.manji.financesystem.primaryDomain.repository.SatisfactionVoucherRepository;
import com.manji.financesystem.primaryDomain.repository.ShopInfoRepository;
import com.manji.financesystem.requestParam.MonthAndDailySheetRequestParam;
import com.manji.financesystem.requestParam.SatisfactionVoucherRequestParam;
import com.manji.financesystem.requestParam.VoucherDetailRequestParam;
import com.manji.financesystem.responseData.DailySheetResult;
import com.manji.financesystem.responseData.MessageSendResult;
import com.manji.financesystem.responseData.MonthSheetResult;
import com.manji.financesystem.responseData.OrdersResult;
import com.manji.financesystem.service.MonthAndDailySheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */
@RequestMapping("/monthAndDailySheet")
@Controller
public class MonthAndDailySheetController {

    @Autowired
    private MonthAndDailySheetService monthAndDailySheetService;

    @Autowired
    private EveryIndustryManagementSituationRepository everyIndustryManagementSituationRepository;

    @Autowired
    private SatisfactionVoucherRepository satisfactionVoucherRepository;

    @Autowired
    private DailySheetRepository dailySheetRepository;

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    /**
     * 默认查询今天的
     * @param model
     * @return
     * @throws ParseException 
     */
    @RequestMapping("/index.html")
    public String indexHtml(Model model) throws ParseException {
        DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        List<EveryIndustryManagementSituationDO> industryResult = everyIndustryManagementSituationRepository.getStationOfIndustry();
        //MonthSheetResult monthSheetResult = monthAndDailySheetService.getMonthSheetResult(new SimpleDateFormat("yyyy-MM").format(new Date()));
        monthAndDailySheet.setStartTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        model.addAttribute("monthAndDailySheet",monthAndDailySheet);
        model.addAttribute("industryResult",industryResult);
//        model.addAttribute("moneyResult",industryResult);
        return "layout/index";
    }

    /**
     * 选择日期查询
     * @param
     * @param monthAndDailySheetRequestParam
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getDailySheet")
    @ResponseBody
    public BaseResult getDailySheet(MonthAndDailySheetRequestParam monthAndDailySheetRequestParam) throws ParseException {
        ObjectBaseResult<DailySheetResult> baseQueryResult=new ObjectBaseResult<DailySheetResult>();
        DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(monthAndDailySheetRequestParam.getStartTime());
        monthAndDailySheet.setStartTime(monthAndDailySheetRequestParam.getStartTime());
        baseQueryResult.setObj(monthAndDailySheet);
        baseQueryResult.setSuccessResult("操作成功");
        return baseQueryResult;
    }

    /**
     * 查询月报表
     * @param monthAndDailySheetRequestParam
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/getMonthSheet")
    @ResponseBody
    public BaseResult getMonthSheet(MonthAndDailySheetRequestParam monthAndDailySheetRequestParam) throws ParseException {
        ObjectBaseResult<MonthSheetResult> baseQueryResult=new ObjectBaseResult<MonthSheetResult>();
        MonthSheetResult monthAndDailySheet = monthAndDailySheetService.getMonthSheetResult(monthAndDailySheetRequestParam.getStartTime());
        monthAndDailySheet.setStartMonthTime(monthAndDailySheetRequestParam.getStartTime());
        baseQueryResult.setObj(monthAndDailySheet);
        baseQueryResult.setSuccessResult("操作成功");
        return baseQueryResult;
    }

    /**
     * 查询各个行业的经营情况
     *
     * @return
     */
    @RequestMapping(value = "/getEveryIndustryManagementSituation")
    @ResponseBody
    public BaseResult getEveryIndustryManagementSituation(){
        BaseQueryResult<EveryIndustryManagementSituationDO> baseQueryResult=new BaseQueryResult<EveryIndustryManagementSituationDO>();
        List<EveryIndustryManagementSituationDO> industryResult = everyIndustryManagementSituationRepository.getStationOfIndustry();

        baseQueryResult.setResultList(industryResult);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    /**
     * 查询用户和商家的账户满意券余额
     * @param model
     * @param satisfactionVoucherRequestParam
     * @return
     */
    @RequestMapping("/satisfactionVoucher.html")
    public String getSatisfactionVoucherInfo(Model model,SatisfactionVoucherRequestParam satisfactionVoucherRequestParam){

        List<SatisfactionVoucherDO> satisfactionVoucherResult = satisfactionVoucherRepository.getSatisfactionVoucher(satisfactionVoucherRequestParam);
        //List<SatisfactionVoucherCountDO> satisfactionVoucherCountResult = satisfactionVoucherRepository.getCount(roleType);
        model.addAttribute("satisfactionVoucherResult",satisfactionVoucherResult);
        //model.addAttribute("satisfactionVoucherCountResult",satisfactionVoucherCountResult);
        return "info/satisfactionVoucherInfo";
    }

    /**
     * 查询总数据
     * @param satisfactionVoucherRequestParam
     * @return
     */
    @RequestMapping("getCountAndPage")
    @ResponseBody
    public BaseResult getCountAndPage(SatisfactionVoucherRequestParam satisfactionVoucherRequestParam){
        BaseQueryResult<SatisfactionVoucherCountDO> baseQueryResult=new BaseQueryResult<SatisfactionVoucherCountDO>();
        List<SatisfactionVoucherCountDO> industryResult = satisfactionVoucherRepository.getCount(satisfactionVoucherRequestParam);

        baseQueryResult.setResultList(industryResult);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    @RequestMapping("getSatisfactionVoucher")
    @ResponseBody
    public BaseResult getSatisfactionVoucher(SatisfactionVoucherRequestParam satisfactionVoucherRequestParam){
        BaseQueryResult<SatisfactionVoucherDO> baseQueryResult=new BaseQueryResult<SatisfactionVoucherDO>();

        List<SatisfactionVoucherDO> industryResult = satisfactionVoucherRepository.getSatisfactionVoucher(satisfactionVoucherRequestParam);

        baseQueryResult.setResultList(industryResult);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    /**
     * 查询满意券的详情
     * @param param
     * @return
     */
    @RequestMapping("getVoucherDetail")
    @ResponseBody
    public BaseResult getVoucherDetail(VoucherDetailRequestParam param){
        BaseQueryResult<VoucherInfoDO> baseQueryResult=new BaseQueryResult<VoucherInfoDO>();
        List<VoucherInfoDO> voucherInfoList = new ArrayList<VoucherInfoDO>();
        //查询充值记录中的满意券
        List<UserRechargeDO> userRechargeList = satisfactionVoucherRepository.getRecharge(param);
        for(UserRechargeDO temp:userRechargeList){
            VoucherInfoDO list1 = new VoucherInfoDO();
            list1.setUserId(temp.getUserId());
            list1.setUserRoleType(temp.getUserRoleType());
            list1.setOrderNo(temp.getRechargeNo());
            list1.setOrderTitle("退单");
            list1.setVoucher(temp.getVoucher());
            list1.setAddTime(temp.getAddTime());
            voucherInfoList.add(list1);
        }

        //查询提现记录中的满意券
        List<UserWithdrawalsDO> userWithdrawalsList = satisfactionVoucherRepository.getWithDrawals(param);
        for(UserWithdrawalsDO temp:userWithdrawalsList){
            VoucherInfoDO list2 = new VoucherInfoDO();
            list2.setUserId(temp.getUserId());
            list2.setUserRoleType(temp.getUserRoleTyoe());
            list2.setOrderNo(temp.getWithdrawalsNo());
            list2.setOrderTitle("提现");
            list2.setVoucher(temp.getVoucher());
            list2.setAddTime(temp.getAdd_time());
            list2.setCompleteTime(temp.getComplete_time());
            voucherInfoList.add(list2);
        }

        //查询订单中的满意券
        List<OrdersDo> ordersList = satisfactionVoucherRepository.getOrderVoucher(param);
        for(OrdersDo temp:ordersList){
            VoucherInfoDO list3 = new VoucherInfoDO();
            list3.setUserId(temp.getUserId());
            list3.setUserRoleType(temp.getUserRoleType());
            list3.setOrderNo(temp.getOrderNo());
            list3.setOrderTitle(temp.getOrderTitle());
            list3.setVoucher(temp.getVoucher());
            list3.setAddTime(temp.getAddTime());
            list3.setCompleteTime(temp.getCompleteTime());
            voucherInfoList.add(list3);
        }
        //查询退单中的满意券
        List<OrderBackInfoDO> orderBackInfoList = satisfactionVoucherRepository.getOrderBackVoucher(param);
        for(OrderBackInfoDO temp:orderBackInfoList){
            VoucherInfoDO list4 = new VoucherInfoDO();
            list4.setUserId(temp.getUserId());
            list4.setUserRoleType(temp.getUserRoleType());
            list4.setOrderNo(temp.getOrderNO());
            list4.setOrderTitle("退单");
            list4.setVoucher(temp.getVoucher());
            list4.setAddTime(temp.getAddTime());
            list4.setCompleteTime("无");
            voucherInfoList.add(list4);
        }
        //查询在线支付中的满意券
        List<OrderOnlinePayDO> orderOnlinePayList = satisfactionVoucherRepository.getOnlinePayVoucher(param);
        for(OrderOnlinePayDO temp:orderOnlinePayList){
            VoucherInfoDO list5 = new VoucherInfoDO();
            list5.setUserId(temp.getUserId().toString());
            list5.setUserRoleType(temp.getUserRoleType());
            list5.setOrderNo("无");
            list5.setOrderTitle(temp.getPaymentName());
            list5.setVoucher(temp.getUseVoucher());
            list5.setAddTime(temp.getAddTime().toString());
            list5.setCompleteTime("无");
            voucherInfoList.add(list5);
        }
        //合并查询到的结果集
        baseQueryResult.setResultList(voucherInfoList);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    /**
     * 查询每天的交易流水量，支出，结存，订单交易笔数，成功订单数，金额
     * @return
     */
    @RequestMapping("getDailyInfo")
    @ResponseBody
    public BaseResult getDailyInfo() throws ParseException {
        Date date = new Date();
        String message = "";
        String  myDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        ObjectBaseResult<MessageSendResult> baseQueryResult=new ObjectBaseResult<MessageSendResult>();
        //查询到的是每天的交易流水金额和支出以及结存
        MessageSendResult messageSendResult = new MessageSendResult();
        DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(myDate);
        OrdersResult ordersDo1 = dailySheetRepository.getAllOrdersMoney(myDate);
        OrdersResult ordersDo2 = dailySheetRepository.getSuccessOrdersMoney(myDate);
        double jiecunMoney = monthAndDailySheet.getIncomeAmount() - monthAndDailySheet.getExpenditureAmount();
        message = "本日产生平台交易流水量"+monthAndDailySheet.getIncomeAmount()+"元，支出"+monthAndDailySheet.getExpenditureAmount()+"元," +
                "结存"+jiecunMoney+"元。本日平台订单交易"+ordersDo1.getAmountOrders()+"笔，总金额"+ordersDo1.getAmountMoney()+"元，其中成功订单"+ordersDo2.getSuccessOrders()+"笔，金额"+ordersDo2.getSuccessMoney()+"元";
        //把支出和结存金额，以及订单的相关消息存放到一个对象里面，返回消息到前端
        //向数据库插入两条数据即可
        UserSmsLogDO param = new UserSmsLogDO();
        /*param.setUserId();
        param.setUserRoleType();
        param.setUserRoleValue();
        param.setType("平台交易信息推送");
        param.setUserIp(id.getLocalHost().getHostAddress());//本机IP
        param.setContent(message);
        param.setMobile();
        param.setStatus(0);
        param.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        param.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        dailySheetRepository.addInfo(param);*/

        messageSendResult.setMessage(message);
        baseQueryResult.setObj(messageSendResult);
        baseQueryResult.setSuccessResult("操作成功");
        return baseQueryResult;
    }

    /**
     * 获取平台当日的交易流水
     * @return
     */
    @RequestMapping("/getDay")
    @ResponseBody
    public BaseResult getToday() throws ParseException {
        ObjectBaseResult obj = new ObjectBaseResult();
        Date d = new Date();
        String startTime = new SimpleDateFormat("yyyy-MM").format(d);
        DailySheetResult monthAndDailySheet = monthAndDailySheetService.getAccountInfoResult(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        MonthAndDailySheetIncomeDO dailySheetDO = dailySheetRepository.getMonthSheetIncome(startTime);
        int num = shopInfoRepository.getShopCount();
        String message = "平台内商家总数量:"+num+"家,当日平台业务流水量:"+monthAndDailySheet.getIncomeAmount()+"元,当月业务流水量:"+dailySheetDO.getIncomeAmount()+"元";
        obj.setObj(message);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }
}
