package com.manji.financesystem.web;

import com.manji.financesystem.common.*;
import com.manji.financesystem.config.CwConfig;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDMCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.OaRequestParam;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.secondaryDomain.entity.OaInfoDO;
import com.manji.financesystem.secondaryDomain.entity.RechargeConfigDO;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.entity.extra.RechargeMoneyAndPersonDO;
import com.manji.financesystem.secondaryDomain.repository.InteriorRechargeRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemPushRepository;
import com.manji.financesystem.service.UserInsideRechargeService;
import com.manji.financesystem.service.UserNormalRechargeService;
import com.manji.financesystem.utils.VerificationCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pudding on 2017-2-5.
 */
@RequestMapping("/userRecharge")
@Controller
public class UserRechargeController {

    @Autowired
    private CwConfig cwConfig;

    @Autowired
    private UserNormalRechargeService userNormalRechargeService;

    @Autowired
    private UserInsideRechargeService userInteriorRechargeService;

    @Autowired
    private InteriorRechargeRepository interiorRechargeRepository;

    @Autowired
    private SystemPushRepository systemPushRepository;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 生成业务处理单号
     * @param model
     * @return
     */
    @RequestMapping("/interiorRecharge.html")
    public String interiorRecharge(Model model) {
        String rechargeNo = UserRechargeController.format.format(new Date());
        model.addAttribute("rechargeNo", rechargeNo);
        return "/normalRecharge/interiorRecharge";
    }

    @RequestMapping("/oa.html")
    public String showOa(HttpServletRequest request){

        /*HttpSession session = request.getSession();
        UserDO u = (UserDO) session.getAttribute("user");
        if(u.getUserName().equals("") || null == u.getUserName()){
            return "layout/main";
        }*/
        return "/insideRecharge/oa";
    }

