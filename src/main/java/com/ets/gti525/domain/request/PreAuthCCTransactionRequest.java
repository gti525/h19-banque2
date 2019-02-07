package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

public class PreAuthCCTransactionRequest {
	
	
	@NotNull
	private double amount;
	
	@NotNull
	private String merchant;
	
	@NotNull
	private AccountRequest account;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	public AccountRequest getAccount() {
		return account;
	}
	public void setAccount(AccountRequest account) {
		this.account = account;
	}

}
