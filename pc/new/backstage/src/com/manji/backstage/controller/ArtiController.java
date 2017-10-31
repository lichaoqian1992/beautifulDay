package com.manji.backstage.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.Article;
import com.manji.backstage.model.content.ArticleAlbums;
import com.manji.backstage.model.content.ArticleCategory;
import com.manji.backstage.model.content.ArticleCategoryCount;
import com.manji.backstage.model.content.ArticleCategoryField;
import com.manji.backstage.model.content.ArticleCategorySpec;
import com.manji.backstage.model.content.ArticleCategoryUser;
import com.manji.backstage.model.content.ArticleComment;
import com.manji.backstage.model.content.ArticleContent;
import com.manji.backstage.model.content.ArticleCount;
import com.manji.backstage.model.content.ArticleGoods;
import com.manji.backstage.model.content.ArticleInfo;
import com.manji.backstage.model.content.ArticleScreen;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.content.ArticleAlbumsVo;
import com.manji.backstage.vo.content.ArticleCategoryCountVo;
import com.manji.backstage.vo.content.ArticleCategoryFieldVo;
import com.manji.backstage.vo.content.ArticleCategorySpecVo;
import com.manji.backstage.vo.content.ArticleCategoryUserVo;
import com.manji.backstage.vo.content.ArticleCategoryVo;
import com.manji.backstage.vo.content.ArticleCommentVo;
import com.manji.backstage.vo.content.ArticleContentVo;
import com.manji.backstage.vo.content.ArticleCountVo;
import com.manji.backstage.vo.content.ArticleGoodsVo;
import com.manji.backstage.vo.content.ArticleInfoVo;
import com.manji.backstage.vo.content.ArticleScreenVo;
import com.manji.backstage.vo.content.ArticleVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;
@Controller
public class ArtiController extends BaseController{

	@Autowired
	private ArtiService service;
	
