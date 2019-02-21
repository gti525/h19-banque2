package com.ets.gti525.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Description : Entity class representing a partner bank with our bank.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 09-02-2019
 */
@Entity
public class PartnerBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String apiKey;
	private String name;
	private String accountPrefix;
	private String postUrlToUse;
	private String apiKeyToUse;
	
	
	
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountPrefix() {
		return accountPrefix;
	}
	public void setAccountPrefix(String accountPrefix) {
		this.accountPrefix = accountPrefix;
	}
	public String getPostUrlToUse() {
		return postUrlToUse;
	}
	public void setPostUrlToUse(String postUrlToUse) {
		this.postUrlToUse = postUrlToUse;
	}
	public String getApiKeyToUse() {
		return apiKeyToUse;
	}
	public void setApiKeyToUse(String apiKeyToUse) {
		this.apiKeyToUse = apiKeyToUse;
	}
	
	
	
	
}
