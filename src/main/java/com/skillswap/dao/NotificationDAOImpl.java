package com.skillswap.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.skillswap.model.Notifications;

@Repository
public class NotificationDAOImpl implements NotificationDAO{

	
	private SessionFactory sessionFactory;
	
	public NotificationDAOImpl(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	
	@Override
	public void saveNotification(Notifications notification) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(notification);
		
	}


	@Override
	public List<Notifications> searchBySender(Integer senderId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Notifications> query = session.createQuery(
				"SELECT n FROM Notifications n LEFT JOIN FETCH n.requests WHERE n.sender.id =:senderId",
				Notifications.class);
		
		query.setParameter("senderId", senderId);
		return query.getResultList();
	}


	

}
