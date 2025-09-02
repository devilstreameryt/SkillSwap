package com.skillswap.service;

import java.util.List;

import com.skillswap.model.Skill;
import com.skillswap.model.SkillsWanted;

public interface SkillService {
	
	public int getSkillsOfferedById(int id);  // total in Dashboard
	public int getSkillsWantedById(int id);  // total in Dashboard
	
	public List<Skill> findAll();
	public Skill getSkillByName(String skillName);
	public SkillsWanted addSkillWanted(int userId,String skill); // in profile
	public Skill searchById(Integer id);
	

}
