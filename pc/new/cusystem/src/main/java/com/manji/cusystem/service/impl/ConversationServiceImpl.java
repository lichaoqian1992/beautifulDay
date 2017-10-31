package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.base.PageResult;
import com.manji.cusystem.dao.Conversation;
import com.manji.cusystem.dao.Reason;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.feign.DataHostFeignService;
import com.manji.cusystem.feign.OrServiceFeignService;
import com.manji.cusystem.mapper.ConversationMapper;
import com.manji.cusystem.service.ConversationService;
import com.manji.cusystem.service.LogService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.ExcelUtils;
import com.manji.cusystem.utils.PageUtils;
import com.manji.cusystem.vo.OrderVo;
import com.manji.cusystem.vo.common.ExcelVo;
import com.manji.cusystem.vo.common.LogVo;
import com.manji.cusystem.vo.conversation.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */
@Service
public class ConversationServiceImpl implements ConversationService {


    private static final Logger logger = Logger.getLogger(ConversationServiceImpl.class);

    @Autowired
    private DataHostFeignService dataHostFeignService;

    @Autowired
    private OrServiceFeignService orServiceFeignService;

    @Autowired
    private LogService service;

    @Autowired
    private ConversationMapper mapper;

    BaseResult result = new BaseResult();

    @Transactional
    public BaseResult addConversation(CustomerVo vo,Account user,String type,String cus_id,String orderId) {

        logger.info("=====调用addConversation接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(vo)+"==============");
        //1.保存会话信息
        vo.setCus_ctime(new DatesUtils().time());
        vo.setCus_isdel("1");
        //生成会话单号，规则：CON+年月日时分秒毫秒
        ConversationVo conversationVo = new ConversationVo();
        conversationVo.setCus_user_id(user.getUserId());
        conversationVo.setCus_user_name(user.getRealName());
        conversationVo.setCus_code(user.getJob());
        conversationVo.setCus_reasonid(vo.getCus_reasonid());
        conversationVo.setCus_ctime(vo.getCus_ctime());
        conversationVo.setCus_from(vo.getCus_from());
        conversationVo.setCus_isdel("1");
        conversationVo.setCus_ltime(vo.getCus_ltime());
        conversationVo.setCus_reason(vo.getCus_reason());
        conversationVo.setCus_remark(vo.getCus_remark());
        conversationVo.setCus_way(vo.getCus_way());
        String content = "";
        int a = 0;
        boolean flag = false;
        int cus_cid = 0;
        if(type.equals("add")){
            conversationVo.setCus_result("1");
            //新增账户信息,新增之前查询账户的状态和注册时间
            String str = "";
            str = dataHostFeignService.getUserRegister(vo.getCus_account(),vo.getCus_type());
            JSONObject object = JSONObject.parseObject(str);

            if(object.get("code").toString().equals("200")){
                mapper.addConversation(conversationVo);//保存会话信息
                AddCustomerVo addCustomerVo = new AddCustomerVo();
                JSONObject back = JSONObject.parseObject(object.getJSONArray("result").get(0).toString());
                String status = back.get("status").toString();
                String state = back.get("state").toString();
                String reg_time = back.get("reg_time").toString();
                String role_type = back.get("role_type").toString();
                String area = "";
                if(vo.getCus_type().equals("Buyer")){
                    addCustomerVo.setCus_user_time(reg_time);
                }else{
                    addCustomerVo.setCus_shop_time(reg_time);
                    area = back.get("area").toString();
                }
                addCustomerVo.setCus_account(vo.getCus_account());
                addCustomerVo.setCus_area(area);
                addCustomerVo.setCus_mobile(vo.getCus_mobile());
                addCustomerVo.setCus_name(vo.getCus_name());
                addCustomerVo.setCus_tel(vo.getCus_tel());
                addCustomerVo.setCus_state(Integer.parseInt(state));
                addCustomerVo.setCus_status(Integer.parseInt(status));
                addCustomerVo.setCus_type(role_type);
                flag = mapper.addCusInfo(addCustomerVo);
                //查询客户信息表的id和会话信息表的id
                int cus_info_id = Integer.parseInt(addCustomerVo.getCus_id());//客户信息表id
                cus_cid = Integer.parseInt(conversationVo.getCus_id());//会话信息表id
                //新增关联关系表信息
                boolean flag3 = mapper.addConAndSheet(cus_cid,cus_info_id);
                if(orderId != null && !"".equals(orderId)){
                    //保存订单和会话记录的关系
                    mapper.addConAndOrder(cus_cid,Integer.parseInt(orderId));
                }
                content = user.getRealName()+"在"+new DatesUtils().time()+"新增会话记录";
            }else if(object.get("code").toString().equals("404")){
                result.setCode("404");
                result.setContent("数据不存在，请检查");
                result.setResult("");
            }
        }else if(type.equals("update")){
            conversationVo.setCus_id(cus_id);
            a = mapper.updateCusInfo(conversationVo);
            content = user.getRealName()+"在"+new DatesUtils().time()+"修改了编号为"+conversationVo.getCus_id()+"的会话记录";
        }
        //2.保存日志信息
        if(flag || a > 0){
            LogVo log = new LogVo();
            log.setCus_type(type);
            log.setCus_content(content);
            log.setCus_user_id(Integer.parseInt(user.getUserId()));
            log.setCus_user_name(user.getUserName());
            log.setCus_ctime(new DatesUtils().time());
            log.setCus_isdel(1);
            boolean b = service.addLog(log);
            if(b){
                result.setCode("200");
                result.setContent("操作成功");
                result.setResult(cus_cid);
            }
        }
        //3.返回数据

        logger.info("=====调用addConversation接口结束，结束时间："+new DatesUtils().time()+",返回值："+ JSONObject.toJSONString(result)+"==============");

        return result;
    }

    /**
     * 查询我的会话记录
     * @param vo
     * @return
     */
    public BaseResult findConversation(AddConversationVo vo,Account user){

        logger.info("=====调用findConversation接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(vo)+"==============");
        //1.设置默认的分页  默认第一页，每页15条
        int pageSize = 15;
        //2.处理查询条件
        if(vo.getPageSize() == 0){
            vo.setPageSize(pageSize);
        }
        if(vo.getPageNum() != 0){
            vo.setPageNum(vo.getPageNum()-1);
        }
        List<Conversation> list = mapper.findConversation(vo,user.getUserId());
        if(list != null){
            for(int i=0;i<list.size();i++){
                //查询工单号
                List<String> sheetId = mapper.findSheet(list.get(i).getCus_id());
                System.out.println(sheetId);
                if(sheetId.size()>0){
                    list.get(i).setCus_result("生成工单");
                    list.get(i).setSheetNo(sheetId);
                }else{
                    list.get(i).setCus_result("已完成");
                }
                //判断数据是否可以编辑
                //在创建时间24小时后不可编辑
                try{

                    if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(i).getCus_ctime()).getTime()+24*60*60*1000 < new Date().getTime()){
                        list.get(i).setIsEdit("不可编辑");
                    }else{
                        list.get(i).setIsEdit("可编辑");
                    }
                }catch(Exception e){

                }
            }
        }
        int count = mapper.findConversationCount(vo,"1");
        //int count = 0;
        PageResult page = new PageUtils().pageInit(vo.getPageNum(),vo.getPageSize(),count);

        if(list != null && list.size() > 0){
            LogVo log = new LogVo();
            log.setCus_type("select");
            log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"查询了会话记录，查询参数："+JSONObject.toJSONString(vo));
            log.setCus_user_id(Integer.parseInt(user.getUserId()));
            log.setCus_user_name(user.getUserName());
            log.setCus_ctime(new DatesUtils().time());
            log.setCus_isdel(1);
            boolean b = service.addLog(log);
            result.setCode("200");
            result.setContent("查询成功");
            result.setPage(page);
            result.setResult(list);
        }else{
            result.setCode("404");
            result.setContent("无数据");
            result.setPage(page);
            result.setResult(list);
        }
        logger.info("=====调用findConversation接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }

    /**
     * 根据手机号、账号、名称等查询客户信息
     * @param cus_name
     * @return
     */
    @Override
    public BaseResult findCustomer(String cus_name, String mobile,String name,String userType,Account user) {

        logger.info("=====调用findCustomer接口开始，调用时间："+new DatesUtils().time()+",参数值：cus_name="+ cus_name+"==============");
        //1.判断参数是否为空
        if((cus_name == null || "".equals(cus_name)) && (mobile == null || "".equals(mobile)) && (name == null || "".equals(name))){
            result.setCode("404");
            result.setContent("参数userName,mobile,shopName至少一个不能为空");
            result.setResult("");
            return result;
        }
        if(userType == null || "".equals(userType)){
            result.setCode("404");
            result.setContent("参数userType不能为空");
            result.setResult("");
            return result;
        }
        String pageSize = "", pageNumber = "";
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "10";
        }
        if(pageNumber == null || "".equals(pageNumber)){
            pageNumber = "1";
        }
        //2.调用远程接口，查询数据
        try{
            JSONObject back_userInfo = null;
            //根据传入的用户类型查询信息
            if(userType.equals("Buyer")){
                back_userInfo = dataHostFeignService.getAccountByName(cus_name,mobile,name);//用户信息
            }else if(userType.equals("Shop")){
                back_userInfo = dataHostFeignService.getShopInfo(cus_name,mobile,name);//商家信息
            }else if(userType.equals("Agent")){
                back_userInfo = dataHostFeignService.getAgentInfo(cus_name,mobile,name);//合伙人信息
            }else if(userType.equals("other")){
                back_userInfo = dataHostFeignService.getOtherInfo(mobile);//合伙人信息
            }

            //3.判断返回的数据
            result.setCode(back_userInfo.getString("code"));
            result.setContent(back_userInfo.getString("message"));
            result.setPage(null);
            result.setResult(back_userInfo.get("result"));
            //记录日志
            LogVo log = new LogVo();
            log.setCus_type("select");
            log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"查询了客户"+cus_name+"的信息");
            log.setCus_user_id(Integer.parseInt(user.getUserId()));
            log.setCus_user_name(user.getUserName());
            log.setCus_ctime(new DatesUtils().time());
            log.setCus_isdel(1);
            boolean b = service.addLog(log);

            logger.info("=====调用findCustomer接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");

        }catch(Exception e){
            e.printStackTrace();
            result.setCode("403");
            result.setPage(null);
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        return result;
    }

