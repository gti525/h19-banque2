package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

public class TransactionResponse extends AbstractResponse{

	public TransactionResponse(HttpStatus status, String result) {
		super(status);
		this.result = result;
	}

	public static final String ACCEPTED = "ACCEPTED";
	public static final String DECLINED = "DECLINED";
	public static final String DECLINED_INSUFFICIANT_FUNDS = "DECLINED-INSUFFICIANT-FUNDS";
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	
}
