package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class CreateUserResponse extends AbstractResponse {

	private String message;
	private String accountNumber;
	private String password;

	public CreateUserResponse(HttpStatus status,
			String message,
			String accountNumber,
			String password) {
		super(status);
		this.message = message;
		this.accountNumber = accountNumber;
		this.password = password;
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

}
