package com.skillswap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.skillswap.model.Message;

@Repository
public class MessageDAOImpl implements MessageDAO{
	
	private SessionFactory sessionFactory;
	
	public MessageDAOImpl(SessionFactory session) {
		this.sessionFactory = session;
	}

	@Override
	public void addMessage(Message message) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(message);
		
	}

}
