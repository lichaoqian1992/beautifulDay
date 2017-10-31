package com.manji.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.ArticleCategory;
import com.manji.backstage.model.logger.FileFilterLog;
import com.manji.backstage.model.logger.KeyHashs;
import com.manji.backstage.model.logger.KeyWords;
import com.manji.backstage.model.logger.ReportLog;
import com.manji.backstage.model.logger.WordsFilterLog;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.monitor.InfoCorrecting;
import com.manji.backstage.model.shop.CompContent;
import com.manji.backstage.model.shop.CompHandle;
import com.manji.backstage.model.shop.Complaint;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.MontService;
import com.manji.backstage.utils.Base64Utils;
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

@Controller
public class MontController extends BaseController{
	
	
	@Autowired
	private MontService service;
	@Autowired
	private LoggersService logService;
	@Autowired
	private ArtiService artiService;
	
	
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("user");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
//	dt_business_complaint	投诉记录信息表

	
	@RequestMapping("/selComplaint")
	public String selComplaint(HttpServletRequest req){
		
		return "shop/complaint_list";
		
	}
	@RequestMapping(value ="/queryComplaint", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryComplaint(HttpServletRequest req,ComplaintVo vo){
		Message msg =createMsg();
		
		Page<Complaint> page =service.queryComplaint(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readComplaint")
	public String readComplaint(HttpServletRequest req,long id){
		Complaint c =service.getComplaint(id);
		req.setAttribute("complaint", Json(c));
		return "shop/complaint_upd";
		
	}

	@RequestMapping("/updComplaint")
	public String updComplaint(HttpServletRequest req,Complaint complaint,@RequestParam MultipartFile[] file){
		String id = req.getParameter("id");
		Message msg = createMsg();
		int a = file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(a+1)){
					url += artiService.sendPostReq(fileName, base64Str);
				}else{
					url += artiService.sendPostReq(fileName, base64Str)+",";
				}
			}
			complaint.setPics(url);
		}else{
			complaint.setPics(req.getParameter("icon"));
		}
		
		if(service.updComplaint(complaint)){
			msg.setStatus("0");
			msg.setResult("修改成功");
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
		}
		return "redirect:/readComplaint?id="+id;
	}
	
	@RequestMapping("/insComplaint")
	public String insComplaint(HttpServletRequest req){
		
		return "shop/complaint_add";
	}
	
