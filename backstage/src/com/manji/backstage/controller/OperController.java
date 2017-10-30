package com.manji.backstage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.manji.backstage.model.base.LogRemark;
import com.manji.backstage.model.base.Message;
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
import com.manji.backstage.model.content.ArticleComment;
import com.manji.backstage.model.content.ArticleContent;
import com.manji.backstage.model.content.ArticleCount;
import com.manji.backstage.model.content.ArticleGoods;
import com.manji.backstage.model.content.ArticleInfo;
import com.manji.backstage.model.content.ArticleScreen;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.oper.Advert;
import com.manji.backstage.model.oper.AdvertBanner;
import com.manji.backstage.model.oper.AdvertContent;
import com.manji.backstage.model.oper.AdvertNavigation;
import com.manji.backstage.model.oper.AppVersion;
import com.manji.backstage.model.oper.ArticleAttributeField;
import com.manji.backstage.model.oper.ArticleAttributeValue;
import com.manji.backstage.model.oper.ArticleGoodsGroupshop;
import com.manji.backstage.model.oper.ArticleGoodsSpec;
import com.manji.backstage.model.oper.ArticleGroupshop;
import com.manji.backstage.model.oper.ArticleTags;
import com.manji.backstage.model.oper.ArticleTagsRelation;
import com.manji.backstage.model.oper.ChannelTagsRelation;
import com.manji.backstage.model.oper.Menu;
import com.manji.backstage.model.oper.Navigation;
import com.manji.backstage.model.oper.QrCode;
import com.manji.backstage.model.oper.Screen;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.service.LoggersService;
import com.manji.backstage.service.OperService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.utils.TimeUtils;
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
import com.manji.backstage.vo.content.ArticleCommentVo;
import com.manji.backstage.vo.content.ArticleContentVo;
import com.manji.backstage.vo.content.ArticleCountVo;
import com.manji.backstage.vo.content.ArticleGoodsVo;
import com.manji.backstage.vo.content.ArticleInfoVo;
import com.manji.backstage.vo.content.ArticleScreenVo;
import com.manji.backstage.vo.content.ArticleVo;
import com.manji.backstage.vo.operation.AdvertBannerVo;
import com.manji.backstage.vo.operation.AdvertContentVo;
import com.manji.backstage.vo.operation.AdvertNavigationVo;
import com.manji.backstage.vo.operation.AdvertVo;
import com.manji.backstage.vo.operation.AppVersionVo;
import com.manji.backstage.vo.operation.ArticleAttributeFieldVo;
import com.manji.backstage.vo.operation.ArticleAttributeValueVo;
import com.manji.backstage.vo.operation.ArticleGoodsGroupshopVo;
import com.manji.backstage.vo.operation.ArticleGoodsSpecVo;
import com.manji.backstage.vo.operation.ArticleGroupshopVo;
import com.manji.backstage.vo.operation.ArticleTagsRelationVo;
import com.manji.backstage.vo.operation.ArticleTagsVo;
import com.manji.backstage.vo.operation.ChannelTagsRelationVo;
import com.manji.backstage.vo.operation.MenuVo;
import com.manji.backstage.vo.operation.NavigationVo;
import com.manji.backstage.vo.operation.QrCodeVo;
import com.manji.backstage.vo.operation.ScreenVo;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import net.sf.morph.transform.copiers.dsl.A;

@Controller
public class OperController extends BaseController{

	

	@Autowired
	private OperService service;
	@Autowired
	private ArtiService articService;
	@Autowired
	private LoggersService logService;
	
	public void saveLog(Loggers log, String type, String json, String remark) {
		Data d = logService.addData(json);

		System.out.println(d.getId() + "");
		log.setModule("oper");
		log.setType(type);
		log.setData(d.getId() + "");
		log.setRemark(remark);
		logService.addLoggers(log);

	}
	
	
	
	

	
	
