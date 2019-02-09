package com.ets.gti525.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PartnerBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String apiKey;
	private String name;
	private String accountPrefix;
	
	
	
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
	
	
}
