package com.skillswap.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "chat_id", referencedColumnName =  "id")
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name = "sender_id", referencedColumnName = "id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id", referencedColumnName = "id")
	private User receiver;
	
	@Column(name = "message_text", length = 1000)
	private String messageText;
	
	@Column(name = "timestamp", nullable = true, updatable = false)
	private LocalDateTime timestamp;
	
	
	
	public Message() {}

	
	
	


	public Message(Chat chatId, User sender, User receiver, String messageText, LocalDateTime time) {
		this.chat = chatId;
		this.sender = sender;
		this.receiver = receiver;
		this.messageText = messageText;
		this.timestamp = time;
	}






	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	



	public Chat getChat() {
		return chat;
	}






	public void setChat(Chat chat) {
		this.chat = chat;
	}






	public User getSender() {
		return sender;
	}



	public void setSender(User sender) {
		this.sender = sender;
	}



	public User getReceiver() {
		return receiver;
	}



	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}



	public String getMessageText() {
		return messageText;
	}



	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}






	public LocalDateTime getTimestamp() {
		return timestamp;
	}






	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}



	
	protected void onCreate() {
		this.timestamp = LocalDateTime.now();
	}

	
	
	
	
	
	
	
	

}
