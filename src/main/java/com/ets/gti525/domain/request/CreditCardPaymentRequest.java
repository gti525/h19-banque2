package com.ets.gti525.domain.request;

public class CreditCardPaymentRequest {
	
	private String sourceDebitCardNumber;
	private String targetCreditCardNumber;
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
