package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.CreditCardTransaction;

/**
 * Description : Class representing a response containing informations about credit card transactions. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 26-01-2019
 */
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
