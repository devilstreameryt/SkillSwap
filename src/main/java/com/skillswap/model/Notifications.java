package com.skillswap.model;

import java.util.ArrayList;
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
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	
	@OneToMany(mappedBy = "notifications", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Request> requests;
	
	
	public Notifications() {}

	public Notifications(User sender, User receiver, Skill skill, List<Request> requests) {
		this.sender = sender;
		this.receiver = receiver;
		this.skill = skill;
		this.requests = requests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	
	
	public void addRequest(Request request ) {
		if(this.requests == null) {
			this.requests = new ArrayList<Request>();
		}
		
		this.requests.add(request);
		request.setNotifications(this);
	}
	
	
	
	
	
	
}
