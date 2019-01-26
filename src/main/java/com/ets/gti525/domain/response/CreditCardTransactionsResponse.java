package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.CreditCardTransaction;

public class CreditCardTransactionsResponse extends AbstractResponse {
	
	private List<CreditCardTransaction> transactions;
	
	public CreditCardTransactionsResponse(HttpStatus status, List<CreditCardTransaction> transactions) {
		super(status);
		this.transactions = transactions;
	}
	
	public List<CreditCardTransaction> getTransactions(){
		return this.transactions;
	}
	
	public void setTransactions(List<CreditCardTransaction> transactions) {
		this.transactions = transactions;
	}
}
