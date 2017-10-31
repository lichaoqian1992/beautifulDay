package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.base.PageResult;
import com.manji.cusystem.dao.Conversation;
import com.manji.cusystem.dao.Reason;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.dao.conversation.ConversationCount;
import com.manji.cusystem.feign.DataHostFeignService;
import com.manji.cusystem.feign.UServiceFeignService;
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

    private static final String Jurisdiction = "/con/editCon";

    private static final String project = "0001";

    @Autowired
    private DataHostFeignService dataHostFeignService;

    @Autowired
    private UServiceFeignService uServiceFeignService;

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
        conversationVo.setCus_user_account(user.getUserName());
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
        int a = 0,c = 0;
        boolean flag = false;
        int cus_cid = 0;

        //新增账户信息,新增之前查询账户的状态和注册时间
        String str = "";
        str = dataHostFeignService.getUserRegister(vo.getCus_account(),vo.getCus_type());
        JSONObject object = JSONObject.parseObject(str);
        AddCustomerVo addCustomerVo = new AddCustomerVo();

        if(object.get("code").toString().equals("200")){
            String state = null;
            JSONObject back = JSONObject.parseObject(object.getJSONArray("result").get(0).toString());
            String status = back.get("status").toString();
            if(back.get("state") != null){

                state = back.get("state").toString();
            }
            String reg_time = back.get("reg_time").toString();
            //String role_type = back.get("role_type").toString();
            String area = vo.getCus_area();
            if(vo.getCus_type().equals("Buyer")){
                addCustomerVo.setCus_user_time(reg_time);
            }else{
                addCustomerVo.setCus_shop_time(reg_time);
                if(area.equals("")){

                    area = back.get("area").toString();
                }
            }
            addCustomerVo.setCus_account(vo.getCus_account());
            addCustomerVo.setCus_area(area);
            addCustomerVo.setCus_mobile(vo.getCus_mobile());
            addCustomerVo.setCus_name(vo.getCus_name());
            addCustomerVo.setCus_tel(vo.getCus_tel());
            if(state != null && !"".equals(state)){

                addCustomerVo.setCus_state(Integer.parseInt(state));
            }
            addCustomerVo.setCus_status(Integer.parseInt(status));
            addCustomerVo.setCus_type(vo.getCus_type());

        }else if(object.get("code").toString().equals("404")){
            result.setCode("404");
            result.setContent("数据不存在，请检查");
            result.setResult("");
        }

        if(type.equals("add")){
            conversationVo.setCus_result("1");
            mapper.addConversation(conversationVo);//保存会话信息
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
        }else if(type.equals("update")){
            conversationVo.setCus_id(cus_id);
            addCustomerVo.setCus_id(vo.getCus_info_id());
            //修改会话信息
            a = mapper.updateCusInfo(conversationVo);
            //修改客户信息
            c = mapper.updateCusInfos(addCustomerVo);
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
        PageResult page = null;
        if(list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //查询工单号
                List<Map<String,Object>> sheetId = mapper.findSheet(list.get(i).getCus_id());
                System.out.println(sheetId);
                if (sheetId.size() > 0) {
                    list.get(i).setCus_result("生成工单");
                    String sheetNo = "",sheetIds = "",sheetStatus = "";
                    for(int j=0;j<sheetId.size();j++){
                        if(j != sheetId.size()-1){
                            sheetNo += sheetId.get(j).get("or_number")+",";
                            System.out.println("============"+sheetId.get(j).get("or_id")+"================");
                            sheetIds += sheetId.get(j).get("or_id")+",";
                            sheetStatus += sheetId.get(j).get("or_sheet_status")+",";
                        }else{
                            sheetNo += sheetId.get(j).get("or_number");
                            sheetIds += sheetId.get(j).get("or_id");
                            sheetStatus += sheetId.get(j).get("or_sheet_status");
                        }
                    }
                    list.get(i).setSheetNo(sheetNo);
                    list.get(i).setSheetId(sheetIds);
                    list.get(i).setSheetStatus(sheetStatus);
                } else {
                    list.get(i).setCus_result("已完成");
                }
                //判断数据是否可以编辑
                //在创建时间24小时后不可编辑
                try {

                    if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(i).getCus_ctime()).getTime() + 24 * 60 * 60 * 1000 < new Date().getTime()) {
                        list.get(i).setIsEdit("不可编辑");
                    } else {
                        //获取登录人的user_id
                        String user_id = user.getUserId();
                        //判断登录人是否是领导
                        boolean flag = uServiceFeignService.checkUserRole(Integer.parseInt(user_id), Jurisdiction, project);
                        if (flag) {
                            list.get(i).setIsEdit("可编辑");
                        } else {
                            //判断登录人是否是创建该会话单的人
                            if (user.getJob().equals(list.get(i).getCus_code())) {
                                list.get(i).setIsEdit("可编辑");
                            } else {
                                list.get(i).setIsEdit("不可编辑");
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }

            int count = mapper.findConversationCount(vo,"1");
            //int count = 0;
            page = new PageUtils().pageInit(vo.getPageNum(),vo.getPageSize(),count);

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
    public BaseResult findCustomer(String cus_name, String mobile,String name,String userType,Account user,String type) {
        BaseResult result = new BaseResult();
        logger.info("=====调用findCustomer接口开始，调用时间："+new DatesUtils().time()+",参数值：cus_name="+ cus_name+"==============");
        //1.判断参数是否为空
        if((cus_name == null || "".equals(cus_name)) && (mobile == null || "".equals(mobile)) && (name == null || "".equals(name))){
            result.setCode("404");
            result.setContent("参数手机号,账号,商家名称至少一个不能为空");
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
            String cus_tel = "",person_name = "",reg_time = "",area = "";
            Map<String,Object> map = new HashMap<String, Object>();
            Conversation conversation = null;
            /*if(type.equals("sheet")){
                conversation = mapper.findInfoByAccount(cus_name);
            }*/
            if(conversation != null){
                back_userInfo = (JSONObject) JSONObject.toJSON(conversation);

                map = back_userInfo;
                map.put("code","200");
                map.put("message","查询成功");
                map.put("user_name",map.get("cus_account"));
                map.put("area",map.get("cus_area"));
                map.put("mobile",map.get("cus_tel"));
                map.put("person_name",map.get("cus_name"));
                map.put("status",map.get("cus_status"));
                map.put("state",map.get("cus_state"));
                map.put("reg_time",map.get("cus_user_time"));
                map.put("add_time",map.get("cus_shop_time"));
            }else{
                //根据传入的用户类型查询信息
                if(userType.equals("Buyer")){
                    back_userInfo = dataHostFeignService.getAccountByName(cus_name,mobile,name);//用户信息
                    if(back_userInfo.getJSONObject("result") != null){
                        cus_tel = back_userInfo.getJSONObject("result").get("mobile") == null?"--":back_userInfo.getJSONObject("result").get("mobile").toString();
                        person_name = back_userInfo.getJSONObject("result").get("person_name")==null?"--":back_userInfo.getJSONObject("result").get("person_name").toString();
                        area = back_userInfo.getJSONObject("result").get("area")==null?"--":back_userInfo.getJSONObject("result").get("area").toString();
                    }
                }else if(userType.equals("Shop")){
                    back_userInfo = dataHostFeignService.getShopInfo(cus_name,mobile,name);//商家信息
                    if(back_userInfo.getJSONObject("result") != null){
                        cus_tel = back_userInfo.getJSONObject("result").get("shop_mobile")==null?"--":back_userInfo.getJSONObject("result").get("shop_mobile").toString();
                        person_name = back_userInfo.getJSONObject("result").get("name")==null?"--":back_userInfo.getJSONObject("result").get("name").toString();
                        //reg_time = back_userInfo.getJSONObject("result").get("add_time")==null?"--":back_userInfo.getJSONObject("result").get("add_time").toString();
                        area = back_userInfo.getJSONObject("result").get("area")==null?"--":back_userInfo.getJSONObject("result").get("area").toString();
                    }
                }else if(userType.equals("Agent")){
                    back_userInfo = dataHostFeignService.getAgentInfo(cus_name,mobile,name);//合伙人信息
                    if(back_userInfo.getJSONObject("result") != null){
                        cus_tel = back_userInfo.getJSONObject("result").get("telephone") == null?"--":back_userInfo.getJSONObject("result").get("telephone").toString();
                        person_name = back_userInfo.getJSONObject("result").get("person_name")==null?"--":back_userInfo.getJSONObject("result").get("person_name").toString();
                        reg_time = back_userInfo.getJSONObject("result").get("add_time")==null?"--":back_userInfo.getJSONObject("result").get("add_time").toString();
                        area = back_userInfo.getJSONObject("result").get("agent_area")==null?"--":back_userInfo.getJSONObject("result").get("agent_area").toString();

                    }
                }else if(userType.equals("other")){
                    back_userInfo = dataHostFeignService.getOtherInfo(mobile);//合伙人信息
                    if(back_userInfo.getJSONObject("result") != null){
                        cus_tel = back_userInfo.getJSONObject("result").get("mobile")==null?"--":back_userInfo.getJSONObject("result").get("mobile").toString();
                        person_name = back_userInfo.getJSONObject("result").get("user_name")==null?"--":back_userInfo.getJSONObject("result").get("user_name").toString();
                        area = back_userInfo.getJSONObject("result").get("cus_area")==null?"--":back_userInfo.getJSONObject("result").get("cus_area").toString();
                    }
                }
                if(back_userInfo.getJSONObject("result")!= null){
                    map = back_userInfo.getJSONObject("result");

                    map.put("cus_tel",cus_tel);
                    map.put("person_name",person_name);
                    map.put("reg_time",reg_time);
                    map.put("area",area);
                }
            }
            if(map.size() == 0){
                //3.判断返回的数据
                result.setCode("404");
                result.setContent("查询无数据");
                result.setPage(null);
                result.setResult(null);
            }else{
                //3.判断返回的数据
                result.setCode(map.get("code")==null?"200":map.get("code").toString());
                result.setContent(map.get("message")==null?"查询成功":map.get("message").toString());
                result.setPage(null);
                result.setResult(JSONObject.toJSON(map));
            }

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
        String biaoshi = "";
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
            if(tabType.equals("5")){
                biaoshi = "会话";
            }
            back_userAccount = dataHostFeignService.getUserAccount(mobile,userId,tabType,pageSize,pageNumber);//用户详情
        }else if(userType.equals("Shop")){
            if(tabType.equals("6")){
                biaoshi = "会话";
            }
            back_userAccount = dataHostFeignService.getShopDetail(mobile,userId,tabType,pageSize,pageNumber);//商家详情
        }else if(userType.equals("Agent")){
            if(tabType.equals("1")){
                biaoshi = "会话";
            }
            back_userAccount = dataHostFeignService.getAgentDetail(mobile,userId,tabType,pageSize,pageNumber);//代理商详情
        }else if(userType.equals("Other")){
            if(tabType.equals("1")){
                biaoshi = "会话";
            }
            back_userAccount = dataHostFeignService.getOtherDetail(mobile,userId,tabType,pageSize,pageNumber);//其他详情
        }
        Map<String , Object> map = new HashMap<String, Object>();
        if(biaoshi.equals("会话")){
            JSONArray list = back_userAccount.getJSONObject("result").getJSONArray("dataList");
            if(list != null && list.size() > 0){
                for(int i=0;i<list.size();i++){
                    //查询工单号
                    int cus_id = 0;
                    if(list.getJSONObject(i).get("cus_id") != null){
                        cus_id = Integer.parseInt(list.getJSONObject(i).get("cus_id").toString());
                    }
                    List<Map<String,Object>> sheetId = mapper.findSheet(cus_id);
                    System.out.println(sheetId);
                    if(sheetId.size()>0){
                        list.getJSONObject(i).put("cus_result","生成工单");
                        String sheetNo = "",sheetIds = "",sheetStatus = "";
                        for(int j=0;j<sheetId.size();j++){
                            if(j != sheetId.size()-1){
                                sheetNo += sheetId.get(j).get("or_number")+",";
                                System.out.println("============"+sheetId.get(j).get("or_id")+"================");
                                sheetIds += sheetId.get(j).get("or_id")+",";
                                sheetStatus += sheetId.get(j).get("or_sheet_status")+",";
                            }else{
                                sheetNo += sheetId.get(j).get("or_number");
                                sheetIds += sheetId.get(j).get("or_id");
                                sheetStatus += sheetId.get(j).get("or_sheet_status");
                            }
                        }
                        list.getJSONObject(i).put("sheetNo",sheetNo);
                        list.getJSONObject(i).put("sheetId",sheetIds);
                        list.getJSONObject(i).put("sheetStatus",sheetStatus);
                    }else{
                        list.getJSONObject(i).put("cus_result","已完成");
                    }
                    //判断数据是否可以编辑
                    //在创建时间24小时后不可编辑
                    try{

                        if(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.getJSONObject(i).get("cus_ctime").toString()).getTime()+24*60*60*1000 < new Date().getTime()){
                            list.getJSONObject(i).put("isEdit","不可编辑");

                        }else{
                            //获取登录人的user_id
                            String user_id = user.getUserId();
                            //判断登录人是否是领导
                            boolean flag = uServiceFeignService.checkUserRole(Integer.parseInt(user_id),Jurisdiction,project);
                            if(flag){
                                list.getJSONObject(i).put("isEdit","可编辑");
                            }else{
                                //判断登录人是否是创建该会话单的人
                                if(user.getJob().equals(list.getJSONObject(i).get("cus_code").toString())){
                                    list.getJSONObject(i).put("isEdit","可编辑");
                                }else{
                                    list.getJSONObject(i).put("isEdit","可编辑");
                                }
                            }
                        }
                    }catch(Exception e){

                    }
                }
            }
            map = back_userAccount.getJSONObject("result");
            map.put("dataList",list);
            //back_userAccount.getJSONObject("result").put("datas",list.toString());
            //back_userAccount.getJSONObject("result").replace("dataList",back_userAccount.getJSONObject("result").getJSONArray("dataList"),list);
        }
        result.setCode(back_userAccount.getString("code"));
        result.setContent(back_userAccount.getString("message"));
        result.setPage(null);
        if(!biaoshi.equals("")){
            result.setResult(JSONObject.toJSON(map));
        }else{

            result.setResult(back_userAccount.get("result"));
        }
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
        //1.查询来电原因的code
        String codes = mapper.findFirstReasonById(code);
        codes = codes+"_%";
        List<Reason> secondReason = mapper.findSecondReason(codes);

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
            System.out.println(jsonToString);
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
            e.printStackTrace();
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
            JSONObject back = dataHostFeignService.findOrderDetail(Integer.parseInt(orderId));

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
     * 查询退单详情
     * @param orderNo
     * @return
     */
    @Override
    public BaseResult getOrderBackDetail(String orderNo) {

        try{
            logger.info("=====调用findOrderDetail接口开始，调用时间："+new DatesUtils().time()+"参数值："+orderNo+"=============");
            //1.校验参数
            if(orderNo == null || "".equals(orderNo)){
                result.setCode("404");
                result.setContent("参数orderNo不能为空");
                result.setResult("");
                return result;
            }
            //2.返回值
            JSONObject back = dataHostFeignService.getOrderBackDetail(orderNo);

            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(back.get("result"));

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("=====调用getOrderBackDetail接口开始，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"=============");
        return result;
    }

    /**
     * 查询用户信息
     * @param mobile
     * @param type
     * @return
     */
    @Override
    public BaseResult findCusInfo(String mobile, String type) {

        BaseResult result = new BaseResult();
        List<Conversation> list = mapper.findCusInfo(mobile,type);

        if(list != null && list.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(list.get(0));
        }else{
            result.setCode("404");
            result.setContent("暂无数据");
            result.setResult(null);
        }
        return result;
    }

    /**
     * 查询工单记录
     * @param mobile
     * @return
     */
    public BaseResult findOrderConversation(String orderId,String mobile,String pageSize,String pageNumber,Account user){

        String userId = user.getUserId();
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

            for (int i = 0; i < list.size(); i++) {
                //查询工单号
                List<Map<String,Object>> sheetId = mapper.findSheet(list.get(i).getCus_id());
                System.out.println(sheetId);
                if (sheetId.size() > 0) {
                    list.get(i).setCus_result("生成工单");
                    String sheetNo = "",sheetIds = "",sheetStatus = "";
                    for(int j=0;j<sheetId.size();j++){
                        if(j != sheetId.size()-1){
                            sheetNo += sheetId.get(j).get("or_number")+",";
                            System.out.println("============"+sheetId.get(j).get("or_id")+"================");
                            sheetIds += sheetId.get(j).get("or_id")+",";
                            sheetStatus += sheetId.get(j).get("or_sheet_status")+",";
                        }else{
                            sheetNo += sheetId.get(j).get("or_number");
                            sheetIds += sheetId.get(j).get("or_id");
                            sheetStatus += sheetId.get(j).get("or_sheet_status");
                        }
                    }
                    list.get(i).setSheetNo(sheetNo);
                    list.get(i).setSheetId(sheetIds);
                    list.get(i).setSheetStatus(sheetStatus);
                } else {
                    list.get(i).setCus_result("已完成");
                }
                //判断数据是否可以编辑
                //在创建时间24小时后不可编辑
                try {

                    if (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(list.get(i).getCus_ctime()).getTime() + 24 * 60 * 60 * 1000 < new Date().getTime()) {
                        list.get(i).setIsEdit("不可编辑");
                    } else {
                        //获取登录人的user_id
                        String user_id = userId;
                        String job = user.getJob();
                        //判断登录人是否是领导
                        boolean flag = uServiceFeignService.checkUserRole(Integer.parseInt(user_id), Jurisdiction, project);
                        if (flag) {
                            list.get(i).setIsEdit("可编辑");
                        } else {
                            //判断登录人是否是创建该会话单的人
                            if (job.equals(list.get(i).getCus_code())) {
                                list.get(i).setIsEdit("可编辑");
                            } else {
                                list.get(i).setIsEdit("不可编辑");
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }


            if(orderId != null && !"".equals(orderId)){
                //查询订单相关的会话
                List<String> str = mapper.findOrderByOrderId(orderId);
                System.out.println(str+"=============================");

                for(int i=0;i<list.size();i++){
                    for(int j=0;j<str.size();j++) {
                        if ((list.get(i).getCus_id() + "").equals(str.get(j).toString())) {
                            list.get(i).setIsOrder("true");
                            break;
                        }else{
                            list.get(i).setIsOrder("false");
                        }
                    }
                }
            }
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
        String excelName = "会话记录";
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
        /*String[] str = {"zero","one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen","twenty","twenty-one","twenty-two","twenty-three","twenty-four"};
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
        }*/
        String str = "";
        for(int i=0;i<24;i++){
            int a = 0;
            for(int j=0;j<list.size();j++){
                String s = "";
                if(i < 10){
                    s = "0"+i;
                }else{
                    s = i+"";
                }
                //System.out.println(s);
                if(list.get(j).getCus_ctime().equals(s)){
                    a++;
                }
            }
            if(i == 23){
                str += a;
            }else{
                str += a+",";
            }
        }
        map.put("datas",str);
        result.setCode("200");
        result.setContent("查询成功");
        result.setResult(map);
        return result;
    }

    /**
     * 根据会话id查询会话信息
     * @param cusId
     * @param user
     * @return
     */
    @Override
    public BaseResult findConversationById(String cusId, Account user) {


        logger.info("=====调用findConversationById接口开始，调用时间："+new DatesUtils().time()+"参数值："+cusId+"=============");

        Conversation conversation = mapper.findConversationById(cusId);

        if(conversation != null){

            result.setCode("200");
            result.setContent("请求成功");
            result.setResult(conversation);
        }else{
            result.setCode("404");
            result.setContent("暂无数据");
            result.setResult("");
        }

        logger.info("=====结束调用findConversationById接口开始，结束调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"=============");
        return result;
    }

    /**
     * 会话统计
     * @return
     */
    @Override
    public BaseResult findConversationCount(String cusTime,String cusWay,String cusFrom,String other,String pageNumber,String pageSize) {

        AddConversationVo vo = new AddConversationVo();
        //处理参数
        logger.info("=====调用findConversationCount接口开始，调用时间："+new DatesUtils().time()+"参数值："+cusTime+"=============");
        if(cusTime != null && !cusTime.equals("")){
            cusTime = cusTime.replace(" ","");
            if(cusTime.equals("今天")){
                vo.setStime(new DatesUtils().getToday());
                vo.setEtime(new DatesUtils().getTomorrow());
            }else if(cusTime.equals("最近7天")){
                vo.setStime(new DatesUtils().getDateByDay(7));
                vo.setEtime(new DatesUtils().getToday());
            }else if(cusTime.equals("最近30天")){
                vo.setStime(new DatesUtils().getDateByDay(30));
                vo.setEtime(new DatesUtils().getToday());
            }else if(cusTime.equals("到")){
                vo.setStime(null);
                vo.setEtime(null);
            }else{
                vo.setStime(cusTime.split("到")[0]);
                vo.setEtime(cusTime.split("到")[1]);
            }
        }

        vo.setOther(other);
        vo.setCus_way(cusWay);
        //处理来电原因
        //1.查询来电原因的code
        String code = mapper.findFirstReasonById(cusFrom);
        //2.查询二级来电原因的id
        code = code+"_%";
        List<Reason> reason = mapper.findSecondReason(code);
        String cusReasonId = "";//拼接原因id
        if(reason != null && reason.size() > 0){

            for(int i=0;i<reason.size();i++){
                if(i == reason.size()-1){
                    cusReasonId += reason.get(i).getCus_id();
                }else{
                    cusReasonId += reason.get(i).getCus_id()+",";
                }

            }
        }else{
            cusReasonId = cusFrom;
        }
        vo.setCus_from(cusReasonId);
        vo.setPageNum(Integer.parseInt(pageNumber)-1);
        vo.setPageSize(Integer.parseInt(pageSize));
//        vo.setPageNum(1);
//        vo.setPageSize(15);
        List<ConversationCount> list = mapper.findConversationStatistics(vo);
        int counts = mapper.findConversationStatisticsCount(vo);
        PageResult page = new PageUtils().pageInit(vo.getPageNum(),vo.getPageSize(),counts);
        if(list != null && list.size() > 0){

            result.setCode("200");
            result.setContent("请求成功");
            result.setPage(page);
            result.setResult(list);
        }else{
            result.setCode("404");
            result.setContent("暂无数据");
            result.setPage(null);
            result.setResult("");
        }


        return result;
    }

    /**
     * 根据姓名或者店铺名称查询信息
     * @param personName
     * @param user
     * @return
     */
    @Override
    public BaseResult getUserNameByPersonName(String personName, Account user) {

        logger.info("=====调用findConversationCount接口开始，调用时间："+new DatesUtils().time()+"参数值："+personName+"=============");

        JSONObject back = dataHostFeignService.getUserNameByPersonName(personName);

        if(back.get("code").toString().equals("200")){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(back.get("result"));
        }else if(back.get("code").toString().equals("404")){
            result.setCode("404");
            result.setContent("数据不存在");
            result.setResult("");

        }
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
