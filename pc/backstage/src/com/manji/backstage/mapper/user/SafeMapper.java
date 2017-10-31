package com.manji.backstage.mapper.user;

import java.util.List;

import com.manji.backstage.dto.user.VerifyDto;
import com.manji.backstage.model.user.PersonInfo;
import com.manji.backstage.model.user.RecAddr;
import com.manji.backstage.model.user.User;
import com.manji.backstage.model.user.Verify;
import com.manji.backstage.vo.user.RecAddrVo;
import com.manji.backstage.vo.user.VerifyVo;

public interface SafeMapper {

	
	int countVerify(VerifyVo vo);

	List<Verify> queryVerify(VerifyVo vo);

	void addVerify(Verify verify);

	Verify getVerify(int id);

	int updVerify(Verify verify);

	int delVerify(int id);
	
	
}
