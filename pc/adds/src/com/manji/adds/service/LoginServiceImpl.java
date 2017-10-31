package com.manji.adds.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.UserMapper;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public Map<String, String> checkUser(String username, String password) {

		Map<String,String> userMap =mapper.checkUser(username, password);
		
		return userMap;
	}

	

}
