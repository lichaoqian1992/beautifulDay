package com.manji.cl.controller;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.service.CountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/29.
 * 统计管理
 */
@Controller
@RequestMapping(value = "/count")
public class CountController {

    @Autowired
    private CountService service;
    /**
     * 查询某个业务员总的用户量
     * @param id
     * @param stime
     * @param etime
     * @return
     */
    @RequestMapping(value = "/getUserCount")
    @ResponseBody
    public String getUserCount(@Param("id") String id,@Param("stime") String stime,@Param("etime") String etime){

        BaseResult result = service.getUserCount(id,stime,etime);
        return JSONObject.toJSON(result).toString();

    }
}
