package com.manji.backstage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.TempMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.service.TempService;
import com.manji.backstage.vo.content.BusiTempVo;
@Service
public class TempServiceImpl implements TempService {
	@Autowired
	private TempMapper mapper;
	
	
	@Override
	public Page<BusiTemp> queryBusiTemp(BusiTempVo vo) {
		Page<BusiTemp> page =new Page<BusiTemp>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<BusiTemp> dataList =mapper.queryBusiTemp(vo);
		int totalCount =mapper.countBusiTemp(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public BusiTemp getBusiTemp(int id) {
		return mapper.getBusiTemp(id);
	}

	@Override
	public boolean updBusiTemp(BusiTemp bt) {
		if(mapper.updBusiTemp(bt)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addBusiTemp(BusiTemp bt) {
		mapper.addBusiTemp(bt);
	}

	@Override
	public boolean delBusiTemp(int id) {
		if(mapper.delBusiTemp(id)>0){
			return true;
		}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
