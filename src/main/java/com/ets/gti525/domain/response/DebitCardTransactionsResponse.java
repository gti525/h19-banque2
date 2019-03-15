package com.ets.gti525.domain.response;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.ets.gti525.domain.entity.DebitCardTransaction;

/**
 * Description : Class representing a response containing informations about debit card transactions. 
 * (Used by controllers and services)
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 26-01-2019
 */
public class DebitCardTransactionsResponse extends AbstractResponse {
	
private List<DebitCardTransactionResponseItem> transactions;
	
	public DebitCardTransactionsResponse(HttpStatus status, List<DebitCardTransaction> transactionsInput) {
		super(status);
		
		this.transactions = new ArrayList<DebitCardTransactionResponseItem>();
		
		double cumulativeSum = 0;
		
		for(DebitCardTransaction t : transactionsInput) {
			cumulativeSum += t.getAmount();
			DebitCardTransactionResponseItem tItem = new DebitCardTransactionResponseItem(t);
			tItem.setCumulativeSum(cumulativeSum);
			
			this.transactions.add(tItem);
		}
		
	}
	
	public List<DebitCardTransactionResponseItem> getTransactions(){
		return this.transactions;
	}
	
	
	public class DebitCardTransactionResponseItem{
		

		private int id;
		private Timestamp timestamp;
		private String description;
		private double amount;
		private double cumulativeSum;
		
		public DebitCardTransactionResponseItem(DebitCardTransaction trans){
			this.id = trans.getId();
			this.timestamp = trans.getTimestamp();
			this.description = trans.getDescription();
			this.amount = trans.getAmount();
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

		public double getCumulativeSum() {
			return cumulativeSum;
		}
		
		
		
	}
}
