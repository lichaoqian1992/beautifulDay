package com.manji.financesystem.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.UserOrdersDO;
import com.manji.financesystem.primaryDomain.entiity.UserSmsLogDO;
import com.manji.financesystem.primaryDomain.entiity.UserWithdrawalsDO;
import com.manji.financesystem.primaryDomain.repository.UserDoRepository;
import com.manji.financesystem.primaryDomain.repository.UserWithdrawalsRepository;
import com.manji.financesystem.requestParam.WithDrawalsParam;
import com.manji.financesystem.secondaryDomain.entity.HttplogDO;
import com.manji.financesystem.secondaryDomain.entity.TUserWithdrawals;
import com.manji.financesystem.secondaryDomain.entity.UserDO;
import com.manji.financesystem.secondaryDomain.repository.HttplogRepository;
import com.manji.financesystem.secondaryDomain.repository.SystemPushRepository;
import com.manji.financesystem.secondaryDomain.repository.TUserWithdrawalsRepository;
import com.manji.financesystem.service.SynchronizationWithdrawalsService;
import com.manji.financesystem.service.UserInsideRechargeService;
import com.manji.financesystem.utils.InterfaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 提现管理Controller
 * Created by Administrator on 2017/2/16.
 */
@RequestMapping("/withdrawals")
@Controller
public class WithDrawalsController extends BaseController{

    @Autowired
    private UserWithdrawalsRepository userWithdrawalsRepository;

    @Autowired
    private UserInsideRechargeService userInsideRechargeService;

    @Autowired
     private HttplogRepository httplogRepository;

    @Autowired
    private SynchronizationWithdrawalsService synchronizationWithdrawalsService;

    @Autowired
    private TUserWithdrawalsRepository tUserWithdrawalsRepository;

    @Autowired
    private SystemPushRepository systemPushRepository;

    @Autowired
    private UserDoRepository userDoRepository;



    @RequestMapping("/withdrawalsQuery.html")
    public String withDrawals(){

        return "/withdrawls/withDrawlsQuery";
    }

    @RequestMapping("/withdrawalsErrQuery.html")
    public String withErrDrawals(){

        return "/withdrawls/withDrawlsErrQuery";
    }

    @RequestMapping("/Pendingprocessing.html")
    public String Pendingprocessing(){

        return "/withdrawls/Pendingprocessing";
    }

    /**
     * 查询正常提现记录
     * @param param
     * @return
     */
    @RequestMapping("/getWithDrawals")
    @ResponseBody
    public BaseResult getWithDrawals(WithDrawalsParam param ,HttpServletRequest requst){
        //獲取當前用戶
        HttpSession session=requst.getSession();
        UserDO user=(UserDO) session.getAttribute("user");
        param.setExceptions(0);
        BaseQueryResult<TUserWithdrawals> baseQueryResult=new BaseQueryResult<TUserWithdrawals>();
        List<TUserWithdrawals> resultList = tUserWithdrawalsRepository.getWithDrawals(param,user);
        baseQueryResult.setResultList(resultList);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }

    /**
     * 查询异常提现记录
     * @param param
     * @return
     */
    @RequestMapping("/getErrWithDrawals")
    @ResponseBody
    public BaseResult getErrWithDrawals(WithDrawalsParam param ,HttpServletRequest requst){
        //獲取當前用戶
        HttpSession session=requst.getSession();
        UserDO user=(UserDO) session.getAttribute("user");
        param.setExceptions(1);
        BaseQueryResult<TUserWithdrawals> baseQueryResult=new BaseQueryResult<TUserWithdrawals>();
        List<TUserWithdrawals> resultList = tUserWithdrawalsRepository.getWithDrawals(param,user);
        baseQueryResult.setResultList(resultList);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }


    /**
     * 查询正常总数据
     * @param param
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public BaseResult getCount(WithDrawalsParam param,HttpServletRequest requst){
        HttpSession session=requst.getSession();
        UserDO user=(UserDO) session.getAttribute("user");
        param.setExceptions(0);
        ObjectBaseResult obj = new ObjectBaseResult();
        TUserWithdrawals udo = tUserWithdrawalsRepository.getCount(param,user);
        obj.setObj(udo);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }


    /**
     * 查询异常总数据
     * @param param
     * @return
     */
    @RequestMapping("/getErrCount")
    @ResponseBody
    public BaseResult getErrCount(WithDrawalsParam param,HttpServletRequest requst){
        HttpSession session=requst.getSession();
        UserDO user=(UserDO) session.getAttribute("user");
        param.setExceptions(1);
        ObjectBaseResult obj = new ObjectBaseResult();
        TUserWithdrawals udo = tUserWithdrawalsRepository.getCount(param,user);
        obj.setObj(udo);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }



