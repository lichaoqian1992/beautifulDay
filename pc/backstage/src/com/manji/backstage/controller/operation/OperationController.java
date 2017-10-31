package com.manji.backstage.controller.operation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.manji.backstage.controller.base.BaseController;
import com.manji.backstage.controller.base.LogRemark;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.login.Data;
import com.manji.backstage.model.login.Loggers;
import com.manji.backstage.model.message.UserNotice;
import com.manji.backstage.model.operation.Advert;
import com.manji.backstage.model.operation.AdvertNavigation;
import com.manji.backstage.model.operation.ApoCity;
import com.manji.backstage.model.operation.ApoCounty;
import com.manji.backstage.model.operation.ApoProvince;
import com.manji.backstage.model.operation.ApoScreen;
import com.manji.backstage.model.operation.ApoSfz;
import com.manji.backstage.model.operation.ApoSite;
import com.manji.backstage.model.operation.ApoSiteContent;
import com.manji.backstage.model.operation.ApoTown;
import com.manji.backstage.model.operation.ApoVillage;
import com.manji.backstage.model.operation.AppVersion;
import com.manji.backstage.model.operation.Category;
import com.manji.backstage.model.operation.Files;
import com.manji.backstage.model.operation.Menu;
import com.manji.backstage.model.operation.Navigation;
import com.manji.backstage.model.operation.QrCode;
import com.manji.backstage.model.operation.Screen;
import com.manji.backstage.model.user.Group;
import com.manji.backstage.service.login.LoggersService;
import com.manji.backstage.service.operation.OperationService;
import com.manji.backstage.utils.Base64Utils;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.base.Message;
import com.manji.backstage.vo.operation.AdvertNavigationVo;
import com.manji.backstage.vo.operation.AdvertVo;
import com.manji.backstage.vo.operation.ApoCityVo;
import com.manji.backstage.vo.operation.ApoCountyVo;
import com.manji.backstage.vo.operation.ApoProvinceVo;
import com.manji.backstage.vo.operation.ApoScreenVo;
import com.manji.backstage.vo.operation.ApoSfzVo;
import com.manji.backstage.vo.operation.ApoSiteContentVo;
import com.manji.backstage.vo.operation.ApoSiteVo;
import com.manji.backstage.vo.operation.ApoTownVo;
import com.manji.backstage.vo.operation.ApoVillageVo;
import com.manji.backstage.vo.operation.AppVersionVo;
import com.manji.backstage.vo.operation.FilesVo;
import com.manji.backstage.vo.operation.MenuVo;
import com.manji.backstage.vo.operation.NavigationVo;
import com.manji.backstage.vo.operation.QrCodeVo;
import com.manji.backstage.vo.operation.ScreenVo;
@Controller
public class OperationController extends BaseController{

	@Autowired
	private OperationService service;
	@Autowired
	private LoggersService logService;


	public  void saveLog(Loggers log,String type,String json,String remark){
		Data d =logService.addData(json);
		
		System.out.println(d.getId()+"");
		log.setModule("operation");
		log.setType(type);
		log.setData(d.getId()+"");
		log.setRemark(remark);
		logService.addLoggers(log);
		
	}
	
	/**
	 * 显示商品分类信息
	 */
	@RequestMapping("/selCategory")
	public String selCategory(HttpServletRequest req){
		
		return "operation/operation_attri_list";
	}
	
