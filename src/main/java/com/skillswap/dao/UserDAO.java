package com.skillswap.dao;

import java.util.List;

import com.skillswap.model.User;

public interface UserDAO {
	
	public void addUser(User user); // sign up 
	public void removeUserById(int id); // delete
	public void saveUser(User user); // save User
	public User getUserById(int id); 
	public boolean existsByEmail(String email); // login 
	public String getPasswordByEmail(String email); // retrieve password
	public User findByEmail(String email);
	public List<User> findUsersByOfferedSkill(Integer skillId);
	
	
}
