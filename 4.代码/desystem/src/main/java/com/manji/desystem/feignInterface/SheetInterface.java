package com.manji.desystem.feignInterface;

import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.vo.AssignVo;
import com.manji.desystem.vo.CommonSheetVo;
import com.manji.desystem.vo.ExamineVo;
import com.manji.desystem.vo.SelectSheetVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 工单接口
 */
@FeignClient(value = "orservice")
public interface SheetInterface {

    /**
     * 新增工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/insertSheet", method = RequestMethod.POST)
    public JSONObject insertSheet(@RequestBody CommonSheetVo commonSheetVo, @RequestParam("sessionId") String sessionId);

    /**
     * 修改工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/updateSheet", method = RequestMethod.POST)
    public JSONObject updateSheet(@RequestBody CommonSheetVo commonSheetVo, @RequestParam("sessionId") String sessionId);

    /**
     * 查询工单
     *
     * @param selectSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheet", method = RequestMethod.POST)
    public JSONObject selectSheet(@RequestBody SelectSheetVo selectSheetVo, @RequestParam("sessionId") String sessionId);

    /**
     * 查看工单详情
     * @param id
     * @param sendId
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/selectSheetDetails", method = RequestMethod.POST)
    public JSONObject selectSheetDetails(@RequestParam("id") int id, @RequestParam("sendId") Integer sendId,@RequestParam("sessionId") String sessionId);

    /**
     * 删除工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/delSheet", method = RequestMethod.POST)
    public JSONObject delSheet(@RequestParam("id") int id, @RequestParam("sessionId") String sessionId);

    /**
     * 提交工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/submitSheet", method = RequestMethod.POST)
    public JSONObject submitSheet(@RequestParam("id") int id, @RequestParam("sessionId") String sessionId);

    /**
     * 查看工单数量
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/countSheet", method = RequestMethod.POST)
    public JSONObject countSheet(@RequestParam("sessionId") String sessionId);

    /**
     * 保存并提交工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/saveSubmitSheet", method = RequestMethod.POST)
    public JSONObject saveSubmitSheet(@RequestBody CommonSheetVo commonSheetVo, @RequestParam("sessionId") String sessionId);

    /**
     * 备注
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/remark", method = RequestMethod.POST)
    public JSONObject remark(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String sessionId, @RequestParam("pics") String pics);

    /**
     * 恢复工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/recovery", method = RequestMethod.POST)
    public JSONObject recovery(@RequestParam("id") int id, @RequestParam("sessionId") String sessionId);

    /**
     * 指派工单
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/assign", method = RequestMethod.POST)
    public JSONObject assign(@RequestBody AssignVo assignVo, @RequestParam("sessionId") String sessionId);

    /**
     * 关闭工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/close", method = RequestMethod.POST)
    public JSONObject close(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String sessionId);

    /**
     * 重置工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/reset", method = RequestMethod.POST)
    public JSONObject reset(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String sessionId);

    /**
     * 查询工单日志记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sheet/journalRecord", method = RequestMethod.POST)
    public JSONObject journalRecord(@RequestParam("id") int id);

    /**
     * 审核工单
     *
     * @param examineVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/examine", method = RequestMethod.POST)
    public JSONObject examine(@RequestBody ExamineVo examineVo, @RequestParam("sessionId") String sessionId);

    /**
     * 处理完毕
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/finishedProcess", method = RequestMethod.POST)
    public JSONObject finishedProcess(@RequestBody AssignVo assignVo, @RequestParam("sessionId") String sessionId);

    /**
     * 驳回工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/rejectSheet", method = RequestMethod.POST)
    public JSONObject rejectSheet(@RequestParam("id") int id, @RequestParam("content") String content, @RequestParam("sessionId") String sessionId);

    /**
     * 保存并指派
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/saveAssign", method = RequestMethod.POST)
    public JSONObject saveAssign(@RequestBody CommonSheetVo commonSheetVo, @RequestParam("sessionId") String sessionId);

    /**
     * 查询站内消息(首页)
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/selInformations", method = RequestMethod.POST)
    public JSONObject selInformations(@RequestParam("sessionId") String sessionId);

    /**
     * 清除站内信息(首页)
     *
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/clearInformations", method = RequestMethod.POST)
    public JSONObject clearInformations(@RequestParam("sessionId") String sessionId);

    /**
     * 查询自己创建的工单(首页)
     *
     * @param sessionId
     * @return
     */

    @RequestMapping(value = "/sheet/sheetSelf", method = RequestMethod.POST)
    public JSONObject sheetSelf(@RequestParam("sessionId") String sessionId);

    /**
     * 推送信息
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/sheet/pushInfo", method = RequestMethod.POST)
    public JSONObject pushInfo(@RequestParam(value = "sessionId") String sessionId);

    /**
     * 关闭推送信息
     * @param informaId
     * @return
     */
    @RequestMapping(value = "/sheet/clearInformationsOne", method = RequestMethod.POST)
    public JSONObject clearInformationsOne(@RequestParam(value = "informaId") Integer informaId);
}
