package com.ets.gti525.domain.response;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.CreditCardTransaction;

/**
 * Description : Class representing a response containing informations about credit card transactions. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 26-01-2019
 */
public class CreditCardTransactionsResponse extends AbstractResponse {
	
	private List<CreditCardTransactionResponseItem> transactions;
	
	public CreditCardTransactionsResponse(HttpStatus status, List<CreditCardTransaction> transactionsInput) {
		super(status);
		
		this.transactions = new ArrayList<CreditCardTransactionResponseItem>();
		
		double cumulativeSum = 0;
		
		for(CreditCardTransaction t : transactionsInput) {
			cumulativeSum += t.getAmount();
			CreditCardTransactionResponseItem tItem = new CreditCardTransactionResponseItem(t);
			tItem.setCumulativeSum(cumulativeSum);
			
			this.transactions.add(tItem);
		}
		
	}
	
	public List<CreditCardTransactionResponseItem> getTransactions(){
		return this.transactions;
	}
	
	
	public class CreditCardTransactionResponseItem{
		

		private int id;
		private Timestamp timestamp;
		private String description;
		private double amount;
		private boolean isPreauth;
		private double cumulativeSum;
		
		public CreditCardTransactionResponseItem(CreditCardTransaction trans){
			this.id = trans.getId();
			this.timestamp = trans.getTimestamp();
			this.description = trans.getDescription();
			this.amount = trans.getAmount();
			this.isPreauth = trans.isPreauth();
		}
		
		public String getTimestampAsString() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
		}
		
		public String getAmountAsString() {
			return NumberFormat.getCurrencyInstance().format(amount);
		}

		public String getCumulativeSumAsString() {
			return NumberFormat.getCurrencyInstance().format(cumulativeSum);
		}

		public void setCumulativeSum(double cumulativeSum) {
			this.cumulativeSum = cumulativeSum;
		}

		public int getId() {
			return id;
		}

		public Timestamp getTimestamp() {
			return timestamp;
		}

		public String getDescription() {
			return description;
		}

		public double getAmount() {
			return amount;
		}

		public boolean isPreauth() {
			return isPreauth;
		}

		public double getCumulativeSum() {
			return cumulativeSum;
		}
		
		
		
	}
}
