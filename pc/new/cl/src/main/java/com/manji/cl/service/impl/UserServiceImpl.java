package com.manji.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manji.cl.base.BaseResult;
import com.manji.cl.dao.Log;
import com.manji.cl.dao.Ruser;
import com.manji.cl.mapper.UserMapper;
import com.manji.cl.service.BaseService;
import com.manji.cl.service.LogService;
import com.manji.cl.service.UserService;
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
public class UserServiceImpl implements UserService{

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper mapper;//

    @Autowired
    private LogService service;//日志service

    @Autowired
    private BaseService baseService;

    BaseResult result = new BaseResult();
    /**
     * 用户注册保存信息
     * @param buyer_id
     * @param user_id
     * @return
     */
    @Override
    public BaseResult registerUser(String buyer_id, String user_id) {

        Ruser ruser = new Ruser();
        //1.判断传入的参数值是否为空
        if(buyer_id == null || buyer_id.isEmpty()){
            result.setCode("404");
            result.setContent("参数值buyer_id不能为空");
            return result;
        }
        if(user_id == null || user_id.isEmpty()){
            result.setCode("404");
            result.setContent("参数值user_id不能为空");
            return result;
        }
        logger.info("======开始调用registerUser接口，调用时间："+new DatesUtils().time()+"参数值：buyer_id="+buyer_id+"user_id="+user_id+"==========");
        //2.保存数据
        ruser.setCl_buyer_id(Integer.parseInt(buyer_id));
        ruser.setCl_user_id(Integer.parseInt(user_id));
        ruser.setCl_ctime(new DatesUtils().time());
        ruser.setCl_isdel(1);
        boolean flag = mapper.registerUser(ruser);
        if(flag){
            //保存操作日志
            Log log = new Log();
            log.setCl_type("save");
            log.setCl_content(user_id+"在"+new DatesUtils().time()+"成功推荐用户"+buyer_id+"入驻满集网");
            log.setCl_operator_id(Integer.parseInt(user_id));
            log.setCl_operator_name("测试账号");
            log.setCl_ctime(new DatesUtils().time());
            log.setCl_isdel(1);
            boolean b = service.addLog(log);
            if(b){
                result.setCode("200");
                result.setContent("操作成功");
                result.setResult(ruser);
            }
        }else{
            result.setCode("500");
            result.setContent("操作失败");
            result.setResult(ruser);
        }
        logger.info("======结束调用registerUser接口，结束时间："+new DatesUtils().time()+"返回值："+ JSONObject.toJSON(result).toString()+"==========");
        return result;
    }

    /**
     * 查询业务员的当日新增和当月新增的用户数量
     * @param id       业务员id
     * @param type    区分是单日还是当月
     * @return
     */
    public BaseResult findUser(String id,String type) throws ParseException {

        //1.判断传入的参数值是否为空
        if(id == null || id.isEmpty()){
            result.setCode("404");
            result.setContent("参数值id不能为空");
            return result;
        }
        if(type == null || type.isEmpty()){
            result.setCode("404");
            result.setContent("参数值type不能为空");
            return result;
        }
        logger.info("======开始调用findUser接口，调用时间："+new DatesUtils().time()+"参数值：id="+id+"type="+type+"==========");

        //2.开始查询
        //2.1处理时间
        Map<String,String> map = baseService.processingTime(type);
        String stime = map.get("stime").toString();
        String etime = map.get("etime").toString();
        System.out.println(stime+":"+etime);
        //2.2开始查询
        List<Ruser> rusers = mapper.findUser(Integer.parseInt(id),stime,etime);
        if(rusers != null && rusers.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(rusers);
        }
        logger.info("======结束调用findUser接口，结束时间："+new DatesUtils().time()+"返回值："+ JSONObject.toJSON(result).toString()+"==========");
        return result;
    }
}
