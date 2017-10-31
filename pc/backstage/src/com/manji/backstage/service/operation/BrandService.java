package com.manji.backstage.service.operation;

import java.util.List;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.operation.Brand;
import com.manji.backstage.vo.operation.BrandVo;

public interface BrandService {

	List<Brand> getBrandList();
	
	void addBrand(Brand brand);
	
	boolean updBrand(Brand brand);
	
	boolean delBrand(String id);
	
	Brand getBrandById(int id);
	
	List<Brand> queryBrandList(Brand brand);
	
	String sendPostReq(String fileName,String base64Str);

	boolean checkBrandName(String brandName);

	Page<Brand> getBrand(BrandVo vo);
}
