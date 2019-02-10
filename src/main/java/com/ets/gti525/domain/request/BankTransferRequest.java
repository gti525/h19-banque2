package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

public class BankTransferRequest {
	
	@NotNull
	private long sourceAccountNumber;
	
	@NotNull
	private long targetAccountNumber;
	
	@NotNull
	private double amount;


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(long sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public long getTargetAccountNumber() {
		return targetAccountNumber;
	}

	public void setTargetAccountNumber(long targetAccountNumber) {
		this.targetAccountNumber = targetAccountNumber;
	}
	
	
	

}
