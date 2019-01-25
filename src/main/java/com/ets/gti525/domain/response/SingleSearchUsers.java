package com.ets.gti525.domain.response;

public class SingleSearchUsers {
	private String firstName, lastname, accountNumber, cardNumber;
	
	public SingleSearchUsers (String firstName, String lastName, String accountNumber, String cardNumber) {
		this.firstName = firstName;
		this.lastname = lastName;
		this.accountNumber = accountNumber;
		this.cardNumber = cardNumber;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getCardNumber() {
		return this.cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
