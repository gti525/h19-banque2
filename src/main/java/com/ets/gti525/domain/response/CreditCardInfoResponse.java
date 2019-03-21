package com.ets.gti525.domain.response;

import java.text.NumberFormat;

import org.springframework.http.HttpStatus;

/**
 * Description : Class representing a response containing informations about a credit card. (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 25-01-2019
 */
public class CreditCardInfoResponse extends AbstractResponse {

	private long nbr;
	private double balance;
	private double cardLimit = 10000;
	private int monthExp;
	private int yearExp; 
	
	public CreditCardInfoResponse(HttpStatus status) {
		super(status);
	}
	
	public CreditCardInfoResponse(HttpStatus status, long nbr,
			double balance, double cardLimit, int monthExp, int yearExp) {
		super(status);
		this.nbr = nbr;
		this.balance = balance;
		this.cardLimit = cardLimit;
		this.monthExp = monthExp;
		this.yearExp = yearExp;
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
	
	public double getCardLimit() {
		return this.cardLimit;
	}
	
	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
	
	public int getMonthExp() {
		return this.monthExp;
	}
	
	public void setMonthExp(int monthExp) {
		this.monthExp = monthExp;
	}
	
	public int getYearExp() {
		return this.yearExp;
	}
	
	public void setYearExp(int yearExp) {
		this.yearExp = yearExp;
	}
	
	public String getBalanceAsString() {
		return NumberFormat.getCurrencyInstance().format(balance);
	}
	
	public String getCardLimitAsString() {
		return NumberFormat.getCurrencyInstance().format(cardLimit);
	}
}