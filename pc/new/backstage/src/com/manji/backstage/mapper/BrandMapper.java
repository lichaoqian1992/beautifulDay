package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.oper.Brand;
import com.manji.backstage.vo.operation.BrandVo;
@Resource
public interface BrandMapper {


	List<Brand> getBrandList();
	
	void addBrand(Brand brand);
	
	int updBrand(Brand brand);
	
	int delBrand(int id);
	
	Brand getBrandById(int id);
	
	List<Brand> queryBrandList(Brand brand);

	int checkBrandName(String brandName);

	int countBrand(BrandVo vo);

	List<Brand> queryBrand(BrandVo vo);
	
	
	
}
