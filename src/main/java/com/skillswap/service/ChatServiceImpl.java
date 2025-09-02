package com.skillswap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillswap.dao.ChatDAO;
import com.skillswap.model.Chat;
import com.skillswap.model.User;
@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	public ChatDAO chatDao;
	
	public ChatServiceImpl() {}
	
	
	@Override
	@Transactional
	public Chat findByUsers(User sender, User receiver) {
		return chatDao.findByUsers(sender, receiver);
	}


	@Override
	@Transactional
	public void saveChat(Chat chat) {
		chatDao.saveChat(chat);
		
	}


	@Override
	@Transactional
	public List<Chat> getAllChatsByIdForUser(Integer id) {
		return chatDao.getAllChatsByIdForUser(id);
	}


	@Override
	@Transactional
	public Chat getChatById(int id) {
		return chatDao.getChatById(id);
	}

}
