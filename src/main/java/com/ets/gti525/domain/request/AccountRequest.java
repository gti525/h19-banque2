package com.ets.gti525.domain.request;

public class AccountRequest {
	
	private long number;
	private int monthExp;
	private int yearExp;
	private String authHash;
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
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
	public String getAuthHash() {
		return authHash;
	}
	public void setAuthHash(String authHash) {
		this.authHash = authHash;
	}
	
	

}
