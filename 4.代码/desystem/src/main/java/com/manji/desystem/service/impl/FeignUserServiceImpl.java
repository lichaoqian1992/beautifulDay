package com.manji.desystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.desystem.feignInterface.LoginInterface;
import com.manji.desystem.service.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignUserServiceImpl implements FeignUserService {

    @Autowired
    private LoginInterface loginInterface;


    @Override
    public JSONArray findAllDept() throws Exception {
        return loginInterface.findAllDept();
    }

    @Override
    public JSONArray findByName(String realName) throws Exception {
        return loginInterface.findByName(realName);
    }

    @Override
    public JSONArray findExaminePeople(String userId, String path) {
        return loginInterface.findExaminePeople(userId,path);
    }

    @Override
    public JSONObject findUserByDeptId(Integer deptId, Integer pageNum, Integer pageSize) {
        return loginInterface.findUserByDeptId(deptId,pageNum,pageSize,"");
    }

    @Override
    public JSONArray findByUserId(Integer personId) {
        return loginInterface.findByUserId(personId);
    }


}