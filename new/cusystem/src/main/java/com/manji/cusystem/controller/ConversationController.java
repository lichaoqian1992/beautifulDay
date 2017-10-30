package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.ConversationService;
import com.manji.cusystem.vo.OrderVo;
import com.manji.cusystem.vo.conversation.AddConversationVo;
import com.manji.cusystem.vo.conversation.CustomerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/8/31.
 */
@RestController
@RequestMapping(value = "/con")
public class ConversationController extends BaseController{

    @Autowired
    private ConversationService service;

    /**
     * 根据   手机号、账号、商家名称查询商家、用户、合伙人、非满集用户的信息
     * cus_name :输入的电话号码、商家名称、账号等
     * @return
     */
    @RequestMapping(value = "/findCustomer")
    public String findCustomer(@Param("userName") String userName,@Param("mobile") String mobile,@Param("shopName") String shopName,@Param("userType") String userType,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){

            Account user = (Account) result.getResult();
            result = service.findCustomer(userName,mobile,shopName,userType,user);
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 根据账号查询资金流水、满意券流水、订单详情等信息
     * @param userId    账号
     * @param tabType      类型
     *  @Param userType     用户类型
     * @return
     */
    @RequestMapping("/selectCustomerInfo")
    public String selectCustomerInfo(@Param("mobile") String mobile,@Param("userId") String userId,@Param("tabType") String tabType,@Param("userType") String userType,@Param("pageSize")String pageSize,@Param("pageNumer")String pageNumber,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){

            Account user = (Account) result.getResult();

            result = service.selectCustomerInfo(mobile,userId,tabType,userType,user,pageSize,pageNumber);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }
    /**
     * 根据账号查询基本信息（类型、电话、姓名、注册时间、账户状态、账号状态等）
     * @param cus_name
     * @return
     */
    @RequestMapping(value = "/findCustomerInfo")
    public String findCustomerInfo(@Param("cus_name") String cus_name,@Param("cus_type") String cus_type,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){

            Account user = (Account) result.getResult();
            result = service.findCustomerInfo(cus_name,cus_type,user);

        }


        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 点击新增会话记录按钮
     * @return
     */
    @RequestMapping(value = "/addNewConversation")
    public String addNewConversation(@Param("userName") String userName,@Param("roleType") String roleType, HttpServletRequest request){

        HttpSession session = request.getSession();
        Account user = (Account)session.getAttribute("user");

        BaseResult result = service.addNewConversation(user,userName,roleType);
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 添加会话信息
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/addConversation")
    public String addConversation(@Valid CustomerVo customerVo,BindingResult bindingResult, HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }else{

                Account user = (Account)result.getResult();
                result = service.addConversation(customerVo,user,"add","","");

            }
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询我的会话记录
     * @param vo
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/findConversation" ,method = RequestMethod.GET)
    public String findConversation(@Valid AddConversationVo vo, BindingResult bindingResult,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                result = service.findConversation(vo,user);
            }
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 修改会话信息
     * @param customerVo
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateConversation")
    public String updateConversation(@Valid CustomerVo customerVo,@Param("cus_id") String cus_id,BindingResult bindingResult, HttpServletRequest request){

        BaseResult result = new BaseResult();
        if(cus_id == null || cus_id.equals("")){
            result.setCode("404");
            result.setContent("会话id cus_id不能为空");
            return JSONObject.toJSONString(result);
        }
        if(bindingResult.hasErrors()){
            result.setCode("404");
            result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }else{
            HttpSession session = request.getSession();
            Account user = (Account)session.getAttribute("user");
            result.setResult(service.addConversation(customerVo,user,"update",cus_id,""));
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询一级来电原因
     * @return
     */
    @RequestMapping(value = "/findFirstReason")
    public String findFirstReason(@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().equals("200")){
            result = service.findFirstReason();
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**\
     * 查询二级来电原因
     * @return
     */
    @RequestMapping(value = "/findSecondReason")
    public String findSecondReason(@Param("code") String code,@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().equals("200")){
            result = service.findSecondReason(code);
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /*****************************订单外呼******************************************************************************************/

    /**
     * 查询订单外呼记录
     * @return
     */
    @RequestMapping(value = "/findOrderAndCon")
    public String findOrderAndCon(OrderVo vo,@Param("sessionId") String sessionId){

        BaseResult result = new BaseResult();

        if(logins(sessionId).getCode().equals("200")){
            result = service.findOrderAndCon(vo);
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 查询订单详情
     * @return
     */
    @RequestMapping(value = "/findOrderDetail")
    public String findOrderDetail(@Param("orderId") String orderId,@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);

        if(result.getCode().equals("200")){
            result = service.findOrderDetail(orderId);
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }

    /**
     * 订单外呼页面的新增会话记录
     * @return
     */
    @RequestMapping(value = "/addConversationByOrder")
    public String addConversationByOrder(@Valid CustomerVo customerVo,BindingResult bindingResult, HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));

        if(result.getCode().equals("200")){
            if(request.getParameter("orderId") == null || "".equals(request.getParameter("orderId"))){
                result.setCode("404");
                result.setContent("订单id orderId不能为空");
                result.setResult("");
                return JSONObject.toJSON(result).toString();
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
            }else{
                Account user = (Account)result.getResult();
                result = service.addConversation(customerVo,user,"add","",request.getParameter("orderId"));

            }
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询订单外呼里面的会话记录
     * @param mobile      进线号码
     * @param sessionId    sessionId
     * @return
     */
    @RequestMapping(value = "/findOrderConversation")
    public String findOrderConversation(@Param("mobile") String mobile,@Param("pageSize") String pageSize,@Param("pageNumber") String pageNumber,@Param("sessionId") String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){

            Account user = (Account)result.getResult();
            result = service.findOrderConversation(mobile,pageSize,pageNumber,user.getUserId());

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }


    /********************************获取详情**************************************************************************************************/

    /*@RequestMapping(value = "/findInfoByMobile")
    public String findInfoByMobile(@Param("mobile") String mobile,@Param("sessionId") String sessionId,@Param("userType") String userType,@Param("tabType") String tabType,@Param("pageSize")String pageSize,@Param("pageNumer")String pageNumber){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            result = service.findInfoByMobile(user,mobile,userType,tabType,pageNumber,pageSize);
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }*/


}
