package com.skillswap.service;

import java.util.List;

import com.skillswap.model.Notifications;

public interface NotificationService {
	
	public void saveNotification(Notifications notification);
	public List<Notifications> findBySender(Integer senderId);

}
