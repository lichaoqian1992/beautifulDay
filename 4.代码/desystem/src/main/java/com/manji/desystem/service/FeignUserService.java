package com.manji.desystem.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface FeignUserService {

    JSONArray findAllDept() throws Exception;

    JSONArray findByName(String realName) throws Exception;

    JSONArray findExaminePeople(String userId,String path);

    JSONObject findUserByDeptId(Integer deptId,Integer pageNum,Integer pageSize);

    JSONArray findByUserId(Integer personId);

}