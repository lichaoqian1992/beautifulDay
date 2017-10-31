package com.manji.cusystem.feign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/9/4.
 */
@FeignClient(name = "orService" ,url = "${orService.service.url}")//name:表示调用的是那个服务，后面的url表示服务地址
public interface OrServiceFeignService {

    /**
     * 新增工单
     * @param sheetJson
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/insertSheet")
    JSONObject addSheet(@RequestParam("jsonToString") String sheetJson,@RequestParam("user") String user);//@RequestParam("jsonToString") String sheetJson

    /**
     * 查询所有工单
     * @param sheetJson
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheet")
    JSONObject findSheets(@RequestParam("jsonToString") String sheetJson,@RequestParam("user") String user);

    /**
     * 查询工单数量
     * @param id
     * @return
     */
    @RequestMapping(value = "/sheet/countSheet")
    JSONObject findSheetCount(@RequestParam("id") String id);

    /**
     * 仅保存工单
     * @param jsonToString
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/updateSheet")
    JSONObject updateSheet(@RequestParam("jsonToString") String jsonToString,@RequestParam("user") String user);

    /**
     * 保存并提交工单
     * @param jsonToString
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/saveSubmitSheet")
    JSONObject saveSubmitSheet(@RequestParam("jsonToString")String jsonToString, @RequestParam("user")String u);
    /**
     * 查询工单详情
     * @param sheetId    工单id
     * @param sendId     抄送人id
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheetDetails")
    JSONObject selectSheetDetail(@RequestParam("id") String sheetId,@RequestParam("sendId")String sendId);

    /**
     * 查询工单流程
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/journalRecord")
    JSONObject selectSheetLog(@RequestParam("id")String sheetId);

    /**
     * 提交工单
     * @param id    工单id
     * @param u     操作人信息
     * @return
     */
    @RequestMapping(value = "/sheet/submitSheet")
    JSONObject submitSheet(@RequestParam("jsonToString") String id, @RequestParam("user") String u);

    /**
     * 审核工单
     * @param jsonToString
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/examine")
    JSONObject examineSheet(@RequestParam("jsonToString") String jsonToString, @RequestParam("user") String u);

    /**
     * 删除工单
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/delSheet")
    JSONObject deleteSheet(@RequestParam("id") String sheetId);

    /**
     * 添加备注信息
     * @param sheetId
     * @param u
     * @return
     */
    @RequestMapping(value = "/sheet/remark")
    JSONObject remark(@RequestParam("jsonToString") String sheetId, @RequestParam("user") String u);

    /**
     * 驳回工单
     * @param jsonToString
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/rejectSheet")
    JSONObject rejectSheet(@RequestParam("jsonToString") String jsonToString, @RequestParam("user") String user);

    /**
     * 关闭（重置）工单
     * @param jsonToString
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/close")
    JSONObject closeSheet(@RequestParam("jsonToString") String jsonToString, @RequestParam("user") String user);

    /**
     * 恢复工单
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/sheet/recovery")
    JSONObject recoverSheet(@RequestParam("id") String sheetId);

    /**
     * 指派工单
     * @param jsonToString
     * @param user
     * @return
     */
    @RequestMapping(value = "/sheet/assign")
    JSONObject assignSheet(@RequestParam("jsonToString") String jsonToString, @RequestParam("user") String user);

    @RequestMapping("/sheet/saveAssign")
    JSONObject saveAssignSheet(@RequestParam("jsonToString") String sheets, @RequestParam("user") String user, @RequestParam("content")String content);
}
