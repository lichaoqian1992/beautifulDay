package com.ackservice.Service;

import com.ackservice.Dto.Tree;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by pudding on 2017-9-14.(YYR)
 */
public interface InformationService {

    int findInformationByTreeCount(int treeId);

    boolean updateInformationState(int state,int id);

    boolean delectInformation(int id);

    JSONArray findInformationByState(int state, String pjt_code);

    JSONArray findInformationByTrees(int treeId,String pjt_code);

    JSONArray findInformationBytitle(String title,String pjt_code);

    JSONArray findInformationBycontent(String content,String pjt_code);

    boolean updateInformation(int id,String title,String content,String category,int tree_id,int state);

    boolean insertInformation(String title,String content,String category,int tree_id,int state);
}
