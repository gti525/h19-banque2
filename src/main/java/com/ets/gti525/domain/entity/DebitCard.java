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
public class DebitCard {

	@Id
	private long nbr;
	
	private double balance;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEBIT_CARD_NBR")
	private List<DebitCardTransaction> transactionList;
	
	@JsonIgnore
	@OneToOne
	private User owner;

	public long getNbr() {
		return nbr;
	}

	public void setNbr(long nbr) {
		this.nbr = nbr;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<DebitCardTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<DebitCardTransaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	public boolean addTransaction(DebitCardTransaction transaction) {
		if(transaction.getAmount() > balance)
			return false;
		
		balance = balance - transaction.getAmount();
		transactionList.add(transaction);
		return true;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
}
