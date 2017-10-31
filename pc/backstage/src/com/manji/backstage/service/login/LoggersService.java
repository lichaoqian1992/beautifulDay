package com.manji.backstage.service.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.manji.backstage.dto.login.LoggersDto;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.vo.login.LoggersVo;

public interface LoggersService {

	/**
	 * 插入操作记录数据
	 * @param req
	 * @param dto
	 */
	void addLoggers(Loggers log);
	
	Loggers getLoggers(int id);
	
	Page<Loggers> queryLoggers(LoggersVo vo);
	
	Data addData(String json);
	
	Data getData(int id);
}
