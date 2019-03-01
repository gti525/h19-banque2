package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Description : Class representing a abstract response. (Used by controllers and services)
 * Each response class extends this class.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 20-01-2019
 */
public abstract class AbstractResponse {

	@JsonIgnore
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