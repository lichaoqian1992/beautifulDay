package com.manji.cusystem.service;

import com.manji.cusystem.base.BaseResult;

/**
 * Created by pudding on 2017-9-18.(YYR)
 */
public interface AckService {

    BaseResult addTree(int pid,int menuId,String title);

    BaseResult updateTree(String title,int id);

    BaseResult deleteTree(int id);

    BaseResult findTreeByMenuId(int menuId);

    BaseResult findTreeSun(int id);

    BaseResult insertInformation(String title,String content,String category,int treeId,int state);

    BaseResult updateInformationState(int state,int id);

    BaseResult updateInformation(int id,String title,String content,String category,int tree_id,int state);

    BaseResult findInfo(Integer state,Integer tree_id,String title,String content,int pageNum,int pageSize);

    BaseResult deleteInformation(int id[]);

    BaseResult findInformationById(int id);

    BaseResult findInformationByTrees(int treeId);

    BaseResult prmentTrees(int treeId);

    BaseResult findInformationByContent(String content);
}
