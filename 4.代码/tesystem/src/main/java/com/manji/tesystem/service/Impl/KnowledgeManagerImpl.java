package com.manji.tesystem.service.Impl;

import com.manji.tesystem.controller.api.knowledge.requestbody.AddKnowledgeBody;
import com.manji.tesystem.controller.api.knowledge.requestbody.EditKnowledgeBody;
import com.manji.tesystem.feign.knowledge.KnowledgeFeignService;
import com.manji.tesystem.feign.response.common.ResultPageInfoObject;
import com.manji.tesystem.feign.response.knowledge.Information;
import com.manji.tesystem.service.KnowledgeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeManagerImpl implements KnowledgeManager{
    @Value("${project.code}")
    private String projectCode;

    @Autowired
    private KnowledgeFeignService knowledgeFeignService;
    /**
     * 查询知识库列表
     *
     * @param title
     * @param state
     * @param pageNum
     * @param pageSize @return
     */
    @Override
    public ResultPageInfoObject<Information> list(String title, Integer treeId, Integer state, int pageNum, int pageSize) throws Exception{
        ResultPageInfoObject<Information> result = knowledgeFeignService.findInfo(title,treeId,state,projectCode,pageNum,pageSize);
        return result;
    }

    /**
     * 更新某个姿势的状态
     *
     * @param id
     * @param state
     * @return
     */
    @Override
    public boolean updateInformationState(Integer id, Integer state)throws Exception {
        return knowledgeFeignService.updateInformationState(id, state);
    }

    /**
     * 查询某条知识点的详情
     *
     * @param id
     * @return
     */
    @Override
    public Information findInformationById(Integer id)throws Exception {
        return knowledgeFeignService.findInformationById(id);
    }

    /**
     * 添加一个知识点
     * @param body
     * @return
     */
    @Override
    public boolean addInfomation(AddKnowledgeBody body) throws Exception {
        return knowledgeFeignService.insertInformation(body.getTitle(),body.getContent(),
                body.getCategory(),body.getTree_id(),body.getState());
    }

    /**
     * 修改某个知识点信息
     *
     * @param body
     * @return
     */
    @Override
    public boolean updateInformation(EditKnowledgeBody body) throws Exception {
        return knowledgeFeignService.updateInformation(body.getId(),body.getTitle(),body.getContent(),
                body.getCategory(),body.getTree_id(),body.getState());
    }

    /**
     * 批量删除知识点
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteInformation(int[] ids) throws Exception{
        return knowledgeFeignService.deleteInformation(ids);
    }
}
