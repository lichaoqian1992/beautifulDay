package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.SheetService;
import com.manji.cusystem.vo.sheets.AssignSheetVo;
import com.manji.cusystem.vo.sheets.ExamineSheetVo;
import com.manji.cusystem.vo.sheets.QuerySheetsVo;
import com.manji.cusystem.vo.sheets.SheetsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Administrator on 2017/9/4.
 */
@RestController
@RequestMapping(value = "/sheet")
public class SheetController extends BaseController{

    @Autowired
    private SheetService service;

    /**
     * 新增工单
     * @param sheets
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/addSheet")
    public String addSheet(@Valid SheetsVo sheets, BindingResult bindingResult,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(request.getParameter("sessionId"));
                result = service.addSheet(sheets,user);
            }
        }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);

    }

    /**
     * 修改工单
     * @param sheets
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateSheet")
    public String updateSheet(@Valid SheetsVo sheets, BindingResult bindingResult,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        String sheet_id = request.getParameter("sheet_id");
        if(result.getCode().equals("200")){
            if(sheet_id == null || "".equals(sheet_id)){
                result.setCode("404");
                result.setContent("参数sheet_id工单id不能为空");
                result.setResult("");
                return JSONObject.toJSON(result).toString();
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(request.getParameter("sessionId"));
                result = service.updateSheet(sheets,user,sheet_id);
            }
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询各种状态的工单
     * @param vo
     * @return
     */
    @RequestMapping(value = "/findSheet")
    public String findSheets(@Valid QuerySheetsVo vo,BindingResult bindingResult,HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(request.getParameter("sessionId"));
                result = service.findSheets(vo,request.getParameter("sessionId"),user);
            }

        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询所有状态的工单的数量
     * @return
     */
    @RequestMapping(value = "/findSheetCount")
    public String findSheetCount(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(request.getParameter("sessionId"));
            result = service.findSheetCount(user);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询工单详情
     * @param sessionId
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/selectSheetDetail")
    public String selectSheetDetail(@Param("sessionId")String sessionId,@Param("sheetId")String sheetId,@Param("sendId")Integer sendId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.selectSheetDetail(user,sheetId,sendId);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询工单日志流程
     * @param sessionId
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/selectSheetLog")
    public String selectSheetLog(@Param("sessionId")String sessionId,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.selectSheetLog(user,sheetId);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 待提交的工单详情页面的提交操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/submitSheet")
    public String submitSheet(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            if(request.getParameter("sheetId") == null || "".equals(request.getParameter("sheetId"))){
                result.setCode("404");
                result.setContent("工单id sheetId不能为空");
                result.setResult("");
                return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
            }

            Account user = (Account)result.getResult();
            user.setSessionId(request.getParameter("sessionId"));
            result = service.submitSheet(user,request.getParameter("sheetId"));
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 保存工单或者保存并提交工单
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/saveOrSubmit")
    public String saveOrSubmit(@Valid SheetsVo vo,BindingResult bindingResult,@Param("sessionId") String sessionId,@Param("type") String type){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            if(type == null || "".equals(type)){
                result.setCode("404");
                result.setContent("参数type不能为空");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(sessionId);
                result = service.saveOrSubmit(vo,user,type);
            }
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 待处理页面添加备注信息
     * @param sessionId
     * @param sheetId
     * @param remark
     * @return
     */
    @RequestMapping(value = "/remark")
    public String remark(@Param("sessionId")String sessionId,@Param("sheetId")String sheetId,@Param("remark")String remark,@Param("pics")String pics){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.remark(sheetId,user,remark,pics);
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 删除工单
     * @param sessionId
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/deleteSheet")
    public String deleteSheet(@Param("sessionId")String sessionId,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.deleteSheet(user,sheetId);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 审核工单
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/examineSheet")
    public String examineSheet(@Valid ExamineSheetVo vo,BindingResult bindingResult, @Param("sessionId")String sessionId){

        BaseResult result = logins(sessionId);
        if(result.getCode().toString().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(sessionId);
                result = service.examineSheet(vo,user);
            }
        }
        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 驳回工单
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/backSheet")
    public String backSheet(@Param("sessionId")String sessionId,@Param("reason")String reason,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.backSheet(reason,sheetId,user,"back");
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 关闭工单（重置工单）
     * @param sessionId
     * @param reason
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/closeSheet")
    public String closeSheet(@Param("sessionId")String sessionId,@Param("reason")String reason,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.backSheet(reason,sheetId,user,"close");
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);


    }

    /**
     * 重置工单
     * @param sessionId
     * @param reason
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/resetSheet")
    public String resetSheet(@Param("sessionId")String sessionId,@Param("reason")String reason,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.backSheet(reason,sheetId,user,"reset");
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);


    }

    /**
     * 恢复工单
     * @param sessionId
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/recoverSheet")
    public String recoverSheet(@Param("sessionId")String sessionId,@Param("sheetId")String sheetId){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){
            Account user = (Account)result.getResult();
            user.setSessionId(sessionId);
            result = service.recoverSheet(sheetId,user);
        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);


    }

    /**
     * 指派工单
     * @param sessionId
     * @param vo
     * @return
     */
    @RequestMapping(value = "/assignSheet")
    public String assignSheet(@Param("sessionId")String sessionId, @Valid AssignSheetVo vo,BindingResult bindingResult){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){

            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setPage(null);
                result.setResult("");
            }else{

                Account user = (Account)result.getResult();
                user.setSessionId(sessionId);
                result = service.assignSheet(vo,user);
            }

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 保存并指派工单
     * @param sessionId
     * @param sheets
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/saveAssignSheet")
    public String saveAssignSheet(@Param("sessionId")String sessionId,@Param("content")String content, @Valid SheetsVo sheets,BindingResult bindingResult){

        BaseResult result = logins(sessionId);

        if(result.getCode().toString().equals("200")){

            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setPage(null);
                result.setResult("");
            }else{

                Account user = (Account)result.getResult();
                user.setSessionId(sessionId);
                result = service.saveAssignSheet(sheets,user,content);
            }

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);


    }

    /**
     * 查询所有的部门
     * @return
     */
    @RequestMapping(value = "/findAllDept")
    public String findAllDept(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            result = service.findAllDept(user);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);


    }

    /**
     * 查询部门下面的人
     * @param deptId
     * @param request
     * @return
     */
    @RequestMapping(value = "/findDeptUser")
    public String findDeptUser(@Param("deptId") String deptId, HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            result = service.findDeptUser(deptId,user);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 根据姓名查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/findByName")
    public String findByName(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));

        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            result = service.findByName(request.getParameter("realName"),user);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询拥有审核权限的人
     * @return
     */
    @RequestMapping(value = "/findExaminePeople")
    public String findExaminePeople(HttpServletRequest request){

        BaseResult result = logins(request.getParameter("sessionId"));
        if(result.getCode().equals("200")){
            Account user = (Account)result.getResult();
            result = service.findExaminePeople(user);

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);

    }

    /**
     * 处理完毕
     * @return
     */
    @RequestMapping(value = "/finishedProcess")
    public String finishedProcess(@Valid AssignSheetVo vo,BindingResult bindingResult,@Param("sessionId") String sessionId){//@Valid AssignSheetVo vo,HttpServletRequest request,BindingResult bindingResult

        BaseResult result = logins(sessionId);
        if(result.getCode().equals("200")){
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setPage(null);
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                user.setSessionId(sessionId);
                result = service.finishedProcess(user,vo);
            }

        }

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
        //return "cao";

    }

    /**
     * 查询客户信息  根据会话id
     * @param cus_cid
     * @return
     */
    @RequestMapping("/findCustomerByCid")
    public String findCustomerByCid(@RequestParam("cus_cid") String cus_cid){

        BaseResult result = new BaseResult();
        result = service.findCustomerByCid(cus_cid);

        return JSONObject.toJSONString(result,SerializerFeature.WriteMapNullValue);
    }

}
