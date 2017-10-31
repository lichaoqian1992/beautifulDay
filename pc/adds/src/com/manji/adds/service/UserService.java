package com.manji.adds.service;

import java.util.List;

import com.manji.adds.model.User;

public interface UserService {

	void addUser(User user);

	boolean updUser(User user);

	boolean delUser(int id);

	List<User> getAllUsers();

	User getUserById(int id);

}
