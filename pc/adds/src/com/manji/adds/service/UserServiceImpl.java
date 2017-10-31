package com.manji.adds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.adds.mapper.UserMapper;
import com.manji.adds.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public void addUser(User user) {

		mapper.insertUser(user);
		
	}

	

	@Override
	public boolean delUser(int id) {
		
		return mapper.delUser(id);
	}

	@Override
	public List<User> getAllUsers() {
		
		return mapper.getUsers();
	}

	@Override
	public User getUserById(int id) {
		
		return mapper.getUserById(id);
	}



	@Override
	public boolean updUser(User user) {
		
		
		return mapper.updUser(user);
	}
	
	
}
