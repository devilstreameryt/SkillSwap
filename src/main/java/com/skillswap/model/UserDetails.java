package com.skillswap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "location")
	private String location;
	
	@Lob
	@Column(name = "photo", columnDefinition="LONGBLOB")
	private byte[] photo;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "photo_type")
	public String photoType;
	
	
	
	public UserDetails() {}
	
	
	


	public UserDetails(String bio, String location, byte[] photo) {
		this.bio = bio;
		this.location = location;
		this.photo = photo;
	}





	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getBio() {
		return bio;
	}


	public void setBio(String bio) {
		this.bio = bio;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}





	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}





	public String getPhotoType() {
		return photoType;
	}





	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	
	
	
	
	
	

}
