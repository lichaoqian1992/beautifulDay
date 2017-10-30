package com.manji.cusystem.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.dao.common.Role;
import com.manji.cusystem.feign.UServiceFeignService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
@Data
public class BaseController {

    @Autowired
    private UServiceFeignService uServiceFeignService;

    /**
     * 处理ajax 跨域的返回值
     * @param data
     * @return
     */
    public String checkJsonP(String data){

        return "callback("+data+")";
    }

    /**
     * 校验是否登录
     * @return
     */
    public BaseResult logins(String sessionId){

        BaseResult result = new BaseResult();
        Account account = new Account();
        List<Role> roleList = new ArrayList<Role>();
        try{
            if(sessionId == null || "".equals(sessionId)){
                result.setCode("404");
                result.setContent("参数sessionId不能为空");
            }else{
                JSONObject back = uServiceFeignService.logins(sessionId);

                System.out.println(back+"===============");
                if(back.get("result").toString().equals("0")){
                    if(back.get("code").toString().equals("1000")) {

                        //处理返回数据
                        JSONObject user = back.getJSONObject("user");
                        account.setUserId(user.getString("id"));
                        account.setUserName(user.getString("username"));
                        account.setRealName(user.getString("vsername"));
                        account.setMobile(user.getString("mobile"));
                        account.setEmail(user.getString("email"));
                        account.setJob(user.getString("job"));
                        //处理角色信息
                        JSONArray roles = back.getJSONArray("role");
                        for (int i = 0; i < roles.size(); i++) {
                            Role role = new Role();
                            JSONObject object = roles.getJSONObject(i);
                            role.setRoleName(object.getString("role_name"));
                            role.setDeptName(object.getString("dept_name"));
                            roleList.add(role);
                        }
                        account.setRole(roleList);
                        result.setCode("200");
                        result.setContent("校验通过");
                        result.setResult(account);
                    }
                }else{
                    result.setCode("404");
                    result.setContent("登录已失效或sessionId不正确");
                    return result;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }

        return result;
    }

    private String path;

    public ResponseEntity<InputStreamResource> downLoad() throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        if (fileSystemResource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=%s", fileSystemResource.getFilename()));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(fileSystemResource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(fileSystemResource.getInputStream()));
        }
        return null;
    }
}
