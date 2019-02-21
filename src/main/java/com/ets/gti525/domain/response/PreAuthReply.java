package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing a response of a pre-authorization request.
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 02-02-2019
 */
public class PreAuthReply extends AbstractResponse{

	public PreAuthReply(HttpStatus status, String result, Integer transactionId) {
		super(status);
		this.result = result;
		this.transactionId = transactionId;
	}

	public static final String ACCEPTED = "ACCEPTED";
	public static final String DECLINED = "DECLINED";
	public static final String DECLINED_INSUFFICIANT_FUNDS = "DECLINED-INSUFFICIANT-FUNDS";
	
	private String result;
	private Integer transactionId;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}	
}