package com.manji.cl.service.impl;

import com.manji.cl.base.BaseResult;
import com.manji.cl.dao.Log;
import com.manji.cl.mapper.CountMapper;
import com.manji.cl.service.CountService;
import com.manji.cl.service.LogService;
import com.manji.cl.utils.DatesUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/8/29.
 */
@Service
public class CountServiceImpl implements CountService{

    private static final Logger logger = Logger.getLogger(CountServiceImpl.class);

    @Autowired
    private CountMapper mapper;

    @Autowired
    private LogService service;//日志service

    BaseResult result = new BaseResult();

    @Override
    public BaseResult getUserCount(String id, String stime, String etime) {

        //1.判断参数
        if(id == null || id.isEmpty()){
            result.setCode("404");
            result.setContent("参数值id不能为空");
            return result;
        }
        if(stime == null || stime.isEmpty()){
            result.setCode("404");
            result.setContent("参数值stime不能为空");
            return result;
        }
        if(etime == null || etime.isEmpty()){
            result.setCode("404");
            result.setContent("参数值etime不能为空");
            return result;
        }
        logger.info("======开始调用getUserCount接口，调用时间："+new DatesUtils().time()+"参数值：id="+id+"stime="+stime+"etime="+etime+"=========");

        //2.开始业务
        int count = mapper.getUserCount(id,stime,etime+" 23:59:59");
        result.setCode("200");
        result.setContent("查询成功");
        result.setResult(count);

        //3.记录操作日志
        //保存操作日志
        Log log = new Log();
        log.setCl_type("select");
        log.setCl_content(id+"在"+new DatesUtils().time()+"查询了自己推荐入驻满集网的用户数量");
        log.setCl_operator_id(Integer.parseInt(id));
        log.setCl_operator_name("测试账号");
        log.setCl_ctime(new DatesUtils().time());
        log.setCl_isdel(1);
        boolean b = service.addLog(log);
        if(b){
            return result;
        }else{
            return null;
        }
    }
}
