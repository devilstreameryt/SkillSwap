package com.skillswap.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.skillswap.model.User;

@Repository
public class UserDAOImpl implements UserDAO{
	
	private SessionFactory sessionFactory;
	
	
	public UserDAOImpl(SessionFactory session) {
		this.sessionFactory = session;
	}
	

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
			session.save(user);
	}
	
	@Override
	public String getPasswordByEmail(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<String> query = session.createQuery("SELECT password FROM User WHERE email=:email",String.class);
		query.setParameter("email", email);
		
		try{
			return query.uniqueResult();
		}catch(NoResultException e)
			{
				return null;
			}
		}
	
	
	@Override
	public User findByEmail(String email) {
	
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("FROM User WHERE email=:email",User.class);
		query.setParameter("email",email);
		return query.uniqueResult();
		
	}
	

	@Override
	public void removeUserById(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		session.delete(user);
		
	}

	

	@Override
	public User getUserById(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    User user = session.get(User.class, id);
	    
	    
	    if (user.getSkillsWanteds() != null) user.getSkillsWanteds().size();
	    if (user.getSkillsOffereds() != null) user.getSkillsOffereds().size();
	    
	    return user;
	}


	

	@Override
	public boolean existsByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<String> query = session.createQuery("SELECT email FROM User WHERE email =:email",String.class);
		query.setParameter("email", email);
		
		String result = query.uniqueResult();
		
		if(result != null) {
			return true; // exists
		}else {
			return false ; // not exists
		}
		
	}

	
	@Override
	public List<User> findUsersByOfferedSkill(Integer skillId) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery(
				"SELECT u FROM User u JOIN u.skillsOffereds s WHERE s.skill.id = :skillId",
				User.class);
		query.setParameter("skillId", skillId);
		return query.getResultList();
	    
	}

	

	

	
	
	


	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
	}


	

	


	


	

}
