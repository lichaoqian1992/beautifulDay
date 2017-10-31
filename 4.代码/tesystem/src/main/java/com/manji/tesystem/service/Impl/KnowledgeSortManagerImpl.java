package com.manji.tesystem.service.Impl;

import com.manji.tesystem.common.exception.BusinessDealException;
import com.manji.tesystem.feign.knowledge.KnowledgeFeignSortService;
import com.manji.tesystem.feign.response.knowledge.Tree;
import com.manji.tesystem.service.KnowledgeSortManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeSortManagerImpl implements KnowledgeSortManager {
    @Value("${project.code}")
    private String projectCode;
    @Autowired
    private KnowledgeFeignSortService knowledgeFeignSortService;
    /**
     * 通过项目code查询全部栏目分类
     * @return
     */
    @Override
    public List<Tree> findAllTree()throws Exception {
        List<Tree> result = knowledgeFeignSortService.findAllTree(projectCode);
        return result;
    }

    /**
     * 通过级别查询栏目分类
     * @param menuId
     * @return
     */
    @Override
    public List<Tree> findTreeByMenuId(Integer menuId)throws Exception {
        List<Tree> result = knowledgeFeignSortService.findTreeByMenuId(projectCode,menuId);
        return result;
    }

    /**
     * 查询某个档位下的子集列表
     * @param id
     * @return
     */
    @Override
    public List<Tree> findTreeSun(Integer id)throws Exception {
        List<Tree> result = knowledgeFeignSortService.findTreeSun(projectCode,id);
        return result;
    }

    /**
     * 添加节点
     * @param pId
     * @param menuId
     * @param title
     * @return
     */
    @Override
    public boolean add(Integer pId, Integer menuId, String title) throws Exception{
        return knowledgeFeignSortService.add(projectCode,pId,menuId,title);
    }

    /**
     * 修改节点
     * @param id
     * @param title
     * @return
     */
    @Override
    public boolean updateTitle(Integer id, String title) throws Exception{
        return knowledgeFeignSortService.updateTitle(id, title);
    }

    /**
     * 删除节点
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) throws Exception{
        //判断是否含有子节点
        boolean sbc = knowledgeFeignSortService.isTree(id,projectCode);
        if(sbc){
            throw new BusinessDealException("当前结点下面含有子节点，不允许直接删除");
        }
        //判断是否有知识点添加在这个节点上
        int count =  knowledgeFeignSortService.findInformationByTreeCount(id);
        if(count > 0){
            throw new BusinessDealException("当前结点下面含知识点，不允许直接删除");
        }
        return knowledgeFeignSortService.delete(id);
    }
}
