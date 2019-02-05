package com.ets.gti525.domain.response;

import org.springframework.http.HttpStatus;

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
