package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.AttrMapper;
import com.manji.backstage.model.oper.Attri;
import com.manji.backstage.model.oper.Category;
import com.manji.backstage.service.AttrService;
import com.manji.backstage.utils.TimeUtils;
import com.manji.backstage.vo.operation.CategoryAttriVo;

import net.sf.json.JSONObject;
@Service
public class AttrServiceImpl implements AttrService {

	@Autowired
	private AttrMapper mapper;

	@Override
	public List<Attri> attriList(int category_id) {

		return mapper.attriList(category_id);
	}

	@Override
//	public List<Category> getCategoryList(String category_id) {
//		
//		return mapper.getCategoryList(category_id);
//	}
	public List<Category> getCategoryList(String category_id) {
		
		boolean firstFlag =false;
		boolean secondFlag =false;
		
		List<Category> cateList =mapper.getCategoryList(category_id);
		
		Category category =mapper.getCategoryById(Integer.valueOf(category_id));
		
		
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
	public Attri getAttri(int category_id,String content) {

		return mapper.getAttri(category_id,content);
	}
	
	@Override
	public Attri getContentByCateId(int category_id) {
		
		return mapper.getContentByCateId(category_id);
	}

	@Override
	public Attri getAttriById(int attri_id) {

		return mapper.getAttriById(attri_id);
	}

//	@Override
//	public Map<String, String> getCategoryMap(int category_id) {
//
//		Map<String,String> cateMap =new HashMap<String,String>();
//		
//		Category tempCate =mapper.getCategoryById(category_id);
//		
//		String layerStr =tempCate.getClass_list();
//		
//		String[] layer=layerStr.split(",");
//		
//		String idStr ="'"+layer[1]+"','"+layer[2]+"','"+layer[3]+"'";
//		System.out.println(idStr);
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
//	
//	}
	
	@Override
	public String getCategoryCav(int category_id){
		Category tempCate =mapper.getCategoryById(category_id);
		
		String layerStr =tempCate.getClass_list();
		
		String[] layer=layerStr.split(",");
		
		String idStr ="'"+layer[1]+"','"+layer[2]+"','"+layer[3]+"'";
		System.out.println(idStr);
		Category cates =new Category();
		cates.setTitle(idStr);
		List<Category> cateList =mapper.getCategorysByIds(cates);
		CategoryAttriVo cav =new CategoryAttriVo(); 
		for(int i=0;i<cateList.size();i++){
			
			Category cate =cateList.get(i);
			
			switch(cate.getClass_layer()){
			
			case 1:
				cav.setFirst_name(cate.getTitle());
				break;
			case 2:
				cav.setSecond_name(cate.getTitle());
				break;
			case 3:
				cav.setThird_name(cate.getTitle());
				break;
			}
			
		}
		return JSONObject.fromObject(cav).toString();
	}
	

	@Override
	public void addAttri(int category_id, String content) {

		Attri attri =new Attri();
		
		attri.setChannel_id(7);
		/*attri.setStatus();*/
		attri.setCategory_id(category_id);
		String addTime =TimeUtils.getCurrentTime();
		attri.setUpdate_time(addTime);
		attri.setAdd_time(addTime);
		attri.setContent(content);
		attri.setRemark("");
		mapper.addAttri(attri);
		
		
	}

	@Override
	public boolean updAttri(int attri_id, String content) {
		
		Attri attri =new Attri();
		attri.setId(attri_id);
		attri.setContent(content);
		attri.setUpdate_time(TimeUtils.getCurrentTime());
		
		if(mapper.updAttri(attri)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delAttri(int attri_id) {

		if(mapper.delAttri(attri_id)>0){
			return true;
		}
		
		return false;
	}
}