    /**
     * 充值发布
     *
     * @return
     */
    @RequestMapping(value = "/rechargePush", method = RequestMethod.POST,produces="text/html;charset=utf-8")
    public String rechargePush(@RequestParam("oaNo") int oaNo, @RequestParam("excelNo") String excelNo,@RequestParam("rechargeType") String rechargeType,@RequestParam("excelName") String excelName, @RequestParam("personRelease") String personRelease, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String name = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                String path=cwConfig.getFileUploadPath()+name;
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path)));
                stream.write(bytes);
                stream.flush();
                stream.close();
                String status = userNormalRechargeService.recharge(oaNo,path,excelNo,rechargeType,excelName,personRelease);
                System.out.println(status);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/normalRecharge/interiorRecharge";
    }

    /**
     * 查询我充值的订单
     * @return
     */
    @RequestMapping("/getMyRecharge")
    @ResponseBody
    public BaseResult getMyRecharge(InteriorRechargeRequestParam param){
        PagedQueryResult<InteriorRechargeDetailDO> baseQueryResult = new PagedQueryResult<InteriorRechargeDetailDO>();
        List<InteriorRechargeDetailDO> list = interiorRechargeRepository.getMyRecharge(param);
        int udo = interiorRechargeRepository.getCount(param);
        if(list != null && list.size()>0){
            baseQueryResult.setResultList(list);
            baseQueryResult.setTotalCount(udo);
            baseQueryResult.setSuccessResult("SUCCESS");
        }else{
            baseQueryResult.setSuccessResult("ERROR");
        }
        return baseQueryResult;
    }
    /**
     * 获取当日和当月的充值总金额
     * @return
     */
    @RequestMapping("getDMCount")
    @ResponseBody
    public BaseResult getDMCount() throws ParseException {
        ObjectBaseResult<UserInsideRechargeDMCountDO> baseQueryResult=new ObjectBaseResult<UserInsideRechargeDMCountDO>();
        UserInsideRechargeDMCountDO do1 = interiorRechargeRepository.getDMCount();
        baseQueryResult.setObj(do1);
        baseQueryResult.setSuccessResult("查询成功");
        return baseQueryResult;
    }
    /**
     * 报批充值订单
     * @return
     */
    @RequestMapping("/approvalOrder")
    @ResponseBody
    public BaseResult approvalOrder(InteriorRechargeRequestParam param){

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();

        String status = interiorRechargeRepository.batchApproval(param);
        baseQueryResult.setSuccessResult(status);
        return baseQueryResult;
    }

    /**
     * 判断是否是密集充值
     * @param param
     * @return
     */
    @RequestMapping("/checkApproval")
    @ResponseBody
    public BaseResult checkApproval(InteriorRechargeRequestParam param){
        BaseQueryResult<StringDO> baseQueryResult=new BaseQueryResult<StringDO>();
        List<StringDO> list = interiorRechargeRepository.checkApproval(param);
        if(list != null && list.size()>0){
            baseQueryResult.setResultList(list);
            baseQueryResult.setSuccessResult("SUCCESS");
        }else{
            baseQueryResult.setSuccessResult("ERROR");
        }
        return baseQueryResult;
    }

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping("/getYzm")
    @ResponseBody
    public BaseResult getYzm(InteriorRechargeRequestParam param) throws UnknownHostException {

        BaseQueryResult<String> baseQueryResult=new BaseQueryResult<String>();
        List<UserSmsLogDO> list = new ArrayList<UserSmsLogDO>();
        InetAddress id=null;
        String prompt = "";
        Date date = new Date();
        String yzm = new VerificationCodeUtils().getYzm();
        //获取充值申请人的电话号码，根据登录名获取电话号码信息

        String mobile = interiorRechargeRepository.getApplicant(param.getUserName());
        String message = param.getUserName()+"通过财务系统向您发出充值审批请求，本次充值共1笔，总金额"+param.getAccountMoney()+"元，单笔最大金额"+param.getAccountMoney()+"元，验证码为:"+yzm+"。如果您同意此次充值请将该短信验证码回复至操作人。如有疑问致电"+mobile+"咨询。";
        //这里需要根据充值的金额来判断审批人是谁，然后把验证码发给这个人
        if(param != null){
            double acountMoney = param.getAccountMoney();
            if(acountMoney <= 1000){
                param.setUserName("资金部负责人");
            }else if(acountMoney>1000 && acountMoney<=5000){
                param.setUserName("财务经理");
            }else if(acountMoney>5000 && acountMoney<=10000){
                param.setUserName("财务副总裁");
            }else if(acountMoney>10000){
                param.setUserName("董事长");
            }
            //如果充值金额小于1000，那么在判断今天的审批总金额是否大于3000
            //查询该审批人的今日审批总金额，返回金额
            double rdo1 = interiorRechargeRepository.getAcountMoney(param);
            //查询该审批人的审批额度
            List<RechargeConfigDO> rod3 = interiorRechargeRepository.queryRechargeConfig(acountMoney);
            RechargeMoneyAndPersonDO rdo2 = interiorRechargeRepository.getMobile(param);
            if((acountMoney + rdo1)<=rod3.get(0).getTotalMoney()){
                UserSmsLogDO userSmsLogDO = new UserSmsLogDO();
                userSmsLogDO.setUserId(1);
                userSmsLogDO.setUserRoleType("用户");
                userSmsLogDO.setUserRoleValue(1);
                userSmsLogDO.setType("临时充值验证码推送");
                userSmsLogDO.setUserIp(id.getLocalHost().getHostAddress());
                userSmsLogDO.setContent(message);
                userSmsLogDO.setStatus(0);
                userSmsLogDO.setMobile(rdo2.getMobile());
                userSmsLogDO.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                userSmsLogDO.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                list.add(userSmsLogDO);
                systemPushRepository.addInfo(list);
                prompt = "SUCCESS";//消息发送成功
            }else{
                prompt = "FULL";//今日审批金额已达上限
            }
        }
        baseQueryResult.setSuccessResult(prompt);
        return baseQueryResult;
    }

    /**
     * 验证验证码是否正确
     * @return
     */
    @RequestMapping("/checkYzm")
    @ResponseBody
    public BaseResult checkYzm(InteriorRechargeRequestParam param) throws ParseException {
        String status = "";
        String str = "";
        //String content = "我通过财务系统向您发出充值审批请求，本次充值共1笔，总金额100元，单笔最大金额100元，验证码为:1234。如果您同意此次充值请将该短信验证码回复至操作人。如有疑问致电***********咨询。";
        ObjectBaseResult<String> obj = new ObjectBaseResult<String>();
        if(param != null){
            //查询验证码短信
            //String message = interiorRechargeRepository.checkYzm().split("【")[1].split("】")[0];
            List<UserSmsLogDO> content = interiorRechargeRepository.checkYzm();
            //long time = param.getNowTime() - content.get(0).getSendTime();
            long time = new Date().getTime();
            long sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").parse(content.get(0).getSendTime()).getTime();
            System.out.println(sendTime);
            //获取验证码的数字
            str=content.get(0).getContent().trim();
            String str2 = str.split("。")[0].split(":")[1];
            System.out.println(str2);
            //先判断验证码是否正确，然后判断是否过期
            if(str2.equals(param.getYzm())){
                if(time - sendTime < 10*60*1000){
                    status = "SUCCESS";
                }else{
                    status = "OVERDU";//过期
                }
            }else{
                status = "FAIL";
            }
        }else{
            status = "ERROR";
        }
        obj.setSuccessResult(status);
        return obj;
    }

    /**
     * 临时充值
     * @param param
     * @return
     */
    @RequestMapping("/doRecharge")
    @ResponseBody
    public BaseResult doRecharge(InteriorRechargeRequestParam param){
        ObjectBaseResult<String> obj = new ObjectBaseResult<String>();
        String status = interiorRechargeRepository.doRecharge(param);
        obj.setSuccessResult(status);
        return obj;
    }
    /**
     * 批量充值
     * @param param
     * @return
     */
    @RequestMapping("/rollBackRecharge")
    @ResponseBody
    public BaseResult rollBackRecharge(InteriorRechargeRequestParam param){
        ObjectBaseResult<String> obj = new ObjectBaseResult<String>();

        String status = interiorRechargeRepository.batchApproval(param);
        obj.setSuccessResult(status);
        return obj;
    }

    /**
     * 录入OA文件编号信息
     * @return param
     */
    @RequestMapping("/inputOaInfo")
    @ResponseBody
    public BaseResult inputOaInfo(OaRequestParam param){
        ObjectBaseResult<String> obj = new ObjectBaseResult<String>();
        String message = interiorRechargeRepository.inputOaInfo(param);
        obj.setSuccessResult(message);
        return obj;
    }

    /**
     * 查询OA编号信息
     * @param param
     * @return
     */
    @RequestMapping("/queryOaInfo")
    @ResponseBody
    public BaseResult queryOaInfo(OaRequestParam param){

        PagedQueryResult<OaInfoDO> result = new PagedQueryResult<OaInfoDO>();
        List<OaInfoDO>  list = interiorRechargeRepository.queryOaInfo(param);
        int count = interiorRechargeRepository.queryForCount(param);
        result.setSuccessResult("SUCCESS");
        result.setResultList(list);
        result.setTotalCount(count);
        result.setPage(param.getPageNum());
        return result;
    }

    /**
     * 获取用户职位
     * @param userName
     * @return
     */
    @RequestMapping("/getUserRole")
    @ResponseBody
    public BaseResult getUserRole(String userName){

        ObjectBaseResult obj = new ObjectBaseResult();
        String message = interiorRechargeRepository.getUserRole(userName);
        obj.setSuccessResult(message);

        return obj;
    }

    /**
     * 修改充值订单信息
     * @param param
     * @return
     */
    @RequestMapping("/modifyOrderInfo")
    @ResponseBody
    public BaseResult modifyOrderInfo(InteriorRechargeRequestParam param){

        ObjectBaseResult obj = new ObjectBaseResult();
        String message = interiorRechargeRepository.modifyOrderInfo(param);
        if(message.equals("SUCCESS")){
            obj.setObj("修改成功");
        }else if(message.equals("NOT EXIT")){
            obj.setObj("充值账户不存在，请检查");
        }else{
            obj.setObj("参数错误");
        }
        obj.setSuccessResult("SUCCESS");
        return obj;
    }
}
