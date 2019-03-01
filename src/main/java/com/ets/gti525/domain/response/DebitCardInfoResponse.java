package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing a response containing informations about a debit card. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 25-01-2019
 */
public class DebitCardInfoResponse extends AbstractResponse {

	private long nbr;
	private double balance;
	
	public DebitCardInfoResponse(HttpStatus status) {
		super(status);
	}
	
	public DebitCardInfoResponse(HttpStatus status, long nbr, double balance) {
		super(status);
		this.nbr = nbr;
		this.balance = balance;
	}
	
	public long getNbr() {
		return this.nbr;
	}
	
	public void setNbr(long nbr) {
		this.nbr = nbr;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
