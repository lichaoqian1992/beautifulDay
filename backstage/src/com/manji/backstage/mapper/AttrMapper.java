package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.oper.Attri;
import com.manji.backstage.model.oper.Category;
@Resource
public interface AttrMapper {
	List<Attri> attriList(int category_id);

	List<Category> getCategoryList(String category_id);

	Attri getAttri(int category_id,String content);

	Attri getAttriById(int attri_id);

	Category getCategoryById(int category_id);

	List<Category> getCategorysByIds(Category cate);

	void addAttri(Attri attri);

	int updAttri(Attri attri);

	int delAttri(int attri_id);
	
	Attri getContentByCateId(int category_id);
}
