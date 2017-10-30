package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.MontMapper;
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
import com.manji.backstage.service.MontService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.logger.FileFilterLogVo;
import com.manji.backstage.vo.logger.KeyHashsVo;
import com.manji.backstage.vo.logger.KeyWordsVo;
import com.manji.backstage.vo.logger.ReportLogVo;
import com.manji.backstage.vo.logger.WordsFilterLogVo;
import com.manji.backstage.vo.monitor.InfoCorrectingVo;
import com.manji.backstage.vo.shop.CompContentVo;
import com.manji.backstage.vo.shop.CompHandleVo;
import com.manji.backstage.vo.shop.ComplaintVo;

@Service
public class MontServiceImpl implements MontService {

	@Autowired
	private MontMapper mapper;
	
	
	

//	dt_business_complaint	投诉记录信息表
	
	@Override
	public Page<Complaint> queryComplaint(ComplaintVo vo) {
		Page<Complaint> page =new Page<Complaint>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =(int)mapper.countComplaint(vo);
		List<Complaint> list =mapper.queryComplaint(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public Complaint getComplaint(long id) {
		
		return mapper.getComplaint(id);
	}

	@Override
	public boolean updComplaint(Complaint c) {
		if(mapper.updComplaint(c)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addComplaint(Complaint c) {
		mapper.addComplaint(c);
	}

	@Override
	public boolean delComplaint(long id) {
		if(mapper.delComplaint(id)>0){
			return true;
		}
		return false;
	}

//	dt_business_complaint_content	投诉处理详细内容表
	
	@Override
	public Page<CompContent> queryCompContent(CompContentVo vo) {
		Page<CompContent> page =new Page<CompContent>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countCompContent(vo);
		List<CompContent> list =mapper.queryCompContent(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public CompContent getCompContent(long id) {
		return mapper.getCompContent(id);
	}

	@Override
	public boolean updCompContent(CompContent cc) {
		if(mapper.updCompContent(cc)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addCompContent(CompContent cc) {
		mapper.addCompContent(cc);
	}

	@Override
	public boolean delCompContent(long id) {
		if(mapper.delCompContent(id)>0){
			return true;
		}
		return false;
	}

//	dt_business_complaint_handle	投诉处理记录信息表
	
	@Override
	public Page<CompHandle> queryCompHandle(CompHandleVo vo) {
		Page<CompHandle> page =new Page<CompHandle>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			page.setIndex(1);
			vo.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		int count =mapper.countCompHandle(vo);
		List<CompHandle> list =mapper.queryCompHandle(vo);
		page.transform(count, list);
		
		return page;
	}

	@Override
	public CompHandle getCompHandle(long id) {
		
		return mapper.getCompHandle(id);
	}

	@Override
	public boolean updCompHandle(CompHandle ch) {
		if(mapper.updCompHandle(ch)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addCompHandle(CompHandle ch) {
		mapper.addCompHandle(ch);
	}

	@Override
	public boolean delCompHandle(long id) {
		if(mapper.delCompHandle(id)>0){
			return true;
		}
		return false;
	}
	
//	dt_info_correcting	信息纠错记录

	@Override               
	public Page<InfoCorrecting> queryInfoCorrecting(InfoCorrectingVo vo) {
		Page<InfoCorrecting> page =new Page<InfoCorrecting>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<InfoCorrecting> dataList =mapper.queryInfoCorrecting(vo);
		int totalCount =mapper.countInfoCorrecting(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addInfoCorrecting(InfoCorrecting ic) {
		mapper.addInfoCorrecting(ic);
		
	}
	
	@Override
	public InfoCorrecting getInfoCorrecting(int id) {
		
		return mapper.getInfoCorrecting(id);
	}
	
	@Override
	public boolean updInfoCorrecting(InfoCorrecting ic) {
		if(mapper.updInfoCorrecting(ic)>0){
			return true;	
		}
		return false;
	}
	
	@Override
	public boolean delInfoCorrecting(int id) {
		if(mapper.delInfoCorrecting(id)>0){
			return true;	
		}
		return false;
	}
	
	//110

	@Override
	public KeyWords getKeyWordsList(int id) {
		return mapper.getKeyWordsList(id);
	}

	@Override
	public Page<KeyWords> queryKeyWords(KeyWordsVo vo) {
		Page<KeyWords> page =new Page<KeyWords>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<KeyWords> dataList =mapper.queryKeyWords(vo);
		int totalCount =mapper.countKeyWords(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public void addKeyWords(KeyWords keywords) {
		
		String time=TimeUtils.getCurrentTime();
		keywords.setAdd_time(time);
		mapper.addKeyWords(keywords);
		
	}

	@Override
	public boolean updKeyWords(KeyWords keywords) {
		
		if(mapper.updKeyWords(keywords)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delKeyWords(int id) {
		
		if(mapper.delKeyWords(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	
	@Override
	public KeyHashs getKeyHashsList(int id) {
		return mapper.getKeyHashsList(id);
	}
	
	@Override
	public Page<KeyHashs> queryKeyHashs(KeyHashsVo vo) {
		Page<KeyHashs> page =new Page<KeyHashs>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<KeyHashs> dataList =mapper.queryKeyHashs(vo);
		int totalCount =mapper.countKeyHashs(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addKeyHashs(KeyHashs keyhashs) {
		
		String time=TimeUtils.getCurrentTime();
		keyhashs.setAdd_time(time);
		mapper.addKeyHashs(keyhashs);
		
	}
	
	@Override
	public boolean updKeyHashs(KeyHashs keyhashs) {
		
		if(mapper.updKeyHashs(keyhashs)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delKeyHashs(int id) {
		
		if(mapper.delKeyHashs(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}

	
	@Override
	public WordsFilterLog getWordsFilterLogList(int id) {
		return mapper.getWordsFilterLogList(id);
	}
	
	@Override
	public Page<WordsFilterLog> queryWordsFilterLog(WordsFilterLogVo vo) {
		Page<WordsFilterLog> page =new Page<WordsFilterLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<WordsFilterLog> dataList =mapper.queryWordsFilterLog(vo);
		int totalCount =mapper.countWordsFilterLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addWordsFilterLog(WordsFilterLog wfl) {
		
		String time=TimeUtils.getCurrentTime();
		wfl.setAdd_time(time);
		mapper.addWordsFilterLog(wfl);
		
	}
	
	@Override
	public boolean updWordsFilterLog(WordsFilterLog wfl) {
		
		if(mapper.updWordsFilterLog(wfl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delWordsFilterLog(int id) {
		
		if(mapper.delWordsFilterLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	@Override
	public FileFilterLog getFileFilterLogList(int id) {
		return mapper.getFileFilterLogList(id);
	}
	
	@Override
	public Page<FileFilterLog> queryFileFilterLog(FileFilterLogVo vo) {
		Page<FileFilterLog> page =new Page<FileFilterLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<FileFilterLog> dataList =mapper.queryFileFilterLog(vo);
		int totalCount =mapper.countFileFilterLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addFileFilterLog(FileFilterLog ffl) {
		
		String time=TimeUtils.getCurrentTime();
		ffl.setAdd_time(time);
		mapper.addFileFilterLog(ffl);
		
	}
	
	@Override
	public boolean updFileFilterLog(FileFilterLog ffl) {
		
		if(mapper.updFileFilterLog(ffl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delFileFilterLog(int id) {
		
		if(mapper.delFileFilterLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	

	@Override
	public ReportLog getReportLogList(int id) {
		return mapper.getReportLogList(id);
	}
	
	@Override
	public Page<ReportLog> queryReportLog(ReportLogVo vo) {
		Page<ReportLog> page =new Page<ReportLog>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ReportLog> dataList =mapper.queryReportLog(vo);
		int totalCount =mapper.countReportLog(vo);
		
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public void addReportLog(ReportLog rl) {
		
		String time=TimeUtils.getCurrentTime();
		rl.setAdd_time(time);
		mapper.addReportLog(rl);
		
	}
	
	@Override
	public boolean updReportLog(ReportLog rl) {
		
		if(mapper.updReportLog(rl)>0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delReportLog(int id) {
		
		if(mapper.delReportLog(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}
	
	
	
}
