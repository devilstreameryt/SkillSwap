package com.skillswap.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.skillswap.model.Request;


@Repository
public class RequestDAOImpl implements RequestDAO {

	private SessionFactory sessionFactory;
	
	public RequestDAOImpl(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	@Override
	public void saveRequest(Request request) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(request);
		
	}

	@Override
	public boolean existsBySenderAndReceiver(Integer senderId, Integer receiverId) {
		
		Session session = sessionFactory.getCurrentSession();
		Long count = session.createQuery(
			    "SELECT COUNT(r.id) FROM Request r WHERE r.sender.id = :senderId AND r.receiver.id = :receiverId",
			    Long.class)
			    .setParameter("senderId", senderId)
			    .setParameter("receiverId", receiverId)
			    .uniqueResult();

		
		
		return count != null && count > 0 ;
			
	}

	@Override
	public List<Request> findByReceiver(Integer receiverId) {
		Session session = sessionFactory.getCurrentSession();
		Query<Request> query = session.createQuery(
				"FROM Request r WHERE r.receiver.id =: receiverId",
				Request.class);
		
		query.setParameter("receiverId", receiverId);
		return query.getResultList();
	}

	@Override
	public Request getRequestById(int requestId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Request.class, requestId);
	} 

}
