package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.Conversation;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.feign.OrServiceFeignService;
import com.manji.cusystem.feign.UServiceFeignService;
import com.manji.cusystem.mapper.ConversationMapper;
import com.manji.cusystem.service.LogService;
import com.manji.cusystem.service.SheetService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.vo.common.LogVo;
import com.manji.cusystem.vo.sheets.AssignSheetVo;
import com.manji.cusystem.vo.sheets.ExamineSheetVo;
import com.manji.cusystem.vo.sheets.QuerySheetsVo;
import com.manji.cusystem.vo.sheets.SheetsVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/4.
 */
@Service
public class SheetServiceImpl implements SheetService{

    private static final Logger logger = Logger.getLogger(SheetServiceImpl.class);

    @Autowired
    private LogService service;

    @Autowired
    private OrServiceFeignService orServiceFeignService;

    @Autowired
    private UServiceFeignService uServiceFeignService;

    @Autowired
    private ConversationMapper mapper;

    @Override
    public BaseResult addSheet(SheetsVo sheets,Account user) {

        BaseResult result = new BaseResult();
        try{
            logger.info("=====调用addSheet接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(sheets)+"==============");
            //1.处理参数
            String str = JSONObject.toJSONString(sheets);
            //String u = JSONObject.toJSONString(user);
            System.out.println(str+"====================");
            //2.发送请求
            JSONObject back = orServiceFeignService.addSheet(sheets,user.getSessionId());
            //3.判断返回值
            String content = user.getRealName()+"在"+new DatesUtils().time()+"新增了工单";
            result = detailCode(back,"add",content,user);

        }catch (Exception e){
            result.setCode("403");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("=====调用addSheet接口结束，结束时间："+new DatesUtils().time()+",返回值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }

    /**
     * 修改工单
     * @param sheets
     * @param user
     * @param sheet_id
     * @return
     */
    public BaseResult updateSheet(SheetsVo sheets, Account user,String sheet_id){

        BaseResult result = new BaseResult();
        try{

            logger.info("=====调用updateSheet接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(sheets)+"==============");
            //1.处理参数值
            sheets.setOrId(sheet_id);
            String jsonToString = JSONObject.toJSONString(sheets, SerializerFeature.WriteMapNullValue);
            String u = JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue);
            JSONObject back = orServiceFeignService.updateSheet(sheets,user.getSessionId());

            //3.判断返回值
            String content = user.getRealName()+"在"+new DatesUtils().time()+"修改了工单id为"+sheet_id;
            result = detailCode(back,"update",content,user);
        }catch (Exception e){
            result.setCode("403");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }

        return result;

    }
    /**
     * 查询所有工单
     * @param vo
     * @return
     */
    @Override
    public BaseResult findSheets(QuerySheetsVo vo,String sessionId,Account user) {

        BaseResult result = new BaseResult();
        try{
            logger.info("=====调用findSheets接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(vo)+"==============");

            if(vo.getPageSize() == 0){
                vo.setPageSize(15);
            }
            if(vo.getPageNum() == 0){
                vo.setPageNum(1);
            }

            String str = JSONObject.toJSONString(vo,SerializerFeature.WriteMapNullValue);

            String u = JSONObject.toJSONString(user,SerializerFeature.WriteMapNullValue);
            //2.发送请求
            JSONObject back = orServiceFeignService.findSheets(vo,sessionId);
            System.out.println(back+"==================");
            //3.判断返回值
            String content = user.getRealName()+"在"+new DatesUtils().time()+"查询了工单";
            result = detailCode(back,"select",content,user);

        }catch (Exception e){
            result.setCode("403");
            result.setContent("链接超时");
        }
        logger.info("=====调用findSheets接口结束，结束时间："+new DatesUtils().time()+",返回值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }
    /**
     * 查询工单的数量
     * @param user
     * @return
     */
    @Override
    public BaseResult findSheetCount(Account user) {

        BaseResult result = new BaseResult();
        try{
            logger.info("=====调用findSheetCount接口开始，调用时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(user)+"==============");
            //1.处理参数
            String str = user.getSessionId();
            //2.发送请求
            JSONObject back = orServiceFeignService.findSheetCount(str);
            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了所有状态下的工单数量";
            result = detailCode(back,"select",content,user);
        }catch (Exception e){
            result.setCode("403");
            result.setContent("链接超时");
        }

        logger.info("=====调用findSheetCount接口结束，结束时间："+new DatesUtils().time()+",参数值："+ JSONObject.toJSONString(result)+"==============");
        return result;
    }

    /**
     * 查询所有部门
     * @param user
     * @return
     */
    public BaseResult findAllDept(Account user){

        BaseResult result = new BaseResult();
        //1.判断是否登录
        logger.info("========开始调用findAllDept接口，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(user)+"==========");

        //发送请求
        JSONArray back =  uServiceFeignService.findAllDept();
        String content = user.getRealName()+"在"+new DatesUtils().time()+"查询了所有的部门信息";
        System.out.println(back+"_______________________");
        //4.记录日志
        LogVo log = new LogVo();
        log.setCus_type("select");
        log.setCus_content(content);
        log.setCus_user_id(Integer.parseInt(user.getUserId()));
        log.setCus_user_name(user.getUserName());
        log.setCus_ctime(new DatesUtils().time());
        log.setCus_isdel(1);
        boolean b = service.addLog(log);
        result.setCode("200");
        result.setContent("操作成功");
        result.setResult(back);

        logger.info("========结束调用findAllDept接口，结束时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"==========");
        return result;

    }

    /**
     * 查询部门下面的人
     * @param deptId
     * @param user
     * @return
     */
    public BaseResult findDeptUser(String deptId,Account user){

        BaseResult result = new BaseResult();
        //1.判断是否登录
        logger.info("========开始调用findDeptUser接口，调用时间："+new DatesUtils().time()+"参数值："+deptId+"==========");

        if(deptId == null || "".equals(deptId)){
            result.setCode("404");
            result.setContent("参数deptId不能为空");
            result.setResult("");
            return result;
        }
        //发送请求
        JSONObject back =  uServiceFeignService.findUserByDeptId(Integer.parseInt(deptId),1,9999999,"");
        String content = user.getRealName()+"在"+new DatesUtils().time()+"查询了部门id为"+deptId+"下面的所有员工信息";
        System.out.println(back+"_______________________");
        //4.记录日志
        LogVo log = new LogVo();
        log.setCus_type("select");
        log.setCus_content(content);
        log.setCus_user_id(Integer.parseInt(user.getUserId()));
        log.setCus_user_name(user.getUserName());
        log.setCus_ctime(new DatesUtils().time());
        log.setCus_isdel(1);
        boolean b = service.addLog(log);
        result.setCode("200");
        result.setContent("操作成功");
        result.setResult(back);

        logger.info("========结束调用findDeptUser接口，结束时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"==========");
        return result;

    }

    /**
     * 查询拥有审核权限的人
     * @param user
     * @return
     */
    public BaseResult findExaminePeople(Account user){

        BaseResult result = new BaseResult();
        logger.info("========开始调用findExaminePeople接口，调用时间："+new DatesUtils().time()+"参数值："+user+"==========");

        JSONArray back =  uServiceFeignService.findExaminePeople(user.getUserId(),"/sheet/examine");
        String content = user.getRealName()+"在"+new DatesUtils().time()+"查询了拥有审核权限的员工信息";
        System.out.println(back+"_______________________");
        //4.记录日志
        LogVo log = new LogVo();
        log.setCus_type("select");
        log.setCus_content(content);
        log.setCus_user_id(Integer.parseInt(user.getUserId()));
        log.setCus_user_name(user.getUserName());
        log.setCus_ctime(new DatesUtils().time());
        log.setCus_isdel(1);
        boolean b = service.addLog(log);
        result.setCode("200");
        result.setContent("操作成功");
        result.setResult(back);

        logger.info("========结束调用findExaminePeople接口，结束时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(result)+"==========");
        return result;

    }

    /**
     * 查询工单详情
     * @param user
     * @param sheetId
     * @return
     */
    @Override
    public BaseResult selectSheetDetail(Account user, String sheetId, Integer sendId) {

        BaseResult result = new BaseResult();
        try{

            if(sheetId == null || "".equals(sheetId)){
                result.setCode("404");
                result.setContent("工单id必须填写");
                result.setResult("");
                return result;
            }
            logger.info("=======开始调用selectSheetDetail接口，调用时间："+new DatesUtils().time()+",参数值：sheetId="+sheetId);

            JSONObject back = orServiceFeignService.selectSheetDetail(Integer.parseInt(sheetId),sendId,user.getSessionId());
            //3.判断返回值
            if(back.get("datas") != null){
                Map<String,String> map = (Map<String, String>) back.get("datas");
                //查询处理人、抄送人部门
                int id = Integer.parseInt(map.get("personId").toString());//处理人
                String[] cid = {};
                String ccid ="";
                if(map.get("sendId").toString().indexOf(",") != -1){
                    cid = map.get("sendId").toString().split(",");
                }else{
                    ccid = map.get("sendId").toString();
                }
                JSONArray list = uServiceFeignService.findByUserId(id);
                String deptname_send = "暂无",realname_send = "暂无";
                String deptname = "暂无",realname = "暂无";
                if(list != null && list.size() > 0){
                    deptname_send = "";
                    realname_send = "";
                    //处理人信息
                    System.out.println(list.getJSONObject(0));
                    deptname = list.getJSONObject(0).get("deptname").toString();
                    realname=list.getJSONObject(0).get("vsername").toString();

                    //抄送人信息
                    if(cid.length > 0){
                        for(int i=0;i<cid.length;i++){
                            JSONArray array = uServiceFeignService.findByUserId(Integer.parseInt(cid[i]));
                            for(int j=0;j<array.size();j++){
                                if(i != cid.length-1){

                                    if(array.getJSONObject(j).get("deptname") != null){

                                        deptname_send+=array.getJSONObject(j).get("deptname").toString()+",";
                                    }
                                    if(array.getJSONObject(j).get("vsername") != null){

                                        realname_send+=array.getJSONObject(j).get("vsername").toString()+",";
                                    }
                                }else{
                                    if(array.getJSONObject(j).get("deptname") != null){

                                        deptname_send+=array.getJSONObject(j).get("deptname").toString();
                                    }
                                    if(array.getJSONObject(j).get("vsername") != null){

                                        realname_send+=array.getJSONObject(j).get("vsername").toString();
                                    }
                                }
                            }
                        }
                    }else{
                        if(!ccid.equals("")){

                            JSONArray array = uServiceFeignService.findByUserId(Integer.parseInt(ccid));
                            for(int j=0;j<array.size();j++){
                                if(array.getJSONObject(j).get("deptname") != null){

                                    deptname_send+=array.getJSONObject(j).get("deptname").toString();
                                }
                                if(array.getJSONObject(j).get("vsername") != null){

                                    realname_send+=array.getJSONObject(j).get("vsername").toString();
                                }

                            }
                        }
                    }
                }
                map.put("deptname",deptname);
                map.put("realname",realname);
                map.put("deptname_send",deptname_send);
                map.put("realname_send",realname_send);
                String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了工单id为"+sheetId+"的详细信息";
                result = detailCode(back,"select",content,user);
            }else{
                result.setCode("404");
                result.setContent("暂无数据");
                result.setResult("");
                result.setPage(null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }

        logger.info("=======结束调用selectSheetDetail接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 查询工单流程
     * @param user
     * @param sheetId
     * @return
     */
    @Override
    public BaseResult selectSheetLog(Account user, String sheetId) {

        BaseResult result = new BaseResult();

        if(sheetId == null || "".equals(sheetId)){
            result.setCode("404");
            result.setContent("工单id,sheetId不能为空");
            result.setResult("");
            return result;
        }
        try{

            logger.info("=======开始调用selectSheetDetail接口，调用时间："+new DatesUtils().time()+",参数值：sheetId="+sheetId);

            JSONObject back = orServiceFeignService.selectSheetLog(Integer.parseInt(sheetId));
            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了工单id为"+sheetId+"的流程信息";
            result = detailCode(back,"select",content,user);

            logger.info("=======结束调用selectSheetDetail接口，结束调用时间："+new DatesUtils().time()+",返回值：result="+JSONObject.toJSONString(result));
        }catch (Exception e){
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        return result;
    }

    /**
     * 待提交的工单详情页面的提交操作
     * @param user
     * @param sheetId
     * @return
     */
    @Override
    public BaseResult submitSheet(Account user, String sheetId) {
        BaseResult result = new BaseResult();
        try{

            logger.info("==========开始调用submitSheet接口，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(sheetId));

            //处理参数
            String u = JSONObject.toJSONString(user);
            //String sid = JSONObject.toJSONString(id);
            JSONObject back = orServiceFeignService.submitSheet(sheetId,user.getSessionId());

            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"提交了工单id为"+sheetId+"的工单";
            result = detailCode(back,"update",content,user);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("==========结束调用submitSheet接口，结束调用时间："+new DatesUtils().time()+"返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 删除工单
     * @param user
     * @param sheetId
     * @return
     */
    @Override
    public BaseResult deleteSheet(Account user, String sheetId) {

        BaseResult result = new BaseResult();
        try{

            logger.info("========开始调用deleteSheet接口，调用时间："+new DatesUtils().time()+",参数值："+sheetId);

            //处理参数
            String u = JSONObject.toJSONString(user);
            JSONObject back = orServiceFeignService.deleteSheet(Integer.parseInt(sheetId),user.getSessionId());

            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"删除了工单id为"+sheetId+"的工单";
            result = detailCode(back,"delete",content,user);

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("");
        }

        return result;
    }

    /**
     * 审核工单
     * @param vo
     * @param user
     * @return
     */
    @Override
    public BaseResult examineSheet(ExamineSheetVo vo, Account user) {

        BaseResult result = new BaseResult();
        try{

            logger.info("========开始调用examineSheet接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(vo));

            //处理参数
            String jsonToString = JSONObject.toJSONString(vo);
            String u = JSONObject.toJSONString(user);
            JSONObject back = orServiceFeignService.examineSheet(vo,user.getSessionId());

            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"审核了工单id为"+vo.getId()+"的工单";
            result = detailCode(back,"update",content,user);

        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("========结束调用examineSheet接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 保存工单或者保存并提交工单
     * @param vo
     * @param user
     * @param type
     * @return
     */
    @Override
    public BaseResult saveOrSubmit(SheetsVo vo, Account user, String type) {

        BaseResult result = new BaseResult();
        try{
            if(type.equals("1")){

                if(vo.getOrId() == null || "".equals(vo.getCusId())){
                    result.setCode("404");
                    result.setContent("参数工单id   orId不能为空");
                    result.setResult("");
                    return result;
                }
            }

            logger.info("========开始调用saveOrSubmit接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(vo));
            String jsonToString = JSONObject.toJSONString(vo);
            String u = JSONObject.toJSONString(user);
            JSONObject back = new JSONObject();
            if(type.equals("2")){//提交工单
               back = orServiceFeignService.saveSubmitSheet(vo,user.getSessionId());
            }else if(type.equals("1")){//保存工单
                back = orServiceFeignService.updateSheet(vo,user.getSessionId());
            }
            //3.判断返回值
            String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"保存了工单id为"+vo.getOrId()+"的工单";
            result = detailCode(back,"update",content,user);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        logger.info("========结束调用saveOrSubmit接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 添加备注信息
     * @param sheetId
     * @param user
     * @param remark
     * @return
     */
    @Override
    public BaseResult remark(String sheetId, Account user, String remark,String pics) {

        BaseResult result = new BaseResult();
        if(sheetId == null || "".equals(sheetId) || remark == null || "".equals(remark)){
            result.setCode("404");
            result.setContent("参数工单id和备注  sheetId、remark不能为空");
            result.setResult("");
            return result;
        }
        logger.info("========开始调用remark接口，调用时间："+new DatesUtils().time()+",参数值："+sheetId+remark);

        Map<String,String> map = new HashMap<String, String>();
        map.put("id",sheetId);
        map.put("content",remark);
        String u = JSONObject.toJSONString(user);
        String jsonToString = JSONObject.toJSONString(map);
        JSONObject back = orServiceFeignService.remark(Integer.parseInt(sheetId),remark,user.getSessionId(),pics);

        //3.判断返回值
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"为工单id为"+sheetId+"的工单添加备注，内容为："+remark;
        result = detailCode(back,"update",content,user);

        return result;
    }

    /**
     * 驳回工单、重置（关闭）工单
     * @param reason
     * @param sheetId
     * @param user
     * @return
     */
    @Override
    public BaseResult backSheet(String reason, String sheetId, Account user,String type) {

        BaseResult result = new BaseResult();
        //1.校验参数
        if(reason == null || sheetId == null || reason.equals("") || "".equals(sheetId)){
            result.setContent("参数reason和sheetId不能为空");
            result.setCode("404");
            result.setResult("");
            return result;
        }
        logger.info("========开始调用backSheet接口，调用时间："+new DatesUtils().time()+",参数值："+sheetId+reason);
        Map<String,String> map = new HashMap<String, String>();
        map.put("id",sheetId);
        map.put("content",reason);

        JSONObject back = null;
        if(type.equals("back")){

            back = orServiceFeignService.rejectSheet(Integer.parseInt(sheetId),reason,user.getSessionId());
        }else if(type.equals("close")){
            back = orServiceFeignService.closeSheet(Integer.parseInt(sheetId),reason,user.getSessionId());
        }else if(type.equals("reset")){
            back = orServiceFeignService.resetSheet(Integer.parseInt(sheetId),reason,user.getSessionId());
        }

        //3.判断返回值
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+type+"工单id为"+sheetId+"的工单,原因："+reason;
        result = detailCode(back,"update",content,user);

        logger.info("========结束调用backSheet接口，结束调用时间："+new DatesUtils().time()+",参数值："+sheetId+reason);
        return result;
    }

    /**
     * 恢复工单
     * @param sheetId
     * @param user
     * @return
     */
    @Override
    public BaseResult recoverSheet(String sheetId, Account user) {

        BaseResult result = new BaseResult();
        if(sheetId == null || "".equals(sheetId)){
            result.setCode("404");
            result.setContent("参数sheetId不能为空");
            result.setResult("");
            result.setPage(null);
            return result;
        }
        logger.info("========开始调用recoverSheet接口，调用时间："+new DatesUtils().time()+",参数值："+sheetId);

        JSONObject back =  orServiceFeignService.recoverSheet(Integer.parseInt(sheetId),user.getSessionId());

        //3.判断返回值
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"恢复工单id为"+sheetId+"的工单";
        result = detailCode(back,"update",content,user);

        logger.info("========结束调用recoverSheet接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;

    }

    /**
     * 指派工单
     * @param vo
     * @param user
     * @return
     */
    @Override
    public BaseResult assignSheet(AssignSheetVo vo, Account user) {

        logger.info("========开始调用assignSheet接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(vo));

        String jsonToString = JSONObject.toJSONString(vo,SerializerFeature.WriteMapNullValue);
        JSONObject back =  orServiceFeignService.assignSheet(vo,user.getSessionId());

        //3.判断返回值
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"指派工单id为"+vo.getId()+"的工单给"+vo.getOrHandle()+"并抄送给"+vo.getOrSendList();
        BaseResult result = detailCode(back,"update",content,user);

        logger.info("========结束调用assignSheet接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;

    }

    /**
     * 保存并指派工单
     * @param sheets
     * @param user
     * @return
     */
    @Override
    public BaseResult saveAssignSheet(SheetsVo sheets, Account user, String content) {

        BaseResult result = new BaseResult();
        if(sheets.getOrId() == null || "".equals(sheets.getOrId())){
            result.setCode("404");
            result.setContent("参数orId不能为空");
            result.setResult("");
            return result;
        }
        logger.info("========开始调用saveAssignSheet接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(sheets));

        JSONObject back = orServiceFeignService.saveAssignSheet(sheets,user.getSessionId());
        result = detailCode(back,"update",content,user);

        logger.info("========结束调用saveAssignSheet接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 根据姓名查询人
     * @param realName
     * @param user
     * @return
     */
    @Override
    public BaseResult findByName(String realName, Account user) {

        BaseResult result = new BaseResult();
        if(realName == null || "".equals(realName)){
            result.setCode("404");
            result.setContent("参数realName不能为空");
            result.setResult("");
            return result;
        }
        logger.info("========开始调用sfindByName接口，调用时间："+new DatesUtils().time()+",参数值："+realName);

        JSONArray back = uServiceFeignService.findByName(realName);
        //String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了姓名为"+realName+"的人员";
        //result = detailCode(back,"update",content,user);
        result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);
        logger.info("========结束调用findByName接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 查询自己创建的工单
     * @param user
     * @return
     */
    @Override
    public BaseResult sheetSelf(Account user) {

        logger.info("========开始调用sheetSelf接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(user));
        JSONObject back = orServiceFeignService.sheetSelf(user.getSessionId());
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了自己创建的工单";
        BaseResult result = detailCode(back,"select",content,user);
        /*result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);*/
        logger.info("========结束调用findByName接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 消息通知
     * @param user
     * @param user
     * @return
     */
    @Override
    public BaseResult selInformations(Account user,String type) {

        logger.info("========开始调用selInformations接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(user));
        JSONObject back = null;
        if(type.equals("select")){
            back = orServiceFeignService.selInformations(user.getSessionId());
        }else{
            back = orServiceFeignService.clearInformations(user.getSessionId());
        }
        System.out.println(back+"+++++++++++++++++++++++++++++++++++++++++++++++++");
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了消息通知";
        BaseResult result = detailCode(back,"select",content,user);
        /*result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);*/

        logger.info("========结束调用selInformations接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 处理完毕
     * @param user
     * @param vo
     * @return
     */
    @Override
    public BaseResult finishedProcess(Account user, AssignSheetVo vo) {

        logger.info("========开始调用finishedProcess接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(vo));

        JSONObject back = orServiceFeignService.finishedProcess(vo,user.getSessionId());
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"查询了自己创建的工单";
        BaseResult result = detailCode(back,"select",content,user);
        result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);

        logger.info("========结束调用finishedProcess接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 推送消息
     * @param user
     * @return
     */
    @Override
    public BaseResult pushInfo(Account user) {

        logger.info("========开始调用pushInfo接口，调用时间："+new DatesUtils().time()+",参数值："+JSONObject.toJSONString(user));

        JSONObject back = orServiceFeignService.pushInfo(user.getSessionId());
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"推送消息";
        BaseResult result = detailCode(back,"select",content,user);
        /*result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);*/

        logger.info("========结束调用pushInfo接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 关闭消息通知
     * @param user
     * @param infoId
     * @return
     */
    @Override
    public BaseResult clearPushInfo(Account user, String infoId) {

        logger.info("========开始调用clearPushInfo接口，调用时间："+new DatesUtils().time()+",参数值：infoId="+infoId+"其他："+JSONObject.toJSONString(user));

        JSONObject back = orServiceFeignService.clearPushInfo(Integer.parseInt(infoId));
        String content = "用户"+user.getRealName()+"在"+new DatesUtils().time()+"关闭推送消息";
        BaseResult result = detailCode(back,"select",content,user);
        /*result.setCode("200");
        result.setContent("请求成功");
        result.setResult(back);*/

        logger.info("========结束调用pushInfo接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 查询客户信息
     * @param cus_cid
     * @return
     */
    @Override
    public BaseResult findCustomerByCid(String cus_cid) {

        BaseResult result = new BaseResult();
        logger.info("========开始调用findCustomerByCid接口，调用时间："+new DatesUtils().time()+",参数值：cus_cid="+cus_cid);

        Conversation con = mapper.findCustomerByCid(cus_cid);

        if(con != null){
            result.setCode("200");
            result.setContent("请求成功");
            result.setResult(con);
        }else{
            result.setCode("404");
            result.setContent("暂无数据");
            result.setResult(con);
        }

        logger.info("========结束调用findCustomerByCid接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));

        return result;
    }


    /**
     * 处理返回的参数
     * @param back
     * @return
     */
    public BaseResult detailCode(JSONObject back,String type,String content,Account user){

        BaseResult result = new BaseResult();
        String code = "";
        if(back.get("state").toString().equals("0")){
           code = "200";
            //4.记录日志
            LogVo log = new LogVo();
            log.setCus_type(type);
            log.setCus_content(content);
            log.setCus_user_id(Integer.parseInt(user.getUserId()));
            log.setCus_user_name(user.getUserName());
            log.setCus_ctime(new DatesUtils().time());
            log.setCus_isdel(1);
            boolean b = service.addLog(log);

        }else if(back.get("state").toString().equals("100")){
            code = "404";

        }else {
            code = "403";

        }
        result.setCode(code);
        result.setContent(back.get("message").toString());
        result.setResult(back.get("datas"));

        return result;
    }

}
