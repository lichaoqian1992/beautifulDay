package com.manji.backstage.service.operation;

import java.util.List;
import java.util.Map;

import com.manji.backstage.model.operation.Attri;
import com.manji.backstage.model.operation.Category;
import com.manji.backstage.vo.operation.CategoryAttriVo;

public interface AttriService {

	
	List<Attri> attriList(int category_id);
	
	List<Category> getCategoryList(String category_id);
	
	Attri getAttri(int category_id);
	
	Attri getAttriById(int attri_id);
	
//	Map<String,String> getCategoryMap(int category_id);
	
	String getCategoryCav(int category_id);
	
	void addAttri(int category_id,String content);
	
	boolean updAttri(int attri_id, String content);
	
	boolean delAttri(int attri_id);
	
}
