package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;


public class BankTransferRequestBank1 {
	
	private long senderAccountNo;
	
	@NotNull
	private long receiverAccountNo;
	
	@NotNull
	private double amount;
	
	public BankTransferRequestBank1(BankTransferRequest request) {
		this.senderAccountNo = request.getSourceAccountNumber();
		this.receiverAccountNo = request.getTargetAccountNumber();
		this.amount = request.getAmount();
	}

	public long getSenderAccountNo() {
		return senderAccountNo;
	}

	public void setSenderAccountNo(long senderAccountNo) {
		this.senderAccountNo = senderAccountNo;
	}

	public long getReceiverAccountNo() {
		return receiverAccountNo;
	}

	public void setReceiverAccountNo(long receiverAccountNo) {
		this.receiverAccountNo = receiverAccountNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}





}
