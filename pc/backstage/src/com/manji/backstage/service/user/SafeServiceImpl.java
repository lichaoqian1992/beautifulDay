package com.manji.backstage.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.user.SafeMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.vo.user.VerifyVo;
@Service
public class SafeServiceImpl implements SafeService {
	@Autowired
	private SafeMapper mapper;
	
	@Override
	public Page<Verify> queryVerify(VerifyVo vo) {
		Page<Verify> page =new Page<Verify>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countVerify(vo);
		List<Verify> list =mapper.queryVerify(vo);
		
		page.transform(count, list);
		return page;
	}

	@Override
	public void addVerify(Verify verify) {
		mapper.addVerify(verify);
	}

	@Override
	public Verify getVerify(int id) {
		return mapper.getVerify(id);
	}

	@Override
	public boolean updVerify(Verify verify) {
		if(mapper.updVerify(verify)>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean delVerify(int id) {
		if(mapper.delVerify(id)>0){
			return true;
		}
		return false;
	}

	
	
}
