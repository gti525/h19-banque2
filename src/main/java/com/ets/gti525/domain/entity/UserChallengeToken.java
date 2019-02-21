package com.ets.gti525.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserChallengeToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private int challengeId;
	private String token;
	private Date timestamp;
	
	public UserChallengeToken() {
		
	}
	
	public UserChallengeToken (String username, int challengeId, String token, Date timestamp) {
		this.username = username;
		this.challengeId = challengeId;
		this.token = token;
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getChallengeId() {
		return challengeId;
	}
	
	public void setChallengeId(int challengeId) {
		this.challengeId = challengeId;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
