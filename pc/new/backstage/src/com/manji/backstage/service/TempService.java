package com.manji.backstage.service;

import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.vo.content.BusiTempVo;

public interface TempService {

	
	Page<BusiTemp> queryBusiTemp(BusiTempVo vo);

	BusiTemp getBusiTemp(int id);

	boolean updBusiTemp(BusiTemp bt);

	void addBusiTemp(BusiTemp bt);

	boolean delBusiTemp(int id);
	
	
	
}
