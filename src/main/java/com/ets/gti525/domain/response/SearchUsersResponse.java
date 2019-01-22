package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

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
