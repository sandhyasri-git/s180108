package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.User;

public interface UserDAO {
	public boolean addUser(User user);
	
	public boolean deleteUser(User user);
	
	public User get(String email);
	
	public List<User>getAllUsers();
}
