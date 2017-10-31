package com.manji.backstage.mapper.login;

import java.util.List;

import javax.annotation.Resource;

import com.manji.backstage.model.login.User;
@Resource
public interface LoginMapper {

	
	User checkUser(String userName,String password);
	
	List<String> getAuth(String userName);
	
}
