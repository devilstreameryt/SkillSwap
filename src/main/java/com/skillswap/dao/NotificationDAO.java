package com.skillswap.dao;

import java.util.List;

import com.skillswap.model.Notifications;

public interface NotificationDAO {
	
	public void saveNotification(Notifications notification);
	public List<Notifications> searchBySender(Integer senderId);

}
