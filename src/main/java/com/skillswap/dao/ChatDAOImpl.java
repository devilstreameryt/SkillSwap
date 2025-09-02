package com.skillswap.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.skillswap.model.Chat;
import com.skillswap.model.User;

@Repository
public class ChatDAOImpl implements ChatDAO {
	
	
	private SessionFactory sessionFactory;
	
	public ChatDAOImpl(SessionFactory session) {
		this.sessionFactory  = session;
	}
	

	@Override
	public Chat findByUsers(User sender, User receiver) {
		Session session = sessionFactory.getCurrentSession();
		Query<Chat> query = session.createQuery(
			    "FROM Chat c WHERE " +
			    "(c.userSender.id = :sender AND c.userReceiver.id = :receiver) " +
			    "OR " +
			    "(c.userSender.id = :receiver AND c.userReceiver.id = :sender)",
			    Chat.class
			);

		
		query.setParameter("sender", sender.getId());
		query.setParameter("receiver", receiver.getId());
		return query.uniqueResult();
		
	}


	@Override
	public void saveChat(Chat chat) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(chat);
		
	}


	@Override
	public List<Chat> getAllChatsByIdForUser(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Chat> query = session.createQuery(
			    "FROM Chat c WHERE c.userSender.id = :id OR c.userReceiver.id = :id",
			    Chat.class
			);
		query.setParameter("id", id);
		return query.getResultList();
		
	}


	@Override
	public Chat getChatById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Chat.class, id);
	}

}
