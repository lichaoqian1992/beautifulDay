package com.manji.orService.feignInterface;


import com.alibaba.fastjson.JSONObject;
import com.manji.orService.config.FeignMultipartSupportConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value="cusystem",configuration = FeignMultipartSupportConfig.class)
public interface PicInterface {

    @RequestMapping(value = "/mail/uploadPic",method= RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    JSONObject uploadPic(@RequestPart(value = "file") MultipartFile file);

}
