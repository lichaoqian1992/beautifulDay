package com.manji.backstage.controller.content;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.ActAdvert;
import com.manji.backstage.model.content.ActBusGoods;
import com.manji.backstage.model.content.ActBusShop;
import com.manji.backstage.model.content.ActBusiness;
import com.manji.backstage.model.content.ActCommon;
import com.manji.backstage.model.content.ActIndUser;
import com.manji.backstage.model.content.ActIndex;
import com.manji.backstage.model.content.ActRecUser;
import com.manji.backstage.model.content.ActRecherge;
import com.manji.backstage.model.content.ActShop;
import com.manji.backstage.model.content.ActShopGoods;
import com.manji.backstage.model.content.ActSms;
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
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.model.content.Channel;
import com.manji.backstage.model.content.ChannelField;
import com.manji.backstage.model.content.ChannelSite;
import com.manji.backstage.model.content.ChannelSpec;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.service.content.ContentService;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.content.ActAdvertVo;
import com.manji.backstage.vo.content.ActBusGoodsVo;
import com.manji.backstage.vo.content.ActBusShopVo;
import com.manji.backstage.vo.content.ActBusinessVo;
import com.manji.backstage.vo.content.ActCommonVo;
import com.manji.backstage.vo.content.ActIndUserVo;
import com.manji.backstage.vo.content.ActIndexVo;
import com.manji.backstage.vo.content.ActRecUserVo;
import com.manji.backstage.vo.content.ActRechergeVo;
import com.manji.backstage.vo.content.ActShopGoodsVo;
import com.manji.backstage.vo.content.ActShopVo;
import com.manji.backstage.vo.content.ActSmsVo;
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
import com.manji.backstage.vo.content.BusiTempVo;
import com.manji.backstage.vo.content.ChannelFieldVo;
import com.manji.backstage.vo.content.ChannelSiteVo;
import com.manji.backstage.vo.content.ChannelSpecVo;
import com.manji.backstage.vo.content.ChannelVo;


@Controller
public class ContentController extends BaseController{

	@Autowired
	private ContentService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("content");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	///dt_channel ;

//	public String queryChannel(){
//		
//	}
//	public String readChannel(){
//		
//	}
//	public String updChannel(){
//		
//	}
//	public String addChannel(){
//		
//	}
//	public String insChannel(){
//		
//	}
//	public String delChannel(){
//		
//	}
	
	
	//分类
	/*@RequestMapping("/selCategory")
	public String selCategory(HttpServletRequest req){
		return "content/category_list";
	}*/
//	public String queryCategory(){
//		
//	}
	
	
	
