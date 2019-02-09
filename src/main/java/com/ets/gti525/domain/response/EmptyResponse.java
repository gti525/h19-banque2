package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class EmptyResponse extends AbstractResponse{
	
	private String info;

	public EmptyResponse(HttpStatus status, String info) {
		super(status);
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
	

}
