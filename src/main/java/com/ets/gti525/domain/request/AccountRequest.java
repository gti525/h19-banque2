package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

/**
 * Description : Class representing a request for an account (used by a controller).
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 16-01-2019
 */
public class AccountRequest {
	
	@NotNull
	private String cardholderName;
	@NotNull
	private String number;
	@NotNull
	private String exp;
	@NotNull
	private String cvv;
	
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCardholderName() {
		return cardholderName;
	}
	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
}
