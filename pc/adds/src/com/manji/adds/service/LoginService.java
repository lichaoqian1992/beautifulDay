package com.manji.adds.service;


import java.util.Map;



public interface LoginService {

	Map<String, String> checkUser(String username, String password);
	
	
}
