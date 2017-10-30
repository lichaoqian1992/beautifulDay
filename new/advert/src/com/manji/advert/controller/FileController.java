package com.manji.advert.controller;


import java.io.File;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.manji.advert.interceptor.LoginInterceptor;
import com.manji.advert.model.Result;
import com.manji.advert.model.param.FileParam;
import com.manji.advert.service.FileService;

/**
 * 文件上传管理
 * @author Administrator
 *
 */
@Before(LoginInterceptor.class)
public class FileController extends Controller{

	private FileService service =new FileService();
	
	
	/**
	 * 文件上传管理首页
	 */
	public void index(){
		
		
		render("file.html");
	}
	
	/**
	 * 查询图片
	 */
	public void query(){
		
		FileParam params =getBean(FileParam.class,"");
		
		Page<Record> page =service.getFilePage(params);
		
		Result r =new Result();
		
		if(page.getTotalRow()==0){
			
			r.setCode("0001");
			r.setData("查无数据");
		}else{
			r.setCode("0000");
			r.setData(page);
		}
		
		renderJson(r);
		
		
	}
	
	
	/**
	 * 跳转到上传图片页面
	 */
	public void add(){
		
		
		
		render("file_upload.html");
		
	}
	
	/**
	 * 上传图片
	 */
	public void upload(){
		
		System.out.println("+++++++++++++++++++++++++++++++");
		
		
		
		UploadFile uploadFile =getFile("uploadfiles");
		File file =uploadFile.getFile();
		String fileName =getPara("name");
		
		Result r =service.uploadFile(fileName,file);
		
		
		
		render("file_upload.html");
	}
	
	
	
	
	
	
	
}
