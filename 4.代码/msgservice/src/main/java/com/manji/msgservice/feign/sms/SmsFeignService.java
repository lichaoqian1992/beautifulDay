package com.manji.msgservice.feign.sms;

import com.manji.msgservice.feign.response.Message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="datahost")
public interface SmsFeignService {

    /**
     * 真实发送短信业务
     * @param type
     * @param mobile
     * @param content
     * @return
     */
    @GetMapping("sendMessage")
    Message sendMessage(@RequestParam("type") String type, @RequestParam("mobile") String mobile, @RequestParam("content") String content);

    /**
     * 真实发送短信业务
     * @param content
     * @return
     */
    @GetMapping("TimingTaskSend")
    Message sendMessage(@RequestParam("mobiles") String mobiles, @RequestParam("title") String title, @RequestParam("content") String content,@RequestParam("ip")String ip,@RequestParam("pass")Integer pass);
}
