package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.DebitCardTransaction;

public class DebitCardTransactionsResponse extends AbstractResponse {
	
	private List<DebitCardTransaction> transactions;
	
	public DebitCardTransactionsResponse(HttpStatus status, List<DebitCardTransaction> transactions) {
		super(status);
		this.transactions = transactions;
	}
	
	public List<DebitCardTransaction> getTransactions(){
		return this.transactions;
	}
	
	public void setTransactions(List<DebitCardTransaction> transactions) {
		this.transactions = transactions;
	}
}
