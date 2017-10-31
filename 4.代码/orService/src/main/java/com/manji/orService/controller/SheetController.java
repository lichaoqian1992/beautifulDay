package com.manji.orService.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.manji.orService.dao.*;
import com.manji.orService.dao.account.Account;
import com.manji.orService.dao.account.Menu;
import com.manji.orService.dao.account.Role;
import com.manji.orService.enums.ErrorCodeEnums;
import com.manji.orService.service.FeignService;
import com.manji.orService.service.SheetService;
import com.manji.orService.util.SessionUtil;
import com.manji.orService.util.WebUtil;
import com.manji.orService.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/sheet"})
@Api(value = "api-sheet", description = "工单接口")
public class SheetController extends BaseController {

    @Autowired
    private SheetService sheetService;

    @Autowired
    private FeignService feignService;


    /**
     * 新增工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/insertSheet", method = RequestMethod.POST)
    @ApiOperation(value = "修改工单", notes = "commonSheetVo:工单基本信息,sessionId:用户的sessionId")
    public Object insertSheet(@RequestBody @Validated CommonSheetVo commonSheetVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            Map<String, Object> map = IncommonSheetVo(commonSheetVo);

            boolean insertSheet = sheetService.insertSheet(map, account, false);//调用新增方法

            if (!insertSheet) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {

            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果

    }

    /**
     * 修改工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/updateSheet", method = RequestMethod.POST)
    @ApiOperation(value = "修改工单", notes = "commonSheetVo:工单基本信息,sessionId:用户的sessionId")
    public Object updateSheet(@RequestBody @Validated CommonSheetVo commonSheetVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isEdit(Integer.parseInt(commonSheetVo.getOrId()), account);//判断是否可以编辑
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            Map<String, Object> map = UpcommonSheetVo(commonSheetVo);


            /******************************************************************************/

            boolean updateSheet = sheetService.updateSheet(map, account, false);//调用修改方法

