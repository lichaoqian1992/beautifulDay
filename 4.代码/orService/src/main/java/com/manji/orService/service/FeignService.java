package com.manji.orService.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.orService.dao.account.Account;
import org.springframework.web.multipart.MultipartFile;

public interface FeignService {

    Account sessionId(String sessionId);

    JSONObject uploadPic(MultipartFile file);

    JSONArray name(String name);

}