    /**
     * 查询待提现用户个数和总金额
     * @return
     */
    @RequestMapping("/getCountAndMoney")
    @ResponseBody
    public BaseResult getCountAndMoney(){
        ObjectBaseResult obj = new ObjectBaseResult();
        TUserWithdrawals udo = tUserWithdrawalsRepository.getCountAndMoney();
        obj.setObj(udo);
        obj.setSuccessResult("SUCCESS");
        return obj;
    }

    /**
     * 点击商家查询商家订单详情
     * @param param 根据商家或者用户的ID查询订单详情
     * @return
     */
    @RequestMapping("/getOrderDetail")
    @ResponseBody
    public BaseResult getOrderDetail(WithDrawalsParam param){
        BaseQueryResult<UserOrdersDO> baseQueryResult=new BaseQueryResult<UserOrdersDO>();
        List<UserOrdersDO> list = userWithdrawalsRepository.getOrderDetails(param);
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }

    /**
     * 获取详细的订单信息
     * @param param
     * @return
     */
    @RequestMapping("/getCountDetail")
    @ResponseBody
    public BaseResult getCountDetail(WithDrawalsParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        String udo = userWithdrawalsRepository.getCountDetail(param);
        obj.setSuccessResult(udo);
        return obj;
    }

    /**
     * HTTP修改提现状态
     * @param id
     * @param stats
     * @param remark
     * @return
     */
    @RequestMapping("/HttpWithdrawals")
    @ResponseBody
    public  BaseResult sendSms(String id ,String stats,String remark){
        ObjectBaseResult Result=new ObjectBaseResult();
        String noncestr=String.valueOf(System.currentTimeMillis());
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tranSN=dateFormat.format(date).toString();
        TreeMap<String,String> map=new TreeMap<String, String>();
        map.put("accessKey","javamanager");
        map.put("action","UserWithdrawals");
        map.put("idList",id);
        map.put("noncestr",noncestr);
        map.put("remark",remark);
        map.put("status",stats);
        map.put("tranSN",tranSN+"1");
        try {
            String k=InterfaceUtil.GetAPI(InterfaceUtil.ATURL,map);
           JSONObject jsonObject=JSON.parseObject(k);
            int ErrCode=Integer.parseInt(jsonObject.get("ErrCode").toString());
            //请求成功
            if (ErrCode==0){
                //添加到http日志表
                int ResultCode=Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
                if (ResultCode==8){
                    HttplogDO hl=new HttplogDO();
                    hl.setAction(jsonObject.getJSONObject("Data").getString("Action"));
                    hl.setQuerySN(jsonObject.getJSONObject("Data").getString("QuerySN"));
                    hl.setTranSN(jsonObject.getJSONObject("Data").getString("TranSN"));
                    hl.setResultData(jsonObject.getJSONObject("Data").getString("ResultData"));
                    httplogRepository.setHttplog(hl);
                    String json=jsonObject.getJSONObject("Data").getString("ResultData");
                    if (jsonObject.getJSONObject("Data").getString("ResultData").equals("[]")){
                        Result.setSuccessResult("操作有误，数据未改变");
                    }else{
                        Result.setSuccessResult("success");
                    }

                }
            }else{
                //请求失败
                String Message =jsonObject.getString("Message");
                Result.setSuccessResult(Message);
            }
        } catch (IOException e) {
            Result.setSuccessResult("err");
            e.printStackTrace();
        }
        return Result;
    }


    /**
     * 批量判定异常订单成功
     * @param idArry
     * @return
     */
    @RequestMapping(value = "/Toexamine",method = RequestMethod.POST)
    @ResponseBody
    public  BaseResult Toexamine(String[] idArry){
        ObjectBaseResult Result=new ObjectBaseResult();
        try {
            tUserWithdrawalsRepository.updateToexamine(idArry);
            Result.setSuccessResult("success");
        }catch (Exception e){
             e.printStackTrace();
            Result.setSuccessResult("err");
        }
        return Result;
    }


