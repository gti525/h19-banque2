package com.ets.gti525.domain.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditCardTransaction {
	
	public static final String PAYMENT_OF_CREDIT_CARD = "Paiement de la carte de crédit - Merci";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Timestamp timestamp;
	private String description;
	private double amount;
	private boolean isPreauth;
	
	@JsonIgnore
	@OneToOne
	private CreditCard creditCard;
	
	
	


	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public boolean isPreauth() {
		return isPreauth;
	}
	public void setPreauth(boolean isPreauth) {
		this.isPreauth = isPreauth;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
