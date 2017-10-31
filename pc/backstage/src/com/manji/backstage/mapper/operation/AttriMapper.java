package com.manji.backstage.mapper.operation;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.operation.Attri;
import com.manji.backstage.model.operation.Category;

@Resource
public interface AttriMapper {

	List<Attri> attriList(int category_id);
	
	List<Category> getCategoryList(String category_id);
	
	Attri getAttri(int category_id);
	
	Attri getAttriById(int attri_id);
	
	Category getCategoryById(int category_id);
	
	List<Category> getCategorysByIds(Category cate);
	
	void addAttri(Attri attri);
	
	int updAttri(Attri attri);
	
	int delAttri(int attri_id);
	
}
