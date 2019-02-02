package com.ets.gti525.domain.request;

import javax.validation.constraints.NotNull;

public class AccountRequest {
	@NotNull
	private String number;
	@NotNull
	private int monthExp;
	@NotNull
	private int yearExp;
	@NotNull
	private String cvv;
	

	public int getMonthExp() {
		return monthExp;
	}
	public void setMonthExp(int monthExp) {
		this.monthExp = monthExp;
	}
	public int getYearExp() {
		return yearExp;
	}
	public void setYearExp(int yearExp) {
		this.yearExp = yearExp;
	}
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

	
	

}
