package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing a response after the creation of a user. (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 20-01-2019
 */
public class CreateUserResponse extends AbstractResponse {

	private String message;
	private String accountNumber;
	private String password;
	private String creditCardNumber;

	public CreateUserResponse(HttpStatus status,
			String message,
			String accountNumber,
			String password,
			String creditCardNumber) {
		super(status);
		this.message = message;
		this.accountNumber = accountNumber;
		this.password = password;
		this.creditCardNumber = creditCardNumber;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
}