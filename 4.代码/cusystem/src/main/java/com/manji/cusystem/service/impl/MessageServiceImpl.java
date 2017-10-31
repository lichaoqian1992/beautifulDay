package com.manji.cusystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.base.PageResult;
import com.manji.cusystem.dao.Message.Message;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.feign.DataHostFeignService;
import com.manji.cusystem.mapper.MessageMapper;
import com.manji.cusystem.service.LogService;
import com.manji.cusystem.service.MessageService;
import com.manji.cusystem.utils.DatesUtils;
import com.manji.cusystem.utils.MailUtils;
import com.manji.cusystem.utils.PageUtils;
import com.manji.cusystem.vo.common.LogVo;
import com.manji.cusystem.vo.message.MessageVo;
import com.manji.cusystem.vo.message.QueryMessageVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘英杰 on 2017/9/10.
 */
@Service
public class MessageServiceImpl implements MessageService{


    private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageMapper mapper;

    @Autowired
    private LogService service;

    @Autowired
    private DataHostFeignService dataHostFeignService;

    @Autowired
    JavaMailSender javaMailSender;

    BaseResult result = new BaseResult();

    /**
     * 新增短信
     * @param vo
     * @param user
     * @return
     */
    @Override
    @Transactional
    public BaseResult addMessage(MessageVo vo, Account user,String oType,String url) {

        logger.info("=====开始调用addMessage接口，调用时间："+new DatesUtils().time()+"，参数："+ JSONObject.toJSONString(vo));
        //1.判断是直接发送短信还是保存为草稿
        JSONObject back = null;
        String sendTime="",status="",content = "";
        vo.setCusTime(new DatesUtils().time());
        if(oType.equals("send")){

            //2.调用发送短信的接口，发送短信。返回成功，则继续执行，失败则回滚
            //判断是发送短信还是发送邮件
            if(vo.getCusKind().equals("mobile")){

                back = dataHostFeignService.sendMessage(vo.getCusType(),vo.getCusAcceptType(),vo.getCusContent());
            }else if(vo.getCusKind().equals("email")){
                if(vo.getCusTheme() == null || "".equals(vo.getCusTheme())){
                    result.setCode("404");
                    result.setContent("邮件主题不能为空");
                    result.setResult("");
                    return result;
                }
                /*if(url == null || "".equals(url)){

                    content = vo.getCusContent();
                }else{
                    content = "<html><body>"+vo.getCusContent()+"<img src='"+vo.getCusUrl()+"' ></body></html>";
                }*/
                content = vo.getCusContent();
                System.out.println("======="+content+"=========");
                //处理邮件内容

                new MailUtils().sendTextMail(vo.getCusAcceptType(),vo.getCusTheme(),content.replaceAll("X",""),javaMailSender);

            }
            //获取查询
            sendTime = new DatesUtils().time();
            status = "1";
        }else{
            sendTime = "-";
            status = "0";
            vo.setCusCount("-");//发送短信的数量==========================================
        }
        vo.setCusSendTime(sendTime);
        vo.setCusStatus(status);

        //3.处理返回值
        if(back == null || back.get("code").toString().equals("200")){//相当于保存为草稿
            //4.保存短信发送记录
            boolean flag = mapper.addMessage(vo);
            if(flag){
                //保存日志信息
                LogVo log = new LogVo();
                log.setCus_type("add");
                log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"新增"+vo.getCusKind()+"信息");
                log.setCus_user_id(Integer.parseInt(user.getUserId()));
                log.setCus_user_name(user.getUserName());
                log.setCus_ctime(new DatesUtils().time());
                log.setCus_isdel(1);
                boolean b = service.addLog(log);
                if(b){
                    result.setCode("200");
                    result.setContent("新增成功");
                    result.setResult("");
                }
            }
        }else{
            result.setCode("500");
            result.setContent("短信发送失败，不保存短信记录");
            result.setResult("");
        }
        logger.info("=====结束调用addMessage接口，结束调用时间："+new DatesUtils().time()+"，返回值："+ JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 查询短信记录
     * @param vo
     * @return
     */
    @Override
    public BaseResult selectMessage(QueryMessageVo vo) {

        logger.info("=====开始调用selectMessage接口，调用时间："+new DatesUtils().time());

        if(vo.getCusKind() == null || "".equals(vo.getCusKind())){
            result.setCode("404");
            result.setContent("参数cusKind必须为mobile或者email");
            result.setResult("");
            return result;
        }
        if(vo.getPageSize() == 0){
            vo.setPageSize(15);
        }
        if(vo.getPageNum() != 0){
            vo.setPageNum(vo.getPageNum()-1);
        }
        List<Message> list = mapper.selectMessage(vo);
        int count = mapper.selectMessageCount(vo);
        PageResult page = new PageUtils().pageInit(vo.getPageNum(),vo.getPageSize(),count);
        if(list != null && list.size() > 0){
            result.setCode("200");
            result.setContent("查询成功");
            result.setPage(page);
            result.setResult(list);
        }else{
            result.setCode("404");
            result.setContent("暂无数据");
            result.setPage(null);
            result.setResult("");
        }
        logger.info("=====结束调用selectMessage接口，结束调用时间："+new DatesUtils().time()+"返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 查询短信详情
     * @param cusId
     * @return
     */
    @Override
    public BaseResult selectMessageDetail(String cusId) {

        logger.info("=====开始调用selectMessage接口，调用时间："+new DatesUtils().time());

        Message message = mapper.selectMessageDetail(cusId);

        result.setCode("200");
        result.setContent("查询成功");
        result.setPage(null);
        result.setResult(message);
        logger.info("=====结束调用selectMessage接口，结束调用时间："+new DatesUtils().time()+"返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 删除短信
     * @param cusId
     * @return
     */
    @Override
    public BaseResult delMessage(String cusId) {

        logger.info("=====开始调用delMessage接口，调用时间："+new DatesUtils().time());

        int a = mapper.delMessage(cusId);
        if(a > 0){
            result.setCode("200");
            result.setContent("删除成功");
            result.setResult("");
        }else{
            result.setCode("500");
            result.setContent("删除失败，请联系技术人员");
            result.setResult("");
        }
        logger.info("=====结束调用delMessage接口，结束调用时间："+new DatesUtils().time()+"返回值："+JSONObject.toJSONString(result));

        return result;
    }

    /**
     * 选择接收对象时查询总数据
     * @param acceptType
     * @return
     */
    @Override
    public BaseResult getCount(String acceptType,String cusKind) {

        if(acceptType == null || "".equals(acceptType) || cusKind == null || "".equals(cusKind)){
            result.setCode("404");
            result.setContent("参数acceptType接收对象类型,cusKind类型不能为空");
            result.setResult("");
            return  result;
        }
        logger.info("=====开始调用delMessage接口，调用时间："+new DatesUtils().time());

        //获取总数据
        JSONObject back = dataHostFeignService.getCount(acceptType,cusKind);
        //获取电话号码集合
        JSONObject mobile = dataHostFeignService.getMobileInfo(acceptType,cusKind);

        //处理返回值
        Map<String,Object> map = new HashMap<String, Object>();
        if(back.get("code").toString().equals("200") && mobile.get("code").toString().equals("200")){
            map.put(cusKind,mobile.get("result"));
            map.put("count",back.get("result"));
            result.setCode("200");
            result.setContent("查询成功");
            result.setResult(map);
        }
        logger.info("=====结束调用delMessage接口，结束调用时间："+new DatesUtils().time()+",返回值："+JSONObject.toJSONString(result));
        return result;
    }

    /**
     * 短信详情页面的发送按钮
     * @param vo
     * @param user
     * @return
     */
    @Override
    public BaseResult updateMessage(MessageVo vo, Account user) {

        logger.info("=====开始调用updateMessage接口，调用时间："+new DatesUtils().time()+"参数值："+JSONObject.toJSONString(vo));
        try{
            //短信id不能为空
            if(vo.getCusId() == null || "".equals(vo.getCusId())){
                result.setCode("404");
                result.setContent("参数cusId短信id不能为空");
                result.setResult("");
                return result;
            }
            //2.调用发送短信的接口，发送短信。返回成功，则继续执行，失败则回滚
            JSONObject back = new JSONObject();

            if(vo.getCusKind().equals("mobile")){//发送短信

                back = dataHostFeignService.sendMessage(vo.getCusType(),vo.getCusAcceptType(),vo.getCusContent());
            }else if(vo.getCusKind().equals("email")){//发送邮件
                if(vo.getCusTheme() == null || "".equals(vo.getCusTheme())){
                    result.setCode("404");
                    result.setContent("参数cusTheme邮件主题不能为空");
                    result.setResult("");
                    return result;
                }
                String content = "";
                /*if(vo.getCusUrl() == null || "".equals(vo.getCusUrl())){

                    content = vo.getCusContent();
                }else {
                    content = "<html><body>" + vo.getCusContent() + "<img src='" + vo.getCusUrl() + "' ></body></html>";
                }*/
                content = vo.getCusContent();
                System.out.println("======="+content+"=========");
                //处理邮件内容

                new MailUtils().sendTextMail(vo.getCusAcceptType(),vo.getCusTheme(),content.replaceAll("X",""),javaMailSender);

                //new MailUtils().sendTextMail(vo.getCusAcceptType(),vo.getCusTheme(),content,javaMailSender);
                back.put("code","200");
            }
            if(back.get("code").toString().equals("200")){
                //3.修改短信信息
                vo.setCusSendTime(new DatesUtils().time());
                vo.setCusStatus("1");
                int a = mapper.updateMessage(vo);
                if(a > 0){
                    //4.保存日志
                    LogVo log = new LogVo();
                    log.setCus_type("update");
                    log.setCus_content(user.getRealName()+"在"+new DatesUtils().time()+"发送了短信id为"+vo.getCusId()+"的短信");
                    log.setCus_user_id(Integer.parseInt(user.getUserId()));
                    log.setCus_user_name(user.getUserName());
                    log.setCus_ctime(new DatesUtils().time());
                    log.setCus_isdel(1);
                    boolean b = service.addLog(log);
                    if(b){
                        result.setCode("200");
                        result.setContent("发送成功");
                        result.setResult("");
                    }
                }
            }else{
                result.setCode("403");
                result.setContent("发送失败，请联系技术人员");
                result.setResult(back.get("result"));
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("500");
            result.setContent("系统异常");
            result.setResult("异常类型："+e.getClass().getName().toString());
        }
        return result;
    }
}
