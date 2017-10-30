package com.manji.tesystem.service;

import com.manji.tesystem.controller.api.knowledge.requestbody.AddKnowledgeBody;
import com.manji.tesystem.controller.api.knowledge.requestbody.EditKnowledgeBody;
import com.manji.tesystem.feign.response.common.ResultPageInfoObject;
import com.manji.tesystem.feign.response.knowledge.Information;
import org.springframework.web.bind.annotation.RequestParam;

public interface KnowledgeManager {
    /**
     * 查询知识库列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultPageInfoObject<Information> list(String title, Integer treeId, Integer state, int pageNum, int pageSize)throws Exception;

    /**
     * 更新某个姿势的状态
     * @param id
     * @param state
     * @return
     */
    boolean updateInformationState(Integer id,Integer state)throws Exception;

    /**
     * 查询某条知识点的详情
     * @param id
     * @return
     */
    Information findInformationById(@RequestParam("id") Integer id)throws Exception;
    /**
     *添加一个知识点
     * @param body
     * @return
     */
    boolean addInfomation(AddKnowledgeBody body)throws Exception;
    /**
     * 修改某个知识点信息
     * @param body
     * @return
     */
    boolean updateInformation(EditKnowledgeBody body)throws Exception;
    /**
     * 批量删除知识点
     * @return
     */
    boolean deleteInformation(@RequestParam("id")int [] ids)throws Exception;
}
