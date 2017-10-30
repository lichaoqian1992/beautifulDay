package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.oper.Attri;
import com.manji.backstage.model.oper.Category;

public interface AttrService {


	List<Attri> attriList(int category_id);
	
	List<Category> getCategoryList(String category_id);
	
	Attri getAttri(int category_id,String content);
	
	Attri getAttriById(int attri_id);
	
	Attri getContentByCateId(int category_id);
	
//	Map<String,String> getCategoryMap(int category_id);
	
	String getCategoryCav(int category_id);
	
	void addAttri(int category_id,String content);
	
	boolean updAttri(int attri_id, String content);
	
	boolean delAttri(int attri_id);
	
}
