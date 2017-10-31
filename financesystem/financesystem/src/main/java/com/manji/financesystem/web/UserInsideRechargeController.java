package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseQueryResult;
import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.MyDealDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDMCountDO;
import com.manji.financesystem.primaryDomain.entiity.UserInsideRechargeDO;
import com.manji.financesystem.primaryDomain.repository.UserInsideRechargeRepository;
import com.manji.financesystem.requestParam.ExamRechargeParam;
import com.manji.financesystem.requestParam.InteriorRechargeRequestParam;
import com.manji.financesystem.requestParam.UserInsideRechargeParam;
import com.manji.financesystem.requestParam.UserQueryNormalRechargeListParam;
import com.manji.financesystem.secondaryDomain.entity.InteriorRechargeDetailDO;
import com.manji.financesystem.service.UserInsideRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/3.
 */
@RequestMapping("/userInsideRecharge")
@Controller
public class UserInsideRechargeController extends BaseController{

    @Autowired
    private UserInsideRechargeRepository userInsideRechargeRepository;

    @Autowired
    private UserInsideRechargeService userInsideRechargeService;

    @RequestMapping("/userInsideRecharge.html")
    public String getUserInsideRecharge(Model model ,UserInsideRechargeParam param){
        List<UserInsideRechargeDO> list = userInsideRechargeRepository.getUserInsideRecharge(param);
        model.addAttribute("UserInsideRechargeResult",list);
        return "insideRecharge/userInsideRecharge";
    }

    /**
     * 审批充值订单
     * @return
     */
    @RequestMapping("/approval.html")
    public String approvalUserInsideRecharge(){

        return "insideRecharge/approval";
    }

    /**
     * 获取内部充值记录,分页 每页20条
     * @return
     */
    @RequestMapping("/getUserInsideRechargeInfo")
    @ResponseBody
    public BaseResult getUserInsideRechargeInfo(UserInsideRechargeParam param){
        BaseQueryResult<UserInsideRechargeDO> baseQueryResult=new BaseQueryResult<UserInsideRechargeDO>();
        List<UserInsideRechargeDO> list = userInsideRechargeRepository.getUserInsideRecharge(param);

        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("查询成功");

        return baseQueryResult;
    }

    /**
     * 查询数据总条数
     * @return
     */
    @RequestMapping("getCount")
    @ResponseBody
    public BaseResult getCount(UserInsideRechargeParam param){
        ObjectBaseResult<UserInsideRechargeCountDO> baseQueryResult=new ObjectBaseResult<UserInsideRechargeCountDO>();
        UserInsideRechargeCountDO count = userInsideRechargeRepository.getCount(param);
        baseQueryResult.setObj(count);
        return baseQueryResult;
    }

    /**
     * 查询要审批的充值订单
     * @return
     */
    @RequestMapping("/queryInsideRechargeOrder")
    @ResponseBody
    public BaseResult queryInsideRechargeOrder(InteriorRechargeRequestParam param){

        PagedQueryResult<InteriorRechargeDetailDO> baseQueryResult = new PagedQueryResult<InteriorRechargeDetailDO>();

        List<InteriorRechargeDetailDO> list = userInsideRechargeRepository.queryInsideRechargeOrder(param);
        int countNum = userInsideRechargeRepository.getCountInsideRechargeOrder(param);
        baseQueryResult.setTotalCount(countNum);
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }

    /**
     * 查询会计审批的充值订单
     * @return
     */
    @RequestMapping("/queryMyDealOrder")
    @ResponseBody
    public BaseResult queryMyDealOrder(InteriorRechargeRequestParam param){
        PagedQueryResult<InteriorRechargeDetailDO> baseQueryResult = new PagedQueryResult<InteriorRechargeDetailDO>();

        List<InteriorRechargeDetailDO> list = userInsideRechargeRepository.queryMyDealOrder(param);
        int countNum = userInsideRechargeRepository.getCountMyDealOrder(param);
        baseQueryResult.setTotalCount(countNum);
        baseQueryResult.setResultList(list);
        baseQueryResult.setSuccessResult("SUCCESS");
        return baseQueryResult;
    }
    /**
     * 出纳确认充值
     * @param param
     * @return
     */
    @RequestMapping("/examRechargeOrder")
    @ResponseBody
    public BaseResult examRechargeOrder(ExamRechargeParam param){
        String status = "";
        ObjectBaseResult<String> obj = new ObjectBaseResult<String>();
        if(param != null){
            status = userInsideRechargeService.approvalOrder(param);
        }
        if(status.equals("SUCCESS")){
            obj.setObj("充值成功");
        }else if(status.equals("EMPTY")){
            obj.setObj("充值失败，参数错误");
        }else if(status.equals("ERROR")){
            obj.setObj("充值失败，远程接口异常,请稍后再试");
        }
        obj.setSuccessResult("SUCCESS");
        return obj;
    }

    /**
     * 会计审批出纳的充值申请
     * @param param
     * @return
     */
    @RequestMapping("/examByAccountor")
    @ResponseBody
    public BaseResult examByAccountor(ExamRechargeParam param){
        ObjectBaseResult obj = new ObjectBaseResult();
        String message = userInsideRechargeService.examByAccountor(param);
        if(message.equals("SUCCESS")){
            obj.setObj("审批成功");
        }else{
            obj.setObj("审批失败，参数错误");
        }
        obj.setSuccessResult("SUCCESS");
        return obj;
    }
}