	//基础内容
	@RequestMapping("/selArticle")
	public String selArticle(HttpServletRequest req){
		
		return "content/article_list";
	}
	@RequestMapping(value ="/queryArticle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticle(HttpServletRequest req,ArticleVo vo){
		
		Message msg =createMsg();
		Page<Article> page=service.queryArticle(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticle")
	public String readArticle(HttpServletRequest req,int id){
		Article art =service.getArticle(id);
		req.setAttribute("article", Json(art));
		
		return "content/article_upd";
	}
	@RequestMapping(value ="/updArticle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticle(HttpServletRequest req,Article art){
		
		Message msg =createMsg();
		Article a =service.getArticle(art.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLE);
		
		if(service.updArticle(art)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insArticle")
	public String insArticle(HttpServletRequest req){
		
		return "content/article_add";
	}
	@RequestMapping(value ="/addArticle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticle(HttpServletRequest req,Article art){
		Message msg =createMsg();
		service.addArticle(art);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(art),LogRemark.ADDARTICLE);
		return Json(msg);
	}
	@RequestMapping("/delArticle")
	public String delArticle(HttpServletRequest req,int id){
		
		Article a =service.getArticle(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLE);
		
		if(service.delArticle(id)){
			
		}
		return "redirect:/selArticle";
	}
	
	
	@RequestMapping("/selArticleInfo")
	public String selArticleInfo(HttpServletRequest req){
		
		return "content/article_info_list";
	}
	@RequestMapping(value ="/queryArticleInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleInfo(HttpServletRequest req,ArticleInfoVo vo){
		Message msg =createMsg();
		Page<ArticleInfo> page=service.queryArticleInfo(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleInfo")
	public String readArticleInfo(HttpServletRequest req,int id){
		ArticleInfo ai =service.getArticleInfo(id);
		req.setAttribute("articleinfo", Json(ai));
		return "content/article_info_upd";
	}
	@RequestMapping(value ="/updArticleInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleInfo(HttpServletRequest req,ArticleInfo ai){
		
		Message msg =createMsg();
		
		ArticleInfo a =service.getArticleInfo(ai.getArticle_id());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLEINFO);
		
		if(service.updArticleInfo(ai)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleInfo")
	public String insArticleInfo(HttpServletRequest req){
		
		return "content/article_info_add";
	}
	@RequestMapping(value ="/addArticleInfo", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleInfo(HttpServletRequest req,ArticleInfo ai){
		Message msg =createMsg();
		
		service.addArticleInfo(ai);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ai),LogRemark.ADDARTICLEINFO);
		
		return Json(msg);
	}
	@RequestMapping("/delArticleInfo")
	public String delArticleInfo(HttpServletRequest req,int id){
		Message msg =createMsg();
		ArticleInfo a =service.getArticleInfo(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLEINFO);
		
		if(service.delArticleInfo(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selArticleInfo";
	}
	
	
	
	@RequestMapping("/selArticleContent")
	public String selArticleContent(HttpServletRequest req){
		
		return "content/article_content_list";
	}
	@RequestMapping(value ="/queryArticleContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleContent(HttpServletRequest req,ArticleContentVo vo){
		Message msg =createMsg();
		Page<ArticleContent> page=service.queryArticleContent(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("2");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticleContent")
	public String readArticleContent(HttpServletRequest req,int id){
		ArticleContent ac =service.getArticleContent(id);
		req.setAttribute("articlecontent", Json(ac));
		
		return "content/article_content_upd";
	}
	@RequestMapping(value ="/updArticleContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleContent(HttpServletRequest req,ArticleContent ac){
		Message msg =createMsg();
		
		ArticleContent a =service.getArticleContent(ac.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLECONTENT);
		
		if(service.updArticleContent(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleContent")
	public String insArticleContent(HttpServletRequest req){
		
		return "content/article_content_add";
	}
	@RequestMapping(value ="/addArticleContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleContent(HttpServletRequest req,ArticleContent ac){
		Message msg =createMsg();
		ac.setUpdate_time(TimeUtils.getCurrentTime());
		service.addArticleContent(ac);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDARTICLECONTENT);
		return Json(msg);
	}
	@RequestMapping("/delArticleContent")
	public String delArticleContent(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ArticleContent a =service.getArticleContent(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLECONTENT);
		
		if(service.delArticleContent(id)){
			msg.setStatus("0");
		}
		return "redirect:/selArticleContent";
	}
	
	
	@RequestMapping("/selArticleCount")
	public String selArticleCount(HttpServletRequest req){
		
		return "content/article_count_list";
	}
	@RequestMapping(value ="/queryArticleCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleCount(HttpServletRequest req,ArticleCountVo vo){
		Message msg =createMsg();
		Page<ArticleCount> page=service.queryArticleCount(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("2");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticleCount")
	public String readArticleCount(HttpServletRequest req,int id){
		ArticleCount ac =service.getArticleCount(id);
		req.setAttribute("articlecount", Json(ac));
		return "content/article_count_upd";
		
	}
	@RequestMapping(value ="/updArticleCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCount(HttpServletRequest req,ArticleCount ac){
		Message msg =createMsg();
		ArticleCount a =service.getArticleCount(ac.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLECOUNT);
		if(service.updArticleCount(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insArticleCount")
	public String insArticleCount(HttpServletRequest req){
		return "content/article_count_add";
		
	}
	@RequestMapping(value ="/addArticleCount", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCount(HttpServletRequest req,ArticleCount ac){
		
		Message msg =createMsg();
		ac.setUpdate_time(TimeUtils.getCurrentTime());
		service.addArticleCount(ac);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDARTICLECOUNT);
		return Json(msg);
	}
	@RequestMapping("/delArticleCount")
	public String delArticleCount(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ArticleCount a =service.getArticleCount(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLECOUNT);
		
		if(service.delAtricleCount(id)){
			msg.setStatus("0");
		}
		return "redirect:/selArticleCount";
	}
	
	
	
	
	@RequestMapping("/selArticleComment")
	public String selArticleComment(HttpServletRequest req){
	
		return "content/article_comment_list";
	}
	@RequestMapping(value ="/queryArticleComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleComment(HttpServletRequest req,ArticleCommentVo vo){
		Message msg =createMsg();
		Page<ArticleComment> page=service.queryArticleComment(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticleComment")
	public String readArticleComment(HttpServletRequest req,int id){
		ArticleComment ac =service.getArticleComment(id);
		req.setAttribute("articlecomment", Json(ac));
		return "content/article_comment_upd";
	}
	@RequestMapping(value ="/updArticleComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleComment(HttpServletRequest req,ArticleComment ac){
		Message msg =createMsg();
		ArticleComment a =service.getArticleComment(ac.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLECOMMENT);
		
		if(service.updArticleComment(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insArticleComment")
	public String insArticleComment(HttpServletRequest req){
		
		return "content/article_comment_add";
	}
	@RequestMapping(value ="/addArticleComment", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleComment(HttpServletRequest req,ArticleComment ac){
		Message msg =createMsg();
		
		service.addArticleComment(ac);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDARTICLECOMMENT);
		return Json(msg);
	}
	@RequestMapping("/delArticleComment")
	public String delArticleComment(HttpServletRequest req,int id){
		Message msg =createMsg();
		ArticleComment a =service.getArticleComment(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLECOMMENT);
		
		if(service.delArticleComment(id)){
			msg.setStatus("0");
			
		}else{
			
		}
		return "redirect:/selArticleComment";
		
	}
	
	
	
	@RequestMapping("/selArticleAlbums")
	public String selArticleAlbums(HttpServletRequest req){
		
		return "content/article_albums_list";
	}
	@RequestMapping(value ="/queryArticleAlbums", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleAlbums(HttpServletRequest req,ArticleAlbumsVo vo){
		Message msg =createMsg();
		Page<ArticleAlbums> page=service.queryArticleAlbums(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticleAlbums")
	public String readArticleAlbums(HttpServletRequest req,int id){
		ArticleAlbums aa =service.getArticleAlbums(id);
		req.setAttribute("articlealbums", Json(aa));
		return "content/article_albums_upd";
		
	}
	@RequestMapping(value ="/updArticleAlbums", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleAlbums(HttpServletRequest req,ArticleAlbums aa){
		Message msg =createMsg();
		ArticleAlbums a =service.getArticleAlbums(aa.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLEALBUMS);
		if(service.updArticleAlbums(aa)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insArticleAlbums")
	public String insArticleAlbums(HttpServletRequest req){
		
		return "content/article_albums_add";
	}
	@RequestMapping(value ="/addArticleAlbums", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleAlbums(HttpServletRequest req,ArticleAlbums aa){
		Message msg =createMsg();
		aa.setAdd_time(TimeUtils.getCurrentTime());
		service.addArticleAlbums(aa);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(aa),LogRemark.ADDARTICLEALBUMS);
		return Json(msg);
	}
	@RequestMapping("/delArticleAlbums")
	public String delArticleAlbums(HttpServletRequest req,int id){
		
		Message msg =createMsg();
		ArticleAlbums a =service.getArticleAlbums(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLEALBUMS);
		
		if(service.delArticleAlbums(id)){
			msg.setStatus("0");
		}else{
			
		}
		return "redirect:/selArticleAlbums";
		
	}
	
	
	@RequestMapping("/selArticleGoods")
	public String selArticleGoods(HttpServletRequest req){
		return "content/article_goods_list";
	}
	@RequestMapping(value ="/queryArticleGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleGoods(HttpServletRequest req,ArticleGoodsVo vo){
		Message msg =createMsg();
		Page<ArticleGoods> page=service.queryArticleGoods(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readArticleGoods")
	public String readArticleGoods(HttpServletRequest req, int id){
		ArticleGoods ag =service.getArticleGoods(id);
		req.setAttribute("articlegoods", Json(ag));
		
		return "content/article_goods_upd";
	}
	@RequestMapping(value ="/updArticleGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleGoods(HttpServletRequest req,ArticleGoods ag){
		Message msg =createMsg();
		ArticleGoods a =service.getArticleGoods(ag.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLEGOODS);
		
		if(service.updArticleGoods(ag)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleGoods")
	public String insArticleGoods(HttpServletRequest req){
		
		return "content/article_goods_add";
	}
	@RequestMapping(value ="/addArticleGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleGoods(HttpServletRequest req,ArticleGoods ag){
		Message msg =createMsg();
		service.addArticleGoods(ag);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ag),LogRemark.ADDARTICLEGOODS);
		return Json(msg);
	}
	@RequestMapping("/delArticleGoods")
	public String delArticleGoods(HttpServletRequest req,int id){
		Message msg =createMsg();
		ArticleGoods a =service.getArticleGoods(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLEGOODS);
		
		if(service.delArticleGoods(id)){
			msg.setStatus("0");
		}else{
			
		}
		return "redirect:/selArticleGoods";
		
	}
	
	
	
	@RequestMapping("/selArticleScreen")
	public String selArticleScreen(HttpServletRequest req){
	
		return "content/article_screen_list";
	}
	@RequestMapping(value ="/queryArticleScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleScreen(HttpServletRequest req,ArticleScreenVo vo){
		Message msg =createMsg();
		Page<ArticleScreen> page=service.queryArticleScreen(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readArticleScreen")
	public String readArticleScreen(HttpServletRequest req,long id){
		ArticleScreen as =service.getArticleScreen(id);
		req.setAttribute("articlescreen", Json(as));
		return "content/article_screen_upd";
	}
	@RequestMapping(value ="/updArticleScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleScreen(HttpServletRequest req,ArticleScreen as){
		Message msg =createMsg();
		ArticleScreen a =service.getArticleScreen(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDARTICLESCREEN);
		
		if(service.updArticleScreen(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insArticleScreen")
	public String insArticleScreen(HttpServletRequest req){
		
		return "content/article_screen_add";
	}
	@RequestMapping(value ="/addArticleScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleScreen(HttpServletRequest req,ArticleScreen as){
		
		Message msg =createMsg();
		service.addArticleScreen(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDARTICLESCREEN);
		return Json(msg);
	}
	@RequestMapping("/delArticleScreen")
	public String delArticleScreen(HttpServletRequest req,int id){
		Message msg =createMsg();
		ArticleScreen a =service.getArticleScreen(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELARTICLESCREEN);
		
		if(service.delArticleScreen(id)){
			msg.setStatus("0");
		}
		return "redirect:/selArticleScreen";
	}
	
	
	
	
	
	
	/*模板*/

	@RequestMapping("/selBusiTemp")
	public String selBusiTemp(HttpServletRequest req){
		
		return "content/template_busi_list";
	}
	@RequestMapping(value ="/queryBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryBusiTemp(HttpServletRequest req,BusiTempVo vo){
		Message msg =createMsg();
		Page<BusiTemp> page =service.queryBusiTemp(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readBusiTemp")
	public String readBusiTemp(HttpServletRequest req,int id){
		BusiTemp bt =service.getBusiTemp(id);
		req.setAttribute("busitemp", Json(bt));
		return "content/busitemp_upd";
	}
	@RequestMapping(value ="/updBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updBusiTemp(HttpServletRequest req,BusiTemp bt){
		Message msg =createMsg();
		if(service.updBusiTemp(bt)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insBusiTemp")
	public String insBusiTemp(HttpServletRequest req){
	
		return "content/busitemp_add";	
	}
	@RequestMapping(value ="/addBusiTemp", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addBusiTemp(HttpServletRequest req,BusiTemp bt){
		Message msg =createMsg();
		service.addBusiTemp(bt);
		msg.setStatus("0");
		return Json(msg);
	}
	@RequestMapping("/delBusiTemp")
	public String delBusiTemp(HttpServletRequest req,int id){
		
		if(service.delBusiTemp(id)){
			
		}
		
		return "redirect:/selBusiTemp";
	}
	
	
	
	
	
//	
//	
//	public String selGoodsTemp(HttpServletRequest req){
//		
//		
//		return "content/template_goods_list";
//	}
//	
//	public String selCategoryTemp(HttpServletRequest req){
//		
//		return "content/template_category_list";
//	}
	
	
	
	
	
	/*活动*/

	//业务活动
	@RequestMapping("/selActBusiness")
	public String selActBusiness(HttpServletRequest req){
		
		return "content/activity_business_list";
	}
	@RequestMapping(value ="/queryActBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActBusiness(HttpServletRequest req,ActBusinessVo vo){
		Message msg =createMsg();
		Page<ActBusiness> page =service.queryActBusiness(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readActBusiness")
	public String readActBusiness(HttpServletRequest req,int id){
		ActBusiness ab =service.getActBusiness(id);
		req.setAttribute("actbusiness", Json(ab));
		return "content/activity_business_upd";
		
	}
	@RequestMapping(value ="/updActBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActBusiness(HttpServletRequest req,ActBusiness ab){
		Message msg =createMsg();
		ActBusiness a =service.getActBusiness(ab.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTBUSINESS);
		if(service.updActBusiness(ab)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActBusiness")
	public String insActBusiness(HttpServletRequest req){
		
		return "content/activity_business_add";
	}
	@RequestMapping(value ="/addActBusiness", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActBusiness(HttpServletRequest req,ActBusiness ab){
		Message msg =createMsg();
		
		service.addActBusiness(ab);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ab),LogRemark.ADDACTBUSINESS);
		return Json(msg);
		
	}
	@RequestMapping("/delActBusiness")
	public String delActBusiness(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActBusiness a =service.getActBusiness(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTBUSINESS);
		
		if(service.delActBusiness(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
//		return Json(msg);
		return "redirect:/selActBusiness";
		
	}
	
	//首页推荐活动
	@RequestMapping("/selActIndex")
	public String selActIndex(HttpServletRequest req){
		
		return "content/activity_index_list";
	}
	@RequestMapping(value ="/queryActIndex", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActIndex(HttpServletRequest req,ActIndexVo vo){
		Message msg =createMsg();
		Page<ActIndex> page =service.queryActIndex(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readActIndex")
	public String readActIndex(HttpServletRequest req,int id){
		ActIndex ai =service.getActIndex(id);
		req.setAttribute("actindex", Json(ai));
		return "content/activity_index_upd";
		
	}
	@RequestMapping(value ="/updActIndex", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActIndex(HttpServletRequest req,ActIndex ai){
		Message msg =createMsg();
		ActIndex a =service.getActIndex(ai.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTINDEX);
		
		if(service.updActIndex(ai)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActIndex")
	public String insActIndex(HttpServletRequest req){
		return "content/activity_index_add";
	}
	@RequestMapping(value ="/addActIndex", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActIndex(HttpServletRequest req,ActIndex ai){
		Message msg =createMsg();
		service.addActIndex(ai);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ai),LogRemark.ADDACTINDEX);
		return Json(msg);
	}
	@RequestMapping("/delActIndex")
	public String delActIndex(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ActIndex a =service.getActIndex(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTINDEX);
		
		if(service.delActIndex(id)){
			msg.setStatus("0");
			
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActIndex";
	}
	
	
	
	
	
	
	
//	首页推荐活动审核
	@RequestMapping("/selActIndUser")
	public String selActIndUser(HttpServletRequest req){
		
		return "content/activity_induser_list";
	}
	@RequestMapping(value ="/queryActIndUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActIndUser(HttpServletRequest req,ActIndUserVo vo){
		Message msg =createMsg();
		Page<ActIndUser> page =service.queryActIndUser(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/readActIndUser")
	public String readActIndUser(HttpServletRequest req,int id){
		ActIndUser aiu =service.getActIndUser(id);
		req.setAttribute("actinduser", Json(aiu));
		return "content/activity_induser_upd";
	}
	@RequestMapping(value ="/updActIndUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActIndUser(HttpServletRequest req,ActIndUser aiu){
		Message msg =createMsg();
		ActIndUser a =service.getActIndUser(aiu.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTINDUSER);
		
		if(service.updActIndUser(aiu)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActIndUser")
	public String insActIndUser(HttpServletRequest req){
		
		return "content/activity_induser_add";
		
	}
	@RequestMapping(value ="/addActIndUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActIndUser(HttpServletRequest req,ActIndUser aiu){
		Message msg =createMsg();
		service.addActIndUser(aiu);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(aiu),LogRemark.ADDACTINDUSER);
		return Json(msg);
	}
	@RequestMapping("/delActIndUser")
	public String delActIndUser(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ActIndUser a =service.getActIndUser(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTINDUSER);
		
		if(service.delActIndUser(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selActIndUser";
	}
	
	
	
	
//	普通活动
	@RequestMapping("/selActCommon")
	public String selActCommon(HttpServletRequest req){
		
		return "content/activity_common_list";
	}
	@RequestMapping(value ="/queryActCommon", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActCommon(HttpServletRequest req,ActCommonVo vo){
		Message msg =createMsg();
		Page<ActCommon> page =service.queryActCommon(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/readActCommon")
	public String readActCommon(HttpServletRequest req,int id){
		ActCommon ac =service.getActCommon(id);
		req.setAttribute("actcommon", Json(ac));
		return "content/activity_common_upd";
		
		
	}
	@RequestMapping(value ="/updActCommon", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActCommon(HttpServletRequest req,ActCommon ac){
		Message msg =createMsg();
		ActCommon a =service.getActCommon(ac.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTCOMMON);
		
		
		if(service.updActCommon(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
		
	}
	@RequestMapping("/insActCommon")
	public String insActCommon(HttpServletRequest req){
		
		return "content/activity_common_add";
		
	}
	@RequestMapping(value ="/addActCommon", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActCommon(HttpServletRequest req,ActCommon ac){
		Message msg =createMsg();
		service.addActCommon(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDACTCOMMON);
		return Json(msg);
		
	}
	@RequestMapping("/delActCommon")
	public String delActCommon(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ActCommon a =service.getActCommon(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTCOMMON);
		
		if(service.delActCommon(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActCommon";
	}
	
	
	
	
	
//	商城活动
	@RequestMapping("/selActShop")
	public String selActShop(HttpServletRequest req){
		
		return "content/activity_shop_list";
	}
	@RequestMapping(value ="/queryActShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActShop(HttpServletRequest req,ActShopVo vo){
		
		Message msg =createMsg();
		Page<ActShop> page =service.queryActShop(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readActShop")
	public String readActShop(HttpServletRequest req,int id){
		ActShop as =service.getActShop(id);
		req.setAttribute("actshop", Json(as));
		return "content/activity_shop_upd";
		
	}
	@RequestMapping(value ="/updActShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActShop(HttpServletRequest req,ActShop as){
		Message msg =createMsg();
		ActShop a =service.getActShop(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTSHOP);
		
		if(service.updActShop(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActShop")
	public String insActShop(HttpServletRequest req){
		
		return "content/activity_shop_add";
		
	}
	@RequestMapping(value ="/addActShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActShop(HttpServletRequest req,ActShop as){
		
		Message msg =createMsg();
		service.addActShop(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDACTSHOP);
		return Json(msg);
		
	}
	@RequestMapping("/delActShop")
	public String delActShop(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ActShop a =service.getActShop(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTSHOP);
		
		if(service.delActShop(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActShop";
	}
	
	
//	商城活动商品
	@RequestMapping("/selActShopGoods")
	public String selActShopGoods(HttpServletRequest req){
		
		return "content/activity_shopgoods_list";
		
	}
	@RequestMapping(value ="/queryActShopGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActShopGoods(HttpServletRequest req,ActShopGoodsVo vo){
		Message msg =createMsg();
		Page<ActShopGoods> page =service.queryActShopGoods(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/readActShopGoods")
	public String readActShopGoods(HttpServletRequest req,int id){
		ActShopGoods asg =service.getActShopGoods(id);
		req.setAttribute("actshopgoods", Json(asg));
		return "content/activity_shopgoods_upd";
		
	}
	@RequestMapping(value ="/updActShopGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActShopGoods(HttpServletRequest req,ActShopGoods asg){
		Message msg =createMsg();
		ActShopGoods a =service.getActShopGoods(asg.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTSHOPGOODS);
		if(service.updActShopGoods(asg)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActShopGoods")
	public String insActShopGoods(HttpServletRequest req){
		
		return "content/activity_shopgoods_add";
		
	}
	@RequestMapping(value ="/addActShopGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActShopGoods(HttpServletRequest req,ActShopGoods asg){
		Message msg =createMsg();
		asg.setUpdate_time(TimeUtils.getCurrentTime());
		service.addActShopGoods(asg);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(asg),LogRemark.ADDACTSHOPGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/delActShopGoods")
	public String delActShopGoods(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActShopGoods a =service.getActShopGoods(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTSHOPGOODS);
		
		if(service.delActShopGoods(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActShopGoods";
	}
	
	
	
	
	
//	短信活动
	@RequestMapping("/selActSms")
	public String selActSms(HttpServletRequest req){
		return "content/activity_sms_list";
	}
	@RequestMapping(value ="/queryActSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActSms(HttpServletRequest req,ActSmsVo vo){
		
		Message msg =createMsg();
		Page<ActSms> page =service.queryActSms(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/readActSms")
	public String readActSms(HttpServletRequest req,int id){
		
		ActSms as =service.getActSms(id);
		req.setAttribute("actsms", Json(as));
		return "content/activity_sms_upd";
		
	}
	@RequestMapping(value ="/updActSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActSms(HttpServletRequest req,ActSms as){
		Message msg =createMsg();
		
		ActSms a =service.getActSms(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTSMS);
		if(service.updActSms(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insActSms")
	public String insActSms(HttpServletRequest req){
		
		return "content/activity_sms_add";
		
	}
	@RequestMapping(value ="/addActSms", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActSms(HttpServletRequest req,ActSms as){
		Message msg =createMsg();
		service.addActSms(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDACTSMS);
		return Json(msg);
		
	}
	@RequestMapping("/delActSms")
	public String delActSms(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActSms a =service.getActSms(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTSMS);
		
		if(service.delActSms(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActSms";
	}
	
	
//	广告活动
	@RequestMapping("/selActAdvert")
	public String selActAdvert(HttpServletRequest req){
		return "content/activity_advert_list";
	}
	@RequestMapping(value ="/queryActAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActAdvert(HttpServletRequest req,ActAdvertVo vo){
		
		Message msg =createMsg();
		Page<ActAdvert> page =service.queryActAdvert(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readActAdvert")
	public String readActAdvert(HttpServletRequest req,int id){
		
		ActAdvert aa =service.getActAdvert(id);
		req.setAttribute("actadvert", Json(aa));
		return "content/activity_advert_upd";
	}
	@RequestMapping(value ="/updActAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActAdvert(HttpServletRequest req,ActAdvert aa){
		Message msg =createMsg();
		ActAdvert a =service.getActAdvert(aa.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTADVERT);
		
		if(service.updActAdvert(aa)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insActAdvert")
	public String insActAdvert(HttpServletRequest req){
		
		return "content/activity_advert_add";
	}
	@RequestMapping(value ="/addActAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActAdvert(HttpServletRequest req,ActAdvert aa){
		Message msg =createMsg();
		service.addActAdvert(aa);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(aa),LogRemark.ADDACTADVERT);
		return Json(msg);
		
	}
	@RequestMapping("/delActAdvert")
	public String delActAdvert(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActAdvert a =service.getActAdvert(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTADVERT);
		
		if(service.delActAdvert(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActAdvert";
		
	}
	
	
	
	
//	商家活动
	@RequestMapping("/selActBusShop")
	public String selActBusShop(HttpServletRequest req){
		return "content/activity_busshop_list";
	}
	@RequestMapping(value ="/queryActBusShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActBusShop(HttpServletRequest req,ActBusShopVo vo){
		
		Message msg =createMsg();
		Page<ActBusShop> page =service.queryActBusShop(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readActBusShop")
	public String readActBusShop(HttpServletRequest req,int id){
		ActBusShop abs =service.getActBusShop(id);
		req.setAttribute("actbusshop", Json(abs));
		return "content/activity_busshop_upd";
	}
	@RequestMapping(value ="/updActBusShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActBusShop(HttpServletRequest req,ActBusShop abs){
		Message msg =createMsg();
		
		ActBusShop a=service.getActBusShop(abs.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTBUSSHOP);
		
		if(service.updActBusShop(abs)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActBusShop")
	public String insActBusShop(HttpServletRequest req){
		
		return "content/activity_busshop_add";
	}
	@RequestMapping(value ="/addActBusShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActBusShop(HttpServletRequest req,ActBusShop abs){
		Message msg =createMsg();
		service.addActBusShop(abs);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(abs),LogRemark.ADDACTBUSSHOP);
		return Json(msg);
		
		
	}
	@RequestMapping("/delActBusShop")
	public String delActBusShop(HttpServletRequest req,int id){
		Message msg =createMsg();
		
		ActBusShop a=service.getActBusShop(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTBUSSHOP);
		
		if(service.delActBusShop(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActBusShop";
		
	}
	
	
	
	
//	商家活动商品
	@RequestMapping("/selActBusGoods")
	public String selActBusGoods(HttpServletRequest req){
		return "content/activity_busgoods_list";
	}
	@RequestMapping(value ="/queryActBusGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActBusGoods(HttpServletRequest req,ActBusGoodsVo vo){
		Message msg =createMsg();
		Page<ActBusGoods> page =service.queryActBusGoods(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/readActBusGoods")
	public String readActBusGoods(HttpServletRequest req,int id){
		ActBusGoods abg =service.getActBusGoods(id);
		req.setAttribute("actbusgoods", Json(abg));
		return "content/activity_busgoods_upd";
	}
	@RequestMapping(value ="/updActBusGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActBusGoods(HttpServletRequest req,ActBusGoods abg){
		Message msg =createMsg();
		ActBusGoods a =service.getActBusGoods(abg.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTBUSGOODS);
		if(service.updActBusGoods(abg)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/insActBusGoods")
	public String insActBusGoods(HttpServletRequest req){
		
		return "content/activity_busgoods_add";
		
	}
	@RequestMapping(value ="/addActBusGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActBusGoods(HttpServletRequest req,ActBusGoods abg){
		
		Message msg =createMsg();
		service.addActBusGoods(abg);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(abg),LogRemark.ADDACTBUSGOODS);
		return Json(msg);
		
	}
	@RequestMapping("/delActBusGoods")
	public String delActBusGoods(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActBusGoods a =service.getActBusGoods(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTBUSGOODS);
		
		if(service.delActBusGoods(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActBusGoods";
		
	}
	
	
	
//	充值首单返现
	@RequestMapping("/selActRecherge")
	public String selActRecherge(HttpServletRequest req){
		return "content/activity_recherge_list";
	}
	@RequestMapping(value ="/queryActRecherge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActRecherge(HttpServletRequest req,ActRechergeVo vo){
		
		Message msg =createMsg();
		Page<ActRecherge> page =service.queryActRecherge(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readActRecherge")
	public String readActRecherge(HttpServletRequest req,int id){
		
		ActRecherge ar =service.getActRecherge(id);
		req.setAttribute("actrecherge", Json(ar));
		return "content/activity_recherge_upd";
		
	}
	@RequestMapping(value ="/updActRecherge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActRecherge(HttpServletRequest req,ActRecherge ar){
		Message msg =createMsg();
		ActRecherge a =service.getActRecherge(ar.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTRECHERGE);
		
		if(service.updActRecherge(ar)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
		
	}
	@RequestMapping("/insActRecherge")
	public String insActRecherge(HttpServletRequest req){
		
		return "content/activity_recherge_add";
		
	}
	@RequestMapping(value ="/addActRecherge", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActRecherge(HttpServletRequest req,ActRecherge ar){
		Message msg =createMsg();
		service.addActRecherge(ar);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ar),LogRemark.ADDACTRECHERGE);
		return Json(msg);
		
	}
	@RequestMapping("/delActRecherge")
	public String delActRecherge(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActRecherge a =service.getActRecherge(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTRECHERGE);
		if(service.delActRecherge(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "redirect:/selActRecherge";
		
	}
//	充值首单返现参与用户表
	@RequestMapping("/selActRecUser")
	public String selActRecUser(HttpServletRequest req){
		return "content/activity_recuser_list";
	}
	@RequestMapping(value ="/queryActRecUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActRecUser(HttpServletRequest req,ActRecUserVo vo){
		
		Message msg =createMsg();
		Page<ActRecUser> page =service.queryActRecUser(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readActRecUser")
	public String readActRecUser(HttpServletRequest req,int id){
		
		ActRecUser aru =service.getActRecUser(id);
		req.setAttribute("actrecuser", Json(aru));
		return "content/activity_recuser_upd";
		
	}
	@RequestMapping(value ="/updActRecUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updActRecUser(HttpServletRequest req,ActRecUser aru){
		Message msg =createMsg();
		
		ActRecUser a =service.getActRecUser(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTRECUSER);
		
		if(service.updActRecUser(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insActRecUser")
	public String insActRecUser(HttpServletRequest req){
		
		return "content/activity_recuser_add";
	}
	@RequestMapping(value ="/addActRecUser", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addActRecUser(HttpServletRequest req,ActRecUser aru){
		Message msg =createMsg();
		service.addActRecUser(aru);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDACTRECUSER);
		return Json(msg);
	}
	@RequestMapping("/delActRecUser")
	public String delActRecUser(HttpServletRequest req,int id){
		Message msg =createMsg();
		ActRecUser a =service.getActRecUser(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELACTRECUSER);
		
		if(service.delActRecUser(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selActRecUser";
		
		
	}
	
	
	
	@RequestMapping("/selChannel")
	public String selChannel(HttpServletRequest req){
		return "content/channel_list";
	}
	@RequestMapping(value ="/queryChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryChannel(HttpServletRequest req,ChannelVo vo){
		
		Message msg =createMsg();
		Page<Channel> page =service.queryChannel(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readChannel")
	public String readChannel(HttpServletRequest req,int id){
		
		Channel aru =service.getChannel(id);
		req.setAttribute("channelinfo", Json(aru));
		return "content/channel_upd";
		
	}
	@RequestMapping(value ="/updChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updChannel(HttpServletRequest req,Channel aru){
		Message msg =createMsg();
		
		/*Channel a =service.getChannel(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDChannel);*/
		
		if(service.updChannel(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insChannel")
	public String insChannel(HttpServletRequest req){
		
		return "content/channel_add";
	}
	@RequestMapping(value ="/addChannel", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addChannel(HttpServletRequest req,Channel aru){
		Message msg =createMsg();
		service.addChannel(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDChannel);*/
		return Json(msg);
	}
	@RequestMapping("/delChannel")
	public String delChannel(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*Channel a =service.getChannel(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELChannel);
		*/
		if(service.delChannel(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selChannel";
		
		
	}
	
	
	
	@RequestMapping("/selChannelField")
	public String selChannelField(HttpServletRequest req){
		return "content/channel_field_list";
	}
	@RequestMapping(value ="/queryChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryChannelField(HttpServletRequest req,ChannelFieldVo vo){
		
		Message msg =createMsg();
		Page<ChannelField> page =service.queryChannelField(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readChannelField")
	public String readChannelField(HttpServletRequest req,int id){
		
		ChannelField aru =service.getChannelField(id);
		req.setAttribute("channelfield", Json(aru));
		return "content/channel_field_upd";
		
	}
	@RequestMapping(value ="/updChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updChannelField(HttpServletRequest req,ChannelField aru){
		Message msg =createMsg();
		
		/*ChannelField a =service.getChannelField(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDChannelField);*/
		
		if(service.updChannelField(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insChannelField")
	public String insChannelField(HttpServletRequest req){
		
		return "content/channel_field_add";
	}
	@RequestMapping(value ="/addChannelField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addChannelField(HttpServletRequest req,ChannelField aru){
		Message msg =createMsg();
		service.addChannelField(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDChannelField);*/
		return Json(msg);
	}
	@RequestMapping("/delChannelField")
	public String delChannelField(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ChannelField a =service.getChannelField(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELChannelField);*/
		
		if(service.delChannelField(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selChannelField";
		
		
	}
	
	
	
	@RequestMapping("/selChannelSpec")
	public String selChannelSpec(HttpServletRequest req){
		return "content/channel_spec_list";
	}
	@RequestMapping(value ="/queryChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryChannelSpec(HttpServletRequest req,ChannelSpecVo vo){
		
		Message msg =createMsg();
		Page<ChannelSpec> page =service.queryChannelSpec(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readChannelSpec")
	public String readChannelSpec(HttpServletRequest req,int id){
		
		ChannelSpec aru =service.getChannelSpec(id);
		req.setAttribute("channelspec", Json(aru));
		return "content/channel_spec_upd";
		
	}
	@RequestMapping(value ="/updChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updChannelSpec(HttpServletRequest req,ChannelSpec aru){
		Message msg =createMsg();
		
		/*ChannelSpec a =service.getChannelSpec(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDChannelSpec);*/
		
		if(service.updChannelSpec(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insChannelSpec")
	public String insChannelSpec(HttpServletRequest req){
		
		return "content/channel_spec_add";
	}
	@RequestMapping(value ="/addChannelSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addChannelSpec(HttpServletRequest req,ChannelSpec aru){
		Message msg =createMsg();
		service.addChannelSpec(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDChannelSpec);*/
		return Json(msg);
	}
	@RequestMapping("/delChannelSpec")
	public String delChannelSpec(HttpServletRequest req,int id){
		Message msg =createMsg();
		ChannelSpec a =service.getChannelSpec(id);
		/*saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELChannelSpec);*/
		
		if(service.delChannelSpec(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selChannelSpec";
		
		
	}
	
	
	
	@RequestMapping("/selChannelSite")
	public String selChannelSite(HttpServletRequest req){
		return "content/channel_site_list";
	}
	@RequestMapping(value ="/queryChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryChannelSite(HttpServletRequest req,ChannelSiteVo vo){
		
		Message msg =createMsg();
		Page<ChannelSite> page =service.queryChannelSite(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
	}
	@RequestMapping("/readChannelSite")
	public String readChannelSite(HttpServletRequest req,int id){
		
		ChannelSite aru =service.getChannelSite(id);
		req.setAttribute("channelsite", Json(aru));
		return "content/channel_site_upd";
		
	}
	@RequestMapping(value ="/updChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updChannelSite(HttpServletRequest req,ChannelSite aru){
		Message msg =createMsg();
		/*
		ChannelSite a =service.getChannelSite(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDChannelSite);*/
		
		if(service.updChannelSite(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insChannelSite")
	public String insChannelSite(HttpServletRequest req){
		
		return "content/channel_site_add";
	}
	@RequestMapping(value ="/addChannelSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addChannelSite(HttpServletRequest req,ChannelSite aru){
		Message msg =createMsg();
		service.addChannelSite(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDChannelSite);*/
		return Json(msg);
	}
	@RequestMapping("/delChannelSite")
	public String delChannelSite(HttpServletRequest req,int id){
		Message msg =createMsg();
		/*ChannelSite a =service.getChannelSite(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELChannelSite);*/
		
		if(service.delChannelSite(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selChannelSite";
		
		
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
	@RequestMapping(value ="/updArticleCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleCategory(HttpServletRequest req,ArticleCategory aru){
		Message msg =createMsg();
		
		/*ArticleCategory a =service.getArticleCategory(aru.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDArticleCategory);*/
		
		if(service.updArticleCategory(aru)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleCategory")
	public String insArticleCategory(HttpServletRequest req){
		
		return "content/article_category_add";
	}
	@RequestMapping(value ="/addArticleCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleCategory(HttpServletRequest req,ArticleCategory aru){
		Message msg =createMsg();
		service.addArticleCategory(aru);
		msg.setStatus("0");
		
		/*saveLog(createLog(req),LogRemark.ADD,Json(aru),LogRemark.ADDArticleCategory);*/
		return Json(msg);
	}
	@RequestMapping("/delArticleCategory")
	public String delArticleCategory(HttpServletRequest req,int id){
		Message msg =createMsg();
	/*	ArticleCategory a =service.getArticleCategory(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELArticleCategory);*/
		
		if(service.delArticleCategory(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
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
		req.setAttribute("ArticleCategoryCount", Json(aru));
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
	
	
	
}
