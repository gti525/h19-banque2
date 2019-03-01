package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

/**
 * Description : Class representing a request for a credit card payment (used by a controller).
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 23-01-2019
 */
public class CreditCardPaymentRequest {
	
	@NotNull
	private String sourceDebitCardNumber;
	@NotNull
	private String targetCreditCardNumber;
	@NotNull
	private double amount;
	
	public String getSourceDebitCardNumber() {
		return sourceDebitCardNumber;
	}
	public void setSourceDebitCardNumber(String sourceDebitCardNumber) {
		this.sourceDebitCardNumber = sourceDebitCardNumber;
	}
	public String getTargetCreditCardNumber() {
		return targetCreditCardNumber;
	}
	public void setTargetCreditCardNumber(String targetCreditCardNumber) {
		this.targetCreditCardNumber = targetCreditCardNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}