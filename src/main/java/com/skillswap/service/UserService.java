package com.skillswap.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.skillswap.model.User;

public interface UserService {
	
	
	
	public void registerUser(User user); // sign up 
	public void removeUserById(int id); // delete
	public void save(User user);
	
	public User getUserById(int id); 
	public boolean existsByEmail(String email); // login 
	public String getPasswordByEmail(String email); // retrieve password
	public User findByEmail(String email);

	public List<User> findUsersByOfferedSkill(Integer skillId);
	public void updateUserProfile(User user, List<Integer> skillIds, List<String> skillLevels, MultipartFile file, String email, List<Integer> offeredSkillRowIds, List<Integer> wantedSkillIds,List<String> wantedSkillRowIds);
	
	
	

}
