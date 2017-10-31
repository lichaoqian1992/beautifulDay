package com.manji.backstage.mapper.operation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.manji.backstage.model.operation.Brand;
import com.manji.backstage.vo.operation.BrandVo;

@Service
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