    /**
     * 查询流水、订单详情等信息
     * @return
     */
    public BaseResult selectCustomerInfo(String mobile,String userId,String tabType,String userType,Account user,String pageSize,String pageNumber){

        logger.info("=====调用selectCustomerInfo接口开始，调用时间："+new DatesUtils().time()+",参数值：userId="+ userId+"tabType="+tabType+mobile+userType+"=============");
        //1.判断参数是否为空
        if(userId == null || "".equals(userId)){
            result.setCode("404");
            result.setContent("参数userId不能为空");
            return result;
        }
        if(tabType == null || "".equals(tabType)){
            result.setCode("404");
            result.setContent("参数tabType不能为空");
            return result;
        }
        if(userType == null || "".equals(userType)){
            result.setCode("404");
            result.setContent("参数userType不能为空");
            return result;
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "10";
        }
        if(pageNumber == null || "".equals(pageNumber)){
            pageNumber = "1";
        }
        JSONObject back_userAccount = null;
        if(userType.equals("Buyer")){
            back_userAccount = dataHostFeignService.getUserAccount(mobile,userId,tabType,pageSize,pageNumber);//用户详情
        }else if(userType.equals("Shop")){
            back_userAccount = dataHostFeignService.getShopDetail(mobile,userId,tabType,pageSize,pageNumber);//商家详情
        }else if(userType.equals("Agent")){
            back_userAccount = dataHostFeignService.getAgentDetail(mobile,userId,tabType,pageSize,pageNumber);//代理商详情
        }else if(userType.equals("Other")){
            back_userAccount = dataHostFeignService.getOtherDetail(mobile,userId,tabType,pageSize,pageNumber);//其他详情
        }

        result.setCode(back_userAccount.getString("code"));
        result.setContent(back_userAccount.getString("message"));
        result.setPage(null);
        result.setResult(back_userAccount.get("result"));
        //记录日志
        LogVo log = new LogVo();
        log.setCus_type("select");
        log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"查询了客户的"+tabType+"的信息");
        log.setCus_user_id(Integer.parseInt(user.getUserId()));
        log.setCus_user_name(user.getUserName());
        log.setCus_ctime(new DatesUtils().time());
        log.setCus_isdel(1);
        boolean b = service.addLog(log);

