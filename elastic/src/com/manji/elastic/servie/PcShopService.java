package com.manji.elastic.servie;

import org.apache.log4j.Logger;

import com.manji.elastic.base.IndexFinal;
import com.manji.elastic.utils.ElasticClient;

public class PcShopService {

	private static Logger logger = Logger.getLogger(PcShopService.class);
	
	private static final String GOODSINDEX =  IndexFinal.ShopIndex;;

	// 公共调起ES服务方法
	public static String loader(StringBuffer sb) {

		logger.info("==============================querybody查询语句：      " + sb.toString());

		long t3 = System.currentTimeMillis();

		String esReturn = ElasticClient.postMethodWithQeuryBody(GOODSINDEX + "/_search", sb.toString());
		long t4 = System.currentTimeMillis();
		logger.info("==============================ES调用耗费时间 :     " + (t4 - t3));

		logger.info("==============================ES返回数据：     " + esReturn);
		return esReturn;

	}

}
