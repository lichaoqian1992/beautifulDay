package com.manji.backstage.service;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.vo.login.LoggersVo;

public interface LoggersService {
	
	void addLoggers(Loggers log);

	Loggers getLoggers(int id);

	Page<Loggers> queryLoggers(LoggersVo vo);

	Data addData(String json);

	Data getData(int id);
}
