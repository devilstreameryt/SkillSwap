package com.skillswap.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "chats")
public class Chat {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_sender", referencedColumnName = "id")
	private User userSender;
	
	@ManyToOne
	@JoinColumn(name = "user_receiver", referencedColumnName = "id")
	private User userReceiver;
	
	
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL ,orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("timestamp ASC")
	private List<Message> messages;
	
	
	
	public Chat() {}
	
	



	public Chat(User userSender, User userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}





	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public User getUserSender() {
		return userSender;
	}



	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}



	public User getUserReceiver() {
		return userReceiver;
	}



	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}
	
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	
	
	
	
	

}
