package com.ackservice.Service;

import com.ackservice.Dto.Tree;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by pudding on 2017-9-13.(YYR)
 */
public interface TreeService {

    boolean insertTree(int id,int menuId,String title,String pjt_code);

    boolean delectTree(int id);

    JSONArray findTreeByMenuId(int mentId, String pjt_id);

    JSONArray findTreeSun(int pid,String pjt_code);

    boolean isTree(int id,String pjt_code);

    JSONArray findAllTree(String pjt_code);

    boolean updateTitle(String title,int id);

}
