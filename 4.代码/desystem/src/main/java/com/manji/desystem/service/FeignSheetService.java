package com.manji.desystem.service;

import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.common.result.BaseResult;
import com.manji.desystem.dao.Reason;
import com.manji.desystem.dao.account.Account;
import com.manji.desystem.vo.AssignVo;
import com.manji.desystem.vo.CommonSheetVo;
import com.manji.desystem.vo.ExamineVo;
import com.manji.desystem.vo.SelectSheetVo;

import java.util.List;

public interface FeignSheetService {
    /**
     * 新增工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    JSONObject insertSheet(CommonSheetVo commonSheetVo, String sessionId) throws Exception;

    /**
     * 修改工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    JSONObject updateSheet(CommonSheetVo commonSheetVo, String sessionId) throws Exception;

    /**
     * 查询工单
     *
     * @param selectSheetVo
     * @param sessionId
     * @return
     */
    JSONObject selectSheet(SelectSheetVo selectSheetVo, String sessionId) throws Exception;

    /**
     * 查看工单详情
     *
     * @param id
     * @param sendId
     * @return
     */
    JSONObject selectSheetDetails(int id, Integer sendId, String sessionId) throws Exception;

    /**
     * 删除工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    JSONObject delSheet(int id, String sessionId) throws Exception;

    /**
     * 提交工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    JSONObject submitSheet(int id, String sessionId) throws Exception;

    /**
     * 查看工单数量
     *
     * @param sessionId
     * @return
     */
    JSONObject countSheet(String sessionId) throws Exception;

    /**
     * 保存并提交工单
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    JSONObject saveSubmitSheet(CommonSheetVo commonSheetVo, Integer typr, String sessionId) throws Exception;

    /**
     * 备注
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    JSONObject remark(int id, String content, String sessionId, String pics) throws Exception;

    /**
     * 恢复工单
     *
     * @param id
     * @param sessionId
     * @return
     */
    JSONObject recovery(int id, String sessionId) throws Exception;

    /**
     * 指派工单
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    JSONObject assign(AssignVo assignVo, String sessionId) throws Exception;

    /**
     * 关闭工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    JSONObject close(int id, String content, String sessionId) throws Exception;

    /**
     * 重置工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    JSONObject reset(int id, String content, String sessionId) throws Exception;

    /**
     * 查询工单日志记录
     *
     * @param id
     * @return
     */
    JSONObject journalRecord(int id) throws Exception;

    /**
     * 审核工单
     *
     * @param examineVo
     * @param sessionId
     * @return
     */
    JSONObject examine(ExamineVo examineVo, String sessionId) throws Exception;

    /**
     * 处理完毕
     *
     * @param assignVo
     * @param sessionId
     * @return
     */
    JSONObject finishedProcess(AssignVo assignVo, String sessionId) throws Exception;

    /**
     * 驳回工单
     *
     * @param id
     * @param content
     * @param sessionId
     * @return
     */
    JSONObject rejectSheet(int id, String content, String sessionId) throws Exception;

    /**
     * 保存并指派
     *
     * @param commonSheetVo
     * @param sessionId
     * @return
     */
    JSONObject saveAssign(CommonSheetVo commonSheetVo, String sessionId) throws Exception;

    /**
     * 查询站内消息(首页)
     *
     * @param sessionId
     * @return
     */
    JSONObject selInformations(String sessionId) throws Exception;

    /**
     * 清除站内信息(首页)
     *
     * @param sessionId
     * @return
     */
    JSONObject clearInformations(String sessionId) throws Exception;

    /**
     * 查询自己创建的工单(首页)
     *
     * @param sessionId
     * @return
     */
    JSONObject sheetSelf(String sessionId) throws Exception;

    /**
     * 推送信息
     *
     * @param sessionId
     * @return
     * @throws Exception
     */
    JSONObject pushInfo(String sessionId) throws Exception;

    /**
     * 关闭推送信息
     *
     * @param informaId
     * @return
     * @throws Exception
     */
    JSONObject clearInformationsOne(Integer informaId) throws Exception;

    /**
     * 查询一级分类
     *
     * @return
     * @throws Exception
     */
    List<Reason> findFirstReason() throws Exception;

    /**
     * 查询二级分类
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<Reason> findSecondReason(Integer id) throws Exception;

    /**
     * 根据 手机号、账号、商家名称查询商家、用户、合伙人、非满集用户的信息
     *
     * @param userName
     * @param mobile
     * @param shopName
     * @param userType
     * @return
     */
    JSONObject findCustomer(String userName, String mobile, String shopName, String userType);

    /**
     * 根据姓名或者店铺名称查询信息
     *
     * @param personName
     * @return
     */
    Object getUserNameByPersonName(String personName);

}