	@Autowired
	private LoggersService logService;

	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("arti");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}

	// 基础内容
	@RequestMapping("/selArticle")
	public String selArticle(HttpServletRequest req) {

		return "content/article_list";
	}

	@RequestMapping(value = "/queryArticle", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticle(HttpServletRequest req, ArticleVo vo) {

		Message msg = createMsg();
		Page<Article> page = service.queryArticle(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticle")
	public String readArticle(HttpServletRequest req, int id) {
		Article art = service.getArticle(id);
		req.setAttribute("article", Json(art));

		return "content/article_upd";
	}

	@RequestMapping(value = "/updArticle", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String updArticle(HttpServletRequest req, Article art,@RequestParam MultipartFile file) {

		Message msg = createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = service.sendPostReq(fileName, base64Str);
			art.setImg_url(url);
		}else{
			art.setImg_url(req.getParameter("icon"));
		}
		
		
		Article a = service.getArticle(art.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLE);

		if (service.updArticle(art)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "content/article_list";

	}

	@RequestMapping("/insArticle")
	public String insArticle(HttpServletRequest req) {

		return "content/article_add";
	}

	@RequestMapping(value = "/addArticle", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String addArticle(HttpServletRequest req, Article art,@RequestParam MultipartFile file) {
		Message msg = createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = service.sendPostReq(fileName, base64Str);
			art.setImg_url(url);
		}else{
			art.setImg_url(req.getParameter("icon"));
		}
		
		service.addArticle(art);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(art), LogRemark.ADDARTICLE);
		return "content/article_list";
	}

	@RequestMapping("/delArticle")
	public String delArticle(HttpServletRequest req, int id) {

		Article a = service.getArticle(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLE);

		if (service.delArticle(id)) {

		}
		return "redirect:/selArticle";
	}

	@RequestMapping("/selArticleInfo")
	public String selArticleInfo(HttpServletRequest req) {

		return "content/article_info_list";
	}

	@RequestMapping(value = "/queryArticleInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleInfo(HttpServletRequest req, ArticleInfoVo vo) {
		Message msg = createMsg();
		Page<ArticleInfo> page = service.queryArticleInfo(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);

	}

	@RequestMapping("/readArticleInfo")
	public String readArticleInfo(HttpServletRequest req, int id) {
		ArticleInfo ai = service.getArticleInfo(id);
		req.setAttribute("articleinfo", Json(ai));
		return "content/article_info_upd";
	}

	@RequestMapping(value = "/updArticleInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleInfo(HttpServletRequest req, ArticleInfo ai) {

		Message msg = createMsg();

		ArticleInfo a = service.getArticleInfo(ai.getArticle_id());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLEINFO);

		if (service.updArticleInfo(ai)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/insArticleInfo")
	public String insArticleInfo(HttpServletRequest req) {

		return "content/article_info_add";
	}

	@RequestMapping(value = "/addArticleInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleInfo(HttpServletRequest req, ArticleInfo ai) {
		Message msg = createMsg();

		service.addArticleInfo(ai);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(ai), LogRemark.ADDARTICLEINFO);

		return Json(msg);
	}

	@RequestMapping("/delArticleInfo")
	public String delArticleInfo(HttpServletRequest req, int id) {
		Message msg = createMsg();
		ArticleInfo a = service.getArticleInfo(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLEINFO);

		if (service.delArticleInfo(id)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "redirect:/selArticleInfo";
	}

	@RequestMapping("/selArticleContent")
	public String selArticleContent(HttpServletRequest req) {

		return "content/article_content_list";
	}

	@RequestMapping(value = "/queryArticleContent", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleContent(HttpServletRequest req, ArticleContentVo vo) {
		Message msg = createMsg();
		Page<ArticleContent> page = service.queryArticleContent(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("2");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticleContent")
	public String readArticleContent(HttpServletRequest req, int id) {
		ArticleContent ac = service.getArticleContent(id);
		req.setAttribute("articlecontent", Json(ac));

		return "content/article_content_upd";
	}

	@RequestMapping(value = "/updArticleContent", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleContent(HttpServletRequest req, ArticleContent ac) {
		Message msg = createMsg();

		ArticleContent a = service.getArticleContent(ac.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLECONTENT);

		if (service.updArticleContent(ac)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/insArticleContent")
	public String insArticleContent(HttpServletRequest req) {

		return "content/article_content_add";
	}

	@RequestMapping(value = "/addArticleContent", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleContent(HttpServletRequest req, ArticleContent ac) {
		Message msg = createMsg();
		ac.setUpdate_time(TimeUtils.getCurrentTime());
		service.addArticleContent(ac);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(ac), LogRemark.ADDARTICLECONTENT);
		return Json(msg);
	}

	@RequestMapping("/delArticleContent")
	public String delArticleContent(HttpServletRequest req, int id) {
		Message msg = createMsg();

		ArticleContent a = service.getArticleContent(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLECONTENT);

		if (service.delArticleContent(id)) {
			msg.setStatus("0");
		}
		return "redirect:/selArticleContent";
	}

	@RequestMapping("/selArticleCount")
	public String selArticleCount(HttpServletRequest req) {

		return "content/article_count_list";
	}

	@RequestMapping(value = "/queryArticleCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCount(HttpServletRequest req, ArticleCountVo vo) {
		Message msg = createMsg();
		Page<ArticleCount> page = service.queryArticleCount(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("2");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticleCount")
	public String readArticleCount(HttpServletRequest req, int id) {
		ArticleCount ac = service.getArticleCount(id);
		req.setAttribute("articlecount", Json(ac));
		return "content/article_count_upd";

	}

	@RequestMapping(value = "/updArticleCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCount(HttpServletRequest req, ArticleCount ac) {
		Message msg = createMsg();
		ArticleCount a = service.getArticleCount(ac.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLECOUNT);
		if (service.updArticleCount(ac)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);

	}

	@RequestMapping("/insArticleCount")
	public String insArticleCount(HttpServletRequest req) {
		return "content/article_count_add";

	}

	@RequestMapping(value = "/addArticleCount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCount(HttpServletRequest req, ArticleCount ac) {

		Message msg = createMsg();
		ac.setUpdate_time(TimeUtils.getCurrentTime());
		service.addArticleCount(ac);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(ac), LogRemark.ADDARTICLECOUNT);
		return Json(msg);
	}

	@RequestMapping("/delArticleCount")
	public String delArticleCount(HttpServletRequest req, int id) {
		Message msg = createMsg();

		ArticleCount a = service.getArticleCount(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLECOUNT);

		if (service.delAtricleCount(id)) {
			msg.setStatus("0");
		}
		return "redirect:/selArticleCount";
	}

	@RequestMapping("/selArticleComment")
	public String selArticleComment(HttpServletRequest req) {

		return "content/article_comment_list";
	}

	@RequestMapping(value = "/queryArticleComment", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleComment(HttpServletRequest req, ArticleCommentVo vo) {
		Message msg = createMsg();
		Page<ArticleComment> page = service.queryArticleComment(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticleComment")
	public String readArticleComment(HttpServletRequest req, int id) {
		ArticleComment ac = service.getArticleComment(id);
		req.setAttribute("articlecomment", Json(ac));
		return "content/article_comment_upd";
	}

	@RequestMapping(value = "/updArticleComment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String updArticleComment(HttpServletRequest req, ArticleComment ac,@RequestParam MultipartFile[] file) {
		Message msg = createMsg();
		String url = "";
		if(file != null && file.length>0){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				//System.out.println(fileName+" "+base64Str);
				
				if(i == file.length-1){
					url += service.sendPostReq(fileName, base64Str);
				}else{
					url += service.sendPostReq(fileName, base64Str)+",";
				}
			}
			if(url.equals("")){
				ac.setPics(req.getParameter("icon2"));
			}else{
				ac.setPics(url);
			}
		}else{
			ac.setPics(req.getParameter("icon2"));
		}
		
		ArticleComment a = service.getArticleComment(ac.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLECOMMENT);

		if (service.updArticleComment(ac)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return "content/article_comment_list";

	}

	@RequestMapping("/insArticleComment")
	public String insArticleComment(HttpServletRequest req) {

		return "content/article_comment_add";
	}

	@RequestMapping(value = "/addArticleComment", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String addArticleComment(HttpServletRequest req, ArticleComment ac,@RequestParam MultipartFile[] file) {
		Message msg = createMsg();
		String url = "";
		if(file != null && file.length>0){
			for(int i=0;i<file.length;i++){
				String fileName = file[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file[i]);
				//System.out.println(fileName+" "+base64Str);
				url += service.sendPostReq(fileName, base64Str)+",";
				
			}
			ac.setPics(url);
		}else{
			ac.setPics(req.getParameter("icon2"));
		}
		
		service.addArticleComment(ac);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(ac), LogRemark.ADDARTICLECOMMENT);
		return "content/article_comment_list";
	}

	@RequestMapping("/delArticleComment")
	public String delArticleComment(HttpServletRequest req, int id) {
		Message msg = createMsg();
		ArticleComment a = service.getArticleComment(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLECOMMENT);

		if (service.delArticleComment(id)) {
			msg.setStatus("0");

		} else {

		}
		return "redirect:/selArticleComment";

	}

	@RequestMapping("/selArticleAlbums")
	public String selArticleAlbums(HttpServletRequest req) {

		return "content/article_albums_list";
	}

	@RequestMapping(value = "/queryArticleAlbums", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleAlbums(HttpServletRequest req, ArticleAlbumsVo vo) {
		Message msg = createMsg();
		Page<ArticleAlbums> page = service.queryArticleAlbums(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticleAlbums")
	public String readArticleAlbums(HttpServletRequest req, int id) {
		ArticleAlbums aa = service.getArticleAlbums(id);
		req.setAttribute("articlealbums", Json(aa));
		return "content/article_albums_upd";

	}

	@RequestMapping(value = "/updArticleAlbums", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String updArticleAlbums(HttpServletRequest req, ArticleAlbums aa,@RequestParam MultipartFile file,@RequestParam MultipartFile file2) {
		Message msg = createMsg();
		
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = service.sendPostReq(fileName, base64Str);
			aa.setThumb_path(url);
			
		}else{
			aa.setThumb_path(req.getParameter("icon"));
		}
		if(!file2.isEmpty()){
			String fileName2 = file2.getOriginalFilename();
			String base64Str2 = Base64Utils.GetBase64Code(file2);
			String url2 = service.sendPostReq(fileName2, base64Str2);
			aa.setOriginal_path(url2);
		}else{
			aa.setOriginal_path(req.getParameter("icon2"));
		}
		
		ArticleAlbums a = service.getArticleAlbums(aa.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLEALBUMS);
		if (service.updArticleAlbums(aa)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return "content/article_albums_list";
	}

	@RequestMapping("/insArticleAlbums")
	public String insArticleAlbums(HttpServletRequest req) {

		return "content/article_albums_add";
	}

	@RequestMapping(value = "/addArticleAlbums", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	
	public String addArticleAlbums(HttpServletRequest req, ArticleAlbums aa,@RequestParam MultipartFile file,@RequestParam MultipartFile file2) {
		Message msg = createMsg();
		
		if(!file.isEmpty() && !file2.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String fileName2 = file.getOriginalFilename();
			String base64Str2 = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = service.sendPostReq(fileName, base64Str);
			String url2 = service.sendPostReq(fileName2, base64Str2);
			aa.setThumb_path(url);
			aa.setOriginal_path(url2);
		}else{
			aa.setThumb_path(req.getParameter("icon"));
			aa.setOriginal_path(req.getParameter("icon2"));
		}
		
		aa.setAdd_time(TimeUtils.getCurrentTime());
		service.addArticleAlbums(aa);
		msg.setStatus("0");
		saveLog(createLog(req), LogRemark.ADD, Json(aa), LogRemark.ADDARTICLEALBUMS);
		return "content/article_albums_list";
	}

	@RequestMapping("/delArticleAlbums")
	public String delArticleAlbums(HttpServletRequest req, int id) {

		Message msg = createMsg();
		ArticleAlbums a = service.getArticleAlbums(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLEALBUMS);

		if (service.delArticleAlbums(id)) {
			msg.setStatus("0");
		} else {

		}
		return "redirect:/selArticleAlbums";

	}

	@RequestMapping("/selArticleGoods")
	public String selArticleGoods(HttpServletRequest req) {
		return "content/article_goods_list";
	}

	@RequestMapping(value = "/queryArticleGoods", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleGoods(HttpServletRequest req, ArticleGoodsVo vo) {
		Message msg = createMsg();
		Page<ArticleGoods> page = service.queryArticleGoods(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/readArticleGoods")
	public String readArticleGoods(HttpServletRequest req, int id) {
		ArticleGoods ag = service.getArticleGoods(id);
		req.setAttribute("articlegoods", Json(ag));

		return "content/article_goods_upd";
	}

	@RequestMapping(value = "/updArticleGoods", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleGoods(HttpServletRequest req, ArticleGoods ag) {
		Message msg = createMsg();
		ArticleGoods a = service.getArticleGoods(ag.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLEGOODS);

		if (service.updArticleGoods(ag)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}
		return Json(msg);
	}

	@RequestMapping("/insArticleGoods")
	public String insArticleGoods(HttpServletRequest req) {

		return "content/article_goods_add";
	}

	@RequestMapping(value = "/addArticleGoods", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleGoods(HttpServletRequest req, ArticleGoods ag) {
		Message msg = createMsg();
		service.addArticleGoods(ag);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(ag), LogRemark.ADDARTICLEGOODS);
		return Json(msg);
	}

	@RequestMapping("/delArticleGoods")
	public String delArticleGoods(HttpServletRequest req, int id) {
		Message msg = createMsg();
		ArticleGoods a = service.getArticleGoods(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLEGOODS);

		if (service.delArticleGoods(id)) {
			msg.setStatus("0");
		} else {

		}
		return "redirect:/selArticleGoods";

	}

	@RequestMapping("/selArticleScreen")
	public String selArticleScreen(HttpServletRequest req) {

		return "content/article_screen_list";
	}

	@RequestMapping(value = "/queryArticleScreen", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleScreen(HttpServletRequest req, ArticleScreenVo vo) {
		Message msg = createMsg();
		Page<ArticleScreen> page = service.queryArticleScreen(vo);
		if (page.getTotalCount() != 0) {
			msg.setStatus("0");
			msg.setResult(page);
		} else {
			msg.setStatus("1");
		}
		return Json(msg);

	}

	@RequestMapping("/readArticleScreen")
	public String readArticleScreen(HttpServletRequest req, long id) {
		ArticleScreen as = service.getArticleScreen(id);
		req.setAttribute("articlescreen", Json(as));
		return "content/article_screen_upd";
	}

	@RequestMapping(value = "/updArticleScreen", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleScreen(HttpServletRequest req, ArticleScreen as) {
		Message msg = createMsg();
		ArticleScreen a = service.getArticleScreen(as.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLESCREEN);

		if (service.updArticleScreen(as)) {
			msg.setStatus("0");
		} else {
			msg.setStatus("1");
		}

		return Json(msg);
	}

	@RequestMapping("/insArticleScreen")
	public String insArticleScreen(HttpServletRequest req) {

		return "content/article_screen_add";
	}

	@RequestMapping(value = "/addArticleScreen", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleScreen(HttpServletRequest req, ArticleScreen as) {

		Message msg = createMsg();
		service.addArticleScreen(as);
		msg.setStatus("0");

		saveLog(createLog(req), LogRemark.ADD, Json(as), LogRemark.ADDARTICLESCREEN);
		return Json(msg);
	}

	@RequestMapping("/delArticleScreen")
	public String delArticleScreen(HttpServletRequest req, int id) {
		Message msg = createMsg();
		ArticleScreen a = service.getArticleScreen(id);
		saveLog(createLog(req), LogRemark.DEL, Json(a), LogRemark.DELARTICLESCREEN);

		if (service.delArticleScreen(id)) {
			msg.setStatus("0");
		}
		return "redirect:/selArticleScreen";
	}

	@RequestMapping("/selArticleCategory")
	public String selArticleCategory(HttpServletRequest req){
		return "content/article_category_list";
	}
	@RequestMapping(value ="/queryArticleCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCategory(HttpServletRequest req,ArticleCategoryVo vo){
		
		Message msg =createMsg();
		Page<ArticleCategory> page =service.queryArticleCategory(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	
	@RequestMapping("/readArticleCategory")
	public String readArticleCategory(HttpServletRequest req,int id){
		
		ArticleCategory aru =service.getArticleCategory(id);
		req.setAttribute("articlecategory", Json(aru));
		return "content/article_category_upd";
		
	}
	@RequestMapping("/insArticleCategory")
	public String insArticleCategory(HttpServletRequest req){
		
		return "content/article_category_add";
	}
	@RequestMapping("/addArticleCategory")
	public String addArticleCategory(HttpServletRequest req,ArticleCategory ac,@RequestParam MultipartFile file,@RequestParam MultipartFile[] file2){
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = service.sendPostReq(fileName, base64Str);
			ac.setImg_url(address);
		}else{
			ac.setImg_url(req.getParameter("imgUrl"));
		}
		String address2 = "";
		if(file2 != null && file2.length>0){
			for(int i=0;i<file2.length;i++){
				String fileName = file2[i].getOriginalFilename();
				String base64Str = Base64Utils.GetBase64Code(file2[i]);
				address2 += service.sendPostReq(fileName, base64Str)+";";
			}
			ac.setMoren(address2);
		}else{
			ac.setMoren("");
		}
		service.addArticleCategory(ac);
		saveLog(createLog(req), LogRemark.ADD, Json(ac), LogRemark.ADDARTICLECATEGORY);
		return "redirect:/insArticleCategory";
	}
	
	
	@RequestMapping(value = "/updArticleCategory", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updArticleCategory(HttpServletRequest req,ArticleCategory ac,@RequestParam MultipartFile file,@RequestParam MultipartFile [] file2){
		String id = req.getParameter("id");
		String oldUrl = req.getParameter("oldUrl");
		String address2 = "";
		Message msg = createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String address = service.sendPostReq(fileName, base64Str);
			ac.setImg_url(address);
		}else{
			ac.setImg_url(req.getParameter("imgUrl"));
		}
		if(file2 != null && file2.length>0){
			for(int i=0;i<file2.length;i++){
				String fileName2 = file2[i].getOriginalFilename();
				String base64Str2 = Base64Utils.GetBase64Code(file2[i]);
				if(i == file2.length - 1){
					address2 += service.sendPostReq(fileName2, base64Str2);
				}else{
					address2 += service.sendPostReq(fileName2, base64Str2)+";";
				}
			}
			if("".equals(address2)){
				ac.setMoren(oldUrl);
			}else{
				ac.setMoren(oldUrl+address2+";");
			}
		}else{
			ac.setMoren(oldUrl);
		}
		if(service.updArticleCategory(ac)){
			msg.setStatus("0");
			msg.setResult("修改成功");
		}else{
			msg.setStatus("1");
			msg.setResult("修改失败");
		}
		ArticleCategory a = service.getArticleCategory(ac.getId());
		saveLog(createLog(req), LogRemark.UPD, Json(a), LogRemark.UPDARTICLECATEGORY);
		
		return "redirect:/readArticleCategory?id="+id;
		
	}
	
	
	@RequestMapping("/delArticleCategory")
	public String delArticleCategory(HttpServletRequest req,int id){
		Message msg =createMsg();
		if(service.delArticleCategory(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		ArticleCategory ac = service.getArticleCategory(id);
		saveLog(createLog(req), LogRemark.DEL, Json(ac), LogRemark.DELARTICLECATEGORY);
		return "redirect:/selArticleCategory";
		
		
	}
	
	
	
	@RequestMapping("/selArticleCategoryCount")
	public String selArticleCategoryCount(HttpServletRequest req){
		return "content/article_categorycount_list";
	}
	@RequestMapping(value ="/queryArticleCategoryCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCategoryCount(HttpServletRequest req,ArticleCategoryCountVo vo){
		
		Message msg =createMsg();
		Page<ArticleCategoryCount> page =service.queryArticleCategoryCount(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleCategoryCount")
	public String readArticleCategoryCount(HttpServletRequest req,int id){
		
		ArticleCategoryCount aru =service.getArticleCategoryCount(id);
		req.setAttribute("catecount", Json(aru));
		return "content/article_categorycount_upd";
		
	}
	@RequestMapping(value ="/updArticleCategoryCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCategoryCount(HttpServletRequest req,ArticleCategoryCount aru){
		Message msg =createMsg();
		
		/*ArticleCategoryCount a =service.getArticleCategoryCount(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDArticleCategoryCount);*/
		
		if(service.updArticleCategoryCount(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleCategoryCount")
	public String insArticleCategoryCount(HttpServletRequest req){
		
		return "content/article_categorycount_add";
	}
	@RequestMapping(value ="/addArticleCategoryCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCategoryCount(HttpServletRequest req,ArticleCategoryCount aru){
		Message msg =createMsg();
		service.addArticleCategoryCount(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDArticleCategoryCount);*/
		return Json(msg);
	}
	@RequestMapping("/delArticleCategoryCount")
	public String delArticleCategoryCount(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ArticleCategoryCount a =service.getArticleCategoryCount(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELArticleCategoryCount);*/
		
		if(service.delArticleCategoryCount(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selArticleCategoryCount";
		
		
	}
	
	@RequestMapping("/selArticleCategoryUser")
	public String selArticleCategoryUser(HttpServletRequest req){
		return "content/article_categoryuser_list";
	}
	@RequestMapping(value ="/queryArticleCategoryUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCategoryUser(HttpServletRequest req,ArticleCategoryUserVo vo){
		
		Message msg =createMsg();
		Page<ArticleCategoryUser> page =service.queryArticleCategoryUser(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleCategoryUser")
	public String readArticleCategoryUser(HttpServletRequest req,int id){
		
		ArticleCategoryUser aru =service.getArticleCategoryUser(id);
		req.setAttribute("articlecategoryuser", Json(aru));
		return "content/article_categoryuser_upd";
		
	}
	@RequestMapping(value ="/updArticleCategoryUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCategoryUser(HttpServletRequest req,ArticleCategoryUser aru){
		Message msg =createMsg();
		
		/*ArticleCategoryUser a =service.getArticleCategoryUser(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDArticleCategoryUser);*/
		
		if(service.updArticleCategoryUser(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleCategoryUser")
	public String insArticleCategoryUser(HttpServletRequest req){
		
		return "content/article_categoryuser_add";
	}
	@RequestMapping(value ="/addArticleCategoryUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCategoryUser(HttpServletRequest req,ArticleCategoryUser aru){
		Message msg =createMsg();
		service.addArticleCategoryUser(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDArticleCategoryUser);*/
		return Json(msg);
	}
	@RequestMapping("/delArticleCategoryUser")
	public String delArticleCategoryUser(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ArticleCategoryUser a =service.getArticleCategoryUser(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELArticleCategoryUser);*/
		
		if(service.delArticleCategoryUser(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selArticleCategoryUser";
		
		
	}
	
	
	
	@RequestMapping("/selArticleCategoryField")
	public String selArticleCategoryField(HttpServletRequest req){
		return "content/article_categoryfield_list";
	}
	@RequestMapping(value ="/queryArticleCategoryField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCategoryField(HttpServletRequest req,ArticleCategoryFieldVo vo){
		
		Message msg =createMsg();
		Page<ArticleCategoryField> page =service.queryArticleCategoryField(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleCategoryField")
	public String readArticleCategoryField(HttpServletRequest req,int id){
		
		ArticleCategoryField aru =service.getArticleCategoryField(id);
		req.setAttribute("articlecategoryfield", Json(aru));
		return "content/article_categoryfield_upd";
		
	}
	@RequestMapping(value ="/updArticleCategoryField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCategoryField(HttpServletRequest req,ArticleCategoryField aru){
		Message msg =createMsg();
		
		/*ArticleCategoryField a =service.getArticleCategoryField(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDArticleCategoryField);*/
		
		if(service.updArticleCategoryField(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleCategoryField")
	public String insArticleCategoryField(HttpServletRequest req){
		
		return "content/article_categoryfield_add";
	}
	@RequestMapping(value ="/addArticleCategoryField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCategoryField(HttpServletRequest req,ArticleCategoryField aru){
		Message msg =createMsg();
		service.addArticleCategoryField(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDArticleCategoryField);*/
		return Json(msg);
	}
	@RequestMapping("/delArticleCategoryField")
	public String delArticleCategoryField(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ArticleCategoryField a =service.getArticleCategoryField(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELArticleCategoryField);*/
		
		if(service.delArticleCategoryField(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selArticleCategoryField";
		
		
	}
	
	
	
	@RequestMapping("/selArticleCategorySpec")
	public String selArticleCategorySpec(HttpServletRequest req){
		return "content/article_categoryspec_list";
	}
	@RequestMapping(value ="/queryArticleCategorySpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCategorySpec(HttpServletRequest req,ArticleCategorySpecVo vo){
		
		Message msg =createMsg();
		Page<ArticleCategorySpec> page =service.queryArticleCategorySpec(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleCategorySpec")
	public String readArticleCategorySpec(HttpServletRequest req,int id){
		
		ArticleCategorySpec aru =service.getArticleCategorySpec(id);
		req.setAttribute("articlecategoryspec", Json(aru));
		return "content/article_categoryspec_upd";
		
	}
	@RequestMapping(value ="/updArticleCategorySpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCategorySpec(HttpServletRequest req,ArticleCategorySpec aru){
		Message msg =createMsg();
		
		/*ArticleCategorySpec a =service.getArticleCategorySpec(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDArticleCategorySpec);*/
		
		if(service.updArticleCategorySpec(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleCategorySpec")
	public String insArticleCategorySpec(HttpServletRequest req){
		
		return "content/article_categoryspec_add";
	}
	@RequestMapping(value ="/addArticleCategorySpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCategorySpec(HttpServletRequest req,ArticleCategorySpec aru){
		Message msg =createMsg();
		service.addArticleCategorySpec(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDArticleCategorySpec);*/
		return Json(msg);
	}
	@RequestMapping("/delArticleCategorySpec")
	public String delArticleCategorySpec(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ArticleCategorySpec a =service.getArticleCategorySpec(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELArticleCategorySpec);*/
		
		if(service.delArticleCategorySpec(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selArticleCategorySpec";
		
		
	}
	
	

	
	@RequestMapping("/selOrderCommentFalse")
	public String selOrderCommentFalse(HttpServletRequest req){
		
		return "order/order_commentfalse_list";
	}
	@RequestMapping(value ="/queryOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderCommentFalse(HttpServletRequest req,OrderCommentFalseVo vo){
		Message msg =createMsg();
		
		Page<OrderCommentFalse> page =service.queryOrderCommentFalse(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderCommentFalse")
	public String insOrderCommentFalse(HttpServletRequest req){
		
		return "order/order_commentfalse_add";
	}
	@RequestMapping(value ="/addOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderCommentFalse(HttpServletRequest req,OrderCommentFalse ocf){
		Message msg =createMsg();
		service.addOrderCommentFalse(ocf);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ocf),LogRemark.ADDORDERCOMMENTFALSE);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderCommentFalse")
	public String readOrderCommentFalse(HttpServletRequest req,int id){
		OrderCommentFalse ocf =service.getOrderCommentFalse(id);
		req.setAttribute("ordercommentfalseinfo", Json(ocf));
		return "order/order_commentfalse_upd";
	}
	@RequestMapping(value ="/updOrderCommentFalse", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderCommentFalse(HttpServletRequest req,OrderCommentFalse ocf){
		Message msg =createMsg();
		OrderCommentFalse o =service.getOrderCommentFalse(ocf.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCOMMENTFALSE);
		if(service.updOrderCommentFalse(ocf)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderCommentFalse")
	public String delOrderCommentFalse(HttpServletRequest req,int id){
		OrderCommentFalse o =service.getOrderCommentFalse(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERCOMMENTFALSE);
		if(service.delOrderCommentFalse(id)){
			
		}
		return "redirect:/selOrderCommentFalse";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	@RequestMapping("/selOrderCommentTem")
	public String selOrderCommentTem(HttpServletRequest req){
		
		return "order/order_commenttem_list";
	}
	@RequestMapping(value ="/queryOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryOrderCommentTem(HttpServletRequest req,OrderCommentTemVo vo){
		Message msg =createMsg();
		
		Page<OrderCommentTem> page =service.queryOrderCommentTem(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insOrderCommentTem")
	public String insOrderCommentTem(HttpServletRequest req){
		
		return "order/order_commenttem_add";
	}
	@RequestMapping(value ="/addOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addOrderCommentTem(HttpServletRequest req,OrderCommentTem oct){
		Message msg =createMsg();
		service.addOrderCommentTem(oct);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(oct),LogRemark.ADDORDERCOMMENTTEM);
		return Json(msg);
		
	}
	@RequestMapping("/readOrderCommentTem")
	public String readOrderCommentTem(HttpServletRequest req,int id){
		OrderCommentTem oct =service.getOrderCommentTem(id);
		req.setAttribute("ordercommentteminfo", Json(oct));
		return "order/order_commenttem_upd";
	}
	@RequestMapping(value ="/updOrderCommentTem", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updOrderCommentTem(HttpServletRequest req,OrderCommentTem oct){
		Message msg =createMsg();
		OrderCommentTem o =service.getOrderCommentTem(oct.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDORDERCOMMENTTEM);
		if(service.updOrderCommentTem(oct)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delOrderCommentTem")
	public String delOrderCommentTem(HttpServletRequest req,int id){
		OrderCommentTem o =service.getOrderCommentTem(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELORDERCOMMENTTEM);
		
		if(service.delOrderCommentTem(id)){
			
		}
		return "redirect:/selOrderCommentTem";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
}
