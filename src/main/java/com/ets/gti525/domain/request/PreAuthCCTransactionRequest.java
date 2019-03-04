package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

/**
 * Description : Class representing a request for a pre-authorization for an external transaction (used by a controller).
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 02-02-2019
 */
public class PreAuthCCTransactionRequest {
	@NotNull
	private double amount;
	
	@NotNull
	private String merchantDesc;
	
	@NotNull
	private Integer merchantAccountNumber;
	
	@NotNull
	private AccountRequest account;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public AccountRequest getAccount() {
		return account;
	}
	public void setAccount(AccountRequest account) {
		this.account = account;
	}
	public String getMerchantDesc() {
		return merchantDesc;
	}
	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}
	public Integer getMerchantAccountNumber() {
		return merchantAccountNumber;
	}
	public void setMerchantAccountNumber(Integer merchantAccountNumber) {
		this.merchantAccountNumber = merchantAccountNumber;
	}
}