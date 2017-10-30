package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.SpecMapper;
import com.manji.backstage.model.oper.Category;
import com.manji.backstage.model.oper.CategorySpec;
import com.manji.backstage.model.oper.Spec;
import com.manji.backstage.service.SpecService;
import com.manji.backstage.vo.operation.CategorySpecVo;

import net.sf.json.JSONObject;

@Service
public class SpecServiceImpl implements SpecService {

	
	@Autowired
	private SpecMapper mapper;
	
	@Override
//	public List<Category> getCategoryList(int id) {
//
//		return mapper.getCategoryList(id);
//	}
	
	public List<Category> getCategoryList(String id) {

		boolean firstFlag =false;
		boolean secondFlag =false;
		List<Category> cateList =mapper.getCategoryList(id);
		Category category =mapper.getCategoryById(Integer.valueOf(id));
		
		for(Category cate: cateList){
			
			if(cate.getClass_layer()==1){
				firstFlag =true;
			}
			if(cate.getClass_layer()==2){
				secondFlag =true;
			}
			
		}
		
		if(!secondFlag){
			
			String idStr =category.getClass_list();
			String[] ids =idStr.split(",");
			Category firstCate =mapper.getCategoryById(Integer.valueOf(ids[1]));
			Category secondCate =mapper.getCategoryById(Integer.valueOf(ids[2]));
			
			cateList.add(0,secondCate);
			cateList.add(0,firstCate);
			
			firstFlag=true;
		}
		
		if(!firstFlag){
			String idStr =category.getClass_list();
			String[] ids =idStr.split(",");
			Category firstCate =mapper.getCategoryById(Integer.valueOf(ids[1]));
			cateList.add(0,firstCate);
		}
		
		return cateList;
	}

	

	@Override
	public List<Spec> getSpecs(int id) {
		return mapper.getSpec(id);
	}

	public List<Spec> getSpecs2(int id,String title){
		Spec s = new Spec();
		s.setId(id);
		s.setTitle(title);
		return mapper.getSpec2(s);
	}
	@Override
	public List<Spec> getSecondSpecs(int parent_id) {

		return mapper.getSecondSpecs(parent_id);
	}

	public String findById(String id){
		return mapper.findById(id);
	}
	@Override
	public String getCategorySV(int category_id){
		
		Category tempCate =mapper.getCategoryById(category_id);
		
		String layerStr =tempCate.getClass_list();
		
		String[] layer=layerStr.split(",");
		
		String idStr ="'"+layer[1]+"','"+layer[2]+"','"+layer[3]+"'";
		System.out.println(idStr);
		
		Category cates =new Category();
		cates.setTitle(idStr);
		List<Category> cateList =mapper.getCategorysByIds(cates);
		
		
		CategorySpecVo csv =new CategorySpecVo();
		for(int i=0;i<cateList.size();i++){
			Category cate =cateList.get(i);
			switch(cate.getClass_layer()){
			case 1:
				csv.setFirstName(cate.getTitle());
				break;
			case 2:
				csv.setSecondName(cate.getTitle());
				break;
			case 3:
				csv.setThirdName(cate.getTitle());
				break;
			}
			
		}
		
		return JSONObject.fromObject(csv).toString();
	}
	
//	@Override
//	public Map<String, String> getCategoryMap(int categoryId) {
//		
//		Map<String,String> cateMap =new HashMap<String,String>();
//		
//		Category tempCate =mapper.getCategoryById(categoryId);
//		
//		String layerStr =tempCate.getClass_list();
//		
//		String[] layer=layerStr.split(",");
//		
//		String idStr ="('"+layer[1]+"','"+layer[2]+"','"+layer[3]+"')";
//		
//		List<Category> cateList =mapper.getCategorysByIds(idStr);
//		
//		for(int i=0;i<cateList.size();i++){
//			
//			Category cate =cateList.get(i);
//			
//			switch(cate.getClass_layer()){
//			
//			case 1:
//				cateMap.put("firstCate", cate.getTitle());
//				break;
//			case 2:
//				cateMap.put("secondCate", cate.getTitle());
//				break;
//			case 3:
//				cateMap.put("thirdCate", cate.getTitle());
//				break;
//			}
//			
//			
//		}
//		
//		
//		
//		
//		return cateMap;
//	}



	@Override
	public boolean insertCategorySpec(int category_id, int spec_id) {
		
		CategorySpec cs =new CategorySpec();
		
		cs.setCategory_id(category_id);
		cs.setSpec_id(spec_id);
		
		if(mapper.addCategorySpec(cs)>0){
			return true;
		}
		
		return false;
	}



	@Override
	public boolean updSpecTitle(String id, String title,String img_url) {
		
		Spec s =new Spec();
		s.setId(Integer.valueOf(id));
		s.setTitle(title);
		s.setImg_url(img_url);
		if(mapper.updSepcTitle(s)>0){
			return true;
		}
			
		
		return false;
	}



	@Override
	public int addMainSpec(Spec s) {

		
		return mapper.addMainSpec(s);
	}



	@Override
	public boolean addSecondSpec(Spec s) {
		
		if(mapper.addSecondSpec(s)>0){
			return true;
		}
		
		return false;
	}



	@Override
	public boolean delMainSpec(CategorySpec cs) {

		if(mapper.delMainSpec(cs)>0){
			return true;
		}
		
		return false;
	}



	@Override
	public boolean delSecondSpec(Spec s) {
		
		if(mapper.delSecondSpec(s)>0){
			return true;
		}
		
		return false;
	}



	@Override
	public boolean checkCate(int category_id) {

		if(mapper.checkCate(category_id)>0){
			return true;
		}
		
		return false;
	}
	
}
