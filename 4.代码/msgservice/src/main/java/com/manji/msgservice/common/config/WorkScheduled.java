package com.manji.msgservice.common.config;

import com.manji.msgservice.service.MsgSmsRecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class WorkScheduled {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource MsgSmsRecService msgSmsRecService;

	/**
	 * 处理未发送短信
	 * 执行完成后 隔4分钟执行下一次
	 */
	@Scheduled(fixedDelay = 4*60*1000)
	public void executeSmsTask() {
		new Thread(() -> {
			try {
				msgSmsRecService.sendSmsTask();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * 处理未发送站内信
	 * 执行完成后 隔4分钟20秒 执行下一次
	 */
	@Scheduled(fixedDelay = 4*60*1000 + 20000)
	public void executeInnerTask() {

	}
	/**
	 * 处理未发送极光推送
	 *执行完成后 隔4分钟28秒 执行下一次
	 */
	@Scheduled(fixedDelay = 4*60*1000 + 28000)
	public void executePushTask() {

	}

	/**
	 * 处理未发送邮件信息
	 *执行完成后 隔5分钟12秒 执行下一次
	 */
	@Scheduled(fixedDelay = 5*60*1000 + 12000)
	public void executeMailTask() {

	}

}
