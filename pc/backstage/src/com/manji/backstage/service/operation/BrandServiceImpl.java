package com.manji.backstage.service.operation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.operation.BrandMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.operation.Brand;
import com.manji.backstage.utils.MD5util;
import com.manji.backstage.utils.PostUrlUtils;
import com.manji.backstage.vo.operation.BrandVo;

import net.sf.json.JSONObject;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper mapper;
	
	@Override
	public List<Brand> getBrandList() {
	
		return mapper.getBrandList();
	}

	@Override
	public void addBrand(Brand brand) {
		mapper.addBrand(brand);
	}

	@Override
	public boolean updBrand(Brand brand) {
		
		if("".equals(brand.getLogo())){
			return false;
		}
		
		if(mapper.updBrand(brand)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delBrand(String id) {
		
		
		if(mapper.delBrand(Integer.valueOf(id))>0){
			return true;
		}
		return false;
	}

	@Override
	public Brand getBrandById(int id) {
		return mapper.getBrandById(id);
	}

	@Override
	public List<Brand> queryBrandList(Brand brand) {
		return mapper.queryBrandList(brand);
	}

	@Override
	public String sendPostReq(String fileName, String base64Str)  {

		Map<String,String> map =new HashMap<String,String>();
		
		String url ="http://service.manji.com/AppService.asmx/UploadFile";
		
		long timestamp =System.currentTimeMillis();
		long current =timestamp/1000;
		
		String roundStr =current+base64Str+fileName+"asalt" ;
		
		String roundNumber=MD5util.getMD5(roundStr);
		
		try {
			base64Str =URLEncoder.encode(base64Str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		map.put("currentDate", current+"");
		map.put("roundNumber", roundNumber.toLowerCase());
		map.put("isResponseJson", "1");
		map.put("loginType", "Third");
		map.put("base64Str", base64Str);
		map.put("fileName", fileName);
		
		String html =PostUrlUtils.postHttpRequest(url,map);
			
		System.out.println(html);
		String path ="";
		JSONObject htmlObj =JSONObject.fromObject(html);
		if("1".equals(htmlObj.getString("State"))){
			
			path =htmlObj.getJSONObject("Datas").getString("Path");
			
		}
		
		
		
		return path;
		
	}

	@Override
	public boolean checkBrandName(String brandName) {
		if(mapper.checkBrandName(brandName)>1){
			return true;
		}
		return false;
	}

	@Override
	public Page<Brand> getBrand(BrandVo vo) {
		System.out.println(vo.getIndex());
		Page<Brand> page =new Page<Brand>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countBrand(vo);
		
		List<Brand> bandList =mapper.queryBrand(vo);
		
		page.transform(count, bandList);
		
		return page;
	}

}