    /**
     * 批量冻结或退回异常订单
     * @param id
     * @param stats
     * @param remark
     * @return
     */
    @RequestMapping("/FrozenOrReturn")
    @ResponseBody
    public  BaseResult FrozenOrReturn(String id ,String stats,String remark,String info){
        ObjectBaseResult Result=new ObjectBaseResult();
        //获取当前时间戳
        String noncestr=String.valueOf(System.currentTimeMillis());
        Date date=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String tranSN=dateFormat.format(date).toString();
        //生成请求数据
        TreeMap<String,String> map=new TreeMap<String, String>();
        map.put("accessKey","javamanager");
        map.put("action","UserWithdrawals");
        map.put("idList",id);
        map.put("noncestr",noncestr);
        map.put("remark",remark);
        map.put("status",stats);
        map.put("tranSN",tranSN+"1");
        try {
            //发起请求
            String k=InterfaceUtil.GetAPI(InterfaceUtil.ATURL,map);
            //处理返回数据
            JSONObject jsonObject=JSON.parseObject(k);
            int ErrCode=Integer.parseInt(jsonObject.get("ErrCode").toString());
            //请求成功
            if (ErrCode==0){
                //添加到http日志表
                int ResultCode=Integer.parseInt(jsonObject.getJSONObject("Data").get("ResultCode").toString());
                if (ResultCode==8){
                    HttplogDO hl=new HttplogDO();
                    hl.setAction(jsonObject.getJSONObject("Data").getString("Action"));
                    hl.setQuerySN(jsonObject.getJSONObject("Data").getString("QuerySN"));
                    hl.setTranSN(jsonObject.getJSONObject("Data").getString("TranSN"));
                    hl.setResultData(jsonObject.getJSONObject("Data").getString("ResultData"));
                    httplogRepository.setHttplog(hl);

                    String ResultData=jsonObject.getJSONObject("Data").getString("ResultData");
                    //转JSON数组
                    JSONArray jsonArray=JSONArray.parseArray(ResultData);

                    List<UserSmsLogDO> Slist = new ArrayList<UserSmsLogDO>();

                    //存取操作失败的账号信息和原因
                    StringBuffer masge=new StringBuffer();

                    String content="";

                    SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
                    //返回修改成功的数据，并修改本地数据库状态
                    for (int i=0;i<jsonArray.size();i++){
                        Object obj1 = jsonArray.toArray()[i];
                        JSONObject jo=JSON.parseObject(obj1.toString());
                        String key=jo.getString("Key");
                        String val=jo.getString("Value");
                        TUserWithdrawals tw=tUserWithdrawalsRepository.getTUserWithdrawals(key);
                        if (val.equals("")){
                            com.manji.financesystem.primaryDomain.entiity.UserDO user=userDoRepository.getUserByUserid(Integer.parseInt(tw.getUserId()));
                            UserSmsLogDO userSmsLogDO = new UserSmsLogDO();
                            userSmsLogDO.setUserId(Integer.parseInt(tw.getUserId()));
                            userSmsLogDO.setUserRoleType(tw.getUserRoleTyoe());
                            userSmsLogDO.setUserRoleValue(tw.getUserRoleValue());
                            userSmsLogDO.setType("异常提现操作处理");
                            userSmsLogDO.setUserIp(user.getReg_ip());
                            try {
                            Date d=dateFormat.parse(tw.getAdd_time());
                            if (info.equals("")){
                                info=tw.getAlter();
                             }
                            content="亲！您"+df.format(d)+"的提现申请，因为"+info+"（原因），此次申请提现失败，请尽快核实修改后重新申请。如有疑问致电400-6766-999咨询。[满集网]";
                            userSmsLogDO.setContent(info);
                            userSmsLogDO.setStatus(0);
                            userSmsLogDO.setMobile(user.getMobile());
                            userSmsLogDO.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            userSmsLogDO.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            Slist.add(userSmsLogDO);
                            //修改本地数据状态
                            tUserWithdrawalsRepository.updateStats(key,stats,tw.getRemark()+remark+"<br>");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else{
                            masge.append("提现订单:"+tw.getWithdrawalsNo()+"处理失败!原因:"+val+"<br>");
                        }
                    }
                    //给用户推送短信
                    //systemPushRepository.addInfo(Slist);
                    if (ResultData.equals("[]")){
                        Result.setSuccessResult("操作有误，数据未改变");
                    }else{
                        Result.setSuccessResult("success");
                        Result.setObj(masge.toString());
                    }

                }
            }else{
                //请求失败
                String Message =jsonObject.getString("Message");
                Result.setSuccessResult(Message);
            }
        } catch (IOException e) {
            Result.setSuccessResult("err");
            e.printStackTrace();
        }
        return Result;
    }



    /**
     * 查询待处理提现数据
     * @param Param
     * @return
     */
    @RequestMapping("/getPendingprocessing")
    @ResponseBody
    public  BaseResult getPendingprocessing(WithDrawalsParam Param){
        PagedQueryResult<UserWithdrawalsDO> baseQueryResult=new PagedQueryResult<UserWithdrawalsDO>();
        List<UserWithdrawalsDO> li= userWithdrawalsRepository.findpageWithdrawalsinfo(Param);
        baseQueryResult.setResultList(li);
        baseQueryResult.setTotalCount(userWithdrawalsRepository.findpageWithdrawalsinfocount());
        return baseQueryResult;
    }


    @RequestMapping("/test")
    @ResponseBody
    public BaseResult  test(){
        ObjectBaseResult Result=new ObjectBaseResult();
        synchronizationWithdrawalsService.SynchronizationWithdrawals();
        return Result;
    }







    }
