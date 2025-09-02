package com.skillswap.dao;

import java.util.List;

import com.skillswap.model.Request;

public interface RequestDAO {
	
	public void saveRequest(Request request);
	public boolean existsBySenderAndReceiver(Integer senderId, Integer receiverId);
	public List<Request> findByReceiver(Integer receiverId);
	public Request getRequestById(int requestId);

}