	@RequestMapping("/selAppVersion")
	public String selAppVersion(HttpServletRequest req){
		
		return "operation/operation_apversion_list";
	}
	@RequestMapping(value ="/queryAppVersion", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAppVersion(HttpServletRequest req,AppVersionVo vo){
		Message msg =createMsg();
		
		Page<AppVersion> page =service.queryAppVersion(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insAppVersion")
	public String insAppVersion(HttpServletRequest req){
		
		return "operation/operation_apversion_add";
	}
	@RequestMapping(value ="/addAppVersion", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAppVersion(HttpServletRequest req,AppVersion av){
		Message msg =createMsg();
		service.addAppVersion(av);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPPVERSION);
		return Json(msg);
		
	}
	@RequestMapping("/readAppVersion")
	public String readAppVersion(HttpServletRequest req,int id){
		AppVersion av =service.getAppVersion(id);
		req.setAttribute("appversioninfo", Json(av));
		return "operation/operation_apversion_upd";
	}
	@RequestMapping(value ="/updAppVersion", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAppVersion(HttpServletRequest req,AppVersion av){
		Message msg =createMsg();
		AppVersion a =service.getAppVersion(av.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPPVERSION);
		if(service.updAppVersion(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delAppVersion")
	public String delAppVersion(HttpServletRequest req ,int id){
		Message msg =createMsg();
		AppVersion a =service.getAppVersion(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPPVERSION);
		if(service.delAppVersion(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAppVersion";
	}
	
	
	
	
	
	//二维码管理
	@RequestMapping("/selQrCode")
	public String selQrCode(HttpServletRequest req){
		
		return "operation/operation_qrcode_list";
	}
	@RequestMapping(value ="/queryQrCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryQrCode(HttpServletRequest req,QrCodeVo vo){
		Message msg =createMsg();
		
		Page<QrCode> page =service.queryQrCode(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insQrCode")
	public String insQrCode(HttpServletRequest req){
		
		return "operation/operation_qrcode_add";
	}
	@RequestMapping(value ="/addQrCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addQrCode(HttpServletRequest req,QrCode qc){
		Message msg =createMsg();
		service.addQrCode(qc);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(qc),LogRemark.ADDQRCODE);
		return Json(msg);
		
	}
	@RequestMapping("/readQrCode")
	public String readQrCode(HttpServletRequest req,int id){
		QrCode qc =service.getQrCode(id);
		req.setAttribute("qrcodeinfo", Json(qc));
		return "operation/operation_qrcode_upd";
	}
	@RequestMapping(value ="/updQrCode", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updQrCode(HttpServletRequest req,QrCode qc){
		Message msg =createMsg();
		QrCode q =service.getQrCode(qc.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(q),LogRemark.UPDQRCODE);
		if(service.updQrCode(qc)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delQrCode")
	public String delQrCode(HttpServletRequest req ,int id){
		Message msg =createMsg();
		QrCode q =service.getQrCode(id);
		saveLog(createLog(req),LogRemark.DEL,Json(q),LogRemark.DELQRCODE);
		if(service.delQrCode(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selQrCode";
	}
	
	
	//生成二维码
	@RequestMapping("/selMakeQrCode")
	public String selMakeQrCode(HttpServletRequest req){
		return "operation/operation_makeqrcode_list";
	}
	
	@RequestMapping("/makeQrCode")
	public void madeQrCode(HttpServletResponse res,String content,Integer width,Integer height) throws IOException{
		
		String format = "png";
		content = (content == null || "".equals(content) ? "http://www.manjiwang.com" : content);
		width = (width == null ? 200 : width);
		height = (height == null ? 200 : height);
		System.out.println(content);
		System.out.println(width);
		System.out.println(height);
		if(content != null && !"".equals(content)){
			ServletOutputStream stream = null;
		try {
			//定义二维码的参数
			HashMap<EncodeHintType,Object> hints = new HashMap<EncodeHintType,Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			hints.put(EncodeHintType.MARGIN, 2);
			//生成二维码
			stream = res.getOutputStream();
			BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height , hints);
			MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
		} catch (WriterException e) {
			e.printStackTrace();
		}finally{
			if(stream != null){
				stream.flush();
				stream.close();
			}
		}
	
	}
	}
	
	

	
	
//	菜单信息
	
	@RequestMapping("/selMenu")
	public String selMenu(HttpServletRequest req){
		
		return "operation/operation_menu_list";
	}
	@RequestMapping(value ="/queryMenu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryMenu(HttpServletRequest req,MenuVo vo){
		Message msg =createMsg();
		
		Page<Menu> page =service.queryMenu(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insMenu")
	public String insMenu(HttpServletRequest req){
		
		return "operation/operation_menu_add";
	}
	@RequestMapping(value ="/addMenu", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addMenu(HttpServletRequest req,Menu menu,@RequestParam MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			menu.setIcon(url);
		}else{
			menu.setIcon(req.getParameter("icon"));
		}
		service.addMenu(menu);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(menu),LogRemark.ADDMENU);
		return "operation/operation_menu_add";
		
	}
	@RequestMapping("/readMenu")
	public String readMenu(HttpServletRequest req,int id){
		Menu menu =service.getMenu(id);
		req.setAttribute("menuinfo", Json(menu));
		return "operation/operation_menu_upd";
	}
	@RequestMapping(value ="/updMenu", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")

	public String updMenu(HttpServletRequest req,Menu menu,@RequestParam MultipartFile file){
		Message msg =createMsg();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			menu.setIcon(url);
		}else{
			menu.setIcon(req.getParameter("imgUrl"));
		}
		Menu m =service.getMenu(menu.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMENU);
		if(service.updMenu(menu)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readMenu?id="+req.getParameter("id");
	}
	@RequestMapping("/delMenu")
	public String delMenu(HttpServletRequest req ,int id){
		Message msg =createMsg();
		Menu m =service.getMenu(id);
		saveLog(createLog(req),LogRemark.DEL,Json(m),LogRemark.DELMENU);
		if(service.delMenu(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selMenu";
	}
	
//	筛选信息
	@RequestMapping("/selScreen")
	public String selScreen(HttpServletRequest req){
		
		return "operation/operation_screen_list";
	}
	@RequestMapping(value ="/queryScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryScreen(HttpServletRequest req,ScreenVo vo){
		Message msg =createMsg();
		
		Page<Screen> page =service.queryScreen(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insScreen")
	public String insScreen(HttpServletRequest req){
		
		return "operation/operation_screen_add";
	}
	@RequestMapping(value ="/addScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addScreen(HttpServletRequest req,Screen screen){
		Message msg =createMsg();
		service.addScreen(screen);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(screen),LogRemark.ADDSCREEN);
		return Json(msg);
		
	}
	@RequestMapping("/readScreen")
	public String readScreen(HttpServletRequest req,int id){
		Screen screen =service.getScreen(id);
		req.setAttribute("screeninfo", Json(screen));
		return "operation/operation_screen_upd";
	}
	@RequestMapping(value ="/updScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updScreen(HttpServletRequest req,Screen screen){
		Message msg =createMsg();
		Screen s=service.getScreen(screen.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(s),LogRemark.UPDSCREEN);
		
		if(service.updScreen(screen)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delScreen")
	public String delScreen(HttpServletRequest req ,int id){
		Message msg =createMsg();
		
		Screen s=service.getScreen(id);
		saveLog(createLog(req),LogRemark.DEL,Json(s),LogRemark.DELSCREEN);
		if(service.delScreen(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selScreen";
	}
	
	

//	广告导航
	
	//根据parent_id获取title
	@RequestMapping(value ="/getTitle", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTitle(HttpServletRequest req,Integer parent_id){
		String an = service.getTitle(parent_id);
		System.out.println(an);
		return an;
	}
	
	//根据title模糊查询 
	@RequestMapping(value ="/getTitleId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTitleId(HttpServletRequest req,@Param(value="title") String title){
		/*String title = req.getParameter("title");*/
		List<AdvertNavigation> an = service.getTitleId(title);
		System.out.println("11111");
		System.out.println(an);
		Message msg = createMsg();
		msg.setResult(an);
		
		return Json(msg);
	}
	@RequestMapping("/selAdvertNavigation")
	public String selAdvertNavigation(HttpServletRequest req){
		
		return "operation/operation_advertnavigation_list";
	}
	@RequestMapping(value ="/queryAdvertNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdvertNavigation(HttpServletRequest req,AdvertNavigationVo vo){
		Message msg =createMsg();
		System.out.println(vo.getIndex());
		Page<AdvertNavigation> page =service.queryAdvertNavigation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insAdvertNavigation")
	public String insAdvertNavigation(HttpServletRequest req){
		List<AdvertNavigation> adn = service.getAdvertType();
		req.setAttribute("advertType",JSONArray.fromObject(adn));
		return "operation/operation_advertnavigation_add";
	}
	@RequestMapping(value ="/addAdvertNavigation", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addAdvertNavigation(HttpServletRequest req,AdvertNavigation an,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			an.setIcon(url);
		}else{
			an.setIcon(req.getParameter("imgUrl"));
		}
		an.setUpdate_time(TimeUtils.getCurrentTime());
		service.addAdvertNavigation(an);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(an),LogRemark.ADDADVERTNAVIGATION);
		return "redirect:/selAdvertNavigation";
		
	}
	@RequestMapping("/readAdvertNavigation")
	public String readAdvertNavigation(HttpServletRequest req,int id){
		AdvertNavigation an =service.getAdvertNavigation(id);
		req.setAttribute("advertnavigationinfo", Json(an));
		AdvertNavigation parent =service.getAdvertNavigation(an.getParent_id());
		req.setAttribute("parent", Json(parent));
		List<AdvertNavigation> adn = service.getAdvertType();
		req.setAttribute("advertType",JSONArray.fromObject(adn));
		return "operation/operation_advertnavigation_upd";
	}
	@RequestMapping(value ="/updAdvertNavigation", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updAdvertNavigation(HttpServletRequest req,AdvertNavigation an,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			String url = articService.sendPostReq(fileName, base64Str);
			an.setIcon(url);
		}else{
			an.setIcon(req.getParameter("imgUrl"));
		}
		an.setUpdate_time(TimeUtils.getCurrentTime());
		AdvertNavigation an1 = new AdvertNavigation();
		String ptitle = req.getParameter("ptitle");
		String pid = req.getParameter("parent_id");
		an1.setTitle(ptitle);
		an1.setId(Integer.parseInt(pid));
		AdvertNavigation a =service.getAdvertNavigation(an.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDADVERTNAVIGATION);
		if(service.updAdvertNavigation(an) && service.updAdvertNavigation2(an1)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/readAdvertNavigation?id="+an.getId();
	}
	@RequestMapping("/delAdvertNavigation")
	public String delAdvertNavigation(HttpServletRequest req ,int id){
		Message msg =createMsg();
		AdvertNavigation a =service.getAdvertNavigation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELADVERTNAVIGATION);
		if(service.delAdvertNavigation(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAdvertNavigation";
	}
	
//	广告位信息
	
	@RequestMapping("/selAdvert")
	public String selAdvert(HttpServletRequest req){
		
		return "operation/operation_advert_list";
	}
	@RequestMapping(value ="/queryAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdvert(HttpServletRequest req,AdvertVo vo){
		Message msg =createMsg();
		
		Page<Advert> page =service.queryAdvert(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insAdvert")
	public String insAdvert(HttpServletRequest req){
		
		return "operation/operation_advert_add";
	}
	@RequestMapping(value ="/addAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdvert(HttpServletRequest req,Advert advert){
		Message msg =createMsg();
		service.addAdvert(advert);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(advert),LogRemark.ADDADVERT);
		return Json(msg);
		
	}
	@RequestMapping("/readAdvert")
	public String readAdvert(HttpServletRequest req,int id){
		Advert advert =service.getAdvert(id);
		req.setAttribute("advertinfo", Json(advert));
		return "operation/operation_advert_upd";
	}
	@RequestMapping(value ="/updAdvert", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAdvert(HttpServletRequest req,Advert advert){
		Message msg =createMsg();
		
		Advert a =service.getAdvert(advert.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDADVERT);
		if(service.updAdvert(advert)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delAdvert")
	public String delAdvert(HttpServletRequest req ,int id){
		Message msg =createMsg();
		Advert a =service.getAdvert(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELADVERT);
		if(service.delAdvert(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAdvert";
	}
	
	
	///////////////////////////////////
	

	
//	系统管理菜单信息表
	
	@RequestMapping("/selNavigation")
	public String selNavigation(HttpServletRequest req){
		
		return "operation/operation_navigation_list";
	}
	@RequestMapping(value ="/queryNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryNavigation(HttpServletRequest req,NavigationVo vo){
		Message msg =createMsg();
		
		Page<Navigation> page =service.queryNavigation(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insNavigation")
	public String insNavigation(HttpServletRequest req){
		
		return "operation/operation_navigation_add";
	}
	@RequestMapping(value ="/addNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addNavigation(HttpServletRequest req,Navigation navigation){
		Message msg =createMsg();
		service.addNavigation(navigation);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(navigation),LogRemark.ADDNAVIGATION);
		return Json(msg);
		
	}
	@RequestMapping("/readNavigation")
	public String readNavigation(HttpServletRequest req,int id){
		Navigation navigation =service.getNavigation(id);
		req.setAttribute("navigationinfo", Json(navigation));
		return "operation/operation_navigation_upd";
	}
	@RequestMapping(value ="/updNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updNavigation(HttpServletRequest req,Navigation navigation){
		Message msg =createMsg();
		Navigation n =service.getNavigation(navigation.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(n),LogRemark.UPDNAVIGATION);
		if(service.updNavigation(navigation)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delNavigation")
	public String delNavigation(HttpServletRequest req ,int id){
		Message msg =createMsg();
		Navigation n =service.getNavigation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(n),LogRemark.DELNAVIGATION);
		if(service.delNavigation(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selNavigation";
	}
	
	
	
	
	
	
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
	@RequestMapping(value ="/updActBusiness", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActBusiness(HttpServletRequest req,ActBusiness ab,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ab.setPics(url);
		}else{
			ab.setPics(req.getParameter("icon"));
		}
		
		
		ActBusiness a =service.getActBusiness(ab.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTBUSINESS);
		if(service.updActBusiness(ab)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/activity_business_list";
		
	}
	@RequestMapping("/insActBusiness")
	public String insActBusiness(HttpServletRequest req){
		
		return "content/activity_business_add";
	}
	@RequestMapping(value ="/addActBusiness", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActBusiness(HttpServletRequest req,ActBusiness ab,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ab.setPics(url);
		}else{
			ab.setPics(req.getParameter("icon"));
		}
		
		
		service.addActBusiness(ab);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(ab),LogRemark.ADDACTBUSINESS);
		return "content/activity_business_list";
		
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
	@RequestMapping(value ="/updActIndUser", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActIndUser(HttpServletRequest req,ActIndUser aiu,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			aiu.setImage_url(url);
		}else{
			aiu.setImage_url(req.getParameter("icon"));
		}
		
		
		
		ActIndUser a =service.getActIndUser(aiu.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTINDUSER);
		
		if(service.updActIndUser(aiu)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/activity_induser_list";
		
	}
	@RequestMapping("/insActIndUser")
	public String insActIndUser(HttpServletRequest req){
		
		return "content/activity_induser_add";
		
	}
	@RequestMapping(value ="/addActIndUser", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActIndUser(HttpServletRequest req,ActIndUser aiu,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			aiu.setImage_url(url);
		}else{
			aiu.setImage_url(req.getParameter("icon"));
		}
		
		
		service.addActIndUser(aiu);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(aiu),LogRemark.ADDACTINDUSER);
		return "content/activity_induser_list";
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
	@RequestMapping(value ="/updActCommon", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActCommon(HttpServletRequest req,ActCommon ac,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ac.setPics(url);
		}else{
			ac.setPics(req.getParameter("icon"));
		}
		
		
		ActCommon a =service.getActCommon(ac.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTCOMMON);
		
		
		if(service.updActCommon(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "content/activity_common_list";
		
		
	}
	@RequestMapping("/insActCommon")
	public String insActCommon(HttpServletRequest req){
		
		return "content/activity_common_add";
		
	}
	@RequestMapping(value ="/addActCommon", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActCommon(HttpServletRequest req,ActCommon ac,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			ac.setPics(url);
		}else{
			ac.setPics(req.getParameter("icon"));
		}
		
		
		service.addActCommon(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDACTCOMMON);
		return "content/activity_common_list";
		
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
	@RequestMapping(value ="/updActShop", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActShop(HttpServletRequest req,ActShop as,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			as.setPics(url);
		}else{
			as.setPics(req.getParameter("icon"));
		}
		
		
		ActShop a =service.getActShop(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTSHOP);
		
		if(service.updActShop(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/activity_shop_list";
		
	}
	@RequestMapping("/insActShop")
	public String insActShop(HttpServletRequest req){
		
		return "content/activity_shop_add";
		
	}
	@RequestMapping(value ="/addActShop", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActShop(HttpServletRequest req,ActShop as,@RequestParam MultipartFile file){
		
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			as.setPics(url);
		}else{
			as.setPics(req.getParameter("icon"));
		}
		
		service.addActShop(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDACTSHOP);
		return "content/activity_shop_list";
		
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
	@RequestMapping(value ="/updActSms", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActSms(HttpServletRequest req,ActSms as,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			as.setPics(url);
		}else{
			as.setPics(req.getParameter("icon"));
		}
		
		
		ActSms a =service.getActSms(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTSMS);
		if(service.updActSms(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "content/activity_sms_list";
	}
	@RequestMapping("/insActSms")
	public String insActSms(HttpServletRequest req){
		
		return "content/activity_sms_add";
		
	}
	@RequestMapping(value ="/addActSms", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActSms(HttpServletRequest req,ActSms as,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			as.setPics(url);
		}else{
			as.setPics(req.getParameter("icon"));
		}
		
		service.addActSms(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDACTSMS);
		return "content/activity_sms_list";
		
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
	@RequestMapping(value ="/updActAdvert", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActAdvert(HttpServletRequest req,ActAdvert aa,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			aa.setPics(url);
		}else{
			aa.setPics(req.getParameter("icon"));
		}
		
		ActAdvert a =service.getActAdvert(aa.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTADVERT);
		
		if(service.updActAdvert(aa)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		
		return "content/activity_advert_list";
		
	}
	@RequestMapping("/insActAdvert")
	public String insActAdvert(HttpServletRequest req){
		
		return "content/activity_advert_add";
	}
	@RequestMapping(value ="/addActAdvert", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addActAdvert(HttpServletRequest req,ActAdvert aa,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			aa.setPics(url);
		}else{
			aa.setPics(req.getParameter("icon"));
		}
		
		service.addActAdvert(aa);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(aa),LogRemark.ADDACTADVERT);
		return "content/activity_advert_list";
		
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
	@RequestMapping(value ="/updActBusShop", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updActBusShop(HttpServletRequest req,ActBusShop abs,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			abs.setPics(url);
		}else{
			abs.setPics(req.getParameter("icon"));
		}
		
		ActBusShop a=service.getActBusShop(abs.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDACTBUSSHOP);
		
		if(service.updActBusShop(abs)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/activity_busshop_list";
		
	}
	@RequestMapping("/insActBusShop")
	public String insActBusShop(HttpServletRequest req){
		
		return "content/activity_busshop_add";
	}
	@RequestMapping(value ="/addActBusShop", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	public String addActBusShop(HttpServletRequest req,ActBusShop abs,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			abs.setPics(url);
		}else{
			abs.setPics(req.getParameter("icon"));
		}
		
		
		service.addActBusShop(abs);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(abs),LogRemark.ADDACTBUSSHOP);
		return "content/activity_busshop_list";
		
		
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

	
	//dt_advert_banner（广告位购买信息表）
	
	@RequestMapping("/selAdvertBanner")
	public String selAdvertBanner(HttpServletRequest req){
		
		return "content/advert_banner_list";
	}
	@RequestMapping(value ="/queryAdvertBanner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdvertBanner(HttpServletRequest req,AdvertBannerVo vo){
		Message msg =createMsg();
		
		Page<AdvertBanner> page =service.queryAdvertBanner(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insAdvertBanner")
	public String insAdvertBanner(HttpServletRequest req){
		
		return "content/advert_banner_add";
	}
	@RequestMapping(value ="/addAdvertBanner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdvertBanner(HttpServletRequest req,AdvertBanner og){
		Message msg =createMsg();
		service.addAdvertBanner(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDADVERTBANNER);
		return Json(msg);
		
	}
	@RequestMapping("/readAdvertBanner")
	public String readAdvertBanner(HttpServletRequest req,int id){
		AdvertBanner og =service.getAdvertBanner(id);
		req.setAttribute("advertbanner", Json(og));
		return "content/advert_banner_upd";
	}
	@RequestMapping(value ="/updAdvertBanner", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAdvertBanner(HttpServletRequest req,AdvertBanner og){
		Message msg =createMsg();
		AdvertBanner o =service.getAdvertBanner(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDADVERTBANNER);
		if(service.updAdvertBanner(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delAdvertBanner")
	public String delAdvertBanner(HttpServletRequest req,int id){
		
		AdvertBanner o =service.getAdvertBanner(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELADVERTBANNER);
		
		if(service.delAdvertBanner(id)){
			
		}
		return "redirect:/selAdvertBanner";
	}
	
	
	
	//dt_advert_content(广告位放置内容表)
	
	@RequestMapping("/selAdvertContent")
	public String selAdvertContent(HttpServletRequest req){
		
		return "content/advert_content_list";
	}
	@RequestMapping(value ="/queryAdvertContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdvertContent(HttpServletRequest req,AdvertContentVo vo){
		Message msg =createMsg();
		
		Page<AdvertContent> page =service.queryAdvertContent(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insAdvertContent")
	public String insAdvertContent(HttpServletRequest req){
		
		return "content/advert_content_add";
	}
	@RequestMapping(value ="/addAdvertContent", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addAdvertContent(HttpServletRequest req,AdvertContent og,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setFile_path(url);
		}else{
			og.setFile_path(req.getParameter("icon"));
		}
		
		service.addAdvertContent(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDADVERTCONTENT);
		return "content/advert_content_list";
		
	}
	@RequestMapping("/readAdvertContent")
	public String readAdvertContent(HttpServletRequest req,int id){
		AdvertContent og =service.getAdvertContent(id);
		req.setAttribute("advertcontent", Json(og));
		return "content/advert_content_upd";
	}
	@RequestMapping(value ="/updAdvertContent", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updAdvertContent(HttpServletRequest req,AdvertContent og,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setFile_path(url);
		}else{
			og.setFile_path(req.getParameter("icon"));
		}
		
		AdvertContent o =service.getAdvertContent(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDADVERTCONTENT);
		if(service.updAdvertContent(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/advert_content_list";
	}
	@RequestMapping("/delAdvertContent")
	public String delAdvertContent(HttpServletRequest req,int id){
		
		AdvertContent o =service.getAdvertContent(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELADVERTCONTENT);
		
		if(service.delAdvertContent(id)){
			
		}
		return "redirect:/selAdvertContent";
	}
	
	
	
	//dt_article_attribute_field（内容扩展属性表）
	
	@RequestMapping("/selArticleAttributeField")
	public String selArticleAttributeField(HttpServletRequest req){
		
		return "content/article_attributefield_list";
	}
	@RequestMapping(value ="/queryArticleAttributeField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleAttributeField(HttpServletRequest req,ArticleAttributeFieldVo vo){
		Message msg =createMsg();
		
		Page<ArticleAttributeField> page =service.queryArticleAttributeField(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleAttributeField")
	public String insArticleAttributeField(HttpServletRequest req){
		
		return "content/article_attributefield_add";
	}
	@RequestMapping(value ="/addArticleAttributeField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleAttributeField(HttpServletRequest req,ArticleAttributeField og){
		Message msg =createMsg();
		service.addArticleAttributeField(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDATTRFIELD);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleAttributeField")
	public String readArticleAttributeField(HttpServletRequest req,int id){
		ArticleAttributeField og =service.getArticleAttributeField(id);
		req.setAttribute("artfield", Json(og));
		return "content/article_attributefield_upd";
	}
	@RequestMapping(value ="/updArticleAttributeField", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleAttributeField(HttpServletRequest req,ArticleAttributeField og){
		Message msg =createMsg();
		ArticleAttributeField o =service.getArticleAttributeField(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDATTRFIELD);
		if(service.updArticleAttributeField(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleAttributeField")
	public String delArticleAttributeField(HttpServletRequest req,int id){
		
		ArticleAttributeField o =service.getArticleAttributeField(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELATTRFIELD);
		
		if(service.delArticleAttributeField(id)){
			
		}
		return "redirect:/selArticleAttributeField";
	}
	
	
	
	//dt_article_attribute_value（内容扩展属性对应值记录表*会动态更新）
	
	@RequestMapping("/selArticleAttributeValue")
	public String selArticleAttributeValue(HttpServletRequest req){
		
		return "content/article_attributevalue_list";
	}
	@RequestMapping(value ="/queryArticleAttributeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleAttributeValue(HttpServletRequest req,ArticleAttributeValueVo vo){
		Message msg =createMsg();
		
		Page<ArticleAttributeValue> page =service.queryArticleAttributeValue(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleAttributeValue")
	public String insArticleAttributeValue(HttpServletRequest req){
		
		return "content/article_attributevalue_add";
	}
	@RequestMapping(value ="/addArticleAttributeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleAttributeValue(HttpServletRequest req,ArticleAttributeValue og){
		Message msg =createMsg();
		service.addArticleAttributeValue(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDATTRVALUE);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleAttributeValue")
	public String readArticleAttributeValue(HttpServletRequest req,int article_id){
		ArticleAttributeValue og =service.getArticleAttributeValue(article_id);
		req.setAttribute("artvalue", Json(og));
		return "content/article_attributevalue_upd";
	}
	@RequestMapping(value ="/updArticleAttributeValue", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleAttributeValue(HttpServletRequest req,ArticleAttributeValue og){
		Message msg =createMsg();
		ArticleAttributeValue o =service.getArticleAttributeValue(og.getArticle_id());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDATTRVALUE);
		if(service.updArticleAttributeValue(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleAttributeValue")
	public String delArticleAttributeValue(HttpServletRequest req,int article_id){
		
		ArticleAttributeValue o =service.getArticleAttributeValue(article_id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELATTRVALUE);
		
		if(service.delArticleAttributeValue(article_id)){
			
		}
		return "redirect:/selArticleAttributeValue";
	}
	
	
	
	//dt_article_goods_spec（文章派生的商品类所选则规格信息表）
	
	@RequestMapping("/selArticleGoodsSpec")
	public String selArticleGoodsSpec(HttpServletRequest req){
		
		return "content/article_goodsspec_list";
	}
	@RequestMapping(value ="/queryArticleGoodsSpec", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleGoodsSpec(HttpServletRequest req,ArticleGoodsSpecVo vo){
		Message msg =createMsg();
		
		Page<ArticleGoodsSpec> page =service.queryArticleGoodsSpec(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleGoodsSpec")
	public String insArticleGoodsSpec(HttpServletRequest req){
		
		return "content/article_goodsspec_add";
	}
	@RequestMapping(value ="/addArticleGoodsSpec", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String addArticleGoodsSpec(HttpServletRequest req,ArticleGoodsSpec og,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setImg_url(url);
		}else{
			og.setImg_url(req.getParameter("icon"));
		}
		
		
		service.addArticleGoodsSpec(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDGOODSSPEC);
		return "content/article_goodsspec_list";
		
	}
	@RequestMapping("/readArticleGoodsSpec")
	public String readArticleGoodsSpec(HttpServletRequest req,int id){
		
		ArticleGoodsSpec og =service.getArticleGoodsSpec(id);
		req.setAttribute("goodsspec", Json(og));
		return "content/article_goodsspec_upd";
	}
	@RequestMapping(value ="/updArticleGoodsSpec", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
	
	public String updArticleGoodsSpec(HttpServletRequest req,ArticleGoodsSpec og,@RequestParam MultipartFile file){
		Message msg =createMsg();
		
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String base64Str = Base64Utils.GetBase64Code(file);
			//System.out.println(fileName+" "+base64Str);
			String url = articService.sendPostReq(fileName, base64Str);
			og.setImg_url(url);
		}else{
			og.setImg_url(req.getParameter("icon"));
		}
		
		
		ArticleGoodsSpec o =service.getArticleGoodsSpec(og.getArticle_id());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDGOODSSPEC);
		if(service.updArticleGoodsSpec(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "content/article_goodsspec_list";
	}
	@RequestMapping("/delArticleGoodsSpec")
	public String delArticleGoodsSpec(HttpServletRequest req,int id){
		
		ArticleGoodsSpec o =service.getArticleGoodsSpec(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELGOODSSPEC);
		
		if(service.delArticleGoodsSpec(id)){
			
		}
		return "redirect:/selArticleGoodsSpec";
	}
	
	
	
	//dt_article_groupshop（集团店批量操作记录表）
	
	@RequestMapping("/selArticleGroupshop")
	public String selArticleGroupshop(HttpServletRequest req){
		
		return "content/article_groupshop_list";
	}
	@RequestMapping(value ="/queryArticleGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleGroupshop(HttpServletRequest req,ArticleGroupshopVo vo){
		Message msg =createMsg();
		
		Page<ArticleGroupshop> page =service.queryArticleGroupshop(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleGroupshop")
	public String insArticleGroupshop(HttpServletRequest req){
		
		return "content/article_groupshop_add";
	}
	@RequestMapping(value ="/addArticleGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleGroupshop(HttpServletRequest req,ArticleGroupshop og){
		Message msg =createMsg();
		service.addArticleGroupshop(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDGROUPSHOP);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleGroupshop")
	public String readArticleGroupshop(HttpServletRequest req,int id){
		ArticleGroupshop og =service.getArticleGroupshop(id);
		req.setAttribute("groupshop", Json(og));
		return "content/article_groupshop_upd";
	}
	@RequestMapping(value ="/updArticleGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleGroupshop(HttpServletRequest req,ArticleGroupshop og){
		Message msg =createMsg();
		ArticleGroupshop o =service.getArticleGroupshop(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDGROUPSHOP);
		if(service.updArticleGroupshop(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleGroupshop")
	public String delArticleGroupshop(HttpServletRequest req,int id){
		
		ArticleGroupshop o =service.getArticleGroupshop(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELGROUPSHOP);
		
		if(service.delArticleGroupshop(id)){
			
		}
		return "redirect:/selArticleGroupshop";
	}
	
	
	
	//dt_article_goods_groupshop（集团店商品规格批量操作记录表）
	
	@RequestMapping("/selArticleGoodsGroupshop")
	public String selArticleGoodsGroupshop(HttpServletRequest req){
		
		return "content/article_goodsgroupshop_list";
	}
	@RequestMapping(value ="/queryArticleGoodsGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleGoodsGroupshop(HttpServletRequest req,ArticleGoodsGroupshopVo vo){
		Message msg =createMsg();
		
		Page<ArticleGoodsGroupshop> page =service.queryArticleGoodsGroupshop(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleGoodsGroupshop")
	public String insArticleGoodsGroupshop(HttpServletRequest req){
		
		return "content/article_goodsgroupshop_add";
	}
	@RequestMapping(value ="/addArticleGoodsGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleGoodsGroupshop(HttpServletRequest req,ArticleGoodsGroupshop og){
		Message msg =createMsg();
		service.addArticleGoodsGroupshop(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDGOODSGROUP);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleGoodsGroupshop")
	public String readArticleGoodsGroupshop(HttpServletRequest req,int id){
		ArticleGoodsGroupshop og =service.getArticleGoodsGroupshop(id);
		req.setAttribute("goodsgroup", Json(og));
		return "content/article_goodsgroupshop_upd";
	}
	@RequestMapping(value ="/updArticleGoodsGroupshop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleGoodsGroupshop(HttpServletRequest req,ArticleGoodsGroupshop og){
		Message msg =createMsg();
		ArticleGoodsGroupshop o =service.getArticleGoodsGroupshop(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDGOODSGROUP);
		if(service.updArticleGoodsGroupshop(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleGoodsGroupshop")
	public String delArticleGoodsGroupshop(HttpServletRequest req,int id){
		
		ArticleGoodsGroupshop o =service.getArticleGoodsGroupshop(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELGOODSGROUP);
		
		if(service.delArticleGoodsGroupshop(id)){
			
		}
		return "redirect:/selArticleGoodsGroupshop";
	}
	
	
	
	//dt_article_tags（热门文章TAG标签存储信息表）
	
	@RequestMapping("/selArticleTags")
	public String selArticleTags(HttpServletRequest req){
		
		return "content/article_tags_list";
	}
	@RequestMapping(value ="/queryArticleTags", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleTags(HttpServletRequest req,ArticleTagsVo vo){
		Message msg =createMsg();
		
		Page<ArticleTags> page =service.queryArticleTags(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleTags")
	public String insArticleTags(HttpServletRequest req){
		
		return "content/article_tags_add";
	}
	@RequestMapping(value ="/addArticleTags", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleTags(HttpServletRequest req,ArticleTags og){
		Message msg =createMsg();
		service.addArticleTags(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDARTICLETAGS);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleTags")
	public String readArticleTags(HttpServletRequest req,int id){
		ArticleTags og =service.getArticleTags(id);
		req.setAttribute("articletags", Json(og));
		return "content/article_tags_upd";
	}
	@RequestMapping(value ="/updArticleTags", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleTags(HttpServletRequest req,ArticleTags og){
		Message msg =createMsg();
		ArticleTags o =service.getArticleTags(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDARTICLETAGS);
		if(service.updArticleTags(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleTags")
	public String delArticleTags(HttpServletRequest req,int id){
		
		ArticleTags o =service.getArticleTags(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELARTICLETAGS);
		
		if(service.delArticleTags(id)){
			
		}
		return "redirect:/selArticleTags";
	}
	
	
	
	//dt_article_tags_relation（热门文章TAG标签对应关系信息表）
	
	@RequestMapping("/selArticleTagsRelation")
	public String selArticleTagsRelation(HttpServletRequest req){
		
		return "content/article_tagsrelation_list";
	}
	@RequestMapping(value ="/queryArticleTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryArticleTagsRelation(HttpServletRequest req,ArticleTagsRelationVo vo){
		Message msg =createMsg();
		
		Page<ArticleTagsRelation> page =service.queryArticleTagsRelation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insArticleTagsRelation")
	public String insArticleTagsRelation(HttpServletRequest req){
		
		return "content/article_tagsrelation_add";
	}
	@RequestMapping(value ="/addArticleTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addArticleTagsRelation(HttpServletRequest req,ArticleTagsRelation og){
		Message msg =createMsg();
		service.addArticleTagsRelation(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDTAGSRELATION);
		return Json(msg);
		
	}
	@RequestMapping("/readArticleTagsRelation")
	public String readArticleTagsRelation(HttpServletRequest req,int id){
		ArticleTagsRelation og =service.getArticleTagsRelation(id);
		req.setAttribute("tagsrelation", Json(og));
		return "content/article_tagsrelation_upd";
	}
	@RequestMapping(value ="/updArticleTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updArticleTagsRelation(HttpServletRequest req,ArticleTagsRelation og){
		Message msg =createMsg();
		ArticleTagsRelation o =service.getArticleTagsRelation(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDTAGSRELATION);
		if(service.updArticleTagsRelation(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delArticleTagsRelation")
	public String delArticleTagsRelation(HttpServletRequest req,int id){
		
		ArticleTagsRelation o =service.getArticleTagsRelation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELTAGSRELATION);
		
		if(service.delArticleTagsRelation(id)){
			
		}
		return "redirect:/selArticleTagsRelation";
	}
	
	
	
	//dt_channel_tags_relation（热门频道TAG标签对应关系信息表）
	
	@RequestMapping("/selChannelTagsRelation")
	public String selChannelTagsRelation(HttpServletRequest req){
		
		return "content/channel_tagsrelation_list";
	}
	@RequestMapping(value ="/queryChannelTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryChannelTagsRelation(HttpServletRequest req,ChannelTagsRelationVo vo){
		Message msg =createMsg();
		
		Page<ChannelTagsRelation> page =service.queryChannelTagsRelation(vo);
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/insChannelTagsRelation")
	public String insChannelTagsRelation(HttpServletRequest req){
		
		return "content/channel_tagsrelation_add";
	}
	@RequestMapping(value ="/addChannelTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addChannelTagsRelation(HttpServletRequest req,ChannelTagsRelation og){
		Message msg =createMsg();
		service.addChannelTagsRelation(og);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(og),LogRemark.ADDCHANNELTAGS);
		return Json(msg);
		
	}
	@RequestMapping("/readChannelTagsRelation")
	public String readChannelTagsRelation(HttpServletRequest req,int id){
		ChannelTagsRelation og =service.getChannelTagsRelation(id);
		req.setAttribute("channeltags", Json(og));
		return "content/channel_tagsrelation_upd";
	}
	@RequestMapping(value ="/updChannelTagsRelation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updChannelTagsRelation(HttpServletRequest req,ChannelTagsRelation og){
		Message msg =createMsg();
		ChannelTagsRelation o =service.getChannelTagsRelation(og.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(o),LogRemark.UPDCHANNELTAGS);
		if(service.updChannelTagsRelation(og)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delChannelTagsRelation")
	public String delChannelTagsRelation(HttpServletRequest req,int id){
		
		ChannelTagsRelation o =service.getChannelTagsRelation(id);
		saveLog(createLog(req),LogRemark.DEL,Json(o),LogRemark.DELCHANNELTAGS);
		
		if(service.delChannelTagsRelation(id)){
			
		}
		return "redirect:/selChannelTagsRelation";
	}
	
	
	
	
	
}
