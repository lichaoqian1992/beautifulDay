package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

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
@Resource
public interface MontMapper {

	

//	dt_business_complaint	投诉记录信息表
	
	
	long countComplaint(ComplaintVo vo);

	List<Complaint> queryComplaint(ComplaintVo vo);

	Complaint getComplaint(long id);

	int updComplaint(Complaint c);

	void addComplaint(Complaint c);

	long delComplaint(long id);

//	dt_business_complaint_content	投诉处理详细内容表
	
	int countCompContent(CompContentVo vo);

	List<CompContent> queryCompContent(CompContentVo vo);

	CompContent getCompContent(long id);

	int updCompContent(CompContent cc);

	void addCompContent(CompContent cc);

	int delCompContent(long id);

//	dt_business_complaint_handle	投诉处理记录信息表
	
	int countCompHandle(CompHandleVo vo);

	List<CompHandle> queryCompHandle(CompHandleVo vo);

	CompHandle getCompHandle(long id);

	int updCompHandle(CompHandle ch);

	void addCompHandle(CompHandle ch);

	int delCompHandle(long id);
	
//	dt_info_correcting	信息纠错记录

	int countInfoCorrecting(InfoCorrectingVo vo);
	
	List<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo);
	
	void addInfoCorrecting(InfoCorrecting ic);
	
	InfoCorrecting getInfoCorrecting(int id);
	
	int updInfoCorrecting(InfoCorrecting ic);
	
	int delInfoCorrecting(int id);	
	
	
	//110
	KeyWords getKeyWordsList(int id);

	List<KeyWords> queryKeyWords(KeyWords keywords);
	
	int countKeyWords(KeyWordsVo vo);

	void addKeyWords(KeyWords keywords);

	int updKeyWords(KeyWords keywords);

	int delKeyWords(int id);
	

	KeyHashs getKeyHashsList(int id);

	List<KeyHashs> queryKeyHashs(KeyHashs keyhashs);
	
	int countKeyHashs(KeyHashsVo vo);

	void addKeyHashs(KeyHashs keyhashs);

	int updKeyHashs(KeyHashs keyhashs);

	int delKeyHashs(int  id);
	
	
	WordsFilterLog getWordsFilterLogList(int id);

	List<WordsFilterLog> queryWordsFilterLog(WordsFilterLog WordsFilterLog);
	
	int countWordsFilterLog(WordsFilterLogVo vo);

	void addWordsFilterLog(WordsFilterLog WordsFilterLog);

	int updWordsFilterLog(WordsFilterLog WordsFilterLog);

	int delWordsFilterLog(int id);
	
	
	FileFilterLog getFileFilterLogList(int id);

	List<FileFilterLog> queryFileFilterLog(FileFilterLog FileFilterLog);
	
	int countFileFilterLog(FileFilterLogVo vo);

	void addFileFilterLog(FileFilterLog FileFilterLog);

	int updFileFilterLog(FileFilterLog FileFilterLog);

	int delFileFilterLog(int id);
	

	ReportLog getReportLogList(int id);

	List<ReportLog> queryReportLog(ReportLog ReportLog);
	
	int countReportLog(ReportLogVo vo);

	void addReportLog(ReportLog ReportLog);

	int updReportLog(ReportLog ReportLog);

	int delReportLog(int id);
	
	
	
	
	
	
	
}
