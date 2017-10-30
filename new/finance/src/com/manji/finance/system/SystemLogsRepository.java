package com.manji.finance.system;

import com.jfinal.plugin.activerecord.Record;
import com.manji.finance.utils.DButils;

/**
 * 系统操作
 * @author Administrator
 *
 */
public class SystemLogsRepository extends DButils{

	/**
	 * 保存系统操作日志
	 * @return
	 */
	public boolean saveSysLogs(Record r){
		
		return mysql.save("t_system_log", "id", r);
		
	}
}
