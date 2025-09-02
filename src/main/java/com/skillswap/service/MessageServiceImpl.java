package com.skillswap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillswap.dao.MessageDAO;
import com.skillswap.model.Message;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageDAO messageDao;
	
	public MessageServiceImpl() {}
	

	@Override
	@Transactional
	public void saveMessage(Message message) {
		messageDao.addMessage(message);
		
	}

}
