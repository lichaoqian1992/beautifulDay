package com.manji.backstage.service;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.logger.FileFilterLog;
import com.manji.backstage.model.logger.KeyHashs;
import com.manji.backstage.model.logger.KeyWords;
import com.manji.backstage.model.logger.ReportLog;
import com.manji.backstage.model.logger.WordsFilterLog;
import com.manji.backstage.model.monitor.InfoCorrecting;
import com.manji.backstage.model.shop.CompContent;
import com.manji.backstage.model.shop.CompHandle;
import com.manji.backstage.model.shop.Complaint;
import com.manji.backstage.vo.logger.FileFilterLogVo;
import com.manji.backstage.vo.logger.KeyHashsVo;
import com.manji.backstage.vo.logger.KeyWordsVo;
import com.manji.backstage.vo.logger.ReportLogVo;
import com.manji.backstage.vo.logger.WordsFilterLogVo;
import com.manji.backstage.vo.monitor.InfoCorrectingVo;
import com.manji.backstage.vo.shop.CompContentVo;
import com.manji.backstage.vo.shop.CompHandleVo;
import com.manji.backstage.vo.shop.ComplaintVo;

public interface MontService {

	


//	dt_business_complaint	投诉记录信息表

	
	Page<Complaint> queryComplaint(ComplaintVo vo);

	Complaint getComplaint(long id);

	boolean updComplaint(Complaint c);

	void addComplaint(Complaint c);

	boolean delComplaint(long id);

//	dt_business_complaint_content	投诉处理详细内容表

	
	Page<CompContent> queryCompContent(CompContentVo vo);

	CompContent getCompContent(long id);

	boolean updCompContent(CompContent cc);

	void addCompContent(CompContent cc);

	boolean delCompContent(long id);
	
//	dt_business_complaint_handle	投诉处理记录信息表


	Page<CompHandle> queryCompHandle(CompHandleVo vo);

	CompHandle getCompHandle(long id);

	boolean updCompHandle(CompHandle ch);

	void addCompHandle(CompHandle ch);

	boolean delCompHandle(long id);
	
	
//	dt_info_correcting	信息纠错记录

	Page<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo);
	
	void addInfoCorrecting(InfoCorrecting ic);
	
	InfoCorrecting getInfoCorrecting(int id);
	
	boolean updInfoCorrecting(InfoCorrecting ic);
	
	boolean delInfoCorrecting(int id);
	
	
	//110
	

	KeyWords getKeyWordsList(int id);

	Page<KeyWords> queryKeyWords(KeyWordsVo vo);
	
	void addKeyWords(KeyWords keywords);

	boolean updKeyWords(KeyWords keywords);

	boolean delKeyWords(int id);
	

	KeyHashs getKeyHashsList(int id);

	Page<KeyHashs> queryKeyHashs(KeyHashsVo vo);
	
	void addKeyHashs(KeyHashs keyhashs);

	boolean updKeyHashs(KeyHashs keyhashs);

	boolean delKeyHashs(int id);
	
	
	WordsFilterLog getWordsFilterLogList(int id);
	
	Page<WordsFilterLog> queryWordsFilterLog(WordsFilterLogVo vo);
	
	void addWordsFilterLog(WordsFilterLog wfl);
	
	boolean updWordsFilterLog(WordsFilterLog wfl);
	
	boolean delWordsFilterLog(int id);
	
	
	FileFilterLog getFileFilterLogList(int id);
	
	Page<FileFilterLog> queryFileFilterLog(FileFilterLogVo vo);
	
	void addFileFilterLog(FileFilterLog wfl);
	
	boolean updFileFilterLog(FileFilterLog wfl);
	
	boolean delFileFilterLog(int id);
	

	ReportLog getReportLogList(int id);
	
	Page<ReportLog> queryReportLog(ReportLogVo vo);
	
	void addReportLog(ReportLog rl);
	
	boolean updReportLog(ReportLog rl);
	
	boolean delReportLog(int id);


	
}
