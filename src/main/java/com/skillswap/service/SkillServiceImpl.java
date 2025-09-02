package com.skillswap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillswap.dao.SkillDAO;
import com.skillswap.model.Skill;
import com.skillswap.model.SkillsWanted;

@Service
public class SkillServiceImpl implements SkillService{
	
	@Autowired
	private SkillDAO skillDao;
	
	
	public SkillServiceImpl() {}


	@Override
	@Transactional
	public int getSkillsOfferedById(int id) {
		return skillDao.getSkillsOfferedById(id);
	}


	@Override
	@Transactional
	public int getSkillsWantedById(int id) {
		return skillDao.getSkillsWantedById(id);
	}


	@Override
	@Transactional
	public List<Skill> findAll() {
		return skillDao.findAll();
	}


	@Override
	@Transactional
	public Skill getSkillByName(String skillName) {
		return skillDao.getSkillByName(skillName);
	}


	

	@Override
	@Transactional
	public SkillsWanted addSkillWanted(int userId, String skill) {
		return skillDao.addSkillWanted(userId, skill);
	}


	@Override
	@Transactional
	public Skill searchById(Integer id) {
		return skillDao.findById(id);
	}


	


	
	

	
	
	
	
	

}
