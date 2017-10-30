package com.manji.desystem.controller.businesslogic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.common.exception.BusinessDealException;
import com.manji.desystem.common.exception.UserNullException;
import com.manji.desystem.common.result.BaseObjectResult;
import com.manji.desystem.common.result.BaseResult;
import com.manji.desystem.common.util.SessionUtil;
import com.manji.desystem.controller.interceptor.LoginAuth;
import com.manji.desystem.dao.Reason;
import com.manji.desystem.dao.account.Account;
import com.manji.desystem.service.FeignSheetService;
import com.manji.desystem.service.FeignUserService;
import com.manji.desystem.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/api-sheet", description = "工单数据接口")
@RequestMapping(value = "/sheet")
public class sheetController {

    @Autowired
    private FeignSheetService feignSheetService;

    @Autowired
    private FeignUserService feignUserService;

    @LoginAuth
    @ApiOperation(value = "查询所有工单")
    @PostMapping(value = "/findSheet")
    public BaseObjectResult<Object> findSheet(@Validated SelectSheetVo selectSheetVo, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.selectSheet(selectSheetVo, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询工单详情")
    @GetMapping(value = "/selectSheetDetail")
    public BaseObjectResult<Object> selectSheetDetails(@RequestParam(value = "id") Integer id, @RequestParam(value = "sendId", required = false) Integer sendId, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.selectSheetDetails(id, sendId, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "删除工单")
    @GetMapping(value = "/deleteSheet")
    public BaseResult deleteSheet(@RequestParam(value = "id") Integer id, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.delSheet(id, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "提交工单")
    @GetMapping(value = "/submitSheet")
    public BaseResult submitSheet(@RequestParam(value = "id") Integer id, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.delSheet(id, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询工单数量")
    @GetMapping(value = "/findSheetCount")
    public BaseObjectResult<Object> findSheetCount(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.countSheet(sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "备注")
    @GetMapping(value = "/remark")
    public BaseResult remark(@RequestParam(value = "id") int id, @RequestParam(value = "content") String content, @RequestParam(value = "sessionId") String sessionId, @RequestParam(value = "pics", required = false) String pics) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.remark(id, content, sessionId, pics);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "恢复工单")
    @GetMapping(value = "/recoverSheet")
    public BaseResult recoverSheet(@RequestParam(value = "id") int id, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.recovery(id, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "关闭工单")
    @GetMapping(value = "/closeSheet")
    public BaseResult closeSheet(@RequestParam(value = "id") int id, @RequestParam(value = "content") String content, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.close(id, content, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "重置工单")
    @GetMapping(value = "/resetSheet")
    public BaseResult resetSheet(@RequestParam(value = "id") int id, @RequestParam(value = "content") String content, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.reset(id, content, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @ApiOperation(value = "查询工单日志记录")
    @GetMapping(value = "/selectSheetLog")
    public BaseObjectResult<Object> selectSheetLog(@RequestParam(value = "id") int id) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.journalRecord(id);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "审核工单")
    @PostMapping(value = "/examine")
    public BaseResult examine(@Validated ExamineVo examineVo, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.examine(examineVo, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "处理完毕")
    @PostMapping(value = "/finishedProcess")
    public BaseResult finishedProcess(@Validated AssignVo assignVo, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.finishedProcess(assignVo, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "驳回工单")
    @GetMapping(value = "/backSheet")
    public BaseResult backSheet(@RequestParam(value = "id") Integer id, @RequestParam(value = "content") String content, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.rejectSheet(id, content, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询站内消息(首页)")
    @GetMapping(value = "/selInformations")
    public BaseObjectResult<Object> selInformations(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.selInformations(sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "清除站内信息(首页)")
    @GetMapping(value = "/clearInformations")
    public BaseResult clearInformations(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.clearInformations(sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询自己创建的工单(首页)")
    @GetMapping(value = "/sheetSelf")
    public BaseObjectResult<Object> sheetSelf(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.sheetSelf(sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "推送信息")
    @GetMapping(value = "/pushInfo")
    public BaseObjectResult<Object> pushInfo(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>();

        JSONObject object = feignSheetService.pushInfo(sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());
        baseResult.setResult(object.get("datas"));

        return baseResult;
    }

    @ApiOperation(value = "关闭推送信息")
    @GetMapping(value = "/clearInformationsOne")
    public BaseResult clearInformationsOne(@RequestParam(value = "informaId") Integer informaId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.clearInformationsOne(informaId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "指派工单")
    @GetMapping(value = "/assignSheet")
    public BaseResult assignSheet(@Validated AssignVo assignVo, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.assign(assignVo, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "保存并指派")
    @GetMapping(value = "/saveAssignSheet")
    public BaseResult saveAssignSheet(@Validated CommonSheetVo commonSheetVo, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.saveAssign(commonSheetVo, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "保存工单和保存并提交工单")
    @GetMapping(value = "/saveOrSubmit")
    public BaseResult saveOrSubmit(@Validated CommonSheetVo commonSheetVo, @RequestParam(value = "type") Integer type, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseResult baseResult = new BaseResult();

        JSONObject object = feignSheetService.saveSubmitSheet(commonSheetVo, type, sessionId);

        baseResult.setCode(object.get("state").toString());
        baseResult.setMessage(object.get("message").toString());

        return baseResult;
    }

    @ApiOperation(value = "查询所有部门")
    @GetMapping(value = "/findAllDept")
    public BaseObjectResult<List<DeptVo>> findAllDept() throws Exception {

        BaseObjectResult<List<DeptVo>> baseResult = new BaseObjectResult<List<DeptVo>>("0", "成功");

        JSONArray object = feignUserService.findAllDept();
        List<DeptVo> collection = JSONObject.parseArray(object.toString(), DeptVo.class);
        baseResult.setResult(collection);

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "根据姓名查询人员及部门")
    @GetMapping(value = "/findByName")
    public BaseObjectResult<Object> findByName(@RequestParam(value = "realName") String realName, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("0", "成功");

        JSONArray object = feignUserService.findByName(realName);
        if (object.size() == 0) {
            throw new UserNullException("没有此用户");
        }
        baseResult.setResult(object);

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询所在组拥有审核权限的人员")
    @GetMapping(value = "/findExaminePeople")
    public BaseObjectResult<Object> findExaminePeople(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("0", "成功");

        Account account = (Account) SessionUtil.httpsession().getAttribute("account");

        JSONArray object = feignUserService.findExaminePeople(account.getUser().getId(), "/sheet/examine");
        if (object==null || object.size()==0 ) {
            throw new BusinessDealException("没有审核人员");
        }
        baseResult.setResult(object);

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询部门下面的人员")
    @GetMapping(value = "/findDeptUser")
    public BaseObjectResult<Object> findDeptUser(@RequestParam(value = "deptId",required = true) Integer deptId, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("0", "成功");

        JSONObject object = feignUserService.findUserByDeptId(deptId,1,9999999);

        if (object == null) {
            throw new BusinessDealException("该部门下面暂无人员");
        }
        baseResult.setResult(object);

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询一级来电")
    @GetMapping(value = "/findFirstReason")
    public BaseObjectResult<List<Reason>> findFirstReason(@RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<List<Reason>> baseResult = new BaseObjectResult<List<Reason>>("0", "成功");

        List<Reason> result = feignSheetService.findFirstReason();
        baseResult.setResult(result);

        return baseResult;
    }

    @LoginAuth
    @ApiOperation(value = "查询二级来电")
    @GetMapping(value = "/findSecondReason")
    public BaseObjectResult<List<Reason>> findSecondReason(@RequestParam(value = "id") Integer id, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<List<Reason>> baseResult = new BaseObjectResult<List<Reason>>("0", "成功");

        List<Reason> result = feignSheetService.findSecondReason(id);
        baseResult.setResult(result);

        return baseResult;

    }

    @LoginAuth
    @ApiOperation(value = "根据 手机号、账号、商家名称查询商家、用户、合伙人、非满集用户的信息")
    @GetMapping(value = "/findCustomer")
    public BaseObjectResult<Object> findCustomer(@RequestParam(value = "userName",required = false) String userName, @RequestParam(value = "mobile" ,required = false) String mobile, @RequestParam(value = "shopName" , required = false) String shopName, @RequestParam(value = "userType") String userType, @RequestParam(value = "sessionId") String sessionId) throws Exception {

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("0", "成功");

        if((userName == null || "".equals(userName)) && (mobile == null || "".equals(mobile)) && (shopName == null || "".equals(shopName))){
            throw new BusinessDealException("参数[userName],[mobile],[shopName]至少一个不能为空");
        }

        JSONObject object=feignSheetService.findCustomer(userName,mobile,shopName,userType);
        baseResult.setResult(object);

        return baseResult;

    }

    @LoginAuth
    @ApiOperation(value = "根据姓名或者店铺名称查询信息")
    @GetMapping(value = "/getUserNameByPersonName")
    public BaseObjectResult<Object> getUserNameByPersonName(@RequestParam("personName") String personName,@RequestParam("sessionId") String sessionId){

        BaseObjectResult<Object> baseResult = new BaseObjectResult<Object>("0", "成功");

        Object object = feignSheetService.getUserNameByPersonName(personName);
        baseResult.setResult(object);

        return baseResult;
    }

}
