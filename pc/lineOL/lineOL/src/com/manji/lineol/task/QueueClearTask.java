package com.manji.lineol.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.manji.lineol.mapper.ShopsMapper;
import com.manji.lineol.model.Shops;

import redis.clients.jedis.Jedis;

@Component("queueClearTask")
public class QueueClearTask {
	private static Logger logger=Logger.getLogger(QueueClearTask.class);

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Autowired
	private ShopsMapper shopsMapper;

	@Scheduled(cron = "0 0 0 * * ?")
	public void task() {
		logger.info("定时任务开始 time="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		try {
			List<Shops> queryShopsByIdAndonoff = shopsMapper.queryShopsByIdAndonoff();
			for (final Shops shops : queryShopsByIdAndonoff) {
				shopsMapper.modfiyShops(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("onOrOff", "1");
						put("shopId", shops.getShopId());

					}
				});
			}

			JedisConnection jedisConnection = jedisConnectionFactory.getConnection();
			Jedis Jedis = jedisConnection.getNativeConnection();
			// 清空所有redis数据
			Jedis.flushDB();
			
		}catch(Exception e){
			logger.error("定时任务异常 info="+e.getMessage());
		}finally {
			logger.info("定时任务结束 time="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
	
	}

}
