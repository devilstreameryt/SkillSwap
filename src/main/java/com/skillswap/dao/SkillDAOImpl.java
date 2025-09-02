package com.skillswap.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.skillswap.model.Skill;
import com.skillswap.model.SkillsWanted;
import com.skillswap.model.User;


@Repository
public class SkillDAOImpl implements SkillDAO{
	
	
	private SessionFactory sessionFactory;
	
	public SkillDAOImpl(SessionFactory session)
	{
		this.sessionFactory = session;
	}
	
	@Override
	public int getSkillsOfferedById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Long> query = session.createQuery(
				
				"SELECT COUNT(s) FROM SkillsOffered s WHERE s.user.id =:userId", 
				Long.class);
		
		query.setParameter("userId", id);
		Long count = query.uniqueResult();
		return count != null ? count.intValue() : 0;
	}

	@Override
	public int getSkillsWantedById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Long> query = session.createQuery(
				"SELECT COUNT(s) FROM SkillsWanted s WHERE s.user.id =:userId",
				Long.class);
		 query.setParameter("userId",id);
		 Long count = query.uniqueResult();
		 return count != null ? count.intValue() : 0;
		
	}

	
	@Override
	public Skill getSkillByName(String skillName) {
	    Session session = sessionFactory.getCurrentSession();
	    Query<Skill> query = session.createQuery(
	            "FROM Skill s WHERE s.skillName = :skillName", Skill.class);
	    query.setParameter("skillName", skillName.trim().toLowerCase());
	    return query.uniqueResult(); 
	}

	
	@Override
	public SkillsWanted addSkillWanted(int userId, String skill) {
		Session session = sessionFactory.getCurrentSession();

	    User user = session.get(User.class, userId);
	    if (user == null) throw new RuntimeException("User not found with id: " + userId);

	    Skill skillEntity = getSkillByName(skill);
	    if (skillEntity == null) {
	        throw new RuntimeException("Skill not found in DB: " + skill);
	    }

	    SkillsWanted wanted = new SkillsWanted();
	    wanted.setUser(user);
	    wanted.setSkill(skillEntity);

	    session.persist(wanted);

	    return wanted;
	}

	
	@Override
	public List<Skill> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Skill> query = session.createQuery("FROM Skill",Skill.class);
		return query.getResultList();
	}

	@Override
	public Skill findById(Integer id) {
	    Session session = sessionFactory.getCurrentSession();
	    Skill skill = session.get(Skill.class, id);
	    if (skill == null) {
	        throw new RuntimeException("Skill not found with id: " + id);
	    }
	    return skill;
	}

	


	

	
	





}
