package com.ets.gti525.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CreditCard {
	@Id
	protected long nbr;
	
	protected double balance;
	
	
	private double cardLimit = 10000;
	
	private int monthExp;
	private int yearExp;
	private int cvv;
	
	@JsonIgnore
	@OneToOne
	private User owner;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "CREDIT_CARD_NBR")
	protected List<CreditCardTransaction> transactionList;
	
	
	
	public double getLimit() {
		return cardLimit;
	}
	public void setLimit(double limit) {
		this.cardLimit = limit;
	}
	
	public long getNumber() {
		return nbr;
	}

	public void setNumber(long number) {
		this.nbr = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public boolean addTransaction(CreditCardTransaction transaction) {
		if(balance + transaction.getAmount() <= cardLimit) {
			transactionList.add(transaction);
			balance = balance + transaction.getAmount();
			return true;
		}
			
		return false;
	}
	public List<CreditCardTransaction> getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(List<CreditCardTransaction> transactionList) {
		this.transactionList = transactionList;
	}
	public long getNbr() {
		return nbr;
	}
	public void setNbr(long nbr) {
		this.nbr = nbr;
	}
	public double getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	

}
