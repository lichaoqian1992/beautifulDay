package com.manji.orService.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.orService.dao.account.Account;
import com.manji.orService.feignInterface.PicInterface;
import com.manji.orService.feignInterface.SessionIdInterface;
import com.manji.orService.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FeignServiceImpl implements FeignService {

    @Autowired
    private SessionIdInterface sessionIdInterface;

    @Autowired
    private PicInterface picInterface;

    /**
     * 根据sessionId获取用户信息
     *
     * @param sessionId
     * @return
     */
    @Override
    public Account sessionId(String sessionId) {
        return sessionIdInterface.sessionId(sessionId);
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @Override
    public JSONObject uploadPic(MultipartFile file) {
        return picInterface.uploadPic(file);
    }

    /**
     * 根据名字获取用户信息
     *
     * @param name
     * @return
     */
    @Override
    public JSONArray name(String name) {
        return sessionIdInterface.findByName(name);
    }


}