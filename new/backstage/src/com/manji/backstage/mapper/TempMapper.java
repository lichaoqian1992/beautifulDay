package com.manji.backstage.mapper;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.vo.content.BusiTempVo;

@Resource
public interface TempMapper {


	List<BusiTemp> queryBusiTemp(BusiTempVo vo);

	int countBusiTemp(BusiTempVo vo);

	BusiTemp getBusiTemp(int id);

	int updBusiTemp(BusiTemp bt);

	void addBusiTemp(BusiTemp bt);

	int delBusiTemp(int id);
	
	
	
}