            if (!updateSheet) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }


        } catch (Exception e) {

            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, null);//返回结果
    }

    /**
     * 查询工单
     *
     * @param selectSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/selectSheet", method = RequestMethod.POST)
    @ApiOperation(value = "查询工单", notes = "selectSheetVo:查询条件,sessionId:用户的sessionId")
    public Object selectSheet(@RequestBody @Validated SelectSheetVo selectSheetVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        PageInfo hashMapList = null;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {
            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            //赋值部门
            String dept = "";
            List<Role> role = account.getRole();
            for (Role x : role) {
                dept += x.getDept_name();
            }

            selectSheetVo.setOrFounderId(account.getUser().getId());
            selectSheetVo.setOrFounderDept(dept);

            JurisdictionVo juris=new JurisdictionVo();
            List<Menu> menuList=account.getMenu();
            for(Menu x : menuList){
                if(x.getTitle().equals("驳回工单")){
                    juris.setRejeshetBoo(true);
                }

                if(x.getTitle().equals("处理完毕待确认")){
                    juris.setOvfishetBoo(true);
                }

                if(x.getTitle().equals("待提交的工单")){
                    juris.setPendshetBoo(true);
                }

                if(x.getTitle().equals("待我处理的派单")){
                    juris.setDealshetBoo(true);
                }
            }

            hashMapList = sheetService.selectSheet(selectSheetVo,juris);

        } catch (Exception e) {

            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, hashMapList);//返回结果
    }

    /**
     * 查询工单详情
     *
     * @param id
     * @param sendId
     * @return
     */
    @RequestMapping(value = "/selectSheetDetails", method = RequestMethod.POST)
    @ApiOperation(value = "查询工单详情", notes = "id:工单ID,sendId:抄送ID")
    public Object selectSheetDetails(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "sendId", required = false) Integer sendId) {

        HashMap<String, Object> hashMapList = null;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {
            if (sendId != null && !sendId.equals("")) {
                sheetService.upSendStatus(sendId);
            }

            hashMapList = sheetService.selectSheetDetails(id);
        } catch (Exception e) {

            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, hashMapList);//返回结果
    }

    /**
     * 删除工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/delSheet", method = RequestMethod.POST)
    @ApiOperation(value = "删除工单", notes = "id:工单ID,sessionId:用户的sessionId")
    public Object delSheet(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isDel(id, account);//判断是否可以删除
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /****************************************验证**************************************/

            boolean boo = sheetService.delSheet(id);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {

            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 提交工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/submitSheet", method = RequestMethod.POST)
    @ApiOperation(value = "提交工单", notes = "id:工单ID,sessionId:用户的sessionId")
    public Object submitSheet(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "sessionId", required = true) String sessionId) {

        JSONArray nameArray=feignService.name("彭怡");
        JSONObject o=nameArray.getJSONObject(0);

        int count = 0;
        boolean boo = true;

        HandleVo handleVo = new HandleVo();
        handleVo.setOrHandleId(o.get("id").toString());
        handleVo.setOrHandle(o.get("vsername").toString());
        handleVo.setOrHandleDept(o.get("deptname").toString());

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();


        try {
            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");

            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            SheetLogDao sheetLogDao = sheetService.selectLog(id);//查询最后一次修改工单的记录
            if (sheetLogDao == null) {
                return message(1, "日志未记录", null);//返回结果
            }

            count = sheetService.isDel(id, account);//判断是否可以提交
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /********************************************************************************/

            boo = sheetService.submitSheet(id, account, handleVo);//提交操作

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }


        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 查询工单数量
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/countSheet", method = RequestMethod.POST)
    @ApiOperation(value = "查询工单数量", notes = "sessionId:用户的sessionId")
    public Object countSheet(@RequestParam(value = "sessionId", required = true) String sessionId) {

        CountSheetDao countSheetDao = null;
        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/
            Account account = (Account) SessionUtil.httpsession().getAttribute("account");

            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }
            /*********************************************************************************/

            int userId = Integer.parseInt(account.getUser().getId());

            countSheetDao = sheetService.countSheet(userId);
        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, countSheetDao);//返回结果
    }

    /**
     * 保存并提交工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/saveSubmitSheet", method = RequestMethod.POST)
    @ApiOperation(value = "保存并提交工单", notes = "commonSheetVo:工单基本信息;sessionId:用户的sessionId")
    public Object saveSubmitSheet(@RequestBody @Validated CommonSheetVo commonSheetVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        JSONArray nameArray=feignService.name("彭怡");
        JSONObject o=nameArray.getJSONObject(0);

        commonSheetVo.setOrHandle(o.get("vsername").toString());
        commonSheetVo.setOrHandleDept(o.get("deptname").toString());
        commonSheetVo.setOrHandleId(Integer.parseInt(o.get("id").toString()));

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();
        boolean boo = true;
        int count = 0;

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            if (commonSheetVo.getOrId() != null) {

                count = sheetService.isEdit(Integer.parseInt(commonSheetVo.getOrId()), account);//判断是否可以编辑
                if (count <= 0) {
                    return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
                }

                count = sheetService.isSubmit(Integer.parseInt(commonSheetVo.getOrId()), account);//判断是否可以提交
                if (count <= 0) {
                    return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
                }

            }

            /********************************************************************************/

            if (commonSheetVo.getOrId() == null) {//判断是否是新增工单

                Map<String, Object> map = IncommonSheetVo(commonSheetVo);

                boo = sheetService.insertSheet(map, account, true);//调用新增方法并提交

            } else {

                Map<String, Object> map = UpcommonSheetVo(commonSheetVo);

                boo = sheetService.updateSheet(map, account, true);//调用修改方法并提交

            }

            if (!boo) {
                code = ErrorCodeEnums.Success.getCode();
                message = ErrorCodeEnums.Success.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 备注
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/remark", method = RequestMethod.POST)
    @ApiOperation(value = "备注", notes = "id:工单ID;content:备注内容;sessionId:用户的sessionId;pics:图片")
    public Object remark(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "content", required = true) String content, @RequestParam(value = "sessionId", required = true) String sessionId, @RequestParam(value = "pics", required = false) String pics) {

        RemarkVo remarkVo = new RemarkVo();
        remarkVo.setId(String.valueOf(id));
        remarkVo.setContent(content);
        remarkVo.setPics(pics);

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();
        boolean boo = true;

        try {

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            boo = sheetService.remark(remarkVo, account);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 恢复工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    @ApiOperation(value = "恢复工单", notes = "id:工单ID;sessionId:用户的sessionId")
    public Object recovery(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "sessionId", required = true) String sessionId) {

        boolean boo = true;
        int count = 0;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            boo = sheetService.recovery(id);

            /****************************************验证**************************************/

            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isRecovery(id, account);//判断是否可以恢复
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/


            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 指派工单
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    @ApiOperation(value = "指派工单", notes = "AssignVo:操作信息;sessionId:用户的sessionId")
    public Object assign(@RequestBody @Validated AssignVo assignVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;
        boolean boo = true;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");

            /****************************************验证**************************************/

            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isAssign(Integer.parseInt(assignVo.getId()), account);//判断是否可以指派
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }


            /*********************************************************************************/

            boo = sheetService.assign(assignVo, account, false);//指派工单

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, null);//返回结果
    }

    /**
     * 关闭工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    @ApiOperation(value = "关闭工单", notes = "id:工单ID;content:关闭内容;sessionId:用户的sessionId")
    public Object close(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "content", required = true) String content, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;
        boolean boo = true;
        RemarkVo remarkVo = new RemarkVo();
        remarkVo.setId(String.valueOf(id));
        remarkVo.setContent(content);

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isClose(Integer.parseInt(remarkVo.getId()), account);//判断是否可以关闭
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            boo = sheetService.close(remarkVo, account);//关闭工单

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, null);//返回结果
    }

    /**
     * 重置工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ApiOperation(value = "重置工单", notes = "id:工单ID;content:重置内容;sessionId:用户的sessionId")
    public Object reset(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "content", required = true) String content, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;
        boolean boo = true;
        RemarkVo remarkVo = new RemarkVo();
        remarkVo.setId(String.valueOf(id));
        remarkVo.setContent(content);

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();


        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isReset(Integer.parseInt(remarkVo.getId()), account);//判断是否可以重置
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            boo = sheetService.close(remarkVo, account);//关闭工单

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, null);//返回结果
    }


    /**
     * 查询工单日志记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/journalRecord", method = RequestMethod.POST)
    @ApiOperation(value = "工单详情日志记录", notes = "id:工单ID")
    public Object journalRecord(@RequestParam(value = "id", required = true) int id) {

        JournalRecord journalRecord = null;
        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            journalRecord = sheetService.journalRecord(id);

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, journalRecord);//返回结果
    }

    /**
     * 审核工单
     *
     * @param examineVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/examine", method = RequestMethod.POST)
    @ApiOperation(value = "审核工单", notes = "examineVo:操作信息;sessionId:用户的sessionId")
    public Object examine(@RequestBody @Validated ExamineVo examineVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        int count = 0;
        boolean boo = true;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isExamine(Integer.parseInt(examineVo.getId()), account);//判断是否可以审核
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            boo = sheetService.examine(examineVo, account, examineVo.getBooSolve());

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果

    }

    /**
     * 处理完毕
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/finishedProcess", method = RequestMethod.POST)
    @ApiOperation(value = "处理完毕", notes = "AssignVo:操作信息;sessionId:用户的sessionId")
    public Object finishedProcess(@RequestBody @Validated AssignVo assignVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        JSONArray nameArray=feignService.name("彭怡");
        JSONObject o=nameArray.getJSONObject(0);

        int count = 0;
        boolean boo = true;

        assignVo.setOrHandleId(o.get("id").toString());
        assignVo.setOrHandle(o.get("vsername").toString());
        assignVo.setOrHandleDept(o.get("deptname").toString());

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isFinishedProcess(Integer.parseInt(assignVo.getId()), account);//判断是否可以处理
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            boo = sheetService.finishedProcess(assignVo, account);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, null);//返回结果
    }

    /**
     * 驳回工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/rejectSheet", method = RequestMethod.POST)
    @ApiOperation(value = "驳回工单", notes = "id：工单id;content:驳回内容;sessionId:用户的sessionId")
    public Object rejectSheet(@RequestParam(value = "id", required = true) int id, @RequestParam(value = "content", required = true) String content, @RequestParam(value = "sessionId", required = true) String sessionId) {

        boolean boo = true;
        int count = 0;

        RemarkVo remarkVo = new RemarkVo();
        remarkVo.setId(String.valueOf(id));
        remarkVo.setContent(content);

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isReject(Integer.parseInt(remarkVo.getId()), account);//判断是否可以驳回
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            boo = sheetService.rejectSheet(Integer.parseInt(remarkVo.getId()), remarkVo.getContent(), account);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 保存并指派
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/saveAssign", method = RequestMethod.POST)
    @ApiOperation(value = "保存并指派", notes = "commonSheetVo:工单基本信息参数;sessionId:用户的sessionId")
    public Object saveAssign(@RequestBody @Validated CommonSheetVo commonSheetVo, @RequestParam(value = "sessionId", required = true) String sessionId) {

        boolean boo = true;
        int count = 0;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            count = sheetService.isEdit(Integer.parseInt(commonSheetVo.getOrId()), account);//判断是否可以编辑
            if (count <= 0) {
                return message(ErrorCodeEnums.IllegalOperation.getCode(), ErrorCodeEnums.IllegalOperation.getMessage(), null);//返回结果
            }

            /*********************************************************************************/


            Map<String, Object> map = UpcommonSheetVo(commonSheetVo);


            boo = sheetService.updateSheet(map, account, false);//调用修改方法

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
                return message(code, message, null);//返回结果
            }

            AssignVo assign = new AssignVo();
            assign.setOrHandle(commonSheetVo.getOrHandle());
            assign.setOrHandleId(String.valueOf(commonSheetVo.getOrHandleId()));
            assign.setOrHandleDept(commonSheetVo.getOrHandleDept());
            assign.setOrSendList(commonSheetVo.getOrSendList());
            assign.setId(commonSheetVo.getOrId());
            assign.setContent("");

            boo = sheetService.assign(assign, account, true);//指派

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
                return message(code, message, null);//返回结果
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }

    /**
     * 查询站内消息(首页)
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/selInformations", method = RequestMethod.POST)
    @ApiOperation(value = "查询站内消息(首页)", notes = "sessionId：用户的sessionId")
    public Object selInformations(@RequestParam(value = "sessionId", required = true) String sessionId) {

        List<HashMap<String, Object>> map = null;
        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            /*********************************************************************************/

            int userId = Integer.parseInt(account.getUser().getId());
            map = sheetService.selInformations(userId);

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, map);//返回结果
    }

    /**
     * 清除站内信息(首页)
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/clearInformations", method = RequestMethod.POST)
    @ApiOperation(value = "清除站内信息(首页)", notes = "sessionId：用户的sessionId")
    public Object clearInformations(@RequestParam(value = "sessionId", required = true) String sessionId) {

        boolean boo = true;
        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            /*********************************************************************************/
            int userId = Integer.parseInt(account.getUser().getId());
            boo = sheetService.clearInformations(userId);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
                return message(code, message, null);//返回结果
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }

        return message(code, message, null);//返回结果
    }


    /**
     * 查询自己创建的工单(首页)
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "sheetSelf", method = RequestMethod.POST)
    @ApiOperation(value = "查询自己创建的工单(首页)", notes = "sessionId：用户的sessionId")
    public Object sheetSelf(@RequestParam(value = "sessionId", required = true) String sessionId) {

        List<HashMap<String, Object>> map = null;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {
            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            /*********************************************************************************/
            map = sheetService.sheetSelf(account);

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }


        return message(code, message, map);//返回结果
    }

    /**
     * 推送信息
     * @return
     */
    @RequestMapping(value = "pushInfo", method = RequestMethod.POST)
    @ApiOperation(value = "推送信息", notes = "sessionId：用户的sessionId")
    public Object pushInfo(@RequestParam(value = "sessionId", required = true) String sessionId) {

        HashMap<String, Object> map = null;

        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {
            /****************************************验证**************************************/

            Account account = (Account) SessionUtil.httpsession().getAttribute("account");
            if (account == null) {
                return message(ErrorCodeEnums.UserNull.getCode(), ErrorCodeEnums.UserNull.getMessage(), null);//返回结果
            }

            map = sheetService.pushInfo(account);
            if(map!=null){
                if(map.get("is_see").toString().equals("0") || map.get("is_push").toString().equals("0")){
                    map=null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }
        return message(code, message, map);//返回结果
    }

    /**
     * 关闭推送信息
     * @return
     */
    @RequestMapping(value = "clearInformationsOne", method = RequestMethod.POST)
    @ApiOperation(value = "关闭推送信息", notes = "informaId：推送信息ID")
    public Object clearInformationsOne(@RequestParam(value = "informaId", required = true) Integer informaId) {

        boolean boo=true;
        code = ErrorCodeEnums.Success.getCode();
        message = ErrorCodeEnums.Success.getMessage();

        try {

            boo=sheetService.clearInformationsOne(informaId);

            if (!boo) {
                code = ErrorCodeEnums.UnknownError.getCode();
                message = ErrorCodeEnums.UnknownError.getMessage();
                return message(code, message, null);//返回结果
            }

        } catch (Exception e) {
            e.printStackTrace();
            code = ErrorCodeEnums.ServiceTemporarilyUnavailable.getCode();
            message = ErrorCodeEnums.ServiceTemporarilyUnavailable.getMessage();
        }
        return message(code, message, null);//返回结果
    }

}
