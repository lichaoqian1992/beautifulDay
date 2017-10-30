package com.manji.finance.system;

import com.manji.finance.utils.DButils;

public class httplogRepository extends DButils{

	/**
	 * 保存充值和提现调用接口返回的参数
	 * @param logs
	 * @return
	 */
	public static int addHttplog(HttplogDO logs){

		return mysql.update("INSERT INTO t_Http_log values (NULL,?,?,?,?,?)",logs.getAction(),logs.getQuerySN(),logs.getTranSN(),logs.getResultData(),logs.getCreatedate());

	}
	
}
