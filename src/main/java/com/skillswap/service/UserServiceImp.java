package com.skillswap.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.skillswap.dao.UserDAO;
import com.skillswap.model.Skill;
import com.skillswap.model.SkillsOffered;
import com.skillswap.model.SkillsWanted;
import com.skillswap.model.User;
import com.skillswap.model.UserDetails;


@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private SkillService skillService;
	
	
	
	
	public UserServiceImp() {}
	
	@Override
	@Transactional
	public void registerUser(User user) {
		if(!existsByEmail(user.getEmail())) {
			userDao.addUser(user);
			System.out.println("User registered successfully");
		}else {
			System.out.println("Email already exists!");
		}
		
		
	}
	
	@Override
	@Transactional
	public String getPasswordByEmail(String email) {
		return userDao.getPasswordByEmail(email);
	}


	@Override
	@Transactional
	public void removeUserById(int id) {
		userDao.removeUserById(id);
		
	}

	

	@Override
	@Transactional
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	

	@Override
	@Transactional
	public boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}


	@Override
	@Transactional
	public User findByEmail(String email) {
		User user =  userDao.findByEmail(email);
		 
		 user.getSkillsOffereds().size();
		 user.getSkillsWanteds().size();
		 return user;
	}

	@Override
	@Transactional
	public void save(User user) {
		userDao.saveUser(user);
		
	}

	
	@Override
	@Transactional
	public void updateUserProfile(User user, 
								  List<Integer> skillIds, 
								  List<String> skillLevels, 
								  MultipartFile file, 
								  String email, 
								  List<Integer> offeredSkillRowIds, 
								  List<Integer> wantedSkillIds,
								  List<String> wantedSkillRowIds
								  ) {
	
		
		User existingUser = userDao.findByEmail(email);
		if(existingUser == null) throw new RuntimeException("User not found");

		
		if(user.getFirstName() != null && !user.getFirstName().trim().isEmpty()) {
	        existingUser.setFirstName(user.getFirstName().trim());
	    }
	    
	    if(user.getLastName()!= null && !user.getLastName().trim().isEmpty()) {
	        existingUser.setLastName(user.getLastName().trim());
	    }
	    
	    if(existingUser.getUserDetails() == null) {
	        existingUser.setUserDetails(new UserDetails());
	        existingUser.getUserDetails().setUser(existingUser);
	    }
	    
	    if (user.getUserDetails() != null) {
	        existingUser.getUserDetails().setBio(user.getUserDetails().getBio());
	        existingUser.getUserDetails().setLocation(user.getUserDetails().getLocation());
	    }
	    
	    
	    if (file != null && !file.isEmpty()) {
	    	try {
	            existingUser.getUserDetails().setPhoto(file.getBytes());
	            existingUser.getUserDetails().setPhotoType(file.getContentType());
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to read uploaded file", e);
	        }
	    }
	    
	    
		
	    
	    List<SkillsOffered> existingSkills = existingUser.getSkillsOffereds();

	    for (int i = 0; i < skillIds.size(); i++) {
	        Integer skillId = skillIds.get(i);
	        String level = skillLevels.get(i);
	        Integer rowId = offeredSkillRowIds.get(i);

	        SkillsOffered offered = null;

	        if (rowId != null && rowId > 0) {
	            offered = existingSkills.stream()
	                    .filter(s -> s.getId() == (rowId))
	                    .findFirst()
	                    .orElse(null);
	        }

	        if (skillId != null && level != null && !level.isEmpty()) {
	            Skill skill = skillService.searchById(skillId);
	            if (offered != null) {
	                offered.setSkill(skill);
	                offered.setLevel(level);
	            } else {
	                offered = new SkillsOffered();
	                offered.setUser(existingUser);
	                offered.setSkill(skill);
	                offered.setLevel(level);
	                existingUser.getSkillsOffereds().add(offered);
	            }
	        } else {
	            if (offered != null) {
	                existingSkills.remove(offered);
	            }
	        }
	    }




	 
	 List<SkillsWanted> existingSkillsWanted = existingUser.getSkillsWanteds();
	 
	 if (wantedSkillIds == null) {
		    wantedSkillIds = new ArrayList<>();
		}
	 
	    for(int i=0; i<wantedSkillIds.size(); i++){
	        Integer skillId = wantedSkillIds.get(i);
	        String rowIdStr = (wantedSkillRowIds != null && wantedSkillRowIds.size() > i) ? wantedSkillRowIds.get(i) : null;
	        SkillsWanted wanted = null;

	        if(rowIdStr != null && !rowIdStr.isEmpty()){
	            Integer rowId = Integer.valueOf(rowIdStr);
	            wanted = existingSkillsWanted.stream().filter(s -> s.getId() == rowId).findFirst().orElse(null);
	        }

	        if(skillId != null){
	            Skill skill = skillService.searchById(skillId);
	            if(wanted != null){
	                wanted.setSkill(skill); 
	                } else {
	                wanted = new SkillsWanted();
	                wanted.setUser(existingUser);
	                wanted.setSkill(skill);
	                existingUser.getSkillsWanteds().add(wanted);
	            }
	        } else if(wanted != null){
	            existingSkillsWanted.remove(wanted); 
	        }
	    }
	    
	userDao.saveUser(existingUser);

    }


	@Override
	@Transactional
	public List<User> findUsersByOfferedSkill(Integer skillId) {
	    return userDao.findUsersByOfferedSkill(skillId);
	}

	
	
	
	
	
	

	
	
	
	
	


	

	
}
