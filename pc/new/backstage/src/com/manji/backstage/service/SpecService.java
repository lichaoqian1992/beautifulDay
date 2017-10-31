package com.manji.backstage.service;

import java.util.List;

import com.manji.backstage.model.oper.Category;
import com.manji.backstage.model.oper.CategorySpec;
import com.manji.backstage.model.oper.Spec;

public interface SpecService {


	List<Category> getCategoryList(String id);
	
	List<Spec> getSpecs(int id);
	String findById(String id);
	List<Spec> getSpecs2(int id,String title);
	List<Spec> getSecondSpecs(int parent_id);
	
//	Map<String, String> getCategoryMap(int categoryId);
	
	boolean insertCategorySpec(int category_id,int spec_id);
	
	String getCategorySV(int category_id);
	
	boolean updSpecTitle(String id,String title,String img_url);
	
	int addMainSpec(Spec s);
	
	boolean addSecondSpec(Spec s);
	
	boolean delMainSpec(CategorySpec cs);
	
	boolean delSecondSpec(Spec s);
	
	boolean checkCate(int category_id);
	
	
}
