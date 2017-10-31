package com.manji.finance.common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.jfinal.plugin.cron4j.ITask;
import com.manji.finance.utils.TimeUtils;
import com.manji.finance.withdrawals.Pay.ReconciliationSercive;

public class newTask implements ITask{

	/**
	 * 主要用于定时获取服务器的对账文件
	 */
	@Override
	public void run() {
		//1.获取的文件是昨天的，
		String name = TimeUtils.getYesterday();//2017-01-01
		String date = name.split("-")[0]+name.split("-")[1]+name.split("-")[2];
		String filePath = "D:/circlemes/download/"+date+".gz";
		String uploadPath = "D:/circlemes/unfold/"+date;
		//1.先判断要获取的文件是否存在
		/*File file = new File(filePath);
		if(file != null){
			
		}*/
		new ReconciliationSercive().addFileInfo();
		System.out.println(date);
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
