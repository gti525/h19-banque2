package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing a response of a transaction processing request.
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 02-02-2019
 */
public class ProcessCCReply extends AbstractResponse {
	
	public static final String STATUS_COMMITED = "COMMITTED";
	public static final String STATUS_CANCELLED = "CANCELLED";
	public static final String STATUS_DECLINED_BY_3RD_PARTY_BANK = "STATUS_DECLINED_BY_3RD_PARTY_BANK";
	
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