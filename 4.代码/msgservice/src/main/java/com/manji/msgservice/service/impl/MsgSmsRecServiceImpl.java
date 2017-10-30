package com.manji.msgservice.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.msgservice.common.exception.BusinessDealException;
import com.manji.msgservice.common.utils.DateUtils;
import com.manji.msgservice.common.utils.mybatis.manager.ManagerImpl;
import com.manji.msgservice.controller.sms.SmsRquestBody;
import com.manji.msgservice.feign.response.Message;
import com.manji.msgservice.feign.sms.SmsFeignService;
import com.manji.msgservice.mapper.MsgSmsRecMapper;
import com.manji.msgservice.model.MsgSmsRec;
import com.manji.msgservice.model.MsgSmsRecExample;
import com.manji.msgservice.model.MsgTemplate;
import com.manji.msgservice.service.MsgSmsRecService;

@Service
public class MsgSmsRecServiceImpl extends ManagerImpl<MsgSmsRec,MsgSmsRecExample,Long> implements MsgSmsRecService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private MsgSmsRecMapper thisMapper;
    @Autowired SmsFeignService smsFeignService;
    @Autowired
    public MsgSmsRecServiceImpl(MsgSmsRecMapper thisMapper){
        this.mapper = thisMapper;
        this.thisMapper = thisMapper;
    }

    /**
     * 发送短息逻辑，处理是否及时发送，以及录入库，等到定时任务发送
     * @param msgTemplate
     * @param body
     * @param content
     * @throws Exception
     */
    @Override
    public void sendSmsBiz(MsgTemplate msgTemplate, SmsRquestBody body, String content) throws Exception {
        String sendTimeType = body.getSendTimeType();
        //封装录入库记录
        MsgSmsRec recModel1 = new MsgSmsRec();
        recModel1.setTitle(msgTemplate.getTemplateName());
        recModel1.setContent(content);
        recModel1.setSendTimeType(sendTimeType);
        recModel1.setMobile(body.getMobile());
        recModel1.setTemplateId(msgTemplate.getTemplateId());
        //及时
        if("timely".equals(sendTimeType)){
            recModel1.setSendStatus("already");
            recModel1.setSendTime(new Date());
            //发送
            sendSms(msgTemplate.getTemplateName(), body.getMobile() ,content);
        }
        //如果为模版时间段。或者自定义时间段，判断当前时间是否在这个时间段，如果是，直接send出去，录入记录
        // 如果不是，。只录入记录等到定时任务处理
        if("temptime".equals(sendTimeType) || "customize".equals(sendTimeType)){
            String liveTime = "00:00:00";
            String deadTime = "23:59:59";
            //模版时间段
            if("temptime".equals(sendTimeType)){
                liveTime = DateUtils.DateToStr("HH:mm:ss",msgTemplate.getLiveTime());
                deadTime = DateUtils.DateToStr("HH:mm:ss",msgTemplate.getDeadTime());
            }
            //自定义时间段
            if("customize".equals(sendTimeType)){
                liveTime = body.getLiveTime();
                deadTime = body.getDeadTime();
            }
            //判断当前时间是否在这个时间段之中
            Date nowDate = new Date();
            //年月日为
            String nyr = DateUtils.DateToStr(DateUtils.YYYY_MM_DD,nowDate);
            //当天的 时间段的开始和结束时间
            Date startTimeTody = DateUtils.strToDate(nyr + " " + liveTime,DateUtils.YYYY_MM_DD_HH_MM_SS);
            Date endTimeTody = DateUtils.strToDate(nyr + " " + deadTime,DateUtils.YYYY_MM_DD_HH_MM_SS);
            //如果在时间段内----及时发送
            if(nowDate.getTime() >= startTimeTody.getTime() && nowDate.getTime() <= endTimeTody.getTime()){
                recModel1.setSendStatus("already");
                recModel1.setLiveTime(DateUtils.strToDate(liveTime, "HH:mm:ss"));
                recModel1.setDeadTime(DateUtils.strToDate(deadTime, "HH:mm:ss"));
                recModel1.setSendTime(new Date());
                //发送
                sendSms(msgTemplate.getTemplateName(), body.getMobile() ,content);
            }else{//否则没在时间段，录入记录，等待定时任务处理
                recModel1.setSendStatus("wait");
                recModel1.setLiveTime(DateUtils.strToDate(liveTime, "HH:mm:ss"));
                recModel1.setDeadTime(DateUtils.strToDate(deadTime, "HH:mm:ss"));
            }
        }
        thisMapper.insert(recModel1);
    }
    /**
     * 定时任务执行业务处理
     *
     * @throws Exception
     */
    @Override
    public void sendSmsTask() throws Exception {
        long count = 0;
        //当前时间的 时分秒
        Date nowDate = new Date();
        //时分秒为
        String sfmString = DateUtils.DateToStr("HH:mm:ss",nowDate);
        Date sfm = DateUtils.strToDate(sfmString,"HH:mm:ss");

        //查询发送短信还未处理的，且当前时间在这个时间段的list，为了防止查询的数据太多，这里查询前100条数据处理
        MsgSmsRecExample msgSmsRecExample = new MsgSmsRecExample();
        msgSmsRecExample.setLimit(100);
        msgSmsRecExample.setOrderByClause("live_time ASC");
        msgSmsRecExample.or().andSendStatusEqualTo("wait")
                .andLiveTimeLessThanOrEqualTo(sfm).andDeadTimeGreaterThanOrEqualTo(sfm);
        List<MsgSmsRec> waitList = thisMapper.selectByExample(msgSmsRecExample);
        for (MsgSmsRec msgSmsRec: waitList ) {
            msgSmsRec.setSendStatus("already");
            msgSmsRec.setSendTime(new Date());
            thisMapper.updateByPrimaryKeySelective(msgSmsRec);
            sendSms(msgSmsRec.getTitle(),msgSmsRec.getMobile(),msgSmsRec.getContent());
            count++;
        }
        logger.info("--------------------waitList的size="+waitList.size()+",共处理待发送短信 " + count + " 条");
    }
    /**
     * 发短信发短信(直接发--及时)
     * @param type
     * @param mobile
     * @param content
     * @throws Exception
     */
    @Override
    public void sendSms(String type, String mobile, String content) throws Exception {
        //Message res = smsFeignService.sendMessage(type, mobile ,content);
        Message res =  smsFeignService.sendMessage(mobile,type,content,"127.0.0.1",1);
        if(!res.getCode().equals(200)){
            throw new BusinessDealException(res.getMessage());
        }
    }
}
