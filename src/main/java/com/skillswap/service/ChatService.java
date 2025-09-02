package com.skillswap.service;

import java.util.List;

import com.skillswap.model.Chat;
import com.skillswap.model.User;

public interface ChatService {
	
	public void saveChat(Chat chat);
	public Chat findByUsers(User sender,User receiver);
	public List<Chat> getAllChatsByIdForUser(Integer id);
	public Chat getChatById(int id);
}
