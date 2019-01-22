package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public abstract class AbstractResponse {

	private HttpStatus status;
	
	public AbstractResponse(HttpStatus status) {
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
