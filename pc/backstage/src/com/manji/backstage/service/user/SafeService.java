package com.manji.backstage.service.user;


import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.vo.user.VerifyVo;

public interface SafeService {
	
	
	Page<Verify> queryVerify(VerifyVo vo);

	void addVerify(Verify verify);

	Verify getVerify(int id);

	boolean updVerify(Verify verify);

	boolean delVerify(int id);
	
	
		

}
