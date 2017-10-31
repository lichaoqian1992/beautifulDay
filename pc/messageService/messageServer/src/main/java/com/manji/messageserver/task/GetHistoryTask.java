package com.manji.messageserver.task;

import com.google.gson.Gson;
import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.service.HistoryFileService;
import com.manji.messageserver.utils.*;
import com.manji.messageserver.vo.HistoryFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by pudding on 2016-12-15.
 * 获取历史消息记录的Task
 */
@Component
public class GetHistoryTask extends AbstractScheduleService {
    @Autowired
    private HxConfig hxConfig;
    @Autowired
    private HistoryFileService historyFileService;

    @Autowired
    private FileUtil fileUtil;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(GetHistoryTask.class);

    /**
     * 获取前一条的所有聊天记录
     */
    @Scheduled(cron = "0 10 0 * * ?")
    public void task() throws InterruptedException {
        logger.info("定时任务:获取历史消息开始 time={}", dateFormat.format(new Date()));
        for (final String timeStr : getTimestamps()) {
            Thread.sleep(6000);
            runInTaskExecutor(new Runnable() {
                @Override
                public void run() {
                    List<HistoryFileVO> data = historyFileService.getHistoryFile(timeStr);
                    if (data != null) {
                        for (final HistoryFileVO vo : data) {
                            runInTaskExecutor(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        //1.下载文件
                                        HttpJsonTool.downLoadFile(vo.getUrl(), hxConfig.getGetZipFileUrl());
                                        String fileName = vo.getUrl().split("/")[7].split("\\?")[0];//带后缀的文件名
                                        String urlName = vo.getUrl().split("/")[7].split("\\.")[0];//不带后缀的文件名
                                        //2.解压文件
                                        //2.1解压文件之前，先判断解压后的文件储存位置是否存在
                                        File file = new File(hxConfig.getGetUnZipFileUrl());
                                        if(!file.exists()){
                                            file.mkdirs();
                                        }
                                        fileUtil.decompress(hxConfig.getGetZipFileUrl() + "/" + fileName, hxConfig.getGetUnZipFileUrl() + "/" + urlName);
                                        fileUtil.readTxtFile(hxConfig.getGetUnZipFileUrl() + "/" + urlName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, Group.DOWNLOAD);
                        }
                    }
                }
            });
        }
        logger.info("定时任务:获取历史消息结束 time={}", dateFormat.format(new Date()));
    }

    private static List<String> getTimestamps() {
        String yesterdayTime = DateUtil.getYesterdayTime("yyyyMMdd", 1);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            if (i <= 9) {
                list.add(yesterdayTime + 0 + i);
            } else {
                list.add(yesterdayTime + i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
    }
}