	/**
	 * ajax--查询商品一级分类
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/firstCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String firstCategory(HttpServletRequest req){
		
		Message msg =createMsg();
		
		List<Category> cateList =service.getFirstCategory();
		
		msg.setResult(cateList);
		
		return Json(msg);
	}
	
	/**
	 * ajax--查询商品二级分类
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/secondCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String secondCategory(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String id =req.getParameter("id");
		
		List<Category> cateList =service.getSecondCategory(Integer.valueOf(id));
		
		
		msg.setResult(cateList);
		
		return Json(msg);
		
	}
	
	/**
	 * ajax--查询商品三级分类
	 * @param req
	 * @return
	 */
	@RequestMapping(value ="/thirdCategory", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String thirdCategory(HttpServletRequest req){
		
		Message msg =createMsg();
		
		String id =req.getParameter("id");
		
		List<Category> cateList =service.getThirdCategory(Integer.valueOf(id));
		
		msg.setResult(cateList);
		
		return Json(msg);
	}
	
	
	
	@RequestMapping("/selApoProvince")
	public String selApoProvince(HttpServletRequest req){
		
		return "operation/operation_apoprovince_list";
	}
	@RequestMapping(value ="/queryApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoProvince(HttpServletRequest req,ApoProvinceVo vo){
		Message msg =createMsg();
		
		Page<ApoProvince> page =service.queryApoProvince(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoProvince")
	public String insApoProvince(HttpServletRequest req){
		
		return "operation/operation_apoprovince_add";
	}
	@RequestMapping(value ="/addApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoProvince(HttpServletRequest req,ApoProvince av){
		Message msg =createMsg();
		service.addApoProvince(av);
		msg.setStatus("0");
		
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOPROVINCE);
		return Json(msg);
		
	}
	@RequestMapping("/readApoProvince")
	public String readApoProvince(HttpServletRequest req,String province_id){
		ApoProvince av =service.getApoProvince(province_id);
		req.setAttribute("apoprovinceinfo", Json(av));
		return "operation/operation_apoprovince_upd";
	}
	@RequestMapping(value ="/updApoProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoProvince(HttpServletRequest req,ApoProvince ap){
		Message msg =createMsg();
		ApoProvince av =service.getApoProvince(ap.getProvince_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOPROVINCE);
		if(service.updApoProvince(ap)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoProvince")
	public String delApoProvince(HttpServletRequest req ,String province_id){
		Message msg =createMsg();
		
		ApoProvince av =service.getApoProvince(province_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOPROVINCE);
		
		if(service.delApoProvince(province_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoCity")
	public String selApoCity(HttpServletRequest req){
		
		return "operation/operation_apocity_list";
	}
	@RequestMapping(value ="/queryApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoCity(HttpServletRequest req,ApoCityVo vo){
		Message msg =createMsg();
		
		Page<ApoCity> page =service.queryApoCity(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoCity")
	public String insApoCity(HttpServletRequest req){
		
		return "operation/operation_apocity_add";
	}
	@RequestMapping(value ="/addApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoCity(HttpServletRequest req,ApoCity ac){
		Message msg =createMsg();
		service.addApoCity(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDAPOCITY);
		return Json(msg);
		
	}
	@RequestMapping("/readApoCity")
	public String readApoCity(HttpServletRequest req,String city_id){
		ApoCity ac =service.getApoCity(city_id);
		req.setAttribute("apocityinfo", Json(ac));
		return "operation/operation_apocity_upd";
	}
	@RequestMapping(value ="/updApoCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoCity(HttpServletRequest req,ApoCity av){
		Message msg =createMsg();
		ApoCity ac =service.getApoCity(av.getCity_id());
		saveLog(createLog(req),LogRemark.UPD,Json(ac),LogRemark.UPDAPOCITY);
		
		if(service.updApoCity(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoCity")
	public String delApoCity(HttpServletRequest req ,String city_id){
		Message msg =createMsg();
		
		ApoCity ac =service.getApoCity(city_id);
		saveLog(createLog(req),LogRemark.DEL,Json(ac),LogRemark.DELAPOCITY);
		
		if(service.delApoCity(city_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoCounty")
	public String selApoCounty(HttpServletRequest req){
		
		return "operation/operation_apocounty_list";
	}
	@RequestMapping(value ="/queryApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoCounty(HttpServletRequest req,ApoCountyVo vo){
		Message msg =createMsg();
		
		Page<ApoCounty> page =service.queryApoCounty(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoCounty")
	public String insApoCounty(HttpServletRequest req){
		
		return "operation/operation_apocounty_add";
	}
	@RequestMapping(value ="/addApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoCounty(HttpServletRequest req,ApoCounty ac){
		Message msg =createMsg();
		service.addApoCounty(ac);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(ac),LogRemark.ADDAPOCOUNTY);
		return Json(msg);
		
	}
	@RequestMapping("/readApoCounty")
	public String readApoCounty(HttpServletRequest req,String county_id){
		ApoCounty av =service.getApoCounty(county_id);
		req.setAttribute("apocountyinfo", Json(av));
		return "operation/operation_apocounty_upd";
	}
	@RequestMapping(value ="/updApoCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoCounty(HttpServletRequest req,ApoCounty ac){
		Message msg =createMsg();
		ApoCounty av =service.getApoCounty(ac.getCounty_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOCOUNTY);
		
		if(service.updApoCounty(ac)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoCounty")
	public String delApoCounty(HttpServletRequest req ,String county_id){
		Message msg =createMsg();
		ApoCounty av =service.getApoCounty(county_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOCOUNTY);
		
		if(service.delApoCounty(county_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoTown")
	public String selApoTown(HttpServletRequest req){
		
		return "operation/operation_apotown_list";
	}
	@RequestMapping(value ="/queryApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoTown(HttpServletRequest req,ApoTownVo vo){
		Message msg =createMsg();
		
		Page<ApoTown> page =service.queryApoTown(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoTown")
	public String insApoTown(HttpServletRequest req){
		
		return "operation/operation_apotown_add";
	}
	@RequestMapping(value ="/addApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoTown(HttpServletRequest req,ApoTown at){
		Message msg =createMsg();
		service.addApoTown(at);
		msg.setStatus("0");
		saveLog(createLog(req),LogRemark.ADD,Json(at),LogRemark.ADDAPOTOWN);
		return Json(msg);
		
	}
	@RequestMapping("/readApoTown")
	public String readApoTown(HttpServletRequest req,String town_id){
		ApoTown av =service.getApoTown(town_id);
		req.setAttribute("apotowninfo", Json(av));
		return "operation/operation_apotown_upd";
	}
	@RequestMapping(value ="/updApoTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoTown(HttpServletRequest req,ApoTown at){
		Message msg =createMsg();
		ApoTown av =service.getApoTown(at.getTown_id());
		saveLog(createLog(req),LogRemark.UPD,Json(av),LogRemark.UPDAPOTOWN);
		if(service.updApoTown(at)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoTown")
	public String delApoTown(HttpServletRequest req ,String town_id){
		Message msg =createMsg();
		
		ApoTown av =service.getApoTown(town_id);
		saveLog(createLog(req),LogRemark.DEL,Json(av),LogRemark.DELAPOTOWN);
		if(service.delApoTown(town_id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoVillage")
	public String selApoVillage(HttpServletRequest req){
		
		return "operation/operation_apovillage_list";
	}
	@RequestMapping(value ="/queryApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoVillage(HttpServletRequest req,ApoVillageVo vo){
		Message msg =createMsg();
		
		Page<ApoVillage> page =service.queryApoVillage(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoVillage")
	public String insApoVillage(HttpServletRequest req){
		
		return "operation/operation_apovillage_add";
	}
	@RequestMapping(value ="/addApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoVillage(HttpServletRequest req,ApoVillage av){
		Message msg =createMsg();
		service.addApoVillage(av);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOVILLAGE);
		return Json(msg);
		
	}
	@RequestMapping("/readApoVillage")
	public String readApoVillage(HttpServletRequest req,String code){
		ApoVillage av =service.getApoVillage(code);
		req.setAttribute("apovillageinfo", Json(av));
		return "operation/operation_apovillage_upd";
	}
	@RequestMapping(value ="/updApoVillage", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoVillage(HttpServletRequest req,ApoVillage av){
		Message msg =createMsg();
		ApoVillage a =service.getApoVillage(av.getCode());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOVILLAGE);
		if(service.updApoVillage(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoVillage")
	public String delApoVillage(HttpServletRequest req ,String code){
		Message msg =createMsg();
		ApoVillage a =service.getApoVillage(code);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOVILLAGE);
		
		if(service.delApoVillage(code)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selAddress";
	}
	
	
	
	@RequestMapping("/selApoSfz")
	public String selApoSfz(HttpServletRequest req){
		
		return "operation/operation_aposfz_list";
	}
	@RequestMapping(value ="/queryApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoSfz(HttpServletRequest req,ApoSfzVo vo){
		Message msg =createMsg();
		
		Page<ApoSfz> page =service.queryApoSfz(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoSfz")
	public String insApoSfz(HttpServletRequest req){
		
		return "operation/operation_aposfz_add";
	}
	@RequestMapping(value ="/addApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoSfz(HttpServletRequest req,ApoSfz av){
		Message msg =createMsg();
		service.addApoSfz(av);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(av),LogRemark.ADDAPOSZF);
		return Json(msg);
		
	}
	@RequestMapping("/readApoSfz")
	public String readApoSfz(HttpServletRequest req,String code){
		ApoSfz av =service.getApoSfz(code);
		req.setAttribute("aposfzinfo", Json(av));
		return "operation/operation_aposfz_upd";
	}
	@RequestMapping(value ="/updApoSfz", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoSfz(HttpServletRequest req,ApoSfz av){
		Message msg =createMsg();
		ApoSfz a =service.getApoSfz(av.getCode());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSZF);
		if(service.updApoSfz(av)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
		
		
	}
	@RequestMapping("/delApoSfz")
	public String delApoSfz(HttpServletRequest req ,String code){
		Message msg =createMsg();
		ApoSfz a =service.getApoSfz(code);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSZF);
		if(service.delApoSfz(code)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selApoSfz";
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
	@RequestMapping(value ="/addMenu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addMenu(HttpServletRequest req,Menu menu){
		Message msg =createMsg();
		service.addMenu(menu);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(menu),LogRemark.ADDMENU);
		return Json(msg);
		
	}
	@RequestMapping("/readMenu")
	public String readMenu(HttpServletRequest req,int id){
		Menu menu =service.getMenu(id);
		req.setAttribute("menuinfo", Json(menu));
		return "operation/operation_menu_upd";
	}
	@RequestMapping(value ="/updMenu", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updMenu(HttpServletRequest req,Menu menu){
		Message msg =createMsg();
		Menu m =service.getMenu(menu.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(m),LogRemark.UPDMENU);
		if(service.updMenu(menu)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
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
	
	@RequestMapping("/selAdvertNavigation")
	public String selAdvertNavigation(HttpServletRequest req){
		
		return "operation/operation_advertnavigation_list";
	}
	@RequestMapping(value ="/queryAdvertNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryAdvertNavigation(HttpServletRequest req,AdvertNavigationVo vo){
		Message msg =createMsg();
		
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
		
		return "operation/operation_advertnavigation_add";
	}
	@RequestMapping(value ="/addAdvertNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdvertNavigation(HttpServletRequest req,AdvertNavigation an){
		Message msg =createMsg();
		service.addAdvertNavigation(an);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(an),LogRemark.ADDADVERTNAVIGATION);
		return Json(msg);
		
	}
	@RequestMapping("/readAdvertNavigation")
	public String readAdvertNavigation(HttpServletRequest req,int id){
		AdvertNavigation an =service.getAdvertNavigation(id);
		req.setAttribute("advertnavigationinfo", Json(an));
		return "operation/operation_advertnavigation_upd";
	}
	@RequestMapping(value ="/updAdvertNavigation", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updAdvertNavigation(HttpServletRequest req,AdvertNavigation an){
		Message msg =createMsg();
		AdvertNavigation a =service.getAdvertNavigation(an.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDADVERTNAVIGATION);
		if(service.updAdvertNavigation(an)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
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
	
//	站点
	@RequestMapping("/selApoSite")
	public String selApoSite(HttpServletRequest req){
		
		return "operation/operation_aposite_list";
	}
	@RequestMapping(value ="/queryApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoSite(HttpServletRequest req,ApoSiteVo vo){
		Message msg =createMsg();
		
		Page<ApoSite> page =service.queryApoSite(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoSite")
	public String insApoSite(HttpServletRequest req){
		
		return "operation/operation_aposite_add";
	}
	@RequestMapping(value ="/addApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoSite(HttpServletRequest req,ApoSite as){
		Message msg =createMsg();
		service.addApoSite(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDAPOSITE);
		return Json(msg);
		
	}
	@RequestMapping("/readApoSite")
	public String readApoSite(HttpServletRequest req,int id){
		ApoSite as =service.getApoSite(id);
		req.setAttribute("apositeinfo", Json(as));
		return "operation/operation_aposite_upd";
	}
	@RequestMapping(value ="/updApoSite", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoSite(HttpServletRequest req,ApoSite as){
		Message msg =createMsg();
		ApoSite a =service.getApoSite(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSITE);
		if(service.updApoSite(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delApoSite")
	public String delApoSite(HttpServletRequest req ,int id){
		Message msg =createMsg();
		ApoSite a =service.getApoSite(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSITE);
		if(service.delApoSite(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selApoSite";
	}
//	站点内容
	
	@RequestMapping("/selApoSiteContent")
	public String selApoSiteContent(HttpServletRequest req){
		
		return "operation/operation_apositecontent_list";
	}
	@RequestMapping(value ="/queryApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoSiteContent(HttpServletRequest req,ApoSiteContentVo vo){
		Message msg =createMsg();
		
		Page<ApoSiteContent> page =service.queryApoSiteContent(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoSiteContent")
	public String insApoSiteContent(HttpServletRequest req){
		
		return "operation/operation_apositecontent_add";
	}
	@RequestMapping(value ="/addApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoSiteContent(HttpServletRequest req,ApoSiteContent asc){
		Message msg =createMsg();
		service.addApoSiteContent(asc);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(asc),LogRemark.ADDAPOSITECONTENT);
		return Json(msg);
		
	}
	@RequestMapping("/readApoSiteContent")
	public String readApoSiteContent(HttpServletRequest req,int id){
		ApoSiteContent asc =service.getApoSiteContent(id);
		req.setAttribute("apositecontentinfo", Json(asc));
		return "operation/operation_apositecontent_upd";
	}
	@RequestMapping(value ="/updApoSiteContent", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoSiteContent(HttpServletRequest req,ApoSiteContent asc){
		Message msg =createMsg();
		ApoSiteContent a =service.getApoSiteContent(asc.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSITECONTENT);
		if(service.updApoSiteContent(asc)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delApoSiteContent")
	public String delApoSiteContent(HttpServletRequest req ,int id){
		Message msg =createMsg();
		ApoSiteContent a =service.getApoSiteContent(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSITECONTENT);
		if(service.delApoSiteContent(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selApoSiteContent";
	}
//	地区站点筛选
	@RequestMapping("/selApoScreen")
	public String selApoScreen(HttpServletRequest req){
		
		return "operation/operation_aposcreen_list";
	}
	@RequestMapping(value ="/queryApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryApoScreen(HttpServletRequest req,ApoScreenVo vo){
		Message msg =createMsg();
		
		Page<ApoScreen> page =service.queryApoScreen(vo);
		
		if(page.getTotalCount()!=0){
			msg.setStatus("0");
			msg.setResult(page);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	@RequestMapping("/insApoScreen")
	public String insApoScreen(HttpServletRequest req){
		
		return "operation/operation_aposcreen_add";
	}
	@RequestMapping(value ="/addApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addApoScreen(HttpServletRequest req,ApoScreen as){
		Message msg =createMsg();
		service.addApoScreen(as);
		msg.setStatus("0");
		
		saveLog(createLog(req),LogRemark.ADD,Json(as),LogRemark.ADDAPOSCREEN);
		return Json(msg);
		
	}
	@RequestMapping("/readApoScreen")
	public String readApoScreen(HttpServletRequest req,int id){
		ApoScreen as =service.getApoScreen(id);
		req.setAttribute("aposcreeninfo", Json(as));
		return "operation/operation_aposcreen_upd";
	}
	@RequestMapping(value ="/updApoScreen", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updApoScreen(HttpServletRequest req,ApoScreen as){
		Message msg =createMsg();
		ApoScreen a =service.getApoScreen(as.getId());
		saveLog(createLog(req),LogRemark.UPD,Json(a),LogRemark.UPDAPOSCREEN);
		if(service.updApoScreen(as)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return Json(msg);
	}
	@RequestMapping("/delApoScreen")
	public String delApoScreen(HttpServletRequest req ,int id){
		Message msg =createMsg();
		ApoScreen a =service.getApoScreen(id);
		saveLog(createLog(req),LogRemark.DEL,Json(a),LogRemark.DELAPOSCREEN);
		if(service.delApoScreen(id)){
			msg.setStatus("0");
		}else{
			msg.setStatus("1");
		}
		return "redirect:/selApoScreen";
	}
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
	
	
	
	@RequestMapping("/selAddress")
	public String selAddress(HttpServletRequest req){
		
		return "operation/operation_findcitybyprovince";
	}
	//显示所有省份信息
	@RequestMapping(value ="/findProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findProvince(HttpServletRequest req){
		Message msg =createMsg();
		List<ApoProvince> ap = service.findProvince();
		if(ap.size()!=0){
			msg.setStatus("0");
			msg.setResult(ap);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据province_id查询city_name
	@RequestMapping(value ="/findCityByProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findCityByProvince(String province_id) {
		Message msg = createMsg();
		List<ApoCity> ac = service.findCityByProvince(province_id);
		if(ac.size()!=0){
			msg.setStatus("0");
			msg.setResult(ac);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据city_id查询county_name
	@RequestMapping(value ="/findCountyByCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findCountyByCity(String city_id) {
		Message msg = createMsg();
		List<ApoCounty> ac = service.findCountyByCity(city_id);
		if(ac.size()!=0){
			msg.setStatus("0");
			msg.setResult(ac);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	//根据county_id查询town_name
	@RequestMapping(value ="/findTownByCounty", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findTownByCounty(String county_id) {
		Message msg = createMsg();
		List<ApoTown> at = service.findTownByCounty(county_id);
		if(at.size()!=0){
			msg.setStatus("0");
			msg.setResult(at);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据town_id查询village_name
	@RequestMapping(value ="/findVillageByTown", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String  findVillageByTown(String town_id) {
		Message msg = createMsg();
		List<ApoVillage> at = service.findVillageByTown(town_id);
		if(at.size()!=0){
			msg.setStatus("0");
			msg.setResult(at);
		}else{
			msg.setStatus("1");
		}
		
		return Json(msg);
	}
	
	//根据province_id查询city_name
		@RequestMapping(value ="/findCityByProvinceId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findCityByProvinceId(ApoCityVo vo) {
			Message msg = createMsg();
			Page<ApoCity> page = service.findCityByProvinceId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据city_id查询county_name
		@RequestMapping(value ="/findCountyByCityId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findCountyByCityId(ApoCountyVo vo) {
			Message msg = createMsg();
			Page<ApoCounty> page = service.findCountyByCityId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据county_id查询town_name
		@RequestMapping(value ="/findTownByCountyId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findTownByCountyId(ApoTownVo vo) {
			Message msg = createMsg();
			Page<ApoTown> page = service.findTownByCountyId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}
		
		//根据town_id查询village_name
		@RequestMapping(value ="/findVillageByTownId", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String  findVillageByTownId(ApoVillageVo vo) {
			Message msg = createMsg();
			Page<ApoVillage> page = service.findVillageByTownId(vo);
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
		}

	
		@RequestMapping("/selFiles")
		public String selFiles(HttpServletRequest req){
			
			return "operation/operation_files_list";
		}
		@RequestMapping(value ="/queryFiles", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String queryFiles(HttpServletRequest req,FilesVo vo){
			Message msg =createMsg();
			
			Page<Files> page =service.queryFiles(vo);
			
			if(page.getTotalCount()!=0){
				msg.setStatus("0");
				msg.setResult(page);
			}else{
				msg.setStatus("1");
			}
			
			return Json(msg);
		}
		@RequestMapping("/insFiles")
		public String insFiles(HttpServletRequest req){
			
			return "operation/operation_files_add";
		}
		@RequestMapping(value ="/addFiles", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String addFiles(HttpServletRequest req,Files files){
			Message msg =createMsg();
			files.setFile_uptime(TimeUtils.getCurrentTime());
			service.addFiles(files);
			msg.setStatus("0");
			
			saveLog(createLog(req),LogRemark.ADD,Json(files),LogRemark.ADDFILES);
			return Json(msg);
			
		}
		@RequestMapping("/readFiles")
		public String readFiles(HttpServletRequest req,int id){
			Files files =service.getFiles(id);
			req.setAttribute("filesinfo", Json(files));
			return "operation/operation_files_upd";
		}
		@RequestMapping(value ="/updFiles", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String updFiles(HttpServletRequest req,Files files){
			Message msg =createMsg();
			Files f =service.getFiles(files.getId());
			saveLog(createLog(req),LogRemark.UPD,Json(f),LogRemark.UPDFILES);
			if(service.updFiles(files)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return Json(msg);
			
			
		}
		@RequestMapping("/delFiles")
		public String delFiles(HttpServletRequest req ,int id){
			Message msg =createMsg();
			
			Files f =service.getFiles(id);
			saveLog(createLog(req),LogRemark.DEL,Json(f),LogRemark.DELFILES);
			if(service.delFiles(id)){
				msg.setStatus("0");
			}else{
				msg.setStatus("1");
			}
			return "redirect:/selFiles";
		}
		
		@RequestMapping(value ="/selFilesPie")
		
		public String countFilesGroup(HttpServletRequest req){
			List<Files> list= service.countFilesGroup();
			
			
			req.setAttribute("filepielist",Array(list));
			return "operation/operation_filepie_list"; 
		}
		
		
}
