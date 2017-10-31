package com.manji.data.service.business;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.data.repository.BusinessRespository;

import java.util.List;
import java.util.Map;

/**
 * 业务
 */
public class BusinessService {
	private BusinessRespository Brespository=new BusinessRespository();


	public Page<Record> businessLogData(int pageNumber,int pageSize,String businessName,String startTime,String endTime){
		return Brespository.businessLogData(pageNumber,pageSize,businessName,startTime,endTime);
	}

	public List<Record> articleCategoryOne(){
		return Brespository.articleCategoryOne();
	}

	public Page<Record> articleCategoryAll(int pageNumber,int pageSize,int levelOne,int levelTow,int levelThere){
		return Brespository.articleCategoryAll(pageNumber,pageSize,levelOne,levelTow,levelThere);
	}

	public List<Record> levelData(int screenId){
		return Brespository.levelData(screenId);
	}

	public List<Record> shopArticleAll(){
		return Brespository.shopArticleAll();
	}

	public Map<String, Object> shopInfoDetails(int userId){
		return Brespository.shopInfoDetails(userId);
	}

	public Map<String, Object> shopInfoData(int pageNumber,int pageSize,String userName,String nickName,String startTime,String endTime,String mobile,String shopName,String screen){
		return Brespository.shopInfoData(pageNumber,pageSize,userName,nickName,startTime,endTime,mobile,shopName,screen);
	}

}
