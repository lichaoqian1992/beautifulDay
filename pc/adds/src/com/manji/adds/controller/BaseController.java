package com.manji.adds.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.manji.adds.model.Adds;
import com.manji.adds.model.AddsMsg;
import com.manji.adds.service.BaseService;
import com.manji.adds.utils.JsonUtils;
import com.manji.adds.utils.WebUrlUtils;

@Controller
public class BaseController {

	@Autowired
	private BaseService service;
	
	@RequestMapping("/test")
	public String fileRev(Adds adds, MultipartFile file){

		String ad_id ="201610310002";
		
		adds.setAd_id(ad_id);
		
		String pic_url ="http://localhost:8090/upload/"+ad_id+"/"+ad_id+".jpg";
		
		adds.setPic_url(pic_url);
		
		adds.setState("0");
		
//		service.addAdds(adds);
		System.out.println(JsonUtils.getJson(adds));
		
		
		 if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  
	                String filePath = "F:/pic/"+ad_id+"/"+ad_id+".jpg";
	                // 转存文件  
	                File picFile =new File(filePath);
	                picFile.mkdirs();
	                file.transferTo(picFile);  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            } 
		 }
		
		
		
//		AddsMsg msg =new AddsMsg();
//		
//		msg.setResult("添加成功");
//		
//		JsonUtils.getJson(msg);
		
		return "adds";
		
	}
	
	
	
	@RequestMapping("/test2")
	public String fileRev2(String title, @RequestParam MultipartFile[] files){

		String ad_id ="201610310003";
		
//		adds.setAd_id(ad_id);
		
		
//		System.out.println(JsonUtils.getJson(adds));
		
		if(null !=files){
			for(int i=0;i<files.length;i++){
				
				try {
					 	MultipartFile file =files[i];
						String myFileName = file.getOriginalFilename();  
						
						String picpath="F:/pic/"+ad_id+"/"+myFileName;
						
				        File targetFile = new File(picpath);  
				        if(!targetFile.exists()){  
				            targetFile.mkdirs();  
				        }  
				        
				        file.transferTo(targetFile);
				} catch (IllegalStateException | IOException e) {

					e.printStackTrace();
				}
			}
		}
		
		
		
//		AddsMsg msg =new AddsMsg();
//		
//		msg.setResult("添加成功");
//		
//		JsonUtils.getJson(msg);
		
		return "adds";
		
	}
	
	
	@RequestMapping(value = "/queryProvince", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	
	public String queryProvince(){
		
		AddsMsg msg =new AddsMsg();
		
		msg.setResult(service.getProvince());
		
		return JsonUtils.getJson(msg);
	}
	
	@RequestMapping(value = "/queryCity", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	
	public String queryCity(String code){
		
		AddsMsg msg =new AddsMsg();
		
		msg.setResult(service.getCity(code));
		
		return JsonUtils.getJson(msg);
	}
	
	@RequestMapping(value = "/queryCountry", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	
	public String queryCountry(String code){
		
		AddsMsg msg =new AddsMsg();
		
		msg.setResult(service.getCountry(code));
		
		return JsonUtils.getJson(msg);
	}
	
	
	
	
	
	/**
	 * 查询商家
	 * @param shopName
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/queryShop", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryShop(String shopName,String address){
		
		AddsMsg msg =new AddsMsg();
		
		String shopRequestUrl ="http://www.manji.com/tools/shop_ajax.ashx";
		
		Map<String, String> map =new HashMap<String,String>();
		
		
		map.put("action", "QueryShop");
		map.put("name", shopName);
		map.put("addr", address);
		
		String httpJson="";
		try {
			httpJson =WebUrlUtils.requestHttpJson(shopRequestUrl,map);
			
			
			msg.setResult(JsonUtils.tranTObject(httpJson));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			msg.setErrCode("1");
			msg.setErrInfo("查询商店信息失败");
		}
			
		return JsonUtils.getJson(msg);
		
	}
	
	/**
	 * 查询商品
	 * @param shopId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value = "/queryGoods", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryGoods(String shopId,String goodsName){
		
		
		
		AddsMsg msg =new AddsMsg();
		
		String shopRequestUrl ="http://www.manji.com/tools/goods_ajax.ashx";
		
		Map<String, String> map =new HashMap<String,String>();
		
		
		map.put("action", "QueryGoods");
		map.put("sid", shopId);
		map.put("name", goodsName);
		
		String httpJson="";
		try {
			httpJson =WebUrlUtils.requestHttpJson(shopRequestUrl,map);
			
			
			msg.setResult(JsonUtils.tranTObject(httpJson));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			msg.setErrCode("1");
			msg.setErrInfo("查询商店信息失败");
		}
			
		return JsonUtils.getJson(msg);
	}
	
	
}
