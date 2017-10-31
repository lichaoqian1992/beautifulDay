package com.manji.backstage.service.operation;

import java.util.List;
import java.util.Map;

import com.manji.backstage.model.operation.Category;
import com.manji.backstage.model.operation.CategorySpec;
import com.manji.backstage.model.operation.Spec;

public interface SpecService {

	
	List<Category> getCategoryList(String id);
	
	List<Spec> getSpecs(int id);
	
	List<Spec> getSecondSpecs(int parent_id);
	
//	Map<String, String> getCategoryMap(int categoryId);
	
	boolean insertCategorySpec(int category_id,int spec_id);
	
	String getCategorySV(int category_id);
	
	boolean updSpecTitle(String id,String title);
	
	int addMainSpec(Spec s);
	
	boolean addSecondSpec(Spec s);
	
	boolean delMainSpec(CategorySpec cs);
	
	boolean delSecondSpec(Spec s);
	
	boolean checkCate(int category_id);
	
}
