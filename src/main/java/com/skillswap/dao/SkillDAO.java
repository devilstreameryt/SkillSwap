package com.skillswap.dao;

import java.util.List;

import com.skillswap.model.Skill;
import com.skillswap.model.SkillsWanted;

public interface SkillDAO {

	
	public int getSkillsOfferedById(int id);  
	public int getSkillsWantedById(int id);  
	
	public List<Skill> findAll();
	public Skill getSkillByName(String skillName);
    public SkillsWanted addSkillWanted(int userId,String skill); 
	public Skill findById(Integer id);
}
