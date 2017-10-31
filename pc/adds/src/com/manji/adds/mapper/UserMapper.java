package com.manji.adds.mapper;

import java.util.List;
import java.util.Map;

import com.manji.adds.model.User;

public interface UserMapper {
	
	Map<String, String> checkUser(String username, String password);
	
	void insertUser(User user);
	
	boolean updUser(User user);
	
	boolean delUser(int id);
	
	List<User> getUsers();
	
	User getUserById(int id);
	
	
	
}
