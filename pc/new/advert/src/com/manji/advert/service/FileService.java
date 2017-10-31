package com.manji.advert.service;


import java.io.File;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.Result;
import com.manji.advert.model.param.FileParam;
import com.manji.advert.utils.DatesUtils;
import com.manji.advert.utils.PicUtils;

public class FileService {

	public Page<Record> getFilePage(FileParam params) {
		
		StringBuffer sqlBuffer =new StringBuffer("from ad_file where 1=1");
		
		if(params.getFileName()!=null&&!"".equals(params.getFileName())){
			sqlBuffer.append("and file_name like '"+params.getFileName()+"%'");
		}
		
		if(params.getFileDate()!=null&&!"".equals(params.getFileDate())){
			sqlBuffer.append("and file_time like '"+params.getFileDate()+"%'");
		}
		
		sqlBuffer.append("order by id desc");
		Page<Record> page =Db.paginate(params.getIndex(), 20, "select *", sqlBuffer.toString());
		
		
		
		return page;
	}

	
	
	public Result uploadFile(String fileName,File file){
		
		Result r =new Result();
		
		String path =PicUtils.postPic(file);
		
		if("".equals(path)){
			r.setCode("0001");
			r.setData("图片上传失败");
		}else{
			String time =DatesUtils.time();
//			Record rec =new Record().set("file_name", fileName).set("file_address", path).set("file_time", time);
			Db.update("insert into ad_file(file_name,file_address,file_time) values(?,?,?)", fileName,path,time);
			r.setCode("0000");
			r.setData(path);
			
		}
		
		return r;
	}
	
	
	
	
	
	
}
