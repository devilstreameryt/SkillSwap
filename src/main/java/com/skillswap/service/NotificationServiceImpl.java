package com.skillswap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillswap.dao.NotificationDAO;
import com.skillswap.model.Notifications;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDAO notificationDAO;
	
	public NotificationServiceImpl() {}
	
	@Override
	@Transactional
	public void saveNotification(Notifications notification) {
		notificationDAO.saveNotification(notification);
		
	}

	@Override
	@Transactional
	public List<Notifications> findBySender(Integer senderId) {
		return notificationDAO.searchBySender(senderId);
	}

}
