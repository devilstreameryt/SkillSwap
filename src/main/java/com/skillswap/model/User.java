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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "users")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "f_name")
	private String firstName;
	
	@Column(name = "l_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private UserDetails userDetails;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<SkillsWanted> skillsWanteds = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<SkillsOffered> skillsOffereds = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "userReceiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chatsAsReceiver = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> sentMessages = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notifications> sentNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notifications> receivedNotifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> sentRequests = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> receivedRequests = new ArrayList<>();

	
	
	public User() {
	}
	
	
	public User(String f_name, String l_name, String email, String password) {
		this.firstName = f_name;
		this.lastName = l_name;
		this.email = email;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public UserDetails getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	


	public List<SkillsOffered> getSkillsOffereds() {
	    return skillsOffereds;
	}


	public void setSkillsOffereds(List<SkillsOffered> skillsOffereds) { 
		if (this.skillsOffereds == null) { 
			this.skillsOffereds = new ArrayList<>(); } 
		else { this.skillsOffereds.clear(); } 
		if (skillsOffereds != null) { 
			for (SkillsOffered so : skillsOffereds) 
			{ so.setUser(this); } this.skillsOffereds.addAll(skillsOffereds); 
			} }


	
	

	public List<SkillsWanted> getSkillsWanteds() {
	    return skillsWanteds;
	}


	public void setSkillsWanteds(List<SkillsWanted> skillsWanteds) {
		if (this.skillsWanteds == null) {
	        this.skillsWanteds = new ArrayList<>();
	    } else {
	        this.skillsWanteds.clear();
	    }
	    if (skillsWanteds != null) {
	        for (SkillsWanted so : skillsWanteds) {
	            so.setUser(this);
	        }
	        this.skillsWanteds.addAll(skillsWanteds);
	    }
	}


	public String getFullName()
	{
		return this.firstName +" " + this.lastName;
	}


	public List<Chat> getChatsAsReceiver() {
		return chatsAsReceiver;
	}


	public void setChatsAsReceiver(List<Chat> chatsAsReceiver) {
		this.chatsAsReceiver = chatsAsReceiver;
	}


	public List<Message> getSentMessages() {
		return sentMessages;
	}


	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}


	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}


	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}


	public List<Notifications> getSentNotifications() {
		return sentNotifications;
	}


	public void setSentNotifications(List<Notifications> sentNotifications) {
		this.sentNotifications = sentNotifications;
	}


	public List<Notifications> getReceivedNotifications() {
		return receivedNotifications;
	}


	public void setReceivedNotifications(List<Notifications> receivedNotifications) {
		this.receivedNotifications = receivedNotifications;
	}


	public List<Request> getSentRequests() {
		return sentRequests;
	}


	public void setSentRequests(List<Request> sentRequests) {
		this.sentRequests = sentRequests;
	}


	public List<Request> getReceivedRequests() {
		return receivedRequests;
	}


	public void setReceivedRequests(List<Request> receivedRequests) {
		this.receivedRequests = receivedRequests;
	}
	
	
	
	
	
	
	
}
