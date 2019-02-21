package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing the result of a users research.
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 21-01-2019
 */
public class SearchUsersResponse extends AbstractResponse {

	private List<SingleSearchUsers> searchResult;

	public SearchUsersResponse(HttpStatus status, List<SingleSearchUsers> searchResult) {
		super(status);
		this.searchResult = searchResult;
	}

	public List<SingleSearchUsers> getSearchResult(){
		return this.searchResult;
	}
	
	public void setSearchResult(List<SingleSearchUsers> searchResult) {
		this.searchResult = searchResult;
	}
}
