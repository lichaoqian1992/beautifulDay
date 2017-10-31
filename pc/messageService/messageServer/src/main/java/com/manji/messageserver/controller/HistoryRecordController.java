package com.manji.messageserver.controller;

import com.manji.messageserver.config.HxConfig;
import com.manji.messageserver.dao.entity.MessageDetailDO;
import com.manji.messageserver.requestParam.QueryHistoryRecordRequestParam;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.service.HistoryRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by pudding on 2016-12-20.
 */
@RequestMapping("/history")
@Controller
public class HistoryRecordController {

    private static final Logger logger = LoggerFactory.getLogger(HistoryRecordController.class);

    @Autowired
    private HxConfig hxConfig;

    @Autowired
    private HistoryRecordService historyRecordService;

    @RequestMapping(value = "/downloadHistoryFile/{msgid}")
    public ResponseEntity<InputStreamResource> downloadHistoryFile(@PathVariable String msgid) throws IOException {
        MessageDetailDO messageDetail1 = historyRecordService.getMessageDetail(msgid);
        MessageDetailDO messageDetail = messageDetail1;
        if (messageDetail.getType().equals("img")) {
            String filePath = hxConfig.getImgDirectory() + "\\" + messageDetail.getFilename();
            return getResponseEntity(filePath);
        } else if (messageDetail.getType().equals("audio")) {
            String filePath = hxConfig.getAudioDirectory() + "\\" + messageDetail.getFilename();
            return getResponseEntity(filePath);
        } else if (messageDetail.getType().equals("video")) {
            String filePath = hxConfig.getVideoDirectory() + "\\" + messageDetail.getFilename();
            return getResponseEntity(filePath);
        } else if (messageDetail.getType().equals("file")) {
            String filePath = hxConfig.getFileDirectory() + "\\" + messageDetail.getFilename();
            return getResponseEntity(filePath);
        } else {
            logger.error("文件类型异常:messageDetail={}", messageDetail);
            return null;
        }
    }

    private ResponseEntity<InputStreamResource> getResponseEntity(String filePath) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        if(fileSystemResource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileSystemResource.getFilename()));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(fileSystemResource.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(fileSystemResource.getInputStream()));
        }
        logger.warn("文件下载失败:有可能文件不存在 filePath={}",filePath);
        return null;
    }


    @RequestMapping("/queryHistoryRecord")
    @ResponseBody
    public BaseResult queryHistoryRecord(QueryHistoryRecordRequestParam requestParam){
        return historyRecordService.queryHistoryRecord(requestParam);
    }

}
