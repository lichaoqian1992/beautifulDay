package com.manji.messageserver.task;

import com.manji.messageserver.common.utils.CollectionsUtils;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.dao.entity.MessageDetailDO;
import com.manji.messageserver.dao.entity.extra.MessageInfoDO;
import com.manji.messageserver.dao.repository.MessageDetailDAO;
import com.manji.messageserver.dao.repository.extra.MessageQueryDAO;
import com.manji.messageserver.utils.AbstractScheduleService;
import com.manji.messageserver.utils.DateUtil;
import com.manji.messageserver.utils.HttpJsonTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
/**
 * Created by pudding on 2016-12-19.
 * 下载昨日备份的聊天记录中的文件
 */
@Component
public class DownloadHisoryFileTask extends AbstractScheduleService {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(DownloadHisoryFileTask.class);


    @Autowired
    private MessageQueryDAO messageQueryDAO;

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private MessageDetailDAO messageDetailDAO;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        try {
            logger.info("定时任务:获取历史文件开始 time={}", dateFormat.format(new Date()));
            //获取前天的日期
            String begin = DateUtil.getYesterdayTime("yyyy-MM-dd", 2);
            String end = DateUtil.getYesterdayTime("yyyy-MM-dd", 1);
            // TODO: 2016-12-19 这里要换成begin 和  end 变量
            //得到数据库的文件
            List<MessageInfoDO> list = messageQueryDAO.query(begin, end);
            if(CollectionsUtils.isEmptyCollection(list)){
                logger.info("定时任务:结束 没有查询到备份文件记录 time={}", dateFormat.format(new Date()));
                return;
            }
            for (MessageInfoDO infoDO : list) {
                String name = UUID.randomUUID().toString();
                //修改数据库文件名并保存数据
                modifyFileName(infoDO.getMsgId(),name);
                if (infoDO.getType().equals("img")) {
                    download(infoDO.getUrl(), hxConfig.getImgDirectory(),name);
                } else if (infoDO.getType().equals("audio")) {
                    download(infoDO.getUrl(), hxConfig.getAudioDirectory(),name);
                } else if (infoDO.getType().equals("video")) {
                    download(infoDO.getUrl(), hxConfig.getVideoDirectory(),name);
                } else if (infoDO.getType().equals("file")) {
                    download(infoDO.getUrl(), hxConfig.getFileDirectory(),name);
                } else {
                    logger.info("下载历史 类型异常info={}", infoDO);
                }
            }
        }catch (Exception e){
            logger.info("定时任务:获取历史文件异常 time={},errorMessage={}", dateFormat.format(new Date()),e.getMessage());
            e.printStackTrace();
        }finally {
            logger.info("定时任务:获取历史文件结束 time={}", dateFormat.format(new Date()));
        }

    }
    private void download(final String url,final String path,final String name) {
        runInTaskExecutor(new Runnable() {
            @Override
            public void run() {
                HttpJsonTool.downLoadHistoryFile(url, path, name);
            }
        }, Group.DOWNLOADFILE);
    }
    private void modifyFileName(String msgId,String name){
        MessageDetailDO detailDO = messageDetailDAO.findByMsgId(msgId);
        String filename = detailDO.getFilename();
        String suffixName = filename.substring(filename.indexOf("."));
        String fileName = name.concat(suffixName);
        detailDO.setFilename(fileName);
        messageDetailDAO.save(detailDO);
    }
}
