package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class ChallengeResponse extends AbstractResponse {
	
	private String challenge;

	public ChallengeResponse(HttpStatus status) {
		super(status);
	}
	
	public ChallengeResponse(HttpStatus status, String challenge) {
		super(status);
		this.challenge = challenge;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

}
