package com.manji.backstage.service.login;

import java.util.List;

import com.manji.backstage.model.login.User;

public interface LoginService {

	
	User checkUser(String userName, String password);
	
	
	
	
	List<String> getAuth(String userName);
	
}
