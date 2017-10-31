package com.manji.advert.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.jfinal.plugin.cron4j.ITask;
import com.manji.advert.service.AppService;

public class SticsTask implements ITask{

	@Override
	public void run() {
		AppService app=new AppService();
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        app.sticsCron(df.format(calendar.getTime()), df.format(new Date()));
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
