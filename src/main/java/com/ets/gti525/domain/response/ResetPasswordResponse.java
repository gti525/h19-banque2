package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class ResetPasswordResponse extends AbstractResponse {

	private String message;

	public ResetPasswordResponse(HttpStatus status,
			String message) {
		super(status);
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}