        logger.info("=====调用selectCustomerInfo接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");
        return result;


    }

    /**
     * 根据账号查询客户信息
     * @param user_name
     * @return
     */
    @Override
    public BaseResult findCustomerInfo(String user_name,String cus_type,Account user) {

        logger.info("=====调用findCustomerInfo接口开始，调用时间："+new DatesUtils().time()+",参数值：user_name="+ user_name+"==============");
        if(user_name == null || user_name.equals("")){
            result.setCode("404");
            result.setContent("参数user_name不能为空");
            return result;
        }
        if(cus_type == null || cus_type.equals("")){
            result.setCode("404");
            result.setContent("参数cus_type不能为空");
            return result;
        }
        //2.调用接口
        String back = dataHostFeignService.getUserRegister(user_name,cus_type);
        //3.判断返回的数据
        if(JSONObject.parseObject(back).get("code").toString().equals("200")){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(JSONObject.parseObject(back).get("result"));
        }else{
            result.setCode("404");
            result.setContent("数据不存在");
        }
        logger.info("=====调用findCustomer接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");

        return result;
    }

    /**
     * 查询一级来电原因
     * @return
     */
    @Override
    public BaseResult findFirstReason() {

        logger.info("=====调用findFirstReason接口开始，调用时间："+new DatesUtils().time()+"==============");
        List<Reason> reasons = mapper.findFirstReason();

        if(reasons != null && reasons.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(reasons);
        }else{
            result.setCode("200");
            result.setContent("查询成功,但没有数据");
            result.setResult(reasons);
        }

        logger.info("=====调用findFirstReason接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }
    /**
     * 查询二级来电原因
     * @return
     */
    @RequestMapping(value = "/findSecondReason")
    public BaseResult findSecondReason(String code){

        logger.info("=====调用findSecondReason接口开始，调用时间："+new DatesUtils().time()+"参数值：code="+code+"=============");

        if(code == null || "".equals(code)){
            result.setCode("404");
            result.setContent("参数code不能发为空");
            return result;
        }
        code = code+"_%";
        List<Reason> secondReason = mapper.findSecondReason(code);

        if(secondReason != null && secondReason.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(secondReason);
        }else{
            result.setCode("200");
            result.setContent("暂无数据");
            result.setResult(secondReason);
        }

        logger.info("=====调用findSecondReason接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");
        return result;

    }

    /**
     * 点击新增会话记录按钮
     * @param user
     * @return
     */
    @Override
    public BaseResult addNewConversation(Account user,String userName,String roleType) {

        logger.info("=====调用addNewConversation接口开始，调用时间："+new DatesUtils().time()+"参数值：code="+user+userName+roleType+"=============");
        Conversation conversation = new Conversation();
        //1.检验参数
        if(userName == null || userName.equals("")){
            result.setCode("404");
            result.setContent("参数userName不能为空");
            result.setResult("");
            return result;
        }
        if(roleType == null || roleType.equals("")){
            result.setCode("404");
            result.setContent("参数roleType不能为空");
            result.setResult("");
            return result;
        }
        if(user == null){
            result.setCode("404");
            result.setContent("用户未登录");
            result.setResult("");
            return result;
        }
        String str = dataHostFeignService.getUserRegister(userName,roleType);
        JSONObject object = JSONObject.parseObject(str);
        if(object.get("code").toString().equals("200")){
            JSONObject back = JSONObject.parseObject(object.getJSONArray("result").get(0).toString());
            String mobile = back.get("mobile").toString();
            String name = back.get("name").toString();
            String area = "";
            if(!roleType.equals("Buyer")){
                area = back.get("area").toString();
            }
            conversation.setCus_mobile(mobile);
            conversation.setCus_name(name);
            conversation.setCus_account(userName);
            conversation.setCus_area(area);
            conversation.setCus_type(roleType);
            conversation.setCus_code(user.getJob());
            //2.保存日志信息
            LogVo log = new LogVo();
            log.setCus_type("select");
            log.setCus_content(user.getUserName()+"在"+new DatesUtils().time()+"查询客户"+userName+"的信息");
            log.setCus_user_id(Integer.parseInt(user.getUserId()));
            log.setCus_user_name(user.getUserName());
            log.setCus_ctime(new DatesUtils().time());
            log.setCus_isdel(1);
            boolean b = service.addLog(log);
            if(b){
                result.setCode("200");
                result.setContent("操作成功");
                result.setResult(conversation);
            }

        }
        logger.info("=====调用addNewConversation接口结束，结束时间："+new DatesUtils().time()+"返回值："+JSONObject.toJSONString(result)+"=============");
        return result;
    }

    /************************************订单外呼相关*****************************************************************************/

    /**
     * 查询订单外呼记录
     * @param vo
     * @return
     */
    public BaseResult findOrderAndCon(OrderVo vo){

        BaseResult result = new BaseResult();
        try{
            logger.info("=====调用findOrderAndCon接口开始，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(vo)+"=============");
            //2.处理参数
            if(vo.getType() == null || "".equals(vo.getType())){
                vo.setType("1");
            }
            String jsonToString = JSONObject.toJSONString(vo, SerializerFeature.WriteMapNullValue);
            //3.返回值
            JSONObject back = dataHostFeignService.findOrderAndCon(jsonToString);
            //System.out.println(back+"=================");
            if(back.get("code").toString().equals("200")){
                result.setCode("200");
                result.setContent("查询成功");
                result.setResult(back.get("result"));
            }else if(back.get("code").toString().equals("404")){
                result.setCode("404");
                result.setContent("数据不存在");

            }

        }catch (Exception e){
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("=====调用findOrderAndCon接口结束，结束时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"=============");
        return result;
    }

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public BaseResult findOrderDetail(String orderId) {

        try{
            logger.info("=====调用findOrderDetail接口开始，调用时间："+new DatesUtils().time()+"参数值："+orderId+"=============");
            //1.校验参数
            if(orderId == null || "".equals(orderId)){
                result.setCode("404");
                result.setContent("参数orderId不能为空");
                result.setResult("");
                return result;
            }
            //2.返回值
            JSONObject back = dataHostFeignService.findOrderDetail(orderId);

            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(back.get("result"));

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("=====调用findOrderDetail接口开始，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"=============");
        return result;
    }

    /**
     * 查询工单记录
     * @param mobile
     * @return
     */
    public BaseResult findOrderConversation(String mobile,String pageSize,String pageNumber,String userId){

        try{
            logger.info("=====调用findOrderConversation接口开始，调用时间："+new DatesUtils().time()+"参数值："+mobile+"=============");

            if(mobile == null || "".equals(mobile) || pageSize == null || "".equals(pageSize) || pageNumber == null || "".equals(pageNumber)){
                result.setCode("404");
                result.setContent("进线号码mobile,pageNumber,pageSize不能为空");
                result.setResult("");
                return result;
            }
            AddConversationVo vo = new AddConversationVo();
            vo.setOther(mobile);
            vo.setPageNum(Integer.parseInt(pageNumber)-1);
            vo.setPageSize(Integer.parseInt(pageSize));
            List<Conversation> list = mapper.findOrderConversation(vo,userId);
            int count = mapper.findOrderConversationCount(vo,userId);

            PageResult page = new PageUtils().pageInit(vo.getPageNum(),vo.getPageSize(),count);
            if(list != null && list.size() > 0){
                result.setCode("200");
                result.setContent("查询成功");
                result.setPage(page);
                result.setResult(list);
            }else{
                result.setCode("404");
                result.setContent("暂无数据");
                result.setResult("");
            }

        }catch (Exception e){
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());

        }
        return result;
    }

    /**
     * 导出会话记录
     * @param vo
     * @return
     */
    public String toExcel(AddConversationVo vo,Account user){

        logger.info("=====调用toExcel接口开始，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(vo)+"=============");

        String[] title = {"序号","进线号码","来电时间","来源渠道","来话方向","客户类型","客户姓名","客户账号","联系电话","来电原因","备注","话务员工号","处理结果"};
        String excelName = "会话记录表";
        List<Conversation> list = (List<Conversation>) findConversation(vo,user).getResult();
        String[][] content = new String[list.size()][title.length];


        ExcelVo excelVo = new ExcelVo();
        excelVo.setStartTime(vo.getStime());
        excelVo.setEndTime(vo.getEtime());
        //处理返回值
        for(int i=0;i<list.size();i++){
            content[i][0] = i+1+"";
            content[i][1] = list.get(i).getCus_tel();
            content[i][2] = list.get(i).getCus_ltime();
            content[i][3] = list.get(i).getCus_from();
            content[i][4] = list.get(i).getCus_way();
            content[i][5] = list.get(i).getCus_type();
            content[i][6] = list.get(i).getCus_name();
            content[i][7] = list.get(i).getCus_account();
            content[i][8] = list.get(i).getCus_mobile();
            content[i][9] = list.get(i).getCus_reason();
            content[i][10] = list.get(i).getCus_remark();
            content[i][11] = list.get(i).getCus_code();
            content[i][12] = list.get(i).getCus_result();
        }
        String path = ExcelUtils.excel(excelVo,title,excelName,content);

        return path;
    }

    /**
     * 获取今日各个时间的会话记录信息
     * @return
     */
    @Override
    public BaseResult getConversationToday() {

        String today = new DatesUtils().getToday()+"%";

        List<Conversation> list = mapper.getConversationToday(today);

        //处理结果
        Map<String,String> map = new HashMap<String, String>();
        String[] str = {"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty","twenty-one","twenty-two","twenty-three","twenty-four"};
        for(int j=0;j<24;j++){
            int a = 0;
            for(int i=0;i<list.size();i++){
                String s = "";
                if(j < 10){
                    s = "0"+j;
                }else{
                    s = j+"";
                }
                System.out.println(s);
                if(list.get(i).getCus_ctime().equals(s)){
                    a++;
                }
            }
            map.put(str[j],a+"");
        }
        result.setCode("200");
        result.setContent("查询成功");
        result.setResult(map);
        return result;
    }

    /**
     * 获取工单信息和会话信息
     * @param user
     * @param mobile
     * @param tabType
     * @param pageNumber
     * @param pageSize
     * @return
     */
    //@Override
    /*public BaseResult findInfoByMobile(Account user, String mobile,String userType, String tabType, String pageNumber, String pageSize) {

        if(mobile == null || "".equals(mobile)){
            result.setCode("404");
            result.setContent("参数mobile必须有值");
            result.setResult("");
            return result;
        }
        if(tabType == null || "".equals(tabType)){
            result.setCode("404");
            result.setContent("参数tabType不能为空");
            return result;
        }
        if(pageSize == null || "".equals(pageSize)){
            pageSize = "10";
        }
        if(pageNumber == null || "".equals(pageNumber)){
            pageNumber = "1";
        }

        JSONObject back_userAccount = null;
        if(userType.equals("Buyer")){
            back_userAccount = dataHostFeignService.getUserAccount(mobile,tabType,pageSize,pageNumber);//用户详情
        }else if(userType.equals("Shop")){
            back_userAccount = dataHostFeignService.getShopDetail(mobile,tabType,pageSize,pageNumber);//商家详情
        }

        result.setCode("200");
        result.setContent("查询成功");
        result.setResult(back_userAccount);
        //记录日志
        LogVo log = new LogVo();
        log.setCus_type("select");
        log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"查询了客户的"+tabType+"的信息");
        log.setCus_user_id(Integer.parseInt(user.getUserId()));
        log.setCus_user_name(user.getUserName());
        log.setCus_ctime(new DatesUtils().time());
        log.setCus_isdel(1);
        boolean b = service.addLog(log);

        logger.info("=====调用selectCustomerInfo接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");

        return result;

    }*/
}
