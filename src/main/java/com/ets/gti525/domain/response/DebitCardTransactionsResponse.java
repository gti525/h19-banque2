package com.ets.gti525.domain.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.DebitCardTransaction;

/**
 * Description : Class representing a response containing informations about debit card transactions. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 26-01-2019
 */
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
