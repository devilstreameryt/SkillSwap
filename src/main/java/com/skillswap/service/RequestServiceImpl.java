package com.skillswap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillswap.dao.RequestDAO;
import com.skillswap.model.Request;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired
	private RequestDAO requestDao;
	
	
	public RequestServiceImpl() {}
	
	@Override
	@Transactional
	public void saveRequest(Request request) {
		requestDao.saveRequest(request);
		
	}

	@Override
	@Transactional
	public boolean existsBySenderAndReceiver(Integer senderId, Integer receiverId) {
		return requestDao.existsBySenderAndReceiver(senderId, receiverId);
	}

	@Override
	@Transactional
	public List<Request> findByReceiver(Integer receiverId) {
		return requestDao.findByReceiver(receiverId);
	}

	@Override
	@Transactional
	public Request getRequestById(int requestId) {
		return requestDao.getRequestById(requestId);
	}

}
