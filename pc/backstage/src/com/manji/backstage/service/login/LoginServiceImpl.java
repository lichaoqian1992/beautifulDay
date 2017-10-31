package com.manji.backstage.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.datasource.DataSourceContextHolder;
import com.manji.backstage.datasource.DataSourceType;
import com.manji.backstage.mapper.login.LoginMapper;
import com.manji.backstage.model.login.User;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper mapper;

	@Override
	public User checkUser(String userName, String password) {
		DataSourceContextHolder.setDSType(DataSourceType.BGM);
	    User u =mapper.checkUser(userName, password);
	    DataSourceContextHolder.setDSType(DataSourceType.MANJI);
		
		return u;
		
	}


	@Override
	public List<String> getAuth(String userName) {

		List<String> authList =mapper.getAuth(userName);
		
		
		return authList;
	}
	
}
