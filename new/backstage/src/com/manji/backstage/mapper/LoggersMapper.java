package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.logger.UserNoticeRead;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.vo.logger.UserNoticeReadVo;
import com.manji.backstage.vo.login.LoggersVo;
@Resource
public interface LoggersMapper {

	void addLoggers(Loggers logg);

	Loggers getLoggers(int id);

	List<Loggers> queryLoggers(LoggersVo vo);

	int countLoggers(LoggersVo vo);

	void addData(Data d);

	Data getData(int id);
	

	
	

}
