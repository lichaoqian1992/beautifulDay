package com.manji.adds.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.manji.adds.mapper.InfMapper;
import com.manji.adds.service.InfService;
import com.manji.adds.utils.JsonUtils;

import org.apache.log4j.Logger;



@Component("CountsTask")
public class CountsTask {

	private static final Logger logger = Logger.getLogger(CountsTask.class);
	
	@Autowired
	private InfMapper mapper;
	
//	@Scheduled(fixedRate = 1000 * 10)
	@Scheduled(cron="0 0 23 * * *")
	public void countsTask(){
		
		logger.info("定时器任务启动。。。。。。。");
		
		logger.info(System.currentTimeMillis()+JsonUtils.getArray(mapper.queryCounts()));;
		
		List list =mapper.queryCounts();
		
		if(null !=list){
			
			for(int i=0;i<list.size();i++){
				
				
				Map countInfo =(Map) list.get(i);
				
				String ad_id =(String) countInfo.get("ad_id");
				
				int count =(int) countInfo.get("count");
				
				logger.info(ad_id+"     "+count);
				
				mapper.addCounts(ad_id, count);
				
				logger.info("执行成功。。。");
				
				
				if(mapper.deleteJnl())logger.info("清楚记录数据成功。。。");
				
			}
		}else{
			logger.info("无点击记录。。。");
		}
	}
	
}
