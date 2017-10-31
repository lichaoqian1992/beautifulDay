package com.manji.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.dao.Log;
import com.manji.cl.dao.Share;
import com.manji.cl.mapper.ShareMapper;
import com.manji.cl.service.BaseService;
import com.manji.cl.service.LogService;
import com.manji.cl.service.ShareService;
import com.manji.cl.utils.DatesUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
@Service
public class ShareServiceImpl implements ShareService{


    private static final Logger logger = Logger.getLogger(ShareServiceImpl.class);

    @Autowired
    private ShareMapper mapper;
    @Autowired
    private LogService service;//日志service

    @Autowired
    private BaseService baseService;

    BaseResult result = new BaseResult();

    /**
     * 查询我的分享 当月/当日
     * @param id
     * @param type
     * @return
     */
    @Override
    public BaseResult myShare(String id, String type) throws ParseException {

        logger.info("======开始调用myShare接口，调用时间："+new DatesUtils().time()+"参数值：id="+id+"type="+type+"==========");

        //2.开始查询
        //2.1处理时间
        Map<String,String> map = baseService.processingTime(type);
        String stime = map.get("stime").toString();
        String etime = map.get("etime").toString();
        System.out.println(stime+":"+etime);

        //2.2开始查询
        List<Share> shares = mapper.myShare(id,stime,etime);
        if(shares != null && shares.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(shares);
        }else{
            result.setCode("200");
            result.setContent("数据不存在");
            result.setResult(shares);
        }
        //3.记录操作日志
        //保存操作日志
        Log log = new Log();
        log.setCl_type("select");
        log.setCl_content(id+"在"+new DatesUtils().time()+"查询了自己分享的新闻数量");
        log.setCl_operator_id(Integer.parseInt(id));
        log.setCl_operator_name("测试账号");
        log.setCl_ctime(new DatesUtils().time());
        log.setCl_isdel(1);
        boolean b = service.addLog(log);
        if(!b){
            return null;
        }
        logger.info("======结束调用myShare接口，结束时间："+new DatesUtils().time()+"返回值："+ JSONObject.toJSON(result).toString()+"==========");

        return result;
    }
}
