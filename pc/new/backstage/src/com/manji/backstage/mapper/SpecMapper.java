package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.oper.Category;
import com.manji.backstage.model.oper.CategorySpec;
import com.manji.backstage.model.oper.Spec;
@Resource
public interface SpecMapper {

	List<Category> getCategoryList(String id);
	
	List<Spec> getSpec(int id);
	List<Spec> getSpec2(Spec s);
	List<Spec> getSecondSpecs(int parent_id);
	String findById(String id);
	Category getCategoryById(int categoryId);
	
	List<Category> getCategorysByIds(Category cate);
	
	int addCategorySpec(CategorySpec cs);
	
	int updSepcTitle(Spec s);
	int updSepcTitleValue(Spec s);
	
	int addMainSpec(Spec s);
	
	int addSecondSpec(Spec s);
	
	int delMainSpec(CategorySpec cs);
	
	int delSecondSpec(Spec s);
	
	int checkCate(int category_id);
	
	
}
