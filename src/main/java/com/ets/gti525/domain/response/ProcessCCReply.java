package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class ProcessCCReply extends AbstractResponse {
	
	public static final String STATUS_COMMITED = "COMMITED";
	public static final String STATUS_CANCELLED = "CANCELLED";
	
	private String result;

	public ProcessCCReply(HttpStatus status, String result) {
		super(status);
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

}
