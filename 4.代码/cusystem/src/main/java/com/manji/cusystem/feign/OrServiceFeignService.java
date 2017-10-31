package com.manji.cusystem.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.vo.sheets.AssignSheetVo;
import com.manji.cusystem.vo.sheets.ExamineSheetVo;
import com.manji.cusystem.vo.sheets.QuerySheetsVo;
import com.manji.cusystem.vo.sheets.SheetsVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/9/4.
 */
@FeignClient(value = "orService")//name:表示调用的是那个服务，后面的url表示服务地址
public interface OrServiceFeignService {

    /**
     * 新增工单
     * @param sheetJson
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/insertSheet",method = RequestMethod.POST)
    JSONObject addSheet(@RequestBody SheetsVo sheetJson, @RequestParam("sessionId") String user);//@RequestParam("jsonToString") String sheetJson

    /**
     * 查询所有工单
     * @param sheetJson
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheet",method = RequestMethod.POST)
    JSONObject findSheets(@RequestBody QuerySheetsVo sheetJson, @RequestParam("sessionId") String user);

    /**
     * 查询工单数量
     * @param id
     * @return
     */
    @RequestMapping(value = "/sheet/countSheet",method = RequestMethod.POST)
    JSONObject findSheetCount(@RequestParam("sessionId") String id);

    /**
     * 仅保存工单
     * @param jsonToString
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/updateSheet",method = RequestMethod.POST)
    JSONObject updateSheet(@RequestBody SheetsVo jsonToString,@RequestParam("sessionId") String user);

    /**
     * 保存并提交工单
     * @param jsonToString
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/saveSubmitSheet",method = RequestMethod.POST)
    JSONObject saveSubmitSheet(@RequestBody SheetsVo jsonToString, @RequestParam("sessionId")String u);
    /**
     * 查询工单详情
     * @param sheetId    工单id
     * @param sendId     抄送人id
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheetDetails",method = RequestMethod.POST)
    JSONObject selectSheetDetail(@RequestParam("id") int sheetId,@RequestParam("sendId")Integer sendId,@RequestParam("sessionId")String sessionId);

    /**
     * 查询工单流程
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/journalRecord",method = RequestMethod.POST)
    JSONObject selectSheetLog(@RequestParam("id")int sheetId);

    /**
     * 提交工单
     * @param id    工单id
     * @param u     操作人信息
     * @return
     */
    @RequestMapping(value = "/sheet/submitSheet",method = RequestMethod.POST)
    JSONObject submitSheet(@RequestParam("id") String id, @RequestParam("sessionId") String u);

    /**
     * 审核工单
     * @param
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/examine")
    JSONObject examineSheet(@RequestBody ExamineSheetVo examineVo, @RequestParam("sessionId") String u);

    /**
     * 删除工单
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/delSheet",method = RequestMethod.POST)
    JSONObject deleteSheet(@RequestParam("id") int sheetId,@RequestParam("sessionId") String sessionId);

    /**
     * 添加备注信息
     * @param sheetId
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/remark",method = RequestMethod.POST)
    JSONObject remark(@RequestParam("id") int sheetId, @RequestParam("content") String remark,@RequestParam("sessionId") String u,@RequestParam("pics") String pics);

    /**
     * 驳回工单
     * @param
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/rejectSheet",method = RequestMethod.POST)
    JSONObject rejectSheet(@RequestParam("id") int id, @RequestParam("content") String content,@RequestParam("sessionId") String user);

    /**
     * 关闭工单
     * @param
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/close",method = RequestMethod.POST)
    JSONObject closeSheet(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String user);

    /**
     * 重置工单
     * @param id
     * @param content
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/reset",method = RequestMethod.POST)
    JSONObject resetSheet(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String user);
    /**
     * 恢复工单
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/recovery",method = RequestMethod.POST)
    JSONObject recoverSheet(@RequestParam("id") int sheetId, @RequestParam("sessionId") String user);

    /**
     * 指派工单
     * @param vo
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/assign",method = RequestMethod.POST)
    JSONObject assignSheet(@RequestBody AssignSheetVo vo, @RequestParam("sessionId") String user);

    /**
     * 保存并指派
     * @param sheets
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/saveAssign",method = RequestMethod.POST)
    JSONObject saveAssignSheet(@RequestBody SheetsVo sheets, @RequestParam("sessionId") String user);

    /**
     * 查询自己的工单
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/sheetSelf",method = RequestMethod.POST)
    JSONObject sheetSelf(@RequestParam("sessionId") String sessionId);

    /**
     * 处理完毕
     * @param vo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/finishedProcess",method = RequestMethod.POST)
    JSONObject finishedProcess(@RequestBody AssignSheetVo vo, @RequestParam("sessionId") String sessionId);

    /**
     * 消息通知
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/selInformations",method = RequestMethod.POST)
    JSONObject selInformations(@RequestParam("sessionId") String sessionId);

    /**
     * 清除消息通知
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/clearInformations",method = RequestMethod.POST)
    JSONObject clearInformations(@RequestParam("sessionId") String sessionId);

    /**
     * 推送消息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/pushInfo",method = RequestMethod.POST)
    JSONObject pushInfo(@RequestParam("sessionId") String sessionId);

    /**
     * 关闭推送消息
     * @param i
     * @return
     */
    @RequestMapping(value = "/sheet/clearInformationsOne",method = RequestMethod.POST)
    JSONObject clearPushInfo(@RequestParam("informaId") int i);
}
