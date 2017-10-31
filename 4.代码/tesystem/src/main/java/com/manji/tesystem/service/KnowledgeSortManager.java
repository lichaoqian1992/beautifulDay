package com.manji.tesystem.service;

import com.manji.tesystem.feign.response.knowledge.Tree;

import java.util.List;

public interface KnowledgeSortManager {
    /**
     * 通过项目code查询全部栏目分类
     * @return
     */
    List<Tree> findAllTree()throws Exception;
    /**
     * 通过级别查询栏目分类
     * @return
     */
    List<Tree> findTreeByMenuId(Integer menuId)throws Exception;
    /**
     * 查询某个档位下的子集列表
     * @return
     */
    List<Tree> findTreeSun(Integer id)throws Exception;

    /**
     *添加节点
     * @param menuId
     * @param title
     * @return
     */
    boolean add(Integer pId,Integer menuId,String title)throws Exception;

    /**
     * 修改节点
     * @param id
     * @param title
     * @return
     */
    boolean updateTitle(Integer id,String title)throws Exception;

    /**
     * 删除节点
     * @param id
     * @return
     */
    boolean delete(Integer id)throws Exception;
}
