package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class ChallengeValidationResponse extends AbstractResponse {

	private String token;
	
	public ChallengeValidationResponse(HttpStatus status) {
		super(status);
	}
	
	public ChallengeValidationResponse(HttpStatus status, String token) {
		super(status);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
