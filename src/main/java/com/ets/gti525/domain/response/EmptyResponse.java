package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing an empty response containing an info message. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 09-02-2019
 */
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