	@RequestMapping("/addComplaint")
	public String addComplaint(HttpServletRequest req,Complaint c,@RequestParam MultipartFile[] file){
		Message msg = createMsg();
		int a = file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(i+1)){
					url += artiService.sendPostReq(fileName, base64Str);
				}else{
					url += artiService.sendPostReq(fileName, base64Str)+",";
				}
			}
			c.setPics(url);
		}else{
			c.setPics(req.getParameter("icon"));
		}
		/*if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = artiService.sendPostReq(fileName, base64Str);
			c.setPics(address);
		}else{
			c.setPics(req.getParameter("pics"));
		}*/
		service.addComplaint(c);
		msg.setStatus("0");
		return "redirect:/selComplaint";
	}
	
	@RequestMapping("/delComplaint")
	public String delComplaint(HttpServletRequest req,long id){
		Message msg =createMsg();
		Complaint agt =service.getComplaint(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMPLAINT);
		if(service.delComplaint(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selComplaint";
	}
	
//	dt_business_complaint_content	投诉处理详细内容表

	
	@RequestMapping("/selCompContent")
	public String selCompContent(HttpServletRequest req){
		
		return "shop/complaint_content_list";
	}
	@RequestMapping(value ="/queryCompContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCompContent(HttpServletRequest req,CompContentVo vo){
		Message msg =createMsg();
		
		Page<CompContent> page =service.queryCompContent(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readCompContent")
	public String readCompContent(HttpServletRequest req,int id){
		CompContent cc =service.getCompContent(id);
		req.setAttribute("compcontent", Json(cc));
		return "shop/complaint_content_upd";
		
	}

	@RequestMapping(value ="/updCompContent", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String updCompContent(HttpServletRequest req,CompContent pi,@RequestParam MultipartFile[] file){
		Message msg =createMsg();
		int a=file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(i+1)){
					url += artiService.sendPostReq(fileName, base64Str);
				}else{
					url += artiService.sendPostReq(fileName, base64Str)+",";
				}
			}
			pi.setImg(url);
		}else{
			pi.setImg(req.getParameter("imgUrl"));
		}
		CompContent agt =service.getCompContent(pi.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDCOMCONTENT);
		if(service.updCompContent(pi)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readCompContent?id="+pi.getId();
	}
	
	@RequestMapping("/insCompContent")
	public String insCompContent(HttpServletRequest req){
		return "shop/complaint_content_add";
	}
	/*@RequestMapping("/addCompContent")
	public String addCompContent(HttpServletRequest req,CompContent ac,@RequestParam MultipartFile file){
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = artiService.sendPostReq(fileName, base64Str);
			ac.setImg(address);
		}else{
			ac.setImg(req.getParameter("img"));
		}
		service.addCompContent(ac);
		return "redirect:/insCompContent";
	}
	*/
	@RequestMapping(value ="/addCompContent", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addCompContent(HttpServletRequest req,CompContent pi,@RequestParam MultipartFile[] file){
		Message msg =createMsg();
		int a=file.length;
		String url = "";
		if(!file[0].getOriginalFilename().equals("")){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				if(a==(i+1)){
					url += artiService.sendPostReq(fileName, base64Str);
				}else{
					url += artiService.sendPostReq(fileName, base64Str)+",";
				}
			}
			pi.setImg(url);
		}else{
			pi.setImg(req.getParameter("imgUrl"));
		}
		service.addCompContent(pi);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(pi),LogRemark.ADDCOMCONTENT);
		return "redirect:/selCompContent";
	}
	
	@RequestMapping("/delCompContent")
	public String delCompContent(HttpServletRequest req,int id){
		Message msg =createMsg();
		CompContent agt =service.getCompContent(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMCONTENT);
		if(service.delCompContent(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selCompContent";
	}
	
//	dt_business_complaint_handle	投诉处理记录信息表

	
	
	@RequestMapping("/selCompHandle")
	public String selCompHandle(HttpServletRequest req){
		
		return "shop/complaint_handle_list";
	}
	@RequestMapping(value ="/queryCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryCompHandle(HttpServletRequest req,CompHandleVo vo){
		Message msg =createMsg();
		
		Page<CompHandle> page =service.queryCompHandle(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/readCompHandle")
	public String readCompHandle(HttpServletRequest req,int id){
		CompHandle ch =service.getCompHandle(id);
		req.setAttribute("comphandle", Json(ch));
		return "shop/complaint_handle_upd";
	}
	@RequestMapping(value ="/updCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updCompHandle(HttpServletRequest req,CompHandle ch){
		Message msg =createMsg();
		CompHandle agt =service.getCompHandle(ch.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDCOMHANDLE);
		System.out.println(Json(ch));
		if(service.updCompHandle(ch)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insCompHandle")
	public String insCompHandle(HttpServletRequest req){
		
		return "shop/complaint_handle_add";
	}
	@RequestMapping(value ="/addCompHandle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCompHandle(HttpServletRequest req,CompHandle ch){
		Message msg =createMsg();
		saveLog(createLog(req),LogRemark.ADD,Json(ch),LogRemark.ADDCOMHANDLE);
		service.addCompHandle(ch);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/delCompHandle")
	public String delCompHandle(HttpServletRequest req,int id){
		Message msg =createMsg();
		CompHandle agt =service.getCompHandle(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELCOMHANDLE);
		if(service.delCompHandle(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selCompHandle";
	}
	
//	dt_info_correcting	信息纠错记录

	@RequestMapping("/selInfoCorrecting")
	public String selInfoCorrecting(HttpServletRequest req){
		
		return "monitor/monitor_infocorrecting_list";
	}
	@RequestMapping(value ="/queryInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryInfoCorrecting(HttpServletRequest req,InfoCorrectingVo vo){
		Message msg =createMsg();
		
		Page<InfoCorrecting> page =service.queryInfoCorrecting(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insInfoCorrecting")
	public String insInfoCorrecting(HttpServletRequest req){
		
		return "monitor/monitor_infocorrecting_add";
	}
	@RequestMapping(value ="/addInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addInfoCorrecting(HttpServletRequest req,InfoCorrecting ic){
		Message msg =createMsg();
		service.addInfoCorrecting(ic);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ic),LogRemark.ADDINFOCORRECT);
		return Json(msg);
		
	}
	@RequestMapping("/readInfoCorrecting")
	public String readInfoCorrecting(HttpServletRequest req,int id){
		InfoCorrecting ic =service.getInfoCorrecting(id);
		req.setAttribute("infocorrectinginfo", Json(ic));
		return "monitor/monitor_infocorrecting_upd";
	}
	@RequestMapping(value ="/updInfoCorrecting", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updInfoCorrecting(HttpServletRequest req,InfoCorrecting ic){
		Message msg =createMsg();
		InfoCorrecting i =service.getInfoCorrecting(ic.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(i),LogRemark.UPDINFOCORRECT);
		
		if(service.updInfoCorrecting(ic)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delInfoCorrecting")
	public String delInfoCorrecting(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		InfoCorrecting i =service.getInfoCorrecting(id);
		saveLog(createLog(req),LogRemark.DEL,Json(i),LogRemark.DELINFOCORRECT);
		if(service.delInfoCorrecting(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selInfoCorrecting";
	}
	
	
	
	@RequestMapping("/selKeyWords")
	public String keyWordsList(HttpServletRequest req){
		
		/*List<Keywords> keywordsList =service.getKeywordsList();
		
		req.setAttribute("keywordslist", Json(keywordsList));*/	
		
		return "logger/logger_keyword_list";
	}
	
	
	//110
	
	@RequestMapping(value="/queryKeyWords",method = RequestMethod.GET)
	@ResponseBody
	public String queryKeyWords(HttpServletRequest req,KeyWordsVo vo){
		Message msg =createMsg();
		
		Page<KeyWords> page =service.queryKeyWords(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insKeyWords")
	public String insertKeyWords(HttpServletRequest req){
		return "logger/logger_keyword_add";
	}
	
	@RequestMapping("/addKeyWords")
	@ResponseBody
	public String add(HttpServletRequest req,KeyWords keywords){
		Message msg =createMsg();
		
		keywords.setAdd_time(TimeUtils.getCurrentTime());
		
		service.addKeyWords(keywords);
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(keywords),LogRemark.ADDKEYWORDS);
		
		return Json(msg);
	}
	@RequestMapping("/readKeyWords")
	public String readKeyWords(HttpServletRequest req,int id){
		KeyWords keywords =service.getKeyWordsList(id);
		req.setAttribute("keywordsinfo",Array(keywords));
		return "logger/logger_keyword_upd";
		
	}
	
	@RequestMapping("/updKeyWords")
	@ResponseBody
	public String updKeyWords(HttpServletRequest req,KeyWords keywords){
		Message msg =createMsg();
		KeyWords kw =service.getKeyWordsList(keywords.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(kw),LogRemark.UPDKEYWORDS);
		
		
		if(service.updKeyWords(keywords)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delKeyWords")
	public String delKeywords(HttpServletRequest req,int id){
		Message msg =createMsg();
		KeyWords agt =service.getKeyWordsList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(agt),LogRemark.DELKEYWORDS);
		if(service.delKeyWords(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selKeyWords";
	}
	
	
	@RequestMapping("/selKeyHashs")
	public String KeyHashsList(HttpServletRequest req){
		
			
		return "logger/logger_keyhashs_list";
	}
	
	@RequestMapping(value="/queryKeyHashs",method = RequestMethod.GET)
	@ResponseBody
	public String queryKeyHashs(HttpServletRequest req,KeyHashsVo vo){
		Message msg =createMsg();
		
		Page<KeyHashs> page =service.queryKeyHashs(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insKeyHashs")
	public String insertKeyHashs(HttpServletRequest req){
		return "logger/logger_keyhashs_add";
	}
	
	@RequestMapping("/addKeyHashs")
	@ResponseBody
	public String add(HttpServletRequest req,KeyHashs keyhashs){
		Message msg =createMsg();
		keyhashs.setAdd_time(TimeUtils.getCurrentTime());
		service.addKeyHashs(keyhashs);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(keyhashs),LogRemark.ADDKEYHASHS);
		return Json(msg);
	}
	@RequestMapping("/readKeyHashs")
	public String readKeyHashs(HttpServletRequest req,int id){
		KeyHashs keyhashs =service.getKeyHashsList(id);
		req.setAttribute("keyhashsinfo",Array(keyhashs));
		return "logger/logger_keyhashs_upd";
		
	}
	
	@RequestMapping("/updKeyHashs")
	@ResponseBody
	public String updKeyHashs(HttpServletRequest req,KeyHashs keyhashs){
		Message msg =createMsg();
		KeyHashs agt =service.getKeyHashsList(keyhashs.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDKEYHASHS);
		if(service.updKeyHashs(keyhashs)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delKeyHashs")
	public String delKeyHashs(HttpServletRequest req,int id){
		Message msg =createMsg();
		KeyHashs keyhashs =service.getKeyHashsList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(keyhashs),LogRemark.DELKEYHASHS);
		if(service.delKeyHashs(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selKeyHashs";
	}
	
	@RequestMapping("/selWordsFilterLog")
	public String WordsFilterLogList(HttpServletRequest req){
		
				
		return "logger/logger_wordsfilterlog_list";
	}
	
	@RequestMapping(value="/queryWordsFilterLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryWordsFilterLog(HttpServletRequest req,WordsFilterLogVo vo){
		Message msg =createMsg();
		
		Page<WordsFilterLog> page =service.queryWordsFilterLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insWordsFilterLog")
	public String insertWordsFilterLog(HttpServletRequest req){
		return "logger/logger_wordsfilterlog_add";
	}
	
	@RequestMapping("/addWordsFilterLog")
	@ResponseBody
	public String add(HttpServletRequest req,WordsFilterLog wfl){
		Message msg =createMsg();
		
		service.addWordsFilterLog(wfl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		
		saveLog(createLog(req),LogRemark.ADD,Json(wfl),LogRemark.ADDWORDSFILETERLOG);
		return Json(msg);
	}
	@RequestMapping("/readWordsFilterLog")
	public String readWordsFilterLog(HttpServletRequest req,int id){
		WordsFilterLog wordsfilterlog =service.getWordsFilterLogList(id);
		req.setAttribute("wordsfilterloginfo",Array(wordsfilterlog));
		return "logger/logger_wordsfilterlog_upd";
		
	}
	
	@RequestMapping("/updWordsFilterLog")
	@ResponseBody
	public String updWordsFilterLog(HttpServletRequest req,WordsFilterLog wfl){
		Message msg =createMsg();
		WordsFilterLog agt =service.getWordsFilterLogList(wfl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(agt),LogRemark.UPDWORDSFILETERLOG);
		if(service.updWordsFilterLog(wfl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delWordsFilterLog")
	public String delWordsFilterLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		WordsFilterLog wfl =service.getWordsFilterLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(wfl),LogRemark.DELWORDSFILETERLOG);
		if(service.delWordsFilterLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selWordsFilterLog";
	}
	
	
	@RequestMapping("/selFileFilterLog")
	public String FileFilterLogList(HttpServletRequest req){
		
		return "logger/logger_filefilterlog_list";
	}
	
	@RequestMapping(value="/queryFileFilterLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryFileFilterLog(HttpServletRequest req,FileFilterLogVo vo){
		Message msg =createMsg();
		
		Page<FileFilterLog> page =service.queryFileFilterLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insFileFilterLog")
	public String insertFileFilterLog(HttpServletRequest req){
		return "logger/logger_filefilterlog_add";
	}
	
	@RequestMapping("/addFileFilterLog")
	@ResponseBody
	public String add(HttpServletRequest req,FileFilterLog ffl){
		Message msg =createMsg();
		
		service.addFileFilterLog(ffl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(ffl),LogRemark.ADDFILTERFILELOG);
		return Json(msg);
	}
	@RequestMapping("/readFileFilterLog")
	public String readFileFilterLog(HttpServletRequest req,int id){
		FileFilterLog ffl =service.getFileFilterLogList(id);
		req.setAttribute("filefilterloginfo",Array(ffl));
		return "logger/logger_filefilterlog_upd";
		
	}
	
	@RequestMapping("/updFileFilterLog")
	@ResponseBody
	public String updFileFilterLog(HttpServletRequest req,FileFilterLog ffl){
		Message msg =createMsg();
		FileFilterLog filefilter =service.getFileFilterLogList(ffl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(filefilter),LogRemark.UPDFILTERFILELOG);
		if(service.updFileFilterLog(ffl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delFileFilterLog")
	public String delFileFilterLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		FileFilterLog filefilter =service.getFileFilterLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(filefilter),LogRemark.DELFILTERFILELOG);
		if(service.delFileFilterLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selFileFilterLog";
	}
	
	@RequestMapping("/selReportLog")
	public String ReportLogList(HttpServletRequest req){
		
		return "logger/logger_reportlog_list";
	}
	
	@RequestMapping(value="/queryReportLog",method = RequestMethod.GET)
	@ResponseBody
	public String queryReportLog(HttpServletRequest req,ReportLogVo vo){
		Message msg =createMsg();
		
		Page<ReportLog> page =service.queryReportLog(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insReportLog")
	public String insertReportLog(HttpServletRequest req){
		return "logger/logger_reportlog_add";
	}
	
	@RequestMapping("/addReportLog")
	@ResponseBody
	public String add(HttpServletRequest req,ReportLog rl){
		Message msg =createMsg();
		
		service.addReportLog(rl);
		
		msg.setStatus("0");
		msg.setResult("成功");
		saveLog(createLog(req),LogRemark.ADD,Json(rl),LogRemark.ADDREPORTLOG);

		return Json(msg);
	}
	@RequestMapping("/readReportLog")
	public String readReportLog(HttpServletRequest req,int id){
		ReportLog rl =service.getReportLogList(id);
		req.setAttribute("reportloginfo",Array(rl));
		return "logger/logger_reportlog_upd";
		
	}
	
	@RequestMapping("/updReportLog")
	@ResponseBody
	public String updReportLog(HttpServletRequest req,ReportLog rl){
		Message msg =createMsg();
		ReportLog report =service.getReportLogList(rl.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(report),LogRemark.UPDREPORTLOG);
		if(service.updReportLog(rl)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/delReportLog")
	public String delReportLog(HttpServletRequest req,int id){
		Message msg =createMsg();
		ReportLog report =service.getReportLogList(id);
		saveLog(createLog(req),LogRemark.DEL,Json(report),LogRemark.DELREPORTLOG);
		if(service.delReportLog(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selReportLog";
	}
	
	

}
